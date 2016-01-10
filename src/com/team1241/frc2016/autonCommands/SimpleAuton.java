package com.team1241.frc2016.autonCommands;

import com.team1241.frc2016.commands.DriveDistance;
import com.team1241.frc2016.commands.TankDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleAuton extends CommandGroup {
    
    public  SimpleAuton() {
        addParallel(new DriveDistance(50, 0.3, 5));
        addSequential(new TankDrive());
    }
}
