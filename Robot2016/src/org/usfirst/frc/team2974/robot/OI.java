package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team2974.robot.commands.Aim;


/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups
 * that allow control of the robot.
 */
public class OI {

	public Joystick left;
	public Joystick right;
	public Gamepad gamepad;

	public Button shiftUp;
	public Button shiftDown;

	public Button flapperDown;
	public Button retractIntake;
	public Button extendIntake;

	public Button aimLeft;
	public Button aimRight;

	public Button shoot;

	public Button normalTensioning;

	public boolean autoShoot;

	public Button testAuton;

	public OI() {
		//SmartDashboard.putData(new DriveSpline());

		left = new Joystick(0);
		right = new Joystick(1);
		gamepad = new Gamepad(2);

		shiftUp = new JoystickButton(left, 3);
		shiftDown = new JoystickButton(left, 2);

		normalTensioning = new JoystickButton(left, 9);

		flapperDown = new JoystickButton(gamepad, 8);

		extendIntake = new JoystickButton(gamepad, 5);
		retractIntake = new JoystickButton(gamepad, 6);

		shoot = new JoystickButton(right, 1);

		aimLeft = new JoystickButton(right, 4);
		aimRight = new JoystickButton(right, 5);

		aimLeft.whenPressed(new Aim(0));
		aimRight.whenPressed(new Aim(2));

		autoShoot = false;

		//testAuton = new JoystickButton(gamepad, 6);//remove l8er TODO
		//testAuton.whenPressed(new TestAuton());
	}
}

