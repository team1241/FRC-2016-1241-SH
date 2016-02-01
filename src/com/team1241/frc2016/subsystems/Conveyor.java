package com.team1241.frc2016.subsystems;

import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.commands.ConveyorCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *This subsystem is used to control the conveyor which intakes and holds the boulder. A motor is used 
 *to intake the boulder into the popper. Two pistons are used to hold the boulder in position for the
 *popper. They extend and retract to either outtake the ball or hold the ball.  
 *
 * @author Eric Huang and William San
 * @since 2016-01-31 
 */


public class Conveyor extends Subsystem {
	//Motor declarations
	private CANTalon conveyorMotor;
	
	//Pneumatic declarations
	private DoubleSolenoid outtakeBall;
	private DoubleSolenoid holdBall;
	
	public static boolean hasBall = false;
	
	/**
	 * Constructor for conveyor class
	 */
	public Conveyor() {
		
		//Motor 
		conveyorMotor = new CANTalon(ElectricalConstants.CONVEYOR_MOTOR);
		
		//Pistons
		outtakeBall = new DoubleSolenoid(ElectricalConstants.POPPER_RELEASE_SOLENOID_A,
										   ElectricalConstants.POPPER_RELEASE_SOLENOID_B);
		
		holdBall = new DoubleSolenoid(ElectricalConstants.POPPER_HOLD_SOLENOID_A,
										ElectricalConstants.POPPER_HOLD_SOLENOID_B);	
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new ConveyorCommand());
    }
	
	/**
	 * This method runs the intake motor at 50% speed (changes can be made)
	 */
	public void runMotor(double val) {
		conveyorMotor.set(val);
	}
	
	/**
	 * This method extends the popper hold piston, which holds the boulder for the shooter.
	 */
	public void extendHolder() {
		holdBall.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * This method retracts the popper hold piston
	 */
	public void retractHolder() {
		holdBall.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * This method extends the outtake piston, which will hold the ball in the shooter.
	 */
	public void extendOutake() {
		outtakeBall.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * This method retracts the outtake piston, which will outtake the ball, in the case of lowgoal. 
	 */
	public void retractOutake() {
		outtakeBall.set(DoubleSolenoid.Value.kReverse);
	}
}
