package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.defence.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OuterWorksAuton extends CommandGroup {
    
    public  OuterWorksAuton(int defenceLoc, int defence) {
        //drive in front of defense
    	
    	System.out.println(Robot.selectedDefence +","+ Robot.defenceLocation);
    	//Cross the defence
    	if(defence==0) {
        	addSequential(new AutoPortcullis());
        }
        else if(defence==1) {
        	addSequential(new AutoCheval());
        }
        else if(defence==2) {
        	addSequential(new AutoSallyPort());
        }
        else if(defence==3) {
        	addSequential(new AutoDrawbridge());
        }
        else if (defence==4) {
        	addSequential(new AutoDriveOver());
        }
    	
    	//Drive curve towards the tower
    	addSequential(new AutoCourtyard(defenceLoc));
    }
}