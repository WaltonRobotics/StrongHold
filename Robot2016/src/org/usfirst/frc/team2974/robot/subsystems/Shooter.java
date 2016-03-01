package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.ShootTemp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		tensioner.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		tensioner.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		tensioner.enable();
	}

	public enum TensionerState {
		tensioned, untensioned, tensioning, untensioning
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShootTemp());
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
		if (getTensionerValue() > ForwardLimit - ForwardThreshold )
			state = TensionerState.tensioned;
		else if (getTensionerValue() < ReverseLimit + ReverseThreshold) 
			state = TensionerState.untensioned;
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
		if (power < 0 && isReverseLimit())
			setZero();
		else if (power > 0 && isForwardLimit())
			setZero();
		else
			tensioner.set(power);
	}

	public double getTensionerValue() {
		return tensioner.getAnalogInPosition();
	}
	
	public void dumpSmartDashboardValues()
	{
		SmartDashboard.putNumber("Tensioner Encoder", Robot.shooter.getTensionerValue());
		SmartDashboard.putNumber("Raw tensioner encoder value", tensioner.getAnalogInPosition());
	}

}
