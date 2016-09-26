package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class CameraTrack.
 */
public class CameraTrack extends Command {

	/** Saves the value for command timeout */
	private double timeOut;

	/** Holds array of target coordinates */
	private double[] target;

	/** Current x value of target */
	private double xVal;

	/** Previous x value of target */
	private double prevXVal = 0;

	/** Used to save to angle target point */
	private double degree;

	/** boolean used to check if image has changed */
	private boolean hasChanged = true;

	/** boolean used to check if tracking has started */
	private boolean started = false;

	/** Uses TurnTurret command to turn turret to target */
	private TurnTurret turret;

	/**
	 * Instantiates a new camera track.
	 */
	public CameraTrack() {
		this(2.0);
	}

	/**
	 * Instantiates a new camera track.
	 *
	 * @param timeOut
	 *            Time command must finish in or else it will end (in seconds)
	 */
	public CameraTrack(double timeOut) {
		this.timeOut = timeOut;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		hasChanged = true;
		started = false;
		prevXVal = 0;
		target = Robot.shooter.getCoordinates();
		if (timeOut > 0)
			setTimeout(timeOut);
		turret = new TurnTurret(0, 0.5, 1, true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Update target coordinates
		Robot.shooter.updateCoordinates();
		target = Robot.shooter.getCoordinates();

		// Checks if target coordinates are valid (8 values = 4 coordinates). If
		// more or no targets are detected, length != 8)
		if (target.length == 8) {

			// Finds the center of the target in the x direction (Will range
			// from 0 to 640)
			xVal = Robot.shooter.getXCoordinates();

			// If the image has changed, hasChanged is set to true (Used to
			// makes sure not to update turret with old values)
			if (prevXVal != xVal)
				hasChanged = true;

			// Checks hasChaged is true and has NOT started (makes sure turret
			// finishes last command)
			if (hasChanged && !started) {
				// Cancels any previous running turret command
				turret.cancel();

				// Finds the current angle of the turrent relative to the target
				degree = Robot.shooter.pixelToDegree(xVal);

				// Sets new setpoint for turret as currentAngle - cameraAngle +
				// offset (any offset resulted by camera not being perfectly
				// center with the turret)
				turret.changeAngle(Robot.shooter.getTurretAngle() - degree + NumberConstants.cameraOffset);
				turret.start();
				hasChanged = false;
			}
			if (turret.isFinished())
				started = false;
			else
				started = true;

			prevXVal = xVal;
		}
	}

	// Ends Camera Tracking once timed out (special case when live tracking,
	// hence the -1)
	protected boolean isFinished() {
		if (timeOut == -1) {
			return false;
		} else {
			return isTimedOut();
		}
	}

	// Called once after isFinished returns true, makes sure to stop turret from
	// moving
	protected void end() {
		Robot.shooter.turnTurret(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, makes sure to stop turret from moving
	protected void interrupted() {
		Robot.shooter.turnTurret(0);
	}
}
