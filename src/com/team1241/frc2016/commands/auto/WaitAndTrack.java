package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.commands.CameraTrack;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to add a wait before tracking
 */
public class WaitAndTrack extends CommandGroup {

	/**
	 * Instantiates a new wait and track.
	 *
	 * @param waitTime
	 *            the wait time
	 */
	public WaitAndTrack(double waitTime) {
		addSequential(new WaitCommand(waitTime));
		addSequential(new CameraTrack(1.5));
	}
}
