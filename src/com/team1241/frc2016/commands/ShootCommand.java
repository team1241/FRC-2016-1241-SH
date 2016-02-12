package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.WaitCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The Default command to the Shooter subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
//4600 FOR OUTERWORKS
//4400 FOR SPY
//3800 - 4400 FOR BATTER - OFF BATTER   
public class ShootCommand extends Command {

    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// if arm position is too high, put it down before shooting
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.shooter.turnTurret(-Robot.oi.getToolRightX()*0.5);
    	
    	if(Robot.oi.getToolLeftTrigger()) {
    		Robot.shooter.setLeft(0);
    		Robot.shooter.setRight(0);
    	}
    	else if(Robot.oi.getToolLeftBumper()) {
    		Robot.shooter.setLeft(0.84);
    		Robot.shooter.setRight(0.84);
    		//new SetShooterSpeed(500, 0.25).start();
    	 }
    	
//    	Robot.oi.yButton.whenPressed(new ActuateHood());
//    	Robot.oi.xButton.whenPressed(new ActuateHolder());
    	
//    	if(Robot.oi.getToolRightTrigger()) {
//    		new TurnTurretToAngle(90, 1, 10).start();
//    	}
//    	else if (Robot.oi.getToolYButton()) {
//    		new TurnTurretToAngle(-90, 1, 10).start();
//    	} 	
    	else if(Robot.oi.getToolRightBumper()) {
    		new ShootSequence().start();
    	}
    	else if(Robot.oi.getToolYButton()) {
//    		new ActuateHood().start();
//    		Robot.oi.actuateHood();	
    		Robot.shooter.closeHood();
    	}
    	else {
    		Robot.shooter.openHood();
    	}
    	
    	if(Robot.oi.getToolXButton()) {
    		Robot.conveyor.extendHolder();
//    		Robot.oi.actuateHolder();
    	}
    	else {
    		Robot.conveyor.retractHolder();
//    		Robot.shooter.openHood();
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
