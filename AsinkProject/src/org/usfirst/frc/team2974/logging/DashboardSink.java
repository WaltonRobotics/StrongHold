package org.usfirst.frc.team2974.logging;
import org.usfirst.frc.team2974.logging.filters.LogFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;
import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardSink extends LogFilter {

	@Override
	public boolean LogCall(LogMessage message) {
		DriverStation.reportError("@" + message.getTime() + " seconds:" + message.getCommand() + ": " + message.getMessage() + "on line " + message.getLine() + "\n",false);
		return true;
	}

}
