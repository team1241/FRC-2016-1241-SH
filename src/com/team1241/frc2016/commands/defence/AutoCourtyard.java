package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCourtyard extends CommandGroup {

	public  AutoCourtyard(int location) {
		//Drive up a bit
		addSequential(new DriveCommand(20, 1, 0, 5));
		if(location==1) {
			//Turn towards tower
			addSequential(new TurnCommand(90, 1, 5));
			
			addSequential(new DriveCommand(75, 1, 0, 5));
			//Turn towards tower
			addSequential(new TurnCommand(-90, 1, 5));
		}
		else if(location==2) {
			//Turn towards tower
			addSequential(new TurnCommand(90, 1, 5));
			
			addSequential(new DriveCommand(25, 1, 0, 5));
			//Turn towards tower
			addSequential(new TurnCommand(-90, 1, 5));
		}
		else if(location==3) {
			//Turn towards tower
			addSequential(new TurnCommand(-90, 1, 5));
			
			addSequential(new DriveCommand(10, 1, 0, 5));
			//Turn towards tower
			addSequential(new TurnCommand(90, 1, 5));
		}
		else if(location==4) {
			//Turn towards tower
			addSequential(new TurnCommand(-90, 1, 5));

			addSequential(new DriveCommand(35, 1, 0, 5));
			//Turn towards tower
			addSequential(new TurnCommand(90, 1, 5));
		}
	}
}
