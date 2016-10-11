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
			p= new RobotPoint(0, .662);//replace all numbers with measured numbers
			break;
		case B:
			p= new RobotPoint(1.3462, 1.778);
			break;
		case C:
			p= new RobotPoint(2.6924, .2);
			break;
		case D:
			p= new RobotPoint(4.0386, .2);
			break;
		case E:
			p= new RobotPoint(5.3848, .2);
			break;
		default:
			p= new RobotPoint(0, 1);
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
	
	public RobotPoint getLocationDistance(){
		RobotPoint p;
		switch(location){
		case A:
			p = new RobotPoint(.5, 1.2);
			break;
		case B:
			p= new RobotPoint(.7, 1.7);
			break;
		case C:
			p= new RobotPoint(.5, .1);
			break;
		case D:
			p= new RobotPoint(.5, .2);
			break;
		case E:
			p= new RobotPoint(.5, .8);
			break;
		default:
			p= new RobotPoint(0, 0);
			break;
		}
		return p;
	}

	public double getCenter() {
		switch(location){
		case A:
			return 101;
		case B:
			return 95;
		case C:
			return 61;
		case D:
			return 95;
		case E:
			return 95;
		default:
			return 0;
		}
	}

	public RobotPoint getTurn() {
		RobotPoint p;
		switch(location){
		case A:
			p = new RobotPoint(.35, -.5);
			break;
		case B:
			p= new RobotPoint(.5, -.5);
			break;
		case C:
			p= new RobotPoint(.25, -.5);
			break;
		case D:
			p= new RobotPoint(.25, .5);
			break;
		case E:
			p= new RobotPoint(.35, .5);
			break;
		default:
			p= new RobotPoint(0, 0);
			break;
		}
		return p;
	}
}
