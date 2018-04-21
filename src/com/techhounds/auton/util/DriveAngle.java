package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAngle extends Command {

	private final double target;
	private double initial;
	private final double rightPower;
	private final double leftPower;
	
	/**
	 * Drive until a target angle is reached
	 * @param targetAngle absolute target angle
	 * @param rightPower
	 * @param leftPower
	 */
    public DriveAngle(double targetAngle, double rightPower, double leftPower) {
    	requires(Robot.drivetrain);
    	this.target = targetAngle;
    	this.rightPower = rightPower;
    	this.leftPower = leftPower;
    }

    protected void initialize() {
    	initial = Robot.gyro.getRotation();
    }

    protected void execute() {
    	double setRight = rightPower;
    	double setLeft = leftPower;
    	
    	Robot.drivetrain.setPower(setRight, setLeft);
    }

    protected boolean isFinished() {
    	return Math.abs(getError()) < 10;
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
    
    private double getError() {
    	return target - getChange();
    }
    
    private double getChange() {
    	return Robot.gyro.getRotation() - initial;
    }
}
