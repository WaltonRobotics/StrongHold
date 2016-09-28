package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraitSpecial extends Command {

    public DriveStraitSpecial() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	new DriveStraight(SmartDashboard.getNumber("SpecialTime"),SmartDashboard.getNumber("SpecialPower")).start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
