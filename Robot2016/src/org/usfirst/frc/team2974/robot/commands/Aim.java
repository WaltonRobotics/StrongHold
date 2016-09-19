
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

	public class Cycle extends State {
		double startTime;

		@Override
		void end() {
			currentState = new Wait();

		}

		@Override
		void execute() {
			if (side == 0) {// aim to the left goal
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(-speed, brakingSpeed);// turn left
				else
					driveTrain.setSpeeds(speed, -brakingSpeed);// turn right
			} else if (side == 2)
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(-brakingSpeed, speed);// turn left
				else
					driveTrain.setSpeeds(brakingSpeed, -speed);// turn right
		}

		@Override
		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		@Override
		boolean isFinished() {
			return Timer.getFPGATimestamp() - startTime > gain * Math.abs(cycleDifference);
		}
	}

	public class Reset extends State {
		@Override
		void end() {
			currentState = new Cycle();
		}

		@Override
		void execute() {

			if (side == 0)
				cycleDifference = camera.getXLeft() - centerX;
			else if (side == 2)
				cycleDifference = camera.getXRight() - centerX;

		}

		@Override
		void init() {

			if (side == 0)
				cycleDifference = camera.getXLeft() - centerX;
			else if (side == 2)
				cycleDifference = camera.getXRight() - centerX;
		}

		@Override
		boolean isFinished() {
			return Math.abs(cycleDifference) > threshold;
		}
	}

	public abstract class State {
		boolean init = false;

		abstract void end();

		abstract void execute();

		abstract void init();

		abstract boolean isFinished();
	}

	public class Wait extends State {
		double startTime;
		double waitTime = .1;

		@Override
		void end() {
			currentState = new Reset();
		}

		@Override
		void execute() {
			driveTrain.setSpeeds(0, 0);
		}

		@Override
		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		@Override
		boolean isFinished() {
			return Timer.getFPGATimestamp() - startTime > waitTime;
		}
	}

	public static final double threshold = 3;
	public static final double centerX = 95;
	public static double cycleDifference;

	private final DriveTrain driveTrain = Robot.getDriveTrain();
	private final Camera camera = Robot.getCamera();
	private final double speed = .35;

	private final double brakingSpeed = 0.05;

	private double gain = .0035;

	private State currentState;

	private final double side;// 0 is left, 2 is right

	public Aim() {
		this(1);
	}

	public Aim(final int side) {
		SmartDashboard.putNumber("gain", gain);
		requires(Robot.getDriveTrain());
		this.side = side;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		driveTrain.setSpeeds(0, 0);
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

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.getDriveTrain().shiftDown();
		gain = SmartDashboard.getNumber("gain");
		currentState = new Reset();
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
		return !Robot.getOi().getAimRight().get();
	}

}