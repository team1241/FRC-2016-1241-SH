package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.ShootSequence;
import com.team1241.frc2016.commands.TurnTurret;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to allow robot to shoot from the spy zone
 */
public class SpyShotAuton extends CommandGroup {

	/**
	 * Instantiates a new spy shot auton.
	 */
	public SpyShotAuton() {
		// Lower arm
		addSequential(new RunArm(NumberConstants.upArmAngle, 1, 1.5));

		// Turn turret to target
		addParallel(new TurnTurret(NumberConstants.spyShotAngle, 1, 1, false));

		// Shoots the boulder
		addParallel(new SetShooterSpeed(NumberConstants.spyShotRPM));
		if (Robot.shooter.shooterPID.isDone())
			addSequential(new ShootSequence());
	}
}
