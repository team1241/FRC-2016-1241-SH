package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoughTerrain extends CommandGroup {
    
    public  AutoRoughTerrain() {
        //Drive to defense
    	addParallel(new DriveCommand(40, 1, 0, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 0.6, 1.5));
    	
    	//Drives over the defense
    	addParallel(new RunArm(NumberConstants.downArmAngle+25, 1, 2.75));
    	addSequential(new DriveCommand(85, 1, 0, 2.75));
    }
}
