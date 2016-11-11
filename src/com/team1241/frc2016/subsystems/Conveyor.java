package com.team1241.frc2016.subsystems;

import com.team1241.frc2016.ElectricalConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem is used to control the conveyor which intakes and holds the
 * boulder. A motor is used to intake the boulder into the popper. Two pistons
 * are used to hold the boulder in position for the popper. They extend and
 * retract to either outtake the ball or hold the ball.
 *
 * @author Eric Huang
 * @author William San
 * @since 2016-01-31
 */

public class Conveyor extends Subsystem {

	/** The conveyor motor. */
	private CANTalon conveyorMotor;

	/** The hold ball. */
	private DoubleSolenoid holdBall;

	/** boolean used to check if popper contains ball. */
	private boolean contains = false;

	/** boolean used to check state */
	private boolean holdState = false;

	/** Optical sensor used to check if popper contains ball */
	private DigitalInput optical;

	/**
	 * Instantiates a new Conveyor subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Conveyor() {

		// Initialize Talons
		conveyorMotor = new CANTalon(ElectricalConstants.CONVEYOR_MOTOR);

		// Initialize Piston
		holdBall = new DoubleSolenoid(ElectricalConstants.PCM, ElectricalConstants.POPPER_HOLD_SOLENOID_A,
				ElectricalConstants.POPPER_HOLD_SOLENOID_B);

		// Initialize optical sensor
		optical = new DigitalInput(ElectricalConstants.POPPER_OPTICS);
	}

	public void initDefaultCommand() {
	}

	/**
	 * This method runs the intake motor at a certain power.
	 *
	 * @param val
	 *            Power value sent to the motors (-1.0 - 1.0)
	 */
	public void runMotor(double val) {
		conveyorMotor.set(val);
	}

	/**
	 * This method extends the "Halo" piston, which holds the boulder for the
	 * shooter.
	 */
	public void extendHolder() {
		holdBall.set(DoubleSolenoid.Value.kForward);
		holdState = true;
	}

	/**
	 * This method retracts the "Halo" piston, which holds the boulder for the
	 * shooter.
	 */
	public void retractHolder() {
		holdBall.set(DoubleSolenoid.Value.kReverse);
		holdState = false;
	}

	/**
	 * Sets the state of the holder
	 *
	 * @param state
	 *            the new state
	 */
	public void setContains(boolean state) {
		this.contains = state;
	}

	/**
	 * @return Retains the state of the holder
	 */
	public boolean getContains() {
		return contains;
	}

	/**
	 * @return the hold state
	 */
	public boolean getHoldState() {
		return holdState;
	}

	/**
	 * @return Returns true if optical sensor detects a boulder, otherwise
	 *         false.
	 */
	public boolean getOptic() {
		return optical.get();
	}
}
