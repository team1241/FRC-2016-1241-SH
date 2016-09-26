package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

// TODO: Auto-generated Javadoc
/**
 *	This class is used to set a default command for the Drivetrain subsystem. This command allows the driver to
 *	control the robot using tank drive. 
 *
 * @author Bryan Kristiono
 * @since 2016-01-10
 */
public class TankDrive extends Command {
	
	/** The Constant DELTA_LIMIT. */
	private static final double DELTA_LIMIT = 0.75;
	
	/** The Constant RAMP_UP_CONSTANT. */
	private static final double RAMP_UP_CONSTANT = 0.05;
	
	/** The Constant RAMP_DOWN_CONSTANT. */
	private static final double RAMP_DOWN_CONSTANT = 0.05;
	
	/** Variables used for joystick ramping*/
	double deltaL = 0;
	double deltaR = 0;
	double prevInputL = 0;
	double inputL = 0;
	double prevInputR = 0;
	double inputR = 0;

	/**
	 * Instantiates a new tank drive Command.
	 */
	public TankDrive() {
		// Makes sure that no other commands use the drivetrain at the same time
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
		
		// Runs drive at jotstick input, if right bumper is pressed drive speed is halved
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
	
	// isFinished() always returns false, as we don't want TankDrive to stop by default
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {   
	}
}