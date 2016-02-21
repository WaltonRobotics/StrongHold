package org.usfirst.frc.team2974.logging;
import org.usfirst.frc.team2974.logging.filters.LogFilter;

import edu.wpi.first.wpilibj.DriverStation;

public class DashboardSink extends LogFilter {

	@Override
	public boolean logCall(LogMessage message) {
		DriverStation.reportError("@" + message.getTime() + " seconds:" + message.getCommand() + ": " + message.getMessage() + "on line " + message.getLine() + "\n",false);
		return true;
	}

}
