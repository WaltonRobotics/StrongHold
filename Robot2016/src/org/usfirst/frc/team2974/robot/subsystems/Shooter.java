package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Shoot;
import org.usfirst.frc.team2974.robot.commands.Shoot.ShooterState;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	Solenoid latch = RobotMap.latch;
	CANTalon tensioner = RobotMap.tensioner;
	Encoder encoder = RobotMap.encoderShooter;
	private final double maxTensionerPower = 0.25;
	private final double holdTensionerPower = .125;
	private TensionerState state; 
	private final double ForwardThreshold = .1;
	private final double ReverseThreshold = .1;
	private final double ForwardLimit = 10;
	private final double ReverseLimit = 30;
	
	public Shooter()
	{
		state = TensionerState.untensioned;
		encoder.reset();
	}
	public enum TensionerState{
		tensioned, untensioned, tensioning, untensioning
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Shoot(ShooterState.returning));
    }
    
    public void latch(){
    	latch.set(true);
    }
    public void unlatch(){
    	latch.set(false);
    }
//    public void reset(double on){
//    	reset.set(on);	
//    }
    public void tension(){
    	tensioner.set(maxTensionerPower);
    	state = TensionerState.tensioning;
    }
    public void unload(){
    	tensioner.set(- maxTensionerPower);
    	state = TensionerState.untensioning;
    }
    public void setZero(){
    	tensioner.set(0);
    }
    
    public void atTensionLimit(){
    	tensioner.set(holdTensionerPower);
    }
    public void atUnloadLimit(){
    	setZero();
    }
    public TensionerState getState(){
    	if(encoder.get() + ForwardThreshold > ForwardLimit){
    		state = TensionerState.tensioned;
    	}
    	if(encoder.get() + ReverseThreshold > ReverseLimit){
    		state = TensionerState.untensioned;
    	}
    	return state;
    }
}

