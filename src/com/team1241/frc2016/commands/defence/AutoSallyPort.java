package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSallyPort extends CommandGroup {
    
    public  AutoSallyPort() {
        //Set Robot position
    	addSequential(new RunArm(NumberConstants.maxArmAngle, 1, 1));
    	addParallel(new DriveCommand(10, .8, 0, 1, 1));
    }
}