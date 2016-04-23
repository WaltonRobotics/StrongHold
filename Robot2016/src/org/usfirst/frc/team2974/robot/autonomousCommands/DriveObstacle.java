package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import com.ni.vision.NIVision.DrawMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveObstacle extends Command {
	private double yaw;
	private double speed  =.8;
	private final double multiplierConstatnt = 1.5;
	private driveState state;
	private double upYaw =20;
	private double downYaw = -20;
	private double crossYaw;
	private double normalYaw;
	private double threshold  = 5;
	
	private double timeUp;
	private double timeCross;
	private double timeDown;
	
	Compass compass = Robot.compass;
	DriveTrain driveTrain = Robot.driveTrain;
	
	private double time;
	private double startTime;
	
    public DriveObstacle(double time) {
    	requires(driveTrain);
    	requires(compass);
    }
    enum driveState
    {
    	drive,up, crossing, down, done
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	yaw = compass.getYaw();
    	startTime = Timer.getFPGATimestamp();
    	state = driveState.drive;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	yaw = compass.getYaw();
    	switch(state)
    	{
    	case drive:
    		straight();
    		if(yaw>upYaw)
    		{
    			state = driveState.crossing;
    			startTime = Timer.getFPGATimestamp();
    		}
    		break;
    	case up:
    		straight();
    		if(Timer.getFPGATimestamp()-startTime>timeUp && yaw<crossYaw)
    		{
    			state = driveState.crossing;
    			startTime = Timer.getFPGATimestamp();
    		}
    		break;
    	case crossing:
    		straight();
    		if(Timer.getFPGATimestamp()-startTime>timeCross&& yaw < downYaw)
    		{
    			state = driveState.down;
    			startTime = Timer.getFPGATimestamp();
    		}
    		break;
    	case down:
    		straight();
    		if(Math.abs(normalYaw - yaw)< threshold && Timer.getFPGATimestamp()-time> timeDown )
    		{
    			state = driveState.done;
    		}
    		break;
    	case done:
    		Robot.driveTrain.setSpeeds(0, 0);
    		break;
    	}
 	

    }
    
    private void straight()
    {
    	double speedLeft = speed;
    	double speedRight = speed;
    	
    	if(compass.getYaw()>yaw)
    	{
    		//veering left
    		speedLeft +=(compass.getYaw()-yaw)* multiplierConstatnt;
    		speedRight -= (compass.getYaw()-yaw)* multiplierConstatnt;
    	}
    	else
    	{
    		//veering right
    		speedLeft -= (yaw - compass.getYaw())* multiplierConstatnt;
    		speedRight += (yaw - compass.getYaw())* multiplierConstatnt;
    	}
    	driveTrain.setSpeeds(speedLeft, speedRight);   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return state == driveState.done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
