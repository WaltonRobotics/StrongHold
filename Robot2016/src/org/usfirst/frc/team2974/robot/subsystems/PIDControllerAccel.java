package org.usfirst.frc.team2974.robot.subsystems;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import motionProfilling.MotionControl;
import motionProfilling.Position;

public class PIDControllerAccel extends PIDController {
	public PIDControllerAccel(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output, double kV, double kA, boolean isLeft) {
		super(Kp, Ki, Kd, Kf, source, output, .01);
		
		this.kV = kV;
		this.kA = kA;
		this.isLeft = isLeft;
		
	}
	private double kV;
	private double kA;
	private boolean isLeft;
	private double currentVelocity;
	private double currentAcceleration;


	@Override
	protected double calculateFeedForward()
	{
		//System.out.println("i live");
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
	public synchronized void setSetpoint(MotionControl mc, double time)
	{
		//todo fix accelerations
		if(isLeft)
		{
			//System.out.println("setSetPoint left"+pos.totalDistanceLeft+"vel left"+pos.getVelocityLeft()+"accel left"+pos.accelerationLeft);
			setSetpoint(mc.distanceleft(time));
			currentVelocity = mc.velocityLeft(time);
			currentAcceleration = 0;
			System.out.println("vel left"+currentVelocity);
		}
		
		else
		{
			//System.out.println("setSetPoint right"+pos.totalDistanceRight+"vel right"+pos.getVelocityRight()+"accel right"+pos.accelerationRight);
			setSetpoint(mc.distanceRight(time));
			currentVelocity = mc.velocityRight(time);
			currentAcceleration = 0;
			System.out.println("vel right"+currentVelocity);
		}
			
		
	}
}
