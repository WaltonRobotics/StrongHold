package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;

import edu.wpi.first.wpilibj.command.Command;

class MoveToObstacle extends Command {
	private Compass thisCompass;
	private double threshold;

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// Create an if statement which calls is finished
		if (isFinished()) {
			end();
		}

	}

	@Override
	protected void initialize() {
		Robot.driveTrain.setSpeeds(25, 25);// Magic number - change this after
		// testing with actual robot
		this.thisCompass = new Compass();
		thisCompass.initializeCompass();
		threshold = 25; // another magic number to test
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// Create an if statement which will call end
		return thisCompass.getYaw() > threshold;
	}

}
