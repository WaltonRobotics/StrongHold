package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	private final double SPEED = .5;
	private final double TOLERANCE = 10;
    private final double goalAngle;
    
    private  double startYawAngle;
    private boolean turnClockwise;
    private double anglesToTurn;
	
    public TurnToAngle(double angle) {
        requires(Robot.driveTrain);
        requires(Robot.compass);
        this.goalAngle = Math.abs(angle);
    }
    
    // Called once after isFinished returns true
    @Override
	protected void end() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	double deltaYawAngle = Math.abs(Robot.compass.getYaw()- startYawAngle); 	
    	
    	if(turnClockwise)
    		if(Math.abs(deltaYawAngle - startYawAngle) < anglesToTurn)
    			Robot.driveTrain.setSpeeds(SPEED, -SPEED);
    	
    	else if(Math.abs(deltaYawAngle - startYawAngle) < anglesToTurn)
    		Robot.driveTrain.setSpeeds(-SPEED, SPEED);
    	
    	else
    		Robot.driveTrain.setSpeeds(0, 0);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	startYawAngle = Robot.compass.getYaw();
    	turnClockwise = Math.abs(goalAngle - startYawAngle) <= 180;
    	anglesToTurn = turnClockwise? Math.abs(goalAngle - startYawAngle): 360 - Math.abs(goalAngle - startYawAngle);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return Math.abs(Math.abs(Robot.compass.getYaw() -startYawAngle) - goalAngle) <= TOLERANCE;
    }
}
