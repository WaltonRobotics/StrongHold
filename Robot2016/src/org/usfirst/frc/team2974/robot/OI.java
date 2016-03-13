package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.robot.autonomousCommands.DriveSpline;
import org.usfirst.frc.team2974.robot.commands.Aim;
import org.usfirst.frc.team2974.robot.commands.ShooterReset;
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
	
	public Joystick left;
	public Joystick right;
	public Gamepad gamepad; 
	public Button shiftUp;
	public Button shiftDown;
	public Button intake;
	public Button outtake;
	public Button stoptake;
	public Button flapperUp;
	public Button flapperDown;
	public Button aim;
	public Button latchButton1;
	public Button latchButton2;
	public Button shoot;
	public Button resetShooter;
	public Button startUntentioning;
	public Button stopUntensioning;
	
	public OI()
	{
		//SmartDashboard.putData(new DriveSpline());
		
		left = new Joystick(0);
		right = new Joystick(1);
		gamepad = new Gamepad(2);
			
		shiftUp = new JoystickButton (left,2);
		shiftDown = new JoystickButton (left,3);
		intake = new JoystickButton(gamepad, 1);
		outtake = new JoystickButton(gamepad, 3);
		stoptake = new JoystickButton(gamepad, 2);
		shoot = new JoystickButton(right, 1);
		flapperUp = new JoystickButton(gamepad,7);
		flapperDown = new JoystickButton(gamepad, 8);
		aim = new JoystickButton(right, 2);
		startUntentioning = new JoystickButton(left, 8);
		stopUntensioning = new JoystickButton(left, 9);
		resetShooter = new JoystickButton(left, 10);

		startUntentioning.whenPressed(new UnTension());
		resetShooter.whenPressed(new ShooterReset());
		aim.whenPressed(new Aim());
	}

}

