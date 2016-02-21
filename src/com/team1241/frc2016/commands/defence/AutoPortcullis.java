package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class AutoPortcullis extends CommandGroup {
    
    public  AutoPortcullis() {
    	addParallel(new DriveCommand(55, 0.3, 0, 5));
    	addSequential(new RunArm(NumberConstants.downArmAngle, 1, 5));
    	//Drive under portcullis
    	addSequential(new DriveCommand(15, 0.3, 0, 5));
    	
    	addSequential(new RunArm(NumberConstants.downArmAngle+50, 1, 5));
        addParallel(new DriveCommand(10, 0.3, 0, 5));
//        addSequential(new RunArm(NumberConstants.downArmAngle, 1, 5));
        addParallel(new DriveCommand(10, .3, 0, 5));
        addSequential(new RunArm(NumberConstants.upArmAngle, 1, 5));
        addSequential(new DriveCommand(20,0.5, 0, 5));
        
    }
}
