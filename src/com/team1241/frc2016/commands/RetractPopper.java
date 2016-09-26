package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to retract popper.
 */
public class RetractPopper extends Command {

	/**
	 * Instantiates a new retract popper.
	 */
	public RetractPopper() {
		// Makes sure no other shooter commands are running at the same time.
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.allainceColor();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.retractPop();
	}

	// Makes sure to command only runs once when called
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
