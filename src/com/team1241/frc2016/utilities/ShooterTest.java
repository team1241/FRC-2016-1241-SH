package com.team1241.frc2016.utilities;

import java.util.Arrays;

import com.team1241.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Class used to automatically calculate feed forward values for shooter
 * velocity controller.
 * 
 * @author Bryan
 */
public class ShooterTest extends Command {

	/** The static RPM. */
	private static int[] staticRPM = new int[] { 4000, 4100, 4200, 4500, 5000 };

	/** The power. */
	private static double[] power = new double[staticRPM.length];

	/** The rpm. */
	private static double rpm = 0;

	/** The current. */
	private static double current = 0;

	/** The state. */
	private static int state = 0;

	/** The timer. */
	private static Timer timer = new Timer();

	/** The target. */
	private static Timer target = new Timer();

	/** The at target. */
	private static boolean atTarget = false;

	/** The l R. */
	private LineRegression lR = new LineRegression();

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wpi.first.wpilibj.command.Command#initialize()
	 */
	@Override
	protected void initialize() {
		rpm = 0.62;
		state = 0;
		atTarget = false;
		timer.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wpi.first.wpilibj.command.Command#execute()
	 */
	@Override
	protected void execute() {
		rpm = Math.round(rpm * 10000.0) / 10000.0;
		current = Robot.shooter.getRPM();
		Robot.shooter.setSpeed(rpm);
		if (atTarget) {
			if (target.get() == 0) {
				target.start();
				System.out.println("started");
			}

			current = Robot.shooter.getRPM();
			if (Math.abs(staticRPM[state] - current) > 50) {
				atTarget = true;
			} else if (target.get() > 0.4) {
				System.out.println("finished: " + rpm);
				power[state] = rpm;
				state++;
				atTarget = false;
			}
		} else if (timer.get() > 0.3) {
			target.stop();
			target.reset();
			System.out.println("Sensor:" + current + " power:" + rpm);

			current = Robot.shooter.getRPM();
			if (Math.abs(staticRPM[state] - current) < 50) {
				atTarget = true;
			} else if (staticRPM[state] > current) {
				if (Math.abs(staticRPM[state] - current) > 200)
					rpm = rpm + 0.01;
				else
					rpm = rpm + 0.0025;
			} else if (staticRPM[state] < current) {
				if (Math.abs(staticRPM[state] - current) > 200)
					rpm = rpm + 0.01;
				else
					rpm = rpm + 0.0025;
			}
			timer.reset();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wpi.first.wpilibj.command.Command#isFinished()
	 */
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return state == staticRPM.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wpi.first.wpilibj.command.Command#end()
	 */
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setSpeed(0);
		timer.stop();
		System.out.println(Arrays.toString(power));

		lR.setValues(staticRPM, power);
		System.out.println("Slope: " + lR.getSlope() + "intercept: " + lR.getIntercept());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wpi.first.wpilibj.command.Command#interrupted()
	 */
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooter.setSpeed(0);
		timer.stop();
		System.out.println(Arrays.toString(power));
	}

}
