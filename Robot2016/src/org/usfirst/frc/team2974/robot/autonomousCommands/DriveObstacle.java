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
	private double startYaw;
	private double pitch;
	private double speed  =-.8;
	private final double multiplierConstatnt = 1.5;
	private driveState state;
	private double upPitch =15;
	private double downPitch = -15;
	private double crossPitch = 0;
	private double normalPitch = 0;
	private double threshold  = 5;
	
	private double timeUp =1;
	private double timeCross = 1;
	private double timeDown = 1;
	
	Compass compass = Robot.compass;
	DriveTrain driveTrain = Robot.driveTrain;
	
	private double time;
	private double startTime;
	
    public DriveObstacle() {
    	requires(driveTrain);
    	requires(compass);
    }
    enum driveState
    {
    	drive,up, crossing, down, done
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	startYaw = compass.getYaw();
    	startTime = Timer.getFPGATimestamp();
    	state = driveState.drive;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	pitch = compass.getPitch();
    	switch(state)
    	{
    	case drive:
    		straight();
    		if(pitch>upPitch)
    		{
    			state = driveState.crossing;
    			startTime = Timer.getFPGATimestamp();
    		}
    		break;
    	case up:
    		straight();
    		if(Timer.getFPGATimestamp()-startTime>timeUp && pitch<crossPitch)
    		{
    			state = driveState.crossing;
    			startTime = Timer.getFPGATimestamp();
    		}
    		break;
    	case crossing:
    		straight();
    		if(Timer.getFPGATimestamp()-startTime>timeCross&& pitch < downPitch)
    		{
    			state = driveState.down;
    			startTime = Timer.getFPGATimestamp();
    		}
    		break;
    	case down:
    		straight();
    		if(Math.abs(normalPitch - pitch)< threshold && Timer.getFPGATimestamp()-time> timeDown )
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
    	
    	if(compass.getYaw()>startYaw)
    	{
    		//veering right
    		speedLeft -=Math.abs(compass.getYaw()-startYaw)* multiplierConstatnt;
    		speedRight += Math.abs(compass.getYaw()-startYaw)* multiplierConstatnt;
    	}
    	else
    	{
    		//veering left
    		speedLeft +=Math.abs(compass.getYaw()-startYaw)* multiplierConstatnt;
    		speedRight -= Math.abs(compass.getYaw()-startYaw)* multiplierConstatnt;
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
