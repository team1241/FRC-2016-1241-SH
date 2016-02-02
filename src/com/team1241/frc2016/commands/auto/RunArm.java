package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class RunArm extends Command {

	private double angle;
	private double speed;
	private double timeOut;
	
    public RunArm(double angle, double speed, double timeOut) {
    	this.angle = angle;
    	this.speed = speed;
    	this.timeOut = timeOut;
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setArmPosition(angle,speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut()||Robot.intake.armPID.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.runArms(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
