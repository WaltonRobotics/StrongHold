package motionProfilling;

public class Segment {
	private Trajectory trajectory;


	public Segment(State start, State end) {
		Spline spline = new Spline(start,end);
		TrajectorySpline ts = spline.getTrajectory();
		trajectory = new Trajectory(ts,start,end);
		
	}
	public Trajectory getTrajectory()
	{
		return trajectory;
	}
}

