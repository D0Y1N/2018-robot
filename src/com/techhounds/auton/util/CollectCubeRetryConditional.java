package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class CollectCubeRetryConditional extends ConditionalCommand {

	public CollectCubeRetryConditional() {
		super(new CollectCubeRetry());
	}

	@Override
	protected boolean condition() {
		return Robot.intake.isCubeDetected();
	}

}
