package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
//import org.usfirst.frc.team2974.robot.commands.LoadBall;
import org.usfirst.frc.team2974.robot.commands.MoveIntake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeExtender extends Subsystem {
	   Solenoid intakeExtender = RobotMap.intakeExtender;
	   double time;
	   IntakeExtenderState state;
	   
	    public void initDefaultCommand() {
	    	setDefaultCommand(new MoveIntake());
	    }
	    
	    public IntakeExtender()
	    {
	    	intakeExtender.set(false);
	    	state = IntakeExtenderState.in;
	    	time = Timer.getFPGATimestamp();
	    }
	  
	    
	    public enum IntakeExtenderState
	    {
	    	in, out, middle
	    }
	    
	    public void extend()
	    {
	    	intakeExtender.set(true);
	    	if(state == IntakeExtenderState.in)
	    	{
		    	state = IntakeExtenderState.out;
		    	time = Timer.getFPGATimestamp();
	    	}
	    	
	    }
	    
	    public void retract()
	    {
	    	intakeExtender.set(false);
	    	if(state == IntakeExtenderState.out)
	    	{
		    	state = IntakeExtenderState.in;
		    	time = Timer.getFPGATimestamp();
	    	}

	    }
	    
	    public IntakeExtenderState getState()
	    {
	    	if(Timer.getFPGATimestamp()-time>.2)
	    		return state;
	    	else
	    		return IntakeExtenderState.middle;
	    }	
	    
}