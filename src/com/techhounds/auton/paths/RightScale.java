package com.techhounds.auton.paths;

import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
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
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1));

    	// drive up & curve
    	addSequential(new DriveStraightRamp(40, 0.3, 1));
    	addSequential(new DriveArc(90, 80, 1, 0.9));
    	addSequential(new DriveStraight(40, 1));
    	addSequential(new DriveStraightRamp(100, 1, 0));
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.4), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new TurnToAngleGyro(-170), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	
    	// grab second cube
    	addSequential(new DriveStraight(30, 0.35), 3);
//    	addSequential(new DriveArc(50, 60, 0.4, 0.5), 2);
    	addSequential(new CollectCube(25), 3);
//    	
//		// retry collection
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//
    	addSequential(new TurnToAngleGyro(-167), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new DriveStraight(-50, -0.35), 2);
    	addSequential(new TurnToAngleGyro(-15), 1.5);
    	
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
//    	// drive back to scale
//    	addSequential(new TurnToAngleGyro(135), 1);
//    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
//    	addSequential(new DriveStraight(-60, -0.4), 3);
//    	addSequential(new TurnToAngleGyro(30), 1);
//    	
//    	// place in scale
//    	addParallel(new SetTiltPosition(Tilt.POS_MID));
//    	addSequential(new SetIntakePower(-0.5), 0.5);
//    	
//    	// back off and reset
//    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
//    	addSequential(new TurnToAngleGyro(140), 2);
//    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
//    	
//    	// grab third cube
//    	addSequential(new DriveArc(80, 60, 0.4, 0.6), 2);
//    	addSequential(new CollectCube(25), 3);
//    	
//		// retry collection
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
    }
}
