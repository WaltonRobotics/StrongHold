
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
    
    public final String keyTank = "Tank";
    public final String keyDriveTrain = "Drive Train:";
    public final String keyCheesy = "Cheesy";
	
    public Drive() {
        requires(Robot.driveTrain);
        isTank = true;
        
        SmartDashboard.putBoolean(keyTank, isTank); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean(keyTank, isTank); //$NON-NLS-1$
    	String driveTrain = SmartDashboard.getString(keyDriveTrain);
    	if (SmartDashboard.getBoolean(keyTank)){ //$NON-NLS-1$
    		if(driveTrain.equals(keyCheesy)) //$NON-NLS-1$ //$NON-NLS-2$
    			SmartDashboard.putString(keyDriveTrain, keyTank); //$NON-NLS-1$ //$NON-NLS-2$
    		
    		double left = Robot.oi.left.getY();
    		double right = Robot.oi.right.getY();
    	
    		if(Math.abs(left)<deadband)
    			left = 0;
    		if(Math.abs(right)<deadband)
    			right = 0;
    		Robot.driveTrain.setSpeeds(-1*left,-1*right);
    	

    	}else{
    		if(driveTrain.equals(keyTank)) //$NON-NLS-1$ //$NON-NLS-2$
    			SmartDashboard.putString(keyDriveTrain, keyCheesy); //$NON-NLS-1$ //$NON-NLS-2$
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
