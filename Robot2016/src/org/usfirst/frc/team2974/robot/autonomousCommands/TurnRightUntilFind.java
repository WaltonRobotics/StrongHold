package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRightUntilFind extends Command {

	double totalTime;
	double startTime;
	double speed;

	public TurnRightUntilFind(double totalTime, double speed) {
		requires(Robot.driveTrain);
		this.totalTime = totalTime;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Timer.getFPGATimestamp() - startTime < totalTime)
			Robot.driveTrain.setSpeeds(speed, -speed);
		else
			Robot.driveTrain.setSpeeds(0, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.camera.getXLeft() != -1 && Timer.getFPGATimestamp()-startTime < 4;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
