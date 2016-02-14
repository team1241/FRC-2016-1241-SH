package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDrawbridge extends CommandGroup {
    
    public  AutoDrawbridge() {
    	//Robot has to start with bumpers bud up with drawbridge and arms up
        
        //Bring arm down
        addSequential(new RunArm(150, 0.5, 5));
        
        //Bring down drawbridge
        addParallel(new DriveCommand(-50, .5, 0, 5));
        addSequential(new RunArm(NumberConstants.downArmAngle, 0.5, 5));
        
        //Go over drawbridge
        addSequential(new DriveCommand(150, 1, 0, 1));
    }
}
