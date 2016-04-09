package com.team1241.frc2016;

/**
 * This class is used to save constant values that will be changed every once in awhile. This can include PID tuned
 * values or autonomous distances, etc...
 *
 * @author Mahrus Kazi
 * @author Bryan Kristiono
 * @since 2015-09-13
 */

//Practice
public class NumberConstants {
	//**************************************************************************
    //*************************** PID VALUES (DRIVE) ***************************
    //**************************************************************************
	
	//Practice
	public static final double pDrive 									 = 0.03;
	public static final double iDrive 									 = 0.00;
	public static final double dDrive 									 = 0.008;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	//Practice
	public static final double pGyro 									 = 0.0125;
	public static final double iGyro 									 = 0.00;
	public static final double dGyro 									 = 0.00;
	
	//**************************************************************************
    //*************************** PID VALUES (TURRET) **************************
    //**************************************************************************
	
	//Practice
	public static final double pTurret 									 = 0.03;
	public static final double iTurret 									 = 0.00;
	public static final double dTurret 									 = 0.01;

	//**************************************************************************
    //*************************** PID VALUES (CAMERA) **************************
    //**************************************************************************
	
	//Practice
	public static final double pCamera 									 = 0.07;
	public static final double iCamera 									 = 0.007;
	public static final double dCamera 									 = 0.00;
	
	public static final double cameraOffset								= -0.6;
		
	//**************************************************************************
    //************************** PID VALUES (SHOOTER) **************************
    //**************************************************************************
	
	//Practice
	public static final double pShooterBadder							 = 0.00023;
	public static final double iShooterBadder							 = 0.00000;
	public static final double dShooterBadder							 = 0.00;
	
	//d 1.0E-4
	//P 9.0E-4
	
//	public static final double pShooterSpy								 = 0.001;
//	public static final double iShooterSpy								 = 0.00000625;
//	public static final double dShooterSpy								 = 0.00;
	
	//1.0/5582.0
	//0.0
	public static final double kForward									 = 0.000149539877;
	public static final double bForward									 = 0.090506134969;
	
//	public static final double kForward									 = 0.67;

	//**************************************************************************
    //**************************** PID VALUES (ARM) ****************************
    //**************************************************************************
	
	//Practice
	public static final double pArm 									 = 0.03;
	public static final double iArm 									 = 0.00;
	public static final double dArm 									 = 0.00;
		
	//**************************************************************************
    //**************************OUTPUT VALUES (Shooter)*************************
    //**************************************************************************
	
	//Practice
	//Arm all the way down
	public static final int downArmAngle								= 650;
	//Arm all the way up
	public static final int upArmAngle									= 930;	
	
	//**************************************************************************
	//***************************** SHOOTER NUMBERS ****************************
	//**************************************************************************
	
	//Practice
	public static final int outerShotRPM								= 4900;
	public static final int spyShotRPM									= 4120;
	public static final int badderShotRPM								= 4500;
	
	public static final int spyShotAngle								= -78;
	public static final int backAngle									= -180;
	
	public static final double waitForPop								= 0.75;
	public static final double waitForHolder							= 0.15;
	public static final double waitForHolderClose						= 0.50;	

	//**************************************************************************
	//***************************** INTAKE NUMBERS *****************************
	//**************************************************************************
	
	public static final double intakeDist								= 12.2;
	
}
