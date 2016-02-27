package org.usfirst.frc.team2974.logging;

import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.enumerations.SubSystem;

public class LogMessage {
	Severity severity;
	SubSystem subSystem;
	String command;
	String message;
	double time;
	int line;

	public LogMessage(Severity severity, SubSystem subSystem, String command, String message, int line) {
		this.severity = severity;
		this.subSystem = subSystem;
		this.command = command;
		this.message = message;
		this.line = line;
		// time = Timer.getFPGATimestamp();
		time = 0;
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public SubSystem getSubSystem() {
		return this.subSystem;
	}

	public String getCommand() {
		return this.command;
	}

	public String getMessage() {
		return this.message;
	}

	public Double getTime() {
		return time;
	}

	public int getLine() {
		return this.line;
	}
}