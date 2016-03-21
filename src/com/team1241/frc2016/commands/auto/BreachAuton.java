package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.TurnTurret;
import com.team1241.frc2016.commands.defence.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BreachAuton extends CommandGroup {	
    public  BreachAuton(int defence) {
    	//Cross the defence
//    	addSequential(new WaitCommand(5.0));
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
    	
    	addSequential(new DriveCommand(50, 1.0, 0, 5));
    }
}