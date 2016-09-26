package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to actuate hood on the shooter.
 *
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class ActuateHood extends Command {

	/** Used to save new state for the hood */
	private boolean actuate;

	/**
	 * Instantiates a new actuate hood.
	 *
	 * @param actuate
	 *            Used to set new state for hood
	 */
	public ActuateHood(boolean actuate) {
		this.actuate = actuate;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (actuate) {
			Robot.shooter.extendHood();
		} else {
			if (ShootCommand.toggleHood.get())
				ShootCommand.toggleHood.set(true);
			Robot.shooter.retractHood();
		}
	}

	// Set to true, this makes sure the command will only run once whenever
	// called
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
