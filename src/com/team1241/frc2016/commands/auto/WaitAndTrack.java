package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.commands.CameraTrack;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WaitAndTrack extends CommandGroup {
    
    public  WaitAndTrack(double waitTime) {
    	addSequential(new WaitCommand(waitTime));
    	addSequential(new CameraTrack(1.5));
    }
}
