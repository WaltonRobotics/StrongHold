package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.autonomousCommands.*;

public class Moat extends CommandGroup {
    public Moat() {
        addSequential(new ShiftDown());
        addSequential(new IntakeIn());
        addSequential(new TurnToAngle(180));
        // addSequential(new FlapDownTime(.2));

        addParallel(new ArmDown());
        addSequential(new DriveStraight(2, .7)); // Test this number
    }
}
