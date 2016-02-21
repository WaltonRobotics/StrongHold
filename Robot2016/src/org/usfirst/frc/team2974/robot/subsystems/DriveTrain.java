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
import motionProfilling.MotionControl;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private Talon right = RobotMap.driveTrainRight;
	private Talon left = RobotMap.driveTrainLeft;
	
	private Encoder encoderLeft = RobotMap.encoderLeft;
	private Encoder encoderRight = RobotMap.encoderRight;
	
	Solenoid shifter = RobotMap.pnuematicsShifter;
	
	public PIDControllerAccel leftController;
	public PIDControllerAccel rightController;
	
	public final double distancePerPulse  = .000515417;
	
	private class SharedDrive implements PIDOutput
	{
		SpeedController one;
		
		Boolean isLeft;
		
		public SharedDrive(SpeedController one, boolean isLeft)
		{
			this.isLeft = isLeft;
			this.one = one;
		}
		@Override
		public void pidWrite(double out)
		{
			if(!isLeft)
				out*=-1;
			
			one.set(out);
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
		
		leftController = new PIDControllerAccel(1, 0, 0,1, RobotMap.encoderLeft,  new SharedDrive(left,true),1,0);
		rightController = new PIDControllerAccel(1, 0, 0,1, RobotMap.encoderRight,  new SharedDrive( right,false),1,0);
	}
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    public void setSpeeds(double left, double right)
    {
    	this.right.set(right);
    	this.left.set(-left);
    }
    public void resetEncoders()
    {
    	encoderLeft.reset();
    	encoderRight.reset();
    }
    public void setSetPoint(MotionControl mc, double time)
    {
    	//fix acceleration
    	leftController.setSetpoint(mc.distanceleft(time),mc.velocityLeft(time),0);
    	rightController.setSetpoint(mc.distanceRight(time),mc.velocityRight(time),0);
    }
    
    public void shiftDown()
    {
    	if(shifter.get())
    	{
    		shifter.set(false);
    	}
    }
    public void shiftUp()
    {
    	if(!shifter.get())
    	{
    		shifter.set(true);
    	}
    }
    
}

