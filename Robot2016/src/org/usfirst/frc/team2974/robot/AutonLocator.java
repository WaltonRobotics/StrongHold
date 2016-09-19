package org.usfirst.frc.team2974.robot;

import java.awt.Point;

public class AutonLocator {

	private final AutonPossibleLocation location;

	public AutonLocator(final AutonPossibleLocation location) {
		this.location = location;
	}

	public double getAngle(final Point start, final Point finish) {
		return Math.atan((start.getY() - finish.getY()) / (start.getX() - finish.getX()));

	}

	public AutonPossibleLocation getAutonPossibleLocation() {
		return location;
	}

	public double getDistance(final Point start, final Point finish) {
		return Math.sqrt(Math.pow(start.getX() - finish.getX(), 2) + Math.pow(start.getY() - finish.getY(), 2));
	}

	public Point getLocation() {
		final Point p = new Point();
		switch (location) {
		case A:
			p.setLocation(0, 0);// replace all numbers with measured numbers
			break;
		case B:
			p.setLocation(1.3462, 0);
			break;
		case C:
			p.setLocation(2.6924, 0);
			break;
		case D:
			p.setLocation(4.0386, 0);
			break;
		case E:
			p.setLocation(5.3848, 0);
			break;
		default:
			p.setLocation(0, 0);
			break;
		}
		return p;
	}

	public Point getLocationGoal() {
		final Point p = new Point();
		switch (location) {
		case A:
			p.setLocation(3.3655, 4.2164);
			break;
		case B:
			p.setLocation(3.3655, 4.2164);
			break;
		case C:
			p.setLocation(2.2352, 4.2672);
			break;
		case D:
			p.setLocation(2.2352, 4.2672);
			break;
		case E:
			p.setLocation(2.2352, 4.2672);
			break;
		default:
			p.setLocation(0, 0);
			break;
		}
		return p;
	}

	public Point getLocationShoot() {
		final Point p = new Point();
		switch (location) {
		case A:
			p.setLocation(0.7747, 2.9972);// replace all numbers with measured
			// numbers
			break;
		case B:
			p.setLocation(0.7747, 2.9972);
			break;
		case C:
			p.setLocation(2.5654, 1.8542);
			break;
		case D:
			p.setLocation(2.5654, 1.8542);
			break;
		case E:
			p.setLocation(2.5654, 1.8542);
			break;
		default:
			p.setLocation(0, 0);
			break;
		}
		return p;
	}
}
