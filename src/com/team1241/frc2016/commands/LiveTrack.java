package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used to test live tracking, not used in match
 */
public class LiveTrack extends Command {

	public static boolean tracking = false;
	private double[] target;
	private double xVal;
	private double degree;
	private double angle;
	private double power = 0.5;

	protected void initialize() {
		tracking = true;
	}

	protected void execute() {
		Robot.shooter.updateCoordinates();
		target = Robot.shooter.getCoordinates();
		if (target.length == 8) {
			xVal = Robot.shooter.getXCoordinates();
			degree = Robot.shooter.pixelToDegree(xVal);
			Robot.shooter.liveTrack(Robot.shooter.getTurretAngle() - degree + NumberConstants.cameraOffset);
		} else {
			tracking = false;
		}
	}

	protected boolean isFinished() {
		return !tracking;
	}

	protected void end() {
		Robot.shooter.turnTurret(0);
		tracking = false;
	}

	protected void interrupted() {
		tracking = false;
		Robot.shooter.turnTurret(0);
	}
}
