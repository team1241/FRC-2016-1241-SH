package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Groups used to cross the portcullis
 */
public class AutoPortcullis extends CommandGroup {

	/**
	 * Instantiates a new auto portcullis.
	 */
	public AutoPortcullis() {
		addParallel(new DriveCommand(72, 0.5, 0, 2.5));
		addSequential(new RunArm(NumberConstants.downArmAngle - 20, 1, 2.5));

		// Drive under portcullis
		addParallel(new RunArm(NumberConstants.downArmAngle + 100, 1, 2));
		addSequential(new ContinousMotion(0.3, 10, 2));
		addParallel(new DriveCommand(60, 1.0, 0, 2));
		addSequential(new RunArm(NumberConstants.upArmAngle, 1, 2));

		addSequential(new RunArm(NumberConstants.downArmAngle + 50, 1, 1.5));

	}
}
