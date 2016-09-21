package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Shoot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	public enum TensionerState {
		tensioned, untensioned, tensioning, untensioning
	}

	private final Solenoid latch = RobotMap.getLatch();
	private final CANTalon tensioner = RobotMap.getTensioner();
	private final double maxTensionerPower = 0.450;

	private final DigitalInput forwardLimitSwitch = RobotMap.getForwardLimit();
	// private final double ForwardThreshold = 1000;
	// private final double ReverseThreshold = 300;
	// private final double ForwardLimit = 135000;
	// private final double ReverseLimit = 1300;
	private final DigitalInput reverseLimitSwitch = RobotMap.getBackwardLimit();
	private final DigitalInput shooterLimitSwitch = RobotMap.getShooterLimit();

	private TensionerState state;

	public Shooter() {
		state = TensionerState.untensioned;
		tensioner.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		tensioner.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		tensioner.reset();
		tensioner.enable();
	}

	public void dumpSmartDashboardValues() {
		SmartDashboard.putNumber("Tensioner Encoder", Robot.getShooter().getTensionerValue());
		SmartDashboard.putNumber("Raw tensioner encoder value", tensioner.getAnalogInPosition());
	}

	public TensionerState getState() {
		// if (getTensionerValue() > ForwardLimit - ForwardThreshold ||
		// isForwardLimit())
		// state = TensionerState.tensioned;
		// else if (getTensionerValue() < ReverseLimit + ReverseThreshold ||
		// isReverseLimit())
		// state = TensionerState.untensioned;
		if (isForwardLimit())
			state = TensionerState.tensioned;
		else if (isReverseLimit())
			state = TensionerState.untensioned;
		return state;
	}

	private double getTensionerValue() {
		return tensioner.getEncPosition();

	}

	public void holdTension() {
		final double holdTensionerPower = .030;
		setTensionerPower(holdTensionerPower);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Shoot());
	}

	private boolean isForwardLimit() {
		return forwardLimitSwitch.get();
	}

	private boolean isReverseLimit() {
		return reverseLimitSwitch.get();
	}

	public boolean isShooterDown() {
		return !shooterLimitSwitch.get();
	}

	public void latch() {
		latch.set(false);
	}

	private void setTensionerPower(final double power) {
		if (power < 0 && isReverseLimit())
			setZero();
		// tensioner.setEncPosition(1000);
		else if (power > 0 && isForwardLimit())
			setZero();
		else {
			tensioner.set(power);
			SmartDashboard.putString("im going to power", String.valueOf(power));
		}

		// if(isForwardLimit())
		// tensioner.setEncPosition(130000);
		// else if(isReverseLimit())
		// tensioner.setEncPosition(1000);

	}

	public void setZero() {
		tensioner.set(0);
	}

	public void tension() {
		setTensionerPower(maxTensionerPower);
		state = TensionerState.tensioning;
	}

	public void unlatch() {
		latch.set(true);
	}

	public void unTension() {
		setTensionerPower(-maxTensionerPower);
		state = TensionerState.untensioning;
	}
}
