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
	private double distanceLeft;
	private double deltaDistanceLeft;
	private double deltaDistanceRight;
	
	public double getDeltaDistanceLeft() {
		return deltaDistanceLeft;
	}

	public void setDeltaDistanceLeft(double deltaDistanceLeft) {
		this.deltaDistanceLeft = deltaDistanceLeft;
	}

	public double getDeltaDistanceRight() {
		return deltaDistanceRight;
	}

	public void setDeltaDistanceRight(double deltaDistanceRight) {
		this.deltaDistanceRight = deltaDistanceRight;
	}

	public double getDistanceLeft() {
		return distanceLeft;
	}

	public void setDistanceLeft(double distanceLeft) {
		this.distanceLeft = distanceLeft;
	}

	public double getDistanceRight() {
		return distanceRight;
	}

	public void setDistanceRight(double distanceRight) {
		this.distanceRight = distanceRight;
	}

	private double distanceRight;
	
	// TFS: I would remove the trajectory from the state and have the MotionController 
	// handle it directly. You should be able to just store the current trajectory, and
	// create them on demand. As you create them, they can be initialized with the final
	// values from the previous one. You could make a method here that would replace the
	// Segment class, creating and returning a trajectory based on the State definition.
	// It would need either the last trajectory or the information from it definining the
	// current time/distance left/distance right.
	
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

		// TFS: Is this here to get round issues with calculating tangents? 
		// or is it test code? 
		//
		//PGK To get around issues - I use the slope of lines and when the angle 
		//is 90 or 270, the slope is infinity!
		if(theta==90)
			theta = 90.5;
		if(theta == 270)
			theta = 270.5;
		return theta;
	}
	
	public void setDirection(double theta)
	{
		while(theta>=360)
			theta-=360;
		while(theta<0)
			theta+=360;
		
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
	@Override
	public String toString() {
		return "State [coord=" + coord + ", theta=" + theta + ", velocity=" + velocity + "]";
	}
}
