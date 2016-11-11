package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Class RunArm.
 *
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class RunArm extends Command {

	// Variables to hold parameter information
	private double angle;
	private double speed;
	private double timeOut;

	/**
	 * Instantiates a new run arm.
	 *
	 * @param angle
	 *            The angle to turn the arm to (NOTE PHYSICAL LIMITATIONS)
	 * @param speed
	 *            The speed the arm will travel at
	 * @param timeOut
	 *            The timeout for the command in seconds
	 */
	public RunArm(double angle, double speed, double timeOut) {
		this.angle = angle;
		this.speed = speed;
		this.timeOut = timeOut;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.setTimeout(timeOut);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intake.setArmPosition(angle, speed);
	}

	// Command is finished when arm is timed out
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true, resets PID controller and
	// stops arm from
	// moving.
	protected void end() {
		Robot.intake.runArms(0);
		Robot.intake.armPID.resetPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, resets PID controller and stops arm from
	// moving.
	protected void interrupted() {
		Robot.intake.runArms(0);
		Robot.intake.armPID.resetPID();
	}
}
