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
    	addParallel(new DriveCommand(60, 0.5, 0, 2));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 1, 3));
    	addSequential(new RunArm(NumberConstants.downArmAngle, 1, 2));
    	
    	//Drives over the cheval
    	addSequential(new DriveCommand(80, 1, 0, 5));

    }
}
