package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

public class FullRunnableAuton extends CommandGroup {
    public FullRunnableAuton(CommandGroup obstacleCommand, AutonLocator location) {
        if (obstacleCommand.getName().equals("RockWall")) {
            addSequential(new TurnToAngle(180));
        }
        addSequential(obstacleCommand);
        addSequential(new MoveToShoot(location));
        addSequential(new Shoot());
    }
}
