package motionProfilling;

import java.util.ArrayList;
//todo fix end condition
public class MotionControl {
	private ArrayList<State> states= new ArrayList<State>();
	private ArrayList<Segment> segments = new ArrayList<Segment>();
	public static final double Robot_Width = 1;
	public static final double Robot_Max_Speed = 1;
	public static final double Robot_Max_Acceleration = 1;

	public static final int numPoints = 20;
	private int trajIndex;
	private int stateIndex;
	public MotionControl(String s)
	{
		states = InputText.parseInput(s);
		
		states.get(0).setVelocity(0);
		states.get(states.size()-1).setVelocity(0);
		
		calculateDirections();
		
		for(int i=0; i<states.size()-1; i++)
		{
			segments.add(new Segment(states.get(i),states.get(i+1)));
			states.get(i).setTrajectory(segments.get(i).getTrajectory());
		}
		calculateTotalTimesForStates();
		calculateDistanceLeft();
		calculateDistanceRight();
		
		for(Segment seg: segments)
		{
			seg.getTrajectory().setTotals();
		}
		stateIndex = 0;
		trajIndex = 0;
		
	}
	private void calculateDistanceLeft()
	{
		double dl = 0;
		states.get(0).setDistanceLeft(0);
		for(int i=1; i<states.size(); i++)
		{
			dl+=segments.get(i-1).getTrajectory().getDeltaDistanceLeft();
			states.get(i).setDistanceLeft(dl);		
		}
	}
	private void calculateDistanceRight()
	{
		double dr = 0;
		states.get(0).setDistanceRight(0);
		for(int i=1; i<states.size(); i++)
		{
			dr+=segments.get(i-1).getTrajectory().getDeltaDistanceRight();
			states.get(i).setDistanceRight(dr);		
		}
	}
	private void calculateTotalTimesForStates()
	{
		double time = 0;
		states.get(0).setTime(0);
		for(int i=1; i<states.size(); i++)
		{
			time+=segments.get(i-1).getTrajectory().getDeltaTime();
			states.get(i).setTime(time);
			
		}
	}
	public Position getPosition(double time)
	{
		if(trajIndex>=numPoints)
		{
			stateIndex++;
			trajIndex = 0;
		}
		

			State state = states.get((int) stateIndex);
			Trajectory traj = state.getTrajectory();
			if(traj.get(trajIndex).totalTime<time)
			{
				trajIndex++;
				return getPosition(time);
			}
				
			return traj.get(trajIndex);
	}
	
	public double distanceleft(double  time)
	{
		Position p = getPosition(time);
		double distanceLeft = time- p.totalTime;
		distanceLeft = distanceLeft/p.deltaTime;
		distanceLeft*=p.getDeltaLengthLeft();
		distanceLeft+=p.totalDistanceLeft;
		return distanceLeft;
	}
	public double distanceRight(double time)
	{
		Position p = getPosition(time);
		double distanceRight = time- p.totalTime;
		distanceRight = distanceRight/p.deltaTime;
		distanceRight*=p.getDeltaLengthRight();
		distanceRight+=p.totalDistanceRight;
		return distanceRight;
	}
	private void calculateDirections()
	{
		for(int i =1; i<states.size()-1; i++)
		{
			double direction1 = states.get(i-1).getDirection();
			double direction2 = Math.toDegrees(calculateTheta(states.get(i),states.get(i+1)));
			if(direction1==0||direction2==0)
				if(direction2>=180)
					direction1=360;
				else
					direction2=360;
			
			double computedDirection = (direction1-direction2)/2;
			states.get(i).setDirection(computedDirection+direction2);
		}
		
	}
	
	private double calculateTheta(State state1,State state2)
	{
		double x1 = state1.getX();
		double y1 = state1.getY();
		double x2 = state2.getX();
		double y2 = state2.getY();
		if(x2>=x1 &&y2>=y1)
		{
			return Math.atan((y2-y1)/(x2-x1));
		}
		if(x2<x1 && y2<y1)
		{
			return Math.atan((y2-y1)/(x2-x1));
		}
		return Math.atan((x2-x1)/(y2-y1));
	}
	
}
