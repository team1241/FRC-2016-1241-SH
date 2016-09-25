package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.utilities.BezierCurve;
import com.team1241.frc2016.utilities.Point;

import edu.wpi.first.wpilibj.command.*;

public class DrivePath extends Command{
	BezierCurve curve;
	int counter;
	double distance;
	double timeOut;
	double speed;
	boolean reverse;
	
	public DrivePath(Point startPoint, Point controlPoint1, Point controlPoint2, Point endPoint, double timeOut, double speed)
    {
		this(startPoint, controlPoint1, controlPoint2, endPoint, timeOut, speed, false);
    }
	
	public DrivePath(Point startPoint, Point controlPoint1, Point controlPoint2, Point endPoint, double timeOut, double speed, boolean reverse)
    {
		curve = new BezierCurve(startPoint, controlPoint1, controlPoint2, endPoint);
		distance = curve.findDistance();
		this.timeOut = timeOut;
		this.speed = speed;
		this.reverse = reverse;
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
    	if(reverse){
    		if(-Robot.drive.getAverageDistance() > curve.findHypotenuse(counter) && counter <= curve.size())
        		counter++;

        	Robot.drive.driveStraight(-distance, speed, curve.findAngle(counter), 1);
    	}
    	else{
    		if(Robot.drive.getAverageDistance() > curve.findHypotenuse(counter) && counter < curve.size())
        		counter++;

        	Robot.drive.driveStraight(distance, speed, curve.findAngle(counter), 1);
    	}
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
