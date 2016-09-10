package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeIn;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChivalDeFreze extends CommandGroup{
    public ChivalDeFreze() {
    	addSequential(new ShiftDown());   	
    	addSequential(new IntakeIn());
    	
    	addSequential(new FlapDownTime(.2));
    	
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(5, -.7)); //Test this number
    }
}
