package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightMoat extends Command {
	double time;
	double speed;
	double startTime;
	double multiplierConstatnt = .01;
	double threshold = 10;
    public DriveStraightMoat() {
        requires(Robot.driveTrain);
    	//SmartDashboard.putNumber("DriveStraightTime", 4.5);
    	//SmartDashboard.putNumber("DriveStraightSpeed", .5);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = 5;
    	speed = .7;//(double) SmartDashboard.getNumber("DriveStraightSpeed", .5);
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speedLeft = speed;
    	double speedRight = speed;
//    	if(Math.abs(RobotMap.encoderLeft.getzDistance()-RobotMap.encoderRight.getDistance())>threshold)
//    	{
//    		if(RobotMap.encoderLeft.getDistance()>RobotMap.encoderRight.getDistance())
//        		speedRight+= (RobotMap.encoderLeft.getDistance()-RobotMap.encoderRight.getDistance())*multiplierConstatnt;
//        	else 
//        		speedLeft+=(RobotMap.encoderRight.getDistance()-RobotMap.encoderLeft.getDistance())*multiplierConstatnt;
//    	}
    	
    	Robot.driveTrain.setSpeeds(speedLeft, speedRight);
    	if(Timer.getFPGATimestamp()-startTime>.3&& Timer.getFPGATimestamp()-startTime<.7)
    		Robot.arm.moveArmPower(-1);
    	else
    		Robot.arm.moveArmPower(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Autonomous stuff", "There ya go, you crossed the low bar");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
