package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Camera extends Subsystem {

	double[] defaultValue;
	NetworkTable table;

	// final int Max_Area = 2700;
	public void initDefaultCommand() {
	}

	public Camera() {
		defaultValue = new double[] { -1 };
		setNetTable();
	}

	private int getIndexofMaxAreaContour() {
		NetworkTable table = getNetTable();
		int maxIndex = -1;
		if (table != null) {
			double[] area = table.getNumberArray("area", defaultValue);
			try {
				double maxArea = 0;
				for (int i = 0; i < area.length; i++) {
					if (area[i] > maxArea) {
						maxArea = area[i];
						maxIndex = i;
					}
				}
			} catch (Exception e) {
			}
		}

		return maxIndex;
	}

	private NetworkTable getNetTable() {

		return table;

	}

	public void setNetTable() {
		try {
			table = NetworkTable.getTable("GRIP/report");
		} catch (Exception e) {
			table = null;
		}
	}

	public double getX() {
		int index = getIndexofMaxAreaContour();
		if (index != -1) {
			double[] centerX = getNetTable().getNumberArray("centerX", defaultValue);
			return centerX[index];
		}

		return index;
	}

	public double getY() {
		int index = getIndexofMaxAreaContour();
		if (index != -1) {
			double[] centerY = getNetTable().getNumberArray("centerY", defaultValue);
			if(index>centerY.length)
				index = 0;
			return centerY[index];
		}

		return index;
	}

	public void dumpSmartDshboardValues() {
		SmartDashboard.putNumber("CenterX", getX());
		SmartDashboard.putNumber("CenterY", getY());
	}
}
