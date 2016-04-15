package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWallShoot extends CommandGroup {
    
    public  RockWallShoot() {
    	addSequential(new ShiftDown());
    	
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(4.7, -.7));
    	
    	addSequential(new Wait(.3));
    	addSequential(new TurnLeft());
		
    	addParallel(new FlapDownTime(1));
    	
		addSequential(new Aim(2,3));
		addSequential(new Aim(2,3));
		addSequential(new Aim(2,.5,true));
		
		addSequential(new Shoot());
    }
}
