package org.usfirst.frc.team2974.logging;

import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.messages.LogMessage;

/**
 * 
 */

/**
 * @author Matt
 *
 */
public class Log {
	private static Log mLog = null;
	private ArrayList<LogSink> logSinks;

	public void logCall(LogMessage message) {
		for(LogSink logSinkIterator : logSinks){
			logSinkIterator.LogCall(message);
		}
	}

	public static Log instance() {
		if (mLog == null) {
			mLog = new Log();
		}
		return mLog;
	}

	public void attach(LogSink logSink) {// Push LogSink to the ArrayList of											// LogSinks
		logSinks.add(logSink);
	}

	public void detach(LogSink logSink) {// Remove LongSink from the ArrayList											// of LogSinks
		logSinks.remove(logSink);
	}

}