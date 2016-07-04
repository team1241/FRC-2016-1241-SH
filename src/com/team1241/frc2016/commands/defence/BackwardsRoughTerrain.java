package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BackwardsRoughTerrain extends CommandGroup {
    
    public  BackwardsRoughTerrain() {
        //Drive to defense
    	addSequential(new ActuateHood(true));
    	addParallel(new ContinousMotion(-0.6, -40, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+160, 0.6, 1.5));
    	
    	//Drives over the defense
//    	addParallel(new RunArm(NumberConstants.downArmAngle+100, 1, 1.75));
    	addParallel(new TurnTurret(-190, 1.0, 3, false));
    	addParallel(new SetShooterSpeed(4500));
//    	addSequential(new ContinousMotion(-0.8, -45, 1.75));
    	addSequential(new DriveCommand(-55, 1, 0, 1.75));
    	
    	addSequential(new WaitCommand(0.5));
    	addSequential(new CameraTrack(1.0));
    	addSequential(new ShootSequence());
		
    	//Driving Forward
//    	addParallel(new ContinousMotion(0.8, 40, 1.5));
//    	addSequential(new RunArm(NumberConstants.downArmAngle+50, 0.6, 1.5));
    	addSequential(new RunIntake(RunIntake.INTAKE));
    	
    	addParallel(new DriveCommand(170, 0.7, 0, 3.0));
    	addSequential(new RunArm(NumberConstants.downArmAngle+10, 0.6, 3.0));
    	
    	addParallel(new ContinousMotion(-0.6, -40, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 0.6, 1.5));
    	
    	//Drives over the defense
    	addParallel(new RunArm(NumberConstants.downArmAngle+100, 1, 1.75));
    	addParallel(new SetShooterSpeed(4500));
    	addSequential(new DriveCommand(-65, 1, 0, 1.75));
    	
    	addSequential(new WaitCommand(0.5));
    	addSequential(new CameraTrack(1.0));
    	addSequential(new ShootSequence());
    	addSequential(new DriveCommand(-20, 1, 0, 1.75));
    }
}
