package org.usfirst.frc.team2974.diagnostics.dataGatherers;

import java.util.Stack;

import org.usfirst.frc.team2974.diagnostics.DataGatherer;
import org.usfirst.frc.team2974.diagnostics.DiagnosticDataTypeList;
import org.usfirst.frc.team2974.diagnostics.messageObjects.VelocityData;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VelosityGatherer extends DataGatherer{
	double rightPercentDifference;
	double leftPercentDifference;
	Stack<VelocityData> velocityList = new Stack<VelocityData>();
	public void processDataElement(DiagnosticDataTypeList type, Object object) {
		leftPercentDifference = (((VelocityData) object).getPredictedLeftVelocity() - ((VelocityData) object).getTrueLeftVelocity())
				/((VelocityData) object).getPredictedLeftVelocity();
		rightPercentDifference = (((VelocityData) object).getPredictedRightVelocity() - ((VelocityData) object).getTrueRightVelocity())
				/((VelocityData) object).getPredictedRightVelocity();
		
		
		
		velocityList.push((VelocityData) object);
		updateDashboard();
	}

			
	public void updateDashboard() {
		SmartDashboard.putNumber("leftPercentError", leftPercentDifference);
		SmartDashboard.putNumber("rightPercentError", rightPercentDifference);
	}

	public boolean IsDataElementHandle(DiagnosticDataTypeList type) {
		if (type == DiagnosticDataTypeList.VELOCITY_INFO) {
			return true;
		} else {
			return false;
		}

	}
}
