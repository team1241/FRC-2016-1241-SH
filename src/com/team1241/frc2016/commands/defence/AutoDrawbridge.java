package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDrawbridge extends CommandGroup {
    
    public  AutoDrawbridge() {
        //Bring arm over the drawbridge
    	addSequential(new RunArm(NumberConstants.maxArmAngle, 1, 1));
        addSequential(new DriveCommand(10,1,0,1,1));
        
        //Bring arm down
        addSequential(new RunArm(NumberConstants.maxArmAngle-10, 1, 1));
        
        //Bring down drawbridge
        addSequential(new RunArm(NumberConstants.minArmAngle, 1, 1));
        addParallel(new DriveCommand(-20, .8, 0, 1, 1));
        
        //Go over drawbridge
        addSequential(new DriveCommand(50, 1, 0, 1, 1));
    }
}
