package com.team1241.frc2016.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Class WaitCommand.
 *
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class WaitCommand extends Command {

	// Variable to store wait time
	private double wait;

	/**
	 * Instantiates a new wait command.
	 *
	 * @param wait
	 *            The wait time in seconds
	 */
	public WaitCommand(double wait) {
		this.wait = wait;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(wait);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Command is finished once the wait time is over
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
