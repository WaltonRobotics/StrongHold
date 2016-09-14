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
public class Intake extends Subsystem {
	   Solenoid intakeExtender = RobotMap.intakeExtender;
	   double time;
	   IntakeExtenderState state;
	   
	   
	    public void initDefaultCommand() {
	    	setDefaultCommand(new MoveIntake());
	    }
	    
	    public Intake()
	    {
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
//	    	if(getState() == IntakeExtenderState.in)
//	    	{
//	    		intakeExtender.set(true);
//		    	state = IntakeExtenderState.out;
//		    	time = Timer.getFPGATimestamp();
//	    	}
	    	
	    }
	    
	    public void retract()
	    {
	    	intakeExtender.set(false);
//	    	if(getState() == IntakeExtenderState.out)
//	    	{
//		    	intakeExtender.set(false);
//		    	state = IntakeExtenderState.in;
//		    	time = Timer.getFPGATimestamp();
//	    	}

	    }
	    
	    public IntakeExtenderState getState()
	    {
	    	if(Timer.getFPGATimestamp()-time>.1)
	    		return state;
	    	else
	    		return IntakeExtenderState.middle;
	    }	
	    
}