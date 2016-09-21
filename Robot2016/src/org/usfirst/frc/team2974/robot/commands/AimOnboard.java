package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

class AimOnboard extends Command {
	// private double brakingSpeed = 0.05;
	private static final double centerX = 95;
	// 0 =left, 2 = right
	private final double side;
	private final DriveTrain driveTrain = Robot.getDriveTrain();
	private final Camera camera = Robot.getCamera();
	private double cycleDifference;

	public AimOnboard(final double side) {
		requires(Robot.getDriveTrain());
		// requires(Robot.camera);
		this.side = side;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (side == 0)
			cycleDifference = camera.getXLeft() - centerX;
		else if (side == 2)
			cycleDifference = camera.getXRight() - centerX;
		final double speed = Math.abs(cycleDifference * .01);
		if (side == 0) {
			if (cycleDifference > 0)// im to the right
				driveTrain.setSpeeds(-speed, 0);// turn left
			else
				driveTrain.setSpeeds(speed, 0);// turn right
		} else if (side == 2)
			if (cycleDifference > 0)// im to the right
				driveTrain.setSpeeds(0, speed);// turn left
			else
				driveTrain.setSpeeds(0, -speed);// turn right
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
		if (side == 0)
			return !Robot.getOi().getAimLeft().get();
		else
			return side == 2 && !Robot.getOi().getAimRight().get();
	}
}
