package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.robot.autonomousCommands.DriveSpline;

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
	public Button shoot;
	public Button flapperUp;
	public Button flapperDown;
	public Button aim;
	public Button latchButton1;
	public Button latchButton2;
	public Button position1;
	public Button position2;
	public Button position3;
	
	public OI()
	{
		SmartDashboard.putData(new DriveSpline());
		left = new Joystick(0);
		right = new Joystick(1);
		gamepad =new Gamepad(2);
		
		shiftUp = new JoystickButton (left,3);
		shiftDown = new JoystickButton (left,2);
		intake = new JoystickButton(left, 4);
		outtake = new JoystickButton(left,5);
		shoot = new JoystickButton(left, 6);
		flapperUp = new JoystickButton(right,3);
		flapperDown = new JoystickButton(right, 2);
		aim = new JoystickButton(right, 0);
		latchButton1 = new JoystickButton(right, 4);
		latchButton2 = new JoystickButton(right, 5);
		position1 = new JoystickButton(right, 6);
		position2 = new JoystickButton(right, 7);
		position3 = new JoystickButton(right, 8);
		
	}

}

