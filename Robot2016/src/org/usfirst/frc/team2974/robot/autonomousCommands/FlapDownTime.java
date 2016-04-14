package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flapper.FlapperState;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlapDownTime extends Command {
double time;
double startTime;
    public FlapDownTime(double time) {
        requires(Robot.flapper);
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	Robot.flapper.setFlapper(FlapperState.down);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flapper.setFlapper(FlapperState.down);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flapper.setFlapper(FlapperState.up);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
