
package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team2974.logging.DashboardSink;
import org.usfirst.frc.team2974.logging.FileSink;
import org.usfirst.frc.team2974.logging.Log;
import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;
import org.usfirst.frc.team2974.logging.filters.SeverityFilter;
import org.usfirst.frc.team2974.logging.filters.ThreadFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;
import org.usfirst.frc.team2974.robot.commands.ExampleCommand;
import org.usfirst.frc.team2974.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	
	static NetworkTable table;
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        
        table = NetworkTable.getTable("datatable");
        
        ThreadFilter threadFilter = new ThreadFilter();
        SeverityFilter severityFilter = new SeverityFilter();
        FileSink fileSink = new FileSink();
        DashboardSink dashboardSink = new DashboardSink();
        
        severityFilter.Passthrough(Severity.ERROR);
        Log.instance().attach(threadFilter);
        threadFilter.attach(severityFilter);
        severityFilter.attach(fileSink);
        Log.instance().attach(dashboardSink);
        
        //Start test messages:
        Log.instance().logCall(new LogMessage(Severity.ERROR,SubSystem.DRIVETRAIN,"motionProfileTurn","Syntax Error in equation."));
        Log.instance().logCall(new LogMessage(Severity.INFORMATION,SubSystem.INTAKE,"IntakeLoader","Loaded Sucessfully."));
        Log.instance().logCall(new LogMessage(Severity.DEBUG,SubSystem.CLMBARM,"ArmExtend","Took longer to reach up than expected."));
        Log.instance().logCall(new LogMessage(Severity.WARNING,SubSystem.SHOOTER,"Shoot","Stuck in loop."));
        //End test messages
    }
	
    public static void writeToLocal(LogMessage message){
    	table.putString("message:", message.getMessage());
    }
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

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
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
