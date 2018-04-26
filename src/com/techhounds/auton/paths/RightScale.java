package com.techhounds.auton.paths;

import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightRamp;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	// TODO timeouts
    	
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5)); //wait a little longer?

    	// drive up & curve
    	addSequential(new DriveStraightRamp(40, 0.4, 1));
    	addSequential(new DriveStraight(130, 1, -10), 4);
    	addSequential(new DriveStraightRamp(100, 1, 0.2));
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.55), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.75));
    	addSequential(new TurnToAngleGyro(-170), 2);
    	
    	// grab second cube
    	addSequential(new DriveStraight(25, 0.45), 3);
    	addSequential(new CollectCube(25), 2);
    	
//		// retry collection
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//
    	// place second cube
    	addSequential(new TurnToAngleGyro(-167), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE)); //wait a little longer?
    	addSequential(new DriveStraight(-50, -0.35), 2);
    	addSequential(new TurnToAngleGyro(-15, 2, 0.28)); //lower rotation power here?
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.75));
    	addSequential(new TurnToAngleGyro(-158), 2);
    	
    	// grab third cube
    	addSequential(new DriveStraight(25, 0.45), 3);
    	addSequential(new CollectCube(25), 2);
    }
}
