package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
class TestAuton extends Command {

	public TestAuton() {

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		// Robot.autonomousCommand.cancel();
		// SendableChooser autoChooser = Robot.autoChooser;
		// Command autonomousCommand = (Command) autoChooser.getSelected();
		// autonomousCommand.start();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;// change this fool
	}
}
