package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;

public interface DetectorPlots {

	public void createTabs();
	
	public void createHistograms();
	
	public void fillHistograms(Event event);
	
}
