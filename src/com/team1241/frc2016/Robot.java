
package com.team1241.frc2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import com.team1241.frc2016.commands.auto.NoAuto;
import com.team1241.frc2016.commands.auto.OuterWorksAuton;
import com.team1241.frc2016.commands.auto.SetDefence;
import com.team1241.frc2016.commands.auto.SimpleAuton;
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
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No Auto", new NoAuto());
		autoChooser.addObject("OuterWorks", new OuterWorksAuton());
		autoChooser.addObject("SpyShot", new SpyShotAuton());
		
		SmartDashboard.putData("Auto Mode", autoChooser);
		
		defenceChooser = new SendableChooser();
		defenceChooser.addObject("Portcullis", new SetDefence(0));
		defenceChooser.addObject("Cheval de Frise", new SetDefence(1));
		defenceChooser.addObject("SallyPort", new SetDefence(2));
		defenceChooser.addObject("DrawBridge", new SetDefence(3));
		defenceChooser.addDefault("Drive Over (B/D)", new SetDefence(4));
		
//		SmartDashboard.putData("Defence Mode", defenceChooser);
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
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
        defenceLocation = pref.getInt("Defense Location", 3);
    }
}
