package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveObstacle extends Command {
	enum driveState {
		drive, up, crossing, down, done
	}

	private final Compass compass = Robot.compass;
	private final DriveTrain driveTrain = Robot.driveTrain;
	private double startYaw;
	private driveState state;
	private double time;

	private double startTime;

	public DriveObstacle() {
		requires(driveTrain);
		requires(compass);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double pitch = compass.getPitch();
		switch (state) {
		case drive:
			straight();
			double upPitch = 15;
			if (pitch > upPitch) {
				state = driveState.crossing;
				startTime = Timer.getFPGATimestamp();
			}
			break;
		case up:
			straight();
			double timeUp = 1;
			double crossPitch = 0;
			if (Timer.getFPGATimestamp() - startTime > timeUp && pitch < crossPitch) {
				state = driveState.crossing;
				startTime = Timer.getFPGATimestamp();
			}
			break;
		case crossing:
			straight();
			double timeCross = 1;
			double downPitch = -15;
			if (Timer.getFPGATimestamp() - startTime > timeCross && pitch < downPitch) {
				state = driveState.down;
				startTime = Timer.getFPGATimestamp();
			}
			break;
		case down:
			straight();
			double timeDown = 1;
			double threshold = 5;
			double normalPitch = 0;
			if (Math.abs(normalPitch - pitch) < threshold && Timer.getFPGATimestamp() - time > timeDown) {
				state = driveState.done;
			}
			break;
		case done:
			Robot.driveTrain.setSpeeds(0, 0);
			break;
		}

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startYaw = compass.getYaw();
		startTime = Timer.getFPGATimestamp();
		state = driveState.drive;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return state == driveState.done;
	}

	private void straight() {
		double speed = -.8;
		double speedLeft = speed;
		double speedRight = speed;

		double multiplierConstatnt = 1.5;
		if (compass.getYaw() > startYaw) {
			// veering right
			speedLeft -= Math.abs(compass.getYaw() - startYaw) * multiplierConstatnt;
			speedRight += Math.abs(compass.getYaw() - startYaw) * multiplierConstatnt;
		} else {
			// veering left
			speedLeft += Math.abs(compass.getYaw() - startYaw) * multiplierConstatnt;
			speedRight -= Math.abs(compass.getYaw() - startYaw) * multiplierConstatnt;
		}
		driveTrain.setSpeeds(speedLeft, speedRight);
	}
}
