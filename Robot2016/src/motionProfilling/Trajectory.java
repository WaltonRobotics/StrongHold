package motionProfilling;

import java.util.ArrayList;


public class Trajectory extends java.util.ArrayList<Position> {
	private State start;
	private State end;

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
			// TFS: This would be better as a constructor on Position which takes an MathPosition as a parameter
			Position p = new Position();
			p.s = mp.s;
			p.curvature = mp.curvature;
			p.x = mp.x;
			p.y = mp.y;
			add(p);
		}
		setDeltaDistances();
		limitVelocity();
		setDeltaTimes();
		setDeltaV();
		setA();
		
	}
	
	public void setDeltaV()
	{
		for(int i=0;i<size()-1; i++)
		{
			get(i).deltaVelocityLeft = get(i+1).getVelocityLeft()-get(i).getVelocityLeft();
			get(i).deltaVelocityRight = get(i+1).getVelocityRight()-get(i).getVelocityRight();
		}
	}
	public void setA()
	{
		for(int i=0;i<size()-1; i++)
		{
			get(i).accelerationLeft = get(i).deltaVelocityLeft/get(i).deltaTime;
			get(i).accelerationRight = get(i).deltaVelocityRight/get(i).deltaTime;
		}
		int i =size()-1;
		get(i).accelerationLeft = 0;
		get(i).accelerationRight = 0;
	
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
		// TFS: Could put into same loop?
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
	/**
	 *  
	 */
	private void setDeltaDistances()
	{
		for(int i = 1; i<size(); i++)
		{
			double dl = pythag(get(i).x-get(i-1).x, get(i).y-get(i-1).y);
			get(i-1).deltaLength = dl;
		}
	}
	/**
	 * set the delta time for each position in the trajectory 
	 */
	private void setDeltaTimes()
	{
		for(int i = 1; i<size(); i++)
		{
			double time = get(i-1).deltaLength*2; 
			time /= (get(i).velocity + get(i-1).velocity);
			get(i-1).deltaTime = time;
		}
	}
	/**
	 * Limit velocity so that the robot is physically capable of traveling the desired path
	 * Limits the velocity of the left and the right side so that the velocity does not exceed the max
	 * Limits velocity to ensure that the robot can accelerate to the desired speed
	 */
	private void limitVelocity() {
		get(0).velocity = 0;
		for(int i=1; i<size(); i++)
		{
			double velocity1 = Math.pow(get(i-1).velocity*get(i-1).velocity+2*MotionControl.Robot_Max_Acceleration*get(i-1).deltaLength, .5);
			double velocity2 = MotionControl.Robot_Max_Speed/(1+get(i).curvature*MotionControl.Robot_Width/2);
			double velocity3 = MotionControl.Robot_Max_Speed/(1-get(i).curvature*MotionControl.Robot_Width/2);
//			System.out.println("v1 "+velocity1+" v2 "+velocity2+" v3 "+velocity3);
			get(i).velocity = Math.min(velocity1, Math.min(velocity2, velocity3));
		}
		if(end.getVelocity()!=-1)
			get(size()-1).velocity = end.getVelocity();
		for(int i= size()-2; i>=0; i--)
		{
			double velocity = Math.pow(get(i+1).velocity*get(i+1).velocity+2*MotionControl.Robot_Max_Acceleration*get(i).deltaLength, .5);
			get(i).velocity = Math.min(get(i).velocity, velocity);
//			System.out.println("v4 "+velocity);
		}
	}
	/**
	 * Pythagorean formula
	 * @param a first double
	 * @param b second double
	 * @return (a^2 +b^2)^.5
	 */
	private double pythag(double a, double b)
	{
		return Math.pow(a*a+b*b,.5);
	}

	// TFS: Why are these here? Does setTotals not calculate the same stuff? It would be more
	// efficient to use cached values rather than recalculate each time
	// these methods are called?
	
	/**
	 * @return the total time this trajectory will take
	 */
	public double getTotalTime() {
		double totalTime = 0;
		for (int i = 0; i < size(); i++) 
				totalTime += get(i).deltaTime;
		return totalTime;
	}

	/**
	 * @return the total length the left side of the robot should travel during this tajectory
	 */
	public double getTotalDistanceLeft() {
		double totalDistanceLeft =0;
			for (int i = 0; i < size(); i++) 
				totalDistanceLeft += get(i).getDeltaLengthLeft();

		return totalDistanceLeft;
	}

	/**
	 * @return the total length the right side of the robot should travel during this trajectory
	 */
	public double getTotalDistanceRight() {
		double totalDistanceRight = 0;
			for (int i = 0; i < size(); i++) 
				totalDistanceRight += get(i).getDeltaLengthRight();

		return totalDistanceRight;
	}

}
