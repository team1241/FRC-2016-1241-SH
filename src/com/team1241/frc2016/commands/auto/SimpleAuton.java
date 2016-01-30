package com.team1241.frc2016.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleAuton extends CommandGroup {
    
    public  SimpleAuton() {
        addParallel(new DriveCommand(50, 0.3, 0, 5));
    }
}
