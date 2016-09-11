package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.autonomousCommands.*;

/**
 *
 */
public class LowBar extends CommandGroup {

    public LowBar() {
        addParallel(new ArmDown());
        addSequential(new DriveStraight(4, -.5));
        addSequential(new TurnLeft());
        addSequential(new Aim());
        addSequential(new DriveStraight(2, .5));
        addSequential(new Shoot());
    }
}
