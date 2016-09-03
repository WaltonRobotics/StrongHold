package org.usfirst.frc.team2974.robot.subsystems;

import java.awt.Point;

import org.usfirst.frc.team2974.robot.AutonPossibleLocation;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PositionSubsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
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
	

}
