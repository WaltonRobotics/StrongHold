package org.usfirst.frc.team2974.diagnostics;

import java.util.ArrayList;

public class DataCollector{
	ArrayList<DataGatherer> dataList;

	public void attach(DataGatherer dataGatherer){//Push LogSink to the ArrayList of LogSinks
		dataList.add(dataGatherer);
	}
	public void detach(DataGatherer dataGatherer){//Remove LongSink from the ArrayList of LogSinks
		 dataList.remove(dataGatherer);
	}
	public void updateDashboard(){
		for(DataGatherer iterator : dataList){
			iterator.updateDashboard();
		}

	}
	public void ProcessDataElement(DiagnosticDataTypeList type, Object object){ // TFS: DiagnosticDataTypeList missing from github
		for(DataGatherer iterator : dataList){
			if(iterator.IsDataElementHandle(type)) {
				iterator.processDataElement(type, object);
			}
		}
	}
}