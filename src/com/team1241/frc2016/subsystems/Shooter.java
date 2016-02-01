package com.team1241.frc2016.subsystems;


import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ShootCommand;
import com.team1241.frc2016.pid.PIDController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	private DoubleSolenoid hoodPop;
	
	public PIDController shooterPID;
	public PIDController turretPID;
	
	private Encoder turretEncoder;
	
	public Shooter(){
		 // Initialize Talons
		 rightShooter = new CANTalon (ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		 leftShooter = new CANTalon (ElectricalConstants.LEFT_SHOOTER_MOTOR);
		 
		 
		 // Initialize turret, encoder, and the encoder properties
		 turret = new CANTalon(ElectricalConstants.TURRET_MOTOR);
		 // to satisfy the requirements of shooting accurately, use k4x and sample more to reduce jitter
		 turretEncoder = new Encoder(ElectricalConstants.TURRET_ENCODER_A, ElectricalConstants.TURRET_ENCODER_B,
				 ElectricalConstants.turretEncoderReverse, Encoder.EncodingType.k4X);
		 turretEncoder.setDistancePerPulse(ElectricalConstants.turretEncoderDistPerTick);
		 // TODO: will sampling reduce accuracy in which case we'd need to use k1x/k2x???
		 turretEncoder.setSamplesToAverage(ElectricalConstants.samplesToAverage);
		 
	     // Initialize Pistons		
		 popUp = new DoubleSolenoid (ElectricalConstants.POPPER_SHOOT_SOLENOID_A, 
				 					ElectricalConstants.POPPER_SHOOT_SOLENOID_B);
		 hoodPop = new DoubleSolenoid (ElectricalConstants.SHOOTER_HOOD_SOLENOID_A,
				 					ElectricalConstants.SHOOTER_HOOD_SOLENOID_B);
		 
		// Initialize PID	
		 shooterPID = new PIDController (NumberConstants.pShooter,
								   NumberConstants.iShooter,
								   NumberConstants.dShooter);
		 
		 turretPID = new PIDController(NumberConstants.pTurret,
				 					NumberConstants.iTurret,
				 					NumberConstants.dTurret);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShootCommand());
    }
    
    
   /**********************************************PNEUMATIC METHODS**********************************************/
    public void closeHood() {
    	hoodPop.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openHood(){
    	hoodPop.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractPop(){
    	popUp.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void extendPop(){
    	popUp.set(DoubleSolenoid.Value.kForward);
    }
    
    /**********************************************MOTOR METHODS**************************************************/

    public void setSpeed(double shotVal){
    	rightShooter.set(shotVal);
    	leftShooter.set(shotVal);
    }
    
    public void setRight(double speed){
    	rightShooter.set(speed);
    }
    
    public void setLeft(double speed){
    	leftShooter.set(speed);
    }
    
    public void turnTurret(double pwr) {
    	if (isLimitReached()){
    		adjustTurret(pwr);
    	} else {
    		turret.set(pwr);
    	}
    }
    
    /** @param angle angle is in radians
     * @param pwr pwr is +/- 0 to 1 
     * Restriction: -2pi <= x <= 2pi
     * */
    public void turnTurretToAngle(double angle, double pwr) {
    	if (getTurretAngle() <= angle) {
    		turret.set(pwr);  
    	} else if (getTurretAngle() >= angle ){
    		turret.set(-pwr);
    	}
    }
    
    /** 
     * If turret goes over the rotational limit, swing it in the opposite direction to the same angle
     * Eg: 370 degrees is the same as 10 degrees */
    public void adjustTurret(double pwr) {
    	if (getTurretAngle() >= 2*Math.PI) {
    		turnTurretToAngle(getTurretAngle() - 2*Math.PI, pwr);
    	} else if (getTurretAngle() <= -2*Math.PI) {
    		turnTurretToAngle(getTurretAngle() + 2*Math.PI, pwr);
    	}
    }
    
    /********************************************** TURRET ENCODER METHODS **********************************************/
    
    public boolean isLimitReached() {
    	return turretEncoder.get() > ElectricalConstants.driveEncoderPulsePerRot;
    }
    
    public double getTurretDistance() {
    	return turretEncoder.getDistance();
    }
    
    /**
     * Initial: 0 radians, facing forwards
     * Derivation: from % of rotation multiplied by 2pi 
     */
    public double getTurretAngle() {
    	double angle = 2*Math.PI * (turretEncoder.get() / ElectricalConstants.driveEncoderPulsePerRot);
    	
    	if (getTurretDirection()) {
    		return angle;
    	} else {
    		return -angle;
    	}
    }
    
    /** true for "up", false for "down" */
    public boolean getTurretDirection() {
    	return turretEncoder.getDirection();
    }
    
    /** true for "up", false for "down" */
    public void setTurretEncDirection(boolean direction) {
    	turretEncoder.setReverseDirection(direction);
    }
    
    public void resetTurretEncoder() {
    	turretEncoder.reset();
    }
        
    /**********************************************SHOOTING METHOD
     * @throws InterruptedException ***********************************************/
    
    
    public void shoot(boolean batter) throws InterruptedException{
    	if (batter) {
    		openHood();	
    	} else {
    		closeHood();
    	}
    	
    	if (shooterPID.isDone()){
    		extendPop();
    		Thread.sleep(1000);
    		retractPop();
    	}
    }
}


