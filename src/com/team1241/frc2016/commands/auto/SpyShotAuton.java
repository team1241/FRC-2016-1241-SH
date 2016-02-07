package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.ShootSequence;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyShotAuton extends CommandGroup {
    
	    public  SpyShotAuton() {
    	double power = 0.75;
    	double targetAngle = 90;

    	// lower arm
    	if (Robot.intake.isArmPosTooHigh())
    		addSequential(new RunArm(0, power, 1));
    	
    	// Turn turret to target
    	addParallel(new TurnTurret(targetAngle, power, 1));
    	
    	// Shoot @ 1000 rpm (guesstimate)
    	addParallel(new SetShooterSpeed(1000, power, 2));
    	addSequential(new ShootSequence());
    	
    	// Turn turret to initial
    	addSequential(new TurnTurret(0, power, 1));	
    }
}
