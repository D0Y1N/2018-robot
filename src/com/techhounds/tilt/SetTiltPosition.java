package com.techhounds.tilt;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO: use RobotPreferences for range values
 */
public class SetTiltPosition extends Command {

	public enum TiltPosition {
		UP(Tilt.POS_UP),
		MIDDLE(Tilt.POS_MID),
		DOWN(Tilt.POS_DOWN);

		public final double setpoint;
		TiltPosition(double setpoint) {
			this.setpoint = setpoint;
		}
	}

	private double setpoint;

	public SetTiltPosition(TiltPosition position) {
		this(position.setpoint);
		System.out.println("craeted");
	}

	public SetTiltPosition(double setpoint) {
		this.setpoint = setpoint;
		requires(Robot.tilt);
	}

	protected void initialize() {
		System.out.println("initialized");
	}

	protected void execute() {
		double position = Robot.tilt.getPosition();
		double error = setpoint - position;
		double power = 0;
		
		System.out.println(position + " " + setpoint + " " + error);

		if (setpoint > Tilt.POS_DOWN + 20) { // going up
			if (error > 25) {
				power = 1;
			} else if (error > 5) { // need to move up
				power = 0.75;
			} else if (error < -5) { //need to go down
				power = -0.2;
			} else {
				power = 0.15;
			}
		} else { //going down
			if (error < -5) { //need to go down
				double proportion = (position - Tilt.POS_DOWN) / (Tilt.POS_RANGE);
				power = -0.15 - (proportion * 1.25);
			} else {
				power = 0;
			}
		}
		
		// HACK for broken sensor
		if (position > Tilt.POS_UP + 50 || position < Tilt.POS_DOWN - 50) {
			power = 0;
		}
		
		Robot.tilt.setPower(power);

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {}

	protected void interrupted() {}
}
