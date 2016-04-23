package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFlipper extends Command {

    public MoveFlipper() {
        requires(Robot.flipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.flapperDown.get())
    	{
    		if(Robot.intake.getState() == Intake.IntakeExtenderState.out)
    		Robot.flipper.setFlapper(Flipper.FlipperState.down);
    		else
    			Robot.intake.extend();
    	}	
    	else 
    		Robot.flipper.setFlapper(Flipper.FlipperState.up);
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
