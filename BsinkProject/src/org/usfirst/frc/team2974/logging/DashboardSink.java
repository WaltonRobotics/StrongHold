package org.usfirst.frc.team2974.logging;
import org.usfirst.frc.team2974.logging.filters.LogFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardSink extends LogFilter {

	@Override
	public boolean LogCall(LogMessage message) {
		SmartDashboard.putString("message:", message.getMessage());
		return true;
	}

}
