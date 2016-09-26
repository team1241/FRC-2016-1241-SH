package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ActuateHolder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group opens and closes holder with set intervals
 */
public class AutoHolder extends CommandGroup {

	/**
	 * Instantiates a new auto holder.
	 */
	public AutoHolder() {
		addSequential(new WaitCommand(NumberConstants.waitForHolderClose));
		addSequential(new ActuateHolder(true));
		addSequential(new WaitCommand(0.25));
		addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(0.25));
		addSequential(new ActuateHolder(true));
	}
}
