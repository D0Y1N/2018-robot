package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadElevatorControl extends Command {
	
	private final XboxController controller;
	private final int axis;

    public GamepadElevatorControl(XboxController controller, int axis) {
    	requires(Robot.powerPack);
    	this.controller = controller;
    	this.axis = axis;
    }

    protected void initialize() {}

    protected void execute() {
    	double power = -Math.pow(controller.getRawAxis(axis), 1);
    	
    	// NOT peak_elevator_forward (0.75) - this is too fast
    	power = (power > 0) ? (power * 0.45) : (power * 0.25);
   
//    	System.out.println(power);
    	Robot.powerPack.setElevatorPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
