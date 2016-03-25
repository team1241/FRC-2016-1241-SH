package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.utilities.TheoryCurve;
import edu.wpi.first.wpilibj.command.*;

public class DrivePath extends Command{
	TheoryCurve curve;
	int counter;
	double distance;
	double timeOut;
	double speed;
	
	public DrivePath(String startPoint, String controlPoint1, String controlPoint2, String endPoint, double timeOut, double speed)
    {
		curve = new TheoryCurve(startPoint, controlPoint1, controlPoint2, endPoint);
		distance = curve.findDistance();
		this.timeOut = timeOut;
		this.speed = speed;
		requires(Robot.drive);
    }
    
    protected void initialize()
    {
    	counter = 0;
    	setTimeout(timeOut);
        Robot.drive.resetEncoders();
    }
    
    protected void execute()
    {
    	if(Robot.drive.getAverageDistance() > curve.findHypotenuse(counter) && counter < 19)
    		counter++;

    	Robot.drive.driveStraight(distance, speed, curve.findAngle(counter), 1);
    }
    
    protected boolean isFinished()
    {
        return Robot.drive.getAverageDistance() == distance || 
        		isTimedOut();
    }
    
    protected void end()
    {
    	Robot.drive.runLeftDrive(0);
        Robot.drive.runRightDrive(0);
    }
    
    protected void interrupted()
    {
        
    }
}
