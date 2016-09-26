package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command to the Shooter subsystem. Used to control the shooter and
 * the turret.
 * 
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class ShootCommand extends Command {

	/** Different SetShooterSpeed Commands used for different rpms */
	private SetShooterSpeed badderRPM;
	private SetShooterSpeed outerRPM;
	private SetShooterSpeed spyRPM;

	/** Different TurnTurret Commands used for different angles */
	private TurnTurret backAngle;
	private TurnTurret originAngle;
	private TurnTurret leftAngle;
	private TurnTurret rightAngle;
	private TurnTurret leftSquareAngle;
	private TurnTurret rightSquareAngle;

	// Used for camera tracking
	private CameraTrack track;

	// boolean used to check if camera has tracked
	public static boolean tracked;

	// boolean used to check if target is detected
	public static boolean detects;

	// Used to toggle hood from extended to retracted
	public static ToggleBoolean toggleHood;

	/**
	 * Instantiates a new shoot command.
	 */
	public ShootCommand() {
		requires(Robot.shooter);
		toggleHood = new ToggleBoolean();
		badderRPM = new SetShooterSpeed(NumberConstants.badderShotRPM);
		outerRPM = new SetShooterSpeed(NumberConstants.outerShotRPM);
		spyRPM = new SetShooterSpeed(NumberConstants.spyShotRPM);

		backAngle = new TurnTurret(-180, 1, 3, false);
		originAngle = new TurnTurret(0, 1, 3, false);
		leftAngle = new TurnTurret(67, 1, 3, false);
		rightAngle = new TurnTurret(-67, 1, 3, false);

		leftSquareAngle = new TurnTurret(90, 1, 3, false);
		rightSquareAngle = new TurnTurret(-90, 1, 3, false);

		tracked = false;

		track = new CameraTrack(2);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Updates rpm values
		outerRPM.changeRPM(Robot.outerRPM);
		spyRPM.changeRPM(Robot.spyRPM);

		// Depending on joystick direction, turret will turn to face that
		// direction
		if (Robot.oi.getToolRightX() > 0.9) {
			cancelTurret();
			rightAngle.start();
		} else if (Robot.oi.getToolRightX() < -0.9) {
			cancelTurret();
			leftAngle.start();
		} else if (Robot.oi.getToolRightY() > 0.9) {
			cancelTurret();
			backAngle.start();
		} else if (Robot.oi.getToolRightY() < -0.9) {
			cancelTurret();
			originAngle.start();
		}

		else if (Robot.oi.getDriveLeftTrigger()) {
			cancelTurret();
			leftSquareAngle.start();
		} else if (Robot.oi.getDriveRightTrigger()) {
			cancelTurret();
			rightSquareAngle.start();
		}

		// When toolop start button is pressed, start camera tracking
		if (Robot.oi.getToolStartButton()) {
			cancelTurret();
			track.start();
		}

		// Depending on button pressed run shooter at a certain rpm
		if (Robot.oi.getToolLeftTrigger()) {
			Robot.shooter.setShooterState(true);
			badderRPM.cancel();
			outerRPM.cancel();
			spyRPM.start();
		} else if (Robot.oi.getToolLeftBumper()) {
			Robot.shooter.setShooterState(true);
			outerRPM.cancel();
			spyRPM.cancel();
			badderRPM.start();
		} else if (Robot.oi.getToolRightBumper()) {
			Robot.shooter.setShooterState(true);
			if (!toggleHood.get())
				toggleHood.set(true);
			badderRPM.cancel();
			spyRPM.cancel();
			outerRPM.start();
		}
		// Shoot ball when button is pressed
		else if (Robot.oi.getToolRightTrigger()) {
			Robot.conveyor.setContains(true);
			new ShootSequence().start();
		}
		// Turn off the shooter
		else if (Robot.oi.getToolBackButton()) {
			Robot.shooter.setShooterState(false);
		}

		// If toolop Y button is pressed, toggle hood between retracted and
		// extended
		if (Robot.oi.getToolYButton()) {
			toggleHood.set(true);
		}
		if (toggleHood.get()) {
			Robot.shooter.extendHood();
		} else {
			Robot.shooter.retractHood();
		}
	}

	// Set to false, to make sure command always runs
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	/**
	 * Cancel turret.
	 */
	private void cancelTurret() {
		rightSquareAngle.cancel();
		leftSquareAngle.cancel();
		backAngle.cancel();
		originAngle.cancel();
		leftAngle.cancel();
		rightAngle.cancel();
	}
}
