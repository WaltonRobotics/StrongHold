
package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	private final double deadband = .05;
    
	public static boolean isTank = true;
	public static String driveMode = "Tank";
	
    public Drive() {
    	
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Drive.isTank){
    		double left = Robot.oi.left.getY();
    		double right = Robot.oi.right.getY();
    	
    		if(Math.abs(left)<deadband)
    			left = 0;
    		if(Math.abs(right)<deadband)
    			right = 0;
    		Robot.driveTrain.setSpeeds(-1*left,-1*right);
    	
    		if(Robot.oi.shiftDown.get())
    			Robot.driveTrain.shiftDown();
    		if(Robot.oi.shiftUp.get())
    			Robot.driveTrain.shiftUp();
    	}else{
    		double direction = Robot.oi.left.getX();
    		double throttle = Robot.oi.right.getY();
    		
    		if(Math.abs(direction)<deadband)
    			direction = 0;
    		if(Math.abs(throttle)<deadband)
    			throttle = 0;
    		Robot.driveTrain.setSpeeds(-1*throttle + direction,-1*throttle - direction);
    		
    		if(Robot.oi.shiftDown.get())
    			Robot.driveTrain.shiftDown();
    		if(Robot.oi.shiftUp.get())
    			Robot.driveTrain.shiftUp();
    	}

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
