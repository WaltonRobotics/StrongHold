package org.usfirst.frc.team2974.logging;

import org.usfirst.frc.team2974.logging.messages.LogMessage;

public interface LogSink {
	public boolean LogCall(LogMessage message);
}