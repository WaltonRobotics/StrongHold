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

	private final double threshold = 3;
	private State currentState;
	private double speed = .6;
	private double brakingSpeed = .05;
	private final double centerX = 80;
	private double gain = .0012;
	private double cycleDifference;

	private double side;// 0 is left, 1 is center, 2 is right

	public Aim(int side) {
		SmartDashboard.putNumber("gain", gain);
		requires(Robot.driveTrain);
		this.side = side;
	}
	public Aim()
	{
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

		void init() {
			startTime = Timer.getFPGATimestamp();
		}

		void execute() {
			if (side == 0) {
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(speed, -brakingSpeed);// turn left
				else
					driveTrain.setSpeeds(-speed, brakingSpeed);// turn right
			}
			else if(side == 1)
			{
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(speed, -speed);// turn left
				else
					driveTrain.setSpeeds(-speed, speed);// turn right
			}
			else if(side ==2)
			{
				if (cycleDifference > 0)// im to the right
					driveTrain.setSpeeds(brakingSpeed, -speed);// turn left
				else
					driveTrain.setSpeeds(-brakingSpeed, speed);// turn right
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
			if (side == 1)
				cycleDifference = camera.getX() - centerX;
			else if (side == 0)
				cycleDifference = camera.getXLeft() - centerX;
			else if (side == 2)
				cycleDifference = camera.getXRight() - centerX;
		//if(Math.abs(cycleDifference) < 8)
			//cycleDifference = 8* Math.signum(cycleDifference);
		}
		
		void execute() {

		}

		boolean isFinished() {
			return Math.abs(cycleDifference) > threshold;
		}

		void end() {
			currentState = new Cycle();
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.shiftUp();// change to down TODO
		gain = SmartDashboard.getNumber("gain");
		currentState = new Reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (camera.getX() != -1) {
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
	protected boolean isFinished() {
		if(side == 1)
		return !Robot.oi.aim.get();
		if(side==0)
			return !Robot.oi.aimLeft.get();
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
}
