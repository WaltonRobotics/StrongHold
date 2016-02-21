package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Camera extends Subsystem {
    NetworkTable table;
    double[] defaultValue;

    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    public Camera()
    {
    	table = NetworkTable.getTable("GRIP/myCountoursReport");
    	defaultValue = new double [0];
    }
    
    public double getX()
    {
    	double[] centerX = table.getNumberArray("centerX", defaultValue);
    	return centerX[0];

    }
    public double getY()
    {
    	double[] centerY = table.getNumberArray("centerY", defaultValue);
    	return centerY[0];
    }
}

