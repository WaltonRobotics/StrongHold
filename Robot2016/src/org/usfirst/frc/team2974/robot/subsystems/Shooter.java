package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Shoot;
import org.usfirst.frc.team2974.robot.commands.ShootTemp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	Solenoid latch = RobotMap.latch;
	CANTalon tensioner = RobotMap.tensioner;

	private final double maxTensionerPower = 0.25;
	private final double holdTensionerPower = .125;

	private TensionerState state;

	private final double ForwardThreshold = .1;
	private final double ReverseThreshold = .1;
	private final double ForwardLimit = 10;
	private final double ReverseLimit = 30;

	private DigitalInput forwardLimitSwitch = RobotMap.forwardLimit;
	private DigitalInput reverseLimitSwitch = RobotMap.backwardLimit;
	private DigitalInput shooterLimitSwitch = RobotMap.shooterLimit;

	public Shooter() {
		state = TensionerState.untensioned;
		tensioner.reset();
		tensioner.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		tensioner.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}

	public enum TensionerState {
		tensioned, untensioned, tensioning, untensioning
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShootTemp());
	}

	public void latch() {
		latch.set(true);
	}

	public void unlatch() {
		latch.set(false);
	}

	public void tension() {
		if (!isForwardLimit())
			tensioner.set(maxTensionerPower);
		else
			tensioner.set(0);
		state = TensionerState.tensioning;
	}

	public void unTension() {
		if (!isReverseLimit())
			tensioner.set(-maxTensionerPower);
		else
			tensioner.set(0);
		state = TensionerState.untensioning;
	}

	public void atTensionLimit() {
		tensioner.set(holdTensionerPower);
	}

	public TensionerState getState() {
		if (tensioner.getAnalogInPosition() + ForwardThreshold > ForwardLimit) {
			state = TensionerState.tensioned;
		}
		if (tensioner.getAnalogInPosition() - ReverseThreshold < ReverseLimit) {
			state = TensionerState.untensioned;
		}
		return state;
	}

	private boolean isForwardLimit() {
		return forwardLimitSwitch.get();
	}

	private boolean isReverseLimit() {
		return reverseLimitSwitch.get();
	}

	public boolean isShooterDown() {
		return shooterLimitSwitch.get();
	}

	public double getTensionerValue() {
		return tensioner.getAnalogInPosition();
	}

}
