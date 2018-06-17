package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveFlipper;

/**
 *
 */
public class Flipper extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid flapper = RobotMap.flapper;

	public void initDefaultCommand() {
		setDefaultCommand(new MoveFlipper());
	}

	//
	public void setFlapper(FlipperState state) {
		switch (state) {
			case up:
				flapper.set(false);
				break;
			case down:
				flapper.set(true);
		}
	}

	public enum FlipperState {
		up, down
	}
}

