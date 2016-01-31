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
    //***************************PID VALUES (DRIVE)*****************************
    //**************************************************************************
	
	public static final double pDrive 									 = 0.03;
	//0.05
	public static final double iDrive 									 = 0.00;
	public static final double dDrive 									 = 0.00;
	
	//**************************************************************************
    //***************************PID VALUES (GYRO)******************************
    //**************************************************************************
	
	public static final double pGyro 									 = 0.03;
	public static final double iGyro 									 = 0.00;
	public static final double dGyro 									 = 0.00;
	
	//**************************************************************************
    //*************************PID VALUES (Shooter)*****************************
    //**************************************************************************
	
	public static final double pShoot 									 = 0.00;
	public static final double iShoot 									 = 0.00;
	public static final double dShoot 									 = 0.00;
	public static final double sShoot								     = 0.00;
	
	//**************************************************************************
    //**************************OUTPUT VALUES (Shooter)*************************
    //**************************************************************************
	
	public static final double badderShot								= 0.00;
	public static final double fenderShot								= 0.00;
}
