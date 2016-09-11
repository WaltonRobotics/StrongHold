package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
import org.usfirst.frc.team2974.robot.commands.ShooterReset;
import org.usfirst.frc.team2974.robot.commands.Tension;
import org.usfirst.frc.team2974.robot.commands.UnTension;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public final Joystick left;
    public final Joystick right;
    public final Gamepad gamepad;

    public final Button shiftUp;
    public final Button shiftDown;

    public final Button flapperDown;
    public final Button retractIntake;
    public final Button extendIntake;

    public final Button aimLeft;
    public final Button aimRight;

    public final Button shoot;

    public final Button normalTensioning;

    public boolean autoShoot;

    public Button testAuton;

    public OI() {
        // SmartDashboard.putData(new DriveSpline());

        left = new Joystick(0);
        right = new Joystick(1);
        gamepad = new Gamepad(2);

        shiftUp = new JoystickButton(left, 3);
        shiftDown = new JoystickButton(left, 2);

        flapperDown = new JoystickButton(gamepad, 8);

        Button intake = new JoystickButton(gamepad, 1);
        Button outtake = new JoystickButton(gamepad, 3);
        Button stoptake = new JoystickButton(gamepad, 2);

        extendIntake = new JoystickButton(gamepad, 5);
        retractIntake = new JoystickButton(gamepad, 6);

        shoot = new JoystickButton(right, 1);

        Button changeAim1 = new JoystickButton(left, 6);
        Button changeAim2 = new JoystickButton(right, 6);

        aimLeft = new JoystickButton(right, 4);
        aimRight = new JoystickButton(right, 5);

        Button startUntensioning = new JoystickButton(left, 8);
        Button startTensioning = new JoystickButton(left, 11);
        normalTensioning = new JoystickButton(left, 9);
        Button resetShooter = new JoystickButton(left, 10);

        startUntensioning.whenPressed(new UnTension());
        startTensioning.whenPressed(new Tension());

        resetShooter.whenPressed(new ShooterReset());

        Button testCommand = new JoystickButton(right, 2);

        testCommand.whenPressed(new DriveLocate());
        // aimLeft.whenPressed(new Aim(0));
        // aimRight.whenPressed(new Aim(2));

        autoShoot = false;

        // testAuton = new JoystickButton(gamepad, 6);//remove l8er TODO
        // testAuton.whenPressed(new TestAuton());
        SmartDashboard.putData("Drive Forward 1m", new DriveStraightMC(1.0, 0.5, 0.1));
    }

}
