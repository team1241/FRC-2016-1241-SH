package com.team1241.frc2016.commands;

import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.auto.*;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Default command for the conveyor subsystem. Controls the holder or "halo"
 * along with the conveyor motor.
 *
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class ConveyorCommand extends Command {

	/** Used to toggle between states */
	ToggleBoolean toggle;

	/**
	 * boolean used toggle between auto holding and manual (ie. pressed with a
	 * button)
	 */
	private boolean auto = true;

	/** Timer used to make sure, ball is settled */
	Timer timer;

	/** boolean used to check if Timer has started */
	boolean started = false;

	/**
	 * Instantiates a new conveyor command.
	 */
	public ConveyorCommand() {
		toggle = new ToggleBoolean();
		timer = new Timer();
		requires(Robot.conveyor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// If ball is detected, start timer
		if (!Robot.conveyor.getOptic() && started == false) {
			timer.start();
			started = true;
		}

		// If ball is detected for more than half a second AND auto holder is on
		// AND holder does not previously hold a ball, close the holder.
		if (!Robot.conveyor.getOptic() && auto && !Robot.conveyor.getContains() && timer.get() > 0.5) {
			timer.reset();
			timer.stop();
			Robot.conveyor.setContains(true);
			new AutoHolder().start();
		}

		// If conveyor has a ball, reset process to false
		if (Robot.conveyor.getContains()) {
			started = false;
		}

		// If sensor does not detect a ball and has started, reset process (Used
		// to remove any false detection)
		if (Robot.conveyor.getOptic() && started == true) {
			timer.stop();
			started = false;
		}

		// If toolop B button is pressed, toggle holder and turn off auto
		// holder. Otherwise turn on auto holder
		if (Robot.oi.getToolBButton()) {
			toggle.set(true);
			auto = false;
		} else {
			auto = true;
		}

		// If auto holder is off AND toggle has been set to true then close
		// holder, else open holder
		if (!auto && toggle.get()) {
			Robot.conveyor.extendHolder();
			Robot.conveyor.setContains(true);
		} else if (!auto && !toggle.get()) {
			Robot.conveyor.retractHolder();
			Robot.conveyor.setContains(false);
		}

		// Used to control conveyor motor with gamepad
		if (Robot.oi.getToolAButton()) {
			Robot.conveyor.runMotor(0.90);
		} else if (Robot.oi.getToolXButton()) {
			Robot.conveyor.runMotor(-0.90);
		} else {
			Robot.conveyor.runMotor(0);
		}
	}

	// Set to false to make sure it always runs as a default command.
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
