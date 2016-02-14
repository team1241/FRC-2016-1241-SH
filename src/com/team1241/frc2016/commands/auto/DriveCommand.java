package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class DriveCommand extends Command {
	private double distance;
	private double speed;
	private double angle;
	private double timeOut;
	private double epsilon;
	

    public DriveCommand(double setPoint, double speed, double angle, double timeOut, double epsilon) {
    	this.distance = setPoint;
    	this.speed = speed;
    	this.angle = angle;
    	this.timeOut = timeOut;
    	this.epsilon = epsilon;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.reset();
    	setTimeout(this.timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.driveStraight(distance, speed, angle, epsilon);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();//Robot.drive.drivePID.isDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    	Robot.drive.drivePID.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.drivePID.resetPID();
    }
}
