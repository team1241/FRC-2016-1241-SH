package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	This class is used to set a default command for the Drivetrain subsystem. This command allows the driver to
 *	control the robot using tank drive. 
 *
 * @author Mahrus Kazi
 * @since 2015-09-13
 */
public class TankDrive extends Command {
	private boolean driving = true;

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
		if (!Robot.drive.getBumperSwitch() && Robot.drive.auton==true) {
			Robot.drive.runLeftDrive(0.3);
			Robot.drive.runRightDrive(-0.3);
		}
		else {
			Robot.drive.auton = false;
			Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY()*0.3);
			Robot.drive.runRightDrive(Robot.oi.getDriveRightY()*0.3);
		}
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