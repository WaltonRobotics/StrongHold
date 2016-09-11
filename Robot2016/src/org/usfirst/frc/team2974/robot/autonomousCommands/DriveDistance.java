package org.usfirst.frc.team2974.robot.autonomousCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;

public class DriveDistance extends Command {

    private final double speed;
    private final double distance;// in meters

    public DriveDistance(double speed, double distance) {
        requires(Robot.driveTrain);
        this.speed = speed;
        this.distance = distance;
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub
        Robot.driveTrain.setSpeeds(0, 0);
    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub
        if (Robot.driveTrain.leftController.get() < distance)
            ;// we will need a conversion rate
        end();
    }

    @Override
    protected void initialize() {
        Robot.driveTrain.setSpeeds(speed, speed);
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}
