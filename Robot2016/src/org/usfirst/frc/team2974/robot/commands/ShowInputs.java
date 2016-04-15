package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShowInputs extends Command {
	Accelerometer accel;
	public ShowInputs() {
		requires(Robot.inputs);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.inputs.updateSmartDashboard();
		Robot.compass.dumpSmartDashboardValues();
		Robot.camera.setNetTable();
		Robot.camera.dumpSmartDshboardValues();
		Robot.arm.dumpSmartDashboardValues();
		Robot.shooter.dumpSmartDashboardValues();
		Robot.driveTrain.initSmartdashBoardValues();
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G); 
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.inputs.updateSmartDashboard();
		Robot.compass.dumpSmartDashboardValues();
		Robot.camera.setNetTable();
		Robot.camera.dumpSmartDshboardValues();
		Robot.arm.dumpSmartDashboardValues();
		Robot.shooter.dumpSmartDashboardValues();
		
		//accel = new BuiltInAccelerometer(); 
		
		SmartDashboard.putNumber("x", accel.getX());
		SmartDashboard.putNumber("y", accel.getY());
		SmartDashboard.putNumber("z", accel.getZ());
		
		//		if(Robot.oi.testAuton.get())
	//		Scheduler.getInstance().add(new TestAuton());
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
}
