package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Gamepad;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveArm extends Command {
	CANTalon arm = RobotMap.arm;
	private final double threshold = 10;
	private final double power = .5;
	private final double positionLow = 0;
	private final double positionMiddle = 125;
	private final double positionUp = 250;

	public MoveArm() {
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("armvalue", Robot.arm.getPotValue());

		arm.set(Robot.oi.gamepad.getLeftY());
		double targetPosition =-500;
		if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.W))
			targetPosition = positionLow;
		else if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.N))
			targetPosition = positionMiddle;
		else if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.E))
			targetPosition = positionUp;

		if (targetPosition != -500) {
			if (Math.abs(Robot.arm.getPotValue()) - targetPosition < threshold) {
				Robot.arm.moveArm(0);
			}
			if (Robot.arm.getPotValue() > targetPosition) {
				Robot.arm.moveArm(-power);
			} else
				Robot.arm.moveArm(power);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
