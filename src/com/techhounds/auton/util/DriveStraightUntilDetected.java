package com.techhounds.auton.util;

import com.techhounds.Robot;

public class DriveStraightUntilDetected extends DriveStraight {
	
	
	public DriveStraightUntilDetected(double inches) {
		super(inches);
	}
	
	public DriveStraightUntilDetected(double inches, double power) {
		super(inches, power);
	}

	private int detectCounts = 0;

    protected void execute() {
    	super.execute();
    	
    	if (Robot.intake.isCubeDetected()) {
    		detectCounts++;
    	} else {
    		detectCounts = 0;
    	}
    }

    protected boolean isFinished() {
    	return super.isFinished() || detectCounts > 5;
    }
}
