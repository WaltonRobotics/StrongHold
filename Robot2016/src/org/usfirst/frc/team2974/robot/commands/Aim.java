package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2974.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Aim extends Command {
	
	private DriveTrain driveTrain = Robot.driveTrain;
	private Camera camera = Robot.camera;
	
	private final double threshold = 7;
	
	private double speed = .3;
	private final double centerX = 250;

	public Aim() {
		requires(driveTrain);
		requires(camera);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.shiftDown();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(camera.getX()!=-1)
		{
			//double speed = Math.abs(camera.getX() - centerX)*this.speed;
			if(speed<.2)
				speed = .4;
			if (Math.abs(camera.getX() - centerX) > threshold) {
				if (camera.getX() > centerX) {
					driveTrain.setSpeeds(-speed, speed);
				} else
					driveTrain.setSpeeds(speed, -speed);
			}
		}
		else
			driveTrain.setSpeeds(0, 0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return !Robot.oi.aim.get() || Math.abs(camera.getX() - centerX) <= threshold;
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
