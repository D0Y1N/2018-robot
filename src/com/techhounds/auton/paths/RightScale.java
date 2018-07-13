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
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5)); // TODO wait

    	// drive up & curve
    	addSequential(new DriveStraightRamp(25, 0.5, 1)); // TODO faster
    	addSequential(new DriveStraight(50, 1));
    	addSequential(new DriveStraight(75, 1, -10), 4);
    	addSequential(new DriveStraightRamp(100, 1, 0.3)); // TODO faster
    	
//    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.35), 0.5);
////    	
//    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.75));
    	addSequential(new TurnToAngleGyro(-175, 3, 0.35), 2);
//    	
////    	// grab second cube
    	addSequential(new DriveStraight(25, 0.45), 3);
    	addSequential(new CollectCube(25), 2);
//////    	
//////		// retry collection
//////		addSequential(new CollectCubeRetryConditional());
//////		addSequential(new CollectCubeRetryConditional());
//////		addSequential(new CollectCubeRetryConditional());
//////
    	// place second cube
    	addSequential(new TurnToAngleGyro(-167), 1); // TODO lower timeout
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new DriveStraight(-50, -0.45), 2); // TODO faster
    	addSequential(new TurnToAngleGyro(-23, 1.5, 0.3)); // FIXME
    	addSequential(new SetIntakePower(-0.45), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.75));
    	addSequential(new TurnToAngleGyro(-150, 3, .3), 2);
    	
    	// grab third cube
    	addSequential(new DriveStraight(35, 0.45), 3); // FIXME
    	addSequential(new CollectCube(35), 2); // FIXME
    }
}
