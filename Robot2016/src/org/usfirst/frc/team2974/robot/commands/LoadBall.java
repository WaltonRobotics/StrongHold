package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.OI;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.IntakeWheels;

import edu.wpi.first.wpilibj.command.Command;

public class LoadBall extends Command{

	IntakeWheels intakeWheels;
	OI oi;
	public LoadBall() {
		requires(Robot.intakeWheels);
		intakeWheels = Robot.intakeWheels;
		oi = Robot.oi;
	}
	@Override
	protected void initialize() {

		
	}

	@Override
	protected void execute() {
		if(oi.intake.get())
			intakeWheels.input();
		else if(oi.outtake.get())
			intakeWheels.output();
		else if(oi.stoptake.get())
			intakeWheels.stop();		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
	

}
