package com.techhounds.auton.util;

import com.techhounds.arm.GrabCube;
import com.techhounds.intake.IntakeUntilDetected;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCube extends CommandGroup {

    public CollectCube(double distance) {
    	addParallel(new GrabCube());
    	addParallel(new IntakeUntilDetected());
    	addSequential(new DriveStraightUntilDetected(distance, 0.35));
    }
}
