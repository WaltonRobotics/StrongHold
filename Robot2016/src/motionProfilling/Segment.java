package motionProfilling;

public class Segment {
	private State start;
	private State end;
	private Coordinate point1;
	private Coordinate point2;
	private Trajectory trajectory;
	private double deltaS = .05;

	public Segment(State start, State end) {
		this.start = start;
		this.end = end;
		choosePoints();
		trajectory = new Trajectory();
		generatePositions();
	}
	public Trajectory getTrajectory()
	{
		return trajectory;
	}
	private void generatePositions() {
		for (int s = 0; s <= 1; s += deltaS) {
			Position p = new Position();
			p.setS(s);
			p.setCurvature(getK(s));
			p.setTurningRight(isTurningRight());
			p.setVelocity(getVelocity(s));
			p.setAcceleration(getAcceleration(s));
			trajectory.add(p);
		}
		fixAcceleration();
		limitVelocity();
		findDistance();
		setDeltaTimes();
	}
	private void findDistance()
	{
		for(int i = 1; i<trajectory.size(); i++)
		{
			double distance = trajectory.get(i).getVelocity()*deltaS;
			trajectory.get(i).setDeltaLength(distance);
		}
	}
	private void setDeltaTimes()
	{
		for(int i = 1; i<trajectory.size(); i++)
		{
			double time = trajectory.get(i).getDeltaLength()/2;
			time = time/(trajectory.get(i).getVelocity()+trajectory.get(i-1).getVelocity());
			trajectory.get(i).setDeltaTime(time);
		}
	}
	private void fixAcceleration() {
		trajectory.get(0).setVelocity(start.getVelocity());
		for(int i=1; i<trajectory.size(); i++)
		{
			double velocityPossible = trajectory.get(i-1).getVelocity()+MotionControl.Robot_Max_Acceleration*deltaS;
			double velocity = Math.min(velocityPossible, trajectory.get(i).getVelocity());
			if(trajectory.get(i).getVelocity()!=velocity)
			{
				trajectory.get(i).setVelocity(velocity);
				trajectory.get(i).setAcceleration(MotionControl.Robot_Max_Acceleration);
			}
		}
		if(end.getVelocity()!=-1)
			trajectory.get(trajectory.size()-1).setVelocity(end.getVelocity());
		for(int i=trajectory.size()-2; i>0; i--)
		{
			double velocityPossible = trajectory.get(i+1).getVelocity()-MotionControl.Robot_Max_Acceleration*deltaS;
			double velocity = Math.min(velocityPossible, trajectory.get(i).getVelocity());
			if(trajectory.get(i).getVelocity()!=velocity)
			{
				trajectory.get(i).setVelocity(velocity);
				trajectory.get(i).setAcceleration(-MotionControl.Robot_Max_Acceleration);
			}
		}
	}
	private void limitVelocity()
	{
		for(Position p:trajectory)
		{
			double largerVelocity = Math.max(p.getVelocityLeft(),p.getVelocityRight());
			if(largerVelocity>MotionControl.Robot_Max_Speed)
					p.setVelocity(MotionControl.Robot_Max_Speed/(1+MotionControl.Robot_Width/2*p.getCurvature()));
		}
	}
	
	private double getVelocity(double s)
	{
		return Math.pow(getVX(s)*getVX(s)+getVY(s)*getVY(s),.5);
	}
	
	private double getAcceleration(double s)
	{
		return Math.pow(getAX(s)*getAX(s)+getAY(s)*getAY(s),.5);
	}

	private boolean isTurningRight() {
		Line line1 = new Line(start);
		Line line2 = new Line(end);
		if (line2.getM() - line1.getM() >= 0)
			return false;
		return true;

	}

	private void choosePoints() {
		Line line1 = new Line(start);
		Line line2 = new Line(end);
		Coordinate intersection = getIntersection(line1, line2);

		double x1 = (intersection.getX() - start.getX()) * 2.0 / 3 + start.getX();// x
																					// point
																					// 2/3
																					// of
																					// way
																					// from
																					// start
																					// to
																					// intersection
		point1 = new Coordinate(x1, line1.getY(x1));

		double x2 = (end.getX() - intersection.getX()) * 2.0 / 3 + intersection.getX();// x
																						// point
																						// 2/3
																						// of
																						// way
																						// from
																						// intersection
																						// to
																						// end
		point2 = new Coordinate(x2, line2.getY(x2));

	}

	private Coordinate getIntersection(Line line1, Line line2) {
		double x = (line2.getB() - line1.getB()) / (line1.getM() - line2.getM());
		double y = line1.getM() * x + line1.getB();
		return new Coordinate(x, y);
	}

	public double getPX(double s) {// bezier curve stuffs
		double a = (1 - s) * (1 - s) * (1 - s) * start.getX();
		double b = 3 * (1 - s) * (1 - s) * s * point1.getX();
		double c = 3 * (1 - s) * s * s * point2.getX();
		double d = s * s * s * end.getX();
		return a + b + c + d;
	}

	public double getPY(double s) {// bezier curve stuffs
		double a = (1 - s) * (1 - s) * (1 - s) * start.getY();
		double b = 3 * (1 - s) * (1 - s) * s * point1.getY();
		double c = 3 * (1 - s) * s * s * point2.getY();
		double d = s * s * s * end.getY();
		return a + b + c + d;
	}

	public double getVX(double s) {
		double a = -3 * (1 - s) * (1 - s) * start.getX();
		double b = (3 * (1 - s) * (1 - s) - 6 * s * (1 - s)) * point1.getX();
		double c = (6 * (1 - s) * s - 3 * s * s) * point2.getX();
		double d = 3 * s * s * end.getX();
		return a + b + c + d;
	}

	public double getVY(double s) {
		double a = -3 * (1 - s) * (1 - s) * start.getY();
		double b = (3 * (1 - s) * (1 - s) - 6 * s * (1 - s)) * point1.getY();
		double c = (6 * (1 - s) * s - 3 * s * s) * point2.getY();
		double d = 3 * s * s * end.getY();
		return a + b + c + d;
	}

	public double getAX(double s) {
		double a = 6 * (1 - s) * start.getX();
		double b = (-6 * (1 - s) + 6 * s - 6 * (1 - s)) * point1.getX();
		double c = (-6 * s + 6 * (1 - s) - 6 * s) * point2.getX();
		double d = 6 * s * end.getX();
		return a + b + c + d;
	}

	public double getAY(double s) {
		double a = 6 * (1 - s) * start.getY();
		double b = (-6 * (1 - s) + 6 * s - 6 * (1 - s)) * point1.getY();
		double c = (-6 * s + 6 * (1 - s) - 6 * s) * point2.getY();
		double d = 6 * s * end.getY();
		return a + b + c + d;
	}

	public double getK(double s) {
		double numerator = getVX(s) * getAY(s) - getVY(s) * getAX(s);
		double denominator = getVX(s) * getVX(s) + getVY(s) * getVY(s);
		return numerator / Math.pow(denominator, 1.5);
	}

}

class Line {
	/*
	 * An equation of a line is like:
	 * 
	 * m*x + n = y m can be calculated by angle; m = tan(angle) And if you know
	 * a start point then you can find n.
	 * 
	 * tan(angle) * startPoint_X + n = startPoint_Y So b = startPoint_Y - (tan (
	 * angle) * startPoint_X )
	 */
	private double m;
	private double b;

	public Line(State state1) {
		m = Math.tan(Math.toRadians(state1.getDirection()));
		b = state1.getY() - m * state1.getX();
	}

	public double getM() {
		return m;
	}

	public double getB() {
		return b;
	}

	public double getY(double x) {
		return m * x + b;
	}
}
