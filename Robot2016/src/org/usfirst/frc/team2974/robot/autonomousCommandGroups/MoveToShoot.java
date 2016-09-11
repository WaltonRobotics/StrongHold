package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveDistance;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

class MoveToShoot extends CommandGroup {
    public MoveToShoot(AutonLocator location) {
        addSequential(new DriveLocate());
        addSequential(new DriveStraight(.5, 3));// get actual numbers
        addSequential(new TurnToAngle(location.getAngle(location.getLocation(), location.getLocationShoot())));
        // Test with different speeds
        addSequential(new DriveDistance(30, location.getDistance(location.getLocation(), location.getLocationShoot())));
        addSequential(new TurnToAngle(location.getAngle(location.getLocationShoot(), location.getLocationGoal())));
    }
}
