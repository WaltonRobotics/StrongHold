package org.usfirst.frc.team2974.robot;

public class AutonLocator {
	
	AutonPossibleLocation location;
	
	public AutonLocator(AutonPossibleLocation location){
		this.location = location;
	}
	
	public AutonPossibleLocation getAutonPossibleLocation(){
		return location;
	}
}
