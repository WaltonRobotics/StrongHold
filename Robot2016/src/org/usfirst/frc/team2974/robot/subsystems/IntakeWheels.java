package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

//import org.usfirst.frc.team2974.robot.commands.LoadBall;

/**
 *
 */
public class IntakeWheels extends Subsystem {
	public enum WheelState {
		in, stop, out
	}

	private static WheelState state;

	private final Talon intakeMotor = RobotMap.getIntakeMotor();

	public IntakeWheels() {
		state = WheelState.stop;
	}

	public WheelState getState() {
		return state;
	}

	@Override
	public void initDefaultCommand() {
		// setDefaultCommand(new LoadBall());
	}

	public void input() {
		intakeMotor.set(-1);
		state = WheelState.in;
	}

	public void output() {
		final double speed = .39;
		intakeMotor.set(speed);
		state = WheelState.out;
	}

	public void stop() {
		intakeMotor.set(0);
		state = WheelState.stop;
	}

	// public void setMotor(double value)
	// {
	// intakeMotor.set(value);
	// }
}