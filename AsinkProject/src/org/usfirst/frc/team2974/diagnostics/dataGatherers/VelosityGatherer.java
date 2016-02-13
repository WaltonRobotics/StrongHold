package org.usfirst.frc.team2974.diagnostics.dataGatherers;

import java.util.Stack;

import org.usfirst.frc.team2974.diagnostics.DiagnosticDataTypeList;
import org.usfirst.frc.team2974.diagnostics.messageObjects.VelocityData;

public class VelosityGatherer {
	Stack<VelocityData> velocityList = new Stack<VelocityData>();
	public void processDataElement(DiagnosticDataTypeList type, VelocityData object) {
		
		
		
		velocityList.push(object);
		updateDashboard(1,1);
	}

	public void updateDashboard(double leftWheelDifference, double rightWheelDifference) {
		
	}

	public boolean IsDataElementHandle(DiagnosticDataTypeList type) {
		if (type == DiagnosticDataTypeList.VELOCITY_INFO) {
			return true;
		} else {
			return false;
		}

	}
}
