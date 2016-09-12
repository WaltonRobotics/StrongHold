package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.AutonPossibleLocation;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullRunnableAuton extends CommandGroup {
	public FullRunnableAuton(CommandGroup obstacleCommand, AutonLocator location) {
		if (obstacleCommand.getName().equals("RockWall"))
			addSequential(new TurnToAngle(180));

		if (location.getAutonPossibleLocation().equals(AutonPossibleLocation.A))
			addSequential(new LowBar());

		addSequential(obstacleCommand);
		addSequential(new MoveToShoot(location));
		addSequential(new Shoot());
	}
}