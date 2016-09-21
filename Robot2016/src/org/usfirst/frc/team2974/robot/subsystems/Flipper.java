package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveFlipper;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Flipper extends Subsystem {

	public enum FlipperState {
		up, down
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final Solenoid flapper = RobotMap.getFlapper();

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new MoveFlipper());
	}

	//
	public void setFlapper(final FlipperState state) {
		switch (state) {
		case up:
			flapper.set(false);
			break;
		case down:
			flapper.set(true);
		}
	}
}
