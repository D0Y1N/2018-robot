package com.techhounds.auton.paths;

import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.CollectCubeRetryConditional;
import com.techhounds.auton.util.DriveAngle;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightRamp;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleCross extends CommandGroup {

    public RightScaleCross() {
    	// TODO timeouts
    	
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	
    	// drive across
    	addSequential(new DriveStraightRamp(100, 0.2, 1));
    	addSequential(new DriveStraightRamp(100, 1, 0.5));
    	addSequential(new DriveAngle(85, 0.4, 0.6));
    	
    	addSequential(new DriveStraightRamp(45, 0.5, 0.75));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraightRamp(45, 0.75, 0.5));
    	addSequential(new DriveStraightRamp(30, 0.5, 0.65));
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new DriveStraightRamp(65, 0.65, 0));
    	
    	// put in scale
    	addSequential(new TurnToAngleGyro(-30), 2);
    	addSequential(new DriveStraight(12, 0.4), 2);
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new TurnToAngleGyro(-160), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	
    	// grab second cube
    	addSequential(new DriveArc(60, 50, 0.5, 0.4), 2);
    	addSequential(new CollectCube(25), 3);
    	
		// retry collection
		addSequential(new CollectCubeRetryConditional());
		addSequential(new CollectCubeRetryConditional());
		addSequential(new CollectCubeRetryConditional());
		
		// drive back to scale
    	addSequential(new TurnToAngleGyro(-135), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new DriveStraight(-60, -0.4), 3);
    	addSequential(new TurnToAngleGyro(-30), 1);
    	
    	// place in scale
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new TurnToAngleGyro(-140), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
