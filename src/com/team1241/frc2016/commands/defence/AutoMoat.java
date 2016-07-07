package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.TurnTurret;
import com.team1241.frc2016.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class AutoMoat extends CommandGroup {
    
    public  AutoMoat() {
    	addParallel(new ContinousMotion(-0.9, -60, 1.5));
    	addSequential(new RunArm(NumberConstants.downArmAngle+160, 0.6, 1.5));
    	
    	//Drives over the defense
    	addParallel(new TurnTurret(-190, 1.0, 2.0, false));
    	addParallel(new SetShooterSpeed(4500));
//    	addSequential(new ContinousMotion(-0.8, -45, 2.75));
    	addSequential(new DriveCommand(-60, 1, 0, 1.75));
    }
}
