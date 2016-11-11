package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used for Auto FeedForward calculations for shooter PIDs
 */
public class SetShooterPower extends Command {

	/** Used to store new shooter power */
	private double power;

	/**
	 * Instantiates a new sets the shooter power.
	 *
	 * @param power
	 *            Power sent to the motors (-1.0 to 1.0)
	 */
	public SetShooterPower(double power) {
		this.power = power;
	}

	/**
	 * Change power sent to shooter motors
	 *
	 * @param power
	 *            Power sent to the motors (-1.0 to 1.0)
	 */
	public void changePower(double power) {
		this.power = power;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.setSpeed(power);
	}

	// Makes sure command continuously runs
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.shooter.setSpeed(0);
	}
}
