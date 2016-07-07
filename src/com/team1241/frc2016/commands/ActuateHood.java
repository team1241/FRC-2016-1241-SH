package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class ActuateHood extends Command {

	private boolean actuate;
	
    public ActuateHood(boolean actuate) {
    	this.actuate = actuate;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (actuate) {
    		Robot.shooter.extendHood();
    	}
    	else {
    		if(ShootCommand.toggleHood.get())
    			ShootCommand.toggleHood.set(true);
    		Robot.shooter.retractHood();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
