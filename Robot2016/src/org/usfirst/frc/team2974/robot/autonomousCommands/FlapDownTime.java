package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper.FlipperState;
import org.usfirst.frc.team2974.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FlapDownTime extends Command {
	private final double time;
	private double startTime;

	public FlapDownTime(final double time) {
		requires(Robot.getFlipper());
		this.time = time;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.getFlipper().setFlapper(FlipperState.up);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.getIntake().getState() == Intake.IntakeExtenderState.out)
			Robot.getFlipper().setFlapper(FlipperState.down);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
		Robot.getIntake().extend();

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
