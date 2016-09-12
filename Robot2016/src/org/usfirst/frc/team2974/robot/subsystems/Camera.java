package org.usfirst.frc.team2974.robot.subsystems;

import java.util.Arrays;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.commands.ControlAim;
import org.usfirst.frc.team2974.robot.commands.ControlAim.aimState;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc.team2974.robot.Robot;
//import org.usfirst.frc.team2974.robot.commands.ControlAim;
//import org.usfirst.frc.team2974.robot.commands.ControlAim.aimState;

/**
 *
 */
public class Camera extends Subsystem {

	private final double[] defaultValue;
	double centerX;
	private NetworkTable table;
	// double centerY;

	public Camera() {
		defaultValue = new double[] { -1 };
		setNetTable();
	}

	public void dumpSmartDshboardValues() {
		SmartDashboard.putNumber("CenterXLeft", getXLeft());
		SmartDashboard.putNumber("CenterXRight", getXRight());
		// SmartDashboard.putNumber("CenterY", getY());
	}

	// private int setMaximums() {
	// NetworkTable table = getNetTable();
	// int maxIndex = -1;
	// if (table != null) {
	// double[] area = table.getNumberArray("area", defaultValue);
	// try {
	// double maxArea = 0;
	// for (int i = 0; i < area.length; i++) {
	// if (area[i] > maxArea) {
	// maxArea = area[i];
	// maxIndex = i;
	// }
	// if (maxIndex != -1) {
	// double[] X = getNetTable().getNumberArray("centerX", defaultValue);
	// centerX = X[maxIndex];
	// double[] Y = getNetTable().getNumberArray("centerY", defaultValue);
	// centerY = Y[maxIndex];
	// } else {
	// centerX = -1;
	// centerY = -1;
	// }
	//
	// }
	// } catch (Exception e) {
	// centerX = -1;
	// centerY = -1;
	// }
	// }
	//
	// return maxIndex;
	// }

	private NetworkTable getNetTable() {

		return table;

	}

	// public double getX() {
	// setMaximums();// TODO make this better
	// return centerX;
	// }
	//
	public double getXLeft() {
		setNetTable();
		double left = -1;
		try {
			double[] X = getNetTable().getNumberArray("centerX", defaultValue);
			Arrays.sort(X);
			left = X[0];
		} catch (Exception ignored) {

		}
		return left;

	}

	public double getXRight() {
		setNetTable();
		double right = -1;
		try {
			double[] X = getNetTable().getNumberArray("centerX", defaultValue);
			if (X.length > 1) {
				Arrays.sort(X);
				right = X[1];

			} else
				right = X[0];
		} catch (Exception ignored) {

		}
		return right;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ControlAim());
	}

	// public double getY() {
	// setMaximums();
	// return centerY;
	// }

	public void setNetTable() {
		if (Robot.getAimingstate() == aimState.cpu) {
			try {
				table = NetworkTable.getTable("GRIP/report");
			} catch (Exception e) {
				table = null;
			}
		} else {
			try {
				table = NetworkTable.getTable("GRIP/reportOnboard");
			} catch (Exception e) {
				table = null;
			}
		}
	}
}
