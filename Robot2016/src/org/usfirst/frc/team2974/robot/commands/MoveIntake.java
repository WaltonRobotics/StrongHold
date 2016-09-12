package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveIntake extends Command {
	private boolean bool;
	private double time;

	public MoveIntake() {
		requires(Robot.getIntake());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.getOi().getRetractIntake().get()) {
			if (!bool) {
				Robot.getFlipper().setFlapper(Flipper.FlipperState.up);
				time = Timer.getFPGATimestamp();
				bool = true;
			} else {
				if (Timer.getFPGATimestamp() - time > .4) {
					bool = false;
				} else if (Timer.getFPGATimestamp() - time > .2) {
					Robot.getIntake().retract();
					bool = false;
				}
			}
		} else if (Robot.getOi().getExtendIntake().get()) {
			Robot.getIntake().extend();
		}

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		bool = false;

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
