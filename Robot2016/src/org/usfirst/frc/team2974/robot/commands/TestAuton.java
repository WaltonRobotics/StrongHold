package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestAuton extends Command {

    public TestAuton() {
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		//Robot.autonomousCommand.cancel();
//    	SendableChooser autoChooser = Robot.autoChooser;
//    	Command autonomousCommand = (Command) autoChooser.getSelected();
//		autonomousCommand.start();
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;//change this fool
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
