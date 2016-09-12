package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {
	private final double time;
	private final double speed;
	private double startTime;

	public DriveStraight(double time, double speed) {
		this.speed = speed;
		this.time = time;
		requires(Robot.driveTrain);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		SmartDashboard.putString("Autonomous stuff", "There ya go, you moved forward");
		Robot.driveTrain.setSpeeds(0, 0);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.driveTrain.setSpeeds(speed, speed);

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
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > time;
	}
}
