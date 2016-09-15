package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.sensors.BNO055;
import org.usfirst.frc.team2974.robot.sensors.BNO055.BNO055Exception;
import org.usfirst.frc.team2974.robot.sensors.BNO055.OperationMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Compass extends Subsystem {
	
	private final double PITCHOFFSET = 2.5;
	private double pitchOffset = 0;
	private double yawOffset = 0;
	
    BNO055 compass = RobotMap.compass;

    public void initDefaultCommand() {}
    
    public Compass()
    {
    	initializeCompass();

    }
    
    public void initializeCompass()
    {
    	try {
			compass.initialize(OperationMode.OPERATION_MODE_IMUPLUS);
			//compass.setMode(OperationMode.OPERATION_MODE_IMUPLUS);
		} catch (BNO055Exception e) {
			e.printStackTrace();
		}
    	Robot.compass.zeroRobot();
    }
    public void zeroRobot(){
    	yawOffset = getYaw();
    	pitchOffset = PITCHOFFSET;
    }
    /**
     * left increase
     * right decrease
     * @return yaw (turn)
     */
    public double getYaw() //Horizonal tilt
    {
    	try {
			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[0] - yawOffset;
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
    /**
     * down positive
     * up negative
     * @return pitch
     */
    public double getPitch() //Vertical tilt
    {
       	try {
    			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[1] - pitchOffset;
    		} catch (BNO055Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return 0;
    }
    /**
     * i dont care
     * @return roll
     */
    public double getRoll() //Spin in Z axis - rolling over
    {
       	try {
    			return compass.getVector(BNO055.VectorType.VECTOR_EULER)[2];
    		} catch (BNO055Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return 0;
    }

    
    public void dumpSmartDashboardValues(){
    	SmartDashboard.putNumber("Yaw", getYaw());
    	SmartDashboard.putNumber("Pitch", getPitch());
    	SmartDashboard.putNumber("Roll", getRoll());
    	
    }
}

