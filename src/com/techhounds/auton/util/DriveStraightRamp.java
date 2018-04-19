package com.techhounds.auton.util;

import com.techhounds.Robot;

/**
 * TODO: add TalonPID-based distance command
 */
public class DriveStraightRamp extends DriveStraight {
	
	private final double startPower;
	private final double endPower;
	
	/**
	 * Drives straight with gyro correction, and linearly ramps output power
	 * from start to end over distance.
	 * 
	 * @param inches
	 * @param startPower
	 * @param endPower
	 */
    public DriveStraightRamp(double inches, double startPower, double endPower) {
    	super(inches, startPower);
    	this.startPower = startPower;
    	this.endPower = endPower;
    }
    
    public void execute() {
    	double rampValue = (Robot.drivetrain.getScaledAverageDistance() - initialDistance) / targetDistance;
    	targetPower = startPower + (rampValue * (endPower - startPower));
    	
    	super.execute();
    }
}
