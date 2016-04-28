package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

    public TurnToAngle(double angle) {
        requires(Robot.driveTrain);
        requires(Robot.compass);
        this.angle = Math.abs(angle);
    }
    double speed = .5;
    double speed1;
    double speed2;
    double angle;
    double tolerance = 10;
    double startYaw;

    // Called just before this Command runs the first time
    protected void initialize() {
    	startYaw = Robot.compass.getYaw();
    	if(angle<0)
    	{
    		speed2 = -1*speed;
    		speed1 = speed;
    	}
    	else
    	{
    		speed2 = speed;
    		speed1 = -1*speed;
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double deltaYaw = Math.abs(Robot.compass.getYaw()-startYaw);
    	if(deltaYaw>180)
    		deltaYaw = 360-deltaYaw;

    	if(deltaYaw > angle)
    		Robot.driveTrain.setSpeeds(speed1, 0);
    	else
    		Robot.driveTrain.setSpeeds(speed2, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Math.abs(Robot.compass.getYaw()-startYaw) - angle) <tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
