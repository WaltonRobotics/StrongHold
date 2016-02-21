package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoadBall extends Command {
private double threshold = .05;
    public LoadBall() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.intake.get())
    		Robot.intake.input();
    	else if(Robot.oi.outtake.get())
    		Robot.intake.output();
    	else if(Robot.oi.gamepad.getLeftTrigger()>threshold)
    		Robot.intake.setMotor(Robot.oi.gamepad.getLeftTrigger());
    	else
    		Robot.intake.setMotor(0);
    	
    	if(Robot.oi.ballMoveIn.get())
    		Robot.intake.setIntakeSolenoid(IntakeState.up);
    	else if(Robot.oi.ballRelease.get())
    		Robot.intake.setIntakeSolenoid(IntakeState.down);
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
