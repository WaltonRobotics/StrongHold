package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFlipper extends Command {
	boolean bool2;
	double time2;
	private boolean bool;
	private double time;

	public MoveFlipper() {
		requires(Robot.flipper);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.oi.flapperDown.get()) {
			// if(Robot.intake.getState() == Intake.IntakeExtenderState.out)
			if (!bool) {
				Robot.intake.extend();
				time = Timer.getFPGATimestamp();
				bool = true;
			} else {
				if (Timer.getFPGATimestamp() - time > .4) {
					bool = false;
				} else if (Timer.getFPGATimestamp() - time > .2) {
					Robot.flipper.setFlapper(Flipper.FlipperState.down);
					bool = false;
				}

			}
			// else
			// Scheduler.getInstance().add(new IntakeOut());
		} else {
			// if(Robot.intake.getState() == Intake.IntakeExtenderState.out)
			// if (!bool2) {
			// Robot.intake.extend();
			// time2 = Timer.getFPGATimestamp();
			// bool2 = true;
			// } else {
			// if (Timer.getFPGATimestamp() - time2 > .4) {
			// bool2 = false;
			// } else if (Timer.getFPGATimestamp() - time2 > .1) {
			Robot.flipper.setFlapper(Flipper.FlipperState.up);
			// bool2 = false;
			// }

		}

		// else
		// Scheduler.getInstance().add(new IntakeOut());
		// }

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
