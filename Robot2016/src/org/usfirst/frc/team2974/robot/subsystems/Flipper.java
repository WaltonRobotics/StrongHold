package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveFlipper;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flipper extends Subsystem {
    

	Solenoid flipper = RobotMap.flipper;
	double time;
	FlipperState state;
	
	public Flipper()
	{
		time = Timer.getFPGATimestamp();
		state = FlipperState.up;
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new MoveFlipper());
    }
    public enum FlipperState
    {
    	up, down, middle
    }
    
    public FlipperState getFlipperState()
    {
    	if(Timer.getFPGATimestamp()-time>.4)
    		return state;
    	else
    		return FlipperState.middle;
    }
    
    public void setFlipper(FlipperState state)
    {
    	switch(state)
    	{
    	case up:
    		flipper.set(false);
    		if(state == FlipperState.down)
    		{
    			time = Timer.getFPGATimestamp();
    			state = FlipperState.down;
    		}
    		break;
    	case down:
    		flipper.set(true);
    		if(state == FlipperState.up)
    		{
    			time = Timer.getFPGATimestamp();
    			state = FlipperState.up;
    		}
    	}
    }
}

