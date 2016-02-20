package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTurret extends Command {

	private double angle;
	private double power;
	private double timeout;
	private boolean camera;
    public TurnTurret(double angle, double power, double timeout, boolean camera) {
//    	requires(Robot.shooter);
    	this.power = power;
    	this.angle = angle;
    	this.timeout = timeout;
    	this.camera = camera;
    }
    
    public void changeAngle(double angle){
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    	Robot.shooter.turretPID.resetPID();
    	Robot.shooter.cameraPID.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(camera){
    		Robot.shooter.turnTurretCamera(angle, power);
    	}
    	else
    		Robot.shooter.turnTurretToAngle(angle, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooter.turretPID.isDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.turretPID.resetPID();
    	Robot.shooter.turnTurret(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.turretPID.resetPID();
    	Robot.shooter.turnTurret(0);
    }
}
