package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class AutoPortcullis extends CommandGroup {
    
    public  AutoPortcullis() {
    	//Set Robot position
    	addSequential(new RunArm(NumberConstants.minArmAngle, .8, 1));
    	addSequential(new DriveCommand(10, .8, 0, 1));
    	
    	//Drive under portcullis
        addSequential(new DriveCommand(100, .8, 0, 1));
        addParallel(new RunArm(360, 1, 1));
    }
}
