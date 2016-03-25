package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.DriveCommand;
import com.team1241.frc2016.commands.auto.RunArm;
import com.team1241.frc2016.commands.defence.AutoRockWall;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Test extends CommandGroup {
    
    public  Test() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new DriveCommand(70,0.6,0,5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 0.6, 3));
    	
    	//Drives over the cheval
    	addSequential(new DriveCommand(70, 1, 0, 8));
    	addSequential(new DriveCommand(150, 1, 0, 5));
    }
}
