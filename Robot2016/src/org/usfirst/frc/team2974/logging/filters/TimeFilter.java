package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.LogMessage;
import org.usfirst.frc.team2974.logging.enumerations.Severity;

public class TimeFilter extends LogFilter {
private ArrayList<Severity> severityList;
	public void Passthrough (Severity pass){
		severityList.add(pass);
	}
public boolean logCall(LogMessage message){
		if (severityList.contains(message.getTime())) {
			logPostFilter(message);
			return true;
		}
		return false;
	}
}
