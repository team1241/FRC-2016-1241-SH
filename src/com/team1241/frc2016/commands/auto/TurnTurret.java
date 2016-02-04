package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class TurnTurret extends Command {

	private double setPoint;
	private double speed;
	private double timeOut;
	
    public TurnTurret(double setPoint, double speed, double timeOut) {
    	this.setPoint = setPoint;
    	this.speed = speed;
    	this.timeOut = timeOut;
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.turnTurretTo(setPoint, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut()||Robot.shooter.turretPID.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.turnTurret(0);
    	Robot.shooter.turretPID.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.turnTurret(0);
    	Robot.shooter.turretPID.resetPID();
    }
}
