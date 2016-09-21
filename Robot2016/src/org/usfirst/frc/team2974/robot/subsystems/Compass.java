package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.dataLogs.Message;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.sensors.BNO055;
import org.usfirst.frc.team2974.robot.sensors.BNO055.BNO055Exception;
import org.usfirst.frc.team2974.robot.sensors.BNO055.OperationMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Compass extends Subsystem {

	private final double PITCHOFFSET = 2.5;

	private double pitchOffset = 0;
	// private double yawOffset = 0;

	private final BNO055 compass = RobotMap.getCompass();

	public Compass() {
		initializeCompass();
	}

	public void dumpSmartDashboardValues() {
		SmartDashboard.putNumber("Yaw", getYaw());
		SmartDashboard.putNumber("Pitch", getPitch());
		SmartDashboard.putNumber("Roll", getRoll());
	}

	/**
	 * down positive up negative
	 *
	 * @return pitch
	 */
	public double getPitch() // Vertical tilt
	{
		try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[1] - pitchOffset;
		} catch (final BNO055Exception e) {
			// TODO Auto-generated catch block
			Message.addError("Did not managed to read the pitch value from the compass", this);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * i dont care
	 *
	 * @return roll
	 */
	public double getRoll() // Spin in Z axis - rolling over
	{
		try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[2];
		} catch (final BNO055Exception e) {
			// TODO Auto-generated catch block
			Message.addError("Did not managed to read the roll value from the compass", this);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * left increase right decrease
	 *
	 * @return yaw (turn)
	 */
	public double getYaw() // Horizonal tilt
	{
		try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[0];// -
																		// yawOffset;
		} catch (final BNO055Exception e) {

			// TODO Auto-generated catch block
			Message.addError("Did not managed to read the pitch value from the compass", this);
			e.printStackTrace();

		}
		return 0;
	}

	@Override
	public void initDefaultCommand() {
	}

	public void initializeCompass() {
		try {
			compass.initialize(OperationMode.OPERATION_MODE_IMUPLUS);

			// compass.setMode(OperationMode.OPERATION_MODE_IMUPLUS);
			Message.addAction("Initialized compass sucssefully in implus mode", this);

		} catch (final BNO055Exception e) {

			Message.addError("Could not inititalize compass", this);
			e.printStackTrace();
		}
	}

	public void zeroRobot() {
		// yawOffset = getYaw();
		pitchOffset = PITCHOFFSET;
	}
}