package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.LogSink;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

public abstract class LogFilter implements LogSink{
	private ArrayList<LogSink> logSinks = new ArrayList<LogSink>();
public void attach(LogSink logSink){//Push LogSink to the ArrayList of LogSinks
	logSinks.add(logSink);
}
public void detach(LogSink logSink){//Remove LongSink from the ArrayList of LogSinks
	 logSinks.remove(logSink);
}
public void LogPostFilter(LogMessage message){
	//Iterate over logSinks and call LogCall in each object
	for(LogSink logSinkIterator : logSinks){
		logSinkIterator.LogCall(message);
	}
}
}
