package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveFlapper;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flapper extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid flapper = RobotMap.flapper;
    public void initDefaultCommand() {
        setDefaultCommand(new MoveFlapper());
    }
    public enum FlapperState
    {
    	up, down
    }
    
    public void setFlapper(FlapperState state)
    {
    	switch(state)
    	{
    	case up:
    		flapper.set(false);
    		break;
    	case down:
    		flapper.set(true);
    	}
    }
}

