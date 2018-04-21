package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveAngle;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightRamp;
import com.techhounds.auton.util.DriveStraightUntilDetected;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.IntakeUntilDetected;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftSwitch extends CommandGroup {

    public CenterLeftSwitch() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1.5));
    	
    	// drive up to switch
    	addSequential(new DriveAngle(-35, 0.4, 0), 1);
    	addSequential(new DriveStraightRamp(30, 0.4, 1, -35));
    	addSequential(new DriveStraight(15, 1, -35));
    	addSequential(new DriveStraightRamp(50, 1, 0.2, -35));
    	addSequential(new TurnToAngleGyro(-10), 0.5); //
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back up to starting position
    	addSequential(new DriveAngle(-35, 0, -0.4), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraightRamp(-30, -0.4, -1, -35));
    	addSequential(new DriveStraightRamp(-45, -1, -0.2, -35));
    	
    	// grab another one
    	addSequential(new TurnToAngleGyro(0), 1.5); //TODO reduce timeout?
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraightUntilDetected(40, 0.5), 1);
    	addSequential(new DriveStraightUntilDetected(10, 0.3), 0.5);
    	
    	// line up to switch again
    	addSequential(new TurnToAngleGyro(-70), 1);
    	addSequential(new DriveStraight(50, 0.5), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SWITCH));
    	addSequential(new DriveAngle(0, 0, 0.4));
    	
//    	// place second cube
//    	addSequential(new SetIntakePower(-0.5), 0.5);
//    	
//    	// line up for third
//    	addSequential(new DriveAngle(-30, 0, -0.4), 1);
//    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.5));
//    	addSequential(new DriveStraightRamp(-30, -0.4, -1, -30));
//    	addSequential(new DriveStraightRamp(-45, -1, -0.2, -30));
//    	
//    	// grab third
//    	addSequential(new TurnToAngleGyro(0), 1.5); //TODO reduce timeout?
//    	addParallel(new SetElevatorPosition(220000, 5));
//    	addParallel(new GrabCube(), 3);
//    	addParallel(new IntakeUntilDetected(), 3);
//    	addSequential(new DriveStraightUntilDetected(40, 0.5), 1);
//    	addSequential(new DriveStraightUntilDetected(10, 0.3), 0.5);
    }
}
