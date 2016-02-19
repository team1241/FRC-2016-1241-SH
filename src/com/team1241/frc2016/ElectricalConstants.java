package com.team1241.frc2016;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class ElectricalConstants {
	//***************************************************************************
	//****************************** DRIVE MOTORS *******************************
	//***************************************************************************     
	
	public static final int LEFT_DRIVE_FRONT                                = 10;
	public static final int LEFT_DRIVE_BACK                                 = 11;
	
	public static final int RIGHT_DRIVE_FRONT                               = 2;
	public static final int RIGHT_DRIVE_BACK                                = 1;
	
	//***************************************************************************
	//****************************** SHOOTER MOTORS *****************************
	//***************************************************************************        
	
	public static final int LEFT_SHOOTER_MOTOR								= 5;
	public static final int RIGHT_SHOOTER_MOTOR								= 6;
	
	//***************************************************************************
	//****************************** TURRET MOTORS ******************************
	//***************************************************************************
	
	public static final int TURRET_MOTOR									= 7;
	
	//***************************************************************************
	//******************************* INTAKE MOTORS *****************************
	//***************************************************************************
	
	public static final int INTAKE_MOTOR 									= 6;
	
	//***************************************************************************
	//******************************** ARM MOTORS *******************************
	//***************************************************************************
	
	public static final int LEFT_ARM_MOTOR									= 8;
	public static final int RIGHT_ARM_MOTOR									= 4;
	
	//***************************************************************************
	//***************************** CONVEYOR MOTORS *****************************
	//***************************************************************************
	
	public static final int CONVEYOR_MOTOR									= 7;
	
	//***************************************************************************
	//****************************** DRIVE ENCODERS *****************************
	//***************************************************************************
	
	public static final int LEFT_DRIVE_ENCODER_A                            = 9;
	public static final int LEFT_DRIVE_ENCODER_B                            = 8;
	
	public static final int RIGHT_DRIVE_ENCODER_A                           = 1;
	public static final int RIGHT_DRIVE_ENCODER_B                           = 0;
	
	//***************************************************************************
	//***************************** TURRET ENCODERS *****************************
	//***************************************************************************

	public static final int TURRET_ENCODER_A								= 2;
	public static final int TURRET_ENCODER_B                		        = 3;

	//***************************************************************************
	//***************************** TURRET ENCODERS *****************************
	//***************************************************************************

	public static final int ULTRASONIC_TRIG									= 6;
	public static final int ULTRASONIC_ECHO									= 7;
	
	//***************************************************************************
	//***************************** OPTICAL SENSORS *****************************
	//***************************************************************************

	public static final int SHOOTER_OPTICS									= 4;
	public static final int POPPER_OPTICS									= 5;
	
	//***************************************************************************
	//****************************** ANALOG SENSORS *****************************
	//***************************************************************************
	
	public static final int ARM_POTENTIOMETER								= 0;
	
	//***************************************************************************
	//******************************** PNEUMATICS *******************************
	//***************************************************************************

	public static final int PCM												= 14;

	public static final int SHOOTER_HOOD_SOLENOID_A							= 3;
	public static final int SHOOTER_HOOD_SOLENOID_B							= 4;
	
	public static final int POPPER_SHOOT_SOLENOID_A							= 1;
	public static final int POPPER_SHOOT_SOLENOID_B							= 6;
	
	public static final int POPPER_HOLD_SOLENOID_A							= 2;
	public static final int POPPER_HOLD_SOLENOID_B							= 5;
	
//	public static final int POPPER_RELEASE_SOLENOID_A						= 1;
//	public static final int POPPER_RELEASE_SOLENOID_B						= 0;

	//***************************************************************************
	//************************* DRIVE ENCODER CONSTANTS *************************
	//***************************************************************************
	public static final int driveWheelRadius = 4;//wheel radius in inches
	public static final int drivePulsePerRotation = 128; //encoder pulse per rotation
	public static final double driveGearRatio = 1/1; //ratio between wheel and encoder
	public static final double driveEncoderPulsePerRot = drivePulsePerRotation*driveGearRatio; //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick =(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final boolean leftDriveTrainEncoderReverse = false;
	public static final boolean rightDriveTrainEncoderReverse = true;

	//**************************************************************************
	//************************ TURRET ENCODER CONSTANTS ************************
	//**************************************************************************
	public static final double turretPulsePerRotation = 1024.0; //encoder pulse per rotation
	public static final double turretGearRatio = 216.0/14.0; //ratio between pulley and encoder
	public static final double turretEncoderPulsePerRot = turretPulsePerRotation*turretGearRatio; //pulse per rotation * gear ratio
	public static final double turretEncoderDegPerTick = turretEncoderPulsePerRot/90.0;
	public static final boolean turretEncoderReverse = true;
}
