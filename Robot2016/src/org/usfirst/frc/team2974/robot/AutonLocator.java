package org.usfirst.frc.team2974.robot;


public class AutonLocator {
	
	AutonPossibleLocation location;
	double positionX;
	double positionY;
	
	public AutonLocator(AutonPossibleLocation location){
		this.location = location;
	}
	
	public AutonPossibleLocation getAutonPossibleLocation(){
		return location;
	}
	public double getAngle (RobotPoint start, RobotPoint finish){
		
		positionY = (start.getY() - finish.getY());
		positionX = (start.getX() - finish.getX());
		return Math.atan(positionY/positionX);
	}
	
	public double getDistance (RobotPoint start, RobotPoint finish){
		positionY = (start.getY() - finish.getY());
		positionX = (start.getX() - finish.getX());
		return Math.sqrt(Math.pow(positionX, 2) + Math.pow(positionY, 2));
	}
	
	
	public RobotPoint getLocation(){
		RobotPoint p;
		switch(location){
		case A:
			p= new RobotPoint(0, 0);//replace all numbers with measured numbers
			break;
		case B:
			p= new RobotPoint(1.3462, 0);
			break;
		case C:
			p= new RobotPoint(2.6924, 0);
			break;
		case D:
			p= new RobotPoint(4.0386, 0);
			break;
		case E:
			p= new RobotPoint(5.3848, 0);
			break;
		default:
			p= new RobotPoint(0, 0);
			break;
		}
		return p;
	}
	
	public RobotPoint getLocationGoal(){
		RobotPoint p;
		switch(location){
		case A:
			p= new RobotPoint(3.3655, 4.2164);
			break;
		case B:
			p= new RobotPoint(3.3655, 4.2164);
			break;
		case C:
			p= new RobotPoint(2.2352, 4.2672);
			break;
		case D:
			p= new RobotPoint(2.2352, 4.2672);
			break;
		case E:
			p= new RobotPoint(2.2352, 4.2672);
			break;
		default:
			p= new RobotPoint(0, 0);
			break;
		}
		return p;
	}
	
	public RobotPoint getLocationShoot(){
		RobotPoint p;
		switch(location){
		case A:
			p = new RobotPoint(0.7747, 2.9972);//replace all numbers with measured numbers
			break;
		case B:
			p= new RobotPoint(0.7747, 2.9972);
			break;
		case C:
			p= new RobotPoint(2.5654, 1.8542);
			break;
		case D:
			p= new RobotPoint(2.5654, 1.8542);
			break;
		case E:
			p= new RobotPoint(2.5654, 1.8542);
			break;
		default:
			p= new RobotPoint(0, 0);
			break;
		}
		return p;
	}
}
