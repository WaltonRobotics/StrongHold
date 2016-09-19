package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToObstacle extends Command {
	double threshold;
	double direction;
	double startTime;

	public MoveToObstacle(final double direction) {
		this.direction = direction;
		startTime = Timer.getFPGATimestamp();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		end();
	}

	@Override
	protected void execute() {

		if (isFinished())
			return;
		Robot.getDriveTrain().setSpeeds(1 * direction, 1 * direction);
	}

	@Override
	protected void initialize() {
		Robot.getDriveTrain().setSpeeds(1 * direction, 1 * direction);
		threshold = 5 * direction;

	}

	@Override
	protected void interrupted() {

		Robot.getDriveTrain().setSpeeds(0, 0);

	}

	@Override
	protected boolean isFinished() {
		// Create an if statement which will call end

		if (Robot.getCompass().getPitch() > threshold && Timer.getFPGATimestamp() - startTime > 500)
			return true;
		return false;
	}

}
