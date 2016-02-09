package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command for the intake subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        requires(Robot.intake);
        System.out.println("Initialized intake");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("running");
//    	Robot.intake.runArms(Robot.oi.getToolLeftY()*0.4);
//    	
//    	if(Robot.oi.getToolAButton()) {
//    		System.out.println("Intaking");
//    		Robot.intake.runIntake(1);
//    	}
//    	else if(Robot.oi.getToolBButton()) {
//    		System.out.println("outtaking");
//    		Robot.intake.runIntake(-1);
//    	}
//    	else {
//    		Robot.intake.runIntake(0);
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