package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class Wait extends Command {

	double time;
	double startTime;

	public Wait(double time) {
		requires(Robot.driveTrain);
		this.time = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.setSpeeds(0, 0);
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
