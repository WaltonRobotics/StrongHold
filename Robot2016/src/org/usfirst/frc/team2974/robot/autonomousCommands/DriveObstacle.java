package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveObstacle extends Command {

    public DriveObstacle() {
    	requires(Robot.driveTrain);
    	requires(Robot.compass);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("time", Timer.getFPGATimestamp());
    	SmartDashboard.putNumber("heading1: " , Robot.compass.getHeading1());
    	SmartDashboard.putNumber("heading2: " , Robot.compass.getHeading2());
    	SmartDashboard.putNumber("heading3: " , Robot.compass.getHeading3());
    	SmartDashboard.putNumber("heading4: " , Robot.compass.getHeading4());

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
