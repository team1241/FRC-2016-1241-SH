package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to run Two ball over the rough terrain in reverse
 */
public class BackwardsRoughTerrain extends CommandGroup {

	/**
	 * Instantiates a new backwards rough terrain.
	 *
	 * @param location
	 *            the location
	 */
	public BackwardsRoughTerrain(int location) {
		// Drive to defense
		addSequential(new ActuateHood(true));
		addParallel(new ContinousMotion(-0.6, -40, 1.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 140, 0.6, 1.5));

		if (location == 1)
			addParallel(new TurnTurret(-190, 1.0, 3, false));
		else if (location == 2)
			addParallel(new TurnTurret(-190, 1.0, 3, false));
		else if (location == 3)
			addParallel(new TurnTurret(-180, 1.0, 3, false));
		else if (location == 4)
			addParallel(new TurnTurret(-150, 1.0, 3, false));
		addParallel(new SetShooterSpeed(4500));
		addSequential(new DriveCommand(-65, 1, 0, 1.75));

		addSequential(new WaitCommand(0.5));
		addSequential(new CameraTrack(0.5));
		addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(0.1));
		addSequential(new ExtendPopper());
		addSequential(new WaitCommand(0.4));
		addSequential(new RetractPopper());

		// Driving Forward
		// addParallel(new ContinousMotion(0.8, 40, 1.5));
		addSequential(new RunIntake(RunIntake.INTAKE));

		addParallel(new DriveCommand(170, 0.8, 0, 3.0));
		addSequential(new RunArm(NumberConstants.downArmAngle + 10, 0.4, 3.0));

		addParallel(new ContinousMotion(-0.6, -40, 1.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 150, 0.6, 1.5));

		// Drives over the defense
		addParallel(new RunArm(NumberConstants.downArmAngle + 100, 1, 1.75));
		addParallel(new SetShooterSpeed(4500));
		addSequential(new DriveCommand(-75, 1, 0, 1.75));

		addSequential(new WaitCommand(0.5));
		addSequential(new CameraTrack(0.5));
		addSequential(new WaitCommand(0.1));
		addSequential(new ExtendPopper());
		addSequential(new WaitCommand(0.4));
		addSequential(new RetractPopper());

		addSequential(new RunIntake(RunIntake.STATIC));
		addSequential(new StopShooter());
		addSequential(new TurnTurret(0, 1, 2, false));
	}
}
