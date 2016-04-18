package com.team1241.frc2016.commands;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraTrack extends Command {

	private double timeOut;
	private double[] target;
	private double xVal;
	private double prevXVal = 0;
	private double degree;
	private boolean hasChanged = true;
	private boolean started = false;
	
	private TurnTurret turret;
	
    public CameraTrack() {
    	this(2.0);
    }
    
    public CameraTrack(double timeOut) {
    	this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hasChanged = true;
    	started = false;
    	prevXVal = 0;
    	target = Robot.shooter.getCoordinates();
    	if(timeOut>0)
    		setTimeout(timeOut);
    	turret = new TurnTurret(0,0.5,1, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.updateCoordinates();
    	target = Robot.shooter.getCoordinates();
    	if(target.length == 8) {
	    	xVal = Robot.shooter.getXCoordinates();
	    	
	    	//Robot.shooter.turnTurretToPixel(xVal,1);
	    	if(prevXVal != xVal)
	    		hasChanged = true;
	    	/*degree = Robot.shooter.pixelToDegree(xVal);
	    	Robot.shooter.turnTurretToAngle(Robot.shooter.getTurretAngle() - degree,1);*/
	    	if(hasChanged && !started){
	    		turret.cancel();
	    		degree = Robot.shooter.pixelToDegree(xVal);
	    		System.out.println("SeTPOINT: " + (Robot.shooter.getTurretAngle()-degree+NumberConstants.cameraOffset) + " Degree: " + degree + " Angle: " + Robot.shooter.getTurretAngle());
	    		turret.changeAngle(Robot.shooter.getTurretAngle()-degree+ NumberConstants.cameraOffset);
	    		turret.start();
	    		hasChanged = false;
	    	}
	    	if(turret.isFinished())
	    		started = false;
	    	else
	    		started = true;
	    	
	    	prevXVal = xVal;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timeOut==-1) {
    		return false;
    	} else {
    		return isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.turnTurret(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.turnTurret(0);
    }
}
