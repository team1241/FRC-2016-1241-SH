package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ContinousMotion extends Command {

    private double speed;
	private double setPoint;
	private double timeOut;
	
	public ContinousMotion(double speed, double setPoint, double timeOut) {
        this.speed = speed;
        this.setPoint = setPoint;
        this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.reset();
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.driveAngle(0, speed);
//    	Robot.drive.runLeftDrive(speed);
//    	Robot.drive.runRightDrive(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || Robot.drive.getAverageDistance()>setPoint;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
