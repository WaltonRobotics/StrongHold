package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.LoadBall;

/**
 *
 */
public class IntakeWheels extends Subsystem {

	static WheelState state;
	Talon intakeMotor = RobotMap.intakeMotor;
	private double speed = .39;

	public IntakeWheels() {
		state = WheelState.stop;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new LoadBall());
	}

	public WheelState getState() {
		return state;
	}

	public void input() {
		intakeMotor.set(-1);
		state = WheelState.in;
	}

	public void output() {
		intakeMotor.set(speed);
		state = WheelState.out;
	}

	public void stop() {
		intakeMotor.set(0);
		state = WheelState.stop;
	}

	public enum WheelState {
		in, stop, out
	}

//	    public void setMotor(double value)
//	    {
//	    	intakeMotor.set(value);
//	    }
}