package org.usfirst.frc.team2974.robot;

public class RobotPoint {
	double x;
	double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public RobotPoint(double x, double y){
		this.x = x;
		this.y = y;
	}
}
