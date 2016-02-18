
package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2974.logging.DashboardSink;
import org.usfirst.frc.team2974.logging.FileSink;
import org.usfirst.frc.team2974.logging.Log;
import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;
import org.usfirst.frc.team2974.logging.filters.SeverityFilter;
import org.usfirst.frc.team2974.logging.filters.ThreadFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;
import org.usfirst.frc.team2974.robot.commands.ShowInputs;
import org.usfirst.frc.team2974.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {


	public static OI oi;
	public static DriveTrain driveTrain;
	public static Arm arm;
	public static Inputs inputs;
    Command autonomousCommand;
    
	SendableChooser chooserAUDIT;
	SendableChooser chooserDEBUG;
	SendableChooser chooserWARNING;
	SendableChooser chooserERROR;
	SendableChooser chooserINFORMATION;
	
	SeverityFilter severityFilter;
   // SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	SmartDashboard.putNumber("kV", 1.0/1.5);
    	SmartDashboard.putNumber("kA", 0);
    	SmartDashboard.putNumber("P", 0);
    	SmartDashboard.putNumber("I", 0);
    	SmartDashboard.putNumber("D", 0);
    	
    	driveTrain = new DriveTrain();
    	inputs = new Inputs();
    	arm = new Arm();
    	oi = new OI();
    	
    	establishLoggingChooser();
		establishLogging();
    }
    
	public void establishLoggingChooser() {
		chooserINFORMATION = new SendableChooser();
		SmartDashboard.putData("INFORMATION", chooserINFORMATION);

		chooserERROR = new SendableChooser();
		SmartDashboard.putData("ERROR", chooserERROR);

		chooserWARNING = new SendableChooser();
		SmartDashboard.putData("WARNING", chooserWARNING);

		chooserDEBUG = new SendableChooser();
		SmartDashboard.putData("DEBUG", chooserDEBUG);

		chooserAUDIT = new SendableChooser();
		SmartDashboard.putData("AUDIT", chooserAUDIT);

	}

	public void changeLogPassthrough() {
		if (chooserAUDIT.equals(true)){
			severityFilter.stopPassthrough(Severity.AUDIT);
		}else{
			severityFilter.Passthrough(Severity.AUDIT);
		}
		if (chooserDEBUG.equals(true)){
			severityFilter.stopPassthrough(Severity.DEBUG);
		}else{
			severityFilter.Passthrough(Severity.DEBUG);
		}
		if (chooserWARNING.equals(true)){
			severityFilter.stopPassthrough(Severity.WARNING);
		}else{
			severityFilter.Passthrough(Severity.WARNING);
		}
		if (chooserERROR.equals(true)){
			severityFilter.stopPassthrough(Severity.ERROR);
		}else{
			severityFilter.Passthrough(Severity.ERROR);
		}
		if (chooserINFORMATION.equals(true)){
			severityFilter.stopPassthrough(Severity.INFORMATION);
		}else{
			severityFilter.Passthrough(Severity.INFORMATION);
		}
	}

	public void establishLogging() {
		System.out.println("Creating filters");
		
		severityFilter = new SeverityFilter();

		ThreadFilter threadFilter = new ThreadFilter();
		SeverityFilter fileSeverityFilter = new SeverityFilter();
		FileSink fileSink = new FileSink();
		DashboardSink dashboardSink = new DashboardSink();

		fileSeverityFilter.Passthrough(Severity.ERROR);
		Log.instance().attach(threadFilter);
		Log.instance().attach(severityFilter);
		threadFilter.attach(fileSeverityFilter);
		fileSeverityFilter.attach(fileSink);
		severityFilter.attach(dashboardSink);
		

		// SET YOUR FILE HERE
		// fileSink.setPath("/home/lvuser/FileDump.txt");
		fileSink.setPath("FileDump.txt");

		// Start test messages:
		Log.instance().logCall(new LogMessage(Severity.ERROR, SubSystem.DRIVETRAIN, "motionProfileTurn",
				"Syntax Error in equation.", 32));
		Log.instance().logCall(
				new LogMessage(Severity.INFORMATION, SubSystem.INTAKE, "IntakeLoader", "Loaded Sucessfully.", 89));
		Log.instance().logCall(new LogMessage(Severity.DEBUG, SubSystem.CLMBARM, "ArmExtend",
				"Took longer to reach up than expected.", 86));
		Log.instance().logCall(new LogMessage(Severity.WARNING, SubSystem.SHOOTER, "Shoot", "Stuck in loop.", 99));
		// End test messages
	}
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().add(new ShowInputs());
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
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        Scheduler.getInstance().add(new ShowInputs());
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
