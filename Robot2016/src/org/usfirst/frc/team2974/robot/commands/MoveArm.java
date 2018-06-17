package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Arm;

/**
 *
 */
public class MoveArm extends Command {

	Arm arm = Robot.arm;

	public MoveArm() {
		requires(arm);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("armvalue", Robot.arm.getPotValue());

//		if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.W))
//			arm.moveArmPosition(positionLow);
//		else if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.N))
//			arm.moveArmPosition(positionMiddle);
//		else if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.E))
//			arm.moveArmPosition(positionUp);
//		else
		arm.moveArmPower(-1 * Robot.oi.gamepad.getLeftY());

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

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}
}
