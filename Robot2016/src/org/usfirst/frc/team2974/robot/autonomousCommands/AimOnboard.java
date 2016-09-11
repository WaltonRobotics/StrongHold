package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

/**
 *
 */
class AimOnboard extends Command {
    private static final double centerX = 95;
    // 0 =left, 2 = right
    private final int side;
    private final double threshold = 3;
    private final DriveTrain driveTrain = Robot.driveTrain;
    private final Camera camera = Robot.camera;
    private double cycleDifference;

    public AimOnboard(int side) {
        requires(Robot.driveTrain);
        this.side = side;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (side == 0)
            cycleDifference = camera.getXLeft() - centerX;
        else if (side == 2)
            cycleDifference = camera.getXRight() - centerX;

        if (Math.abs(cycleDifference) > threshold) {
            double brakingSpeed = 0.05;
            double speed = .35;
            if (side == 0) {
                if (cycleDifference > 0)// im to the right
                    driveTrain.setSpeeds(-speed, brakingSpeed);// turn left
                else
                    driveTrain.setSpeeds(speed, -brakingSpeed);// turn right
            } else if (side == 2) {
                if (cycleDifference > 0)// im to the right
                    driveTrain.setSpeeds(-brakingSpeed, speed);// turn left
                else
                    driveTrain.setSpeeds(brakingSpeed, -speed);// turn right
            }
        }

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Math.abs(cycleDifference) < threshold;
    }
}
