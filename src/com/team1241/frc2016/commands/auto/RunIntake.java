package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @since 2016-01-30
 */
public class RunIntake extends Command {
	public static final int INTAKE = 0;
	public static final int OUTTAKE = 1;
	public static final int STATIC = 2;
	
	private int direction;
	
    public RunIntake(int direction) {
    	this.direction = direction;
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction==INTAKE) {
    		Robot.intake.runIntake(1);
    	}
    	else if(direction==OUTTAKE) {
    		Robot.intake.runIntake(-1);
    	}
    	else {
    		Robot.intake.runIntake(0);
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
