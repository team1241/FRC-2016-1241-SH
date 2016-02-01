
package com.team1241.frc2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import com.team1241.frc2016.commands.auto.NoAuto;
import com.team1241.frc2016.commands.auto.OuterWorksAuton;
import com.team1241.frc2016.commands.auto.SpyShotAuton;
import com.team1241.frc2016.pid.Constants;
import com.team1241.frc2016.subsystems.Conveyor;
import com.team1241.frc2016.subsystems.Drivetrain;
import com.team1241.frc2016.subsystems.Intake;
import com.team1241.frc2016.subsystems.Shooter;
import com.team1241.frc2016.utilities.DataOutput;

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
	//Subsystems
	public static Drivetrain drive;
	public static Shooter shooter;
	public static Intake intake;
	public static Conveyor conveyor;
	
	public static Constants constants;
	public static OI oi;
	public static DataOutput output;
	
	Preferences pref;
    Command autonomousCommand;
    public SendableChooser autoChooser;
    public SendableChooser defenceChooser;
    public SendableChooser locationChooser;
    
    public static int defenceLocation;
    public static int selectedDefence;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		constants = new Constants("pidValues.txt");
		output = new DataOutput("data.txt");
		pref = Preferences.getInstance();
		
		drive = new Drivetrain();
		shooter = new Shooter();
		intake = new Intake();
		conveyor = new Conveyor();
		
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
		autoChooser.addObject("No Auto", new NoAuto());
		autoChooser.addDefault("OuterWorks", new OuterWorksAuton());
		autoChooser.addObject("SpyShot", new SpyShotAuton());
		
		SmartDashboard.putData("Autonomous", autoChooser);
    }
    
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	defenceLocation = (int) locationChooser.getSelected();
    	selectedDefence = (int) defenceChooser.getSelected();
    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if(autonomousCommand!=null)
    		autonomousCommand.cancel();
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
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("LeftDrive Encoder", Math.round(drive.getLeftEncoderDist()));
        SmartDashboard.putNumber("RightDrive Encoder", Math.round(drive.getRightEncoderDist()));
        SmartDashboard.putDouble("pot", intake.getPotValue());
    }
}
