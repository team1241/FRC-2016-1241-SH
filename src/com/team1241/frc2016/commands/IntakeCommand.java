package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.RunArm;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Default command for the intake subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class IntakeCommand extends Command {
	
	ToggleBoolean toggle;
	RunArm up;
	RunArm down;
	private boolean auto = false;

    public IntakeCommand() {
    	toggle = new ToggleBoolean();
    	up = new RunArm(NumberConstants.upArmAngle, 0.5, 3);
    	down = new RunArm(NumberConstants.downArmAngle,0.5,3);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getToolLeftAnalogButton()) {
    		toggle.set(true);
    		auto = true;
    	}
    	
    	if(Robot.oi.getToolLeftY()!=0) {
    		auto = false;
    		up.cancel();
    		down.cancel();
    		Robot.intake.runArms(Robot.oi.getToolLeftY()*0.6);
    	}
    	else if(!auto) {
    		Robot.intake.runArms(0);
    	}
//    	else if(toggle.get() && auto){
//    		up.cancel();
//    		down.start();
//    	}
//    	else if(!toggle.get() && auto){
//    		down.cancel();
//    		up.start();
//    	}
    	
    	if(Robot.oi.getToolAButton()) {
    		Robot.intake.runIntake(1);
    	}
    	else if(Robot.oi.getToolXButton()) {
    		Robot.intake.runIntake(-1);
    	}
    	else {
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