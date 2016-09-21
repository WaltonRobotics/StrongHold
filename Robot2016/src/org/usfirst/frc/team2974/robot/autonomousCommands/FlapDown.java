package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class FlapDown extends Command {

	public FlapDown() {
		requires(Robot.getFlipper());
		// requires(Robot.intake);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.getIntake().getState() == Intake.IntakeExtenderState.out)
			Robot.getFlipper().setFlapper(Flipper.FlipperState.down);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Scheduler.getInstance().add(new IntakeIn());
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
		return Robot.getShooter().isShooterDown();
	}
}
