package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

// imports

class LoggingSubsystem extends Subsystem {

    // static SeverityFilter severityFilter;

    public LoggingSubsystem() {
        // establishLoggingChooser();
        // establishLogging();
    }

    public static void changeLogPassthrough() {
        // if (SmartDashboard.getString("Information") == "true"){
        // severityFilter.stopPassthrough(Severity.INFORMATION);
        // }else{
        // severityFilter.Passthrough(Severity.INFORMATION);
        // }
        // if (SmartDashboard.getString("Error") == "true"){
        // severityFilter.stopPassthrough(Severity.ERROR);
        // }else{
        // severityFilter.Passthrough(Severity.ERROR);
        // }
        // if (SmartDashboard.getString("Warning") == "true"){
        // severityFilter.stopPassthrough(Severity.WARNING);
        // }else{
        // severityFilter.Passthrough(Severity.WARNING);
        // }
        // if (SmartDashboard.getString("Debug") == "true"){
        // severityFilter.stopPassthrough(Severity.DEBUG);
        // }else{
        // severityFilter.Passthrough(Severity.DEBUG);
        // }
        // if (SmartDashboard.getString("Audit") == "true"){
        // severityFilter.stopPassthrough(Severity.AUDIT);
        // }else{
        // severityFilter.Passthrough(Severity.AUDIT);
        // }
    }

    /**
     * booleans represented by strings determine what gets written to file
     */

    public void establishLogging() {
        // System.out.println("Creating filters");
        //
        // severityFilter = new SeverityFilter();
        //
        // ThreadFilter threadFilter = new ThreadFilter();
        // SeverityFilter fileSeverityFilter = new SeverityFilter();
        // FileSink fileSink = new FileSink();
        // DashboardSink dashboardSink = new DashboardSink();
        //
        // fileSeverityFilter.Passthrough(Severity.ERROR);
        // Log.instance().attach(threadFilter);
        // Log.instance().attach(severityFilter);
        // threadFilter.attach(fileSeverityFilter);
        // fileSeverityFilter.attach(fileSink);
        // severityFilter.attach(dashboardSink);
        // // asssembles logging system
        //
        // // SET YOUR FILE HERE
        // // fileSink.setPath("/home/lvuser/FileDump.txt");
        // fileSink.setPath("FileDump.txt");
        //
        // // Start test messages:
        // Log.instance().logCall(new LogMessage(Severity.ERROR,
        // SubSystem.DRIVETRAIN, "motionProfileTurn",
        // "Syntax Error in equation.", 32));
        // Log.instance().logCall(
        // new LogMessage(Severity.INFORMATION, SubSystem.INTAKE,
        // "IntakeLoader", "Loaded Sucessfully.", 89));
        // Log.instance().logCall(new LogMessage(Severity.DEBUG,
        // SubSystem.CLMBARM, "ArmExtend",
        // "Took longer to reach up than expected.", 86));
        // Log.instance().logCall(new LogMessage(Severity.WARNING,
        // SubSystem.SHOOTER, "Shoot", "Stuck in loop.", 99));
        // // End test messages
        // SmartDashboard.putData("update", new UpdateFiltration());
    }

    public void establishLoggingChooser() {
        // SmartDashboard.putString("Information", "true");
        //
        // SmartDashboard.putString("Error", "true");
        //
        // SmartDashboard.putString("Warning", "true");
        //
        // SmartDashboard.putString("Debug", "true");
        //
        // SmartDashboard.putString("Audit", "true");
        //// put strings on smart dashboard
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
