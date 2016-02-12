package org.usfirst.frc.team2974.robot;

/**import org.usfirst.frc.team2974.logging.DashboardSink;
import org.usfirst.frc.team2974.logging.FileSink;
import org.usfirst.frc.team2974.logging.Log;
import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;
import org.usfirst.frc.team2974.logging.filters.SeverityFilter;
import org.usfirst.frc.team2974.logging.filters.ThreadFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;
import org.usfirst.frc.team2974.robot.commands.ExampleCommand;
import org.usfirst.frc.team2974.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Runner {
	
	
	static NetworkTable table;
    Command autonomousCommand;
    SendableChooser chooser;
    
public static void main(String[] args){
    
    //table = NetworkTable.getTable("datatable");
    System.out.println("not dead yet");
    
    ThreadFilter threadFilter = new ThreadFilter();
    
    System.out.println("Created threaded, creating severity");
    
    SeverityFilter severityFilter = new SeverityFilter();
    
    System.out.println("Created severity, creating filesink");
    
    FileSink fileSink = new FileSink();
    
    System.out.println("Created filesink, creating dashboardsink");
    
    DashboardSink dashboardSink = new DashboardSink();
    
    System.out.println("Finished dashboardsink,building pipes");
    
    severityFilter.Passthrough(Severity.ERROR);
    Log.instance().attach(threadFilter);
    threadFilter.attach(severityFilter);
    severityFilter.attach(fileSink);
    Log.instance().attach(dashboardSink);
    
    System.out.println("About to set path");
	
    //SET YOUR FILE HERE
    fileSink.setPath("src/FileDump.txt");
    
    System.out.println("Path got set");
    
    //Start test messages:
    Log.instance().logCall(new LogMessage(Severity.ERROR,SubSystem.DRIVETRAIN,"motionProfileTurn","Syntax Error in equation."));
    Log.instance().logCall(new LogMessage(Severity.INFORMATION,SubSystem.INTAKE,"IntakeLoader","Loaded Sucessfully."));
    Log.instance().logCall(new LogMessage(Severity.DEBUG,SubSystem.CLMBARM,"ArmExtend","Took longer to reach up than expected."));
    Log.instance().logCall(new LogMessage(Severity.WARNING,SubSystem.SHOOTER,"Shoot","Stuck in loop."));
    //End test messages
}
}
*/