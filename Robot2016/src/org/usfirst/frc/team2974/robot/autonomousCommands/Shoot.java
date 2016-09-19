package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {
	private double startTime;

	public Shoot() {

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.getOi().setAutoShoot(false);
		SmartDashboard.putString("Autonomous stuff", "There ya go, ball in da goal");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putBoolean("autoshoot", Robot.getOi().isAutoShoot());

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		startTime = Timer.getFPGATimestamp();
		Robot.getOi().setAutoShoot(true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.getOi().setAutoShoot(false);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		final double time = .5;
		return Timer.getFPGATimestamp() - startTime > time;
	}
}
