package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraTrack extends Command {

	
    public CameraTrack() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = Robot.drive.cogx;
    	SmartDashboard.putNumber("X",x);
//    	if (x < 250 && x != 0) {
//    		Robot.drive.runLeftDrive(-0.2);
//    		Robot.drive.runRightDrive(-0.2);
//    	}
//    	else if (x > 450 && x != 0){
//    		Robot.drive.runLeftDrive(0.2);
//    		Robot.drive.runRightDrive(0.2);
//    	}
//    	else {
//    		Robot.drive.runLeftDrive(0);
//    		Robot.drive.runRightDrive(0);
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
