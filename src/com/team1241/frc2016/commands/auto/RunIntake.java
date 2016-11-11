package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to control the intake in autonomous
 *
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class RunIntake extends Command {

	/** The Constant INTAKE. */
	public static final int INTAKE = 0;

	/** The Constant OUTTAKE. */
	public static final int OUTTAKE = 1;

	/** The Constant STATIC. */
	public static final int STATIC = 2;

	// Stores information that will determine intake rotation
	private int direction;

	/**
	 * Instantiates a new run intake.
	 *
	 * @param direction
	 *            The direction the intake will rotate in (1 - INTAKE, 2 -
	 *            OUTTAKE)
	 */
	public RunIntake(int direction) {
		this.direction = direction;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (direction == INTAKE) {
			Robot.intake.runIntake(1);
			Robot.conveyor.runMotor(1);
		} else if (direction == OUTTAKE) {
			Robot.intake.runIntake(-1);
			Robot.conveyor.runMotor(-1);
		} else {
			Robot.intake.runIntake(0);
			Robot.conveyor.runMotor(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
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
