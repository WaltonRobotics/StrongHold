package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class Shoot extends Command {

	double startTime;
	double time = .5;

	public Shoot() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {

		startTime = Timer.getFPGATimestamp();
		Robot.oi.autoShoot = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putBoolean("autoshoot", Robot.oi.autoShoot);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > time;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.oi.autoShoot = false;
		SmartDashboard.putString("Autonomous stuff", "There ya go, ball in da goal");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.oi.autoShoot = false;
	}
}
