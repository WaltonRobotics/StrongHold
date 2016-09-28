package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {

	public LowBar() {
		addSequential(new ShiftDown());   
		addSequential(new DriveStraight(2.15,-.5));
//		addSequential(new TurnLeft());
//		addSequential(new Aim());
//		addSequential(new DriveStraight(2, .5));
//		addSequential(new Shoot());
	}
}
