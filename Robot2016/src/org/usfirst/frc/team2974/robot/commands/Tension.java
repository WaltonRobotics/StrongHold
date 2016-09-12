package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Tension extends Command {

	public Tension() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.shooter);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.shooter.tension();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.shooter.tension();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.oi.normalTensioning.get();
	}
}
