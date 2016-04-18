package com.team1241.frc2016.commands;

import java.util.Arrays;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiveTrack extends Command {	

	public static boolean tracking = false;
	
	private double[] target;
	private double xVal;
	private double degree;
	private double angle;
	private double power = 0.5;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		tracking = true;
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.updateCoordinates();
		target = Robot.shooter.getCoordinates();
		if(target.length==8) {
			xVal = Robot.shooter.getXCoordinates();
			
			degree = Robot.shooter.pixelToDegree(xVal);
			
			
//			Robot.shooter.turnTurretCamera(Robot.shooter.getTurretAngle()-degree+NumberConstants.cameraOffset, 0.5);
			Robot.shooter.liveTrack(Robot.shooter.getTurretAngle()-degree+NumberConstants.cameraOffset);
		}
		else {
			tracking = false;
		}
	}
	
	@Override
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !tracking;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.turnTurret(0);
		tracking = false;
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		tracking = false;
		Robot.shooter.turnTurret(0);
	}
}
