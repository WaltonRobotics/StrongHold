package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flapper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFlapper extends Command {

    public MoveFlapper() {
        requires(Robot.flapper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.flapperDown.get())
    		Robot.flapper.setFlapper(Flapper.FlapperState.down);
    	else 
    		Robot.flapper.setFlapper(Flapper.FlapperState.up);
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
