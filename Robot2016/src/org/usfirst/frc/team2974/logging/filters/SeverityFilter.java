package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

public class SeverityFilter extends LogFilter {
private ArrayList<Severity> severityList = new ArrayList<Severity>();
	public void Passthrough (Severity pass){//Two methods that choose what do and don't go through the filter
		if (!severityList.contains(pass)){
			severityList.add(pass);
			}
	}
	public void stopPassthrough (Severity pass){
		if (severityList.contains(pass)){
		severityList.remove(pass);
		}
	}
public boolean logCall(LogMessage message){
		if (severityList.contains(message.getSeverity())) {
			logPostFilter(message);//Call logPostFilter, which pushes the message down to the next level in the pipe
			return true;
		}
		return false;
	}
}