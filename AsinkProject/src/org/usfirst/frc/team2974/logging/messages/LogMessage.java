package org.usfirst.frc.team2974.logging.messages;

import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;

import edu.wpi.first.wpilibj.Timer;

public class LogMessage {
	Severity severity;
	SubSystem subSystem;
	String command;
	String message;
	double time;
	
	public LogMessage(Severity severity, SubSystem subSystem, String command, String message){
		this.severity = severity;
		this.subSystem = subSystem;
		this.command = command;
		this.message = message;
		time = Timer.getFPGATimestamp();
	}
	public Severity getSeverity(){
		return this.severity;
	}
	public SubSystem getSubSystem(){
		return this.subSystem;
	}
	public String getCommand(){
		return this.command;
	}
	public String getMessage(){
		return this.message;
	}
	public Double getTime(){
		return time;
	}
}