package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Compass;
import org.usfirst.frc.team2974.robot.subsystems.DriveTrain;

public class DriveLocate extends Command {
    private final double aMax;
    private final double vDrive;
    private final DriveTrain driveTrain;
    double conversionRateL;
    double conversionRateR;
    double thresholdYaw;
    private double targetAngle;

    public DriveLocate() {
        requires(Robot.driveTrain);
        requires(Robot.compass);
        driveTrain = Robot.driveTrain;
        Compass compass = Robot.compass;
        aMax = 0.1; // TEST - 0.1 seems slow
        vDrive = 0.5; // TEST - 0.5 so that V can end up being 1

    }

    @Override
    protected void end() {
        // Hand over to next command with robot stopping
        driveTrain.disableMotionController();
        driveTrain.setSpeeds(0, 0);

    }

    @Override
    protected void execute() {
        double t = timeSinceInitialized();
        double tDrive = vDrive / aMax;
        double x;
        double v;
        if (t < tDrive) {
            x = Math.pow(aMax, 2) * t;
            v = aMax * t;
        } else {
            x = (Math.pow(aMax, 2) * tDrive) + vDrive * (t - tDrive);
            v = vDrive;
        }
        driveTrain.setSetPoint(x, v);

    }

    @Override
    protected void initialize() {
        driveTrain.resetEncoders();
        driveTrain.setSetPoint(0, 0);
        driveTrain.enableMotionController();
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished() {
        // Returns true if both tests are satisfied

        double errorAngle = Robot.compass.getYaw() - targetAngle;

        if (errorAngle > 180) {
            errorAngle -= 360;
        }

        if (errorAngle < -180) {
        }

        double timeOut = 3;
        double thresholdPitch = 5;
        return (timeSinceInitialized() > timeOut) || (Math.abs(Robot.compass.getPitch()) > thresholdPitch);// &&
        // (Math.abs(errorAngle) < thresholdYaw);
    }

}
