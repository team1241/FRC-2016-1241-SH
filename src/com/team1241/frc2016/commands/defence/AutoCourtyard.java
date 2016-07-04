package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCourtyard extends CommandGroup {

	public  AutoCourtyard(int location, int endLocation) {
		
		if(endLocation==NumberConstants.DEFAULT) {
			original(location);
		}
		else if(endLocation==NumberConstants.CENTER) {
			center(location);
		}
		
		
		if(Robot.connected) {
			addSequential(new CameraTrack(1.0));
			addParallel(new SetShooterSpeed(4000));
			addSequential(new AutoShoot());

	    	addSequential(new TurnTurret(0, 1, 5, false));
			if(endLocation==NumberConstants.CENTER)
				addSequential(new DriveCommand(-80, 1, 0, 3));
			else if(location==1 || location==4)
				addSequential(new DriveCommand(-100, 1, 0, 3));
			else if(location==2 || location==3)
				addSequential(new DriveCommand(-80, 1, 0, 3));
		}
	}
	
	public void original(int location) {
		if(location==1) {
			//Turn towards tower
			addParallel(new DriveCommand(160, 1, 0, 3));
			addSequential(new TimedTurnTurret(-70, 1, 3, false));
		}
		else if(location==2) {
			//Turn towards tower
			addSequential(new DrivePath("0,0","22.6,40","36,0","36,122",3.5,0.5));
		}
		else if(location==3) {
			addParallel(new DriveCommand(105, 1,0,2));
			addSequential(new TimedTurnTurret(10, 1, 2, false));
		}
		else if(location==4) {
			addParallel(new RunArm(NumberConstants.downArmAngle+30, 1, 3));
			addParallel(new DriveCommand(160, 1,0,3));
			addSequential(new TimedTurnTurret(60, 1, 3, false));
		}
	}
	
	public void center(int location) {
		if(location==1) {
			addSequential(new DrivePath("0,0","50,75","93,0","93,118",3.5,0.5));
		}
		else if(location==2) {
			original(location);
		}
		else if(location==3) {
			original(location);
		}
		else if(location==4) {
			
		}
	}
}
