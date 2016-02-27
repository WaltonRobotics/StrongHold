package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveArm;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    private CANTalon arm = RobotMap.arm;
    private AnalogPotentiometer armPot = RobotMap.armPot;
    public Arm()
    {
//    	arm.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
//    	arm.changeControlMode(CANTalon.TalonControlMode.Position);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArm());
    }
//    public void execute(){
//    	if (armPot.get() >= max){
//    		arm.set(armPot.get() * conversionRate - ((max - armPot.get()) * conversionRate));
//    	}
//    	if (armPot.get() <= min){
//    		arm.set(armPot.get() * conversionRate + ((min - armPot.get()) * conversionRate));
//    	}
//    }
//    
    public void moveArm(double x)
    {
    	arm.set(x);
    }    
//    public void moveArmMax()
//    {
//    	arm.set(max*conversionRate);
//    }    
//    public void moveArmMin()
//    {
//    	arm.set(min*conversionRate);
//    }
//    public void moveArmMid()
//    {
//    	arm.set((max+min)/2*conversionRate);
//    }    
    public double getPotValue()
    {
    	return armPot.get();
    }
//    public void set(double potValue)
//    {
//    	
//    }
}

