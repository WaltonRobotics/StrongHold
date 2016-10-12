package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
//import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Moat extends CommandGroup{
    public  Moat() {
    	addSequential(new ShiftDown());   	
    	//addSequential(new IntakeOut());
    	
    	//addSequential(new FlapDownTime(.2));
    	addParallel(new ArmDown());
    	addSequential(new DriveStraight(4,-.5)); //Test this number
    }
}
