package motionProfilling;

import java.util.ArrayList;

public class MotionControl {
	private ArrayList<State> states= new ArrayList<State>();
	private ArrayList<Segment> segments = new ArrayList<Segment>();
	public static final double Robot_Width = 10;
	public static final double Robot_Max_Speed = 10;
	public static final double Robot_Max_Acceleration = 5;
	
	public MotionControl(String s)
	{
		states = InputText.parseInput(s);
		
		states.get(0).setVelocity(0);
		states.get(states.size()-1).setVelocity(0);
		
		calculateDirections();
		
		for(int i=0; i<states.size()-1; i++)
		{
			segments.add(new Segment(states.get(i),states.get(i+1)));
		}
		for(Segment segment: segments)
		{
			
		}
	}
	private void calculateDirections()
	{
		for(int i =1; i<states.size()-1; i++)
		{
			double direction1 = states.get(i-1).getDirection();
			double direction2 = Math.toDegrees(calculateTheta(states.get(i),states.get(i+1)));
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
