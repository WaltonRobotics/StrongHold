package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.enumerations.Severity;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

public class SubSystemFilter extends LogFilter {
private ArrayList<Severity> subSystemList;
	public void Passthrough (Severity pass){
		subSystemList.add(pass);
	}
public boolean logCall(LogMessage message){
		if (subSystemList.contains(message.getSubSystem())){
			logPostFilter(message);
			return true;
		}
		return false;
	}
}