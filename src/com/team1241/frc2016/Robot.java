package com.team1241.frc2016;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.ConveyorCommand;
import com.team1241.frc2016.commands.ShootCommand;
import com.team1241.frc2016.commands.Test;
import com.team1241.frc2016.commands.auto.*;
import com.team1241.frc2016.commands.defence.*;
import com.team1241.frc2016.pid.Constants;
import com.team1241.frc2016.subsystems.*;
import com.team1241.frc2016.utilities.DataOutput;
import com.team1241.frc2016.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static PowerDistributionPanel pdp;
	
	//Subsystems
	public static Drivetrain drive;
	public static Shooter shooter;
	public static Intake intake;
	public static Conveyor conveyor;
	
	public static Constants constants;
	public static OI oi;
	public static DataOutput output;
	
	Preferences pref;
	double kp;
	double ki;
	double kd;
	double power;
	public static double rpm;
	
    Command autonomousCommand;
    public SendableChooser autoChooser;
    public SendableChooser defenceChooser;
    public SendableChooser locationChooser;
    
    public static int defenceLocation;
    public static int selectedDefence;
    
    CameraServer server;
    
    ToggleBoolean toggle = new ToggleBoolean();
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	pdp = new PowerDistributionPanel();
    	
		oi = new OI();
		constants = new Constants("pidValues.txt");
		output = new DataOutput("data.txt");
		pref = Preferences.getInstance();
		
		drive = new Drivetrain();
		shooter = new Shooter();
		intake = new Intake();
		conveyor = new Conveyor();
		
		// Camera Server
		
		server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
		
        // instantiate the command used for the autonomous period
		
		defenceChooser = new SendableChooser();
		defenceChooser.addObject("Portcullis", 0);
		defenceChooser.addObject("Cheval de Frise", 1);
		defenceChooser.addObject("SallyPort", 2);
		defenceChooser.addObject("DrawBridge", 3);
		defenceChooser.addDefault("Drive Over (B/D)", 4);
		
		SmartDashboard.putData("Defence Mode", defenceChooser);
		
		locationChooser = new SendableChooser();
		
		locationChooser.addObject("1", 0);
		locationChooser.addObject("2", 1);
		locationChooser.addDefault("3", 2);
		locationChooser.addObject("4", 3);
		locationChooser.addObject("5", 4);
		
		SmartDashboard.putData("Location", locationChooser);
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No Auto", 0);
		autoChooser.addObject("SpyShot", 1);
		autoChooser.addObject("Breach", 2);
		autoChooser.addDefault("Breach&Shoot", 3);
		
		SmartDashboard.putData("Autonomous", autoChooser);
		
		updateSmartDashboard();
    }
     
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	
    public void autonomousInit() {
        // schedule the autonomous command (example)
    	conveyor.extendHolder();
    	drive.reset();
    	
    	new BreachAuton(4).start();
//    	new AutoCourtyard(4).start();
    	
//    	defenceLocation = (int) locationChooser.getSelected();
//    	selectedDefence = (int) defenceChooser.getSelected();
//    	
//    	switch((int)autoChooser.getSelected()) {
//    	case 0:
//    		autonomousCommand = (Command) new NoAuto();
//    		break;
//    	case 1:
//    		autonomousCommand = (Command) new SpyShotAuton();
//    		break;
//    	case 2:
//    		autonomousCommand = (Command) new BreachAuton(selectedDefence);
//    		break;
//    	case 3:
//    		autonomousCommand = (Command) new BreachShootAuton(defenceLocation, selectedDefence);
//    		break;
//    	}
//    	autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    }
    
    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if(autonomousCommand!=null)
    		autonomousCommand.cancel();
    	
    	shooter.retractPop();
    	conveyor.retractHolder();
    	conveyor.setContains(false);
    	
    	new ConveyorCommand().start();
    	
    	kp = pref.getDouble("kp", 0.0);
    	ki = pref.getDouble("ki", 0.0);
    	kd = pref.getDouble("kd", 0.0);
    	
//    	Robot.shooter.shooterPID.changePIDGains(kp, ki, kd);
    	
    	power = pref.getDouble("power", 0.0);
    	
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        LiveWindow.run();
        updateSmartDashboard();
        
//        rpm = pref.getDouble("rpm", 0.0);
        
        
        
        /*if(Robot.oi.getDriveAButton()) {
        	new CameraTrack().start();
        }*/
        
//        Robot.shooter.setSpeed(power);
        
//        if(Robot.oi.getDriveXButton()) {
//        	new AutoDrawbridge().start();
//        }
//        if(Robot.oi.getDriveAButton()) {
//        	new AutoPortcullis().start();
//        }
//        else if(Robot.oi.getDriveBButton()) {
//        	new AutoCheval().start();
//        }
        /*if(Robot.oi.getDriveYButton()) {
        	new DrivePath("0,0","22.6,40","48,0","48,96",5,1).start();
        }*/
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("LeftDrive Encoder", drive.getLeftEncoderDist());
        SmartDashboard.putNumber("RightDrive Encoder", drive.getRightEncoderDist());
        SmartDashboard.putNumber("Gyro", drive.getYaw());
        
        SmartDashboard.putBoolean("ShooterState", shooter.getShooterState());
        SmartDashboard.putNumber("Turret Angle", shooter.getTurretAngle());
        SmartDashboard.putNumber("Shooter RPM", shooter.getRPM());
        SmartDashboard.putBoolean("Can Shoot", shooter.shooterPID.isDone());
        
        SmartDashboard.putBoolean("Holders", conveyor.getHoldState());
        SmartDashboard.putBoolean("Detects Ball", !conveyor.getOptic());
        SmartDashboard.putBoolean("Contains Ball", conveyor.getContains());
        
        SmartDashboard.putNumber("Arm Pot", intake.getPotValue());
        
        SmartDashboard.putBoolean("Detects Target", ShootCommand.detects);
        SmartDashboard.putBoolean("Tracked", ShootCommand.tracked);
        SmartDashboard.putNumber("power", power);
        SmartDashboard.putNumber("X", shooter.getXCoordinates());
        SmartDashboard.putNumber("degree", shooter.pixelToDegree(shooter.getXCoordinates()));
        SmartDashboard.putNumber("distance", shooter.getDistanceToTarget());
        SmartDashboard.putNumber("Target width", shooter.targetWidthPixels());
        SmartDashboard.putNumber("Change in Degree",shooter.getTurretAngle() - shooter.pixelToDegree(shooter.getXCoordinates()));
//        System.out.println("PID " + shooter.shooterPID.getPGain() + "," + shooter.shooterPID.getIGain() + "," + shooter.shooterPID.getDGain());
    }
}
