package motionProfilling;

public class Position {
	public double s;
	public double deltaLength;
	public double deltaTime;
	public double deltaVelocityLeft;
	public double deltaVelocityRight;
	@Override
	public String toString() {
		return "Position [s=" + s + ", totalTime=" + totalTime + ", accelerationLeft=" + accelerationLeft
				+ ", accelerationRight=" + accelerationRight + ", totalDistanceLeft=" + totalDistanceLeft
				+ ", totalDistanceRight=" + totalDistanceRight + ", getVelocityRight()=" + getVelocityRight()
				+ ", getVelocityLeft()=" + getVelocityLeft() + "]";
	}

	public double totalTime;//time until the start of this 
	public double velocity;
	//TODO fix acceleration-- limit velocity function in segment.java throws this off
	public double accelerationLeft;
	public double accelerationRight;
	public double curvature;
	public boolean turningRight;
	public double totalDistanceLeft;
	public double totalDistanceRight;
	public double x;
	public double y;
	
	public Position(MathPosition mp)
	{
		this.s = mp.s;
		this.curvature = mp.curvature;
		this.x = mp.x;
		this.y = mp.y;
	}
	public double getDeltaLengthLeft() {
		return (1 - MotionControl.Robot_Width / 2 * curvature) * deltaLength;
		
	}

	public double getDeltaLengthRight() {
		return (1 + MotionControl.Robot_Width / 2 * curvature) * deltaLength;
	}

	public double getVelocityRight() {
		return (1 + MotionControl.Robot_Width / 2 * curvature) * velocity;
	}

	public double getVelocityLeft() {
		return (1 - MotionControl.Robot_Width / 2 * curvature) * velocity;
	}

	
}
