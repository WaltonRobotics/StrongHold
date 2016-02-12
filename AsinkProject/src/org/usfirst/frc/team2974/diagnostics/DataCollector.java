package org.usfirst.frc.team2974.diagnostics;

import java.util.ArrayList;

<<<<<<< HEAD
import org.usfirst.frc.team2974.logging.LogSink;

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
	public void ProcessDataElement(DiagnosticDataTypeList type, Object object){
		for(DataGatherer iterator : dataList){
			if(iterator.IsDataElementHandle(type)) {
				iterator.processDataElement(type, object);
			}
		
		}
	}
=======
public class DataCollector extends DataGatherer{
	
>>>>>>> 62bb7efc4c56312dab86f6dab1bada4f28c25a29
}
