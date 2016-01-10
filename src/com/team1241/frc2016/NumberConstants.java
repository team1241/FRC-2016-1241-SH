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
    //*************************PID VALUES (ELEVATOR)****************************
    //**************************************************************************
	//Original value = 0.05
	
	public static final double pElev 									 = 0.03;
	public static final double iElev 									 = 0.00;
	public static final double dElev 									 = 0.00;
	
	public static final double pElevDown								 = 0.03;
	public static final double iElevDown								 = 0.00;
	public static final double dElevDown								 = 0.00;
	
	//**************************************************************************
    //*******************************TOTE LIMIT*********************************
    //**************************************************************************
	
	public static final int toteLimit 										= 6;
}
