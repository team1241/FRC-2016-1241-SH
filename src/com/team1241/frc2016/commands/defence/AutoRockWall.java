package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Groups used to cross the rockwall
 */
public class AutoRockWall extends CommandGroup {

	/**
	 * Instantiates a new auto rock wall.
	 */
	public AutoRockWall() {
		// Drive to defense
		addParallel(new ContinousMotion(1.0, 40, 1.5));
		addSequential(new RunArm(NumberConstants.downArmAngle + 150, 0.6, 1.5));

		// Drives over the defense
		addParallel(new RunArm(NumberConstants.downArmAngle + 25, 1, 2.75));
		addSequential(new ContinousMotion(1.0, 65, 2.75));
		addSequential(new DriveCommand(15, 1, 0, 0.75));
	}
}
