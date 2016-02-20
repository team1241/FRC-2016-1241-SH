package com.team1241.frc2016.subsystems;


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
		 shooterPID = new PIDController (NumberConstants.pShooter,
								   NumberConstants.iShooter,
								   NumberConstants.dShooter);
		 
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
	     }
	     catch(Exception ex){
	    	 System.out.println("Unable to get coordinates");
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
//    	setDefaultCommand(new ShootCommand());
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
    	double output = cameraPID.calcPID(angle, getTurretAngle(), 0.5);
    	turret.set(pwr*output);	
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
    	double output = cameraPID.calcPIDVelocity(0, pixel, 0.5);
    	turret.set(-(pwr*output));	
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
    
    public void updateCoordinates(){
    	NetworkTable server = NetworkTable.getTable("SmartDashboard");
	     try{
	        targetNum = server.getNumberArray("MEQ_COORDINATES");
	     }
	     catch(Exception ex){
	    	 System.out.println("Unable to get coordinates");
	     }
    }
    
    public double[] getCoordinates(){
    	return targetNum;
    }
    
    public double getXCoordinates(){
    	updateCoordinates();
//    	System.out.println(targetNum[0]);
    	if(targetNum.length == 8)
    		return (targetNum[0]+targetNum[2]+targetNum[4]+targetNum[6])/4;
    	else
    		return -1;
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
    	double output = shooterPID.calcPIDVelocity(rpm, getRPM(), 30);
//    	System.out.println("Output: " + output + " FeedBack: " + rpm*NumberConstants.kForward);
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
    
    public void resetEncoder() {
    	turretEncoder.reset();
    }
    
}


