package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.AutonPossibleLocation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullRunnableAuton extends CommandGroup{
	public FullRunnableAuton(CommandGroup obstacleCommand, AutonLocator location){
		addSequential(obstacleCommand);
		addSequential(new MoveToShoot(location));
	}
}
