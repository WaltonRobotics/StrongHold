package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;
import org.usfirst.frc.team2974.robot.subsystems.Shooter.TensionerState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	private ShooterState currentState;
	private Shooter shooter = Robot.shooter;
	
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
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(currentState){
    	case latched: 
    		shooter.tension();
    		if(shooter.getState() == TensionerState.tensioned)
    			currentState = ShooterState.readyToAim;
    		break;
    	case readyToAim:
    		shooter.atTensionLimit();
    		if(Robot.oi.aim.get())
    		{
    			new Aim();
    		}
    		break;
    	case readyToShoot:
    		shooter.atTensionLimit();
    		if(Robot.oi.shoot.get())
    		{
    			shooter.unlatch();
    			currentState = ShooterState.returning;
    		}
    		break;
    	case returning:
    		shooter.unload();
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
