package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.WaitCommand;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Default command to the Shooter subsystem.
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class ShootCommand extends Command {

	private SetShooterSpeed badderRPM;
	private SetShooterSpeed outerRPM;
	private SetShooterSpeed spyRPM;
	
	private TurnTurret spyAngle;
	private TurnTurret backAngle;
	private TurnTurret originAngle;
	private CameraTrack track;
	
	private TurnTurret leftAngle;
	private TurnTurret rightAngle;
	
	private TurnTurret leftSquareAngle;
	private TurnTurret rightSquareAngle;
	
	public static boolean tracked;
	public static boolean detects;
	
	ToggleBoolean toggleTurret;
	public static boolean auto = false;
	
	
    public ShootCommand() {
    	requires(Robot.shooter);
    	toggleTurret = new ToggleBoolean();
    	badderRPM = new SetShooterSpeed(NumberConstants.badderShotRPM);
    	outerRPM = new SetShooterSpeed(NumberConstants.outerShotRPM);
    	spyRPM = new SetShooterSpeed(NumberConstants.spyShotRPM);
    	
    	spyAngle = new TurnTurret(NumberConstants.spyShotAngle, 1, 3, false);
    	backAngle = new TurnTurret(-180, 1, 3, false);
    	originAngle = new TurnTurret(0, 1, 3, false);
    	leftAngle = new TurnTurret(67, 1, 3, false);
    	rightAngle = new TurnTurret(-67, 1, 3, false);
    	
    	leftSquareAngle = new TurnTurret(90, 1, 3, false);
    	rightSquareAngle = new TurnTurret(-90, 1, 3, false);
    	
    	tracked = false;
    	
    	//1.2
    	track = new CameraTrack(1.2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// if arm position is too high, put it down before shooting
//    	originAngle.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	outerRPM.changeRPM(Robot.outerRPM);
    	spyRPM.changeRPM(Robot.spyRPM);
    	
    	//Turret//
    	if(Robot.oi.getToolRightX() > 0.9) {
    		rightSquareAngle.cancel();
    		leftSquareAngle.cancel();
    		backAngle.cancel();
    		originAngle.cancel();
    		leftAngle.cancel();
    		rightAngle.start();
    		
    	} else if(Robot.oi.getToolRightX() < -0.9) {
    		rightSquareAngle.cancel();
    		leftSquareAngle.cancel();
    		backAngle.cancel();
    		rightAngle.cancel();
    		originAngle.cancel();
    		leftAngle.start();
    	} else if(Robot.oi.getToolRightY() > 0.9) {
    		rightSquareAngle.cancel();
    		leftSquareAngle.cancel();
    		originAngle.cancel();
    		leftAngle.cancel();
    		rightAngle.cancel();
    		backAngle.start();
    	} else if(Robot.oi.getToolRightY() < -0.9) {
    		rightSquareAngle.cancel();
    		leftSquareAngle.cancel();
    		backAngle.cancel();
    		leftAngle.cancel();
    		rightAngle.cancel();
    		originAngle.start();
    	}
    	
    	else if(Robot.oi.getDriveLeftTrigger()) {
    		backAngle.cancel();
    		originAngle.cancel();
    		leftAngle.cancel();
    		rightAngle.cancel();
    		rightSquareAngle.cancel();
    		leftSquareAngle.start();
    	} else if(Robot.oi.getDriveRightTrigger()) {
    		backAngle.cancel();
    		originAngle.cancel();
    		leftAngle.cancel();
    		rightAngle.cancel();
    		leftSquareAngle.cancel();
    		rightSquareAngle.start();
    	}
    	
//    	if(Robot.oi.getToolRightAnalogButton()) {
//    		spyAngle.cancel();
//    		originAngle.cancel();
//    		track.cancel();
//    		backAngle.start();
// 
//    		auto = false;
//    	}
//    	
//    	if(Robot.oi.getToolYButton()) {
//    		toggleTurret.set(true);
//    		auto = true;
//    	}
//    	if(Math.abs(Robot.oi.getToolRightX()) > 0.05) {
//    		auto = false;
//    		spyAngle.cancel();
//    		originAngle.cancel();
//    		track.cancel();
//    		backAngle.cancel();
//    		Robot.shooter.turnTurret(-Robot.oi.getToolRightX()*0.5);
//    	}
//    	else if(toggleTurret.get() && auto) {
//    		track.cancel();
//    		originAngle.cancel();
//    		backAngle.cancel();
//    		spyAngle.start();
//    	}
//    	else if(!toggleTurret.get() && auto) {
//    		track.cancel();
//    		spyAngle.cancel();
//    		backAngle.cancel();
//    		originAngle.start();
//    	}
//    	else {
//    		Robot.shooter.turnTurret(0);
//    	}
    	
    	
    	//Track//
    	if(Robot.oi.getToolStartButton()){
    		spyAngle.cancel();
    		originAngle.cancel();
    		backAngle.cancel();
    		track.start();
    		auto = false;
    	}
    	
    	//Shooter//
    	if (Robot.oi.getToolLeftTrigger()) {
    		Robot.shooter.setShooterState(true);
    		badderRPM.cancel();
    		outerRPM.cancel();
    		spyRPM.start();
    	}
    	else if (Robot.oi.getToolLeftBumper()) {
    		Robot.shooter.setShooterState(true);
    		outerRPM.cancel();
    		spyRPM.cancel();
    		badderRPM.start();
    	}
    	else if(Robot.oi.getToolRightBumper()) {
    		Robot.shooter.setShooterState(true);
    		badderRPM.cancel();
    		spyRPM.cancel();
    		outerRPM.start();    		
    	}
    	else if(Robot.oi.getToolRightTrigger()) {
    		Robot.conveyor.setContains(true);
    		new ShootSequence().start();
//    		Robot.conveyor.setContains(false);
    	}
    	else if(Robot.oi.getToolBackButton()) {
    		Robot.shooter.setShooterState(false);
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
    
    private void cancelTurret() {
    	rightSquareAngle.cancel();
		leftSquareAngle.cancel();
		backAngle.cancel();
		originAngle.cancel();
		leftAngle.cancel();
		rightAngle.cancel();
    }
}
