package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.autonomousCommands.Aim;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
    
    public  LowBar() {
         addSequential(new DriveStraight());
         addSequential(new TurnRight());
         addSequential(new Aim());
         addSequential(new Shoot());
    }
}
