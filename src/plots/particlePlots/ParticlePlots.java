package plots.particlePlots;

import java.util.ArrayList;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;

import plots.PlotTools;

public class ParticlePlots {
	
	Canvas myCanvas;
	double electronEnergy;
	
	ElectronPlots electronPlots;
	ProtonPlots protonPlots;
	PhotonPlots photonPlots;
	
	/**
	 * Create plots class
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
	 * Create number of particles plots
	 */
	public void createNumberParticlesPlots(String tabLegend, String plotExtraLegend){
		int nbParticlesBin = 10;
		double nbParticlesMin = 0;
		double nbParticlesMax = 10;
		
		String plotFamilyName = "Nb";
		
		String tabTitle = PlotTools.createTabName (plotFamilyName+" particles ",tabLegend);
		
		int particleTabNumberOfRows = 4;
		int particleTabNumberOfColumns = 5;
		this.myCanvas.addTab(tabTitle, particleTabNumberOfRows, particleTabNumberOfColumns);

		String prefix = plotFamilyName + ": ";
		String suffix = PlotTools.createSuffix(plotExtraLegend);
		
		myCanvas.create1DHisto(tabTitle, 1, 1, prefix+"electrons"+suffix, prefix+"electrons"+suffix, "Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabTitle, 1, 2, prefix+"photons"+suffix, prefix+"photons"+suffix, "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabTitle, 1, 3, prefix+"protons"+suffix, prefix+"protons"+suffix, "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);

		myCanvas.create2DHisto(tabTitle, 2, 1, prefix+"photons vs electrons"+suffix, prefix+"photons vs electrons"+suffix, "Number of electrons","Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabTitle, 2, 2, prefix+"protons vs electrons"+suffix, prefix+"protons vs electrons"+suffix,"Number of electrons","Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabTitle, 2, 3, prefix+"photons vs protons"+suffix, prefix+"photons vs protons"+suffix,"Number of protons","Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		suffix = PlotTools.createSuffix("with elec", plotExtraLegend);
		myCanvas.create1DHisto(tabTitle, 1, 4, prefix+"photons"+suffix, prefix+"photons"+suffix, "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabTitle, 1, 5, prefix+"protons"+suffix, prefix+"protons"+suffix, "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		myCanvas.create2DHisto(tabTitle, 2, 4, prefix+"photons vs protons"+suffix, prefix+"photons vs protons"+suffix,"Number of protons","Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		
		suffix = PlotTools.createSuffix("rand trig", plotExtraLegend);
		myCanvas.create1DHisto(tabTitle, 3, 1, prefix+"electrons"+suffix, prefix+"electrons"+suffix,"Number of electrons",  nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabTitle, 3, 2, prefix+"photons"+suffix, prefix+"photons"+suffix, "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabTitle, 3, 3, prefix+"protons"+suffix, prefix+"protons"+suffix, "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);

		myCanvas.create2DHisto(tabTitle, 4, 1, prefix+"photons vs electrons"+suffix, prefix+"photons vs electrons"+suffix, "Number of electrons","Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabTitle, 4, 2, prefix+"protons vs electrons"+suffix, prefix+"protons vs electrons"+suffix,"Number of electrons","Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create2DHisto(tabTitle, 4, 3, prefix+"photons vs protons"+suffix, prefix+"photons vs protons"+suffix,"Number of protons","Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		suffix = PlotTools.createSuffix("with elec", "rand trig", plotExtraLegend);
		myCanvas.create1DHisto(tabTitle, 3, 4, prefix+"photons"+suffix, prefix+"photons"+suffix, "Number of photons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		myCanvas.create1DHisto(tabTitle, 3, 5, prefix+"protons"+suffix, prefix+"protons"+suffix, "Number of protons", nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		myCanvas.create2DHisto(tabTitle, 4, 4, prefix+"photons vs protons"+suffix, prefix+"photons vs protons"+suffix,"Number of protons","Number of electrons", nbParticlesBin, nbParticlesMin, nbParticlesMax, nbParticlesBin, nbParticlesMin, nbParticlesMax);
		
		for (int i=1; i<=particleTabNumberOfRows; i++){
			for (int j=1; j<=particleTabNumberOfColumns; j++){
				if (i==1||i==3){
				myCanvas.setLogY(tabTitle, i, j, true);
				}else{
					myCanvas.setLogZ(tabTitle, i, j, true);
				}
			}
			
		}
	}

	public void fillNumberOfParticlesPlots(Event processedEvent, String plotExtraLegend){
		
		/* ===== NUMBER OF PARTICLES ===== */
		String plotFamilyName = "Nb";
		String prefix = plotFamilyName + ": ";
		String suffix = PlotTools.createSuffix( plotExtraLegend);
		this.myCanvas.fill1DHisto(prefix+"electrons"+suffix, processedEvent.getParticleEvent().hasNumberOfElectrons());
		this.myCanvas.fill1DHisto(prefix+"photons"+suffix, processedEvent.getParticleEvent().hasNumberOfPhotons());
		this.myCanvas.fill1DHisto(prefix+"protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons());
		this.myCanvas.fill2DHisto(prefix+"photons vs electrons"+suffix, processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
		this.myCanvas.fill2DHisto(prefix+"protons vs electrons"+suffix, processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfProtons());
		this.myCanvas.fill2DHisto(prefix+"photons vs protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
		if (processedEvent.getParticleEvent().hasNumberOfElectrons()>0){
			suffix = PlotTools.createSuffix("with elec", plotExtraLegend);
			this.myCanvas.fill1DHisto(prefix+"photons"+suffix, processedEvent.getParticleEvent().hasNumberOfPhotons());
			this.myCanvas.fill1DHisto(prefix+"protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons());
			this.myCanvas.fill2DHisto(prefix+"photons vs protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
		}
		
		if (processedEvent.getTrigger_bit(31)){
			suffix = PlotTools.createSuffix("rand trig", plotExtraLegend);
			this.myCanvas.fill1DHisto(prefix+"electrons"+suffix, processedEvent.getParticleEvent().hasNumberOfElectrons());
			this.myCanvas.fill1DHisto(prefix+"photons"+suffix, processedEvent.getParticleEvent().hasNumberOfPhotons());
			this.myCanvas.fill1DHisto(prefix+"protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons());
			this.myCanvas.fill2DHisto(prefix+"photons vs electrons"+suffix, processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
			this.myCanvas.fill2DHisto(prefix+"protons vs electrons"+suffix, processedEvent.getParticleEvent().hasNumberOfElectrons(), processedEvent.getParticleEvent().hasNumberOfProtons());
			this.myCanvas.fill2DHisto(prefix+"photons vs protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
			if (processedEvent.getParticleEvent().hasNumberOfElectrons()>0){
				suffix = PlotTools.createSuffix("with elec", "rand trig", plotExtraLegend);
				this.myCanvas.fill1DHisto(prefix+"photons"+suffix, processedEvent.getParticleEvent().hasNumberOfPhotons());
				this.myCanvas.fill1DHisto(prefix+"protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons());
				this.myCanvas.fill2DHisto(prefix+"photons vs protons"+suffix, processedEvent.getParticleEvent().hasNumberOfProtons(), processedEvent.getParticleEvent().hasNumberOfPhotons());
			}
		}
	}
	
	/**
	 * Create raw plots
	 */
	public void createParticlesPlotsRaw() {
		this.electronPlots.createDefaultHistograms(electronEnergy);
		this.protonPlots.createDefaultHistograms(electronEnergy);
		this.photonPlots.createDefaultHistograms(electronEnergy);		
	}
	
	/**
	 * Fill raw plots
	 * @param processedEvent event to extract particles from
	 */
	public void fillParticlesPlotsRaw(Event processedEvent) {
		
		if (processedEvent.getParticleEvent().hasNumberOfElectrons() > 0) {
			ArrayList<Electron> electrons = processedEvent.getParticleEvent().getElectrons();
			for (Particle electron : electrons) {
				this.electronPlots.fillDefaultHistograms(electron);
			}
		}

		/* ===== PROTON ===== */
		if (processedEvent.getParticleEvent().hasNumberOfProtons() > 0) {
			ArrayList<Proton> protons = processedEvent.getParticleEvent().getProtons();
			for (Particle proton : protons) {
				this.protonPlots.fillDefaultHistograms(proton);
			}
		}
//
		/* ===== PHOTON ===== */
		if (processedEvent.getParticleEvent().hasNumberOfPhotons() > 0) {
			ArrayList<Photon> photons = processedEvent.getParticleEvent().getPhotons();
			for (Particle photon : photons) {
				this.photonPlots.fillDefaultHistograms(photon);
			}
		}
	}
	
	
	/**
	 * Create plots after cuts
	 */
	public void createParticlesPlotsAfterCuts(String tab, String cutDescription){
		this.electronPlots.createDefaultHistograms(electronEnergy, "Electron "+tab, cutDescription);

		this.photonPlots.createDefaultHistograms(electronEnergy, "Photon "+tab, cutDescription);
		
		this.protonPlots.createDefaultHistograms(electronEnergy, "Proton "+tab, cutDescription);	
	}
	
	/**
	 * Fill after cuts
	 * @param particleEvent event to plot
	 */
	public void fillParticlesPlotsAfterCuts(ParticleEvent particleEvent, String tab, String cutDescription){

		if (particleEvent.hasNumberOfElectrons() > 0) {
			ArrayList<Electron> electrons = particleEvent.getElectrons();
			for (Particle electron : electrons) {
				this.electronPlots.fillDefaultHistograms(electron, "Electron "+tab, cutDescription);
			}
		}

		/* ===== PROTON ===== */
		if (particleEvent.hasNumberOfProtons() > 0) {
			ArrayList<Proton> protons = particleEvent.getProtons();
			for (Particle proton : protons) {
				this.protonPlots.fillDefaultHistograms(proton, "Photon "+tab, cutDescription);
			}
		}
//
		/* ===== PHOTON ===== */
		if (particleEvent.hasNumberOfPhotons() > 0) {
			ArrayList<Photon> photons = particleEvent.getPhotons();
			for (Particle photon : photons) {
				this.photonPlots.fillDefaultHistograms(photon, "Proton "+tab, cutDescription);
			}
		}
	}
	
}
