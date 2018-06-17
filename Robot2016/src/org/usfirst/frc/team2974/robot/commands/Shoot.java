package org.usfirst.frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.subsystems.Shooter;

//import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDown;
//import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class Shoot extends Command {

	private State currentState;
	private Shooter shooter = Robot.shooter;

	public Shoot() {
		requires(shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (shooter.isShooterDown()) {
			currentState = new Latched();
		} else {
			currentState = new Returning();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!currentState.init) {
			currentState.init();
			currentState.init = true;
		} else if (currentState.isFinished()) {
			currentState.end();
		} else {
			currentState.execute();
		}

		//Log.instance().logCall(new LogMessage(Severity.INFORMATION, SubSystem.SHOOTER, "Shooter", "Shooter State" + currentState.getClass()+"", 120));
		SmartDashboard.putString("Shooter State", currentState.getClass() + "");
		SmartDashboard.putString("Tensioner State", shooter.getState() + "");


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	abstract class State {

		boolean init = false;

		abstract void init();

		abstract void execute();

		abstract void end();

		abstract boolean isFinished();
	}

	class Returning extends State {

		double readyTime = -1;

		void init() {
		}

		void execute() {
			shooter.unTension();
			if (shooter.isShooterDown() && readyTime == -1) {
				readyTime = Timer.getFPGATimestamp();
			}
		}

		void end() {
			shooter.latch();
			currentState = new Latched();

		}

		boolean isFinished() {
			return readyTime != -1 && ((Timer.getFPGATimestamp() - readyTime) > 1);
		}
	}

	class Latched extends State {

		void init() {

		}

		void execute() {
			shooter.tension();
		}

		void end() {
			currentState = new Ready();
		}

		boolean isFinished() {
			return shooter.getState() == Shooter.TensionerState.tensioned;
		}
	}

	class Ready extends State {

		void init() {
		}

		void execute() {
			shooter.holdTension();
		}

		void end() {
			currentState = new MovingFlapper();
			Robot.oi.autoShoot = false;
		}

		boolean isFinished() {

			return Robot.oi.shoot.get() || Robot.oi.autoShoot;
		}
	}

	class MovingFlapper extends State {

		double initTime;

		void init() {
			initTime = Timer.getFPGATimestamp();
			Scheduler.getInstance().add(new FlapDown());
		}


		@Override
		void execute() {
		}

		@Override
		void end() {
			currentState = new Shooting();

		}

		@Override
		boolean isFinished() {
			return Timer.getFPGATimestamp() - initTime > .5;
		}

	}

	class MovingIntake extends State {

		double startTime;

		@Override
		void init() {
			startTime = Timer.getFPGATimestamp();
			Scheduler.getInstance().add(new IntakeOut());
		}

		@Override
		void execute() {
			// TODO Auto-generated method stub

		}

		@Override
		void end() {
			currentState = new MovingFlapper();

		}

		@Override
		boolean isFinished() {
			// TODO Auto-generated method stub
			return Timer.getFPGATimestamp() - startTime > .1;
		}

	}

	class Shooting extends State {

		double initTime;

		void init() {
			shooter.unlatch();
			initTime = Timer.getFPGATimestamp();
		}

		void execute() {
			shooter.setZero();
		}

		void end() {
			currentState = new Returning();
		}

		boolean isFinished() {
			return !Robot.oi.shoot.get() && Timer.getFPGATimestamp() - initTime > 1;
		}
	}
}
