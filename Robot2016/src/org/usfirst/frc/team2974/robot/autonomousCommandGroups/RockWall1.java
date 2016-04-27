package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveObstacle;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.ShiftDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;
import org.usfirst.frc.team2974.robot.commands.AimOnboard;
import org.usfirst.frc.team2974.robot.commands.ControlAim.aimState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWall1 extends CommandGroup {
    
    public  RockWall1() {
    	addParallel(new ArmDown());
    	addSequential(new ShiftDown());
    	//addSequential(new DriveStraight(.7, -.3));
    	addParallel(new IntakeOut());
    	addSequential(new Wait(.1));
    	addSequential(new DriveObstacle());
    	addSequential(new Wait(.1));
    	addSequential(new TurnToAngle(220, false));
    	addSequential(new Wait(.1));
    	addSequential(new AimOnboard(0));
    	addSequential(new Shoot());
    	
    	
    }
}
