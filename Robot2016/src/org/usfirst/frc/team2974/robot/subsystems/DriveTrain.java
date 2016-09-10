package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import motionProfilling.MotionControl;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private Talon right1 = RobotMap.driveTrainRight1;
	private Talon left1 = RobotMap.driveTrainLeft1;
	private Talon right2 = RobotMap.driveTrainRight2;
	private Talon left2 = RobotMap.driveTrainLeft2;
	
	private Encoder encoderLeft = RobotMap.encoderLeft;
	private Encoder encoderRight = RobotMap.encoderRight;
	
	Solenoid shifter = RobotMap.pnuematicsShifter;
	
	public PIDControllerAccel leftController;
	public PIDControllerAccel rightController;
	
	public final double distancePerPulse  = .000515417;
	private final double kV = 0.5; //Max speed is 2
	private class SharedDrive implements PIDOutput
	{
		SpeedController one;
		SpeedController two;
		
		Boolean isLeft;
		
		public SharedDrive(SpeedController one,SpeedController two, boolean isLeft)
		{
			this.isLeft = isLeft;
			this.one = one;
			this.two = two;
		}
		@Override
		public void pidWrite(double out)
		{
			if(!isLeft)
				out*=-1;
			
			one.set(out);
			two.set(out);
		}
	}
	
	public DriveTrain()
	{
		//128 pluses per revolution
		//3x revolution for each turn of big motor
		//diameter = 8.25 in= .21 m 
		//18 drive shaft
		//60 wheel
		//.21*pi/(128*3*60/18) = distance per pulse		
		resetEncoders();
		
		encoderLeft.setDistancePerPulse(distancePerPulse);
		encoderRight.setDistancePerPulse(distancePerPulse);
		
		encoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
		encoderRight.setPIDSourceType(PIDSourceType.kDisplacement);
		
		leftController = new PIDControllerAccel(1, 0, 0,1, RobotMap.encoderLeft,  
				new SharedDrive(left1,left2,true), kV, 0);
		leftController.disable();
		rightController = new PIDControllerAccel(1, 0, 0,1, RobotMap.encoderRight,  
				new SharedDrive( right1, right2,false), kV, 0);
		rightController.disable();
		
	}
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    public void setSpeeds(double left, double right)
    {
    	this.right1.set(-right);
    	this.right2.set(-right);
    	this.left1.set(left);
    	this.left2.set(left);
    	SmartDashboard.putNumber("left", left);
    	SmartDashboard.putNumber("right", -right);
    }
    public void resetEncoders()
    {
    	encoderLeft.reset();
    	encoderRight.reset();
    }
//    public void setSetPoint(MotionControl mc, double time)
//    {
    	//fix acceleration
//    	leftController.setSetpoint(mc.distanceleft(time),mc.velocityLeft(time),0);
//    	rightController.setSetpoint(mc.distanceRight(time),mc.velocityRight(time),0);
//    }
	public void setSetPoint(double dist, double velocity) {
		// fix acceleration
		leftController.setSetpoint(dist, velocity, 0);
		rightController.setSetpoint(dist, velocity, 0);
	}
	
	public void disableMotionController(){
		leftController.disable();
		rightController.disable();
	}
	
	public void enableMotionController(){
		leftController.enable();
		rightController.enable();
	}
    
    public void shiftUp()
    {
    	if(!shifter.get())
    	{
    		shifter.set(true);
    	}
    }
    public void shiftDown()
    {
    	if(shifter.get())
    	{
    		shifter.set(false);
    	}
    }
    public void dumpSmartdashboardValues()
    {
    	SmartDashboard.putNumber("EncoderLeft", encoderLeft.getDistance());
		SmartDashboard.putNumber("EncoderRight", encoderRight.getDistance());
		
		SmartDashboard.putNumber("Velocity Left", encoderLeft.getRate());
		SmartDashboard.putNumber("Velocity Right", encoderRight.getRate());
    }
    public void initSmartdashBoardValues()
    {
        SmartDashboard.putNumber("kV", 1.0/1.5);
    	SmartDashboard.putNumber("kA", 0);
    	SmartDashboard.putNumber("P", 0);
    	SmartDashboard.putNumber("I", 0);
    	SmartDashboard.putNumber("D", 0);
    }

	
}

