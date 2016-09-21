package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnRightUntilFind;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
