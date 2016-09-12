package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.ShowInputs;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shows Inputs
 */
public class Inputs extends Subsystem {

	private final DigitalInput digital0 = RobotMap.getDigital0();
	private final DigitalInput digital1 = RobotMap.getDigital1();
	private final DigitalInput digital2 = RobotMap.getDigital2();
	private final DigitalInput digital3 = RobotMap.getDigital3();
	private final DigitalInput digital4 = RobotMap.getDigital4();
	private final DigitalInput digital5 = RobotMap.getDigital5();
	private final DigitalInput digital6 = RobotMap.getDigital6();
	private final DigitalInput digital7 = RobotMap.getDigital7();
	private final DigitalInput digital8 = RobotMap.getDigital8();
	private final DigitalInput digital9 = RobotMap.getDigital9();

	private final AnalogInput analog0 = RobotMap.getAnalog0();
	private final AnalogInput analog1 = RobotMap.getAnalog1();
	private final AnalogInput analog2 = RobotMap.getAnalog2();
	private final AnalogInput analog3 = RobotMap.getAnalog3();

	Potentiometer armPot = RobotMap.getArmPot();
	CANTalon tensioner = RobotMap.getTensioner();

	Encoder encoderLeft = RobotMap.getEncoderLeft();
	Encoder encoderRight = RobotMap.getEncoderRight();

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShowInputs());
	}

	public void updateSmartDashboard() {
		SmartDashboard.putData("Digital In 0", digital0);
		SmartDashboard.putData("Digital In 1", digital1);
		SmartDashboard.putData("Digital In 2", digital2);
		SmartDashboard.putData("Digital In 3", digital3);
		SmartDashboard.putData("Digital In 4", digital4);
		SmartDashboard.putData("Digital In 5", digital5);
		SmartDashboard.putData("Digital In 6", digital6);
		SmartDashboard.putData("Digital In 7", digital7);
		SmartDashboard.putData("Digital In 8", digital8);
		SmartDashboard.putData("Digital In 9", digital9);

		SmartDashboard.putData("Analog In 0", analog0);
		SmartDashboard.putData("Analog In 1", analog1);
		SmartDashboard.putData("Analog In 2", analog2);
		SmartDashboard.putData("Analog In 3", analog3);

	}

}
