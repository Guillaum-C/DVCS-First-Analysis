package plots.particlePlots;

import java.util.ArrayList;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;

public class ParticlePlots {
	
	Canvas myCanvas;
	double electronEnergy;
	
	ElectronPlots electronPlots;
	ProtonPlots protonPlots;
	PhotonPlots photonPlots;
	
	/**
	 * Create a particle plots class
	 * @param canvas canvas
	 * @param electronEnergy beam energy
	 */
	public ParticlePlots(Canvas canvas, double electronEnergy){
		this.myCanvas = canvas;
		this.electronEnergy = electronEnergy;
		this.electronPlots = new ElectronPlots(myCanvas);
		this.protonPlots = new ProtonPlots(myCanvas);
		this.photonPlots = new PhotonPlots(myCanvas);
	}
	
	/**
	 * Create raw particles plots
	 */
	public void createParticlesPlotsRaw() {
		this.electronPlots.createDefaultHistograms(electronEnergy);
		this.protonPlots.createDefaultHistograms(electronEnergy);
		this.photonPlots.createDefaultHistograms(electronEnergy);
		
		int nbParticlesBin = 10;
		double nbParticlesMin = 0;
		double nbParticlesMax = 10;
		String tabNbParticles = "NbParticles";
		myCanvas.addTab(tabNbParticles, 4, 6);
		myCanvas.create1DHisto(tabNbParticles, 1, 1, "numberOfElectrons", "Nb electrons", "Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 1, 2, "numberOfPhotons", "Nb photons", "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 1, 3, "numberOfProtons", "Nb protons", "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 1, 4, "numberOfElectronsRandomTrigger", "Nb electrons (random trigger)","Number of electrons",  nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 1, 5, "numberOfPhotonsRandomTrigger", "Nb photons (random trigger)", "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 1, 6, "numberOfProtonsRandomTrigger", "Nb protons (random trigger)", "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		myCanvas.create1DHisto(tabNbParticles, 2, 2, "numberOfPhotonsWithElec", "Nb photons (with an elec)", "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 2, 3, "numberOfProtonsWithElec", "Nb protons (with an elec)", "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 2, 5, "numberOfPhotonsWithElecRandomTrigger", "Nb photons (with an elec, random trigger)", "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabNbParticles, 2, 6, "numberOfProtonsWithElecRandomTrigger", "Nb protons (with an elec, random trigger)", "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		myCanvas.create2DHisto(tabNbParticles, 3, 1, "numberOfPhotonsVSElectrons", "Nb photons vs electrons", "Number of electrons","Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabNbParticles, 3, 2, "numberOfProtonsVSElectrons", "Nb protons vs electrons","Number of electrons","Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabNbParticles, 3, 3, "numberOfPhotonsVSProtons", "Nb photons vs protons","Number of protons","Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabNbParticles, 3, 4, "numberOfPhotonsVSElectronsRandomTrigger", "Nb photons vs electrons (random trigger)", "Number of electrons","Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabNbParticles, 3, 5, "numberOfProtonsVSElectronsRandomTrigger", "Nb protons vs electrons (random trigger)","Number of electrons","Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabNbParticles, 3, 6, "numberOfPhotonsVSProtonsRandomTrigger", "Nb photons vs protons (random trigger)","Number of protons","Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		myCanvas.create2DHisto(tabNbParticles, 4, 3, "numberOfPhotonsVSProtonsWithElec", "Nb photons vs protons (with an elec)","Number of protons","Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabNbParticles, 4, 6, "numberOfPhotonsVSProtonsWithElecRandomTrigger", "Nb photons Nb protons (with an elec, random trigger)","Number of protons","Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);

		for (int i=1; i<=6; i++){
			for (int j=1; j<=2; j++){
				myCanvas.setLogY(tabNbParticles, j, i, true);
			}
			for (int j=3; j<=4; j++){
				myCanvas.setLogZ(tabNbParticles, j, i, true);
			}
		}
		
	}
	
	/**
	 * Fill raw particles plots
	 * @param processedEvent event to extract particles from
	 */
	public void fillParticlesPlotsRaw(ParticleEvent processedEvent) {
		
		if (processedEvent.hasNumberOfElectrons() > 0) {
			ArrayList<Electron> electrons = processedEvent.getElectrons();
			for (Particle electron : electrons) {
				this.electronPlots.fillDefaultHistograms(electron);
			}
		}

		/* ===== PROTON ===== */
		if (processedEvent.hasNumberOfProtons() > 0) {
			ArrayList<Proton> protons = processedEvent.getProtons();
			for (Particle proton : protons) {
				this.protonPlots.fillDefaultHistograms(proton);
			}
		}
//
		/* ===== PHOTON ===== */
		if (processedEvent.hasNumberOfPhotons() > 0) {
			ArrayList<Photon> photons = processedEvent.getPhotons();
			for (Particle photon : photons) {
				this.photonPlots.fillDefaultHistograms(photon);
			}
		}
	}
	
	public void fillNumberOfParticlesPlots(Event processedEvent){
		/* ===== NUMBER OF PARTICLES ===== */
		this.myCanvas.fill1DHisto("numberOfElectrons", processedEvent.getParticleEvent().hasNumberOfElectrons());
		this.myCanvas.fill1DHisto("numberOfPhotons", processedEvent.getParticleEvent().hasNumberOfPhotons());
		this.myCanvas.fill1DHisto("numberOfProtons", processedEvent.getParticleEvent().hasNumberOfProtons());
		this.myCanvas.fill2DHisto("numberOfPhotonsVSElectrons", processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
		this.myCanvas.fill2DHisto("numberOfProtonsVSElectrons", processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfProtons());
		this.myCanvas.fill2DHisto("numberOfPhotonsVSProtons", processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
		if (processedEvent.getParticleEvent().hasNumberOfElectrons()>0){
			this.myCanvas.fill1DHisto("numberOfPhotonsWithElec", processedEvent.getParticleEvent().hasNumberOfPhotons());
			this.myCanvas.fill1DHisto("numberOfProtonsWithElec", processedEvent.getParticleEvent().hasNumberOfProtons());
			this.myCanvas.fill2DHisto("numberOfPhotonsVSProtonsWithElec", processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
		}
		
		if (processedEvent.getTrigger_bit(31)){
			this.myCanvas.fill1DHisto("numberOfElectronsRandomTrigger", processedEvent.getParticleEvent().hasNumberOfElectrons());
			this.myCanvas.fill1DHisto("numberOfPhotonsRandomTrigger", processedEvent.getParticleEvent().hasNumberOfPhotons());
			this.myCanvas.fill1DHisto("numberOfProtonsRandomTrigger", processedEvent.getParticleEvent().hasNumberOfProtons());
			this.myCanvas.fill2DHisto("numberOfPhotonsVSElectronsRandomTrigger", processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
			this.myCanvas.fill2DHisto("numberOfProtonsVSElectronsRandomTrigger", processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfProtons());
			this.myCanvas.fill2DHisto("numberOfPhotonsVSProtonsRandomTrigger", processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
			if (processedEvent.getParticleEvent().hasNumberOfElectrons()>0){
				this.myCanvas.fill1DHisto("numberOfPhotonsWithElecRandomTrigger", processedEvent.getParticleEvent().hasNumberOfPhotons());
				this.myCanvas.fill1DHisto("numberOfProtonsWithElecRandomTrigger", processedEvent.getParticleEvent().hasNumberOfProtons());
				this.myCanvas.fill2DHisto("numberOfPhotonsVSProtonsWithElecRandomTrigger", processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
			}
		}
	}
	
	/**
	 * Create particles plots after cuts
	 */
	public void createParticlesPlotsAfterCuts(){
		this.electronPlots.createDefaultHistograms(electronEnergy, "Electron cut", "after cut");

		this.photonPlots.createDefaultHistograms(electronEnergy, "Photon cut", "after cut");
		
		this.protonPlots.createDefaultHistograms(electronEnergy, "Proton cut", "after cut");	
	}
	
	/**
	 * Fill particles after cuts
	 * @param particleEvent event to plot
	 */
	public void fillParticlesPlotsAfterCuts(ParticleEvent particleEvent){

		if (particleEvent.hasNumberOfElectrons() > 0) {
			ArrayList<Electron> electrons = particleEvent.getElectrons();
			for (Particle electron : electrons) {
				this.electronPlots.fillDefaultHistograms(electron, "Electron cut", "after cut");
			}
		}

		/* ===== PROTON ===== */
		if (particleEvent.hasNumberOfProtons() > 0) {
			ArrayList<Proton> protons = particleEvent.getProtons();
			for (Particle proton : protons) {
				this.protonPlots.fillDefaultHistograms(proton, "Proton cut", "after cut");
			}
		}
//
		/* ===== PHOTON ===== */
		if (particleEvent.hasNumberOfPhotons() > 0) {
			ArrayList<Photon> photons = particleEvent.getPhotons();
			for (Particle photon : photons) {
				this.photonPlots.fillDefaultHistograms(photon, "Photon cut", "after cut");
			}
		}
	}
	
}
