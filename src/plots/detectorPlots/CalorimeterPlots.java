package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.forward.calorimeter.CalorimeterEvent;
import org.clas12.analysisTools.event.forward.calorimeter.CalorimeterRecCluster;
import org.clas12.analysisTools.event.particles.*;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.plots.Canvas;
public class CalorimeterPlots {
	
	private Canvas canvas;
	private final String detectorName;
	private final String detectorTab;
	private final String detectorCorrelationTab;
	private final String detectorBySectorTab;
	private final int numberOfRows = 2;
	private final int numberOfColumns = 4;

	/**
	 * Create a calorimeter tab with calorimeter plots
	 * @param canvas canvas
	 * @param name name used for the tab and the plots
	 */
	public CalorimeterPlots(Canvas canvas, String name) {
		super();
		this.canvas = canvas;
		this.detectorName = name;
		this.detectorTab = name;
		this.detectorCorrelationTab = name+" correlation";
		this.detectorBySectorTab = detectorTab+"/sector";
		createCalorimeterTabs();
		createHistograms();
	}
	
	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Create a new tab
	 */
	public void createCalorimeterTabs(){
		this.getCanvas().addTab(detectorTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorCorrelationTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorBySectorTab, numberOfRows, numberOfColumns);
	}
	
	/**
	 * Create histograms
	 */
	public void createHistograms(){
		this.getCanvas().create2DHisto(detectorTab, 1, 1, detectorName+"SF", detectorName+" SF", "p", "E/p", 100, 0, 10, 100, 0, 0.4);
		this.getCanvas().setLogZ(detectorTab, 1, 1, true);
		this.getCanvas().create2DHisto(detectorTab, 1, 2, detectorName+"SF-elec", detectorName+" SF elec", "p", "E/p", 100, 0, 10, 100, 0, 0.4);
		this.getCanvas().setLogZ(detectorTab, 1, 2, true);
		this.getCanvas().create2DHisto(detectorTab, 1, 3, detectorName+"SF-phot", detectorName+" SF phot", "p", "E/p", 100, 0, 10, 100, 0, 0.4);
		this.getCanvas().setLogZ(detectorTab, 1, 3, true);
		this.getCanvas().create2DHisto(detectorTab, 1, 4, detectorName+"SF-prot", detectorName+" SF prot", "p", "E/p", 100, 0, 10, 100, 0, 0.4);
		this.getCanvas().setLogZ(detectorTab, 1, 4, true);
		this.getCanvas().create2DHisto(detectorTab, 2, 1, detectorName+"EvsT", detectorName+" E vs T", "E", "T", 50, 0, 4, 50, 0, 1000);
		this.getCanvas().setLogZ(detectorTab, 2, 1, true);
		this.getCanvas().create2DHisto(detectorTab, 2, 2, detectorName+"EvsT-elec", detectorName+" E vs T elec", "E", "T", 50, 0, 4, 50, 0, 1000);
		this.getCanvas().setLogZ(detectorTab, 2, 2, true);
		this.getCanvas().create2DHisto(detectorTab, 2, 3, detectorName+"EvsT-phot", detectorName+" E vs T phot", "E", "T", 50, 0, 4, 50, 0, 1000);
		this.getCanvas().setLogZ(detectorTab, 2, 3, true);
		this.getCanvas().create2DHisto(detectorTab, 2, 4, detectorName+"EvsT-prot", detectorName+" E vs T prot", "E", "T", 50, 0, 4, 50, 0, 1000);
		this.getCanvas().setLogZ(detectorTab, 2, 4, true);
		
		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 1, detectorName+"TelecvsTphot", detectorName+"Telec vs Tphot", "Tphot", "Telec", 100, 150, 300, 100, 150, 300);
		this.getCanvas().create2DHisto(detectorCorrelationTab, 2, 1, detectorName+"TelecvsTphot"+1, detectorName+"Telec vs Tphot"+" s"+1, "Tphot", "Telec", 100, 150, 300, 100, 150, 300);
		this.getCanvas().create2DHisto(detectorCorrelationTab, 2, 2, detectorName+"TelecvsTphot"+4, detectorName+"Telec vs Tphot"+" s"+4, "Tphot", "Telec", 100, 150, 300, 100, 150, 300);
		this.getCanvas().create2DHisto(detectorCorrelationTab, 2, 3, detectorName+"TelecvsTphot"+7, detectorName+"Telec vs Tphot"+" s"+7, "Tphot", "Telec", 100, 150, 300, 100, 150, 300);
		
		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 2, detectorName+"TelecvsTprot", detectorName+"Telec vs Tprot", "Tprot", "Telec", 100, 150, 300, 100, 150, 300);
		
		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 3, detectorName+"TprotvsTphot", detectorName+"Tprot vs Tphot", "Tphot", "Tprot", 100, 150, 300, 100, 150, 300);
	}
	
	/**
	 * Fill calorimeter histograms
	 * @param event processed event 
	 */
	public void fillHistograms(Event event){
		CalorimeterEvent caloEvent = event.getForwardEvent().getCalorimeterEvent();
		for (CalorimeterRecCluster caloClusters : caloEvent.getCalorimeterClusters()){
			this.getCanvas().fill2DHisto(detectorName+"EvsT", caloClusters.getEnergy(), caloClusters.getTime());
		}
		ParticleEvent particleEvent = event.getParticleEvent();
		for (Particle particle : particleEvent.getParticles()){
			if (particle.hasCalorimeterClusters()>0){
				double energy = 0;
				for (int i=0 ; i<particle.hasCalorimeterClusters(); i++){
					energy += particle.getCalorimeterRecClusters().get(i).getEnergy();
				}
				this.getCanvas().fill2DHisto(detectorName+"SF", particle.getMomentum().mag(), energy/particle.getMomentum().mag());	
			}
		}
		for (Electron particle : particleEvent.getElectrons()){
			if (particle.hasCalorimeterClusters()>0){
				double energy = 0;
				for (int i=0 ; i<particle.hasCalorimeterClusters(); i++){
					energy += particle.getCalorimeterRecClusters().get(i).getEnergy();
				}
				this.getCanvas().fill2DHisto(detectorName+"SF-elec", particle.getMomentum().mag(), energy/particle.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"EvsT-elec", particle.getCalorimeterRecClusters().get(0).getEnergy(), particle.getCalorimeterRecClusters().get(0).getTime());
			}
			for (Photon particle2 : particleEvent.getPhotons()){
				if (particle.hasCalorimeterClusters()>0 && particle2.hasCalorimeterClusters()>0){
					this.getCanvas().fill2DHisto(detectorName+"TelecvsTphot", particle2.getCalorimeterRecClusters().get(0).getTime(), particle.getCalorimeterRecClusters().get(0).getTime());
					for (int i=0; i<particle.hasCalorimeterClusters(); i++){
						for (int j=0; j<particle.hasCalorimeterClusters(); j++){
							if (particle.getCalorimeterRecClusters().get(i).getLayer()==particle.getCalorimeterRecClusters().get(j).getLayer()){
								this.getCanvas().fill2DHisto(detectorName+"TelecvsTphot"+particle.getCalorimeterRecClusters().get(i).getLayer(), particle2.getCalorimeterRecClusters().get(0).getTime(), particle.getCalorimeterRecClusters().get(0).getTime());
							}
						}
					}
				}
			}
			for (Proton particle2 : particleEvent.getProtons()){
				if (particle.hasCalorimeterClusters()>0 && particle2.hasCalorimeterClusters()>0){
					this.getCanvas().fill2DHisto(detectorName+"TelecvsTprot", particle2.getCalorimeterRecClusters().get(0).getTime(), particle.getCalorimeterRecClusters().get(0).getTime());
				}
			}
		}
		for (Photon particle : particleEvent.getPhotons()){
			if (particle.hasCalorimeterClusters()>0){
				double energy = 0;
				for (int i=0 ; i<particle.hasCalorimeterClusters(); i++){
					energy += particle.getCalorimeterRecClusters().get(i).getEnergy();
				}
				this.getCanvas().fill2DHisto(detectorName+"SF-phot", particle.getMomentum().mag(), energy/particle.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"EvsT-phot", particle.getCalorimeterRecClusters().get(0).getEnergy(), particle.getCalorimeterRecClusters().get(0).getTime());
			}
			for (Proton particle2 : particleEvent.getProtons()){
				if (particle.hasCalorimeterClusters()>0 && particle2.hasCalorimeterClusters()>0){
					this.getCanvas().fill2DHisto(detectorName+"TprotvsTphot", particle.getCalorimeterRecClusters().get(0).getTime(), particle2.getCalorimeterRecClusters().get(0).getTime());
				}
			}
		}
		for (Proton particle : particleEvent.getProtons()){
			if (particle.hasCalorimeterClusters()>0){
				double energy = 0;
				for (int i=0 ; i<particle.hasCalorimeterClusters(); i++){
					energy += particle.getCalorimeterRecClusters().get(i).getEnergy();
				}
				this.getCanvas().fill2DHisto(detectorName+"SF-prot", particle.getMomentum().mag(), energy /particle.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"EvsT-prot", particle.getCalorimeterRecClusters().get(0).getEnergy(), particle.getCalorimeterRecClusters().get(0).getTime());
			}
		}
	}
	
}
