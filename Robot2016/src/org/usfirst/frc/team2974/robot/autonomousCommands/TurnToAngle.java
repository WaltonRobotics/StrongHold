package org.usfirst.frc.team2974.robot.autonomousCommands;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is used to turn the robot from its current angle (START_ANGLE) to
 * its GOAL_ANGLE it does this by turn form a point.
 */
public class TurnToAngle extends Command {

	private final double GOAL_ANGLE;
	private final double START_YAW_ANGLE;// = Robot.compass.getYaw();
	private final boolean TURN_CLOCKWISE;
	private final double ANGLES_TO_TURN;
	private final double TOLERANCE;

	public TurnToAngle(double angle) {
		requires(Robot.getDriveTrain());
		requires(Robot.getCompass());
		this.TOLERANCE = 10;
		this.GOAL_ANGLE = Math.abs(angle);
		this.START_YAW_ANGLE = Robot.getCompass().getYaw();
		this.TURN_CLOCKWISE = Math.abs(this.GOAL_ANGLE - this.START_YAW_ANGLE) <= 180;
		this.ANGLES_TO_TURN = TURN_CLOCKWISE ? Math.abs(GOAL_ANGLE - START_YAW_ANGLE)
				: 360 - Math.abs(GOAL_ANGLE - START_YAW_ANGLE);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double deltaYawAngle = Math.abs(Robot.getCompass().getYaw() - START_YAW_ANGLE);

		double SPEED = .5;
		if (TURN_CLOCKWISE)
			if (Math.abs(deltaYawAngle - START_YAW_ANGLE) < ANGLES_TO_TURN)
				Robot.getDriveTrain().setSpeeds(SPEED, -SPEED);

			else if (Math.abs(deltaYawAngle - START_YAW_ANGLE) < ANGLES_TO_TURN)
				Robot.getDriveTrain().setSpeeds(-SPEED, SPEED);

			else
				Robot.getDriveTrain().setSpeeds(0, 0);
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
		return Math.abs(Math.abs(Robot.getCompass().getYaw() - START_YAW_ANGLE) - GOAL_ANGLE) <= TOLERANCE;
	}
}
