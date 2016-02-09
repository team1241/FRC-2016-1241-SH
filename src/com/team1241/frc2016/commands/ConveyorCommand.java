package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command for the conveyor subsystem
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class ConveyorCommand extends Command {

    public ConveyorCommand() {
        requires(Robot.conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.runArms(-Robot.oi.getToolLeftY()*0.4);
    	
    	if(Robot.oi.getToolAButton()) {
    		Robot.conveyor.runMotor(-1);
    		Robot.intake.runIntake(1);
    	}
    	else if(Robot.oi.getToolBButton()) {
    		Robot.conveyor.runMotor(1);
    		Robot.intake.runIntake(-1);
    	}
    	else {
    		Robot.conveyor.runMotor(0);
    		Robot.intake.runIntake(0);
    	}
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
