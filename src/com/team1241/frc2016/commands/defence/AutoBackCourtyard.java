package com.team1241.frc2016.commands.defence;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.auto.*;
import com.team1241.frc2016.utilities.Point;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group uses DrivePath to traverse court yard, depending on the start
 * and end location
 */
public class AutoBackCourtyard extends CommandGroup {

	/**
	 * Instantiates a new auto back courtyard.
	 *
	 * @param startLocation
	 *            The start location
	 * @param endLocation
	 *            The end location
	 */
	public AutoBackCourtyard(int startLocation, int endLocation) {
		if (startLocation == NumberConstants.CENTER) {
			if (endLocation == 1) {
				addSequential(new DrivePath(new Point(0, 0), new Point(50, 75), new Point(93, 0), new Point(93, 118),
						3.5, 0.5, true));
			} else if (endLocation == 2) {
				addSequential(new DrivePath(new Point(0, 0), new Point(22.6, 40), new Point(36, 0), new Point(36, 122),
						3.5, 0.5, true));
			} else if (endLocation == 3) {
				addSequential(new DrivePath(new Point(0, 0), new Point(50, 75), new Point(93, 0), new Point(93, 118),
						3.5, 0.5, true));
			} else if (endLocation == 4) {
				addSequential(new DrivePath(new Point(0, 0), new Point(50, 75), new Point(93, 0), new Point(93, 118),
						3.5, 0.5, true));
			}
		}
	}
}
