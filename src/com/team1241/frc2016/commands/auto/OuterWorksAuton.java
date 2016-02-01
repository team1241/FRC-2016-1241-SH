package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

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
    	
    }
    
    private void crossDefence() {
    	int defence = Robot.selectedDefence;
    	if(defence==0) {
        	
        }
        else if(defence==1) {
        	
        }
        else if(defence==2) {
        	
        }
        else if(defence==3) {
        	
        }
        else if (defence==4) {
        	
        }
    }
}
