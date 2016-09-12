package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ControlAim extends Command {

	public enum aimState {
		onbaord, cpu
	}

	static aimState state;

	public ControlAim() {
		requires(Robot.getCamera());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// if(Robot.oi.changeAim1.get() && Robot.oi.changeAim2.get())
		// {
		// if(Robot.aimingState == aimState.cpu)
		// Robot.aimingState = aimState.onbaord;
		// else
		// Robot.aimingState = aimState.cpu;
		// }
		if (Robot.getOi().getAimLeft().get()) {
			if (Robot.getAimingstate() == aimState.cpu)
				Scheduler.getInstance().add(new Aim(0));
			else
				Scheduler.getInstance().add(new AimOnboard(0));
		}
		if (Robot.getOi().getAimRight().get()) {
			if (Robot.getAimingstate() == aimState.cpu)
				Scheduler.getInstance().add(new Aim(2));
			else
				Scheduler.getInstance().add(new AimOnboard(2));
		}
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		// state = aimState.onbaord;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	/**
	 * @noinspection SameReturnValue
	 */ // Make this return true when this Command no longer needs to run
		// execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
}
