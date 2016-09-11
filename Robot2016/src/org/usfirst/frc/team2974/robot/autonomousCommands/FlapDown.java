package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Flipper;
import org.usfirst.frc.team2974.robot.subsystems.Intake;

/**
 *
 */
public class FlapDown extends Command {

    public FlapDown() {
        requires(Robot.flipper);
        // requires(Robot.intake);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (Robot.intake.getState() == Intake.IntakeExtenderState.out)
            Robot.flipper.setFlapper(Flipper.FlipperState.down);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

        Scheduler.getInstance().add(new IntakeIn());
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
        return Robot.shooter.isShooterDown();
    }
}
