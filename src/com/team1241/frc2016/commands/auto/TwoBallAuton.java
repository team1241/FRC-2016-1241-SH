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
public class TwoBallAuton extends CommandGroup {	
    public  TwoBallAuton(int defence) {
    	//Cross the defence
        if (defence==4) { 
        	addSequential(new BackwardsRockWall());
        }
        else if (defence==5) {
        }
    	
//        addParallel(new TurnTurret(-180, 1,3, false));
//    	addSequential(new DriveCommand(10, 1.0, 0, 1));
//    	
//    	if(Robot.shooter.getXCoordinates()!=-1) {
//			addSequential(new CameraTrack(1.0));
//			addParallel(new SetShooterSpeed(4000));
//			addSequential(new AutoShoot());
//		}
//    	
//    	if (defence==4) { 
//        	addSequential(new AutoRockWall());
//        }
//        else if (defence==5) {
//        }
//    	
//    	addParallel(new RunIntake(RunIntake.INTAKE));
//    	addParallel(new DriveCommand(20, 0.5, 0, 1));
//    	addSequential(new RunArm(NumberConstants.downArmAngle, 1, 1));
//    	
//    	if (defence==4) { 
//        	addSequential(new BackwardsRockWall());
//        }
//        else if (defence==5) {
//        }
//    	
//        addParallel(new TurnTurret(-180, 1,3, false));
//    	addSequential(new DriveCommand(10, 1.0, 0, 1));
//    	
//    	if(Robot.shooter.getXCoordinates()!=-1) {
//			addSequential(new CameraTrack(1.0));
//			addParallel(new SetShooterSpeed(4000));
//			addSequential(new AutoShoot());
//		}
    }
}