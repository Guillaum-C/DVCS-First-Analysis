package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.plots.Canvas;

public class DetectorPlots {
	
	Canvas myCanvas;
	double electronEnergy;
	
	CVTPlots cvtPlots;
	DCPlots dcPlots;
	CalorimeterPlots caloPlots;
	FTOFPlots ftofPlots;
	HTCCPlots htccPlots;
	
	public DetectorPlots(Canvas canvas, double electronEnergy){
		this.myCanvas=canvas;
		this.electronEnergy=electronEnergy;
		this.cvtPlots = new CVTPlots(myCanvas);
		this.dcPlots = new DCPlots(myCanvas, "DC");
		this.caloPlots = new CalorimeterPlots(myCanvas, "Calo");
		this.ftofPlots = new FTOFPlots(myCanvas, "FTOF");
		this.htccPlots = new HTCCPlots(myCanvas, "HTCC");
	}
	
	public void createDetectorsPlotsRaw() {
		this.cvtPlots.createDefaultHistograms(electronEnergy);
	}
	
	public void fillDetectorsPlotsRaw(Event processedEvent){
		
		this.cvtPlots.fillDefaultHistograms(processedEvent.getCentralEvent().getCvtEvent());
		
		this.dcPlots.fillHistograms(processedEvent);
		this.caloPlots.fillHistograms(processedEvent);
		this.ftofPlots.fillHistograms(processedEvent);
		this.htccPlots.fillHistograms(processedEvent);

	}
	
	public void createDetectorsPlotsAfterCuts(){
		this.cvtPlots.createDefaultHistograms(electronEnergy, "CVT cut", "after cut");
	}
	
	public void fillDetectorsPlotsAfterCuts(Event processedEvent){
		this.cvtPlots.fillDefaultHistograms(processedEvent.getCentralEvent().getCvtEvent(), "CVT cut", "after cut");
	}
}
