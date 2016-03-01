package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.logging.Log;
import org.usfirst.frc.team2974.logging.LogMessage;
import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;
import org.usfirst.frc.team2974.robot.subsystems.Shooter.TensionerState;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	
	//boolean canGoDown = true;
	//boolean canGoUp = true;
	
	private ShooterState currentState;
	private Shooter shooter = Robot.shooter;
	
	private DigitalInput forwardLimit = RobotMap.forwardLimit;
	private DigitalInput backwardLimit = RobotMap.backwardLimit;
	
    public Shoot(ShooterState state) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.shooter);
         currentState = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }	
    public enum ShooterState
    {
    	latched, readyToAim, returning, readyToShoot
    }
//    abstract class State{
//    	abstract void init();
//    	abstract void execute();
//    	abstract void end();
//    	abstract boolean isFinished();
//    }
//    class Latched extends State{
//    	void init(){
//    		
//    	}
//    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(Robot.oi.gamepad.getRightY()>.1)shooter.tension();
    	switch(currentState){
    	case latched:
    		if (!forwardLimit.get()){
    		shooter.tension();
    		}else{
    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
    					"Catapult hit forwardLimit before reaching desired tension in case latched", 63));
    		}
    		if(shooter.getState() == TensionerState.tensioned){
    			currentState = ShooterState.readyToAim;
    		}
    		break;
    	case readyToAim:
    		if (!forwardLimit.get()){
    			shooter.atTensionLimit();
    		}else{
    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
    					"Catapult hit forwardLimit before reaching desired tension in case readyToAim", 74));
    		}
    		if(Robot.oi.aim.get())
    		{
    			new Aim();
    		}
    		break;
    	case readyToShoot:
    		if (!forwardLimit.get()){
    			shooter.atTensionLimit();
    		}else{
    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
    					"Catapult hit forwardLimit before reaching desired tension in case readyToShoot", 87));
    		}
    		if(Robot.oi.shoot.get())
    		{
    			shooter.unlatch();
    			currentState = ShooterState.returning;
    		}
    		break;
    	case returning:
    		if (!backwardLimit.get()){
    			shooter.unload();	
    		}else{
    			Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.SHOOTER, "Shoot.execute",
    					"Catapult hit backwardLimit before reaching minimum tension in case returning", 99));
    		}
    		if(shooter.getState() == TensionerState.untensioned)
    		{
    			shooter.latch();
    			currentState = ShooterState.latched;
    		}
    		break;
    	}
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
