package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import motionProfilling.MotionControl;
import motionProfilling.Position;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private Talon frontRight = RobotMap.driveTrainFrontRight;
	private Talon backRight = RobotMap.driveTrainBackRight;
	private Talon frontLeft = RobotMap.driveTrainFrontLeft;
	private Talon backLeft = RobotMap.driveTrainBackLeft;
	private Encoder encoderLeft = RobotMap.encoderLeft;
	private Encoder encoderRight = RobotMap.encoderRight;
	private MotionControl motionControl;
	private class SharedDrive implements PIDOutput
	{
		SpeedController one;
		SpeedController two;
		public SharedDrive(SpeedController one, SpeedController two)
		{
			this.one = one;
			this.two = two;
		}
		public void pidWrite(double out)
		{
			one.set(out);
			two.set(out);
		}
	}
	public PIDControllerAccel leftController = new PIDControllerAccel(1, 0, 0,1, RobotMap.encoderLeft, (PIDOutput) new SharedDrive(backLeft, frontLeft),1,0,true);
	public PIDControllerAccel rightController = new PIDControllerAccel(1, 0, 0,1, RobotMap.encoderRight, (PIDOutput) new SharedDrive(backRight, frontRight),1,0,false);

	public DriveTrain()
	{
		//128 pluses per revolution
		//3x revolution for each turn of big motor
		//diameter = 8.25 in= .21 m 
		//18 drive shaft
		//60 wheel
		//.21*pi/(128*3*60/18) = distance per pulse
		RobotMap.encoderLeft.setDistancePerPulse(.000515417);
		RobotMap.encoderRight.setDistancePerPulse(.000515417);
		RobotMap.encoderLeft.setPIDSourceType(PIDSourceType.kRate);
		RobotMap.encoderRight.setPIDSourceType(PIDSourceType.kRate);

	}
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    public void setSpeeds(double left, double right)
    {
    	backRight.set(-right);
    	backLeft.set(left);
    	frontRight.set(-right);
    	frontLeft.set(left);
    }
    public void resetEncoders()
    {
    	RobotMap.encoderLeft.reset();
    	RobotMap.encoderLeft.reset();
    }
    public void setSetPoint(Position pos)
    {
    	leftController.setSetpoint(pos);
    	rightController.setSetpoint(pos);
    }
    
}

