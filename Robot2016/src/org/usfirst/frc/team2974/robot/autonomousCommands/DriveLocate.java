package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
//import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLocate extends Command {
	private final double timeOut = 3;

	private double conversionRateL;
	private double conversionRateR;
	private double thresholdYaw;
	private final double thresholdPitch = 5;
	private double targetAngle;
	private double aMax;
	private double vDrive;
	private DriveTrain driveTrain;
	private Compass compass;

	public DriveLocate() {
		requires(Robot.getDriveTrain());
		requires(Robot.getCompass());
		driveTrain = Robot.getDriveTrain();
		// Compass compass = Robot.compass;
		aMax = 0.1; // TEST - 0.1 seems slow
		vDrive = 0.5; // TEST - 0.5 so that V can end up being 1

	}

	@Override
	protected void end() {
		// Hand over to next command with robot stopping
		driveTrain.disableMotionController();
		driveTrain.setSpeeds(0, 0);

	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		double tDrive = vDrive / aMax;
		double x;
		double v;
		if (t < tDrive) {
			x = Math.pow(aMax, 2) * t;
			v = aMax * t;
		} else {
			x = (Math.pow(aMax, 2) * tDrive) + vDrive * (t - tDrive);
			v = vDrive;
		}
		driveTrain.setSetPoint(x, v);

	}

	public double getConversionRateL() {
		return conversionRateL;
	}

	public double getConversionRateR() {
		return conversionRateR;
	}

	public double getThresholdYaw() {
		return thresholdYaw;
	}

	@Override
	protected void initialize() {
		driveTrain.resetEncoders();
		driveTrain.setSetPoint(0, 0);
		driveTrain.enableMotionController();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// Returns true if both tests are satisfied

		double errorAngle = Robot.getCompass().getYaw() - targetAngle;

		if (errorAngle > 180) {
			errorAngle -= 360;
		}

		if (errorAngle < -180) {
		}

		double timeOut = 3;
		double thresholdPitch = 5;
		return (timeSinceInitialized() > timeOut) || (Math.abs(Robot.getCompass().getPitch()) > thresholdPitch);// &&
		// (Math.abs(errorAngle) < thresholdYaw);
	}

	public void setConversionRateL(double conversionRateL) {
		this.conversionRateL = conversionRateL;
	}

	public void setConversionRateR(double conversionRateR) {
		this.conversionRateR = conversionRateR;
	}

	public void setThresholdYaw(double thresholdYaw) {
		this.thresholdYaw = thresholdYaw;
	}

	public double getThresholdPitch() {
		return thresholdPitch;
	}

	public double getTimeOut() {
		return timeOut;
	}

	public Compass getCompass() {
		return compass;
	}

	public void setCompass(Compass compass) {
		this.compass = compass;
	}

}
