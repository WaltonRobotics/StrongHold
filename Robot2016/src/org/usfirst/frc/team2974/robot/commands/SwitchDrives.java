package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchDrives extends Command {

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		requires(Robot.driveTrain);
		if (Drive.isTank) {
			Drive.isTank = false;
		} else {
			Drive.isTank = true;
		}
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
