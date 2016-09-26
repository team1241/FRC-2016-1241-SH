package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to extend popper.
 */
public class ExtendPopper extends Command {

	/**
	 * Instantiates a new extend popper.
	 */
	public ExtendPopper() {
		// Makes sure there are no other shooter commands running at the same
		// time
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.fFlash.set(true);
		Robot.sFlash.set(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.extendPop();
	}

	// Set to true to make sure command only runs once when called
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
