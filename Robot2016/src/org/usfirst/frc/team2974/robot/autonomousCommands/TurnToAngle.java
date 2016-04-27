package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

    public TurnToAngle(double angle, boolean left) {
        requires(Robot.driveTrain);
        requires(Robot.compass);
        this.angle = angle;
        if(left)
        	speedLeft = speed;
        else
        	speedRight = 0;
    }
    double speed = .5;
    double speedLeft = .5;
    double speedRight = 0;
    boolean left;
    double angle;
    double tolerance = 20;
    double startYaw;

    // Called just before this Command runs the first time
    protected void initialize() {
    	startYaw = Robot.compass.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double deltaYaw = Robot.compass.getYaw()-startYaw;
    	if(deltaYaw<0)
    		deltaYaw+=360;
    	if(deltaYaw > angle)
    		Robot.driveTrain.setSpeeds(-1*speedLeft, speedRight);
    	else
    		Robot.driveTrain.setSpeeds(speedLeft, speedRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.compass.getYaw()-startYaw- angle)<tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
