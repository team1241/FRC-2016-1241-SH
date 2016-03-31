package com.team1241.frc2016.utilities;

import java.util.Arrays;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterTest extends Command {
	private static int[] staticRPM = new int[]{4000, 4100, 4200, 4500, 5000};
	private static double[] power = new double[staticRPM.length];
	
	private static double rpm = 0;
	private static double current = 0;
	private static int state = 0;
	private static int timer = 0;
	
	@Override
	protected void initialize() {
		requires(Robot.shooter);
		rpm = 0.75;
	}	

	@Override
	protected void execute() {
		timer++;
		current = Robot.shooter.getRPM();
		Robot.shooter.setSpeed(rpm);
		if (timer>10) {
			if(Math.abs(staticRPM[state]-current) <50) {
				power[state] = rpm;
				state++;
			}
			else if(staticRPM[state]>current) {
				rpm = rpm+0.1;
			}
			else if(staticRPM[state]<current) {
				rpm = rpm-0.1;
			}
			timer = 0;
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
		System.out.println(Arrays.toString(power));
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooter.setSpeed(0);
		System.out.println(Arrays.toString(power));
	}

}
