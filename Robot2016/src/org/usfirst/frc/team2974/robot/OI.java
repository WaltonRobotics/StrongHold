package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.robot.autonomousCommandGroups.ActivateAuton;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.LowBar;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.Moat;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.MoveToShoot;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.RockWall;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.ShootParts;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMCSpecial;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
//import org.usfirst.frc.team2974.robot.autonomousCommands.TurnRight;
import org.usfirst.frc.team2974.robot.commands.DriveStraitSpecial;
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
	
	public Joystick left;
	public Joystick right;
	public Gamepad gamepad; 
	
	public Button shiftUp;
	public Button shiftDown;
	
	public Button flapperDown;
	public Button intake;
	public Button outtake;
	public Button stoptake;
	public Button retractIntake;
	public Button extendIntake;
	
	public Button changeAim1;
	public Button changeAim2;
	public Button aimLeft;
	public Button aimRight;
	
	public Button shoot;
	
	public Button resetShooter;
	public Button startUntensioning;
	public Button startTensioning;
	public Button normalTensioning;
	public Button testCommand;
	
	public boolean autoShoot;
	
	public OI()
	{
		//SmartDashboard.putData(new DriveSpline());
		
		left = new Joystick(0);
		right = new Joystick(1);
		gamepad = new Gamepad(2);
			
		shiftUp = new JoystickButton (left,3);
		shiftDown = new JoystickButton (left,2);
		
		flapperDown = new JoystickButton(gamepad,8);
		
		intake = new JoystickButton(gamepad, 1);
		outtake = new JoystickButton(gamepad, 3);
		stoptake = new JoystickButton(gamepad, 2);
		
		extendIntake = new JoystickButton(gamepad, 5);
		retractIntake = new JoystickButton(gamepad, 6);
		
		shoot = new JoystickButton(right, 1);
		
		changeAim1 = new JoystickButton(left, 6);
		changeAim2 = new JoystickButton(right, 6);
				
		aimLeft = new JoystickButton(right,4);
		aimRight = new JoystickButton(right,5);
		
		startUntensioning = new JoystickButton(left, 8);
		startTensioning = new JoystickButton(left, 11);
		normalTensioning = new JoystickButton(left, 9);
		resetShooter = new JoystickButton(left, 10);
		
		startUntensioning.whenPressed(new UnTension());
		startTensioning.whenPressed(new Tension());
		
		resetShooter.whenPressed(new ShooterReset());
//		aimLeft.whenPressed(new Aim(0));
//		aimRight.whenPressed(new Aim(2));
		
		
		autoShoot = false;
		
		//testAuton = new JoystickButton(gamepad, 6);//remove l8er TODO
		//testAuton.whenPressed(new TestAuton());
		SmartDashboard.putData("Drive Forward 1m", new DriveStraightMC(1.0, 0.5, 0.1));
		SmartDashboard.putData("Drive to Obstiqle", new DriveStraightMC(-1.1, 0.7, 0.7));
		SmartDashboard.putData("DriveStraitSpecial", new DriveStraitSpecial());
		SmartDashboard.putData("DriveStraitMCSpecial", new DriveStraightMCSpecial());
		SmartDashboard.putNumber("SpecialPower", 1);
		SmartDashboard.putNumber("SpecialTime", 1);
		SmartDashboard.putNumber("SpecialAccel", .3);
		SmartDashboard.putData("Turn180",new TurnLeft());		
		SmartDashboard.putData("MoveToShootA",new MoveToShoot(new AutonLocator(AutonPossibleLocation.A)));
		SmartDashboard.putData("MoveToShootB",new MoveToShoot(new AutonLocator(AutonPossibleLocation.B)));
		SmartDashboard.putData("MoveToShootC",new MoveToShoot(new AutonLocator(AutonPossibleLocation.C)));
		SmartDashboard.putData("MoveToShootD",new MoveToShoot(new AutonLocator(AutonPossibleLocation.D)));
		SmartDashboard.putData("MoveToShootE",new MoveToShoot(new AutonLocator(AutonPossibleLocation.E)));
		SmartDashboard.putData("AutonLowBar",new ActivateAuton(new LowBar(), new AutonLocator(AutonPossibleLocation.A)));
		SmartDashboard.putData("AutonRockB",new ActivateAuton(new RockWall(), new AutonLocator(AutonPossibleLocation.B)));
		SmartDashboard.putData("ShootParts",new ShootParts());
		SmartDashboard.putData("AutonMoatC",new ActivateAuton(new Moat(), new AutonLocator(AutonPossibleLocation.C)));
	}

}

