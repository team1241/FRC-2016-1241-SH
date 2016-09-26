package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to cross the cheval defence
 */
public class AutoCheval extends CommandGroup {

	/**
	 * Instantiates a new auto cheval.
	 */
	public AutoCheval() {
		// Move towards cheval
		addParallel(new DriveCommand(52, 0.75, 0, 2.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 150, 1, 2.5));
		addSequential(new RunArm(NumberConstants.downArmAngle - 10, 1, 1.5));

		// Drives over the cheval
		addParallel(new RunArm(NumberConstants.downArmAngle + 50, 1, 3));
		addSequential(new DriveCommand(80, 1, 0, 3));

	}
}
