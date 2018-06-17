package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.sensors.BNO055;
import org.usfirst.frc.team2974.robot.sensors.BNO055.BNO055Exception;
import org.usfirst.frc.team2974.robot.sensors.BNO055.OperationMode;

/**
 *
 */
public class Compass extends Subsystem {

	BNO055 compass = RobotMap.compass;

	public Compass() {
		initializeCompass();

	}

	public void initDefaultCommand() {
	}

	public void initializeCompass() {
		try {
			compass.initialize(OperationMode.OPERATION_MODE_IMUPLUS);
			//compass.setMode(OperationMode.OPERATION_MODE_IMUPLUS);
		} catch (BNO055Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * left increase right decrease
	 *
	 * @return yaw (turn)
	 */
	public double getYaw() {
		try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[0];
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * down positive up negative
	 *
	 * @return pitch
	 */
	public double getPitch() {
		try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[1];
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * i dont care
	 *
	 * @return roll
	 */
	public double getRoll() {
		try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[2];
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	public void dumpSmartDashboardValues() {
		SmartDashboard.putNumber("Yaw", getYaw());
		SmartDashboard.putNumber("Pitch", getPitch());
		SmartDashboard.putNumber("Roll", getRoll());

	}
}

