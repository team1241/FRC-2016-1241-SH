package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to stop shooter
 */
public class StopShooter extends Command {

	/**
	 * Instantiates a new stop shooter.
	 */
	public StopShooter() {
	}

	protected void initialize() {
		Robot.shooter.setShooterState(false);
		Robot.conveyor.setContains(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.setSpeed(0);
	}

	// Set to true, makes sure command runs only once.
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.setSpeed(0);
		Robot.shooter.setShooterState(false);
		Robot.conveyor.setContains(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.shooter.setSpeed(0);
		Robot.shooter.setShooterState(false);
		Robot.conveyor.setContains(false);
	}
}
