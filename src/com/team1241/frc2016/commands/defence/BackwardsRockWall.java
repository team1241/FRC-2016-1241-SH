package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ActuateHolder;
import com.team1241.frc2016.commands.ActuateHood;
import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.ExtendPopper;
import com.team1241.frc2016.commands.RetractPopper;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.StopShooter;
import com.team1241.frc2016.commands.TurnTurret;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to run Two Ball auto over the rockwall in reverse
 */
public class BackwardsRockWall extends CommandGroup {

	/**
	 * Instantiates a new backwards rock wall.
	 *
	 * @param location
	 *            the location
	 */
	public BackwardsRockWall(int location) {
		// Drive to defense
		if (location != 4)
			addSequential(new ActuateHood(true));
		addParallel(new ContinousMotion(-0.8, -60, 1.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 150, 0.6, 1.5));

		// Drives over the defense
		if (location == 1)
			addParallel(new TurnTurret(-190, 1.0, 3, false));
		else if (location == 2)
			addParallel(new TurnTurret(-190, 1.0, 3, false));
		else if (location == 3)
			addParallel(new TurnTurret(-180, 1.0, 3, false));
		else if (location == 4)
			addParallel(new TurnTurret(-130, 1.0, 3, false));

		addParallel(new SetShooterSpeed(4500));

		if (location == 4)
			addSequential(new DriveCommand(-185, 1, 0, 2.5));
		else
			addSequential(new DriveCommand(-45, 1, 0, 1.5));

		addSequential(new WaitCommand(0.5));
		addSequential(new CameraTrack(0.5));
		addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(0.1));
		addSequential(new ExtendPopper());
		addSequential(new WaitCommand(0.4));
		addSequential(new RetractPopper());

		// Driving Forward
		addSequential(new RunIntake(RunIntake.INTAKE));

		if (location == 4)
			addSequential(new DriveCommand(110, 1, 0, 1.5));

		addParallel(new ContinousMotion(0.8, 40, 1.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 150, 0.6, 1.5));

		addParallel(new DriveCommand(110, 0.7, 0, 2.5));
		addSequential(new RunArm(NumberConstants.downArmAngle, 0.6, 2.5));

		addParallel(new ContinousMotion(-0.8, -70, 1.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 160, 0.6, 1.5));

		// Drives over the defense
		addParallel(new SetShooterSpeed(4550));
		addSequential(new DriveCommand(-75, 1, 0, 1.0));

		if (location != 4) {
			addSequential(new WaitCommand(0.5));
			addSequential(new CameraTrack(0.5));
			addSequential(new ActuateHolder(false));
			addSequential(new WaitCommand(0.1));
			addSequential(new ExtendPopper());
			addSequential(new WaitCommand(0.4));
			addSequential(new RetractPopper());
		}
		addSequential(new RunIntake(RunIntake.STATIC));
		addSequential(new StopShooter());
		addParallel(new DriveCommand(-20, 1, 0, 1.0));
		addSequential(new TurnTurret(0, 1, 2, false));

	}
}
