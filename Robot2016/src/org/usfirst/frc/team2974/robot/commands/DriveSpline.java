package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import motionProfilling.MotionControl;

/**
 *
 */
public class DriveSpline extends Command {
	private MotionControl mc;
	private double offsetTime;
	private DriveTrain drive; 
	
    public DriveSpline() {
       	drive = Robot.driveTrain;
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	mc = new MotionControl(SmartDashboard.getString("Spline","0,0,0:1,1,90" ),0,0);
    	offsetTime = Timer.getFPGATimestamp();
    	takeSmartDashValues();
    	drive.resetEncoders();
    	drive.leftController.enable();
    	drive.rightController.enable();
    }
    private void takeSmartDashValues()
    {
    	drive.leftController.setkV(SmartDashboard.getNumber("kV"));
    	drive.leftController.setkA(SmartDashboard.getNumber("kA"));
    	drive.leftController.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D")); 
    	drive.rightController.setkV(SmartDashboard.getNumber("kV"));
    	drive.rightController.setkA(SmartDashboard.getNumber("kA"));
    	drive.rightController.setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D")); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dumpSmartDashBoardValuse();
    	Robot.driveTrain.setSetPoint(mc,Math.min(Timer.getFPGATimestamp()-offsetTime, mc.getMaxTime()) );
    	
    	
    }
    public void dumpSmartDashBoardValuse()
    {
    	double time = Math.min(mc.getMaxTime(), Timer.getFPGATimestamp()-offsetTime);
    	SmartDashboard.putNumber("DistanceLeft", mc.distanceleft(time));
    	SmartDashboard.putNumber("DistanceRight", mc.distanceRight(time));
    	SmartDashboard.putNumber("ErrorLeft", mc.distanceleft(time)-RobotMap.encoderLeft.getDistance());
    	SmartDashboard.putNumber("ErrorRight", mc.distanceRight(time)-RobotMap.encoderRight.getDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       // System.out.println(mc.getMaxTime());
        return Timer.getFPGATimestamp()-offsetTime>=mc.getMaxTime()+5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.leftController.disable();
    	drive.rightController.disable();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
