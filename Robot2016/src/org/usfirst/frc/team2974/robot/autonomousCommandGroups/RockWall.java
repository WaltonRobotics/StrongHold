package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;

/**
 *
 */
public class RockWall extends CommandGroup {

	public RockWall() {
		addSequential(new ShiftDown());
		addSequential(new IntakeOut());

		addSequential(new FlapDownTime(.2));

		addParallel(new ArmDown());
		addSequential(new DriveStraight(5, -.7));


	}
}
