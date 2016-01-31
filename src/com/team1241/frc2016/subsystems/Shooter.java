package com.team1241.frc2016.subsystems;


import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.utilities.PIDController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *The Subsystem that is used for firing the balls from a flywheel. It uses the 2 motors oon the turret head,
 *the vertical indexing piston and reads an encoder for turret position feedback.
 *@author Shaqeeb Momen
 */
public class Shooter extends Subsystem {
	CANTalon rightShooter;
	CANTalon leftShooter;
	
	DoubleSolenoid popUp;
	DoubleSolenoid hoodPop;
	
	public PIDController velPID;
	
	public Shooter(){
		//Initialize Talons
		 rightShooter = new CANTalon (ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		 leftShooter = new CANTalon (ElectricalConstants.LEFT_SHOOTER_MOTOR);
		 
	     //Initialize Pistons		
		 popUp = new DoubleSolenoid (ElectricalConstants.POPPER_SHOOT_SOLENOID_A, 
				 					ElectricalConstants.POPPER_SHOOT_SOLENOID_B);
		 hoodPop = new DoubleSolenoid (ElectricalConstants.SHOOTER_HOOD_SOLENOID_A,
				 					ElectricalConstants.SHOOTER_HOOD_SOLENOID_B);
		 
		//Initialize PID	
		 velPID = new PIDController (NumberConstants.pShooter,
								   NumberConstants.iShooter,
								   NumberConstants.dShooter);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    	leftShooter.set(-shotVal);
    	    	
    }
    
    public void setRight(double testSpeed){
    	rightShooter.set(testSpeed);
    }
    
    public void setLeft(double testSpeed){
    	leftShooter.set(testSpeed);
    }
    
    public void stopShot(){
    	rightShooter.set(0);
    	leftShooter.set(0);
    	
    }
    
    public double readSpeed(){
		return 0;
    	// IDK how to read tachometer values so ill leave it to you felix 
    }
    /**********************************************SHOOTING METHODS***********************************************/

    public void shootBadder(){
    	// Prep the Hood
    	openHood();
    	
    	//Let Motors Accelerate to Speed
//    	setSpeed(velPID.calcVelPID(NumberConstants.badderShot, readSpeed(), 10));
    	
    	// Once the PID is satisfied, pop the ball into the shooter then retract
    	if (velPID.isDone()){
    		extendPop();
    		//dont know the best way to insert a delay here, haalp felix
    		retractPop();
    	}
    }
    
    public void shootFender(){
    	// Prep the Hood
    	closeHood();
    	
    	//Let Motors Accelerate to Speed
//    	setSpeed(velPID.calcVelPID(NumberConstants.fenderShot, readSpeed(), 10));
    	
    	// Once the PID is satisfied, pop the ball into the shooter then retract
    	if (velPID.isDone()){
    		extendPop();
    		//dont know the best way to insert a delay here, haalp felix
    		retractPop();
    	}
    }
}
