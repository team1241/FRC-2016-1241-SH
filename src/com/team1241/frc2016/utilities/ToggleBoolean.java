package com.team1241.frc2016.utilities;

import edu.wpi.first.wpilibj.Timer;

public class ToggleBoolean {

	private boolean isHeld = false;
	private boolean toggle = false;
	private boolean waited = true;
	
	Timer timer = new Timer();
	
	public ToggleBoolean(){
	}
	
	public void set(boolean state){
		if(timer.get() > 0.5){
			waited = true;
			timer.stop();
		}
		
		if(state && waited){
			toggle = !toggle;
			waited = false;
			timer.start();
		}
	}
	
	public boolean get(){
		return toggle;
	}
}
