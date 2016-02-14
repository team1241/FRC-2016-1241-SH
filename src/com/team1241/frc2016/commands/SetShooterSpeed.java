package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeed extends Command {

	private double rpm;
    
    public SetShooterSpeed(double rpm) {
    	this.rpm = rpm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.shooterPID.resetPID();
    	Robot.shooter.setShooterState(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.setRPM(rpm);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooter.getShooterState() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.shooterPID.resetPID();
    	Robot.shooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.shooterPID.resetPID();
    	Robot.shooter.setSpeed(0);
    }
}
