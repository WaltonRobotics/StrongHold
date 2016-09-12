package org.usfirst.frc.team2974.robot.autonomousCommands;

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
			double brakingSpeed = .05;
			double speed = .35;
			if (side == 0) {
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(-speed, brakingSpeed);// turn left
				else
					driveTrain.setSpeeds(speed, -brakingSpeed);// turn right
			} else if (side == 2) {
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(-brakingSpeed, speed);// turn left
				else
					driveTrain.setSpeeds(brakingSpeed, -speed);// turn right
			}
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

	class Reset extends State {
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
		final double waitTime = .1;
		double startTime;

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

	private final DriveTrain driveTrain = Robot.driveTrain;
	private final Camera camera = Robot.camera;
	private final double threshold = 3;
	private final double centerX = 95;
	private final double totalTime;
	private final boolean shoot;
	private final double side;// 0 is left, 1 is center, 2 is right

	private double gain = .0035;

	private State currentState;

	private double cycleDifference;

	private double startTime;

	public Aim() {
		this(1, 2, false);
	}

	public Aim(int side, double totalTime) {
		this(side, totalTime, false);
	}

	public Aim(int side, double totalTime, boolean shoot) {
		SmartDashboard.putNumber("gain", gain);
		requires(Robot.driveTrain);
		this.side = side;
		this.shoot = shoot;
		this.totalTime = totalTime;
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
			Robot.camera.setNetTable();
		}
		SmartDashboard.putString("aim state", currentState.toString());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.driveTrain.shiftDown();
		gain = SmartDashboard.getNumber("gain");
		currentState = new Reset();
		startTime = Timer.getFPGATimestamp();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (!shoot)
			return Math.abs(cycleDifference) < threshold || Timer.getFPGATimestamp() - startTime > totalTime;
		else
			return Math.abs(cycleDifference) < threshold;
	}
}
