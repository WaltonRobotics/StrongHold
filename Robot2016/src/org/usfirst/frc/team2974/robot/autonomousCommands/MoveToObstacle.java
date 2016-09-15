package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToObstacle extends Command{
	double threshold;
	double direction;
	
	public MoveToObstacle(double direction){
		this.direction = direction;
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.setSpeeds(1*direction, 1*direction);//Magic number - change this after testing with actual robot
		threshold = 7*direction; //another magic number to test
	}
	
	@Override
	protected void execute() {
		// Create an if statement which calls is finished
		if(isFinished()){
			end();
		}
		
	}

	@Override
	protected boolean isFinished() {
		// Create an if statement which will call end
		if(Robot.compass.getPitch() > threshold){
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
