package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to turn turret to angle, defendant on timeout given
 */
public class TimedTurnTurret extends Command {

	// Variables to store parameter information
	private double angle;
	private double power;
	private double timeout;
	private boolean camera;

	/**
	 * Instantiates a new timed turn turret.
	 *
	 * @param angle
	 *            Angle to turn turret to in degrees
	 * @param power
	 *            Power the turret will turn at (0.0 - 1.0)
	 * @param timeout
	 *            The timeout in seconds
	 * @param camera
	 *            True if camera is being used, else false
	 */
	public TimedTurnTurret(double angle, double power, double timeout, boolean camera) {
		// requires(Robot.shooter);
		this.power = power;
		this.angle = angle;
		this.timeout = timeout;
		this.camera = camera;
	}

	/**
	 * Change angle setpoint
	 *
	 * @param angle
	 *            Angle to turn turret to in degrees
	 */
	public void changeAngle(double angle) {
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(timeout);
		Robot.shooter.turretPID.resetPID();
		Robot.shooter.cameraPID.resetPID();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (camera) {
			Robot.shooter.turnTurretCamera(angle, power);
		} else
			Robot.shooter.turnTurretToAngle(angle, power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true, reset PID controller and stop
	// turret from moving
	protected void end() {
		Robot.shooter.turretPID.resetPID();
		Robot.shooter.turnTurret(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, reset PID controller and stop turret from
	// moving
	protected void interrupted() {
		Robot.shooter.turretPID.resetPID();
		Robot.shooter.turnTurret(0);
	}
}
