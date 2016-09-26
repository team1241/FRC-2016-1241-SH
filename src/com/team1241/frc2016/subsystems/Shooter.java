package com.team1241.frc2016.subsystems;

import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ShootCommand;
import com.team1241.frc2016.pid.PIDController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *The Subsystem that is used for firing the balls from a flywheel. It uses the 2 motors on the turret head,
 *the vertical indexing piston and reads an encoder for turret position feedback.
 *
 *@author Shaqeeb Momen
 */
public class Shooter extends Subsystem {

	/** The Shooter Talons. */
	private CANTalon rightShooter;
	private CANTalon leftShooter;

	/** The Turret Talon */
	private CANTalon turret;

	/** DoubleSolenoid for popper */
	private DoubleSolenoid popUp;

	/** DoubleSolenoid for the hood */
	private DoubleSolenoid hood;

	/** The shooter state. Used to check if shooter is on, and to turn it off */
	private boolean shooterState;

	/** The shooter PID. */
	public PIDController shooterPID;

	/** The turret PID. */
	public PIDController turretPID;

	/** The camera PID. */
	public PIDController cameraPID;

	/** The turret encoder, used to find turret angle */
	private Encoder turretEncoder;

	/** Optical Sensor, used for finding flywheel rpm */
	private Counter optical;

	/** Used to receive array of coordinates from camera */
	private double[] targetNum = new double[8];

	/**
	 * Instantiates a new shooter subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Shooter() {
		// Initialize Talons
		rightShooter = new CANTalon(ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		leftShooter = new CANTalon(ElectricalConstants.LEFT_SHOOTER_MOTOR);

		// Initialize turret, encoder, and the encoder properties
		turret = new CANTalon(ElectricalConstants.TURRET_MOTOR);
		turretEncoder = new Encoder(ElectricalConstants.TURRET_ENCODER_A, ElectricalConstants.TURRET_ENCODER_B,
				ElectricalConstants.turretEncoderReverse, Encoder.EncodingType.k4X);
		turretEncoder.setDistancePerPulse(ElectricalConstants.turretEncoderDegPerTick);

		// Sets up optical sensor to be used to check rpm
		optical = new Counter();
		optical.setUpSource(ElectricalConstants.SHOOTER_OPTICS);
		optical.setUpDownCounterMode();
		optical.setDistancePerPulse(1);

		// Initialize Pistons
		popUp = new DoubleSolenoid(ElectricalConstants.PCM, ElectricalConstants.POPPER_SHOOT_SOLENOID_A,
				ElectricalConstants.POPPER_SHOOT_SOLENOID_B);
		hood = new DoubleSolenoid(ElectricalConstants.PCM, ElectricalConstants.SHOOTER_HOOD_SOLENOID_A,
				ElectricalConstants.SHOOTER_HOOD_SOLENOID_B);

		// Initialize PIDs
		shooterPID = new PIDController(NumberConstants.pShooterBadder, NumberConstants.iShooterBadder,
				NumberConstants.dShooterBadder);

		turretPID = new PIDController(NumberConstants.pTurret, NumberConstants.iTurret, NumberConstants.dTurret);

		cameraPID = new PIDController(NumberConstants.pCamera, NumberConstants.iCamera, NumberConstants.dCamera);

		// Shooter is not running
		shooterState = false;

		// Uses NetworkTables to receive coordinates from camera
		NetworkTable server = NetworkTable.getTable("SmartDashboard");
		try {
			targetNum = server.getNumberArray("MEQ_COORDINATES");
		} catch (Exception ex) {
			System.out.println("Unable to get coordinates");
		}
	}

	/**
	 * Sets the shooter state.
	 *
	 * @param state
	 *            the new shooter state
	 */
	public void setShooterState(boolean state) {
		shooterState = state;
	}

	/**
	 * Gets the shooter state.
	 *
	 * @return the shooter state
	 */
	public boolean getShooterState() {
		return shooterState;
	}

	/**
	 * Runs ShootCommand as a default command
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ShootCommand());
	}

	// PNEUMATIC METHODS

	/**
	 * Extend hood piston.
	 */
	public void extendHood() {
		hood.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Retract hood piston.
	 */
	public void retractHood() {
		hood.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Retract popper piston.
	 */
	public void retractPop() {
		popUp.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Extend popper piston.
	 */
	public void extendPop() {
		popUp.set(DoubleSolenoid.Value.kForward);
	}

	// MOTOR METHODS

	/**
	 * Used to set speed of shooter motors
	 *
	 * @param shotVal
	 *            Power sent to the shooter motors (-1.0 to 1.0)
	 */

	public void setSpeed(double shotVal) {
		rightShooter.set(-shotVal);
		leftShooter.set(-shotVal);
	}

	/**
	 * Used to set speed of turret motor
	 *
	 * @param pwr
	 *            Power sent to the turret motor (-1.0 to 1.0)
	 */
	public void turnTurret(double pwr) {
		turret.set(pwr);
	}

	/**
	 * Turn turret to angle.
	 *
	 * @param angle
	 *            angle is in degrees - Restriction: -180 <= x <= 90
	 * @param pwr
	 *            Power is 0.0 to 1.0
	 */
	public void turnTurretToAngle(double angle, double pwr) {
		double output = turretPID.calcPID(angle, getTurretAngle(), 0.5);
		turret.set(pwr * output);
	}

	/**
	 * Using the camera to live track (Not used during match)
	 *
	 * @param angle
	 *            the angle
	 */
	public void liveTrack(double angle) {
		// System.out.println(Math.abs(angle-getTurretAngle()));
		if (Math.abs(angle - getTurretAngle()) < 0.5) {
			turret.set(0);
		} else if (angle > getTurretAngle()) {
			if (Math.abs(angle - getTurretAngle()) < 32)
				turret.set(0.2);
			if (Math.abs(angle - getTurretAngle()) < 5)
				turret.set(0.1);
			if (Math.abs(angle - getTurretAngle()) < 2.5)
				turret.set(0.05);
		} else if (angle < getTurretAngle()) {
			if (Math.abs(angle - getTurretAngle()) < 32)
				turret.set(-0.2);
			if (Math.abs(angle - getTurretAngle()) < 5)
				turret.set(-0.1);
			if (Math.abs(angle - getTurretAngle()) < 2.5)
				turret.set(-0.05);
		} else {
			turret.set(0);
		}
	}

	/**
	 * Separate method used turning the turret using the camera
	 *
	 * @param angle
	 *            the angle
	 * @param pwr
	 *            Power is 0.0 to 1.0
	 */
	public void turnTurretCamera(double angle, double pwr) {
		double output = cameraPID.calcPIDVelocity(angle, getTurretAngle(), 0.5, 0.8);

		if (Math.abs(angle - getTurretAngle()) < 0.5) {
			turret.set(0);
		} else if (Math.abs(output) > 0.1) {
			turret.set(pwr * output);
		} else if (output > -0.1 && output < 0) {
			turret.set(-0.1);
		} else if (output < 0.1 && output > 0) {
			turret.set(0.1);
		}
	}

	/**
	 * Converts the pixel offset from the center of the image to degrees, which
	 * is then used for turning the turret
	 *
	 * @param pixel
	 *            The x coordinate pixel from the image. Ranging from 0 to 640
	 *            on a 480x640 image
	 * @return the double
	 */
	public double pixelToDegree(double pixel) {
		return Math.toDegrees(Math.atan(((pixel - 320) * Math.tan(Math.toRadians(31.81))) / 320));
	}

	// TURRET ENCODER METHODS

	/**
	 * Resets the turret encoder
	 */
	public void reset() {
		turretEncoder.reset();
	}

	/**
	 * @return Returns raw value from turret encoder
	 */
	public double getTurretRaw() {
		return turretEncoder.getRaw();
	}

	/**
	 * Update coordinates from image.
	 *
	 * @return Returns true if coordinates received successfully.
	 */
	public boolean updateCoordinates() {
		NetworkTable server = NetworkTable.getTable("SmartDashboard");
		try {
			targetNum = server.getNumberArray("MEQ_COORDINATES");
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * @return Returns an array filled with target coordinates
	 */
	public double[] getCoordinates() {
		return targetNum;
	}

	/**
	 * @return Returns the calculated x value for the center of the target
	 */
	public double getXCoordinates() {
		if (updateCoordinates()) {
			if (targetNum.length == 8)
				return (targetNum[0] + targetNum[2] + targetNum[4] + targetNum[6]) / 4;
		}
		return -1;
	}

	/**
	 * @return Returns the target width in pixels
	 */
	public double targetWidthPixels() {
		if (updateCoordinates()) {
			if (targetNum.length == 8)
				return ((targetNum[0] - targetNum[2]) + (targetNum[6] - targetNum[4])) / 2;
		}
		return -1;
	}

	/**
	 * @return Returns the distance to the target
	 */
	public double getDistanceToTarget() {
		return 20 * 640 / (2 * targetWidthPixels() * Math.tan(Math.toRadians(31.81)));
	}

	// OPTICAL SENSOR METHODS

	/**
	 * @return Returns what the optical sensor is currently sensing
	 */
	public double getOptic() {
		return optical.get();
	}

	/**
	 * @return Returns the rpm
	 */
	public double getRPM() {
		return optical.getRate() * 60;
	}

	// SHOOTER PID

	/**
	 * Method used to set rpm for the shooter, uses PID in combination with
	 * feedforward to reach speed. Inputed rpm is sent into a linear function to
	 * calculate needed feedforward value.
	 * 
	 * @param rpm
	 *            rpm for the shooter
	 */
	public void setRPM(double rpm) {
		double output = shooterPID.calcPIDVelocity(rpm, getRPM(), 50, 0.6);
		setSpeed(output + rpm * NumberConstants.kForward + NumberConstants.bForward);
	}

	/**
	 * @return Returns the turret angle in degrees.
	 */
	public double getTurretAngle() {
		return turretEncoder.getRaw() / ElectricalConstants.turretEncoderDegPerTick;
	}
}
