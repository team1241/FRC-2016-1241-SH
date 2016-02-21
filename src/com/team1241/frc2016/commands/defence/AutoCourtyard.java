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
			addParallel(new DriveCommand(170, 1, 0, 5));
			addSequential(new TurnTurret(-70, 1, 5, false));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
		else if(location==2) {
			//Turn towards tower
			addParallel(new RunArm(NumberConstants.downArmAngle+50, 1, 5));
			addSequential(new DrivePath("0,0","22.6,40","36,0","36,100",5,0.5));
			
			addParallel(new DriveCommand(30, 1, -3, 5));
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
		else if(location==3) {
			addParallel(new DriveCommand(100, 1,0,5));
			addSequential(new TurnTurret(10, 1, 5, false));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
		else if(location==4) {
			addParallel(new RunArm(NumberConstants.downArmAngle+30, 1, 5));
			addSequential(new DriveCommand(165, 1,0,5));
			addSequential(new TurnTurret(63, 1, 5, false));
			
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());
		}
	}
}
