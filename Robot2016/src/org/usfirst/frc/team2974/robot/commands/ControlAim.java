package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ControlAim extends Command {

//	static aimState state;
    public ControlAim() {
    	requires(Robot.camera);
    }
    public enum aimState
    {
    	onbaord, cpu
    }
    // Called just before this Command runs the first time
    protected void initialize() {
//    	state = aimState.onbaord;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.changeAim1.get() && Robot.oi.changeAim2.get())
    	{
    		if(Robot.aimingState == aimState.cpu)
    			Robot.aimingState = aimState.onbaord;
    		else
    			Robot.aimingState = aimState.cpu;
    	}
    	if(Robot.oi.aimLeft.get())
    	{
    		if(Robot.aimingState == aimState.cpu)
    			Scheduler.getInstance().add(new Aim(0));
    		else
    			Scheduler.getInstance().add(new AimOnboard(0));
    	}
    	if(Robot.oi.aimRight.get())
    	{
    		if(Robot.aimingState == aimState.cpu)
    			Scheduler.getInstance().add(new Aim(2));
    		else
    			Scheduler.getInstance().add(new AimOnboard(2));
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
