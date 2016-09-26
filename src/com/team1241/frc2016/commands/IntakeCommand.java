package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command for the intake subsystem. Contains code to control the
 * intake and arms
 * 
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class IntakeCommand extends Command {

	/**
	 * Instantiates a new intake command.
	 */
	public IntakeCommand() {
		// Makes sure no other intake commands are running at the same time
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Runs arm motors from joystick values
		Robot.intake.runArms(Robot.oi.getToolLeftY() * 0.6);

		// Runs intake motors using the gamepad
		if (Robot.oi.getToolAButton()) {
			Robot.intake.runIntake(1);
		} else if (Robot.oi.getToolXButton()) {
			Robot.intake.runIntake(-1);
		} else {
			Robot.intake.runIntake(0);
		}
	}

	// Set to false to make sure command does not end
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}