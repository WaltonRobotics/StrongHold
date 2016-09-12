package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightMC extends Command {

	private final double aMax;
	private final double vDrive;
	private final double x3;
	private final double t1;
	private final double t2;
	private final double t3;
	private final DriveTrain driveTrain;

	public DriveStraightMC(double distance, double velocity, double accel) {
		requires(Robot.driveTrain);
		driveTrain = Robot.driveTrain;
		aMax = Math.abs(accel);
		vDrive = Math.signum(distance) * Math.abs(velocity);
		x3 = distance;
		if (x3 < 2 * Math.pow(vDrive, 2) / aMax) {
			t3 = 2 * Math.sqrt(x3 / 2 / aMax);
			t2 = t3 / 2;
			t1 = t2;
		} else {
			t3 = x3 / vDrive + 2 * vDrive / aMax;
			t2 = t3 - vDrive / aMax;
			t1 = vDrive / aMax;
		}

		System.out.println(String.format("x3 = %1$.2f", x3));
		System.out.println(String.format("t1 = %1$.2f t2 = %2$.2f t3 = %3$.2f", t1, t2, t3));

	}

	@Override
	protected void end() {
		driveTrain.disableMotionController();
		driveTrain.setSpeeds(0, 0);
	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		double x;
		double v;
		if (t < t1) {
			x = Math.pow(t, 2) * aMax;
			v = aMax * t;
		} else if (t < t2) {
			x = Math.pow(t1, 2) * aMax + vDrive * (t - t1);
			v = vDrive;
		} else if (t < t3) {
			x = x3 - aMax * Math.pow(t - t3, 2);
			v = -aMax * (t - t3);
		} else {
			x = x3;
			v = 0;
		}
		driveTrain.setSetPoint(x, v);

	}

	@Override
	protected void initialize() {
		driveTrain.resetEncoders();
		driveTrain.setSetPoint(0, 0);
		driveTrain.enableMotionController();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		double t = timeSinceInitialized();
		double tolerance = 0.1;
		boolean isFinished = (Math.abs(driveTrain.getMeanDistance() - x3) < tolerance) && (t > t3);
		double timeOut = 2;
		boolean isTimedOut = t > (t3 + timeOut);
		return isFinished || isTimedOut;
	}

}
