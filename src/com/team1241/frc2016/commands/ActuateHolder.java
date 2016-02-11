package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class ActuateHolder extends Command {

	private boolean actuate;
	
    public ActuateHolder(boolean actuate) {
    	this.actuate = actuate;
        requires(Robot.shooter);
    }
    
    public ActuateHolder() {
    	this(!Robot.conveyor.getHoldState());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (actuate) {
    		Robot.conveyor.extendHolder();
    	}
    	else {
    		Robot.conveyor.retractHolder();
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
