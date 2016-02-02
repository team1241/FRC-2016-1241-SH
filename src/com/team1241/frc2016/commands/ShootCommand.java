package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command to the Shooter subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
//4600 FOR OUTERWORKS
//4400 FOR SPY
//3800 - 4400 FOR BATTER - OFF BATTER   
public class ShootCommand extends Command {

	public static double power;
    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	power = 0.75;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.turnTurret(Robot.oi.getToolRightX());
    	if(Robot.oi.getToolLeftBumper()) {
//    		Robot.shooter.setLeft(-1);
    		power += 0.005;
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
    	}
    	else if(Robot.oi.getToolRightBumper()) {
//    		Robot.shooter.setRight(-1);
    		power -=0.005;
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
    	}
    	else if(Robot.oi.getToolLeftTrigger()) {
    		Robot.shooter.setLeft(-power);
    		Robot.shooter.setRight(-power);
    	}
    	else if(Robot.oi.getToolRightTrigger()) {
    		Robot.shooter.setLeft(-.74);
    		Robot.shooter.setRight(-.74);
    	}
    	else if(Robot.oi.getToolYButton()) {
    		Robot.shooter.setLeft(.4);
    		Robot.shooter.setRight(.4);
    	}
    	else {
    		Robot.shooter.setSpeed(0);
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
