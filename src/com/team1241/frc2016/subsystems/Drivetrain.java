package com.team1241.frc2016.subsystems;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.ElectricalConstants;
import com.team1241.frc2016.commands.TankDrive;
import com.team1241.frc2016.utilities.PIDController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem that is used for the drive train of the robot. It runs the 4 motors in the drive train as well as
 * read the encoder values from the motors.
 * @author Bryan Kristiono
 * @since 2016-10-10
 */
public class Drivetrain extends Subsystem {
	CANTalon rightDriveFront;
	CANTalon rightDriveBack;
	
	CANTalon leftDriveFront;
	CANTalon leftDriveBack;
	
	Encoder leftDriveEncoder;               
    Encoder rightDriveEncoder; 
    
    public PIDController drivePID;
    
    public double cogx = 0;

	public Drivetrain() {
		rightDriveFront = new CANTalon(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightDriveBack = new CANTalon(ElectricalConstants.RIGHT_DRIVE_BACK);
		
		leftDriveFront = new CANTalon(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftDriveBack = new CANTalon(ElectricalConstants.LEFT_DRIVE_BACK);
		
		leftDriveEncoder = new Encoder(ElectricalConstants.LEFT_DRIVE_ENCODER_A, 
				ElectricalConstants.LEFT_DRIVE_ENCODER_B, 
				ElectricalConstants.leftDriveTrainEncoderReverse, 
				Encoder.EncodingType.k4X);
		
		leftDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);


		rightDriveEncoder = new Encoder(ElectricalConstants.RIGHT_DRIVE_ENCODER_A,
				ElectricalConstants.RIGHT_DRIVE_ENCODER_B, 
				ElectricalConstants.rightDriveTrainEncoderReverse, 
				Encoder.EncodingType.k4X);
		
		rightDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);
		
		drivePID = new PIDController(NumberConstants.pDrive, 
									NumberConstants.iDrive, 
									NumberConstants.dDrive);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new CameraTrack());
    	setDefaultCommand(new TankDrive());
//    	setDefaultCommand(new ArcadeDrive());
    }
    
    public void updateCogX(double x){
    	cogx = x;
    }
    
    public void runLeftDrive(double pwmVal) {
    	if(pwmVal>1)
    		pwmVal = 1;
    	leftDriveFront.set(pwmVal);
    	leftDriveBack.set(pwmVal);
    }
    
    public void runRightDrive(double pwmVal) {
    	pwmVal = pwmVal*1.4;
    	if(pwmVal>1)
    		pwmVal = 1;
    	rightDriveFront.set(pwmVal);
    	rightDriveBack.set(pwmVal);
    }
    
    public double getAverageDistance(){
    	return (getLeftEncoderDist() + getRightEncoderDist())/2;
    }
    
    public void driveStraight(double setPoint, double speed) {
    	double output = drivePID.calcPID(setPoint, getAverageDistance(), 1);
    	
    	runLeftDrive(output*speed);
    	runRightDrive(-output*speed);
    }
    
    /**
     * Resets the encoder AND gyro to zero
     */
    public void reset()
    {
        resetEncoders();
//        resetGyro();
    }

    /**
     * This function returns the distance traveled from the left encoder in inches
     * 
     * @return Returns distance traveled by encoder in inches
     */
    public double getLeftEncoderDist(){
        return leftDriveEncoder.getDistance();
    }
    
    /**
     * This function returns the distance traveled from the right encoder in inches
     * 
     * @return Returns distance traveled by encoder in inches
     */
    public double getRightEncoderDist(){
        return rightDriveEncoder.getDistance();
    }
    
    /**
     * This function returns the raw value from the left encoder
     * 
     * @return Returns raw value from encoder
     */
    public double getLeftEncoderRaw(){
        return leftDriveEncoder.getRaw();
    }

    /**
     * This function returns the raw value from the right encoder
     * 
     * @return Returns raw value from encoder
     */
    public double getRightEncoderRaw(){
        return rightDriveEncoder.getRaw();
    }

    /**
     * This function returns the rate the left encoder is moving at in inches/sec
     * 
     * @return Returns rate of encoder in inches/sec
     */
    public double getLeftEncoderRate(){
        return leftDriveEncoder.getRate();
    }

    /**
     * This function returns the rate the right encoder is moving at in inches/sec
     * 
     * @return Returns rate of encoder in inches/sec
     */
    public double getRightEncoderRate(){
        return rightDriveEncoder.getRate(); 
    }

    /**
     * Resets both left and right encoders
     */
    public void resetEncoders() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }
}

