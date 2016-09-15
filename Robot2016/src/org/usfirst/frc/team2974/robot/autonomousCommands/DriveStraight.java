package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.dataLogs.Message;
import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Use WarningMessages so that if there is an important offset when driving set
 * it will record a warning
 */
public class DriveStraight extends Command {
	private final double time;
	private final double speed;
	private double startTime;
	private double previousYawValue;

	public DriveStraight(double time, double speed) {
		requires(Robot.getCompass());
		requires(Robot.getDriveTrain());

		this.speed = speed;
		this.time = time;
		this.previousYawValue = Robot.getCompass().getYaw();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		SmartDashboard.putString("Autonomous stuff", "There ya go, you moved forward");
		Robot.getDriveTrain().setSpeeds(0, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.getDriveTrain().setSpeeds(speed, speed);

		final int offset = (int) previousYawValue - (int) Robot.getCompass().getYaw();

		if (Math.abs(offset) <= 1) {
			Message.addWarning("The robot is not moving staright there is an offset of ".concat(String.valueOf(offset)),
					this);
			previousYawValue = previousYawValue + offset;
		}
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
