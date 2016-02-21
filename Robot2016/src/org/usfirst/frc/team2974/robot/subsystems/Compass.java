package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.sensors.BNO055;
import org.usfirst.frc.team2974.robot.sensors.BNO055.BNO055Exception;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Compass extends Subsystem {
    BNO055 compass = RobotMap.compass;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public Compass()
    {
    	try {
			compass.initialize();
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * left increase
     * right decrease
     * @return yaw (turn)
     */
    public double getYaw()
    {
    	try {
			return compass.getQuaternion()[0];
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
    /**
     * p.s. i dont care
     * down positive
     * up negative
     * @return pitch
     */
    public double getPitch()
    {
    	try {
			return compass.getQuaternion()[1];
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
    public double getroll()
    {
    	try {
			return compass.getQuaternion()[2];
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
    /**
     * 
     * @return idek
     */
    public double getHeading4()
    {
    	try {
			return compass.getQuaternion()[3];
		} catch (BNO055Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
    
}

