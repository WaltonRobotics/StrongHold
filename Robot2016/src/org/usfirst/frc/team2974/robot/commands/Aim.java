
package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Aim extends Command {

	private DriveTrain driveTrain = Robot.getDriveTrain();
	private Camera camera = Robot.getCamera();

	public static final double threshold = 3;
	private double speed = .35;
	private double brakingSpeed = 0.05;
	public static final double centerX = 95;
	private double gain = .0035;

	private State currentState;
	public static double cycleDifference;
	private double side;// 0 is left, 2 is right

	public Aim(int side) {
		SmartDashboard.putNumber("gain", gain);
		requires(Robot.getDriveTrain());
		this.side = side;
	}

	public Aim() {
		this(1);
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

		@Override
		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		@Override
		void execute() {
			if (side == 0) {// aim to the left goal
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(-speed, brakingSpeed);// turn left
				else
					driveTrain.setSpeeds(speed, -brakingSpeed);// turn right
			} else if (side == 2) {// aim to the right goal
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(-brakingSpeed, speed);// turn left
				else
					driveTrain.setSpeeds(brakingSpeed, -speed);// turn right
			}
		}

		@Override
		boolean isFinished() {
			return Timer.getFPGATimestamp() - startTime > gain * Math.abs(cycleDifference);
		}

		@Override
		void end() {
			currentState = new Wait();

		}
	}

	public class Wait extends State {
		double startTime;
		double waitTime = .1;

		@Override
		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		@Override
		void execute() {
			driveTrain.setSpeeds(0, 0);
		}

		@Override
		boolean isFinished() {
			return Timer.getFPGATimestamp() - startTime > waitTime;
		}

		@Override
		void end() {
			currentState = new Reset();
		}
	}

	public class Reset extends State {
		@Override
		void init() {

			if (side == 0)
				cycleDifference = camera.getXLeft() - centerX;
			else if (side == 2)
				cycleDifference = camera.getXRight() - centerX;
		}

		@Override
		void execute() {

			if (side == 0)
				cycleDifference = camera.getXLeft() - centerX;
			else if (side == 2)
				cycleDifference = camera.getXRight() - centerX;

		}

		@Override
		boolean isFinished() {
			return Math.abs(cycleDifference) > threshold;
		}

		@Override
		void end() {
			currentState = new Cycle();
		}
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.getDriveTrain().shiftDown();
		gain = SmartDashboard.getNumber("gain");
		currentState = new Reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (camera.getXLeft() != -1) {
			if (!currentState.init) {
				currentState.init();
				currentState.init = true;
			} else if (currentState.isFinished())
				currentState.end();
			else
				currentState.execute();

		} else {
			driveTrain.setSpeeds(0, 0);
			currentState = new Reset();

		}
		SmartDashboard.putString("aim state", currentState.toString());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (side == 0)
			return !Robot.getOi().getAimLeft().get();
		return !Robot.getOi().getAimRight().get();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		driveTrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}