package motionProfilling;

public class Position {
	private double s;
	private double deltaLength;
	private double deltaTime;
	private double time;
	private double velocity;
	//TODO fix acceleration-- limit velocity function in segment.java throws this off
	private double acceleration;
	private double curvature;
	private boolean turningRight;

	public double getDeltaLegthLeft() {
		if (turningRight)
			return (1 + MotionControl.Robot_Width / 2 * curvature) * deltaLength;
		else
			return (1 - MotionControl.Robot_Width / 2 * curvature) * deltaLength;
	}

	public double getDeltaLegthRight() {
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

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public double getDeltaLength() {
		return deltaLength;
	}

	public void setDeltaLength(double deltaLength) {
		this.deltaLength = deltaLength;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getCurvature() {
		return curvature;
	}

	public void setCurvature(double curvature) {
		this.curvature = curvature;
	}

	public boolean isTurningRight() {
		return turningRight;
	}

	public void setTurningRight(boolean turningRight) {
		this.turningRight = turningRight;
	}
	
	public void setTime(double time)
	{
		this.time = time;
	}
	
	public double getTime()
	{
		return time;
	}
	
	public void setDeltaTime(double dtime)
	{
		this.deltaTime = dtime;
	}
	
	public double getDeltaTime()
	{
		return deltaTime;
	}

}
