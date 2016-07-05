package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ActuateHolder;
import com.team1241.frc2016.commands.ActuateHood;
import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.ExtendPopper;
import com.team1241.frc2016.commands.RetractPopper;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.StopShooter;
import com.team1241.frc2016.commands.TurnTurret;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BackwardsMoat extends CommandGroup {
    
    public  BackwardsMoat() {
        //Drive to defense
    	addSequential(new ActuateHood(true));
    	addParallel(new ContinousMotion(-0.9, -60, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+160, 0.6, 1.5));
    	
    	//Drives over the defense
    	addParallel(new TurnTurret(-190, 1.0, 2.0, false));
    	addParallel(new SetShooterSpeed(4500));
//    	addSequential(new ContinousMotion(-0.8, -45, 2.75));
    	addSequential(new DriveCommand(-60, 1, 0, 1.75));
    	
    	addSequential(new WaitCommand(0.5));
    	addSequential(new CameraTrack(0.5));
    	addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(0.1));
		addSequential(new ExtendPopper());
		addSequential(new WaitCommand(0.5));
		addSequential(new RetractPopper());
		
    	//Driving Forward
//    	addParallel(new ContinousMotion(0.8, 40, 1.5));
    	addSequential(new RunIntake(RunIntake.INTAKE));
    	
    	addParallel(new ContinousMotion(0.90, 40, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+150, 0.6, 1.5));
    	
    	addParallel(new DriveCommand(115, 0.8, 0, 2.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+10, 0.6, 2.5));
    	
    	addParallel(new ContinousMotion(-0.8, -70, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+160, 0.6, 1.5));
    	
    	//Drives over the defense
    	addParallel(new SetShooterSpeed(4500));
//    	addSequential(new ContinousMotion(-0.8, -45, 2.75));
    	addSequential(new DriveCommand(-85, 1, 0, 1.25));
    	
    	addSequential(new WaitCommand(0.5));
    	addSequential(new CameraTrack(1.0));
    	addSequential(new ActuateHolder(false));
		addSequential(new WaitCommand(0.1));
		addSequential(new ExtendPopper());
		addSequential(new WaitCommand(0.5));
		addSequential(new RetractPopper());
		addSequential(new RunIntake(RunIntake.STATIC));
		addSequential(new StopShooter());
		addSequential(new TurnTurret(0, 1, 2,false));
    	
    }
}
