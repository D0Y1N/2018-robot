package com.techhounds.auton.util;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCubeRetry extends CommandGroup {

    public CollectCubeRetry() {
		addSequential(new DriveStraight(-30, -0.4));
		addSequential(new TurnToAngleGyro(180)); // TODO vision align
		addSequential(new CollectCube(30));
    }
}
