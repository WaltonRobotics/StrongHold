package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	double startTime;
	double time = 1;

	public Shoot() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		requires(Robot.shooter);
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.unlatch();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > time;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
