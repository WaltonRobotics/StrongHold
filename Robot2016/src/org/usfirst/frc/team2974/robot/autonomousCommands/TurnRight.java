package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnRight extends Command {
double time;
double startTime;
double speed;
    public TurnRight() {
        requires(Robot.driveTrain);
        SmartDashboard.putNumber("TurnTime", 1);
        SmartDashboard.putNumber("TurnSpeed", .3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	time = (double)SmartDashboard.getNumber("TurnTime",1);
    	speed = (double)SmartDashboard.getNumber("TurnSpeed",.3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.setSpeeds(-speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.camera.getX()!=-1 || Timer.getFPGATimestamp()-startTime>time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
