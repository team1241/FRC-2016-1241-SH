package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCourtyard extends CommandGroup {

	public  AutoCourtyard(int location) {
		if(location==1) {
			//Turn towards tower
			addParallel(new DriveCommand(160, 1, 0, 3));
			addSequential(new TimedTurnTurret(-70, 1, 3, false));
			
//			addSequential(new WaitAndTrack(2.0));
//			addSequential(new WaitCommand(2.0));
			addSequential(new CameraTrack(1.0));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
		else if(location==2) {
			//Turn towards tower
			addSequential(new DrivePath("0,0","22.6,40","36,0","36,122",3.5,0.5));
			
//			addSequential(new WaitAndTrack(0.5));
			addParallel(new CameraTrack(1.0));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
		else if(location==3) {
			addParallel(new DriveCommand(105, 1,0,2));
			addSequential(new TimedTurnTurret(10, 1, 2, false));
			
//			addSequential(new WaitAndTrack(0.5));
//			addSequential(new WaitCommand(0.5));
			addSequential(new CameraTrack(1.0));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
		else if(location==4) {
			addParallel(new RunArm(NumberConstants.downArmAngle+30, 1, 3));
			addParallel(new DriveCommand(160, 1,0,3));
			addSequential(new TimedTurnTurret(60, 1, 3, false));
			
//			addSequential(new WaitAndTrack(1.5));
//			addSequential(new WaitCommand(1.5));
			addSequential(new CameraTrack(1.0));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
	}
}
