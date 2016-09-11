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

    private final BNO055 compass = RobotMap.compass;

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
            double PITCHOFFSET = 2.5;
            return compass.getVector(BNO055.VectorType.VECTOR_EULER)[1] - PITCHOFFSET;
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
    private double getRoll() // Spin in Z axis - rolling over
    {
        try {
            return compass.getVector(BNO055.VectorType.VECTOR_EULER)[2];
        } catch (BNO055Exception e) {
            // TODO Auto-generated catch block
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
            double yawOffset = 0;
            return compass.getVector(BNO055.VectorType.VECTOR_EULER)[0] - yawOffset;
        } catch (BNO055Exception e) {
            // TODO Auto-generated catch block
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
        } catch (BNO055Exception e) {
            e.printStackTrace();
        }
    }
}
