package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.IntakeExtender;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFlipper extends Command {
	boolean intakeOut;
	double time;
	IntakeExtender intakeExtender;
	public MoveFlipper() {
		requires(Robot.flipper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		intakeOut = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.flapperDown.get()) {
			if (!intakeOut) {
				intakeExtender.extend();
				time = Timer.getFPGATimestamp();
				intakeOut = true;
			} else if (Timer.getFPGATimestamp() - time > .2) {
				Robot.flipper.setFlipper(Flipper.FlipperState.down);
				intakeOut = false;
			}
		} else {
			Robot.flipper.setFlipper(Flipper.FlipperState.up);
			intakeOut = false;
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
