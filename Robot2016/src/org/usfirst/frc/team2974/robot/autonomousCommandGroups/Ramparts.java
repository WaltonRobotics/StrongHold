package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.autonomousCommands.*;

/**
 *
 */
public class Ramparts extends CommandGroup {

    public Ramparts() {
        addSequential(new ShiftDown());
        addSequential(new IntakeIn());

        addSequential(new FlapDownTime(.2));

        addParallel(new ArmDown());
        addSequential(new DriveStraight(5, -.7)); // Test this number
    }
}
