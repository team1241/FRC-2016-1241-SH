package com.team1241.frc2016.subsystems;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.TankDrive;
import com.team1241.frc2016.pid.PIDController;
import com.team1241.frc2016.utilities.Nav6;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// TODO: Auto-generated Javadoc
/**
 * The subsystem that is used for the drive train of the robot. It runs the 4
 * motors in the drive train as well as read the encoder values from the motors.
 * 
 * @author Bryan Kristiono
 * @since 2016-10-10
 */
public class Drivetrain extends Subsystem {

	/** Drive Talons */
	private CANTalon leftDriveFront;
	private CANTalon leftDriveBack;
	private CANTalon rightDriveFront;
	private CANTalon rightDriveBack;

	/** Encoders on the drive */
	private Encoder leftDriveEncoder;
	private Encoder rightDriveEncoder;

	/** Gyro on the drive */
	private SerialPort serialPort;
	private Nav6 gyro;

	/** The drive PID controller. */
	public PIDController drivePID;

	/** The gyro PID conteroller. */
	public PIDController gyroPID;

	/**
	 * Instantiates a new drivetrain subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Drivetrain() {
		try {
			serialPort = new SerialPort(57600, SerialPort.Port.kOnboard);

			// You can add a second parameter to modify the
			// update rate (in hz) from 4 to 100. The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			byte update_rate_hz = 50;
			gyro = new Nav6(serialPort, update_rate_hz);

			if (!gyro.isCalibrating()) {
				Timer.delay(0.3);
				gyro.zeroYaw();
			}
		} catch (Exception e) {
			gyro = null;
		}

		// Initialize Talons
		leftDriveFront = new CANTalon(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftDriveBack = new CANTalon(ElectricalConstants.LEFT_DRIVE_BACK);

		rightDriveFront = new CANTalon(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightDriveBack = new CANTalon(ElectricalConstants.RIGHT_DRIVE_BACK);

		// Initialize Encoders
		leftDriveEncoder = new Encoder(ElectricalConstants.LEFT_DRIVE_ENCODER_A,
				ElectricalConstants.LEFT_DRIVE_ENCODER_B, ElectricalConstants.leftDriveTrainEncoderReverse,
				Encoder.EncodingType.k4X);

		leftDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);

		rightDriveEncoder = new Encoder(ElectricalConstants.RIGHT_DRIVE_ENCODER_A,
				ElectricalConstants.RIGHT_DRIVE_ENCODER_B, ElectricalConstants.rightDriveTrainEncoderReverse,
				Encoder.EncodingType.k4X);

		rightDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);

		// Initialize PID controllers
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
	}

	/**
	 * Sets the command TankDrive as the default command for this subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	/**
	 * Sends supplied power value to the left drive motors.
	 *
	 * @param power
	 *            Power value sent to motors (-1.0 to 1.0)
	 */
	public void runLeftDrive(double power) {
		leftDriveFront.set(power);
		leftDriveBack.set(power);
	}

	/**
	 * Sends supplied power value to the right drive motors.
	 *
	 * @param poewr
	 *            Power value sent to motors (-1.0 to 1.0)
	 */
	public void runRightDrive(double power) {
		rightDriveFront.set(power);
		rightDriveBack.set(power);
	}

	/**
	 * Gets the average distance between both encoders.
	 *
	 * @return Returns the average distance
	 */
	public double getAverageDistance() {
		return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	}

	/**
	 * Using both PID controllers (drive & gyro), the drivetrain will move to
	 * target at given speed and angle
	 *
	 * @param setPoint
	 *            The set point in inches
	 * @param speed
	 *            The speed (0.0 to 1.0)
	 * @param setAngle
	 *            The set angle in degrees
	 * @param epsilon
	 *            How close robot should be to target to consider reached
	 */
	public void driveStraight(double setPoint, double speed, double setAngle, double epsilon) {
		double output = drivePID.calcPIDDrive(setPoint, getAverageDistance(), epsilon);
		double angle = gyroPID.calcPID(setAngle, getYaw(), epsilon);

		runLeftDrive((output + angle) * speed);
		runRightDrive((-output + angle) * speed);
	}

	/**
	 * Used to move robot without a drive PID controller at a given speed, while
	 * at the angle given.
	 *
	 * @param setAngle
	 *            The set angle in degrees
	 * @param speed
	 *            The speed (-1.0 - 1.0)
	 */
	public void driveAngle(double setAngle, double speed) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), 1);

		runLeftDrive(speed + angle);
		runRightDrive(-speed + angle);
	}

	/**
	 * Using a PID controller, turns the robot to given angle with the given
	 * speed.
	 *
	 * @param setAngle
	 *            The set angle in degrees
	 * @param speed
	 *            The speed (0.0 - 1.0)
	 * @param epsilon
	 *            How close robot should be to target to consider reached
	 */
	public void turnDrive(double setAngle, double speed, double epsilon) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), epsilon);

		runLeftDrive(angle * speed);
		runRightDrive(angle * speed);
	}

	/**
	 * Resets the encoder AND gyro to zero.
	 */
	public void reset() {
		resetEncoders();
		resetGyro();
	}

	/**
	 * This function returns the distance traveled from the left encoder in
	 * inches.
	 *
	 * @return Returns distance traveled by encoder in inches
	 */
	public double getLeftEncoderDist() {
		return leftDriveEncoder.getDistance();
	}

	/**
	 * This function returns the distance traveled from the right encoder in
	 * inches.
	 *
	 * @return Returns distance traveled by encoder in inches
	 */
	public double getRightEncoderDist() {
		return rightDriveEncoder.getDistance();
	}

	/**
	 * This function returns the raw value from the left encoder.
	 *
	 * @return Returns raw value from encoder
	 */
	public double getLeftEncoderRaw() {
		return leftDriveEncoder.getRaw();
	}

	/**
	 * This function returns the raw value from the right encoder.
	 *
	 * @return Returns raw value from encoder
	 */
	public double getRightEncoderRaw() {
		return rightDriveEncoder.getRaw();
	}

	/**
	 * This function returns the rate the left encoder is moving at in
	 * inches/sec.
	 *
	 * @return Returns rate of encoder in inches/sec
	 */
	public double getLeftEncoderRate() {
		return leftDriveEncoder.getRate();
	}

	/**
	 * This function returns the rate the right encoder is moving at in
	 * inches/sec.
	 *
	 * @return Returns rate of encoder in inches/sec
	 */
	public double getRightEncoderRate() {
		return rightDriveEncoder.getRate();
	}

	/**
	 * Resets both left and right encoders.
	 */
	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}

	// GYRO FUNCTIONS

	/**
	 * This function is used to check if the gyro is connected
	 * 
	 * @return Returns true or false depending on the state of the gyro
	 */
	public boolean gyroConnected() {
		return gyro.isConnected();
	}

	/**
	 * This function is used to check if the gyro is calibrating.
	 *
	 * @return Returns true or false depending on the state of the gyro
	 */
	public boolean gyroCalibrating() {
		return gyro.isCalibrating();
	}

	/**
	 * This function returns the YAW reading from the gyro.
	 *
	 * @return Returns YAW
	 */
	public double getYaw() {
		return gyro.getYaw() / 88.5 * 90;
	}

	/**
	 * This function returns the PITCH reading from the gyro.
	 *
	 * @return Returns PITCH
	 */
	public double getPitch() {
		return gyro.getPitch();
	}

	/**
	 * This function returns the ROLL reading from the gyro.
	 *
	 * @return Returns ROLL
	 */
	public double getRoll() {
		return gyro.getRoll();
	}

	/**
	 * This function returns the heading from the gyro.
	 *
	 * @return Returns compass heading
	 */
	public double getCompassHeading() {
		return gyro.getCompassHeading();
	}

	/**
	 * Resets the gyro back to zero.
	 */
	public void resetGyro() {
		gyro.zeroYaw();
	}
}