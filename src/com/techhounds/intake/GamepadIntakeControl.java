package com.techhounds.intake;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadIntakeControl extends Command {

	private final XboxController controller;
	private final int forwardAxis;
	private final int reverseAxis;

    public GamepadIntakeControl(XboxController controller, int forward, int reverse) {
    	requires(Robot.intake);
    	this.controller = controller;
    	this.forwardAxis = forward;
    	this.reverseAxis = reverse;
    }

    protected void initialize() {}

    protected void execute() {
    	double forward = Math.pow(controller.getRawAxis(forwardAxis), 2);
    	double reverse = Math.pow(controller.getRawAxis(reverseAxis), 2) * 0.5; // limit max power
    	
    	Robot.intake.setPower(0.15 + forward - reverse);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
