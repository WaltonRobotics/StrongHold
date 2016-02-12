package org.usfirst.frc.team2974.diagnostics;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DataCollector extends DataGatherer{
	public void updateDashboard(Object object){
		double time = Timer.getFPGATimestamp();
		SmartDashboard.putString("at" + time + " seconds:", (String)object);
	}
}
