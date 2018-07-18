package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.forward.ftof.FTOFEvent;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;

public class FTOFPlots implements DetectorPlots {

	private Canvas canvas;
	private final String detectorName;
	private final String detectorTab;
	private final String detectorCorrelationTab;
	private final String detectorBySectorTab;
	private final int numberOfRows = 2;
	private final int numberOfColumns = 2;
	
	public FTOFPlots(Canvas canvas, String name){
		this.canvas = canvas;
		this.detectorName = name;
		this.detectorTab = name;
		this.detectorCorrelationTab = name+" correlation";
		this.detectorBySectorTab = detectorTab+"/sector";
		this.createTabs();
		this.createHistograms();
	}
	
	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void createTabs() {
		this.getCanvas().addTab(detectorTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorCorrelationTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorBySectorTab, numberOfRows, numberOfColumns);
	}

	@Override
	public void createHistograms() {
		this.getCanvas().create2DHisto(detectorTab, 1, 1, detectorName+"BetavsP", detectorName+"Beta vs P", "Tphot", "Telec", 100, 0, 10, 100, 0, 1);
		this.getCanvas().create2DHisto(detectorTab, 2, 1, detectorName+"BetavsP-elec", detectorName+"Beta vs P Elec", "Tphot", "Telec", 100, 0, 10, 100, 0, 1);
		this.getCanvas().create2DHisto(detectorTab, 2, 2, detectorName+"BetavsP-prot", detectorName+"Beta vs P prot", "Tphot", "Tprot", 100, 0, 10, 100, 0, 1);
		this.getCanvas().setLogZ(detectorTab, 1, 1, true);
		this.getCanvas().setLogZ(detectorTab, 2, 1, true);
		this.getCanvas().setLogZ(detectorTab, 2, 2, true);
		
		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 1, detectorName+"TelecvsTprot", detectorName+"Telec vs Tprot", "Tprot", "Telec", 100, 180, 250, 100, 180, 250);
		this.getCanvas().setLogZ(detectorCorrelationTab, 1, 1, true);
	}
	
	@Override
	public void fillHistograms(Event event) {
		FTOFEvent ftofEvent = event.getForwardEvent().getFtofEvent();
		ParticleEvent particleEvent = event.getParticleEvent();
		
		for (Particle particle : particleEvent.getParticles()){
			this.getCanvas().fill2DHisto(detectorName+"BetavsP", particle.getP(), particle.getBeta());
		}
		
		for (Electron particle : particleEvent.getElectrons()){
//			if (particle.hasFTOFClusters()>1){
//				System.out.println("elec nb ftof hits: "+particle.hasFTOFClusters());
//			}
			this.getCanvas().fill2DHisto(detectorName+"BetavsP-elec", particle.getP(), particle.getBeta());
			
			for (Proton particle2 : particleEvent.getProtons()){
				if (particle.hasFTOFClusters()>0 && particle2.hasFTOFClusters()>0){
					for (int i = 0; i<particle2.hasFTOFClusters(); i ++){
						for (int j = 0; j<particle.hasFTOFClusters(); j ++){
//							System.out.println("time: "+ particle2.getFTOFClusters().get(i).getTime());
//							System.out.println("time: "+ particle.getFTOFClusters().get(j).getTime());
							this.getCanvas().fill2DHisto(detectorName+"TelecvsTprot", particle2.getFTOFClusters().get(i).getTime(), particle.getFTOFClusters().get(j).getTime());
						}
					}
				}
			}
		}
		
		for (Proton particle : particleEvent.getProtons()){
//			if (particle.hasFTOFClusters()>1){
//				System.out.println("prot nb ftof hits: "+particle.hasFTOFClusters());
//			}
			this.getCanvas().fill2DHisto(detectorName+"BetavsP-prot", particle.getP(), particle.getBeta());
		}
		
	}

}