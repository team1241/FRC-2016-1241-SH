package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Class not complete, acting as a place holder
 */
public class AutoSallyPort extends CommandGroup {

	/**
	 * Instantiates a new auto sally port.
	 */
	public AutoSallyPort() {
		// Set Robot position
		addParallel(new DriveCommand(45, 0.8, 0, 1));
		addSequential(new RunArm(NumberConstants.downArmAngle + 100, 0.5, 1));

		addSequential(new DriveCommand(45, 0.8, 0, 1, 1));
		addSequential(new TurnCommand(180, 0.8, 3));
		addParallel(new DriveCommand(-65, 0.8, 0, 1));
		// addParallel(new SetShooterSpeed(4500));
		addSequential(new TurnTurret(180, 1, 1, false));

	}
}