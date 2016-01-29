package org.usfirst.frc.team2974.logging;
import org.usfirst.frc.team2974.logging.filters.LogFilter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardSink extends LogFilter {

	@Override
	public void LogCall(String message) {
		SmartDashboard.putString("message:", message);
	}

}
