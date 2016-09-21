package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import motionProfilling.MotionControl;

public class DriveTrain extends Subsystem {
	private class SharedDrive implements PIDOutput {

		SpeedController one;
		SpeedController two;
		Boolean isLeft;

		public SharedDrive(final SpeedController one, final SpeedController two, final boolean isLeft) {
			this.isLeft = isLeft;
			this.one = one;
			this.two = two;
		}

		@Override
		public void pidWrite(double out) {
			if (!isLeft)
				out *= -1;

			one.set(out);
			two.set(out);
		}
	}

	private final Talon right1 = RobotMap.getDriveTrainRight1();
	private final Talon left1 = RobotMap.getDriveTrainLeft1();
	private final Talon right2 = RobotMap.getDriveTrainRight2();

	private final Talon left2 = RobotMap.getDriveTrainLeft2();
	private final Encoder encoderLeft = RobotMap.getEncoderLeft();

	private final Encoder encoderRight = RobotMap.getEncoderRight();

	private final Solenoid shifter = RobotMap.getPnuematicsShifter();
	private PIDControllerAccel leftController;

	public PIDControllerAccel rightController;
	public final double distancePerPulse = .000515417;

	private final double kV = 0.5; // Max speed is 2

	public DriveTrain() {
		// 128 pluses per revolution
		// 3x revolution for each turn of big motor
		// diameter = 8.25 in= .21 m
		// 18 drive shaft
		// 60 wheel
		// .21*pi/(128*3*60/18) = distance per pulse

		resetEncoders();
		encoderLeft.setDistancePerPulse(distancePerPulse);
		encoderRight.setDistancePerPulse(distancePerPulse);
		encoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
		encoderRight.setPIDSourceType(PIDSourceType.kDisplacement);

		setLeftController(new PIDControllerAccel(1, 0, 0, 1, RobotMap.getEncoderLeft(),
				new SharedDrive(left1, left2, true), kV, 0));

		getLeftController().disable();

		rightController = new PIDControllerAccel(1, 0, 0, 1, RobotMap.getEncoderRight(),
				new SharedDrive(right1, right2, false), kV, 0);

		rightController.disable();
	}

	public void disableMotionController() {
		getLeftController().disable();
		rightController.disable();
	}

	public void dumpSmartdashboardValues() {
		SmartDashboard.putNumber("EncoderLeft", encoderLeft.getDistance());
		SmartDashboard.putNumber("EncoderRight", encoderRight.getDistance());
		SmartDashboard.putNumber("Velocity Left", encoderLeft.getRate());
		SmartDashboard.putNumber("Velocity Right", encoderRight.getRate());
	}

	public void enableMotionController() {
		getLeftController().enable();
		rightController.enable();
	}

	public PIDControllerAccel getLeftController() {
		return leftController;
	}

	// public void setSetPoint(MotionControl mc, double time)

	// {

	// fix acceleration

	// leftController.setSetpoint(mc.distanceleft(time),mc.velocityLeft(time),0);

	// rightController.setSetpoint(mc.distanceRight(time),mc.velocityRight(time),0);

	// }

	public double getMeanDistance() {
		return (encoderRight.getDistance() + encoderLeft.getDistance()) / 2;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void initSmartdashBoardValues() {
		SmartDashboard.putNumber("kV", 1.0 / 1.5);
		SmartDashboard.putNumber("kA", 0);
		SmartDashboard.putNumber("P", 0);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("D", 0);
	}

	public void resetEncoders() {
		encoderLeft.reset();
		encoderRight.reset();
	}

	public void setLeftController(final PIDControllerAccel leftController) {
		this.leftController = leftController;
	}

	public void setSetPoint(final double dist, final double velocity) {
		// fix acceleration
		getLeftController().setSetpoint(dist, velocity, 0);
		rightController.setSetpoint(dist, velocity, 0);
	}

	public void setSpeeds(final double left, final double right) {
		right1.set(-right);
		right2.set(-right);

		left1.set(left);
		left2.set(left);

		SmartDashboard.putNumber("left", left);
		SmartDashboard.putNumber("right", -right);
	}

	public void shiftDown() {
		if (shifter.get())
			shifter.set(false);
	}

	public void shiftUp() {
		if (!shifter.get())
			shifter.set(true);
	}
}