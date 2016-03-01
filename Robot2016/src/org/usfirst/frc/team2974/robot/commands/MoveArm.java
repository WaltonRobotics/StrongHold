package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.logging.Log;
import org.usfirst.frc.team2974.logging.LogMessage;
import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;
import org.usfirst.frc.team2974.robot.Gamepad;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveArm extends Command {
	CANTalon arm = RobotMap.arm;
	private final double threshold = 10;
    public MoveArm() {
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Log.instance().logCall(new LogMessage(Severity.INFORMATION, SubSystem.CLMBARM, "MoveArm.execute",
				"Position:" + Robot.arm.getPotValue(), 32));
    	SmartDashboard.putNumber("armvalue", Robot.arm.getPotValue());
    	arm.set(Robot.oi.gamepad.getLeftY());
    	if(Robot.oi.gamepad.getPOVButton(Gamepad.POV.W)){
    		if(Math.abs(Robot.arm.getPotValue()) - 0 < threshold){
    			Robot.arm.moveArm(0);
    		}
    		else if(Robot.arm.getPotValue() > 0){
    			Robot.arm.moveArm(-.5);
    		}
    		else
    			Robot.arm.moveArm(.5);
    	}
    	if(Robot.oi.gamepad.getPOVButton(Gamepad.POV.N)){
    		if(Math.abs(Robot.arm.getPotValue()) - 125 < threshold){
    			Robot.arm.moveArm(0);
    		}
    		if(Robot.arm.getPotValue() > 125){
    			Robot.arm.moveArm(-.5);
    		}
    		else
    			Robot.arm.moveArm(.5);
    	}
    	if(Robot.oi.gamepad.getPOVButton(Gamepad.POV.E)){
    		if(Math.abs(Robot.arm.getPotValue()) - 250 < threshold){
    			Robot.arm.moveArm(0);
    		}
    		if(Robot.arm.getPotValue() > 250){
    			Robot.arm.moveArm(-.5);
    		}
    		else
    			Robot.arm.moveArm(.5);
    	}
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
