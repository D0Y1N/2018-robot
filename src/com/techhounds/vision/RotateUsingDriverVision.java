package com.techhounds.vision;

import com.techhounds.Robot;
import com.techhounds.RobotUtilities;
import com.techhounds.drivetrain.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateUsingDriverVision extends Command {
	private Drivetrain motors;
	private PIDController ctrl;
	private PullVision vis;
	private final double P = 0, I = 0, D = 0; // TODO: tune
	private double setAngle;

	/**
	 * Rotate robot to orientation of given angle.
	 * NOT relative to current robot orientation.
	 * @param angle in degrees
	 */
    public RotateUsingDriverVision() {
    	motors = Robot.drivetrain;
    	vis = Robot.vision;
    	requires(motors);
    	requires(vis);
    	setAngle = vis.getTurnAngle();
    	ctrl = new PIDController(P, I, D, new PIDSource() {
    		public void setPIDSourceType(PIDSourceType pidSource) {}
    		
    		public PIDSourceType getPIDSourceType() {
    			return PIDSourceType.kDisplacement;
    		}
    		
    		public double pidGet() {
    			return Robot.gyro.getRotation();
    		}
    	}, new PIDOutput() {
    		public void pidWrite(double output) {
    			if (output > .05 && output < Drivetrain.MIN_DRIVE_SPEED)
    				output = Drivetrain.MIN_DRIVE_SPEED;
    			if (output < -0.5 && output > -Drivetrain.MIN_DRIVE_SPEED)
    				output = -Drivetrain.MIN_DRIVE_SPEED;
    			motors.setPower(RobotUtilities.constrain(-output), RobotUtilities.constrain(output));
    		}
    	});
    	ctrl.setAbsoluteTolerance(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("Vision Rotation PID", ctrl);
    	setAngle = vis.getTurnAngle();
    	ctrl.setSetpoint(setAngle + Robot.gyro.getRotation());
    	ctrl.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setAngle = vis.getTurnAngle();
    	ctrl.setSetpoint(setAngle + Robot.gyro.getRotation());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ctrl.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ctrl.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
