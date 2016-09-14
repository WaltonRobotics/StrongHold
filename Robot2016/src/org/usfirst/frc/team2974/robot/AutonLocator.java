package org.usfirst.frc.team2974.robot;

import java.awt.Point;

public class AutonLocator {
	
	AutonPossibleLocation location;
	
	public AutonLocator(AutonPossibleLocation location){
		this.location = location;
	}
	
	public AutonPossibleLocation getAutonPossibleLocation(){
		return location;
	}
	public double getAngle (Point start, Point finish){
		
		double positionY = (start.getY() - finish.getY());
		double positionX = (start.getX() - finish.getX());
		return Math.atan(positionY/positionX);

	}
	
	public double getDistance (Point start, Point finish){
		double positionY = (start.getY() - finish.getY());
		double positionX = (start.getX() - finish.getX());
		return Math.sqrt(Math.pow(positionX, 2) + Math.pow(positionY, 2));
	}
	
	
	public Point getLocation(){
		Point p = new Point();
		switch(location){
		case A:
			p.setLocation(0, 0);//replace all numbers with measured numbers
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
	
	public Point getLocationGoal(){
		Point p = new Point();
		switch(location){
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
	
	public Point getLocationShoot(){
		Point p = new Point();
		switch(location){
		case A:
			p.setLocation(0.7747, 2.9972);//replace all numbers with measured numbers
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
