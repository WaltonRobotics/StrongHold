package motionProfilling;

import java.util.ArrayList;

//todo fix end condition
public class MotionControl {
	private ArrayList<State> states = new ArrayList<State>();
	public static final double Robot_Width = .63;
	public static final double Robot_Max_Speed = 1.5;
	public static final double Robot_Max_Acceleration = .5;

	public static final int numPoints = 20;
	private int trajIndex;
	private int stateIndex;

	public MotionControl(String s, double startVelocity, double endVelocity) {
		states = InputText.parseInput(s);// parse input

		// set initial and end conditions
		states.get(0).setVelocity(startVelocity);
		states.get(states.size() - 1).setVelocity(endVelocity);

		// calculate directions of each intermediate point
		calculateDirections();

		// create a segment for each state
		for (int i = 0; i < states.size() - 1; i++) {
			Spline spline = new Spline(states.get(i), states.get(i + 1));
			TrajectorySpline ts = spline.getTrajectory();
			states.get(i).setTrajectory(new Trajectory(ts, states.get(i), states.get(i + 1)));
		}

		// calculate total distances and times for each state
		calculateTotalTimesForStates();
		calculateDistanceLeft();
		calculateDistanceRight();

		// set total distances for each position
		for (int i = 0; i < states.size() - 1; i++) {
			states.get(i).getTrajectory().setTotals();
			states.get(i).getTrajectory().dumpValues();
		}

		// set the starting condition at the start
		stateIndex = 0;
		trajIndex = 0;
		// createGraphs();

	}

	public double getMaxTime() {
		Trajectory traj = states.get(states.size() - 2).getTrajectory();
		return traj.get(traj.size() - 1).totalTime;
	}

	private void calculateDistanceLeft() {
		double dl = 0;
		states.get(0).setDistanceLeft(0);
		for (int i = 1; i < states.size(); i++) {
			dl += states.get(i - 1).getTrajectory().getTotalDistanceLeft();
			states.get(i).setDistanceLeft(dl);
		}
	}

	private void calculateDistanceRight() {
		double dr = 0;
		states.get(0).setDistanceRight(0);
		for (int i = 1; i < states.size(); i++) {
			dr += states.get(i - 1).getTrajectory().getTotalDistanceRight();
			states.get(i).setDistanceRight(dr);
		}
	}

	private void calculateTotalTimesForStates() {
		double time = 0;
		states.get(0).setTime(0);
		for (int i = 1; i < states.size(); i++) {
			time += states.get(i - 1).getTrajectory().getTotalTime();
			states.get(i).setTime(time);

		}
	}

	public Position getPosition(double time) {
		if (stateIndex > states.size() - 2) {
			return null;
		}
		Trajectory traj = states.get(stateIndex).getTrajectory();

		while (traj.get(trajIndex).totalTime + traj.get(trajIndex).deltaTime < time) {
			trajIndex++;
			if (trajIndex > traj.size() - 1) {
				stateIndex++;
				traj = states.get(stateIndex).getTrajectory();
				trajIndex = 0;
			}
			if (stateIndex > states.size() - 2) {
				return null;
			}
		}
		return traj.get(trajIndex);
	}

	public double distanceleft(double time) {
		Position p = getPosition(time);
		double distanceLeft = time - p.totalTime;
		distanceLeft = distanceLeft / p.deltaTime;
		distanceLeft *= p.getDeltaLengthLeft();
		distanceLeft += p.totalDistanceLeft;
		return distanceLeft;
	}

	public double distanceRight(double time) {
		Position p = getPosition(time);
		double distanceRight = time - p.totalTime;
		distanceRight /= p.deltaTime;
		distanceRight *= p.getDeltaLengthRight();
		distanceRight += p.totalDistanceRight;
		return distanceRight;
	}

	public double velocityLeft(double time) {
		Position p = getPosition(time);
		double vl = time - p.totalTime;
		vl /= p.deltaTime;
		vl *= p.deltaVelocityLeft;
		vl += p.getVelocityLeft();
		return vl;
	}

	public double velocityRight(double time) {
		Position p = getPosition(time);
		double vr = time - p.totalTime;
		vr /= p.deltaTime;
		vr *= p.deltaVelocityRight;
		vr += p.getVelocityRight();
		return vr;
	}

	private void calculateDirections() {
		for (int i = 1; i < states.size() - 1; i++) {
			double direction1 = states.get(i - 1).getDirection();
			double direction2 = Math.toDegrees(calculateTheta(states.get(i), states.get(i + 1)));

			// TFS: I'm not sure what you are doing here, but it's never a good
			// idea to do == on a floating point number, as rounding errors make
			// it unlikely the value will be exactly zero.

			// PGK: This is because 0 and 360 are the same direction
			// I made it so that if the getDirection() is 360, it would return 0
			// but if the other angle is on the farther side of the unit circle,
			// the 360 is
			// more useful for calculation purposes
			// this value is usually set by user, so it has a high likelihood of
			// being zero

			if (direction1 == 0 || direction2 == 0)
				if (direction2 >= 180)
					direction1 = 360;
				else
					direction2 = 360;

			double computedDirection = (direction1 - direction2) / 2;
			states.get(i).setDirection(computedDirection + direction2);
		}

	}

	private double calculateTheta(State state1, State state2) {
		double x1 = state1.getX();
		double y1 = state1.getY();
		double x2 = state2.getX();
		double y2 = state2.getY();

		return Math.atan2((y2 - y1), (x2 - x1));

	}

}
