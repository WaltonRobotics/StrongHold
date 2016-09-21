package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterReset extends Command {
	private final Shooter shooter = Robot.getShooter();
	private double initTime;

	public ShooterReset() {
		requires(shooter);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		shooter.unlatch();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		initTime = Timer.getFPGATimestamp();
		// new MoveFlapper();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - initTime > .3;
	}
}
