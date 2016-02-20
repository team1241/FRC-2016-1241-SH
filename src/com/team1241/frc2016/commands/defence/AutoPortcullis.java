package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class AutoPortcullis extends CommandGroup {
    
    public  AutoPortcullis() {
//    	addSequential(new RunArm(NumberConstants.downArmAngle-50, 1, 5));
    	//Drive under portcullis
        
//        addSequential(new RunArm(NumberConstants.downArmAngle, 1, 5));
        addParallel(new DriveCommand(10, .3, 0, 5));
        
    }
}
