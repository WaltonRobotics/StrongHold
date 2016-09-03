package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLocate extends Command{

	double conversionRateL,conversionRateR,threshold,thresholdL,thresholdR;//find numbers
	
	public DriveLocate() {
        requires(Robot.driveTrain);
        requires(Robot.compass);
    }
	
	@Override
	protected void initialize() {
		Robot.driveTrain.setSpeeds(Robot.compass.getYaw()*conversionRateL, Robot.compass.getYaw()*conversionRateR);
	}

	@Override
	protected void execute() {
		if (Robot.compass.getPitch() < threshold && Robot.compass.getYaw() < thresholdL && Robot.compass.getYaw() > thresholdR){
			//stop backing up keep turning
		}
		if (Robot.compass.getPitch() < threshold && Robot.compass.getYaw() < thresholdL && Robot.compass.getYaw() > thresholdR){
			//stop turning keep backing up
		}
		if (Robot.compass.getPitch() < threshold && Robot.compass.getYaw() < thresholdL && Robot.compass.getYaw() > thresholdR){
			end();
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
