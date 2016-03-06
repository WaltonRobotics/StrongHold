
package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.usfirst.frc.team2974.robot.commands.Shoot;
import org.usfirst.frc.team2974.robot.commands.ShootTemp;
import org.usfirst.frc.team2974.robot.commands.ShowInputs;
import org.usfirst.frc.team2974.robot.commands.UpdateFiltration;
import org.usfirst.frc.team2974.robot.subsystems.*;


//grab ball at the end of auto
public class Robot extends IterativeRobot {
	
	private SendableChooser autoChooser;
	 
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Arm arm;
	public static Inputs inputs;
	public static Shooter shooter;
	public static Intake intake;
	public static Camera camera;
	public static Compass compass;
    
	public Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	
    	driveTrain = new DriveTrain();
    	inputs = new Inputs();
    	arm = new Arm();
    	shooter = new Shooter();
    	intake = new Intake();
    	camera = new Camera();
    	compass = new Compass();
    	oi = new OI();
    	createAutonomousChooser();
    }
    private void createAutonomousChooser()
    {//Chival de frise, a "moat", ramparts, a drawbridge, a Sally port, a portcullis, a rock wall, and "rough terrain".
//    	autoChooser = new SendableChooser();
//    	autoChooser.addDefault("Do Nothing",new DoNothing());
//    	autoChooser.addObject("low bar",new OneTote_NoStep());
//    	autoChooser.addObject("rock wall",new OneTote_Step());
//    	autoChooser.addObject("spy bot", new Forward_NoStep());
//    	autoChooser.addObject("moat", new Forward_Step());
//    	autoChooser.addObject("ramparts", new OneTote_Dynamic());
//    	autoChooser.addObject("rough terrain", new Forward_Dynamic());
//    	autoChooser.addObject("porticullus", object);
//    	SmartDashboard.putData("PICK AN AUTONOMOUS",autoChooser);
    }

	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	Scheduler.getInstance().add(new ShowInputs());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	Scheduler.getInstance().add(new ShowInputs());
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        Scheduler.getInstance().add(new Shoot());
       // Scheduler.getInstance().add(new UpdateFiltration());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
