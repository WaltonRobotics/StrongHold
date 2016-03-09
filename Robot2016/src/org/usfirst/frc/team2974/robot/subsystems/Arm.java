package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveArm;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {
    private CANTalon arm = RobotMap.arm;
    private AnalogPotentiometer armPot = RobotMap.armPot;
    private final double zeroPosition = 0;
    private PIDController pid = new PIDController(1, 0, 0, armPot, arm);
    private double absoluteTolerance = 5;
    public Arm()
    {
    	pid.enable();
    	pid.setAbsoluteTolerance(absoluteTolerance);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArm());
    }

    public void moveArmPower(double power)
    {
    	if(pid.isEnabled())
    		pid.disable();
    	arm.set(power);
    }    
        
    public void disablePID()
    {
    	pid.disable();
    }
    
    public void enablePID()
    {
    	pid.enable();
    }
    
    public void moveArmPosition(double position)
    {
    	if(!pid.isEnabled())
    		pid.enable();
    	position -=zeroPosition;
    	pid.setSetpoint(position);
    }

    public double getPotValue()
    {
    	return armPot.get();
    }
    public void dumpSmartDashboardValues()
    {
    	SmartDashboard.putNumber("Arm Potentiometer", armPot.get());
    }
}

