package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.AutonPossibleLocation;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
import org.usfirst.frc.team2974.robot.autonomousCommands.MoveToObstacle;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ActivateAuton extends CommandGroup {
	public ActivateAuton(CommandGroup obstacleCommand, AutonLocator location) {
		Robot.compass.zeroRobot();
		
		if (obstacleCommand.getName().equals("RockWall")){
			addSequential(new TurnToAngle(180));
			addSequential(new MoveToObstacle(-1));
		}else{
			addSequential(new DriveStraightMC(2.0, 0.5, 0.1));//testing instead of move to obstical
		}

		if (location.getAutonPossibleLocation().equals(AutonPossibleLocation.A)){
			addSequential(new LowBar());
		}else{
			addSequential(obstacleCommand);
		}
		
		addSequential(new MoveToShoot(location));
		addSequential(new Shoot());
	}
}
