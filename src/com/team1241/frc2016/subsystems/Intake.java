package com.team1241.frc2016.subsystems;
import com.team1241.frc2016.*;
import com.team1241.frc2016.commands.IntakeCommand;
import com.team1241.frc2016.pid.PIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
/**Intake Class
 * 
 * This class controls the robots Intake. Allows the the intake motor to be run with buttons, and the intake arms with 
 * buttons and a joystick
 * 
 * @author Zubair Waheed
 * @author Taabish Jeshani 
 * @author Bryan Kristiono
 * @since 2016-02-02
 */
public class Intake extends Subsystem {
    
	//Talons to controls all intake motors
    private CANTalon intake;
    private CANTalon rightArm;
    private CANTalon leftArm;
    
    //PID for arm control
    public PIDController armPID;
    
    //Potentiometer for arm control
    private Potentiometer pot;
    private Ultrasonic ultra;
    
    /**Creates an intake object, initializes all its required objects*/
    public Intake(){
    	
        //Initialize Components
    	intake = new CANTalon(ElectricalConstants.INTAKE_MOTOR);
    	
    	//Motor controllers for the intake arm
    	leftArm = new CANTalon(ElectricalConstants.LEFT_ARM_MOTOR);
    	rightArm = new CANTalon(ElectricalConstants.RIGHT_ARM_MOTOR);

    	//PID used for arm control
        armPID = new PIDController(NumberConstants.pArm,
       		 					   NumberConstants.iArm,
       		 					   NumberConstants.dArm);
        
        pot = new AnalogPotentiometer(ElectricalConstants.ARM_POTENTIOMETER, 1080, -2);
        ultra = new Ultrasonic(ElectricalConstants.ULTRASONIC_TRIG, ElectricalConstants.ULTRASONIC_ECHO);
    }
    
    
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCommand());
    }
    
    /**************** Potentiometer methods *****************/
    public double getPotValue() {
    	return pot.get();
    }
    
    
    
    /**Runs the motor backwards to intake the ball */
    public void runIntake(double val){
    	intake.set(val);
    }
    
    public void runLeftArm(double val) {
    	leftArm.set(val);
    }
    
    public void runRightArm(double val) {
    	rightArm.set(val);
    }
    
    /**Moves the intake arms up from button input*/
    public void runArms(double val) {
    	if(Math.abs(val)>1)
    		val = Math.pow(val, 0);
    	leftArm.set(val);
    	rightArm.set(-val);
    }
    
    
    /**Sets the position of the intake arms*/
    public void setArmPosition(double angle, double speed) {
    	//Set up sensor methods
    	double output = armPID.calcPID(angle, pot.get(), 5);
    	runArms(output*speed);
    }
    
    public boolean isArmPosTooHigh() {
    	return getPotValue() > NumberConstants.maxArmAngle;
    }
    
    
    /*****************UltraSonic Sensor Methods*****************/
    
    public double getRange() {
    	return ultra.getRangeInches();
    }
}