package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper.FlipperState;
import org.usfirst.frc.team2974.robot.subsystems.Intake;

/**
 *
 */
public class FlapDownTime extends Command {
    private final double time;
    private double startTime;

    public FlapDownTime(double time) {
        requires(Robot.flipper);
        this.time = time;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.flipper.setFlapper(FlipperState.up);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (Robot.intake.getState() == Intake.IntakeExtenderState.out)
            Robot.flipper.setFlapper(FlipperState.down);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        Robot.intake.extend();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > time;
    }
}
