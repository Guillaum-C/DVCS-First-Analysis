package plots.physics;

import org.clas12.analysisTools.constants.BeamConstants;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;

import physics.ComputePhysicsParameters;

public class KinematicalPlots {

	public Canvas myCanvas;
	double electronEnergy;
	public String particleName = "Kinematic";

	int xBBin = 100;
	double xBMin = 0;
	double xBMax = 1;

	int q2Bin = 100;
	double q2Min = 0;
	double q2Max = 10.6;

	int tBin = 100;
	double tMin = -4;
	double tMax = 4;

	int phiBin = 100;
	double phiMin = 0;
	double phiMax = 360;

	/**
	 * Create an electron plots class
	 * @param canvas canvas to use
	 */
	public KinematicalPlots(Canvas canvas, double electronEnergy) {
		super();
		this.myCanvas = canvas;
		this.electronEnergy = electronEnergy;
	}
	
	/**
	 * Create default plots (a particle tab and a more tab with theta/phi/momentum/vertex default plots)
	 * @param electronEnergy beam energy (for plot scale)
	 * @param tabName name of the tab to create (a "more" tab will be also create)
	 * @param legend fill this to add a legend in parenthesis at the end of the plots title
	 */
	public void createDefaultHistograms(String tabName, String legend) {
		
		q2Max = this.electronEnergy;
		
		String particleTab = tabName;
//		String particleMoreTab = particleTab + " more";
		
		int tabNumberOfRows = 3;
		int tabNumberOfColumns = 4;
//		int moreTabNumberOfRows = 4;
//		int moreTabNumberOfColumns = 4;
		
		

		this.myCanvas.addTab(particleTab, tabNumberOfRows, tabNumberOfColumns);
//		this.myCanvas.addTab(particleMoreTab, moreTabNumberOfRows, moreTabNumberOfColumns);

		String prefix = particleName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		
		this.myCanvas.create1DHisto(particleTab, 1, 1, prefix + "Q^2" + suffix,
				prefix + "Q^2" + suffix, "Q^2 (GeV^2)", q2Bin,
				q2Min, q2Max);
		this.myCanvas.create1DHisto(particleTab, 1, 2, prefix + "xB" + suffix,
				prefix + "xB" + suffix, "xB", xBBin, xBMin, xBMax);
		this.myCanvas.create1DHisto(particleTab, 1, 3, prefix + "t" + suffix, prefix + "t" + suffix,
				"t (GeV^2)", tBin, tMin, tMax);
		this.myCanvas.create1DHisto(particleTab, 1, 4, prefix + "#phi" + suffix,
				prefix + "#phi" + suffix, "#phi (°)", phiBin, phiMin, phiMax);
		
		this.myCanvas.create2DHisto(particleTab, 2, 1, prefix + "Q^2 vs xB" + suffix,
				prefix + "Q^2 vs xB" + suffix, "xB", "Q^2 (GeV^2)", xBBin, xBMin, xBMax, q2Bin,
				q2Min, q2Max);
		this.myCanvas.create2DHisto(particleTab, 2, 2, prefix + "t vs xB" + suffix,
				prefix + "t vs xB" + suffix, "xB", "t (GeV^2)", xBBin, xBMin, xBMax, tBin,
				tMin, tMax);
		this.myCanvas.create2DHisto(particleTab, 2, 3, prefix + "#phi vs xB" + suffix, prefix + "#phi vs xB" + suffix,
				"xB", "#phi (°)", xBBin, xBMin, xBMax, phiBin, phiMin, phiMax);
		this.myCanvas.create2DHisto(particleTab, 2, 4, prefix + "Q^2 vs #phi" + suffix,
				prefix + "Q^2 vs #phi" + suffix, "#phi (°)", "Q^2 (GeV^2)", phiBin, phiMin, phiMax, q2Bin,
				q2Min, q2Max);
		this.myCanvas.create2DHisto(particleTab, 3, 1, prefix + "t vs #phi" + suffix,
				prefix + "t vs #phi" + suffix, "#phi (°)", "t (GeV^2)", phiBin, phiMin,
				phiMax, tBin, tMin, tMax);
		this.myCanvas.create2DHisto(particleTab, 3, 2, prefix + "Q^2 vs t" + suffix, prefix + "Q^2 vs t" + suffix,
				"t (GeV^2)", "Q^2 (GeV^2)", tBin, tMin, tMax, q2Bin,
				q2Min, q2Max);
		this.myCanvas.create2DHisto(particleTab, 3, 3, prefix + "#phi_g vs #phi_p" + suffix,
				prefix + "#phi_g vs #phi_p" + suffix, "#phi with proton (°)", "#phi with photon (°)", phiBin, phiMin,
				phiMax, phiBin, phiMin, phiMax);
		
		
	}

	/**
	 * Create default plots (a particle tab and a more tab with theta/phi/momentum/vertex default plots). This tab is titled by the name of the particle so only one tab can be created using this method.
	 * @param electronEnergy beam energy (for plot scale)
	 */
	public void createDefaultHistograms() {
		createDefaultHistograms(particleName, "");
	}
	
	/**
	 * Fill default plots
	 * @param particle particle to use to fill the plots
	 * @param tabName name of the tab to fill
	 * @param legend to fill plots created with a legend
	 */
	public void fillDefaultHistograms(Electron electron, Proton proton, Photon photon, String tabName, String legend) {

		String prefix = particleName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		
		LorentzVector electronI = new LorentzVector();
		electronI.setPxPyPzM(0, 0, this.electronEnergy, Electron.mass);
		
		double q2 = ComputePhysicsParameters.computeQ2(electronI, electron);
		double xB = ComputePhysicsParameters.computeXB(electronI, electron);
		double t = ComputePhysicsParameters.computeT(proton);
		double phi = ComputePhysicsParameters.computePhiDeg(electronI, electron, photon);
		double phiP = ComputePhysicsParameters.computePhiDegWithProton(electronI, electron, proton);
		
		this.myCanvas.fill1DHisto(prefix + "Q^2" + suffix, q2);
		this.myCanvas.fill1DHisto(prefix + "xB" + suffix, xB);
		this.myCanvas.fill1DHisto(prefix + "t" + suffix, t);
		this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, phi);
		
		this.myCanvas.fill2DHisto(prefix + "Q^2 vs xB" + suffix, xB, q2);
		this.myCanvas.fill2DHisto(prefix + "t vs xB" + suffix, xB, t);
		this.myCanvas.fill2DHisto(prefix + "#phi vs xB" + suffix, xB, phi);
		this.myCanvas.fill2DHisto(prefix + "Q^2 vs #phi" + suffix, phi, q2);
		this.myCanvas.fill2DHisto(prefix + "t vs #phi" + suffix, phi, t);
		this.myCanvas.fill2DHisto(prefix + "Q^2 vs t" + suffix, t, q2);
		this.myCanvas.fill2DHisto(prefix + "#phi_g vs #phi_p" + suffix, phiP, phi);
		
	}

	/**
	 * Fill default plots
	 * @param particle particle to use to fill the plots
	 */
	public void fillDefaultHistograms(Electron electron, Proton proton, Photon photon, double beamEnergy) {
		fillDefaultHistograms(electron, proton, photon, particleName, "");
	}
}