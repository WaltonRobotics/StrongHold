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
	   Solenoid intakeSolenoid = RobotMap.intakeSolenoid;

	    public void initDefaultCommand() {
	    	setDefaultCommand(new LoadBall());
	    }
	    public void input(){
	    	intakeMotor.set(1);
	    }
	    public void output(){
	    	intakeMotor.set(-1);
	    }
	    public void stop(){
	    	intakeMotor.set(0);
	    }
	    
	    public enum IntakeState
	    {
	    	up, down
	    }
	    
	    public void setIntakeSolenoid(IntakeState state)
	    {
	    	switch(state)
	    	{
	    	case up:
	    		intakeSolenoid.set(true);
	    		break;
	    	case down:
	    		intakeSolenoid.set(false);
	    	}
	    }
	    
	    public void setMotor(double value)
	    {
	    	intakeMotor.set(value);
	    }
}

