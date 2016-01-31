package com.team1241.frc2016.subsystems;


import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.ShootCommand;
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
    
    public void stopShot(){
    	rightShooter.set(0);
    	leftShooter.set(0);
    }
    
    public double readSpeed(){
    	double speed = 0;
//    	double speedleft = leftShooter.getsp;
    	double speedRight = 0;
    	
    	
//    	speed = (speedleft + speedRight) / 2; 
    	return speed;
    	// IDK how to read tachometer values so ill leave it to you felix 
    }
    
    
    /**********************************************SHOOTING METHOD
     * @throws InterruptedException ***********************************************/
    
    
    public void shoot(boolean batter) throws InterruptedException{
    	if (batter) {
    		openHood();	
    	} else {
    		closeHood();
    	}
    	
    	if (velPID.isDone()){
    		extendPop();
    		Thread.sleep(1000);
    		retractPop();
    	}
    }
}


