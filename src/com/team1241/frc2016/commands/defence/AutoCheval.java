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
    	addParallel(new DriveCommand(80, 0.5, 0, 5));
    	addSequential(new RunArm(NumberConstants.downArmAngle, 1, 5));
    	
        //Bring down Cheval
//    	addSequential(new RunArm(NumberConstants.downArmAngle, .8, 1));
    	
    	//Drives over the cheval
    	addParallel(new DriveCommand(50, 0.5, 0, 5));
    	addParallel(new RunArm(NumberConstants.downArmAngle+50, .8, 5));

    }
}
