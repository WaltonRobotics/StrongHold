package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper.FlipperState;
import org.usfirst.frc.team2974.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlapDownTime extends Command {
double time;
double startTime;
    public FlapDownTime(double time) {
        requires(Robot.flipper);
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	Robot.intake.extend();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.intake.getState() == Intake.IntakeExtenderState.out)
    	Robot.flipper.setFlapper(FlipperState.down);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flipper.setFlapper(FlipperState.up);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
