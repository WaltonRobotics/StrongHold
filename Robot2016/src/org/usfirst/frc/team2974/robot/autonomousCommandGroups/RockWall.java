package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWall extends CommandGroup {
    
    public  RockWall() {
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(4.7, -.7));
    }
}
