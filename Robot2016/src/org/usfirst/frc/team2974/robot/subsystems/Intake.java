package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.IntakeBall;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
	   Talon intakeMotor = RobotMap.intakeMotor;
	   
	   double power = .5;

	    public void initDefaultCommand() {
	    	setDefaultCommand(new IntakeBall());
	    }
	    
	    public Intake()
	    {
	    	SmartDashboard.putNumber("IntakeSpeed", power);
	    }
	    public void input(){
	    	double power = SmartDashboard.getNumber("IntakeSpeed");
	    	intakeMotor.set(power);
	    }
	    public void output(){
	    	double power = SmartDashboard.getNumber("IntakeSpeed");
	    	intakeMotor.set(-1*power);
	    }
	    public void stop(){
	    	intakeMotor.set(0);
	    }
	    
	   
	    
	    public void setMotor(double value)
	    {
	    	intakeMotor.set(value);
	    }
}

