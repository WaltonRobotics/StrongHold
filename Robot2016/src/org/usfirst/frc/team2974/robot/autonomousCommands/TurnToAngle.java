package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {
	//makes it move for a certain fixed and dynamically found time by using the speed the angle 
	
	 double speed = .5;//speed normally at .5 and not .1
	 double goalAngle;
	 double tolerance = 5;
	 private final double PROPORTIONAL_ZONE = 120;
	    
    public TurnToAngle(double angle) {
        requires(Robot.driveTrain);
        requires(Robot.compass);
        this.goalAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isFinished()) return;
    	double deltaYaw = errorAngle();
    	double direction = Math.signum(deltaYaw);
    	double speedMag = Math.abs(deltaYaw)/PROPORTIONAL_ZONE;
    	System.out.println("Speed Mag " + speedMag);
    	System.out.println("\t\tDelta Yaw " + deltaYaw);
    	Robot.driveTrain.setSpeeds(-speedMag * direction, speedMag * direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double deltaYaw = errorAngle();

    	if(Math.abs(deltaYaw) <= tolerance)
    		return true;
    	return false;
    }
    
    private double errorAngle(){
    	double deltaYaw = Robot.compass.getYaw()-goalAngle;
    	if(deltaYaw > 180){
			deltaYaw -= 360;
		}
    	else if(deltaYaw < -180){
			deltaYaw += 360;
		}
		return deltaYaw;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setSpeeds(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
