package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.LogMessage;
import org.usfirst.frc.team2974.logging.enumerations.Severity;

public class SeverityFilter extends LogFilter {
private ArrayList<Severity> severityList = new ArrayList<Severity>();
	public void Passthrough (Severity pass){
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
			logPostFilter(message);
			return true;
		}
		return false;
	}
}