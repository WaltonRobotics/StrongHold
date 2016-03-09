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
	double centerX;
	double centerY;


	public void initDefaultCommand() {
	}

	public Camera() {
		defaultValue = new double[] { -1 };
		setNetTable();
	}

	private int setMaximums() {
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
					if(maxIndex!=-1)
					{
						double[] X = getNetTable().getNumberArray("centerX", defaultValue);
						centerX =X[maxIndex];
						double[] Y = getNetTable().getNumberArray("centerY",defaultValue);
						centerY = Y[maxIndex];
					}
					else
					{
						centerX = -1;
						centerY = -1;
					}
					
				}
			} catch (Exception e) {
				centerX = -1;
				centerY = -1;
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
		setMaximums();//TODO make this better
		return centerX;
	}

	public double getY() {
		setMaximums();
		return centerY;
	}

	public void dumpSmartDshboardValues() {
		SmartDashboard.putNumber("CenterX", getX());
		SmartDashboard.putNumber("CenterY", getY());
	}
}
