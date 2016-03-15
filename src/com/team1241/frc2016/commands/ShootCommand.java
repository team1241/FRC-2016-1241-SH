package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.WaitCommand;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
	private TurnTurret startAngle;
	private CameraTrack track;
	
	public static boolean tracked;
	public static boolean detects;
	
	ToggleBoolean toggleTurret;
	private boolean auto = false;
	
	
    public ShootCommand() {
    	requires(Robot.shooter);
    	toggleTurret = new ToggleBoolean();
    	badderRPM = new SetShooterSpeed(NumberConstants.badderShotRPM);
    	outerRPM = new SetShooterSpeed(NumberConstants.outerShotRPM);
    	spyRPM = new SetShooterSpeed(NumberConstants.spyShotRPM);
    	
    	spyAngle = new TurnTurret(NumberConstants.spyShotAngle, 1, 5, false);
    	backAngle = new TurnTurret(NumberConstants.backAngle, 1, 5, false);
    	startAngle = new TurnTurret(0, 1, 5, false);
    	
    	tracked = false;
    	detects = false;
    	
    	track = new CameraTrack();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// if arm position is too high, put it down before shooting
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	badderRPM.changeRPM(Robot.rpm);
    	if(Robot.shooter.getXCoordinates()!=-1) {
    		detects = true;
    	}
    	else {
    		detects = false;
    	}
    	
    	if(Math.abs(Robot.shooter.pixelToDegree(Robot.shooter.getXCoordinates())-1.5) <= 1)
			tracked = true;
		else
			tracked = false;
    	
    	if(Robot.oi.getToolRightAnalogButton()) {
    		spyAngle.cancel();
    		startAngle.cancel();
    		track.cancel();
    		backAngle.start();
    		auto = false;
    	}
    	
    	if(Robot.oi.getToolYButton()) {
    		toggleTurret.set(true);
    		auto = true;
    	}
    	if(Math.abs(Robot.oi.getToolRightX()) > 0.05) {
    		auto = false;
    		spyAngle.cancel();
    		startAngle.cancel();
    		track.cancel();
    		backAngle.cancel();
    		Robot.shooter.turnTurret(-Robot.oi.getToolRightX()*0.5);
    	}
    	else if(toggleTurret.get() && auto) {
    		track.cancel();
    		startAngle.cancel();
    		backAngle.cancel();
    		spyAngle.start();
    	}
    	else if(!toggleTurret.get() && auto) {
    		track.cancel();
    		spyAngle.cancel();
    		backAngle.cancel();
    		startAngle.start();
    	}
    	else {
    		Robot.shooter.turnTurret(0);
    	}
    	
    	if(Robot.oi.getToolStartButton()){
    		spyAngle.cancel();
    		startAngle.cancel();
    		backAngle.cancel();
    		track.start();
    		auto = false;
    	}
    	
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
}
