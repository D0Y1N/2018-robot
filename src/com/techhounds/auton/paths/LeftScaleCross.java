package com.techhounds.auton.paths;

import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveAngle;
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
public class LeftScaleCross extends CommandGroup {

    public LeftScaleCross() {
    	// TODO timeouts
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	
    	// drive across
    	addSequential(new DriveStraightRamp(40, 0.3, 1, 0));
    	addSequential(new DriveStraight(100, 1, 0));
    	addSequential(new DriveStraightRamp(30, 1, 0.5, 0));
//    	addSequential(new TurnToAngleGyro(-85), 3);
    	addSequential(new DriveAngle(-75, 0.6, 0.2));
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_MID), 0.5));
//    	addSequential(new DriveStraight(195, 0.6, -90)); // TODO ramping speedup
    	
    	addSequential(new DriveStraightRamp(45, 0.6, 1, -90));
    	addSequential(new DriveStraightRamp(45, 1, 0.5, -90));
    	addSequential(new DriveStraightRamp(55, 0.5, 1, -90));
    	addSequential(new DriveStraightRamp(55, 1, 0.3, -90));
    	
    	// put in scale
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new TurnToAngleGyro(20, 1.5, 0.3));
    	addSequential(new DriveStraight(40, 0.4, 20), 2);
//    	addSequential(new WaitCommand(0.5)); // TODO remove
    	addSequential(new SetIntakePower(-0.35), 0.5);
    	
    	// grab second cube
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addSequential(new TurnToAngleGyro(166.5, 1.5, 0.3));
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	addSequential(new DriveStraight(20, 0.3, 166.5), 3); // TODO speedup
    	addSequential(new CollectCube(25));
    	
    	// place second
    	addSequential(new DriveStraight(-40, -0.4));
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new TurnToAngleGyro(20, 1, 0.3));
    	addSequential(new SetIntakePower(-0.35), 0.5);
    }
}
