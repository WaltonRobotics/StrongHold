package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class PIDControllerAccel extends PIDController {
	private double kV;
	private double kA;
	private double currentVelocity;

	private double currentAcceleration;

	public PIDControllerAccel(final double Kp, final double Ki, final double Kd, final double Kf,
			final PIDSource source, final PIDOutput output, final double kV, final double kA) {
		super(Kp, Ki, Kd, Kf, source, output, .01);

		this.kV = kV;
		this.kA = kA;

	}

	@Override
	protected double calculateFeedForward() {
		return currentVelocity * kV + currentAcceleration * kA;
	}

	public synchronized void setkA(final double kA) {
		this.kA = kA;
	}

	public synchronized void setkV(final double kV) {
		this.kV = kV;
	}

	public synchronized void setSetpoint(final double distance, final double velocity, final double acceleration) {
		setSetpoint(distance);
		currentVelocity = velocity;
		currentAcceleration = acceleration;
	}
}
