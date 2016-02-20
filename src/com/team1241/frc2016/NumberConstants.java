package com.team1241.frc2016;

/**
 * This class is used to save constant values that will be changed every once in awhile. This can include PID tuned
 * values or autonomous distances, etc...
 *
 * @author Mahrus Kazi
 * @since 2015-09-13
 */
public class NumberConstants {

	//**************************************************************************
    //*************************** PID VALUES (DRIVE) ***************************
    //**************************************************************************
	
	//Practice
//	public static final double pDrive 									 = 0.05;
//	public static final double iDrive 									 = 0.00;
//	public static final double dDrive 									 = 0.008;
	
	//Competition
	public static final double pDrive 									 = 0.01;
	public static final double iDrive 									 = 0.00;
	public static final double dDrive 									 = 0.008;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	//Practice
//	public static final double pGyro 									 = 0.05;
//	public static final double iGyro 									 =0.0001;
//	public static final double dGyro 									 = 0.00;
	
	//Competition
	public static final double pGyro 									 = 0.02;
	public static final double iGyro 									 = 0.00;
	public static final double dGyro 									 = 0.00;
	
	
	//**************************************************************************
    //*************************** PID VALUES (TURRET) **************************
    //**************************************************************************
	
	//Practice
	//0.05
//	public static final double pTurret 									 = 0.03;
//	public static final double iTurret 									 = 0.00;
//	public static final double dTurret 									 = 0.01;
	
	//Competition
	public static final double pTurret 									 = 0.025;
	public static final double iTurret 									 = 0.00;
	public static final double dTurret 									 = 0.005;
	
	//**************************************************************************
    //*************************** PID VALUES (CAMERA) **************************
    //**************************************************************************
	
	//Practice
	//0.05
//	public static final double pCamera 									 = 0.03;
//	public static final double iCamera 									 = 0.00;
//	public static final double dCamera 									 = 0.01;
	
	//Competition
	public static final double pCamera 									 = 0.025;
	public static final double iCamera 									 = 0.00;
	public static final double dCamera 									 = 0.005;
	
	
	//**************************************************************************
    //************************** PID VALUES (SHOOTER) **************************
    //**************************************************************************
	
	//Practice
//	public static final double pShooter									 = 0.001;
//	public static final double iShooter									 = 0.00000625;
//	public static final double dShooter									 = 0.00;
//	public static final double kForward									 = 1.0/5582.0;
//	public static final double bForward									 = 0.0;
	
	//Competition
	public static final double pShooter									 = 0.00039;
	public static final double iShooter									 = 0.0000055;
	public static final double dShooter									 = 0.00;
	public static final double kForward									 = 0.0001628902407751;
	public static final double bForward									 = 0.048786729135007;
	
	//**************************************************************************
    //**************************** PID VALUES (ARM) ****************************
    //**************************************************************************
	
	//Practice
//	public static final double pArm 									 = 0.03;
//	public static final double iArm 									 = 0.00;
//	public static final double dArm 									 = 0.00;
	
	//Competition
	public static final double pArm 									 = 0.002;
	public static final double iArm 									 = 0.00;
	public static final double dArm 									 = 0.00;
	
	//**************************************************************************
    //**************************OUTPUT VALUES (Shooter)*************************
    //**************************************************************************
	
	//Practice
	//Arm all the way down
//	public static final int downArmAngle								= 300;
//	//Arm all the way up
//	public static final int upArmAngle									= 53;
	
	//Competition
	public static final int downArmAngle								= 490;
	//53
	//Arm all the way up
	public static final int upArmAngle									= 790;
	
	
	//**************************************************************************
	//***************************** SHOOTER NUMBERS ****************************
	//**************************************************************************
	
	//Practice
	public static final int outerShotRPM								= 4600;
	public static final int spyShotRPM									= 4200;
	public static final int badderShotRPM								= 4500;
	
	public static final int spyShotAngle								= -78;
	
	public static final double waitForPop								= 0.75;
	public static final double waitForHolder							= 0.15;
	public static final double waitForHolderClose						= 0.50;
	
	//Competition
	

	//**************************************************************************
	//***************************** INTAKE NUMBERS *****************************
	//**************************************************************************
	
	public static final double intakeDist								= 12.2;
	
}
