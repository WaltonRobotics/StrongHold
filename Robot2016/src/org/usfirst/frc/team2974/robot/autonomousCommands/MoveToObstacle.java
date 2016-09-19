package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToObstacle extends Command{
	double threshold;
	double direction;
	double startTime;
	
	public MoveToObstacle(double direction){
		this.direction = direction;
		startTime = Timer.getFPGATimestamp();
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.setSpeeds(1*direction, 1*direction);
		threshold = 5*direction;
	}
	
	@Override
	protected void execute() {
		if(isFinished()) return;
		Robot.driveTrain.setSpeeds(1*direction, 1*direction);
	}

	@Override
	protected boolean isFinished() {
		// Create an if statement which will call end
		if(Robot.compass.getPitch() > threshold && Timer.getFPGATimestamp() - startTime > 500){
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.setSpeeds(0, 0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
