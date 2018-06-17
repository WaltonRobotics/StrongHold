package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class DriveStraight extends Command {

	double time;
	double speed;
	double startTime;

	public DriveStraight(double time, double speed) {
		this.speed = speed;
		this.time = time;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.setSpeeds(speed, speed);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > time;
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("Autonomous stuff", "There ya go, you moved forward");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
