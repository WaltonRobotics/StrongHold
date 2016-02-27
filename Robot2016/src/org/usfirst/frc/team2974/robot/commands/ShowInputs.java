package org.usfirst.frc.team2974.robot.commands;



import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Camera;
import org.usfirst.frc.team2974.robot.subsystems.Compass;

import edu.wpi.first.wpilibj.command.Command;

public class ShowInputs extends Command {
    Compass compass = Robot.compass;
    Camera camera = Robot.camera;
	public ShowInputs() {
        requires(Robot.inputs);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.inputs.updateSmartDashboard();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.inputs.updateSmartDashboard();
    	Robot.compass.dumpSmartDashboardValues();
    	Robot.camera.dumpSmartDshboardValues();
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
    	Robot.inputs.updateSmartDashboard();
    }
}
