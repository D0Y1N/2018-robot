package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO: add TalonPID-based distance command
 */
public class DriveStraightRamp extends Command {
	
	private final double startPower;
	private final double endPower;
	private double initialAngle;
	private double initialDistance;
	private double targetDistance;
	
	/**
	 * Drives straight with gyro correction, and linearly ramps output power
	 * from start to end over distance.
	 * 
	 * @param inches
	 * @param startPower
	 * @param endPower
	 */
    public DriveStraightRamp(double inches, double startPower, double endPower) {
    	requires(Robot.drivetrain);
    	this.startPower = startPower;
    	this.endPower = endPower;
    	this.targetDistance = inches;
    }

    protected void initialize() {
    	initialAngle = Robot.gyro.getRotation();
    	initialDistance = Robot.drivetrain.getScaledAverageDistance();
    }

    protected void execute() {
    	double rampValue = (Robot.drivetrain.getScaledAverageDistance() - initialDistance) / targetDistance;
    	double powerRamp = startPower + (rampValue * (endPower - startPower));
    	
    	double setRight = powerRamp;
    	double setLeft = powerRamp;
    	
    	double angleError = Robot.gyro.getRotation() - initialAngle;
    	double angleP = (angleError / 50);
    	
    	// fix for driving backwards
    	if (startPower < 0 && endPower < 0) {
    		angleP *= -1;
    	}
    	
    	Robot.drivetrain.setPower(setRight * (1+angleP), setLeft * (1-angleP));
    }

    protected boolean isFinished() {
    	return Math.abs(Robot.drivetrain.getScaledAverageDistance() - initialDistance) > Math.abs(targetDistance);
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
