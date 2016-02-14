package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCheval extends CommandGroup {
    
    public  AutoCheval() {
        //Bring down Cheval
    	addSequential(new RunArm(NumberConstants.downArmAngle, .8, 1));
    	
    	//Drives over the cheval
    	addParallel(new DriveCommand(10, .8, 0, 1, 1));
    	addParallel(new RunArm(NumberConstants.downArmAngle-20, .8, 1));

    }
}
