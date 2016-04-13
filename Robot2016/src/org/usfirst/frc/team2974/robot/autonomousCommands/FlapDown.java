package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flapper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlapDown extends Command {
double startTime;
boolean reset;
    public FlapDown() {     
        this(false);
    }
    public FlapDown(boolean reset)
    {
    	this.reset = reset;
    	requires(Robot.flapper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.flapper.setFlapper(Flapper.FlapperState.down);
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flapper.setFlapper(Flapper.FlapperState.down);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(!reset)
    	return Robot.shooter.isShooterDown();
       else
    	   return Timer.getFPGATimestamp()-startTime>.3;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
