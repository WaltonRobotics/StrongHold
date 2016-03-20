package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight2;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootSequence extends CommandGroup {
    
    public  ShootSequence() {
		addSequential(new TurnLeft());
		addSequential(new Aim());
		addSequential(new DriveStraight2(2, .5));
		addSequential(new Shoot());
    }
}
