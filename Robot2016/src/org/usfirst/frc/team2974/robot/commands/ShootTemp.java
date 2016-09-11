package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
class ShootTemp extends Command {

    public ShootTemp() {
        requires(Robot.shooter);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // get rid of following code
        if (Robot.oi.right.getRawButton(4))
            Robot.shooter.tension();
        else if (Robot.oi.right.getRawButton(5))
            Robot.shooter.unTension();
        else
            Robot.shooter.setZero();
        if (Robot.oi.right.getRawButton(10))
            Robot.shooter.latch();
        else if (Robot.oi.right.getRawButton(11))
            Robot.shooter.unlatch();

        // get rid of following code
        // if(Robot.oi.latchButton1.get())
        // Robot.shooter.latch();
        // if(Robot.oi.latchButton2.get())
        // Robot.shooter.unlatch();
        // Log.instance().logCall(new LogMessage(Severity.INFORMATION,
        // SubSystem.SHOOTER, "Shooter", "Shooter State" +
        // Robot.shooter.getState(), 120));
        // SmartDashboard.putString("Shooter State",
        // currentState.getClass()+"");
        SmartDashboard.putString("Tensioner State", Robot.shooter.getState() + "");
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
        return false;
    }
}
