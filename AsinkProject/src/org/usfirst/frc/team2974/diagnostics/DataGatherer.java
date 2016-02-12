package org.usfirst.frc.team2974.diagnostics;

import java.util.ArrayList;

public abstract class DataGatherer {

//Parameters of function below should allow it to identify datagatherers.
abstract public void processDataElement(DiagnosticDataTypeList type, Object object);
abstract public void updateDashboard();
abstract public boolean IsDataElementHandle(DiagnosticDataTypeList type);
}
