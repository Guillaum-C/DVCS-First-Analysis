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
		this.dcPlots = new DCPlots(myCanvas);
		this.caloPlots = new CalorimeterPlots(myCanvas, "Calo");
		this.ftofPlots = new FTOFPlots(myCanvas, "FTOF");
		this.htccPlots = new HTCCPlots(myCanvas, "HTCC");
	}
	
	public void createDetectorsPlotsRaw() {
		this.cvtPlots.createDefaultHistograms(electronEnergy);
		this.dcPlots.createDefaultHistograms(electronEnergy);
	}
	
	public void fillDetectorsPlotsRaw(Event processedEvent){
		
		this.cvtPlots.fillDefaultHistograms(processedEvent.getCentralEvent().getCvtEvent());
		
		this.dcPlots.fillDefaultHistograms(processedEvent.getForwardEvent().getForwardTrackerEvent());
		this.caloPlots.fillHistograms(processedEvent);
		this.ftofPlots.fillHistograms(processedEvent);
		this.htccPlots.fillHistograms(processedEvent);
	}
	
	public void createDetectorsPlotsAfterCuts(){
		this.cvtPlots.createDefaultHistograms(electronEnergy, "CVT cut", "after cut");
		this.dcPlots.createDefaultHistograms(electronEnergy, "DC cut", "after cut");
	}
	
	public void fillDetectorsPlotsAfterCuts(Event processedEvent){
		this.cvtPlots.fillDefaultHistograms(processedEvent.getCentralEvent().getCvtEvent(), "CVT cut", "after cut");
		this.dcPlots.fillDefaultHistograms(processedEvent.getForwardEvent().getForwardTrackerEvent(), "DC cut", "after cut");
	}

	public void createDetectorsPlotsRandomTrigger(){
		this.cvtPlots.createDefaultHistograms(electronEnergy, "CVT random", "rand trig");
		this.dcPlots.createDefaultHistograms(electronEnergy, "DC random", "rand trig");
	}
	
	public void fillDetectorsPlotsRandomTrigger(Event processedEvent){
		this.cvtPlots.fillDefaultHistograms(processedEvent.getCentralEvent().getCvtEvent(), "CVT random", "rand trig");
		this.dcPlots.fillDefaultHistograms(processedEvent.getForwardEvent().getForwardTrackerEvent(), "DC random", "rand trig");
	}
}
