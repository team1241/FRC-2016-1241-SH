package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCheval extends CommandGroup {
    
    public  AutoCheval() {
    	//Move towards cheval
    	addParallel(new DriveCommand(45, 0.5, 0, 2));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 1, 2));
    	addSequential(new RunArm(NumberConstants.downArmAngle, 1, 1.5));
    	
    	//Drives over the cheval
    	addParallel(new RunArm(NumberConstants.downArmAngle+50, 1, 3));
    	addSequential(new DriveCommand(70, 1, 0, 3));

    }
}
