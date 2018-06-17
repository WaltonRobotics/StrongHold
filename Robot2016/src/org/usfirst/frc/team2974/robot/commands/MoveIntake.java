package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;

/**
 *
 */
public class MoveIntake extends Command {

	boolean bool;
	double time;

	public MoveIntake() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		bool = false;

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.retractIntake.get()) {
			if (!bool) {
				Robot.flipper.setFlapper(Flipper.FlipperState.up);
				time = Timer.getFPGATimestamp();
				bool = true;
			} else {
				if (Timer.getFPGATimestamp() - time > .4) {
					bool = false;
				} else if (Timer.getFPGATimestamp() - time > .2) {
					Robot.intake.retract();
					bool = false;
				}
			}
		} else if (Robot.oi.extendIntake.get()) {
			Robot.intake.extend();
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
