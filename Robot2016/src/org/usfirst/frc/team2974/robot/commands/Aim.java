package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

/**
 *
 */
public class Aim extends Command {

	public static final double threshold = 3;
	public static final double centerX = 95;
	public static double cycleDifference;
	private DriveTrain driveTrain = Robot.driveTrain;
	private Camera camera = Robot.camera;
	private double speed = .35;
	private double brakingSpeed = 0.05;
	private double gain = .0035;
	private State currentState;
	private double side;// 0 is left, 2 is right

	public Aim(int side) {
		SmartDashboard.putNumber("gain", gain);
		requires(Robot.driveTrain);
		this.side = side;
	}

	public Aim() {
		this(1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.shiftDown();
		gain = SmartDashboard.getNumber("gain");
		currentState = new Reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (camera.getXLeft() != -1) {
			if (!currentState.init) {
				currentState.init();
				currentState.init = true;
			} else if (currentState.isFinished()) {
				currentState.end();
			} else {
				currentState.execute();
			}

		} else {
			driveTrain.setSpeeds(0, 0);
			currentState = new Reset();

		}
		SmartDashboard.putString("aim state", currentState.toString());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (side == 0) {
			return !Robot.oi.aimLeft.get();
		}
		return !Robot.oi.aimRight.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	public abstract class State {

		boolean init = false;

		abstract void init();

		abstract void execute();

		abstract void end();

		abstract boolean isFinished();
	}

	public class Cycle extends State {

		double startTime;

		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		void execute() {
			if (side == 0) {//aim to the left goal
				if (cycleDifference > 0)// im to the right
				{
					driveTrain.setSpeeds(-speed, brakingSpeed);// turn left
				} else {
					driveTrain.setSpeeds(speed, -brakingSpeed);// turn right
				}
			} else if (side == 2) {//aim to the right goal
				if (cycleDifference > 0)// im to the right
				{
					driveTrain.setSpeeds(-brakingSpeed, speed);// turn left
				} else {
					driveTrain.setSpeeds(brakingSpeed, -speed);// turn right
				}
			}
		}

		boolean isFinished() {
			return Timer.getFPGATimestamp() - startTime > gain * Math.abs(cycleDifference);
		}

		void end() {
			currentState = new Wait();

		}
	}

	public class Wait extends State {

		double startTime;
		double waitTime = .1;

		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		void execute() {
			driveTrain.setSpeeds(0, 0);
		}

		boolean isFinished() {
			return Timer.getFPGATimestamp() - startTime > waitTime;
		}

		void end() {
			currentState = new Reset();
		}
	}

	public class Reset extends State {

		void init() {

			if (side == 0) {
				cycleDifference = camera.getXLeft() - centerX;
			} else if (side == 2) {
				cycleDifference = camera.getXRight() - centerX;
			}
		}

		void execute() {

			if (side == 0) {
				cycleDifference = camera.getXLeft() - centerX;
			} else if (side == 2) {
				cycleDifference = camera.getXRight() - centerX;
			}

		}

		boolean isFinished() {
			return Math.abs(cycleDifference) > threshold;
		}

		void end() {
			currentState = new Cycle();
		}
	}
}