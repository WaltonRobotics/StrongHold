package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Ramparts extends CommandGroup {

	public Ramparts() {
    	addSequential(new ShiftDown());   	
    	addSequential(new IntakeOut());
    	
    	addSequential(new FlapDownTime(.2));
    	
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(5, -.7)); //Test this number
	}
}
