package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
import org.usfirst.frc.team2974.robot.commands.ShooterReset;
import org.usfirst.frc.team2974.robot.commands.Tension;
import org.usfirst.frc.team2974.robot.commands.UnTension;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private final Joystick left;
	private final Joystick right;
	private final Gamepad gamepad;

	private final Button shiftUp;

	private final Button shiftDown;

	private final Button flapperDown;

	private final Button retractIntake;

	private final Button extendIntake;
	private final Button intake;
	private final Button outtake;

	private final Button stoptake;
	private final Button changeAim1;
	private final Button changeAim2;

	private final Button aimLeft;
	private final Button aimRight;

	private final Button shoot;
	private final Button normalTensioning;

	private boolean autoShoot;

	public Button testAuton;

	public OI() {
		// SmartDashboard.putData(new DriveSpline());

		left = new Joystick(0);
		right = new Joystick(1);
		gamepad = new Gamepad(2);

		shiftUp = new JoystickButton(getLeft(), 3);
		shiftDown = new JoystickButton(getLeft(), 2);

		flapperDown = new JoystickButton(gamepad, 8);

		intake = new JoystickButton(gamepad, 1);
		outtake = new JoystickButton(gamepad, 3);
		stoptake = new JoystickButton(gamepad, 2);

		extendIntake = new JoystickButton(gamepad, 5);
		retractIntake = new JoystickButton(gamepad, 6);

		shoot = new JoystickButton(right, 1);

		changeAim1 = new JoystickButton(getLeft(), 6);
		changeAim2 = new JoystickButton(right, 6);

		aimLeft = new JoystickButton(right, 4);
		aimRight = new JoystickButton(right, 5);

		Button startUntensioning = new JoystickButton(getLeft(), 8);
		Button startTensioning = new JoystickButton(getLeft(), 11);
		normalTensioning = new JoystickButton(getLeft(), 9);
		Button resetShooter = new JoystickButton(getLeft(), 10);

		startUntensioning.whenPressed(new UnTension());
		startTensioning.whenPressed(new Tension());

		resetShooter.whenPressed(new ShooterReset());

		Button testCommand = new JoystickButton(right, 2);

		testCommand.whenPressed(new DriveLocate());
		// aimLeft.whenPressed(new Aim(0));
		// aimRight.whenPressed(new Aim(2));

		setAutoShoot(false);

		// testAuton = new JoystickButton(gamepad, 6);//remove l8er TODO
		// testAuton.whenPressed(new TestAuton());
		SmartDashboard.putData("Drive Forward 1m", new DriveStraightMC(1.0, 0.5, 0.1));
	}

	public Button getAimLeft() {
		return aimLeft;
	}

	public Button getAimRight() {
		return aimRight;
	}

	public Button getChangeAim1() {
		return changeAim1;
	}

	public Button getChangeAim2() {
		return changeAim2;
	}

	public Button getExtendIntake() {
		return extendIntake;
	}

	public Button getFlapperDown() {
		return flapperDown;
	}

	public Gamepad getGamepad() {
		return gamepad;
	}

	public Button getIntake() {
		return intake;
	}

	public Joystick getLeft() {
		return left;
	}

	public Button getNormalTensioning() {
		return normalTensioning;
	}

	public Button getOuttake() {
		return outtake;
	}

	public Button getRetractIntake() {
		return retractIntake;
	}

	public Joystick getRight() {
		return right;
	}

	public Button getShiftDown() {
		return shiftDown;
	}

	public Button getShiftUp() {
		return shiftUp;
	}

	public Button getShoot() {
		return shoot;
	}

	public Button getStoptake() {
		return stoptake;
	}

	public boolean isAutoShoot() {
		return autoShoot;
	}

	public void setAutoShoot(boolean autoShoot) {
		this.autoShoot = autoShoot;
	}

}
