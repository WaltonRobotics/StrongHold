package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {
	double time;
	double speed;
	double startTime;
    public DriveStraight() {
        requires(Robot.driveTrain);
    	SmartDashboard.putNumber("DriveStraightTime", 4);
    	SmartDashboard.putNumber("DriveStraightSpeed", .4);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = (double) SmartDashboard.getNumber("DriveStraightTime",4 );
    	speed = (double) SmartDashboard.getNumber("DriveStraightSpeed", .4);
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.setSpeeds(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Autonomous stuff", "There ya go, you crossed the low bar");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
