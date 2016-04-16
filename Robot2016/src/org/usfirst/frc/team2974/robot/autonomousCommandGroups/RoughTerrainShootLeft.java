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

/**
 * dont run until uncomment line 23 !!!!!!!!!!!!!!!!!!!
 */
public class RoughTerrainShootLeft extends CommandGroup {
    
    public  RoughTerrainShootLeft() {
    	addSequential(new ShiftDown());
    	
    	addParallel(new ArmDown());
		addSequential(new DriveStraight(4,-.7));
		
//		addSequential(new Wait(.15));
//		addSequential(new TurnLeft(1.6,.5));

//		addSequential(new TurnLeft());
		
		addSequential(new Wait(.1));
    	addSequential(new TurnLeft(1.95,.5));
    	
    	addSequential(new Wait(.1));
    	addSequential(new TurnRightUntilFind(2, .2));
		
		addParallel(new FlapDownTime(.3));
		
		addSequential(new Aim(0,4));
		addSequential(new Aim(0,.5,true));
		
		addSequential(new Shoot());
    }
}
