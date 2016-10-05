
package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {
	private final double deadband = .05;
    public static boolean isTank;
	
    public Drive() {
        requires(Robot.driveTrain);
        isTank = true;
        
        SmartDashboard.getString("Drive mode: ", "Tank");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Tank", isTank);
    	
    	if (SmartDashboard.getBoolean("Tank")){
    		isTank = true;
    		
    		if(SmartDashboard.getString("Drive mode: ").equals("cheesy"))
    			SmartDashboard.putString("Drive mode: ", "tank");
    		
    		double left = Robot.oi.left.getY();
    		double right = Robot.oi.right.getY();
    	
    		if(Math.abs(left)<deadband)
    			left = 0;
    		if(Math.abs(right)<deadband)
    			right = 0;
    		Robot.driveTrain.setSpeeds(-1*left,-1*right);
    	

    	}else{
    		isTank = false;
    		
    		if(SmartDashboard.getString("Drive mode: ").equals("tank"))
    			SmartDashboard.putString("Drive mode: ", "cheesy");
    		double throttle = (-Robot.oi.left.getY()+1)/2;
    		double forward = Robot.oi.right.getY();
    		double turn = Robot.oi.right.getX();
    		  		
    		if(Math.abs(turn)<deadband)
    			turn = 0;
    		if(Math.abs(forward)<deadband)
    			forward = 0;
    		if(Math.abs(throttle)<deadband)
    			throttle = 0;
    		
    		Robot.driveTrain.setSpeeds( throttle * ( forward + turn ), throttle * ( forward - turn ));
    	}
		if(Robot.oi.shiftDown.get())
			Robot.driveTrain.shiftDown();
		if(Robot.oi.shiftUp.get())
			Robot.driveTrain.shiftUp();

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
