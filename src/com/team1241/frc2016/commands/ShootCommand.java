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
	private SetShooterSpeed spy;
	ToggleBoolean toggleTurret;
	private boolean auto = true;
	
	
    public ShootCommand() {
    	requires(Robot.shooter);
    	toggleTurret = new ToggleBoolean();
    	badder = new SetShooterSpeed(NumberConstants.badderShotRPM);
    	outer = new SetShooterSpeed(NumberConstants.outerShotRPM);
    	spy = new SetShooterSpeed(NumberConstants.spyShotRPM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// if arm position is too high, put it down before shooting
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	Robot.shooter.turnTurret(-Robot.oi.getToolRightX()*0.5);
    	if(Robot.oi.getToolYButton()) {
    		toggleTurret.set(true);
    		auto = false;
    	}
    	else
    		auto = true;
    	
    	if(toggleTurret.get() && auto) {
    		new TurnTurretToAngle(NumberConstants.spyShotAngle, 1, 10).start();
    	}
    	else if(!toggleTurret.get() && auto) {
    		new TurnTurretToAngle(0, 1, 10).start();
    	} 	
    	
    	if (Robot.oi.getToolLeftTrigger()) {
    		badder.cancel();
    		outer.cancel();
    		spy.start();
    	}
    	else if (Robot.oi.getToolLeftBumper()) {
    		outer.cancel();
    		spy.cancel();
    		badder.start();
    	}
    	else if(Robot.oi.getToolRightBumper()) {
    		spy.cancel();
    		badder.cancel();
    		outer.start();    		
    	}
    	else if(Robot.oi.getToolRightTrigger()) {
    		new ShootSequence().start();
    		Robot.conveyor.setContains(false);
    	}
    	else if(Robot.oi.getToolBackButton()) {
    		Robot.shooter.setSpeed(0);
    		spy.cancel();
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
