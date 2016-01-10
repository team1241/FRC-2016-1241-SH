package com.team1241.frc2016;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//**************************************************************************
	//*****************************DRIVE MOTORS*********************************
	//**************************************************************************        

	public static final int RIGHT_DRIVE_FRONT                               = 8; 
	public static final int RIGHT_DRIVE_BACK                                = 9;

	public static final int LEFT_DRIVE_FRONT                                = 0;
	public static final int LEFT_DRIVE_BACK                                 = 1;

	//**************************************************************************
	//***************************ELEVATOR MOTORS *******************************
	//**************************************************************************        

	public static final int ELEVATOR                                        = 3; 

	//**************************************************************************
	//**************************** INTAKE MOTORS *******************************
	//**************************************************************************        

	public static final int RIGHT_INTAKE_MOTOR 								= 7;
	public static final int LEFT_INTAKE_MOTOR 						        = 2;

	//**************************************************************************
	//************************** DRIVE ENCODERS ********************************
	//**************************************************************************

	public static final int LEFT_DRIVE_ENCODER_A                            = 2;
	public static final int LEFT_DRIVE_ENCODER_B                            = 3;

	public static final int RIGHT_DRIVE_ENCODER_A                           = 1;
	public static final int RIGHT_DRIVE_ENCODER_B                           = 0;

	//**************************************************************************
	//************************* ELEVATOR ENCODERS ******************************
	//**************************************************************************

	public static final int ELEV_ENCODER_A                		            = 4;
	public static final int ELEV_ENCODER_B                		            = 5;

	//**************************************************************************
	//*************************** Digital Sensors ******************************
	//**************************************************************************

	public static final int INTAKE_BUMPER 								    = 9;

	//***************************************************************************
	//*************************** Pneumatics ************************************
	//***************************************************************************

	public static final int LEFT_INTAKE_SOLENOID_A							= 4;
	public static final int LEFT_INTAKE_SOLENOID_B 							= 3;

	public static final int RIGHT_INTAKE_SOLENOID_A 						= 7;
	public static final int RIGHT_INTAKE_SOLENOID_B 						= 0;

	public static final int CONTAINER_ELEVATOR_HOLDER_SOLENOID_A 			= 2; 
	public static final int CONTAINER_ELEVATOR_HOLDER_SOLENOID_B			= 5;

	public static final int CONTAINER_TOP_HOLDER_SOLENOID_A 				= 1; 
	public static final int CONTAINER_TOP_HOLDER_SOLENOID_B					= 6; 

	//**************************************************************************
	//********************* DRIVE ENCODER CONSTANTS ****************************
	//**************************************************************************
	public static final int driveWheelRadius = 3;//wheel radius in inches
	public static final int drivePulsePerRotation = 128; //encoder pulse per rotation
	public static final double driveGearRatio = 1/1; //ratio between wheel and encoder
	public static final double driveEncoderPulsePerRot = drivePulsePerRotation*driveGearRatio; //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick =(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final boolean rightDriveTrainEncoderReverse = false; 
	public static final boolean leftDriveTrainEncoderReverse = false; 

	//**************************************************************************
	//******************** ELEVATOR ENCODER CONSTANTS **************************
	//**************************************************************************
	public static final int elevPulleyRadius = 1;
	public static final int elevPulsePerRotation = 128; //encoder pulse per rotation
	public static final double elevGearRatio = 1/1; //ratio between pulley and encoder
	public static final double elevEncoderPulsePerRot = elevPulsePerRotation*elevGearRatio; //pulse per rotation * gear ratio
	public static final double elevEncoderDistPerTick =(Math.PI*2*elevPulleyRadius)/elevEncoderPulsePerRot;
	public static final boolean rightElevEncoderReverse = false; 
	public static final boolean leftElevEncoderReverse = false;
}
