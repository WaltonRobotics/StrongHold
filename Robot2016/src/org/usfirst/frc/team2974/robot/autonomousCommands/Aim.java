package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Aim extends Command {
	private DriveTrain driveTrain = Robot.driveTrain;
	private Camera camera = Robot.camera;

	private final double threshold = 2;
	private State currentState;
	private double speed = .6; 
	private double brakingSpeed = .05; 
	private final double centerX = 85;
	private double gain = .0095;
	private double cycleDifference;

    public Aim() {
        requires(Robot.driveTrain);
    }


	abstract class State {
		boolean init = false;

		abstract void init();
		abstract void execute();
		abstract void end();
		abstract boolean isFinished();
	}

    class Cycle extends State
    {
    	double startTime;
    	void init()
    	{
    		startTime = Timer.getFPGATimestamp();
    	}
    	void execute()
    	{
    		if (cycleDifference > 0)//im to the right
				driveTrain.setSpeeds(-speed, brakingSpeed);//turn left
			else
				driveTrain.setSpeeds(speed, -brakingSpeed);//turn right
			
    	}
    	boolean isFinished()
    	{
    		return Timer.getFPGATimestamp() - startTime > gain * Math.abs(cycleDifference);
    	}
    	void end()
    	{
    		currentState = new Wait();
			
    	}
    }
    class Wait extends State
    {
    	double startTime;
    	double waitTime = .1;
    	void init()
    	{
    		startTime = Timer.getFPGATimestamp();
    	}
    	void execute()
    	{
			driveTrain.setSpeeds(0, 0);
    	}
    	boolean isFinished()
    	{
    		return Timer.getFPGATimestamp() - startTime > waitTime;
    	}
    	void end()
    	{
    		currentState = new Reset();
    	}
    }
    class Reset extends State
    {
    	void init()
    	{
    		cycleDifference = camera.getX() - centerX;
    	}
    	void execute()
    	{

    	}
    	boolean isFinished()
    	{
    		return Math.abs(cycleDifference) > threshold;
    	}
    	void end()
    	{
    		currentState = new Cycle();
    	}
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.shiftDown();//change to down TODO
    	gain = SmartDashboard.getNumber("gain");
		currentState = new Reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (camera.getX() != -1) {
    		if (!currentState.init) {
    			currentState.init();
    			currentState.init = true;
    		} else if (currentState.isFinished())
    			currentState.end();
    		else
    			currentState.execute();

		} else {
			driveTrain.setSpeeds(0, 0);
			currentState = new Reset();
			
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(cycleDifference) < threshold;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.setSpeeds(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
