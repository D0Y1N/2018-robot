package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveAngle;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightRamp;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.tilt.SetTiltPosition;
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
//    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 4));
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_MID), 4));
    	addSequential(new DriveStraight(185, 0.6, -90));
    	
    	// put in scale
    	addSequential(new TurnToAngleGyro(15), 2);
//    	addSequential(new DriveStraight(12, 0.4), 2);
//    	addSequential(new WaitCommand(0.5));
//    	addSequential(new SetIntakePower(-0.35), 1);
//    	
//    	// back off
//    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 1));
//    	addSequential(new DriveStraight(-20, -0.4), 2);
//    	
//    	// align to second cube
//    	addSequential(new TurnToAngleGyro(160), 2);
    }
}
