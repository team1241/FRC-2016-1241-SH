package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootSequence extends CommandGroup {

	public  ShootSequence() {
		//Only shoots when contains a ball
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
