package plots.particlePlots;

import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;
import org.jlab.groot.data.H2F;

public class PositiveNegativeChargesPlots {

		public Canvas myCanvas;
		public String particleName = "ID";

		int pBin = 200;
		double pMin = 0;
		double pMax = 10.6;
		
		int betaBin = 200;
		double betaMin = -0.1;
		double betaMax = 1.1;
		
		H2F test;
		H2F test2;

		/**
		 * Create an electron plots class
		 * @param canvas canvas to use
		 */
		public PositiveNegativeChargesPlots(Canvas canvas) {
			super();
			this.myCanvas = canvas;
		}
		
		/**
		 * Create default plots (a particle tab and a more tab with theta/phi/momentum/vertex default plots)
		 * @param electronEnergy beam energy (for plot scale)
		 * @param tabName name of the tab to create (a "more" tab will be also create)
		 * @param legend fill this to add a legend in parenthesis at the end of the plots title
		 */
		public void createDefaultHistograms(double electronEnergy, String tabName, String legend) {
			
			this.pMax = electronEnergy/2;

			String particleTab = tabName;
			String particleMoreTab = particleTab + " more";
			
			int particleTabNumberOfRows = 3;
			int particleTabNumberOfColumns = 2;
//			int particleMoreTabNumberOfRows = 4;
//			int particleMoreTabNumberOfColumns = 4;

			this.myCanvas.addTab(particleTab, particleTabNumberOfRows, particleTabNumberOfColumns);
//			this.myCanvas.addTab(particleMoreTab, particleMoreTabNumberOfRows, particleMoreTabNumberOfColumns);

			String prefix = particleName + " ";
			String suffix = "";
			if (!legend.equals("")) {
				suffix = " (" + legend + ")";
			}
			
			this.myCanvas.create2DHisto(particleTab, 1, 1, prefix + "Positive charges Beta vs P" + suffix,
					prefix + "Positive charges Beta vs P" + suffix, "P (GeV)", "Beta", pBin, pMin, pMax, betaBin,
					betaMin, betaMax);
			this.myCanvas.setLogZ(particleTab, 1, 1, true);
			this.myCanvas.create2DHisto(particleTab, 1, 2, prefix + "Negative charges Beta vs P" + suffix,
					prefix + "Negative charges Beta vs P" + suffix, "P (GeV)", "Beta", pBin, pMin, pMax, betaBin,
					betaMin, betaMax);
			this.myCanvas.setLogZ(particleTab, 1, 2, true);
			this.myCanvas.create2DHisto(particleTab, 2, 1, prefix + "Positive Central charges Beta vs P" + suffix,
					prefix + "Positive Central charges Beta vs P" + suffix, "P (GeV)", "Beta", pBin, pMin, pMax, betaBin,
					betaMin, betaMax);
			this.myCanvas.setLogZ(particleTab, 2, 1, true);
			this.myCanvas.create2DHisto(particleTab, 2, 2, prefix + "Positive Forward charges Beta vs P" + suffix,
					prefix + "Positive Forward charges Beta vs P" + suffix, "P (GeV)", "Beta", pBin, pMin, pMax, betaBin,
					betaMin, betaMax);
			this.myCanvas.setLogZ(particleTab, 2, 2, true);
			this.myCanvas.create2DHisto(particleTab, 3, 1, prefix + "Protons Beta vs P" + suffix,
					prefix + "Protons Beta vs P" + suffix, "P (GeV)", "Beta", pBin, pMin, pMax, betaBin,
					betaMin, betaMax);
			this.myCanvas.setLogZ(particleTab, 3, 1, true);
			this.myCanvas.create2DHisto(particleTab, 3, 2, prefix + "Electrons Beta vs P" + suffix,
					prefix + "Electrons Beta vs P" + suffix, "P (GeV)", "Beta", pBin, pMin, pMax, betaBin,
					betaMin, betaMax);
			this.myCanvas.setLogZ(particleTab, 3, 2, true);
		}

		/**
		 * Create default plots (a particle tab and a more tab with theta/phi/momentum/vertex default plots). This tab is titled by the name of the particle so only one tab can be created using this method.
		 * @param electronEnergy beam energy (for plot scale)
		 */
		public void createDefaultHistograms(double electronEnergy) {
			createDefaultHistograms(electronEnergy, particleName, "");
		}
		
		/**
		 * Fill default plots
		 * @param particle particle to use to fill the plots
		 * @param tabName name of the tab to fill
		 * @param legend to fill plots created with a legend
		 */
		public void fillDefaultHistograms(Particle particle,String tabName, String legend) {

			String prefix = particleName + " ";
			String suffix = "";
			if (!legend.equals("")) {
				suffix = " (" + legend + ")";
			}
			if (particle.getCharge()>0){
				this.myCanvas.fill2DHisto(prefix + "Positive charges Beta vs P" + suffix, particle.getMomentum().mag(), particle.getBeta());
				if (particle.hasCentralTrack()>0){
					this.myCanvas.fill2DHisto(prefix + "Positive Central charges Beta vs P" + suffix, particle.getMomentum().mag(), particle.getBeta());
				}
				if (particle.hasForwardTrack()>0){
					this.myCanvas.fill2DHisto(prefix + "Positive Forward charges Beta vs P" + suffix, particle.getMomentum().mag(), particle.getBeta());
				}
			}
			if (particle.getCharge()<0){
				this.myCanvas.fill2DHisto(prefix + "Negative charges Beta vs P" + suffix, particle.getMomentum().mag(), particle.getBeta());
			}
			if (particle instanceof Proton){
				this.myCanvas.fill2DHisto(prefix + "Protons Beta vs P" + suffix, particle.getMomentum().mag(), particle.getBeta());
			}
			if (particle instanceof Electron){
				this.myCanvas.fill2DHisto(prefix + "Electrons Beta vs P" + suffix, particle.getMomentum().mag(), particle.getBeta());
				
			}
		}

		/**
		 * Fill default plots
		 * @param particle particle to use to fill the plots
		 */
		public void fillDefaultHistograms(Particle particle) {
			fillDefaultHistograms(particle,particleName,"");
		}

}
