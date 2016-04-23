package org.usfirst.frc.team2974.robot.autonomousCommandGroups;



import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnRightUntilFind;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
    
    public  LowBar() {
    	
//    	addSequential(new ShiftDown());
    	
    	//1.6 = 180
    	//1.95 more than 180
    	//1.25 less than 180
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
