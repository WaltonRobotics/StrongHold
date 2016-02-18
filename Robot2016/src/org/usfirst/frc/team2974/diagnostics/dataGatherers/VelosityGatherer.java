package org.usfirst.frc.team2974.diagnostics.dataGatherers;

import java.util.Stack;

import org.usfirst.frc.team2974.diagnostics.DiagnosticDataTypeList;
import org.usfirst.frc.team2974.diagnostics.messageObjects.VelocityData;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VelosityGatherer {
	Stack<VelocityData> velocityList = new Stack<VelocityData>();
	public void processDataElement(DiagnosticDataTypeList type, VelocityData object) {
		double leftPercentDifference = (object.getPredictedLeftVelocity() - object.getTrueLeftVelocity())/object.getPredictedLeftVelocity();
		double rightPercentDifference = (object.getPredictedRightVelocity() - object.getTrueRightVelocity())/object.getPredictedRightVelocity();
		
		
		
		velocityList.push(object);
		updateDashboard(leftPercentDifference ,rightPercentDifference);
	}

			
	public void updateDashboard(double leftPercentError, double rightPercentError) {
		SmartDashboard.putNumber("leftPercentError", leftPercentError);
		SmartDashboard.putNumber("rightPercentError", rightPercentError);
	}

	public boolean IsDataElementHandle(DiagnosticDataTypeList type) {
		if (type == DiagnosticDataTypeList.VELOCITY_INFO) {
			return true;
		} else {
			return false;
		}

	}
}
