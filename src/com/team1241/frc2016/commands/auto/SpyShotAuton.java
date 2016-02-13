package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.ShootSequence;
import com.team1241.frc2016.commands.TurnTurretToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyShotAuton extends CommandGroup {

	public  SpyShotAuton() {
		// lower arm
		addSequential(new RunArm(NumberConstants.minArmAngle, 1, 1.5));

		// Turn turret to target
		addParallel(new TurnTurretToAngle(NumberConstants.spyTurretAngle, 1, 1));

		//shoots the boulder
		addParallel(new SetShooterSpeed(NumberConstants.spyShotRPM, 1));
		if(Robot.shooter.shooterPID.isDone())
			addSequential(new ShootSequence());
	}
}
