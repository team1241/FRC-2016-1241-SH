package com.team1241.frc2016.subsystems;


import java.util.Arrays;

import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ShootCommand;
import com.team1241.frc2016.pid.PIDController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *The Subsystem that is used for firing the balls from a flywheel. It uses the 2 motors on the turret head,
 *the vertical indexing piston and reads an encoder for turret position feedback.
 *@author Shaqeeb Momen
 */
public class Shooter extends Subsystem {
	private CANTalon rightShooter;
	private CANTalon leftShooter;
	
	private CANTalon turret;
	
	private DoubleSolenoid popUp;
//	private DoubleSolenoid hood;
	private boolean shooterState;
	
	public PIDController shooterPID;
	public PIDController turretPID;
	public PIDController cameraPID;
	
	private Encoder turretEncoder;
	
	private Counter optical;
	
	private double[] targetNum = new double[8];
	public boolean connected;
	
	public Shooter(){
		 // Initialize Talons
		 rightShooter = new CANTalon (ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		 leftShooter = new CANTalon (ElectricalConstants.LEFT_SHOOTER_MOTOR);
		 
		 
		 // Initialize turret, encoder, and the encoder properties
		 turret = new CANTalon(ElectricalConstants.TURRET_MOTOR);
		 turretEncoder = new Encoder(ElectricalConstants.TURRET_ENCODER_A, ElectricalConstants.TURRET_ENCODER_B,
				 ElectricalConstants.turretEncoderReverse, Encoder.EncodingType.k4X);
		 turretEncoder.setDistancePerPulse(ElectricalConstants.turretEncoderDegPerTick);
		 
		 optical = new Counter();
		 optical.setUpSource(ElectricalConstants.SHOOTER_OPTICS);
		 optical.setUpDownCounterMode();
		 optical.setDistancePerPulse(1);
		 
	     // Initialize Pistons		
		 popUp = new DoubleSolenoid (ElectricalConstants.PCM, ElectricalConstants.POPPER_SHOOT_SOLENOID_A, 
				 					ElectricalConstants.POPPER_SHOOT_SOLENOID_B);
//		 hood = new DoubleSolenoid (ElectricalConstants.PCM, ElectricalConstants.SHOOTER_HOOD_SOLENOID_A,
//				 					ElectricalConstants.SHOOTER_HOOD_SOLENOID_B);
		 
		// Initialize PID	
		 shooterPID = new PIDController (NumberConstants.pShooterBadder,
								   NumberConstants.iShooterBadder,
								   NumberConstants.dShooterBadder);
		 
		 turretPID = new PIDController(NumberConstants.pTurret,
				 					NumberConstants.iTurret,
				 					NumberConstants.dTurret);
		 
		 cameraPID = new PIDController(NumberConstants.pCamera,
				 					NumberConstants.iCamera,
				 					NumberConstants.dCamera);
		 
		 shooterState = false;
		 
		 NetworkTable server = NetworkTable.getTable("SmartDashboard");
	     try{
	        targetNum = server.getNumberArray("MEQ_COORDINATES");
	        connected = true;
	     }
	     catch(Exception ex){
	    	 System.out.println("Unable to get coordinates");
	    	 connected = false;
	     }
	}
	
	public void setShooterState(boolean state) {
		shooterState = state;
	}
	
	public boolean getShooterState() {
		return shooterState;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShootCommand());
    }
    
    
   /**********************************************PNEUMATIC METHODS**********************************************/
//    public void closeHood() {
//    	hood.set(DoubleSolenoid.Value.kReverse);
//    }
//    
//    public void openHood(){
//    	hood.set(DoubleSolenoid.Value.kForward);
//    }
    
    public void retractPop(){
    	popUp.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void extendPop(){
    	popUp.set(DoubleSolenoid.Value.kForward);
    }
    
    /**********************************************MOTOR METHODS**************************************************/

    public void setSpeed(double shotVal){
    	rightShooter.set(-shotVal);
    	leftShooter.set(-shotVal);
    }
    
    public void setRight(double speed){
    	rightShooter.set(speed);
    }
    
    public void setLeft(double speed){
    	leftShooter.set(speed);
    }
    
    public void turnTurret(double pwr) {
    	turret.set(pwr);
    }
    
    /** @param angle angle is in degrees
     * @param pwr pwr is +/- 0 to 1 
     * Restriction: -180 <= x <= 180
     * */
    public void turnTurretToAngle(double angle, double pwr) {
    	double output = turretPID.calcPID(angle, getTurretAngle(), 0.5);
    	turret.set(pwr*output);	
    }
    
    public void turnTurretCamera(double angle, double pwr) {
    	double output = cameraPID.calcPIDVelocity(angle, getTurretAngle(), 0.5, 0.8);
    	System.out.print("CameraPIDs:" + output);
    	if(Math.abs(angle-getTurretAngle()) <0.5) {
    		turret.set(0);
    		System.out.println(" Output:" + 0);
    	}
    	else if(Math.abs(output)>0.1) {
    		turret.set(pwr*output);
    		System.out.println(" Output:" + pwr*output);
    	}
    	else if(output>-0.1 && output<0) {
    		turret.set(-0.1);
    		System.out.println(" Output:" + -0.1);
    	}
    	else if(output<0.1 && output>0) {
    		turret.set(0.1);
    		System.out.println(" Output:" + 0.1);
    	}
    }
    
    /** 
     * If turret goes over the rotational limit, swing it in the opposite direction to the same angle
     * Eg: 370 degrees is the same as 10 degrees */
    public void adjustTurret(double pwr) {
    	if (getTurretAngle() >= 360) {
    		turnTurretToAngle(getTurretAngle() - 360, pwr);
    	} else if (getTurretAngle() <= -360) {
    		turnTurretToAngle(getTurretAngle() + 360, pwr);
    	}
    }
    
    /** @param pixel pixel is in degrees
     * @param pwr pwr is +/- 0 to 1 
     * Restriction: 0 <= x <= 640
     * */
    public void turnTurretToPixel(double pixel, double pwr) {
    	double output = cameraPID.calcPIDVelocity(320, pixel, 0.2, 0.7);
    	turret.set((pwr*output));	
    }
    
    /*public double pixelToDegree(double pixel) {
    	return 0.105807*pixel-37.4135;
    }*/
    
    public double pixelToDegree(double pixel){
    	return Math.toDegrees(Math.atan(((pixel-320)*Math.tan(Math.toRadians(31.81)))/320));
    }
    
    /********************************************** TURRET ENCODER METHODS **********************************************/
    
    public void reset() {
    	turretEncoder.reset();
    }
    
    public double getTurretDistance() {
    	return turretEncoder.getDistance();
    }
    
    /**
     * This function returns the raw value from the left encoder
     * 
     * @return Returns raw value from encoder
     */
    public double getTurretRaw(){
        return turretEncoder.getRaw();
    }
    
    /**
     * This function returns the rate the left encoder is moving at in inches/sec
     * 
     * @return Returns rate of encoder in inches/sec
     */
    public double getTurretEncoderRate(){
        return turretEncoder.getRate();
    }
    
    public boolean updateCoordinates(){
    	NetworkTable server = NetworkTable.getTable("SmartDashboard");
	     try{
	        targetNum = server.getNumberArray("MEQ_COORDINATES");
//	        System.out.println(Arrays.toString(targetNum));
	        connected = true;
	        return true;
	     }
	     catch(Exception ex){
//	    	 System.out.println("Unable to get coordinates");
	    	 connected = false;
	    	 return false;
	     }
    }
    
    public double[] getCoordinates(){
    	return targetNum;
    }
    
    public double getXCoordinates(){
    	if(updateCoordinates()) {
    		if(targetNum.length == 8)
    			return (targetNum[0]+targetNum[2]+targetNum[4]+targetNum[6])/4;
    	}
    	return -1;
    }
    
    public double targetWidthPixels(){
    	if(updateCoordinates()) {
    		if(targetNum.length == 8)
    			return ((targetNum[0]-targetNum[2]) + (targetNum[6]-targetNum[4]))/2;
    	}
    	return -1;
    }
    
    public double getDistanceToTarget(){
    	return 20*640/(2*targetWidthPixels()*Math.tan(Math.toRadians(31.81)));
    }
    
    /********************************************** OPTICAL SENSOR METHODS **********************************************/
    
    public double getOptic(){
    	return optical.get();
    }
    
    public double getRPM(){
    	return optical.getRate()*60;
    }
    
    /********************************************** TURRET PID *********************************************************/
    
    public void turnTurretTo(double setPoint, double power) {
    	double output = turretPID.calcPID(setPoint, turretEncoder.getRaw(), 5);
    	
    	turret.set(output*power);
    }
    
    /********************************************** SHOOTER PID ********************************************************/
    
    public void setRPM(double rpm){
    	double output = shooterPID.calcPIDVelocity(rpm, getRPM(), 50, 0.6);
    	System.out.println("Output: " + output + " FeedBack: " + rpm*NumberConstants.kForward+NumberConstants.bForward);
    	setSpeed(output+rpm*NumberConstants.kForward+NumberConstants.bForward);
    }
    
    /**
     * Initial: 0 degrees, facing forwards
     * Derivation: from % of rotation multiplied by 360 
     */
    public double getTurretAngle() {
    	return turretEncoder.getRaw() / ElectricalConstants.turretEncoderDegPerTick;
    }
    
    /** true for "up", false for "down" */
    public boolean getTurretDirection() {
    	return turretEncoder.getDirection();
    }
    
    /** true for "up", false for "down" */
    public void setTurretEncDirection(boolean direction) {
    	turretEncoder.setReverseDirection(direction);
    }
}


