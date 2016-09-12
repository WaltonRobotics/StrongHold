package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeIn;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
