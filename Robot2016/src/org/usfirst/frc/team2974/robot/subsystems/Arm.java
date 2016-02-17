package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    private CANTalon arm = RobotMap.arm;

    public Arm()
    {
    	//arm.setControlMode();?
    }

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArm());
    }
    
    public void moveArm(double x)
    {
    	arm.set(x);
    }
}

