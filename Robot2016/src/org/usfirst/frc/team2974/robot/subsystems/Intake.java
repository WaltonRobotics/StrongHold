package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.LoadBall;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	   Talon intakeMotor = RobotMap.intakeMotor;
	   Solenoid intakeExtender = RobotMap.intakeExtender;
	   
	   IntakeExtenderState state;
	   
	   private double speed = .5;
	   
	    public void initDefaultCommand() {
	    	setDefaultCommand(new LoadBall());
	    }
	    
	    public Intake()
	    {
	    	state = IntakeExtenderState.in;
	    }
	    
	    public void input(){
	    	intakeMotor.set(speed);
	    }
	    public void output(){
	    	intakeMotor.set(-1 * speed);
	    }
	    public void stop(){
	    	intakeMotor.set(0);
	    }
	    
	    public enum IntakeExtenderState
	    {
	    	in, out
	    }
	    
	    public void extend()
	    {
	    	intakeExtender.set(true);
	    	state = IntakeExtenderState.out;
	    }
	    
	    public void retract()
	    {
	    	intakeExtender.set(false);
	    	state = IntakeExtenderState.in;
	    }
	    
	    public IntakeExtenderState getState()
	    {
	    	return state;
	    }
	    
	    public void setMotor(double value)
	    {
	    	intakeMotor.set(value);
	    }
}