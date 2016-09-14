package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.dataLogs.WarningMessages;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.ChivalDeFreze;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.FullRunnableAuton;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.LowBar;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.Moat;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.Ramparts;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.RockWall;
import org.usfirst.frc.team2974.robot.autonomousCommandGroups.RoughTerrain;
import org.usfirst.frc.team2974.robot.autonomousCommands.DoNothing;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.commands.Aim;
import org.usfirst.frc.team2974.robot.commands.ControlAim.aimState;
import org.usfirst.frc.team2974.robot.commands.ShowInputs;
import org.usfirst.frc.team2974.robot.subsystems.Arm;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.Inputs;
import org.usfirst.frc.team2974.robot.subsystems.Intake;
//import org.usfirst.frc.team2974.robot.subsystems.IntakeWheels;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private static final aimState aimingState = aimState.onbaord;
	private static OI oi;
	private static DriveTrain driveTrain;
	private static Arm arm;
	private static Inputs inputs;
	private static Shooter shooter;
	private static Camera camera;
	private static Compass compass;
	private static Flipper flipper;
	private static Intake intake;
	private static SendableChooser autoChooser;
	private static SendableChooser locationChooser;
	private static Command autonomousCommand;

	public static aimState getAimingstate() {
		return aimingState;
	}

	public static Arm getArm() {
		return arm;
	}

	public static Camera getCamera() {
		return camera;
	}

	public static Compass getCompass() {
		return compass;
	}

	public static DriveTrain getDriveTrain() {
		return driveTrain;
	}

	public static Flipper getFlipper() {
		return flipper;
	}

	public static Inputs getInputs() {
		return inputs;
	}

	public static Intake getIntake() {
		return intake;
	}

	public static OI getOi() {
		return oi;
	}

	public static Shooter getShooter() {
		return shooter;
	}

	public static void setArm(Arm arm) {
		Robot.arm = arm;
	}

	public static void setCamera(Camera camera) {
		Robot.camera = camera;
	}

	public static void setCompass(Compass compass) {
		Robot.compass = compass;
	}

	public static void setDriveTrain(DriveTrain driveTrain) {
		Robot.driveTrain = driveTrain;
	}

	public static void setFlipper(Flipper flipper) {
		Robot.flipper = flipper;
	}

	public static void setInputs(Inputs inputs) {
		Robot.inputs = inputs;
	}

	public static void setIntake(Intake intake) {
		Robot.intake = intake;
	}

	public static void setOi(OI oi) {
		Robot.oi = oi;
	}

	public static void setShooter(Shooter shooter) {
		Robot.shooter = shooter;
	}

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
		getCompass().initializeCompass();
		autonomousCommand = new FullRunnableAuton((CommandGroup) autoChooser.getSelected(),
				(AutonLocator) locationChooser.getSelected());
		autonomousCommand.start();
		Scheduler.getInstance().add(new ShowInputs());
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.getCamera().getXLeft() - Aim.centerX) < Aim.threshold);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.getCamera().getXRight() - Aim.centerX) < Aim.threshold);

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

		Robot.getInputs().updateSmartDashboard();
		Robot.getCompass().dumpSmartDashboardValues();
		Robot.getCamera().setNetTable();
		Robot.getCamera().dumpSmartDshboardValues();
		Robot.getArm().dumpSmartDashboardValues();
		Robot.getShooter().dumpSmartDashboardValues();
		Robot.getDriveTrain().dumpSmartdashboardValues();

		SmartDashboard.putData("DriveLocate", new DriveLocate());
		SmartDashboard.putData("ChivalDeFreze", new ChivalDeFreze());
		SmartDashboard.putData("Rock wall", new RockWall());
		SmartDashboard.putData("Rough Terain", new RoughTerrain());
		SmartDashboard.putData("Low Bar", new LowBar());
		SmartDashboard.putData("Moat", new Moat());
		SmartDashboard.putData("Ramparts", new Ramparts());

		SmartDashboard.putBoolean("aimed", Math.abs(Robot.getCamera().getXLeft() - Aim.centerX) < Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.getCamera().getXRight() - Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.getCamera().getXRight() - Aim.centerX < 0);
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
		WarningMessages.initiateLoggerFile();

		setDriveTrain(new DriveTrain());
		setInputs(new Inputs());
		setArm(new Arm());
		setShooter(new Shooter());
		setCamera(new Camera());
		setCompass(new Compass());
		setFlipper(new Flipper());
		setIntake(new Intake());
		// IntakeWheels intakeWheels = new IntakeWheels();

		createAutonomousChooser();
		setOi(new OI());

		CameraServer server = CameraServer.getInstance();
		server.setQuality(0);
		server.startAutomaticCapture("cam1");

		// be sure to have the exact same name as the file and the extension,
		// test without the extension
		String imageName = "TestImage.png";

		// be sure to put the image in the same directory as this class
		try {
			SmartDashboard.putData("Image", (Sendable) new RGBImage(imageName));
		} catch (NIVisionException e) {
			WarningMessages.addError("Unable to add image", this);
		}

		WarningMessages.printWarningsFromToday();
	}

	@Override
	public void teleopInit() {
		getCompass().initializeCompass();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new ShowInputs());

		SmartDashboard.putBoolean("aimed", Math.abs(Robot.getCamera().getXRight() - Aim.centerX) < Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.getCamera().getXRight() - Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.getCamera().getXRight() - Aim.centerX < 0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putBoolean("aimed", Math.abs(Robot.getCamera().getXRight() - Aim.centerX) < Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.getCamera().getXRight() - Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.getCamera().getXRight() - Aim.centerX < 0);
		Robot.getDriveTrain().dumpSmartdashboardValues();
	}
}
