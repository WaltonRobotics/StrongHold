package org.usfirst.frc.team2974.diagnostics.dataGatherers;

import org.usfirst.frc.team2974.diagnostics.DiagnosticDataTypeList;

public class VelosityGatherer {
	public void processDataElement(DiagnosticDataTypeList type, Object object) {

	}

	public void updateDashboard() {

	}

	public boolean IsDataElementHandle(DiagnosticDataTypeList type) {
		if (type == DiagnosticDataTypeList.VELOCITY_INFO) {
			return true;
		} else {
			return false;
		}

	}
}
