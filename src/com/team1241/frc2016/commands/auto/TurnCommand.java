package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to turn robot to an angle.
 *
 * @author Bryan
 */
public class TurnCommand extends Command {

	// Variables to hold parameter information
	private double angle;
	private double speed;
	private double timeOut;

	/**
	 * Instantiates a new turn command.
	 *
	 * @param angle
	 *            Angle the robot will turn to (-180 <-> 180)
	 * @param speed
	 *            The speed the robot will turn at (0.0 - 1.0)
	 * @param timeOut
	 *            The time out in seconds
	 */
	public TurnCommand(double angle, double speed, double timeOut) {
		this.angle = angle;
		this.speed = speed;
		this.timeOut = timeOut;
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.turnDrive(angle, speed, 1);
	}

	// Command is finished when timed out
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true, once done will stop robot from
	// moving.
	protected void end() {
		Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, once done will stop robot from moving.
	protected void interrupted() {
	}
}
