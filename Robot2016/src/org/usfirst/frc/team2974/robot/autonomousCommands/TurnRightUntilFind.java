package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnRightUntilFind extends Command {

	private final double totalTime;
	private final double speed;
	private double startTime;

	public TurnRightUntilFind(final double totalTime, final double speed) {
		requires(Robot.getDriveTrain());
		this.totalTime = totalTime;
		this.speed = speed;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.getDriveTrain().setSpeeds(0, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Timer.getFPGATimestamp() - startTime < totalTime)
			Robot.getDriveTrain().setSpeeds(speed, -speed);
		else
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
		return Robot.getCamera().getXLeft() != -1 && Timer.getFPGATimestamp() - startTime < 4;
	}
}
