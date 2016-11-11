package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to set rpm of shooter
 */
public class SetShooterSpeed extends Command {

	/** Used to store new rpm */
	private double rpm;

	/**
	 * Instantiates a new SetShooterSpeed
	 *
	 * @param rpm
	 *            rpm the shooter will be set to (KEEP IN MIND PHYSICAL
	 *            LIMITATIONS)
	 */
	public SetShooterSpeed(double rpm) {
		this.rpm = rpm;
	}

	/**
	 * Used to change rpm set for the shooter
	 *
	 * @param rpm
	 *            rpm the shooter will be set to (KEEP IN MIND PHYSICAL
	 *            LIMITATIONS)
	 */
	public void changeRPM(double rpm) {
		this.rpm = rpm;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Resets PID
		Robot.shooter.shooterPID.resetPID();
		// Sets shooter state to true (shooter has started to run)
		Robot.shooter.setShooterState(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Sets speed to new rpm
		Robot.shooter.setRPM(rpm);
	}

	// Turns of the shooter when state has been changed to false
	protected boolean isFinished() {
		return Robot.shooter.getShooterState() == false;
	}

	// Called once after isFinished returns true, resets PID and turns off
	// shooter
	protected void end() {
		Robot.shooter.shooterPID.resetPID();
		Robot.shooter.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, resets PID and turns off shooter
	protected void interrupted() {
		Robot.shooter.shooterPID.resetPID();
		Robot.shooter.setSpeed(0);
	}
}
