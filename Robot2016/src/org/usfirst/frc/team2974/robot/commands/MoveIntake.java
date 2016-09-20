package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.IntakeExtender;
import org.usfirst.frc.team2974.robot.subsystems.IntakeWheels;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveIntake extends Command {
boolean flipperDown;
double time;
IntakeExtender intakeExtender;

    public MoveIntake() {
        requires(Robot.intakeExtender);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flipperDown =false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.retractIntake.get())
    	{
    		if(!flipperDown)
    		{
    			Robot.flipper.setFlipper(Flipper.FlipperState.up);
    			time = Timer.getFPGATimestamp();
    			flipperDown =true;
    		}
    		else
    		{
    			if(Timer.getFPGATimestamp()-time>.4)
    			{
    				flipperDown = false;
    			}
    			else if(Timer.getFPGATimestamp()-time > .2){ 			
    				intakeExtender.retract();
    				flipperDown = false;
    			}
    		}    		
    	}
    		
    	else if(Robot.oi.extendIntake.get())
    	{
    		intakeExtender.extend();
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
