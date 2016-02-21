package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {
    
    public  AutoShoot() {
    	addSequential(new WaitCommand(4));
    	addSequential(new ShootSequence());
    }
}
