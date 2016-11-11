package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to turn the turret to a set angle
 */
public class TurnTurret extends Command {

	// Used to store set angle
	private double angle;

	// Used to store set power
	private double power;

	// Used to store timeout
	private double timeout;

	// boolean used to check if camera is being used for TurnTurret
	private boolean camera;

	/**
	 * Instantiates a new turn turret.
	 *
	 * @param angle
	 *            Angle for turret in degrees (NOTE PHYSICAL LIMITATIONS)
	 * @param power
	 *            Power turret will turn at (0.0 - 1.0)
	 * @param timeout
	 *            Timeout for TurnTurret Command in seconds
	 * @param camera
	 *            True if camera is being used, else false
	 */
	public TurnTurret(double angle, double power, double timeout, boolean camera) {
		this.power = power;
		this.angle = angle;
		this.timeout = timeout;
		this.camera = camera;
	}

	/**
	 * Change angle setpoint
	 *
	 * @param angle
	 *            Angle for turret in degrees (NOTE PHYSICAL LIMITATIONS)
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

	// Command finished when turret reached target or times out
	protected boolean isFinished() {
		return Robot.shooter.turretPID.isDone() || isTimedOut();
	}

	// Called once after isFinished returns true, resets PID and stops turret
	// from moving.
	protected void end() {
		Robot.shooter.turretPID.resetPID();
		Robot.shooter.turnTurret(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, resets PID and stops turret from moving.
	protected void interrupted() {
		Robot.shooter.turretPID.resetPID();
		Robot.shooter.turnTurret(0);
	}
}
