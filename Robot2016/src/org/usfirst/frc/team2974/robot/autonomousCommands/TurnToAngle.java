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
    double speedLeft;
    double speedRight;
    boolean left;
    double angle;
    double tolerance = 20;

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.compass.getPitch()>Robot.startAngle + angle)
    		Robot.driveTrain.setSpeeds(speedLeft, speedRight);
    	else
    		Robot.driveTrain.setSpeeds(-1 * speedLeft, speedRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.compass.getPitch()-(Robot.startAngle+ angle))<tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
