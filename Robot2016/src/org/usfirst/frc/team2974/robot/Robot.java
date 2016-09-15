package org.usfirst.frc.team2974.robot;

import java.awt.Image;
import java.io.File;

import org.usfirst.frc.team2974.robot.autonomousCommandGroups.ChivalDeFreze;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.FullRunnableAuton;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.LowBar;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.Moat;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.Ramparts;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.RockWall;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.RoughTerrain;
import org.usfirst.frc.team2974.robot.autonomousCommands.DoNothing;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.autonomousCommands.MoveToObstacle;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;
import org.usfirst.frc.team2974.robot.commands.Aim;
import org.usfirst.frc.team2974.robot.commands.ControlAim.aimState;
import org.usfirst.frc.team2974.robot.commands.Drive;
import org.usfirst.frc.team2974.robot.commands.ShowInputs;
import org.usfirst.frc.team2974.robot.commands.SwitchDrives;
import org.usfirst.frc.team2974.robot.subsystems.Arm;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.Inputs;
import org.usfirst.frc.team2974.robot.subsystems.Intake;
import org.usfirst.frc.team2974.robot.subsystems.IntakeWheels;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final aimState aimingState = aimState.onbaord;
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Arm arm;
	public static Inputs inputs;
	public static Shooter shooter;
	public static Camera camera;
	public static Compass compass;
	public static Flipper flipper;
	public static Intake intake;
	private static SendableChooser autoChooser;
	private static SendableChooser locationChooser;
	private static Command autonomousCommand;

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		compass.initializeCompass();
		autonomousCommand = new FullRunnableAuton((CommandGroup) autoChooser.getSelected(),
				(AutonLocator) locationChooser.getSelected());
		autonomousCommand.start();
		Scheduler.getInstance().add(new ShowInputs());
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXLeft() - Aim.centerX) < Aim.threshold);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXRight() - Aim.centerX) < Aim.threshold);

	}

	private void createAutonomousChooser() {
		SmartDashboard.putString("Warning",
				"If you pick A as the location the code automatically switches to Low Bar.");

		locationChooser = new SendableChooser();
		locationChooser.addDefault("Do Nothing", new AutonLocator(AutonPossibleLocation.NO_AUTON_TAKEN));
		locationChooser.addDefault("A", new AutonLocator(AutonPossibleLocation.A));
		locationChooser.addDefault("B", new AutonLocator(AutonPossibleLocation.B));
		locationChooser.addDefault("C", new AutonLocator(AutonPossibleLocation.C));
		locationChooser.addDefault("D", new AutonLocator(AutonPossibleLocation.D));
		locationChooser.addDefault("E", new AutonLocator(AutonPossibleLocation.E));

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new DoNothing());
		// autoChooser.addObject("Lowbar", new LowBar());
		// autoChooser.addObject("Lowbar + shoot", new LowBarShoot());
		// autoChooser.addObject("LowBar return", new LowBarReturn());
		// autoChooser.addObject("Rough Terrain", new RoughTerrain());
		// autoChooser.addObject("rock wall 1", new RockWall1());
		// autoChooser.addObject("Rough terrain shoot right", new
		// RoughTerrainShootRight());
		// autoChooser.addObject("Rough terrain shoot center", new
		// RoughTerrainShootStraight());
		// autoChooser.addObject("Rough Terrain Return", new
		// RoughTerrainReturn());
		// autoChooser.addObject("Rock wall", new RockWall());
		// autoChooser.addObject("Rock wall shoot right", new
		// RockWallShootRight());
		// autoChooser.addObject("Rock wall shoot left", new
		// RockWallShootLeft());
		// autoChooser.addObject("Rock wall shoot center", new
		// RockwallShootStraight());
		// autoChooser.addObject("Rock Wall Return", new RockWallReturn());
		autoChooser.addObject("Rock wall", new RockWall());
		autoChooser.addObject("Rough Terain", new RoughTerrain());
		autoChooser.addObject("Low Bar", new LowBar());
		autoChooser.addObject("ChivalDeFreze", new ChivalDeFreze());
		autoChooser.addObject("Moat", new Moat());
		autoChooser.addObject("Ramparts", new Ramparts());

		SmartDashboard.putData("Pick Location", locationChooser);
		SmartDashboard.putData("Pick Obsticle", autoChooser);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		
		SmartDashboard.putString("Drive Mode:",Drive.driveMode);
		
		Robot.inputs.updateSmartDashboard();
		Robot.compass.dumpSmartDashboardValues();
		Robot.camera.setNetTable();
		Robot.camera.dumpSmartDshboardValues();
		Robot.arm.dumpSmartDashboardValues();
		Robot.shooter.dumpSmartDashboardValues();
		Robot.driveTrain.dumpSmartdashboardValues();

		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXLeft() - Aim.centerX) < Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.camera.getXRight() - Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.camera.getXRight() - Aim.centerX < 0);
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// try{
		// new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
		// }
		// catch(IOException e)
		// {
		// SmartDashboard.putString("Camera Error", "Camera not initialized, do
		// not use aim");
		// e.printStackTrace();
		// }
		RobotMap.init();

		driveTrain = new DriveTrain();
		inputs = new Inputs();
		arm = new Arm();
		shooter = new Shooter();
		camera = new Camera();
		compass = new Compass();
		flipper = new Flipper();
		intake = new Intake();
		IntakeWheels intakeWheels = new IntakeWheels();

		createAutonomousChooser();
		oi = new OI();

		CameraServer server = CameraServer.getInstance();
		server.setQuality(0);
		server.startAutomaticCapture("cam1");
		
		File imageFile = new File("TestImage.jpg");
		File errorImage = new File("ErrorMessage.png");
		
		if(imageFile.exists())
			SmartDashboard.putData("Image", (NamedSendable) imageFile);
		
		else if(errorImage.exists())
			SmartDashboard.putData("Image", (NamedSendable) errorImage);
		
		else
			SmartDashboard.putString("Image Error", "No image found: " + imageFile.getName() + ", " + errorImage.getName());
		
		SmartDashboard.putData("DriveLocate", new DriveLocate());
		SmartDashboard.putData("ChivalDeFreze", new ChivalDeFreze());
		SmartDashboard.putData("Rock wall", new RockWall());
		SmartDashboard.putData("Rough Terain", new RoughTerrain());
		SmartDashboard.putData("Low Bar", new LowBar());
		SmartDashboard.putData("Moat", new Moat());
		SmartDashboard.putData("Ramparts", new Ramparts());
		SmartDashboard.putData("TurnNorth",new TurnToAngle(0));
		SmartDashboard.putData("TurnSouth",new TurnToAngle(180));
		SmartDashboard.putData("TurnEast",new TurnToAngle(90));
		SmartDashboard.putData("TurnWest",new TurnToAngle(270));
		SmartDashboard.putData("ApproachForward",new MoveToObstacle(1));
		SmartDashboard.putData("ApproachBackward",new MoveToObstacle(-1));
		
		SmartDashboard.putData("Switch Drives", new SwitchDrives());
	}

	@Override
	public void teleopInit() {
		compass.initializeCompass();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new ShowInputs());

		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXRight() - Aim.centerX) < Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.camera.getXRight() - Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.camera.getXRight() - Aim.centerX < 0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXRight() - Aim.centerX) < Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.camera.getXRight() - Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.camera.getXRight() - Aim.centerX < 0);
		Robot.driveTrain.dumpSmartdashboardValues();
	}
}
