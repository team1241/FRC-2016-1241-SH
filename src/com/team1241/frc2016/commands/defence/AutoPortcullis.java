package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class AutoPortcullis extends CommandGroup {
    
    public  AutoPortcullis() {
    	addSequential(new RunArm(NumberConstants.downArmAngle-50, 1, 5));
    	//Drive under portcullis
        addParallel(new DriveCommand(100, .3, 0, 5));
        addSequential(new RunArm(NumberConstants.upArmAngle, 1, 5));
    }
}
