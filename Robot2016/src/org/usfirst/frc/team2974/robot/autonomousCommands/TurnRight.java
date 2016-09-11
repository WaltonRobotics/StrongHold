package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
class TurnRight extends Command {
    private double time;
    private double startTime;
    private double speed;

    public TurnRight() {
        requires(Robot.driveTrain);
        SmartDashboard.putNumber("TurnTime", 1);
        SmartDashboard.putNumber("TurnSpeed", .3);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.setSpeeds(-speed, 0);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        time = SmartDashboard.getNumber("TurnTime", 1);
        speed = SmartDashboard.getNumber("TurnSpeed", .3);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.camera.getXLeft() != -1 || Timer.getFPGATimestamp() - startTime > time;
    }
}
