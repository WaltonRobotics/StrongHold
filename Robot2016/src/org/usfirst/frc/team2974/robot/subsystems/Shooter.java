package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Shoot;

/**
 *
 */
public class Shooter extends Subsystem {

	private final double maxTensionerPower = 0.450;
	private final double holdTensionerPower = .030;
	Solenoid latch = RobotMap.latch;
	CANTalon tensioner = RobotMap.tensioner;
	boolean isInit = false;
	private TensionerState state;

	// private final double ForwardThreshold = 1000;
	// private final double ReverseThreshold = 300;
	// private final double ForwardLimit = 135000;
	// private final double ReverseLimit = 1300;

	private DigitalInput forwardLimitSwitch = RobotMap.forwardLimit;
	private DigitalInput reverseLimitSwitch = RobotMap.backwardLimit;
	private DigitalInput shooterLimitSwitch = RobotMap.shooterLimit;

	public Shooter() {
		state = TensionerState.untensioned;
		tensioner.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		tensioner.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		tensioner.reset();
		tensioner.enable();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Shoot());
	}

	public void latch() {
		latch.set(false);
	}

	public void unlatch() {
		latch.set(true);
	}

	public void tension() {
		setTensionerPower(maxTensionerPower);
		state = TensionerState.tensioning;
	}

	public void unTension() {
		setTensionerPower(-maxTensionerPower);
		state = TensionerState.untensioning;
	}

	public void holdTension() {
		setTensionerPower(holdTensionerPower);
	}

	public TensionerState getState() {
		// if (getTensionerValue() > ForwardLimit - ForwardThreshold ||
		// isForwardLimit())
		// state = TensionerState.tensioned;
		// else if (getTensionerValue() < ReverseLimit + ReverseThreshold ||
		// isReverseLimit())
		// state = TensionerState.untensioned;
		if (isForwardLimit()) {
			state = TensionerState.tensioned;
		} else if (isReverseLimit()) {
			state = TensionerState.untensioned;
		}
		return state;
	}

	public boolean isForwardLimit() {
		return forwardLimitSwitch.get();
	}

	public boolean isReverseLimit() {
		return reverseLimitSwitch.get();
	}

	public void setZero() {
		tensioner.set(0);
	}

	public boolean isShooterDown() {
		return !shooterLimitSwitch.get();
	}

	private void setTensionerPower(double power) {
		if (power < 0 && isReverseLimit()) {
			setZero();
			// tensioner.setEncPosition(1000);
			isInit = true;
		} else if (power > 0 && isForwardLimit()) {
			setZero();

		} else {
			tensioner.set(power);
			SmartDashboard.putString("im going to power", "" + power);
		}

		// if(isForwardLimit())
		// tensioner.setEncPosition(130000);
		// else if(isReverseLimit())
		// tensioner.setEncPosition(1000);

	}

	public double getTensionerValue() {
		return tensioner.getEncPosition();

	}

	public void dumpSmartDashboardValues() {
		SmartDashboard.putNumber("Tensioner Encoder", Robot.shooter.getTensionerValue());
		SmartDashboard.putNumber("Raw tensioner encoder value", tensioner.getAnalogInPosition());
	}

	public enum TensionerState {
		tensioned, untensioned, tensioning, untensioning
	}

}
