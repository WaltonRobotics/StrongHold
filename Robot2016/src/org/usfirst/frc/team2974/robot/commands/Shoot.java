package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;
import org.usfirst.frc.team2974.robot.subsystems.Shooter.TensionerState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	private state currentState;
	private Shooter shooter = Robot.shooter;
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	while(shooter.getState() != TensionerState.tensioned){
    		shooter.unload();
    	}
    	shooter.latch();
    	currentState = state.latched;
    }
    	
    private enum state
    {
    	latched, ready, shot, returning
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(currentState){
    	case latched: 
    		shooter.tension();
    		if(shooter.getState() == TensionerState.tensioned)
    			currentState = state.ready;
    		break;
    	case ready:
    		if(Robot.oi.shoot.get())
    			shooter.unlatch();
    		break;
    	case shot:
    		break;
    	case returning:
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
