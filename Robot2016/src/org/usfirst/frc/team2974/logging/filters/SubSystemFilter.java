package org.usfirst.frc.team2974.logging.filters;
import java.util.ArrayList;

import org.usfirst.frc.team2974.logging.LogMessage;
import org.usfirst.frc.team2974.logging.enumerations.Severity;

public class SubSystemFilter extends LogFilter {
private ArrayList<Severity> subSystemList;
	public void Passthrough (Severity pass){
		subSystemList.add(pass);
	} // calling log filter adding subsystem list
public boolean logCall(LogMessage message){
		if (subSystemList.contains(message.getSubSystem())){
			logPostFilter(message);
			return true;
			// boolean tells if message is sent through 
			// and log post filter pushes down to next level
			/*this 
			 * is a multiline
			 * comment
			 *(:
			 */
		}
		return false;
	}
}