package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeIn;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

//import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDown;
//import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class Shoot extends Command {

    private final Shooter shooter = Robot.shooter;
    private State currentState;

    public Shoot() {
        requires(shooter);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (!currentState.init) {
            currentState.init();
            currentState.init = true;
        } else if (currentState.isFinished())
            currentState.end();
        else
            currentState.execute();

        // Log.instance().logCall(new LogMessage(Severity.INFORMATION,
        // SubSystem.SHOOTER, "Shooter", "Shooter State" +
        // currentState.getClass()+"", 120));
        SmartDashboard.putString("Shooter State", currentState.getClass() + "");
        SmartDashboard.putString("Tensioner State", shooter.getState() + "");

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (shooter.isShooterDown())
            currentState = new Latched();
        else
            currentState = new Returning();
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

    class Latched extends State {
        @Override
        void end() {
            currentState = new Ready();
        }

        @Override
        void execute() {
            shooter.tension();
        }

        @Override
        void init() {

        }

        @Override
        boolean isFinished() {
            return shooter.getState() == Shooter.TensionerState.tensioned;
        }
    }

    class MovingFlapper extends State {
        double initTime;

        @Override
        void end() {
            currentState = new Shooting();

        }

        @Override
        void execute() {
        }

        @Override
        void init() {
            initTime = Timer.getFPGATimestamp();
            Scheduler.getInstance().add(new FlapDown());
        }

        @Override
        boolean isFinished() {
            return Timer.getFPGATimestamp() - initTime > .5;
        }

    }

    class MovingIntake extends State {
        double startTime;

        @Override
        void end() {
            currentState = new MovingFlapper();

        }

        @Override
        void execute() {
            // TODO Auto-generated method stub

        }

        @Override
        void init() {
            startTime = Timer.getFPGATimestamp();
            Scheduler.getInstance().add(new IntakeIn());
        }

        @Override
        boolean isFinished() {
            // TODO Auto-generated method stub
            return Timer.getFPGATimestamp() - startTime > .1;
        }

    }

    class Ready extends State {
        @Override
        void end() {
            currentState = new MovingFlapper();
            Robot.oi.autoShoot = false;
        }

        @Override
        void execute() {
            shooter.holdTension();
        }

        @Override
        void init() {
        }

        @Override
        boolean isFinished() {

            return Robot.oi.shoot.get() || Robot.oi.autoShoot;
        }
    }

    class Returning extends State {
        double readyTime = -1;

        @Override
        void end() {
            shooter.latch();
            currentState = new Latched();

        }

        @Override
        void execute() {
            shooter.unTension();
            if (shooter.isShooterDown() && readyTime == -1)
                readyTime = Timer.getFPGATimestamp();
        }

        @Override
        void init() {
        }

        @Override
        boolean isFinished() {
            return readyTime != -1 && ((Timer.getFPGATimestamp() - readyTime) > 1);
        }
    }

    class Shooting extends State {
        double initTime;

        @Override
        void end() {
            currentState = new Returning();
        }

        @Override
        void execute() {
            shooter.setZero();
        }

        @Override
        void init() {
            shooter.unlatch();
            initTime = Timer.getFPGATimestamp();
        }

        @Override
        boolean isFinished() {
            return !Robot.oi.shoot.get() && Timer.getFPGATimestamp() - initTime > 1;
        }
    }

    abstract class State {
        boolean init = false;

        abstract void end();

        abstract void execute();

        abstract void init();

        abstract boolean isFinished();
    }
}
