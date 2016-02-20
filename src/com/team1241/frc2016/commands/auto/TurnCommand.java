package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Bryan
 */
public class TurnCommand extends Command {

	private double angle;
	private double speed;
	private double timeOut;
	
    public TurnCommand(double angle, double speed, double timeOut) {
    	this.angle = angle;
    	this.speed = speed;
    	this.timeOut = timeOut;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.turnDrive(angle, speed, 1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
//        return angle == Robot.drive.getYaw() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
