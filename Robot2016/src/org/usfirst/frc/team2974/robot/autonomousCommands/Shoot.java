package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {
	double startTime;
	double time = .5;
	boolean isTimer;
	
	public Shoot() {
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isTimer = false;
		Robot.shooter.unlatch();
		startTime = Timer.getFPGATimestamp();
		Robot.oi.autoShoot = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putBoolean("autoshoot", Robot.oi.autoShoot);
		if(Robot.shooter.isShooterDown()){
			if(!isTimer){
				time = Timer.getFPGATimestamp() + .25;
			}
			isTimer = true;
		}else{
			Robot.shooter.unTension();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return Timer.getFPGATimestamp() - startTime > time;
		return isTimer && Timer.getFPGATimestamp() > time;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.oi.autoShoot = false;
		Robot.shooter.latch();
		SmartDashboard.putString("Autonomous stuff", "There ya go, ball in da goal");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.oi.autoShoot = false;
	}
}
