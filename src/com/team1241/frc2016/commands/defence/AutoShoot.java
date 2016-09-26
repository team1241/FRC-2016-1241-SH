package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group to set sequence for shot, when done tracking
 */
public class AutoShoot extends CommandGroup {

	/**
	 * Instantiates a new auto shoot.
	 */
	public AutoShoot() {
		addSequential(new WaitCommand(1.5));
		addSequential(new ShootSequence());
		addSequential(new TurnTurret(0, 1, 5, false));
	}
}
