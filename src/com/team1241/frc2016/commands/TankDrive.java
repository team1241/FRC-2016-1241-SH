package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	This class is used to set a default command for the Drivetrain subsystem. This command allows the driver to
 *	control the robot using tank drive. 
 *
 * @author Bryan Kristiono
 * @since 2016-01-10
 */
public class TankDrive extends Command {

	public TankDrive() {
		requires(Robot.drive);
	}

	protected void initialize() {
	}

	/**
	 * This method will run as long as isFinished() returns true
	 * In this method values from the joystick are sent to the corresponding drives to make the robot move.
	 */
	protected void execute() {
		if(Robot.oi.getDriveRightTrigger()) {
			Robot.drive.runLeftDrive(-Robot.oi.getDriveLeftY()*0.6);
			Robot.drive.runRightDrive(Robot.oi.getDriveRightY()*0.6);
		}
		else {
			Robot.drive.runLeftDrive(-Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(Robot.oi.getDriveRightY());
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {   
	}
}