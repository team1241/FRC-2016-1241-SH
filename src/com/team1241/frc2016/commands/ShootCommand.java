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

	private SetShooterSpeed badderRPM;
	private SetShooterSpeed outerRPM;
	private SetShooterSpeed spyRPM;
	
	private TurnTurret spyAngle;
	private TurnTurret startAngle;
	ToggleBoolean toggleTurret;
	private boolean auto = true;
	
	
    public ShootCommand() {
    	requires(Robot.shooter);
    	toggleTurret = new ToggleBoolean();
    	badderRPM = new SetShooterSpeed(NumberConstants.badderShotRPM);
    	outerRPM = new SetShooterSpeed(NumberConstants.outerShotRPM);
    	spyRPM = new SetShooterSpeed(NumberConstants.spyShotRPM);
    	
    	spyAngle = new TurnTurret(NumberConstants.spyShotAngle, 1, 10);
    	startAngle = new TurnTurret(0, 1, 10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// if arm position is too high, put it down before shooting
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.oi.getToolRightX() != 0) {
//    		auto = false;
//    		startAngle.cancel();
//    		spyAngle.cancel();
//    		Robot.shooter.turnTurret(-Robot.oi.getToolRightX()*0.5);
//    	}
    	
    	if(Robot.oi.getToolYButton()) {
    		toggleTurret.set(true);
    		auto = true;
    	}
    	
    	if(toggleTurret.get() && auto) {
    		startAngle.cancel();
    		spyAngle.start();
    	}
    	else if(!toggleTurret.get() && auto) {
    		spyAngle.cancel();
    		startAngle.start();
    	} 	
    	
    	if (Robot.oi.getToolLeftTrigger()) {
    		Robot.shooter.setShooterState(true);
    		badderRPM.cancel();
    		outerRPM.cancel();
    		spyRPM.start();
    	}
    	else if (Robot.oi.getToolLeftBumper()) {
    		Robot.shooter.setShooterState(true);
    		outerRPM.cancel();
    		spyRPM.cancel();
    		badderRPM.start();
    	}
    	else if(Robot.oi.getToolRightBumper()) {
    		Robot.shooter.setShooterState(true);
    		badderRPM.cancel();
    		spyRPM.cancel();
    		outerRPM.start();    		
    	}
    	else if(Robot.oi.getToolRightTrigger()) {
    		new ShootSequence().start();
//    		Robot.conveyor.setContains(false);
    	}
    	else if(Robot.oi.getToolBackButton()) {
    		Robot.shooter.setShooterState(false);
    	}
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
