package motionProfilling;

public class Trajectory extends java.util.ArrayList<Position> {
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
	public void dumpValues()
	{
		for(Position p: this)
			System.out.println(p);
	}
	private void generatePositions(TrajectorySpline tr) {
		for (MathPosition mp: tr) {
			Position p = new Position();
			p.s = mp.s;
			p.curvature = mp.curvature;
			p.x = mp.x;
			p.y = mp.y;
			add(p);
		}
		setDeltaDistances();
		fixAcceleration();
		setDeltaTimes();
		setDeltaV();
		setA();
		
	}
	public void setDeltaV()
	{
		for(int i=0;i<size(); i++)
		{
			get(i).deltaVelocityLeft = get(i+1).getVelocityLeft()-get(i).getVelocityLeft();
			get(i).deltaVelocityRight = get(i+1).getVelocityRight()-get(i).getVelocityRight();
		}
	}
	public void setA()
	{
		for(int i=0;i<size(); i++)
		{
			get(i).accelerationLeft = get(i).deltaVelocityLeft/get(i).deltaTime;
			get(i).accelerationRight = get(i).deltaVelocityRight/get(i).deltaTime;
		}
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
			time += get(i).deltaTime;
		}
	}
	private void setTotalDistances()
	{
		double distanceLeft = start.getDistanceLeft();
		for(int i=0; i<size();i++)
		{
			get(i).totalDistanceLeft = distanceLeft;
			distanceLeft += get(i).getDeltaLengthLeft();
		}
		double distanceRight = start.getDistanceRight();
		for(int i=0; i<size();i++)
		{
			get(i).totalDistanceRight = distanceRight;
			distanceRight += get(i).getDeltaLengthRight();
		}
	}
	private void setDeltaDistances()
	{
		for(int i = 1; i<size(); i++)
		{
			double dl = pythag(get(i).x-get(i-1).x, get(i).y-get(i-1).y);
			get(i-1).deltaLength = dl;
		}
	}
	private void setDeltaTimes()
	{
		for(int i = 1; i<size(); i++)
		{
			double time = get(i-1).deltaLength*2; 
			time /= (get(i).velocity + get(i-1).velocity);
			get(i-1).deltaTime = time;
		}
	}
	private void fixAcceleration() {
		get(0).velocity = 0;
		for(int i=1; i<size(); i++)
		{
			double velocity1 = Math.pow(get(i-1).velocity*get(i-1).velocity+2*MotionControl.Robot_Max_Acceleration*get(i-1).deltaLength, .5);
			double velocity2 = MotionControl.Robot_Max_Speed/(1+get(i).curvature*MotionControl.Robot_Width/2);
			double velocity3 = MotionControl.Robot_Max_Speed/(1-get(i).curvature*MotionControl.Robot_Width/2);
			System.out.println("v1 "+velocity1+" v2 "+velocity2+" v3 "+velocity3);
			get(i).velocity = Math.min(velocity1, Math.min(velocity2, velocity3));
		}
		if(end.getVelocity()!=-1)
			get(size()-1).velocity = end.getVelocity();
		for(int i= size()-2; i>=0; i--)
		{
			double velocity = Math.pow(get(i+1).velocity*get(i+1).velocity+2*MotionControl.Robot_Max_Acceleration*get(i).deltaLength, .5);
			get(i).velocity = Math.min(get(i).velocity, velocity);
			System.out.println("v4 "+velocity);
		}
	}
	private double pythag(double a, double b)
	{
		return Math.pow(a*a+b*b,.5);
	}

	public double getDeltaTime() {
		double totalTime = 0;
		for (int i = 0; i < size(); i++) 
				totalTime += get(i).deltaTime;
		return totalTime;
	}

	public double getDeltaDistanceLeft() {
		double totalDistanceLeft =0;
			for (int i = 0; i < size(); i++) 
				totalDistanceLeft += get(i).getDeltaLengthLeft();

		return totalDistanceLeft;
	}

	public double getDeltaDistanceRight() {
		double totalDistanceRight = 0;
			for (int i = 0; i < size(); i++) 
				totalDistanceRight += get(i).getDeltaLengthRight();

		return totalDistanceRight;
	}

}
