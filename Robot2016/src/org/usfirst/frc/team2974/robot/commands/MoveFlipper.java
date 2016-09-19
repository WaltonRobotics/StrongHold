package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFlipper extends Command {
	private boolean bool2;
	private double time2;
	private boolean bool;
	private double time;

	public MoveFlipper() {
		requires(Robot.getFlipper());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.getOi().getFlapperDown().get()) {
			// if(Robot.intake.getState() == Intake.IntakeExtenderState.out)
			if (!bool) {
				Robot.getIntake().extend();
				time = Timer.getFPGATimestamp();
				bool = true;
			} else if (Timer.getFPGATimestamp() - time > .4)
				bool = false;
			else if (Timer.getFPGATimestamp() - time > .2) {
				Robot.getFlipper().setFlapper(Flipper.FlipperState.down);
				bool = false;
			}
		} else
			// if(Robot.intake.getState() == Intake.IntakeExtenderState.out)
			// if (!bool2) {
			// Robot.intake.extend();
			// time2 = Timer.getFPGATimestamp();
			// bool2 = true;
			// } else {
			// if (Timer.getFPGATimestamp() - time2 > .4) {
			// bool2 = false;
			// } else if (Timer.getFPGATimestamp() - time2 > .1) {
			Robot.getFlipper().setFlapper(Flipper.FlipperState.up);
		// bool2 = false;
		// }

		// else
		// Scheduler.getInstance().add(new IntakeOut());
		// }

	}

	public double getTime2() {
		return time2;
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

	public boolean isBool2() {
		return bool2;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	public void setBool2(final boolean bool2) {
		this.bool2 = bool2;
	}

	public void setTime2(final double time2) {
		this.time2 = time2;
	}
}
