package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command used to shoot ball
 */
public class ShootSequence extends CommandGroup {

	/**
	 * Instantiates a new shoot sequence. CommandGroup ties multiple commands
	 * together to make sure correct sequence of events
	 */
	public ShootSequence() {
		addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(0.1));
		addSequential(new ExtendPopper());
		addSequential(new WaitCommand(NumberConstants.waitForPop));
		addSequential(new RetractPopper());
		addSequential(new WaitCommand(NumberConstants.waitForHolder));
		addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(NumberConstants.waitForHolder));
		addSequential(new StopShooter());
		addSequential(new ActuateHood(false));
	}
}
