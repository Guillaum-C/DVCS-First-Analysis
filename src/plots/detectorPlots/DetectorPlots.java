package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.plots.Canvas;

public class DetectorPlots {
	
	CVTPlots cvtPlots;
	
	DCPlots dcPlots;
	CalorimeterPlots caloPlots;
	FTOFPlots ftofPlots;
	HTCCPlots htccPlots;
	
	
	public void createDetectorsPlots(Canvas myCanvas, double electronEnergy) {
		
		cvtPlots = new CVTPlots(myCanvas, "CVT", "",electronEnergy);
		
		dcPlots = new DCPlots(myCanvas, "DC");
		caloPlots = new CalorimeterPlots(myCanvas, "Calo");
		ftofPlots = new FTOFPlots(myCanvas, "FTOF");
		htccPlots = new HTCCPlots(myCanvas, "HTCC");
		
	}
	
	public void fillDetectorsPlots(Event processedEvent){
		
		cvtPlots.fillHistograms(processedEvent.getCentralEvent().getCvtEvent());
		
		dcPlots.fillHistograms(processedEvent);
		caloPlots.fillHistograms(processedEvent);
		ftofPlots.fillHistograms(processedEvent);
		htccPlots.fillHistograms(processedEvent);

	}
	
}
