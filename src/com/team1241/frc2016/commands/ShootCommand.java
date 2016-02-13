package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.WaitCommand;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The Default command to the Shooter subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
//4600 FOR OUTERWORKS
//4400 FOR SPY
//3800 - 4400 FOR BATTER - OFF BATTER   
public class ShootCommand extends Command {

	private SetShooterSpeed badder;
	private SetShooterSpeed outer;
	ToggleBoolean toggleTurret;
    public ShootCommand() {
    	requires(Robot.shooter);
    	toggleTurret = new ToggleBoolean();
    	badder = new SetShooterSpeed(NumberConstants.badderShotRPM);
    	outer = new SetShooterSpeed(NumberConstants.spyShotRPM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// if arm position is too high, put it down before shooting
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	Robot.shooter.turnTurret(-Robot.oi.getToolRightX()*0.5);
    	toggleTurret.set(Robot.oi.getToolYButton());
    	
    	if(toggleTurret.get()) {
    		new TurnTurretToAngle(-43, 1, 10).start();
    	}
    	else {
    		new TurnTurretToAngle(0, 1, 10).start();
    	} 	
    	
    	if (Robot.oi.getToolLeftTrigger()) {
    		outer.start();
    		badder.cancel();
    	}
    	else if (Robot.oi.getToolLeftBumper()) { 
    		outer.cancel();
    		badder.start();
    	}
    	else if(Robot.oi.getToolRightTrigger()) {
    		new ShootSequence().start();
    	}
    	else if(Robot.oi.getToolRightBumper()) {
    		Robot.shooter.setSpeed(0);
    		outer.cancel();
    		badder.cancel();
    	}

    	
//    	else if(Robot.oi.getToolYButton()) {
////    		new ActuateHood().start();
////    		Robot.oi.actuateHood();	
//    		Robot.shooter.closeHood();
//    	}
//    	else {
//    		Robot.shooter.openHood();
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
