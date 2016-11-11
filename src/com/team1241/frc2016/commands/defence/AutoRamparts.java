package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Groups used to cross the ramparts
 */
public class AutoRamparts extends CommandGroup {

	/**
	 * Instantiates a new auto ramparts.
	 */
	public AutoRamparts() {
		addParallel(new DriveCommand(72, 0.6, 0, 2.5));
		addSequential(new RunArm(NumberConstants.downArmAngle - 20, 1, 2.5));

		addParallel(new RunArm(NumberConstants.downArmAngle + 100, 1, 2));
		addSequential(new ContinousMotion(0.3, 10, 2));
		addParallel(new DriveCommand(60, 1.0, 0, 2));
		addSequential(new RunArm(NumberConstants.upArmAngle, 1, 2));

		addSequential(new RunArm(NumberConstants.downArmAngle + 50, 1, 1.5));

	}
}
