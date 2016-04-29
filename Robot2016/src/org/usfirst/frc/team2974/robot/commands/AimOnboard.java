package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimOnboard extends Command {
	// 0 =left, 2 = right
	double side;
	private double speed = .35;
	//private double brakingSpeed = 0.05;
	public static final double centerX = 95;
	double cycleDifference;
	DriveTrain driveTrain = Robot.driveTrain;
	Camera camera = Robot.camera;
	
	public AimOnboard(double side) {
		requires(Robot.driveTrain);
		//requires(Robot.camera);
		this.side = side;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		 if (side == 0)
			cycleDifference = camera.getXLeft() - centerX;
		else if (side == 2)
			cycleDifference = camera.getXRight() - centerX;
		speed = Math.abs(cycleDifference* .01);
		if (side == 0) {
			if (cycleDifference > 0)// im to the right
				driveTrain.setSpeeds(-speed, 0);// turn left
			else
				driveTrain.setSpeeds(speed, 0);// turn right
		} else if (side == 2) {
			if (cycleDifference > 0)// im to the right
				driveTrain.setSpeeds(0, speed);// turn left
			else
				driveTrain.setSpeeds(0, -speed);// turn right
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (side == 0)
			return (!Robot.oi.aimLeft.get());
		else if (side == 2)
			return (!Robot.oi.aimRight.get());
		else return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
