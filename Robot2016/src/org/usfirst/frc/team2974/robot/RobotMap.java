package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.robot.sensors.BNO055;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// compass
	private static final BNO055 compass = new BNO055(BNO055.BNO055_ADDRESS_A);
	private static DigitalInput digital0;
	private static DigitalInput digital1;
	private static DigitalInput digital2;
	private static DigitalInput digital3;
	private static DigitalInput digital4;
	private static DigitalInput digital5;
	private static DigitalInput digital6;
	private static DigitalInput digital7;
	private static DigitalInput digital8;
	private static DigitalInput digital9;
	private static AnalogInput analog0;
	private static AnalogInput analog1;
	private static AnalogInput analog2;
	private static AnalogInput analog3;
	// drive train
	private static Talon driveTrainRight1;
	private static Talon driveTrainRight2;
	private static Talon driveTrainLeft1;
	private static Talon driveTrainLeft2;
	private static Encoder encoderLeft;
	private static Encoder encoderRight;
	private static Solenoid pnuematicsShifter;
	// intake
	private static Solenoid flapper;
	private static Talon intakeMotor;
	private static Solenoid intakeExtender;
	// shooter
	private static Solenoid latch;
	private static CANTalon tensioner;
	// arm
	private static CANTalon arm;
	private static AnalogPotentiometer armPot;
	private static DigitalInput forwardLimit;
	private static DigitalInput backwardLimit;
	private static DigitalInput shooterLimit;

	public static AnalogInput getAnalog0() {
		return analog0;
	}

	public static AnalogInput getAnalog1() {
		return analog1;
	}

	public static AnalogInput getAnalog2() {
		return analog2;
	}

	public static AnalogInput getAnalog3() {
		return analog3;
	}

	public static CANTalon getArm() {
		return arm;
	}

	public static AnalogPotentiometer getArmPot() {
		return armPot;
	}

	public static DigitalInput getBackwardLimit() {
		return backwardLimit;
	}

	public static BNO055 getCompass() {
		return compass;
	}

	public static DigitalInput getDigital0() {
		return digital0;
	}

	public static DigitalInput getDigital1() {
		return digital1;
	}

	public static DigitalInput getDigital2() {
		return digital2;
	}

	public static DigitalInput getDigital3() {
		return digital3;
	}

	public static DigitalInput getDigital4() {
		return digital4;
	}

	public static DigitalInput getDigital5() {
		return digital5;
	}

	public static DigitalInput getDigital6() {
		return digital6;
	}

	public static DigitalInput getDigital7() {
		return digital7;
	}

	public static DigitalInput getDigital8() {
		return digital8;
	}

	public static DigitalInput getDigital9() {
		return digital9;
	}

	public static Talon getDriveTrainLeft1() {
		return driveTrainLeft1;
	}

	public static Talon getDriveTrainLeft2() {
		return driveTrainLeft2;
	}

	public static Talon getDriveTrainRight1() {
		return driveTrainRight1;
	}

	public static Talon getDriveTrainRight2() {
		return driveTrainRight2;
	}

	public static Encoder getEncoderLeft() {
		return encoderLeft;
	}

	public static Encoder getEncoderRight() {
		return encoderRight;
	}

	public static Solenoid getFlapper() {
		return flapper;
	}

	public static DigitalInput getForwardLimit() {
		return forwardLimit;
	}

	public static Solenoid getIntakeExtender() {
		return intakeExtender;
	}

	public static Talon getIntakeMotor() {
		return intakeMotor;
	}

	public static Solenoid getLatch() {
		return latch;
	}

	public static Solenoid getPnuematicsShifter() {
		return pnuematicsShifter;
	}

	public static DigitalInput getShooterLimit() {
		return shooterLimit;
	}

	public static CANTalon getTensioner() {
		return tensioner;
	}

	public static void init() {
		// inputs
		setDigital0(new DigitalInput(0));
		setDigital1(new DigitalInput(1));
		setDigital2(new DigitalInput(2));
		setDigital3(new DigitalInput(3));
		setDigital4(new DigitalInput(4));
		setDigital5(new DigitalInput(5));
		setDigital6(new DigitalInput(6));
		setDigital7(new DigitalInput(7));
		setDigital8(new DigitalInput(8));
		setDigital9(new DigitalInput(9));

		setAnalog0(new AnalogInput(0));
		setAnalog1(new AnalogInput(1));
		setAnalog2(new AnalogInput(2));
		setAnalog3(new AnalogInput(3));

		setForwardLimit(getDigital2());// forward is tensioned
		setBackwardLimit(getDigital1());
		setShooterLimit(getDigital0());

		// drive train
		setEncoderRight(new Encoder(getDigital6(), getDigital7()));
		setEncoderLeft(new Encoder(getDigital9(), getDigital8()));

		setDriveTrainRight1(new Talon(2));
		setDriveTrainRight2(new Talon(3));

		setDriveTrainLeft1(new Talon(0));
		setDriveTrainLeft2(new Talon(1));

		// arm
		setArm(new CANTalon(2));
		setArmPot(new AnalogPotentiometer(getAnalog0(), 1000, -750));

		// shifter
		setPnuematicsShifter(new Solenoid(0));

		// shooter
		setLatch(new Solenoid(2));
		setTensioner(new CANTalon(1));

		// intake
		setFlapper(new Solenoid(1));
		setIntakeMotor(new Talon(4));
		setIntakeExtender(new Solenoid(3));

	}

	public static void setAnalog0(final AnalogInput analog0) {
		RobotMap.analog0 = analog0;
	}

	public static void setAnalog1(final AnalogInput analog1) {
		RobotMap.analog1 = analog1;
	}

	public static void setAnalog2(final AnalogInput analog2) {
		RobotMap.analog2 = analog2;
	}

	public static void setAnalog3(final AnalogInput analog3) {
		RobotMap.analog3 = analog3;
	}

	public static void setArm(final CANTalon arm) {
		RobotMap.arm = arm;
	}

	public static void setArmPot(final AnalogPotentiometer armPot) {
		RobotMap.armPot = armPot;
	}

	public static void setBackwardLimit(final DigitalInput backwardLimit) {
		RobotMap.backwardLimit = backwardLimit;
	}

	public static void setDigital0(final DigitalInput digital0) {
		RobotMap.digital0 = digital0;
	}

	public static void setDigital1(final DigitalInput digital1) {
		RobotMap.digital1 = digital1;
	}

	public static void setDigital2(final DigitalInput digital2) {
		RobotMap.digital2 = digital2;
	}

	public static void setDigital3(final DigitalInput digital3) {
		RobotMap.digital3 = digital3;
	}

	public static void setDigital4(final DigitalInput digital4) {
		RobotMap.digital4 = digital4;
	}

	public static void setDigital5(final DigitalInput digital5) {
		RobotMap.digital5 = digital5;
	}

	public static void setDigital6(final DigitalInput digital6) {
		RobotMap.digital6 = digital6;
	}

	public static void setDigital7(final DigitalInput digital7) {
		RobotMap.digital7 = digital7;
	}

	public static void setDigital8(final DigitalInput digital8) {
		RobotMap.digital8 = digital8;
	}

	public static void setDigital9(final DigitalInput digital9) {
		RobotMap.digital9 = digital9;
	}

	public static void setDriveTrainLeft1(final Talon driveTrainLeft1) {
		RobotMap.driveTrainLeft1 = driveTrainLeft1;
	}

	public static void setDriveTrainLeft2(final Talon driveTrainLeft2) {
		RobotMap.driveTrainLeft2 = driveTrainLeft2;
	}

	public static void setDriveTrainRight1(final Talon driveTrainRight1) {
		RobotMap.driveTrainRight1 = driveTrainRight1;
	}

	public static void setDriveTrainRight2(final Talon driveTrainRight2) {
		RobotMap.driveTrainRight2 = driveTrainRight2;
	}

	public static void setEncoderLeft(final Encoder encoderLeft) {
		RobotMap.encoderLeft = encoderLeft;
	}

	public static void setEncoderRight(final Encoder encoderRight) {
		RobotMap.encoderRight = encoderRight;
	}

	public static void setFlapper(final Solenoid flapper) {
		RobotMap.flapper = flapper;
	}

	public static void setForwardLimit(final DigitalInput forwardLimit) {
		RobotMap.forwardLimit = forwardLimit;
	}

	public static void setIntakeExtender(final Solenoid intakeExtender) {
		RobotMap.intakeExtender = intakeExtender;
	}

	public static void setIntakeMotor(final Talon intakeMotor) {
		RobotMap.intakeMotor = intakeMotor;
	}

	public static void setLatch(final Solenoid latch) {
		RobotMap.latch = latch;
	}

	public static void setPnuematicsShifter(final Solenoid pnuematicsShifter) {
		RobotMap.pnuematicsShifter = pnuematicsShifter;
	}

	public static void setShooterLimit(final DigitalInput shooterLimit) {
		RobotMap.shooterLimit = shooterLimit;
	}

	public static void setTensioner(final CANTalon tensioner) {
		RobotMap.tensioner = tensioner;
	}
}
