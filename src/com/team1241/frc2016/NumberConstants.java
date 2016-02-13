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
	
	//0.05
	public static final double pDrive 									 = 0.03;
	public static final double iDrive 									 = 0.00;
	public static final double dDrive 									 = 0.00;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	public static final double pGyro 									 = 0.03;
	public static final double iGyro 									 = 0.00;
	public static final double dGyro 									 = 0.00;
	
	//**************************************************************************
    //*************************** PID VALUES (TURRET) **************************
    //**************************************************************************
	
	//0.05
	public static final double pTurret 									 = 0.03;
	public static final double iTurret 									 = 0.00;
	public static final double dTurret 									 = 0.01;
	
	//**************************************************************************
    //************************** PID VALUES (SHOOTER) **************************
    //**************************************************************************
	
	public static final double pShooter									 = 0.00001;
	public static final double iShooter									 = 0.00;
	public static final double dShooter									 = 0.0001;
	
	//**************************************************************************
    //**************************** PID VALUES (ARM) ****************************
    //**************************************************************************
	
	public static final double pArm 									 = 0.03;
	public static final double iArm 									 = 0.00;
	public static final double dArm 									 = 0.00;
	
	
	//**************************************************************************
    //**************************OUTPUT VALUES (Shooter)*************************
    //**************************************************************************
	
	public static final int maxArmAngle									= 290;
	public static final int minArmAngle									= 25;
	
	//**************************************************************************
	//***************************** SHOOTER NUMBERS ****************************
	//**************************************************************************
	
	public static final int spyShotRPM									= 4500;
	public static final int badderShotRPM								= 4600;
//	public static final int outerShotRPM								= 4700;
	
	public static final int spyTurretAngle								= 40;
	
	public static final double waitForPop								= 1.00;
	public static final double waitForSpeed								= 3.00;

	//**************************************************************************
	//***************************** INTAKE NUMBERS *****************************
	//**************************************************************************
	
	public static final double intakeDist								= 12.2;
	
}
