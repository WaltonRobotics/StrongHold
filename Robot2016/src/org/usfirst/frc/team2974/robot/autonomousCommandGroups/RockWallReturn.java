package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWallReturn extends CommandGroup {
    
    public  RockWallReturn() {
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(4.77, -.7));
    	addSequential(new Wait(2));
    	addSequential(new DriveStraight(3,.7));
    }
}
