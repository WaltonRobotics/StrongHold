package motionProfilling;

public class State {
	private Coordinate coord;
	/**
	 * direction in degrees [0,360] (using unit circle notation
	 * -1 represents the direction was not specified
	 */
	private double theta = -1;
	
	private double velocity = -1;
	
	private double time;
	//trajectory from this state to next one
	private Trajectory trajectory;
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @param theta direction
	 */
	public State(double x, double y, double theta) {
		coord = new Coordinate(x,y);
		this.theta = theta;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}

	public Trajectory getTrajectory() {
		return trajectory;
	}

	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	/**
	 * @param x coordinate
	 * @param y coordinate
	 */
	public State(double x, double y) {
		coord = new Coordinate(x,y);
	}
	
	public State(Coordinate coord)
	{
		this.coord = coord;
	}

	/**
	 * @return x coordinate
	 */
	public double getX() {
		return coord.getX();
	}

	/**
	 * @return coordinate
	 */
	public double getY() {
		return coord.getY();
	}

	/**
	 * @return direction, -1 if no direction has been specified 
	 */
	public double getDirection() {
		while(theta>=360)
			theta-=360;
		while(theta<0)
			theta+=360;
		if(theta==90)
			theta = 90.5;
		if(theta == 270)
			theta = 270.5;
		return theta;
	}
	
	public void setDirection(double theta)
	{
		this.theta = theta;
	}
	
	public void setVelocity(double velocity)
	{
		this.velocity = velocity;
	}
	//negative 1 means to trajectory set
	public double getVelocity()
	{
		return velocity;
	}
	public double getTime()
	{
		return time;
	}
	
	public void setTime(double time)
	{
		this.time = time;
	}
}
