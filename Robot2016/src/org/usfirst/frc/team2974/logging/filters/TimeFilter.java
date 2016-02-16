package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

public class TimeFilter extends LogFilter {
private ArrayList<Severity> severityList;
	public void Passthrough (Severity pass){
		severityList.add(pass);
	}
public boolean LogCall(LogMessage message){
		if (severityList.contains(message.getTime())) {
			LogPostFilter(message);
			return true;
		}
		return false;
	}
}