package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Aim extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private Camera camera = Robot.camera;

	private final double threshold = 2;
	private AimState currentState;
	private double speed = .4;
	private double brakingSpeed = .05;
	private final double centerX = 85;
	private double gain = .0087;
	private double cycleInitTime;
	private double cycleDifference;
	private double waitTimeInit;


	public Aim() {
		requires(driveTrain);
		requires(camera);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.shiftDown();
		SmartDashboard.putNumber("gain", gain);
		currentState = AimState.inReset;
	}

	private enum AimState {
		inCycle, inReset, inWait
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putString("AimState", currentState.toString());
		if (camera.getX() != -1) {
			switch (currentState) {
			case inCycle:
				double speed = this.speed;
				if (cycleDifference > 0)
					driveTrain.setSpeeds(-speed, brakingSpeed);
				else
					driveTrain.setSpeeds(speed, -brakingSpeed);
				if (Timer.getFPGATimestamp() - cycleInitTime > gain * Math.abs(cycleDifference)) {
					currentState = AimState.inWait;
					waitTimeInit = Timer.getFPGATimestamp();
				}
			case inWait:
				driveTrain.setSpeeds(0, 0);
				if (Timer.getFPGATimestamp() - waitTimeInit > .1) {
					currentState = AimState.inReset;
				}
			case inReset:
				cycleDifference = camera.getX() - centerX;
				cycleInitTime = Timer.getFPGATimestamp();
				if (Math.abs(cycleDifference) > threshold) {
					currentState = AimState.inCycle;
				}
			}
		} else {
			driveTrain.setSpeeds(0, 0);
			currentState = AimState.inReset;
			gain = SmartDashboard.getNumber("gain");
		}

		// if (inCycle) {
		//
		// //double speed = Math.abs(camera.getX() - centerX) * this.speed;
		// double speed = this.speed;
		// if (cycleDifference > 0)
		// driveTrain.setSpeeds(-speed, brakingSpeed);
		// else
		// driveTrain.setSpeeds(speed, -brakingSpeed);
		// if(Timer.getFPGATimestamp()-cycleInitTime >
		// gain*Math.abs(cycleDifference))
		// {
		// inCycle = false;
		// waitTimeInit = Timer.getFPGATimestamp();
		// inWait = true;
		// }
		//
		// } else if(inWait)
		// {
		// driveTrain.setSpeeds(0, 0);
		// if(Timer.getFPGATimestamp()-waitTimeInit>.1)
		// {
		// inWait = false;
		// inReset = true;
		// }
		//
		//
		// }else{
		//
		//
		// cycleDifference = camera.getX() - centerX;
		// cycleInitTime = Timer.getFPGATimestamp();
		// if (Math.abs(cycleDifference) > threshold)
		// {
		// inCycle = true;
		// inReset = false;
		// }
		//
		// }

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !Robot.oi.aim.get();
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
