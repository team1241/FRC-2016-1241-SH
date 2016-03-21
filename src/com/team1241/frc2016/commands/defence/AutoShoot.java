package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {
    
    public  AutoShoot() {
    	addSequential(new WaitCommand(1.5));
    	addSequential(new ShootSequence());
    	addSequential(new TurnTurret(0, 1, 5, false));
    }
}
