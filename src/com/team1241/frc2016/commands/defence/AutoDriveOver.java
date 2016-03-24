package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveOver extends CommandGroup {
    
    public  AutoDriveOver() {
        //Drive to defense
//    	addParallel(new DriveCommand(60,0.6,0,2));
    	addParallel(new ContinousMotion(0.6, 40, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 0.6, 1.5));
    	
    	//Drives over the defense
    	addParallel(new RunArm(NumberConstants.downArmAngle+25, 1, 2.75));
//    	addSequential(new DriveCommand(80, 1, 0, 2.75));
    	addSequential(new ContinousMotion(1.0, 65, 2.75));
    	addSequential(new DriveCommand(0, 0, 0, 0.0));
    }
}
