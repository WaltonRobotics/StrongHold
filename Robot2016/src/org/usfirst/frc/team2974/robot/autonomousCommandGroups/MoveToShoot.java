package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveDistance;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

class MoveToShoot extends CommandGroup {
	private final double speed = 0.7;

	public MoveToShoot(AutonLocator location) {
		addSequential(new DriveLocate());
		addSequential(new TurnToAngle(location.getAngle(location.getLocation(), location.getLocationShoot())));
		// Test with different speeds
		addSequential(
				new DriveDistance(speed, location.getDistance(location.getLocation(), location.getLocationShoot())));
		addSequential(new TurnToAngle(location.getAngle(location.getLocationShoot(), location.getLocationGoal())));
	}
}
