package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	This class is used to set a default command for the arcadeDrivetrain subsystem. This command allows the arcadeDriver to
 *	control the robot using tank arcadeDrive. 
 *
 * @author Mahrus Kazi
 * @since 2015-09-13
 */
public class ArcadeDrive extends Command {
    
    public ArcadeDrive()
    {
    	requires(Robot.drive);
    }
    
    protected void initialize()
    {
    }
    
    /**
     * This method will run as long as isFinished() returns true
     * In this method values from the joystick are sent to the corresponding arcadeDrives to make the robot move.
     */
    protected void execute()
    {
    	Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY()-Robot.oi.getDriveRightX());
        Robot.drive.runRightDrive(Robot.oi.getDriveLeftY()+Robot.oi.getDriveRightX());
    }
    
    protected boolean isFinished()
    {
        return false;
    }
    
    protected void end()
    {
    }
    
    protected void interrupted()
    {   
    }
}