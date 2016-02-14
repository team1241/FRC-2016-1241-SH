package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.AutoHolder;
import com.team1241.frc2016.commands.auto.WaitCommand;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command for the conveyor subsystem
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class ConveyorCommand extends Command {

	ToggleBoolean toggle;
	private boolean auto = true;
	
    public ConveyorCommand() {
    	toggle = new ToggleBoolean();
        requires(Robot.conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.conveyor.getOptic() && auto && !Robot.conveyor.getContains()) {
    		Robot.conveyor.setContains(true);
    		new AutoHolder().start();
    	}
    	
    	if(Robot.oi.getToolBButton()) {
    		toggle.set(true);
    		auto = false;
    	}
    	else {
    		auto = true;
    	}
    	
    	
    	if(!auto && toggle.get()) {
    		Robot.conveyor.extendHolder();
    		Robot.conveyor.setContains(true);
    	}
    	else if(!auto && !toggle.get()) {
    		Robot.conveyor.retractHolder();
    		Robot.conveyor.setContains(false);
    	}
    	
    	if(Robot.oi.getToolAButton()) {
    		Robot.conveyor.runMotor(-1);
    	}
    	else if(Robot.oi.getToolXButton()) {
    		Robot.conveyor.runMotor(1);
    	}
    	else {
    		Robot.conveyor.runMotor(0);
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
