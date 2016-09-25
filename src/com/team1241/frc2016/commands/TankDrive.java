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
	
	private static final double DELTA_LIMIT = 1;
	private static final double RAMP_UP_CONSTANT = 0;
	private static final double RAMP_DOWN_CONSTANT = 0;
	double deltaL = 0;
	double deltaR = 0;
	
	double prevInputL = 0;
	double inputL = 0;
	
	double prevInputR = 0;
	double inputR = 0;

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
		inputL = -Robot.oi.getDriveLeftY();
		inputR = Robot.oi.getDriveRightY();
		
		deltaL = inputL - prevInputL;
		deltaR = inputR - prevInputR;
		
		if(deltaL >= DELTA_LIMIT)
			inputL += RAMP_UP_CONSTANT;
		else if(deltaL <= -DELTA_LIMIT)
			inputL -= RAMP_DOWN_CONSTANT;
		
		if(deltaR >= DELTA_LIMIT)
			inputR += RAMP_UP_CONSTANT;
		else if(deltaR <= -DELTA_LIMIT)
			inputR -= RAMP_DOWN_CONSTANT;
		
		if(Robot.oi.getDriveRightBumper()) {
			Robot.drive.runLeftDrive(inputL*0.5);
			Robot.drive.runRightDrive(inputR*0.5);
		}
		else {
			Robot.drive.runLeftDrive(inputL);
			Robot.drive.runRightDrive(inputR);
		}
		
		prevInputL = inputL;
		prevInputR = inputR;
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {   
	}
}