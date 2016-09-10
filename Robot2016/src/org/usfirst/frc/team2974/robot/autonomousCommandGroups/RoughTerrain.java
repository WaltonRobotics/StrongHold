package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeIn;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoughTerrain extends CommandGroup {
    
    public  RoughTerrain() {
    	addSequential(new ShiftDown());   	
    	addSequential(new IntakeIn());
    	
    	//addSequential(new FlapDownTime(.2));
    	
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(4,.5)); //Test this number
    }
}
