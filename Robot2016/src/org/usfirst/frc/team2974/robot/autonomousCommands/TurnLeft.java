package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class TurnLeft extends Command {
    private final double time;
    private final double speed;
    private double startTime;

    public TurnLeft() {
        this(1.25, .5);
    }

    public TurnLeft(double time, double speed) {
        requires(Robot.driveTrain);
        // SmartDashboard.putNumber("timeTurn", time);
        // SmartDashboard.putNumber("speedTurn", speed);
        this.time = time;
        this.speed = speed;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.setSpeeds(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.setSpeeds(-speed, speed);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        // time = SmartDashboard.getNumber("timeTurn");
        // speed = SmartDashboard.getNumber("speedTurn");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime > time);
    }
}
