package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.*;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBackCourtyard extends CommandGroup {

	public  AutoBackCourtyard(int startLocation, int endLocation) {
		if(startLocation==NumberConstants.CENTER) {
			if(endLocation==1) {
				addSequential(new DrivePath("0,0","50,75","93,0","93,118",3.5,0.5, true));
			}
			else if(endLocation==2) {
				addSequential(new DrivePath("0,0","22.6,40","36,0","36,122",3.5,0.5, true));
			}
			else if(endLocation==3) {
				addSequential(new DrivePath("0,0","50,75","93,0","93,118",3.5,0.5, true));
			}
			else if(endLocation==4) {
				addSequential(new DrivePath("0,0","50,75","93,0","93,118",3.5,0.5, true));
			}
		}
	}
}
