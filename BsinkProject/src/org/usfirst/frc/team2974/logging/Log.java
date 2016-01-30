package org.usfirst.frc.team2974.logging;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Matt
 *
 */
public class Log {
	private static Log mLog;
	private ArrayList<LogSink> logSinks;
 public void log(String message){
	 //iterate over Arraylist then call LogCall
 }
 public static Log instance(){
	return mLog;
 }
 public void attach(LogSink logSink){//Push LogSink to the ArrayList of LogSinks
	 logSinks.add(logSink);
 }
 public void detach(LogSink logSink){//Remove LongSink from the ArrayList of LogSinks
	 logSinks.remove(logSink);
 }

}