package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmDown extends Command {

	double startTime;
	double totalTime = .7;
	double armPower = -1; 
	//practice bot = 1
	//comp bot = -1
	//TODO
	public ArmDown() {
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Timer.getFPGATimestamp() - startTime > .3 && Timer.getFPGATimestamp() - startTime < totalTime)
			Robot.arm.moveArmPower(armPower);
		else
			Robot.arm.moveArmPower(0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > totalTime;
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("Autonomous stuff", "There ya go, arm is down");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
