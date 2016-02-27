package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Camera extends Subsystem {
	NetworkTable table;
	double[] defaultValue;

	public void initDefaultCommand() {
		// setDefaultCommand(new MySpecialCommand());
	}

	public Camera() {
		
		defaultValue = new double[]{-1,-1};
	}

	public double getX() {
		try {
			table = NetworkTable.getTable("GRIP/report");
			double[] centerX = table.getNumberArray("centerX", defaultValue);
			return centerX[0];
		} catch (Exception e) {
		}
		return -1;

	}

	public double getY() {
		try {
			table = NetworkTable.getTable("GRIP/report");
			double[] centerY = table.getNumberArray("centerY", defaultValue);
			return centerY[0];
		} catch (Exception e) {
		}
		return -1;
	}

	public void dumpSmartDshboardValues() {
		SmartDashboard.putNumber("CenterX", getX());
		SmartDashboard.putNumber("CenterY", getY());
	}
}
