package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command to the Shooter subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class ShootCommand extends Command {

    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.turnTurret(Robot.oi.getToolRightX());
    	if(Robot.oi.getToolLeftTrigger()) {
    		Robot.shooter.setLeft(-.71);
    		Robot.shooter.setRight(-.71);
    	}
    	else if(Robot.oi.getToolRightTrigger()) {
    		Robot.shooter.setLeft(-.735);
    		Robot.shooter.setRight(-.735);
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
