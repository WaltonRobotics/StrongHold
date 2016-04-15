package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.Flipper.FlipperState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlapDown extends Command {

	public FlapDown() {     
    	requires(Robot.flipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.flipper.setFlapper(FlipperState.down);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flipper.setFlapper(Flipper.FlipperState.down);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.shooter.isShooterDown();        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
