package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.commands.ActuateHolder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoHolder extends CommandGroup {
    
    public  AutoHolder() {
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ActuateHolder(true));
    }
}
