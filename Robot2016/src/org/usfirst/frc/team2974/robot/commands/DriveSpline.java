package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import motionProfilling.MotionControl;

/**
 *
 */
public class DriveSpline extends Command {
	MotionControl mc;
	double offsetTime;
	DriveTrain drive = Robot.driveTrain;
    public DriveSpline() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	mc = new MotionControl(SmartDashboard.getString("Spline","0,0,0:5,5,90" ),0,0);
    	offsetTime = Timer.getFPGATimestamp();
    	SmartDashboard.putNumber("kV", 1);
    	SmartDashboard.putNumber("kA", 1);
    	SmartDashboard.putNumber("P", 1);
    	SmartDashboard.putNumber("I", 1);
    	SmartDashboard.putNumber("D", 1);
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
    	takeSmartDashValues();
    	Robot.driveTrain.setSetPoint(mc.getPosition(Timer.getFPGATimestamp()-offsetTime));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
