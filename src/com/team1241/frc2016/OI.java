package com.team1241.frc2016;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * Used with the Logitech F310.
 * 
 * @author Mahrus Kazi
 * @author Bryan Kristiono
 * @since 2016-01-10
 */
public class OI {

	Joystick drivePad;
	Joystick toolPad;
	JoystickButton startButtonTool;

	/**
	 * Initializes the joystick objects 
	 */
	public OI()
	{
		drivePad = new Joystick (GamepadConstants.DRIVE_USB_PORT);
		toolPad = new Joystick (GamepadConstants.TOOL_USB_PORT);
	}

	//***************************************************************************
	//**************************** DRIVER CONTROLLER ****************************
	//***************************************************************************

	/**
	 * Used to return the drivePad's right joystick y-axis value 
	 * 
	 * @return Returns y-value from right joystick on the drivePad
	 */
	public double getDriveRightY ()
	{
		double joy = drivePad.getRawAxis(GamepadConstants.RIGHT_ANALOG_Y);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	/**
	 * Used to return the drivePad's left joystick y-axis value 
	 * 
	 * @return Returns y-value from left joystick on the drivePad
	 */
	public double getDriveLeftY ()
	{
		double joy = drivePad.getRawAxis(GamepadConstants.LEFT_ANALOG_Y);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	/**
	 * Used to return the drivePad's right joystick x-axis value 
	 * 
	 * @return Returns x-value from right joystick on the drivePad
	 */
	public double getDriveRightX()
	{
		double joy = drivePad.getRawAxis(GamepadConstants.RIGHT_ANALOG_X);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	/**
	 * Used to return the drivePad's left joystick x-axis value 
	 * 
	 * @return Returns x-value from left joystick on the drivePad
	 */
	public double getDriveLeftX()
	{
		double joy = drivePad.getRawAxis(GamepadConstants.LEFT_ANALOG_X);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	public boolean getDriveDPadX() 
	{
		return drivePad.getRawButton(GamepadConstants.DPAD_X);
	}

	public boolean getDriveDPadY() 
	{
		return drivePad.getRawButton(GamepadConstants.DPAD_Y);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveRightTrigger()
	{
		return drivePad.getRawButton(GamepadConstants.RIGHT_TRIGGER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveLeftTrigger()
	{
		return drivePad.getRawButton(GamepadConstants.LEFT_TRIGGER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveRightBumper()
	{
		return drivePad.getRawButton(GamepadConstants.RIGHT_BUMPER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveLeftBumper()
	{
		return drivePad.getRawButton(GamepadConstants.LEFT_BUMPER);
	}    

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveXButton()
	{
		return drivePad.getRawButton(GamepadConstants.X_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveAButton()
	{
		return drivePad.getRawButton(GamepadConstants.A_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveBButton()
	{
		return drivePad.getRawButton(GamepadConstants.B_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveYButton()
	{
		return drivePad.getRawButton(GamepadConstants.Y_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveStartButton()
	{
		return drivePad.getRawButton(GamepadConstants.START_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveBackButton()
	{
		return drivePad.getRawButton(GamepadConstants.BACK_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveLeftAnalogButton()
	{
		return drivePad.getRawButton(GamepadConstants.LEFT_ANALOG_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getDriveRightAnalogButton()
	{
		return drivePad.getRawButton(GamepadConstants.RIGHT_ANALOG_BUTTON);
	}

	//***************************************************************************
	//****************************** TOOL CONTROLLER ****************************
	//***************************************************************************

	/**
	 * Used to return the toolPad's right joystick y-axis value 
	 * 
	 * @return Returns y-value from right joystick on the toolPad
	 */
	public double getToolRightY ()
	{
		double joy = toolPad.getRawAxis(GamepadConstants.RIGHT_ANALOG_Y);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	/**
	 * Used to return the toolPad's left joystick y-axis value 
	 * 
	 * @return Returns y-value from left joystick on the toolPad
	 */
	public double getToolLeftY ()
	{
		double joy = toolPad.getRawAxis(GamepadConstants.LEFT_ANALOG_Y);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	/**
	 * Used to return the toolPad's right joystick x-axis value 
	 * 
	 * @return Returns x-value from right joystick on the toolPad
	 */
	public double getToolRightX()
	{
		double joy = toolPad.getRawAxis(GamepadConstants.RIGHT_ANALOG_X);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	/**
	 * Used to return the toolPad's left joystick x-axis value 
	 * 
	 * @return Returns x-value from left joystick on the toolPad
	 */
	public double getToolLeftX()
	{
		double joy = toolPad.getRawAxis(GamepadConstants.LEFT_ANALOG_X);
		if(Math.abs(joy) < 0.05)
			return 0.0;
		else
			return joy;
	}

	public boolean getToolDPadX() 
	{
		return toolPad.getRawButton(GamepadConstants.DPAD_X);
	}

	public boolean getToolDPadY() 
	{
		return toolPad.getRawButton(GamepadConstants.DPAD_Y);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolRightTrigger()
	{
		return toolPad.getRawButton(GamepadConstants.RIGHT_TRIGGER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolLeftTrigger()
	{
		return toolPad.getRawButton(GamepadConstants.LEFT_TRIGGER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolRightBumper()
	{
		return toolPad.getRawButton(GamepadConstants.RIGHT_BUMPER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolLeftBumper()
	{
		return toolPad.getRawButton(GamepadConstants.LEFT_BUMPER);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolXButton()
	{
		return toolPad.getRawButton(GamepadConstants.X_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolAButton()
	{
		return toolPad.getRawButton(GamepadConstants.A_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolBButton()
	{
		return toolPad.getRawButton(GamepadConstants.B_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolYButton()
	{
		return toolPad.getRawButton(GamepadConstants.Y_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolStartButton()
	{
		return toolPad.getRawButton(GamepadConstants.START_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolBackButton()
	{
		return toolPad.getRawButton(GamepadConstants.BACK_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolLeftAnalogButton()
	{
		return toolPad.getRawButton(GamepadConstants.LEFT_ANALOG_BUTTON);
	}

	/** 
	 * @return Returns corresponding value (true or false) when button is pressed
	 */
	public boolean getToolRightAnalogButton()
	{
		return toolPad.getRawButton(GamepadConstants.RIGHT_ANALOG_BUTTON);
	}
}

