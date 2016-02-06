package org.usfirst.frc.team2974.robot.subsystems;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import motionProfilling.Position;

public class PIDControllerAccel extends PIDController {
	public PIDControllerAccel(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output, double kV, double kA, boolean isLeft) {
		super(Kp, Ki, Kd, Kf, source, output);
		this.kV = kV;
		this.kA = kA;
		// TODO Auto-generated constructor stub
	}
	private double kV;
	private double kA;
	private boolean isLeft;
	private double currentVelocity;
	private double currentAcceleration;


	@Override
	protected double calculateFeedForward()
	{
		return currentVelocity*kV+ currentAcceleration*kA;
		
	}
	public synchronized void setkV(double kV)
	{
		this.kV = kV;
	}
	public synchronized void setkA(double kA)
	{
		this.kA = kA;
	}
	public synchronized void setSetpoint(Position pos)
	{
		
		if(isLeft)
		{
			setSetpoint(pos.totalDistanceLeft);
			currentVelocity = pos.getVelocityLeft();
			currentAcceleration = pos.accelerationLeft;
		}
		
		else
		{
			setSetpoint(pos.totalDistanceRight);
			currentVelocity = pos.getVelocityRight();
			currentAcceleration = pos.accelerationRight;
		}
			
		
	}
}
