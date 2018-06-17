package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterReset extends Command {

	double initTime;
	Shooter shooter = Robot.shooter;

	public ShooterReset() {
		requires(shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initTime = Timer.getFPGATimestamp();
		//new MoveFlapper();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - initTime > .3;
	}

	// Called once after isFinished returns true
	protected void end() {
		shooter.unlatch();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
