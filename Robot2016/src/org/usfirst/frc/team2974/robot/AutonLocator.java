package org.usfirst.frc.team2974.robot;

public class AutonLocator {
	
	AutonPossibleLocation location;
	
	public AutonLocator(AutonPossibleLocation location){
		this.location = location;
	}
	
	public AutonPossibleLocation getAutonPossibleLocation(){
		return location;
	}
	
	public double getXposition(){
		switch(location){
		//There are no break statements because the code will never pass the return statement.
		case A:
			return 0;//replace all numbers with measured numbers
		case B:
			return 0;
		case C:
			return 0;
		case D:
			return 0;
		case E:
			return 0;
		default:
			return 0;
		}
	}
	
	public double getYposition(){
		switch(location){
		//There are no break statements because the code will never pass the return statement.
		case A:
			return 0;
		case B:
			return 0;
		case C:
			return 0;
		case D:
			return 0;
		case E:
			return 0;
		default:
			return 0;
		}
	}
}
