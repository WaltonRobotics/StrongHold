package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team2974.robot.sensors.BNO055;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static DigitalInput digital0;
    public static DigitalInput digital1;
    public static DigitalInput digital2;
    public static DigitalInput digital3;
    public static DigitalInput digital4;
    public static DigitalInput digital5;
    public static DigitalInput digital6;
    public static DigitalInput digital7;
    public static DigitalInput digital8;
    public static DigitalInput digital9;
    
    public static AnalogInput analog0;
    public static AnalogInput analog1;
    public static AnalogInput analog2;
    public static AnalogInput analog3;

    //drive train
	public static Talon driveTrainRight1;
	public static Talon driveTrainRight2;
    public static Talon driveTrainLeft1;
    public static Talon driveTrainLeft2;
    
    public static Encoder encoderLeft;
    public static Encoder encoderRight;
    
    public static Solenoid pnuematicsShifter;
    
    //intake
    public static Talon intakeMotor;
	public static Solenoid flapper;
	
	//shooter
    //public static Encoder encoderShooter;
	public static Solenoid latch;
	public static CANTalon tensioner;
	
	//arm
    public static CANTalon arm;
    public static AnalogPotentiometer armPot;
	
    //compass
    public static BNO055 compass = new BNO055(BNO055.BNO055_ADDRESS_A);
    
    public static DigitalInput forwardLimit;
    public static DigitalInput backwardLimit;
    public static DigitalInput shooterLimit;
    
    
    public static void init() {     
        //inputs
        digital0 = new DigitalInput(0);
        digital1 = new DigitalInput(1);
        digital2 = new DigitalInput(2);
        digital3 = new DigitalInput(3);
        digital4 = new DigitalInput(4);
        digital5 = new DigitalInput(5);
        digital6 = new DigitalInput(6);
        digital7 = new DigitalInput(7);
        digital8 = new DigitalInput(8);
        digital9 = new DigitalInput(9);
        
        analog0 = new AnalogInput(0);
        analog1 = new AnalogInput(1);
        analog2 = new AnalogInput(2);
        analog3 = new AnalogInput(3);
        
        forwardLimit = digital2;//forward is tensioned
        backwardLimit = digital1;
        shooterLimit = digital0;
        
    	//drive train
        encoderRight = new Encoder(digital6,digital7);
        encoderLeft = new Encoder(digital9,digital8);
        
    	driveTrainRight1 = new Talon(2);
    	driveTrainRight2 = new Talon(3);
    	
    	driveTrainLeft1 = new Talon(0);
        driveTrainLeft2 = new Talon(1);
        //pcm = 1
        
        //arm
        arm = new CANTalon(2);
        armPot = new AnalogPotentiometer(analog0,1000,-750);
        
        //shifter
        pnuematicsShifter = new Solenoid(0);


        //shooter
        //encoderShooter = new Encoder(digital7,digital8);
        latch = new Solenoid(2);
        tensioner = new CANTalon(1);
        
        //intake
        intakeMotor = new Talon(4);
        flapper = new Solenoid(1);
        
    }
}
