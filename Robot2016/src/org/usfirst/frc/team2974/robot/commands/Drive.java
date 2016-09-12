package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	public Drive() {

		requires(Robot.driveTrain);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		double left = Robot.oi.left.getY();
		double right = Robot.oi.right.getY();

		double deadband = .05;
		if (Math.abs(left) < deadband)
			left = 0;
		if (Math.abs(right) < deadband)
			right = 0;
		Robot.driveTrain.setSpeeds(-1 * left, -1 * right);

		if (Robot.oi.shiftDown.get())
			Robot.driveTrain.shiftDown();
		if (Robot.oi.shiftUp.get())
			Robot.driveTrain.shiftUp();

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
}
