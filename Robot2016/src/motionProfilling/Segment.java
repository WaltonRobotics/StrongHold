package motionProfilling;

public class Segment {
	private Trajectory trajectory;


	public Segment(State start, State end) {
		Spline spline = new Spline(start,end);
		TrajectorySpline ts = spline.getTrajectory();
		trajectory = new Trajectory(ts,start,end);
		
	}
	public Segment(State start, State end, Coordinate point1, Coordinate point2) {
		Spline spline = new Spline(start,end, point1, point2);
		TrajectorySpline ts = spline.getTrajectory();
		trajectory = new Trajectory(ts,start,end);
		
	}
	public Trajectory getTrajectory()
	{
		return trajectory;
	}
}

