package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Command {

	public static boolean isTank = true;

	public static String driveMode = "Tank";
	private final double deadband = .05;

	public Drive() {

		requires(Robot.getDriveTrain());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (SmartDashboard.getString("Drive Mode:").equals("Tank")) {
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
			double directionX = Robot.getOi().getRight().getX();
			double directionY = Robot.getOi().getRight().getY();
			final double throttle = (Robot.getOi().getLeft().getY() + 1) / 2;// returns
			// a
			// value
			// between
			// 0 and
			// 1

			final double throttleCalc = throttle * -1 * directionY;

			if (Math.abs(directionX) < deadband)
				directionX = 0;
			if (Math.abs(throttle) < deadband)
				directionY = 0;
			Robot.getDriveTrain().setSpeeds(throttleCalc - directionX * throttle, throttleCalc - directionX * throttle);

			if (Robot.getOi().getShiftDown().get())
				Robot.getDriveTrain().shiftDown();
			if (Robot.getOi().getShiftUp().get())
				Robot.getDriveTrain().shiftUp();
		}

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
		return !isRunning();
	}
}
