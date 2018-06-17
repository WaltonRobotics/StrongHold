package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class UnTension extends Command {

	public UnTension() {
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.shooter.unTension();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.unTension();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.normalTensioning.get();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
