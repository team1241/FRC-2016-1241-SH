package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.defence.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OuterWorksAuton extends CommandGroup {
    
    public  OuterWorksAuton() {
        //drive in front of defense
    	
    	//Cross the defence
    	this.crossDefence();
    	
    	//Drive curve towards the tower
    	this.driveCurve();
    }
    
    private void crossDefence() {
    	int defence = Robot.selectedDefence;
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
//        	addSequential(new AutoPortcullis());
        }
    }
    
    private void driveCurve() {
    	int location = Robot.defenceLocation;
    	if(location==0) {
        	addSequential(new DriveCommand(1,1,1,1));
        }
        else if(location==1) {
        	addSequential(new DriveCommand(1,1,1,1));
        }
        else if(location==2) {
        	addSequential(new DriveCommand(1,1,1,1));
        }
        else if(location==3) {
        	addSequential(new DriveCommand(1,1,1,1));
        }
        else if (location==4) {
        	addSequential(new DriveCommand(1,1,1,1));
        }
    }
}
