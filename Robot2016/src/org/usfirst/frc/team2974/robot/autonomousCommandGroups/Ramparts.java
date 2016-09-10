package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeIn;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Ramparts extends CommandGroup {

	public Ramparts() {
    	addSequential(new ShiftDown());   	
    	addSequential(new IntakeIn());
    	
    	addSequential(new FlapDownTime(.2));
    	
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(5, -.7)); //Test this number
	}
}
