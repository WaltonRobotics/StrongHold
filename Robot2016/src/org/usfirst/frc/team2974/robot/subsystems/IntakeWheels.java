package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.LoadBall;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeWheels extends Subsystem {
	   Talon intakeMotor = RobotMap.intakeMotor;

	   
	   private double speed = .5;
	   
	    public void initDefaultCommand() {
	    	setDefaultCommand(new LoadBall());
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
	  
	    public void setMotor(double value)
	    {
	    	intakeMotor.set(value);
	    }
}