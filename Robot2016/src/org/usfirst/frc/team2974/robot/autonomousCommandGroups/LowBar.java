package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
