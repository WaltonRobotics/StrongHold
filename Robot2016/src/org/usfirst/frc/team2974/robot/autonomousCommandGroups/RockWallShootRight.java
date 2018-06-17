package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnRightUntilFind;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

/**
 *
 */
public class RockWallShootRight extends CommandGroup {

	public RockWallShootRight() {
		addSequential(new ShiftDown());

		addParallel(new ArmDown());
		addSequential(new DriveStraight(4.77, -.7));

		addSequential(new Wait(.1));
		addSequential(new TurnLeft(1.95, .5));

		addSequential(new Wait(.1));
		addSequential(new TurnRightUntilFind(2, .2));

		addParallel(new FlapDownTime(.3));

		addSequential(new Aim(2, 4));
		addSequential(new Aim(2, .5, true));

		addSequential(new Shoot());
	}
}
