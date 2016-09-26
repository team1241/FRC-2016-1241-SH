package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used when robot stopping at the end of the motion is not required.
 */
public class ContinousMotion extends Command {

	// Variables to hold parameter values
	private double speed;
	private double setPoint;
	private double timeOut;

	/**
	 * Instantiates a new continous motion.
	 *
	 * @param speed
	 *            Speed robot will travel at (-1.0 to 1.0)
	 * @param setPoint
	 *            The set point in inches
	 * @param timeOut
	 *            The time out in seconds
	 */
	public ContinousMotion(double speed, double setPoint, double timeOut) {
		this.speed = speed;
		this.setPoint = setPoint;
		this.timeOut = timeOut;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.reset();
		setTimeout(timeOut);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.driveAngle(0, speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (setPoint >= 0) {
			return isTimedOut() || Robot.drive.getAverageDistance() > setPoint;
		} else {
			return isTimedOut() || Robot.drive.getAverageDistance() < setPoint;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
