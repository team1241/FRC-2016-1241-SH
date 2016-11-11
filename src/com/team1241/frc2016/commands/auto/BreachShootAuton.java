package com.team1241.frc2016.commands.auto;

import com.team1241.frc2016.NumberConstants;
import com.team1241.frc2016.Robot;
import com.team1241.frc2016.commands.CameraTrack;
import com.team1241.frc2016.commands.SetShooterSpeed;
import com.team1241.frc2016.commands.TurnTurret;
import com.team1241.frc2016.commands.defence.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group used to select which auton to cross and shoot after crossing.
 */
public class BreachShootAuton extends CommandGroup {

	/**
	 * Instantiates a new breach shoot auton.
	 *
	 * @param defenceLoc
	 *            The defence location (Position 2 - 5)
	 * @param defence
	 *            The defence type
	 * @param endLocation
	 *            The end location
	 */
	public BreachShootAuton(int defenceLoc, int defence, int endLocation) {
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
		} else if (defence == NumberConstants.MOAT) {
			addSequential(new AutoMoat());
		}

		// Drive curve towards the tower
		addSequential(new AutoCourtyard(defenceLoc, endLocation));
	}
}