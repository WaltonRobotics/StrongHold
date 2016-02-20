package org.usfirst.frc.team2974.diagnostics.regressions;

public class PointValue {
	double x;
	double y;

	public PointValue(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public PointValue() {

	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
