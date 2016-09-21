package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class ShootTemp extends Command {

	public ShootTemp() {
		requires(Robot.getShooter());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// get rid of following code
		if (Robot.getOi().getRight().getRawButton(4))
			Robot.getShooter().tension();
		else if (Robot.getOi().getRight().getRawButton(5))
			Robot.getShooter().unTension();
		else
			Robot.getShooter().setZero();
		if (Robot.getOi().getRight().getRawButton(10))
			Robot.getShooter().latch();
		else if (Robot.getOi().getRight().getRawButton(11))
			Robot.getShooter().unlatch();

		// get rid of following code
		// if(Robot.oi.latchButton1.get())
		// Robot.shooter.latch();
		// if(Robot.oi.latchButton2.get())
		// Robot.shooter.unlatch();
		// Log.instance().logCall(new LogMessage(Severity.INFORMATION,
		// SubSystem.SHOOTER, "Shooter", "Shooter State" +
		// Robot.shooter.getState(), 120));
		// SmartDashboard.putString("Shooter State",
		// currentState.getClass()+"");
		SmartDashboard.putString("Tensioner State", Robot.getShooter().getState() + "");
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return !isRunning();
	}
}
