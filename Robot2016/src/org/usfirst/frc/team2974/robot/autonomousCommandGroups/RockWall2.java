package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWall2 extends CommandGroup {
    
    public  RockWall2() {
addSequential(new ShiftDown());
    	
    	addParallel(new ArmDown());
    	addParallel(new IntakeOut());
    	addSequential(new DriveStraight(4.77, -.7));
    	
		addSequential(new Wait(.1));
		addSequential(new TurnLeft(1.6,.5));
    	
    	addSequential(new Wait(.1));
    	//addSequential(new TurnToAngle(5, true));
    }
}
