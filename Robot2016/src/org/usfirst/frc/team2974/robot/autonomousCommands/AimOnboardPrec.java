package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimOnboardPrec extends Command {
	// 0 =left, 2 = right
	
	double time;
	int side;
	//private double speed = .35;
	private final double BARESPEED = 0.03;
	private double brakingSpeed = 0;
	public static double centerX = 95;
	private double threshold = 3;
	double cycleDifference;
	DriveTrain driveTrain = Robot.driveTrain;
	Camera camera = Robot.camera;
	
	public AimOnboardPrec(int side) {
		requires(Robot.driveTrain);
		this.side = side;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time  = Timer.getFPGATimestamp() + 1;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (side == 0)
			cycleDifference = camera.getXLeft() - centerX;
		else if (side == 2)
			cycleDifference = camera.getXRight() - centerX;
		if(Math.abs(cycleDifference)>threshold)
		{
			if (side == 0) {
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(BARESPEED, -brakingSpeed);// turn left
				else
					driveTrain.setSpeeds(-BARESPEED, brakingSpeed);// turn right
			} else if (side == 2) {
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(brakingSpeed, -BARESPEED);// turn left
				else
					driveTrain.setSpeeds(-brakingSpeed, BARESPEED);// turn right
			}
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(cycleDifference)<threshold || time < Timer.getFPGATimestamp();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
