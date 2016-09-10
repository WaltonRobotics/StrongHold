
package org.usfirst.frc.team2974.robot;

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
import org.usfirst.frc.team2974.robot.subsystems.IntakeWheels;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static SendableChooser autoChooser;
	public static SendableChooser locationChooser;
	
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Arm arm;
	public static Inputs inputs;
	public static Shooter shooter;
	public static Camera camera;
	public static Compass compass;
	public static Flipper flipper;
	public static Intake intake;
	public static IntakeWheels intakeWheels;

	public static Command autonomousCommand;

	public static double startAngle;
	public static aimState aimingState = aimState.onbaord;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
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
		intakeWheels = new IntakeWheels();
	
		createAutonomousChooser();
		oi = new OI();
		
		CameraServer server = CameraServer.getInstance();
		server.setQuality(0);
		server.startAutomaticCapture("cam1");
		
		startAngle = compass.getPitch();
	}

	private void createAutonomousChooser() {

		locationChooser = new SendableChooser();
		locationChooser.addDefault("Do Nothing", new AutonLocator(AutonPossibleLocation.NO_AUTON_TAKEN));
		locationChooser.addDefault("A", new AutonLocator(AutonPossibleLocation.A));
		locationChooser.addDefault("B", new AutonLocator(AutonPossibleLocation.B));
		locationChooser.addDefault("C", new AutonLocator(AutonPossibleLocation.C));
		locationChooser.addDefault("D", new AutonLocator(AutonPossibleLocation.D));
		locationChooser.addDefault("E", new AutonLocator(AutonPossibleLocation.E));
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new DoNothing());
		//autoChooser.addObject("Lowbar", new LowBar());
		//autoChooser.addObject("Lowbar + shoot", new LowBarShoot());
		//autoChooser.addObject("LowBar return", new LowBarReturn());
//		autoChooser.addObject("Rough Terrain", new RoughTerrain());
		//autoChooser.addObject("rock wall 1", new RockWall1());
//		autoChooser.addObject("Rough terrain shoot right", new RoughTerrainShootRight());
//		autoChooser.addObject("Rough terrain shoot center", new RoughTerrainShootStraight());
//		autoChooser.addObject("Rough Terrain Return", new RoughTerrainReturn());
//		autoChooser.addObject("Rock wall", new RockWall());
//		autoChooser.addObject("Rock wall shoot right", new RockWallShootRight());
//		autoChooser.addObject("Rock wall shoot left", new RockWallShootLeft());
//		autoChooser.addObject("Rock wall shoot center", new RockwallShootStraight());
//		autoChooser.addObject("Rock Wall Return", new RockWallReturn());
		autoChooser.addObject("Rock wall",new RockWall());
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
	public void disabledInit() {
	}

	public void disabledPeriodic() {
		
		Robot.inputs.updateSmartDashboard();
		Robot.compass.dumpSmartDashboardValues();
		Robot.camera.setNetTable();
		Robot.camera.dumpSmartDshboardValues();
		Robot.arm.dumpSmartDashboardValues();
		Robot.shooter.dumpSmartDashboardValues();
		Robot.driveTrain.dumpSmartdashboardValues();
		
		SmartDashboard.putData("DriveLocate", new DriveLocate());
		SmartDashboard.putData("ChivalDeFreze", new ChivalDeFreze());
		SmartDashboard.putData("Rock wall",new RockWall());
		SmartDashboard.putData("Rough Terain", new RoughTerrain());
		SmartDashboard.putData("Low Bar", new LowBar());
		SmartDashboard.putData("Moat", new Moat());
		SmartDashboard.putData("Ramparts", new Ramparts());
		
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXLeft()-Aim.centerX)<Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.camera.getXRight()-Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.camera.getXRight()-Aim.centerX < 0);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		compass.initializeCompass();
		autonomousCommand = (Command) new FullRunnableAuton((CommandGroup)autoChooser.getSelected(),(AutonLocator)locationChooser.getSelected());
		autonomousCommand.start();
		Scheduler.getInstance().add(new ShowInputs());
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXLeft()-Aim.centerX)<Aim.threshold);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXRight()-Aim.centerX)<Aim.threshold);

	}

	public void teleopInit() {
		compass.initializeCompass();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new ShowInputs());
		
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXRight()-Aim.centerX)<Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.camera.getXRight()-Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.camera.getXRight()-Aim.centerX < 0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putBoolean("aimed", Math.abs(Robot.camera.getXRight()-Aim.centerX)<Aim.threshold);
		SmartDashboard.putBoolean("left", Robot.camera.getXRight()-Aim.centerX > 0);
		SmartDashboard.putBoolean("right", Robot.camera.getXRight()-Aim.centerX < 0);
		Robot.driveTrain.dumpSmartdashboardValues();
	}
}
