package com.team1241.frc2016.subsystems;

import com.team1241.frc2016.*;
import com.team1241.frc2016.pid.PIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * Intake Class
 * 
 * This class controls the robots Intake. Allows the the intake motor to be run
 * with buttons, and the intake arms with buttons and a joystick
 * 
 * @author Zubair Waheed
 * @author Taabish Jeshani
 * @author Bryan Kristiono
 * @since 2016-02-02
 */
public class Intake extends Subsystem {

	/** Talons to controls all intake motors. */
	private CANTalon intake;
	private CANTalon rightArm;
	private CANTalon leftArm;

	/** PID for arm control. */
	public PIDController armPID;

	/** Potentiometer for arm control. */
	private AnalogInput pot;

	/**
	 * Instantiates a new intake subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Intake() {

		// Initialize Talons
		intake = new CANTalon(ElectricalConstants.INTAKE_MOTOR);
		leftArm = new CANTalon(ElectricalConstants.LEFT_ARM_MOTOR);
		rightArm = new CANTalon(ElectricalConstants.RIGHT_ARM_MOTOR);

		// InitializePID used for arm control
		armPID = new PIDController(NumberConstants.pArm, NumberConstants.iArm, NumberConstants.dArm);

		// Initialize Potentiometer
		pot = new AnalogInput(ElectricalConstants.ARM_POTENTIOMETER);
	}

	public void initDefaultCommand() {
	}

	/**
	 * @return Returns the potentiometer value adjusted for a 3 turn pot.
	 */
	public double getPotValue() {
		return pot.getVoltage() / 5.0 * 1080.0;
	}

	/**
	 * Runs the intake at given value
	 *
	 * @param Power
	 *            value that is sent to the intake motor (-1.0 - 1.0)
	 */
	public void runIntake(double val) {
		intake.set(val);
	}

	/**
	 * Moves the intake arms motors at a given value
	 *
	 * @param Power
	 *            value that is sent to the arm motors (-1.0 - 1.0)
	 */
	public void runArms(double val) {
		leftArm.set(-val);
		rightArm.set(val);
	}

	/**
	 * Sets the position of the intake arms using a PID controller
	 *
	 * @param angle
	 *            The angle to set the arms in (NOTE PHYSICAL LIMITS)
	 * @param speed
	 *            The speed the arms should run at (0.0 - 1.0)
	 */
	public void setArmPosition(double angle, double speed) {
		double output = armPID.calcPID(angle, getPotValue(), 5);
		runArms(-output * speed);
	}
}