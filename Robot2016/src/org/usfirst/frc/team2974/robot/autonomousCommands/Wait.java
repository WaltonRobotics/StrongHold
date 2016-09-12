package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	private final double time;
	private double startTime;

	public Wait(double time) {
		requires(Robot.getDriveTrain());
		this.time = time;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.getDriveTrain().setSpeeds(0, 0);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > time;
	}
}
