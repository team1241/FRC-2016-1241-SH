package com.team1241.frc2016.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class SetShooterSpeed extends Command {
	
	private double velocity;
	
    public SetShooterSpeed(double setVelocity) {
    	this.velocity = velocity;
//        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
