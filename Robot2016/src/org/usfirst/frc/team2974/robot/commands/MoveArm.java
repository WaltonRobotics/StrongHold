package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveArm extends Command {
	private final Arm arm = Robot.getArm();

	public MoveArm() {
		requires(arm);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("armvalue", Robot.getArm().getPotValue());

		// if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.W))
		// arm.moveArmPosition(positionLow);
		// else if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.N))
		// arm.moveArmPosition(positionMiddle);
		// else if (Robot.oi.gamepad.getPOVButton(Gamepad.POV.E))
		// arm.moveArmPosition(positionUp);
		// else
		arm.moveArmPower(-1 * Robot.getOi().getGamepad().getLeftY());

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
}
