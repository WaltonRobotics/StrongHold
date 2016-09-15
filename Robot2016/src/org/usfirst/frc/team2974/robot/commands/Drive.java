package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	private final double deadband = .05;

	public static boolean isTank = true;
	public static String driveMode = "Tank";

	public Drive() {

		requires(Robot.getDriveTrain());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (Drive.isTank) {
			double left = Robot.getOi().getLeft().getY();
			double right = Robot.getOi().getRight().getY();

			if (Math.abs(left) < deadband)
				left = 0;
			if (Math.abs(right) < deadband)
				right = 0;
			Robot.getDriveTrain().setSpeeds(-1 * left, -1 * right);

			if (Robot.getOi().getShiftDown().get())
				Robot.getDriveTrain().shiftDown();
			if (Robot.getOi().getShiftUp().get())
				Robot.getDriveTrain().shiftUp();
		} else {
			double direction = Robot.getOi().getLeft().getX();
			double throttle = Robot.getOi().getRight().getY();

			if (Math.abs(direction) < deadband)
				direction = 0;
			if (Math.abs(throttle) < deadband)
				throttle = 0;
			Robot.getDriveTrain().setSpeeds(-1 * throttle + direction, -1 * throttle - direction);

			if (Robot.getOi().getShiftDown().get())
				Robot.getDriveTrain().shiftDown();
			if (Robot.getOi().getShiftUp().get())
				Robot.getDriveTrain().shiftUp();
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
