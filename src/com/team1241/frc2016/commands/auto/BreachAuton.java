package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.commands.defence.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to select which defence auto run depending on defence
 * selected
 */
public class BreachAuton extends CommandGroup {

	/**
	 * Instantiates a new breach auton.
	 *
	 * @param defence
	 *            Selected defence (See NumberConstants to find defence
	 *            constants)
	 */
	public BreachAuton(int defence) {
		// Cross the defence
		if (defence == NumberConstants.PORTCULLIS) {
			addSequential(new AutoPortcullis());
		} else if (defence == NumberConstants.CHEVAL) {
			addSequential(new AutoCheval());
		} else if (defence == NumberConstants.SALLYPORT) {
			addSequential(new AutoSallyPort());
		} else if (defence == NumberConstants.DRAWBRIDGE) {
			addSequential(new AutoDrawbridge());
		} else if (defence == NumberConstants.ROCKWALL) {
			addSequential(new AutoRockWall());
		} else if (defence == NumberConstants.ROUGHTERRAIN) {
			addSequential(new AutoRoughTerrain());
		}

		addSequential(new DriveCommand(10, 1.0, 0, 1));
	}
}