package com.team1241.frc2016.subsystems;
import com.team1241.frc2016.*;
import com.team1241.frc2016.commands.IntakeCommand;
import com.team1241.frc2016.utilities.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
/**Intake Class
 * 
 * This class controls the robots Intake. Allows the the intake motor to be run with buttons, and the intake arms with 
 * buttons and a joystick
 * 
 * @author Zubair Waheed the faggg
 * @author Taabish Jeshani 
 * @since 2016-01-31
 */
public class Intake extends Subsystem {
    
	//Talons to controls all intake motors
    CANTalon intake;
    CANTalon intakeArmRight;
    CANTalon intakeArmLeft;
    
    //PID for arm control
    PIDController armPID;
    
    //Potentiometer for arm control
    Potentiometer pot;
    
    /**Creates an intake object, initializes all its required objects*/
    public Intake(){
    	
        //Initialize Components
    	intake = new CANTalon(ElectricalConstants.INTAKE_MOTOR);
    	
    	//Motor controllers for the intake arm
    	intakeArmLeft = new CANTalon(ElectricalConstants.LEFT_ARM_MOTOR);
    	intakeArmRight = new CANTalon(ElectricalConstants.RIGHT_ARM_MOTOR);

    	//PID used for arm control
        armPID = new PIDController(NumberConstants.pArm,
       		 					   NumberConstants.iArm,
       		 					   NumberConstants.dArm);
        
        pot = new AnalogPotentiometer(ElectricalConstants.ARM_POTENTIOMETER, 360);
    }
    
    
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCommand());
    }
    
    /**************** Potentiometer methods *****************/
    public double getPotValue() {
    	return pot.get();
    }
    
    /**Runs the motor backwards to intake the ball */
    public void runIntakeMotor(double val){
    	intake.set(val);
    }
    
    /**Moves the intake arms up from button input*/
    public void runIntakeArms(double val) {
    	if(Math.abs(val)>1)
    		val = Math.pow(val, 0);
    	intakeArmLeft.set(val);
    	intakeArmRight.set(-val);
    }
    
    
    /**Sets the position of the intake arms*/
    public void setArmPosition(double angle, double speed) {
    	//Set up sensor methods
//    	double output = armPID.calcPID(angle, 5, 5);
    	runIntakeArms(angle*speed);
    }
}