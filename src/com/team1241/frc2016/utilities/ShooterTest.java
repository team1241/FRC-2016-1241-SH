package com.team1241.frc2016.utilities;

import java.util.Arrays;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterTest extends Command {
	private static int[] staticRPM = new int[]{4000, 4100, 4200, 4500, 5000};
	private static double[] power = new double[staticRPM.length];
	
	private static double rpm = 0;
	private static double current = 0;
	private static int state = 0;
	private static Timer timer = new Timer();
	private static boolean atTarget = false;
	
	@Override
	protected void initialize() {
		rpm = 0.62;
		timer.start();
	}	

	@Override
	protected void execute() {
		current = Robot.shooter.getRPM();
		Robot.shooter.setSpeed(rpm);
		if (timer.get()>0.5) {
			System.out.println("Sensor:" + current + " power:" + rpm);
			if(Math.abs(staticRPM[state]-current) <50) {
				power[state] = rpm;
				state++;
			}
			else if(staticRPM[state]>current) {
				rpm = rpm+0.05;
			}
			else if(staticRPM[state]<current) {
				rpm = rpm-0.05;
			}
			timer.reset();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return state==staticRPM.length;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setSpeed(0);
		timer.stop();
		System.out.println(Arrays.toString(power));
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooter.setSpeed(0);
		timer.stop();
		System.out.println(Arrays.toString(power));
	}

}
