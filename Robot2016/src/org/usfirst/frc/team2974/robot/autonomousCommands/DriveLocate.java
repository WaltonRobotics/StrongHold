package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLocate extends Command{
	private final double timeOut = 3;
	double conversionRateL;
	double conversionRateR;
	double thresholdYaw;
	final double thresholdPitch = 5;//find numbers
	double targetAngle;
	double aMax;
	double vDrive;
	DriveTrain driveTrain;
	Compass compass;
	
	public DriveLocate() {
        requires(Robot.driveTrain);
        requires(Robot.compass);
        driveTrain = Robot.driveTrain;
        compass = Robot.compass;
        aMax = 0.1; //TEST
        vDrive = 0.5; //TEST
        		
    }
	
	@Override
	protected void initialize() {
		driveTrain.resetEncoders();
		driveTrain.setSetPoint(0, 0);
		driveTrain.enableMotionController();
	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		double tDrive = vDrive / aMax;
		double x;
		double v;
		if (t < tDrive) {
			x = Math.pow(aMax, 2) * t;
			v = aMax * t;
		}
		else {
			x = (Math.pow(aMax, 2) * tDrive) + vDrive * (t - tDrive);
			v = vDrive;
		}
		driveTrain.setSetPoint(x, v);

		
	}

	@Override
	protected boolean isFinished() {
		//Returns true if both tests are satisfied
		
		double errorAngle = Robot.compass.getYaw() - targetAngle;
		
		if(errorAngle > 180){
			errorAngle -= 360;
		}
		
		if(errorAngle < -180){
			errorAngle += 360;
		}
		
		return (timeSinceInitialized() > timeOut) && 
				(Robot.compass.getPitch() < thresholdPitch) ;//&&
				//(Math.abs(errorAngle) < thresholdYaw); 
	}

	@Override
	protected void end() {
		//Hand over to next command with robot stopping
		driveTrain.disableMotionController();
		driveTrain.setSpeeds(0, 0);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
