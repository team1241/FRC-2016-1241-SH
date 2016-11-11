package com.team1241.frc2016.utilities;

import edu.wpi.first.wpilibj.Timer;

/**
 * Class used for toggling between two state, mainly created due to Logitech
 * F310 buttons bouncing when pressed
 * 
 * @author Mahrus Kazi
 */
public class ToggleBoolean {

	// booleans to remember states
	private boolean isHeld = false;
	private boolean toggle = false;
	private boolean waited = true;

	// Timer used
	Timer timer = new Timer();

	/**
	 * Instantiates a new toggle boolean.
	 */
	public ToggleBoolean() {
	}

	/**
	 * Sets the new state for the toggle boolean.
	 *
	 * @param state
	 *            The new state for the toggle boolean (Will only change if
	 *            TRUE)
	 */
	public void set(boolean state) {
		// Must be true for more than half a second to change state
		if (timer.get() > 0.5) {
			waited = true;
			timer.stop();
		}

		if (state && waited) {
			toggle = !toggle;
			waited = false;
			timer.start();
		}
	}

	/**
	 * @return Returns the state of the toggle boolean.
	 */
	public boolean get() {
		return toggle;
	}
}
