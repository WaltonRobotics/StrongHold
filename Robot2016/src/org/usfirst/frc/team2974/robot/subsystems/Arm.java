package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveArm;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {
	private final CANTalon arm = RobotMap.arm;
	private final AnalogPotentiometer armPot = RobotMap.armPot;

	// private final double zeroPosition = 0;
	// private PIDController pid = new PIDController(1, 0, 0, armPot, arm);
	// private double absoluteTolerance = 10;
	public Arm() {
		// pid.enable();
		// pid.setAbsoluteTolerance(absoluteTolerance);
		arm.enable();
	}

	public void dumpSmartDashboardValues() {
		SmartDashboard.putNumber("Arm Potentiometer", armPot.get());
	}

	public double getPotValue() {
		return armPot.get();
	}

	// public void disablePID()
	// {
	// //pid.disable();
	// }
	//
	// public void enablePID()
	// {
	// //pid.enable();
	// }
	//
	// public void moveArmPosition(double position)
	// {
	// //if(!pid.isEnabled())
	// //pid.enable();
	// //position -=zeroPosition;
	// //pid.setSetpoint(position);
	// }

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new MoveArm());
	}

	public void moveArmPower(final double power) {
		// if(pid.isEnabled())
		// pid.disable();
		arm.set(power);
	}
}
