package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.MoveIntake;

/**
 *
 */
public class Intake extends Subsystem {

	Solenoid intakeExtender = RobotMap.intakeExtender;
	double time;
	IntakeExtenderState state;


	public Intake() {
		state = IntakeExtenderState.in;
		time = Timer.getFPGATimestamp();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MoveIntake());
	}

	public void extend() {
		intakeExtender.set(true);
//	    	if(getState() == IntakeExtenderState.in)
//	    	{
//	    		intakeExtender.set(true);
//		    	state = IntakeExtenderState.out;
//		    	time = Timer.getFPGATimestamp();
//	    	}

	}

	public void retract() {
		intakeExtender.set(false);
//	    	if(getState() == IntakeExtenderState.out)
//	    	{
//		    	intakeExtender.set(false);
//		    	state = IntakeExtenderState.in;
//		    	time = Timer.getFPGATimestamp();
//	    	}

	}

	public IntakeExtenderState getState() {
		if (Timer.getFPGATimestamp() - time > .1) {
			return state;
		} else {
			return IntakeExtenderState.middle;
		}
	}

	public enum IntakeExtenderState {
		in, out, middle
	}

}