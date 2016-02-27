package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoadBall extends Command {
private IntakeRollerState currentState;
    public LoadBall() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentState = IntakeRollerState.stop;
    }
    public enum IntakeRollerState{
    	in, out, stop
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.outtake.get()){
			currentState = IntakeRollerState.out;
		}
		else if(Robot.oi.stoptake.get()){
			currentState = IntakeRollerState.stop;
		}
		else if(Robot.oi.intake.get()){
			currentState = IntakeRollerState.in;
		}
    	switch(currentState){
    	case in:
    		Robot.intake.input();
    		break;
    	case out:
    		Robot.intake.output();
    		break;
    	case stop:
    		break;
    	}
    	
    	if(Robot.oi.flapperUp.get())
    		Robot.intake.setIntakeSolenoid(IntakeState.up);
    	else if(Robot.oi.gamepad.getLeftTrigger() > .1)
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
