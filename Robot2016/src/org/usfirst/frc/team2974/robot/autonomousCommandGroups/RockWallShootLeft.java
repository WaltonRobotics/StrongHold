package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.autonomousCommands.*;

/**
 *
 */
class RockWallShootLeft extends CommandGroup {

    public RockWallShootLeft() {

        // addSequential(new ShiftDown());
        //
        // addParallel(new ArmDown());
        // addSequential(new DriveStraight(4.77, -.7));
        //
        // addSequential(new Wait(.15));
        // //addSequential(new DriveStraight(.3, .5));
        // //addSequential(new Wait(.15));
        // addSequential(new TurnLeft());
        //
        // addParallel(new FlapDownTime(.3));
        //
        // addSequential(new Aim(0,6));
        // addSequential(new Aim(0,.5,true));
        //
        // addSequential(new Shoot());

        addSequential(new ShiftDown());

        addParallel(new ArmDown());
        addSequential(new DriveStraight(4.77, -.7));

        addSequential(new Wait(.1));
        addSequential(new TurnLeft(1.95, .5));

        addSequential(new Wait(.1));
        addSequential(new TurnRightUntilFind(2, .2));

        addParallel(new FlapDownTime(.3));

        addSequential(new Aim(0, 4));
        addSequential(new Aim(0, .5, true));

        addSequential(new Shoot());
    }
}
