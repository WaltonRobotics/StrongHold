package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmDown extends Command {

	private double startTime;

	public ArmDown() {
		requires(Robot.getArm());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.getArm().moveArmPower(0);
		SmartDashboard.putString("Autonomous stuff", "There ya go, arm is down");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		final double armPower = -1;
		Robot.getArm().moveArmPower(armPower);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.getArm().moveArmPower(0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		final double totalTime = .2;
		return Timer.getFPGATimestamp() - startTime > totalTime;
	}
}
