package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to actuate ball holder or "halo".
 *
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class ActuateHolder extends Command {

	/** Used to set state of holder */
	private boolean actuate;

	/**
	 * Instantiates a new actuate holder.
	 *
	 * @param actuate
	 *            New state of holder
	 */
	public ActuateHolder(boolean actuate) {
		this.actuate = actuate;
	}

	/**
	 * Instantiates a new actuate holder.
	 */
	public ActuateHolder() {
		this(!Robot.conveyor.getHoldState());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (actuate) {
			Robot.conveyor.extendHolder();
		} else {
			Robot.conveyor.retractHolder();
		}
	}

	// Set to true, to make sure command only runs once at every call
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
