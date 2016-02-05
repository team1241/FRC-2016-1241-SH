package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCheval extends CommandGroup {
    
    public  AutoCheval() {
        //Set robot position
    	addSequential(new RunArm(NumberConstants.maxArmAngle, .8, 1));
    	
    	//Bring down cheval
    	addSequential(new DriveCommand(10, .8, 0, 1, 1));
    	addParallel(new RunArm(NumberConstants.minArmAngle, .8, 1));
    	
    	//Drive over cheval
    	addSequential(new DriveCommand(20, .8, 0, 1, 1));
    	addParallel(new RunArm(NumberConstants.minArmAngle+20, .8, 1));
    }
}
