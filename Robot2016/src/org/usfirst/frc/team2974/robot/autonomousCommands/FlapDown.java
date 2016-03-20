package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flapper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlapDown extends Command {

    public FlapDown() {
        //requires(Robot.flapper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.flapper.setFlapper(Flapper.FlapperState.down);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.flapper.setFlapper(Flapper.FlapperState.down);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
