package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.logging.Log;
import org.usfirst.frc.team2974.logging.LogMessage;
import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {
	
	private State currentState;
	private Shooter shooter = Robot.shooter;
		
    public Shoot() {
         requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	currentState = new Latched();
    }
    abstract class State{
    	boolean init = false;
    	abstract void init();
    	abstract void execute();
    	abstract void end();
    	abstract boolean isFinished();
    }
    class Latched extends State{
    	void init(){}
		void execute() {
			shooter.tension();
		}
		void end() {
			currentState = new Ready();
		}
		boolean isFinished() {
			return shooter.getState() == Shooter.TensionerState.tensioned;
		}
    }
    class Returning extends State{
    	void init(){}
		void execute() {
			shooter.unTension();
		}
		void end() {
			currentState = new Latched();
		}
		boolean isFinished() {
			return shooter.isShooterDown();	
		}
    }
    class Ready extends State{
    	void init(){}
		void execute() {
			shooter.atTensionLimit();
		}
		void end() {
			currentState = new Shooting();
		}
		boolean isFinished() {
			return Robot.oi.shoot.get();
		}
    }
    class Shooting extends State{
    	double initTime;
    	void init(){
			shooter.unlatch();
    		initTime = Timer.getFPGATimestamp();
    	}
		void execute(){}
		void end() {
			currentState = new Returning();
		}
		boolean isFinished() {
			return !Robot.oi.shoot.get() && Timer.getFPGATimestamp() - initTime> 1;
		}
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(Robot.oi.gamepad.getRightY()>.1)shooter.tension();
//    	switch(currentState){
//    	case latched:
//    		if (!forwardLimit.get()){
//    		shooter.tension();
//    		}else{
//    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
//    					"Catapult hit forwardLimit before reaching desired tension in case latched", 63));
//    		}
//    		if(shooter.getState() == TensionerState.tensioned){
//    			currentState = ShooterState.readyToAim;
//    		}
//    		break;
//    	case readyToAim:
//    		if (!forwardLimit.get()){
//    			shooter.atTensionLimit();
//    		}else{
//    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
//    					"Catapult hit forwardLimit before reaching desired tension in case readyToAim", 74));
//    		}
//    		if(Robot.oi.aim.get())
//    		{
//    			new Aim();
//    		}
//    		break;
//    	case readyToShoot:
//    		if (!forwardLimit.get()){
//    			shooter.atTensionLimit();
//    		}else{
//    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
//    					"Catapult hit forwardLimit before reaching desired tension in case readyToShoot", 87));
//    		}
//    		if(Robot.oi.shoot.get())
//    		{
//    			shooter.unlatch();
//    			currentState = ShooterState.returning;
//    		}
//    		break;
//    	case returning:
//    		if (!backwardLimit.get()){
//    			shooter.unload();	
//    		}else{
//    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
//    					"Catapult hit backwardLimit before reaching minimum tension in case returning", 99));
//    		}
//    		if(shooter.getState() == TensionerState.untensioned)
//    		{
//    			shooter.latch();
//    			currentState = ShooterState.latched;
//    		}
//    		break;
//    	}
    	if(!currentState.init)
    	{
    		currentState.init();
    		currentState.init = true;
    	}else if(currentState.isFinished())
        		currentState.end();
    	else currentState.execute();
    	
    	SmartDashboard.putNumber("tensioner", RobotMap.tensioner.getAnalogInPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
