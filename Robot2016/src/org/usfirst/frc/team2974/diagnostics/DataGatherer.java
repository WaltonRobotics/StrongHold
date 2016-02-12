package org.usfirst.frc.team2974.diagnostics;

import java.util.ArrayList;

public abstract class DataGatherer {
ArrayList<DataGatherer> dataList;

public void attach(DataGatherer dataGatherer){//Push LogSink to the ArrayList of LogSinks
	dataList.add(dataGatherer);
}
public void detach(DataGatherer dataGatherer){//Remove LongSink from the ArrayList of LogSinks
	 dataList.remove(dataGatherer);
}
//Parameters of function below should allow it to identify datagatherers.
public void processDataElement(Object object){
	for(DataGatherer dataGathererIterator : dataList){
		dataGathererIterator.updateDashboard(object);
	}
}
public void updateDashboard(Object object) {
	
}
}
