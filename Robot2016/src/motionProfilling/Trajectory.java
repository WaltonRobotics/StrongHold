package motionProfilling;

public class Trajectory extends java.util.ArrayList<Position> {
	private double totalDistanceLeft = -1;
	private double totalDistanceRight = -1;
	private double totalTime = -1;
	private State start;
	private State end;
	private final double deltaS = 1.0/(MotionControl.numPoints-1);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Trajectory(TrajectorySpline tr, State start, State end)
	{
		this.start = start;
		this.end = end;
		generatePositions(tr);
	}

	private void generatePositions(TrajectorySpline tr) {
		for (MathPosition mp: tr) {
			Position p = new Position();
			p.s = mp.s;
			p.curvature = mp.curvature;
			p.velocity = pythag(mp.dXds,mp.dYds);
			p.acceleration = pythag(mp.d2Xds2,mp.d2Yds2);
			add(p);
		}
		fixAcceleration();
		limitVelocity();
		setDeltaDistances();
		setDeltaTimes();
		
	}
	public void setTotals()
	{
		setTotalTimes();
		setTotalDistances();
	}
	private void setTotalTimes()
	{
		double time = start.getTime();
		for(int i=0; i<size();i++)
		{
			get(i).totalTime = time;
			time = get(i).deltaTime;
		}
	}
	private void setTotalDistances()
	{
		double distanceLeft = start.getDistanceLeft();
		for(int i=0; i<size();i++)
		{
			get(i).totalDistanceLeft = distanceLeft;
			distanceLeft = get(i).getDeltaLengthLeft();
		}
		double distanceRight = start.getDistanceRight();
		for(int i=0; i<size();i++)
		{
			get(i).totalDistanceRight = distanceRight;
			distanceRight = get(i).getDeltaLengthRight();
		}
	}
	private void setDeltaDistances()
	{
		for(int i = 1; i<size(); i++)
		{
			double distance = get(i).velocity*deltaS;
			get(i).deltaLength = distance;
		}
	}
	private void setDeltaTimes()
	{
		for(int i = 1; i<size(); i++)
		{
			double time = get(i).deltaLength/2;
			time = time/(get(i).velocity + get(i-1).velocity);
			get(i).deltaTime = time;
		}
	}
	private void fixAcceleration() {
		get(0).velocity = start.getVelocity();
		for(int i=1; i<size(); i++)
		{
			double velocityPossible = get(i-1).velocity+MotionControl.Robot_Max_Acceleration*deltaS;
			double velocity = Math.min(velocityPossible, get(i).velocity);
			if(get(i).velocity !=velocity)
			{
				get(i).velocity = velocity;
				get(i).acceleration = MotionControl.Robot_Max_Acceleration;
			}
		}
		if(end.getVelocity()!=-1)
			get(size()-1).velocity = end.getVelocity();
		for(int i=size()-2; i>0; i--)
		{
			double velocityPossible = get(i+1).velocity-MotionControl.Robot_Max_Acceleration*deltaS;
			double velocity = Math.min(velocityPossible, get(i).velocity);
			if(get(i).velocity!=velocity)
			{
				get(i).velocity = velocity;
				get(i).acceleration = -MotionControl.Robot_Max_Acceleration;
			}
		}
	}
	private void limitVelocity()
	{
		for(Position p:this)
		{
			double largerVelocity = Math.max(p.getVelocityLeft(),p.getVelocityRight());
			if(largerVelocity>MotionControl.Robot_Max_Speed)
					p.velocity = MotionControl.Robot_Max_Speed/(1+MotionControl.Robot_Width/2*p.curvature);
		}
	}

	private double pythag(double a, double b)
	{
		return Math.pow(a*a+b*b,.5);
	}

	public double getDeltaTime() {

		if (totalTime == -1) {
			totalTime += 1;
			for (int i = 0; i < size(); i++) {
				totalTime += get(i).deltaTime;
			}
		}

		return totalTime;
	}

	public double getDeltaDistanceLeft() {

		if (totalDistanceLeft == -1) {
			totalDistanceLeft += 1;
			for (int i = 0; i < size(); i++) {
				totalDistanceLeft += get(i).getDeltaLengthLeft();
			}
		}

		return totalDistanceLeft;
	}

	public double getDeltaDistanceRight() {

		if (totalDistanceRight == -1) {
			totalDistanceRight += 1;
			for (int i = 0; i < size(); i++) {
				totalDistanceRight += get(i).getDeltaLengthRight();
			}
		}

		return totalDistanceRight;
	}

}
