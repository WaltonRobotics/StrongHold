package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.commands.Shoot.ShooterState;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Aim extends Command {
	private DriveTrain driveTrain = Robot.driveTrain;
	private Camera camera = Robot.camera;
	private final double threshold = 20; 
	private double speed = .1;
	private final double centerX = 200;
    public Aim() {
        // Use requires() here to declare subsystem dependencies
        //requires(driveTrain);
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(camera.getX() -centerX) > threshold){
    		if(camera.getX() > centerX){
    			driveTrain.setSpeeds(-speed, speed);
    		}
    		else 
    			driveTrain.setSpeeds(speed, -speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return Math.abs(camera.getX() -centerX) <= threshold;
    }

    // Called once after isFinished returns true
    protected void end() {
    	new Shoot(ShooterState.readyToShoot);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
