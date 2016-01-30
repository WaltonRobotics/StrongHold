package motionProfilling;

public class Position {
	public double s;
	public double deltaLength;
	public double deltaTime;
	public double totalTime;//time until the start of this 
	public double velocity;
	//TODO fix acceleration-- limit velocity function in segment.java throws this off
	public double acceleration;
	public double curvature;
	public boolean turningRight;
	public double totalDistanceLeft;
	public double totalDistanceRight;
	
	
	public double getDeltaLengthLeft() {
		if (turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * deltaLength;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * deltaLength;
	}

	public double getDeltaLengthRight() {
		if (!turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * deltaLength;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * deltaLength;
	}

	public double getVelocityRight() {
		if (!turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * velocity;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * velocity;
	}

	public double getVelocityLeft() {
		if (turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * velocity;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * velocity;
	}

	public double getAccelerationLeft() {
		if (turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * acceleration;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * acceleration;
	}

	public double getAccelerationRight() {
		if (!turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * acceleration;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * acceleration;
	}

	
}
