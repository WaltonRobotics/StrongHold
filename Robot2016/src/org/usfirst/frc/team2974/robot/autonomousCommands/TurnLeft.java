package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnLeft extends Command {
	double time ;
	double startTime;
	double speed;

	public TurnLeft() {
		this(1.25,.5);
	}
	public TurnLeft(double time, double speed)
	{
		requires(Robot.driveTrain);
		//SmartDashboard.putNumber("timeTurn", time);
		//SmartDashboard.putNumber("speedTurn", speed);
		this.time = time;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
//		time = SmartDashboard.getNumber("SpecialTime");
//		speed = SmartDashboard.getNumber("SpecialPower");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.setSpeeds(-speed, speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return ( Timer.getFPGATimestamp() - startTime > time);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
