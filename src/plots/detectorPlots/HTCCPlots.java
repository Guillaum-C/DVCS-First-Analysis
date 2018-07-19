package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.forward.htcc.HTCCEvent;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;

public class HTCCPlots{

	private Canvas canvas;
	
	private final String detectorName;
	private final String detectorTab;
	private final String detectorCorrelationTab;
	private final String detectorBySectorTab;
	private final int numberOfRows = 2;
	private final int numberOfColumns = 3;
	
	public HTCCPlots(Canvas canvas, String name){
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

	public void createTabs() {
		this.getCanvas().addTab(detectorTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorCorrelationTab, numberOfRows, numberOfColumns);
//		this.getCanvas().addTab(detectorBySectorTab, numberOfRows, numberOfColumns);
	}

	public void createHistograms() {
		this.getCanvas().create1DHisto(detectorTab, 1, 1, detectorName+"Nphe", detectorName+"Nphe", "Nphe", 100, 0, 100);
		this.getCanvas().create1DHisto(detectorTab, 1, 2, detectorName+"Nphe-elec", detectorName+"Nphe Elec", "Nphe", 100, 0, 100);
		this.getCanvas().create1DHisto(detectorTab, 1, 3, detectorName+"Nphe-p", detectorName+"Nphe p", "Nphe", 100, 0, 100);
		
		this.getCanvas().create1DHisto(detectorTab, 2, 1, detectorName+"T", detectorName+"T", "T", 100, 0, 400);
		this.getCanvas().create1DHisto(detectorTab, 2, 2, detectorName+"T-elec", detectorName+"T Elec", "T", 100, 0, 400);
		this.getCanvas().create1DHisto(detectorTab, 2, 3, detectorName+"T-p", detectorName+"T p", "T", 100, 0, 400);
		
		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 1, detectorName+"TelecvsTprot", detectorName+"Telec vs Tprot", "Tprot", "Telec", 100, 180, 250, 100, 180, 250);
//		this.getCanvas().setLogZ(detectorCorrelationTab, 1, 1, true);
		
	}
	
	public void fillHistograms(Event event) {
		HTCCEvent htccEvent = event.getForwardEvent().getHtccEvent();
		ParticleEvent particleEvent = event.getParticleEvent();
		
		for (Particle particle : particleEvent.getParticles()){
//			if (particle.hasHTCCClusters()>1){
//				System.out.println("part nb htcc hits: "+particle.hasHTCCClusters());
//			}
			if (particle.hasHTCCClusters()>0){
				this.getCanvas().fill1DHisto(detectorName+"Nphe", particle.getHTCCClusters().get(0).getNphe());
				this.getCanvas().fill1DHisto(detectorName+"T", particle.getHTCCClusters().get(0).getTime());
			}
		}
		
		for (Electron particle : particleEvent.getElectrons()){
//			if (particle.hasHTCCClusters()>1){
//				System.out.println("elec nb htcc hits: "+particle.hasFTOFClusters());
//			}
			if (particle.hasHTCCClusters()>0){
				this.getCanvas().fill1DHisto(detectorName+"Nphe-elec", particle.getHTCCClusters().get(0).getNphe());
				this.getCanvas().fill1DHisto(detectorName+"T-elec", particle.getHTCCClusters().get(0).getTime());
			}
			
			for (Proton particle2 : particleEvent.getProtons()){
				if (particle.hasHTCCClusters()>0 && particle2.hasHTCCClusters()>0){
					for (int i = 0; i<particle2.hasHTCCClusters(); i ++){
						for (int j = 0; j<particle.hasHTCCClusters(); j ++){
							this.getCanvas().fill2DHisto(detectorName+"TelecvsTprot", particle2.getHTCCClusters().get(i).getTime(), particle.getHTCCClusters().get(j).getTime());
						}
					}
				}
			}
			
			
		}
		
		for (Proton particle : particleEvent.getProtons()){
			if (particle.hasHTCCClusters()>0){
				this.getCanvas().fill1DHisto(detectorName+"Nphe-p", particle.getHTCCClusters().get(0).getNphe());
				this.getCanvas().fill1DHisto(detectorName+"T-p", particle.getHTCCClusters().get(0).getTime());
			}
		}
		
	}

}
