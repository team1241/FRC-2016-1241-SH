package com.team1241.frc2016;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.ConveyorCommand;
import com.team1241.frc2016.commands.ShootCommand;
import com.team1241.frc2016.commands.Test;
import com.team1241.frc2016.commands.TurnTurret;
import com.team1241.frc2016.commands.auto.*;
import com.team1241.frc2016.commands.defence.*;
import com.team1241.frc2016.pid.Constants;
import com.team1241.frc2016.subsystems.*;
import com.team1241.frc2016.utilities.DataOutput;
import com.team1241.frc2016.utilities.ShooterTest;
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
	public static double outerRPM;
	public static double spyRPM;
	
    Command autonomousCommand;
    public SendableChooser autoChooser;
    public SendableChooser defenceChooser;
    public SendableChooser locationChooser;
    
    public static int defenceLocation;
    public static int selectedDefence;
    public static int autoNumber;
    
    CameraServer server;
    
    public static boolean connected = false;
    public static double runTime = 0;
    public static int timer = 0;
    
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
//		server = CameraServer.getInstance();
//        server.setQuality(50);
//        server.startAutomaticCapture("cam0");
		
        // instantiate the command used for the autonomous period
		
		defenceChooser = new SendableChooser();
		defenceChooser.addObject("Portcullis", 0);
		defenceChooser.addObject("Cheval de Frise", 1);
		defenceChooser.addObject("SallyPort", 2);
		defenceChooser.addObject("DrawBridge", 3);
		defenceChooser.addDefault("Rock Wall", 4);
		defenceChooser.addDefault("Rough Terrain", 5);
		
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
		
		defenceLocation = (int) locationChooser.getSelected();
    	selectedDefence = (int) defenceChooser.getSelected();
    	autoNumber = (int) autoChooser.getSelected();
    	
    	switch(autoNumber) {
    	case 0:
    		autonomousCommand = (Command) new NoAuto();
    		break;
    	case 1:
    		autonomousCommand = (Command) new SpyShotAuton();
    		break;
    	case 2:
    		autonomousCommand = (Command) new BreachAuton(selectedDefence);
    		break;
    	case 3:
    		autonomousCommand = (Command) new BreachShootAuton(defenceLocation, selectedDefence);
    		break;
    	}
	}

	
    public void autonomousInit() {
        // schedule the autonomous command (example)
    	conveyor.extendHolder();
    	drive.reset();
    	shooter.reset();
    	
    	autonomousCommand.start();
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
    	
//    	Robot.shooter.cameraPID.changePIDGains(kp, ki, kd);
    	
    	power = pref.getDouble("power", 0.0);
    	
    	drive.reset();
//    	new CameraTrack(-1).start();
//    	new ShooterTest().start();
    	
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
        
        
        
//	     if(oi.getDriveXButton()) {
//	    	 new ShooterTest().start();
//	     }
//	     else if(oi.getDriveBButton()) {
//	    	 shooter.turnTurret(-pref.getDouble("turretPower", 0.0));
//	     }
//	     else {
//	    	 shooter.turnTurret(0);
//	     }
	     
//        if(Robot.shooter.getXCoordinates()>-1)
//        	new CameraTrack(-1).start();
//        else 
//        	Robot.shooter.turnTurret(0);
        outerRPM = pref.getDouble("outerRPM", 0.0);
        spyRPM = pref.getDouble("reverseOuterRPM", 0.0);
        
//        Robot.shooter.setSpeed(power);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateSmartDashboard() {
    	//DriveTrain
    	SmartDashboard.putNumber("LeftDrive Encoder", drive.getLeftEncoderDist());
        SmartDashboard.putNumber("RightDrive Encoder", drive.getRightEncoderDist());
        SmartDashboard.putNumber("Gyro Yaw", drive.getYaw());
        SmartDashboard.putNumber("Gyro Pitch", drive.getPitch());
        SmartDashboard.putNumber("Gyro Roll", drive.getRoll());
        
        //Shooter
        SmartDashboard.putBoolean("ShooterState", shooter.getShooterState());
        SmartDashboard.putNumber("Turret Angle", shooter.getTurretAngle());
        SmartDashboard.putNumber("Shooter RPM", shooter.getRPM());
        SmartDashboard.putBoolean("Can Shoot", shooter.shooterPID.isDone());
        
        //Popper
        SmartDashboard.putBoolean("Holders", conveyor.getHoldState());
        SmartDashboard.putBoolean("Detects Ball", !conveyor.getOptic());
        SmartDashboard.putBoolean("Contains Ball", conveyor.getContains());
        
        //Arm
        SmartDashboard.putNumber("Arm Pot", intake.getPotValue());
        
        //Detects target
        if(Robot.shooter.getXCoordinates()!=-1)
        	SmartDashboard.putBoolean("Detects Target", true);
    	else
    		SmartDashboard.putBoolean("Detects Target", false);
        
        //On Target
        if(Math.abs(Robot.shooter.pixelToDegree(Robot.shooter.getXCoordinates())-NumberConstants.cameraOffset) <= 0.8)
        	SmartDashboard.putBoolean("Tracked", true);
		else
			SmartDashboard.putBoolean("Tracked", false);
        
        //Camera Tracking
        SmartDashboard.putNumber("X", shooter.getXCoordinates());
        SmartDashboard.putNumber("degree", shooter.pixelToDegree(shooter.getXCoordinates()));
        SmartDashboard.putNumber("distance", shooter.getDistanceToTarget());
        SmartDashboard.putNumber("Target width", shooter.targetWidthPixels());
        SmartDashboard.putNumber("Change in Degree",shooter.getTurretAngle() - shooter.pixelToDegree(shooter.getXCoordinates()));
        
        //Connection to the Kangaroo
        NetworkTable server = NetworkTable.getTable("SmartDashboard");
        double temp = -1;
        timer++; 
        try{
        	temp = server.getDouble("RUN_TIME", -1);
        }
        catch(Exception ex){
        }
        if(timer > 10 && (temp ==-1|| runTime==temp)) {
        	connected = false;
        	timer = 0;
        }
        else if(timer>10) {
        	timer = 0;
        	runTime = temp;
        	connected = true;
        }
        
        SmartDashboard.putBoolean("PC Connected", connected);
        
        //Autonomous
        SmartDashboard.putNumber("defenceLocation", defenceLocation);
        SmartDashboard.putNumber("selectedDefence", selectedDefence);
        SmartDashboard.putNumber("autoNumber", autoNumber);

        SmartDashboard.putData(Scheduler.getInstance());
        SmartDashboard.putData("Calibrate Shooter", new ShooterTest());
    }
}
