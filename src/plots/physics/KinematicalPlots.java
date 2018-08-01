package plots.physics;

import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.plots.Canvas;

public class KinematicalPlots {

	public Canvas myCanvas;
	public String particleName = "Electron";

	int xBBin = 200;
	double xBMin = 0;
	double xBMax = 40;

	int q2Bin = 200;
	double q2Min = -180;
	double q2Max = 180;

	int tBin = 200;
	double tMin = 0;
	double tMax = 10.6;

	int phiBin = 200;
	double phiMin = 0;
	double phiMax = 10.6 / 5.;

	/**
	 * Create an electron plots class
	 * @param canvas canvas to use
	 */
	public KinematicalPlots(Canvas canvas) {
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
		String particleTab = tabName;
		String particleMoreTab = particleTab + " more";
		
		int tabNumberOfRows = 3;
		int tabNumberOfColumns = 4;
		int moreTabNumberOfRows = 4;
		int moreTabNumberOfColumns = 4;

		this.myCanvas.addTab(particleTab, tabNumberOfRows, tabNumberOfColumns);
		this.myCanvas.addTab(particleMoreTab, moreTabNumberOfRows, moreTabNumberOfColumns);

		String prefix = particleName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.create2DHisto(particleTab, 1, 1, prefix + "Q^2 vs xB" + suffix,
				prefix + "Q^2 vs xB" + suffix, "xB", "Q^2 (GeV^2)", xBBin, xBMin, xBMax, q2Bin,
				q2Min, q2Max);
		this.myCanvas.create2DHisto(particleTab, 2, 1, prefix + "t vs xB" + suffix,
				prefix + "t vs xB" + suffix, "xB", "t (GeV^2)", xBBin, xBMin, xBMax, tBin,
				tMin, tMax);
		this.myCanvas.create2DHisto(particleTab, 3, 1, prefix + "#phi vs xB" + suffix, prefix + "#phi vs xB" + suffix,
				"xB", "#phi (°)", xBBin, xBMin, xBMax, phiBin, phiMin, phiMax);
		this.myCanvas.create2DHisto(particleTab, 4, 1, prefix + "Q^2 vs #phi" + suffix,
				prefix + "Q^2 vs #phi" + suffix, "#phi (°)", "Q^2 (GeV^2)", phiBin, phiMin, phiMax, q2Bin,
				q2Min, q2Max);
		this.myCanvas.create2DHisto(particleTab, 1, 2, prefix + "t vs #phi" + suffix,
				prefix + "t vs #phi" + suffix, "#phi (°)", "t (GeV^2)", phiBin, phiMin,
				phiMax, tBin, tMin, tMax);
		this.myCanvas.create2DHisto(particleTab, 2, 2, prefix + "Q^2 vs t" + suffix, prefix + "Q^2 vs t" + suffix,
				"t (GeV^2)", "Q^2 (GeV^2)", tBin, tMin, tMax, q2Bin,
				q2Min, q2Max);
		this.myCanvas.create1DHisto(particleTab, 1, 3, prefix + "Q^2" + suffix,
				prefix + "Q^2" + suffix, "Q^2 (GeV^2)", q2Bin,
				q2Min, q2Max);
		this.myCanvas.create1DHisto(particleTab, 2, 3, prefix + "xB" + suffix,
				prefix + "xB" + suffix, "xB", xBBin, xBMin, xBMax);
		this.myCanvas.create1DHisto(particleTab, 3, 3, prefix + "t" + suffix, prefix + "t" + suffix,
				"t (GeV^2)", tBin, tMin, tMax);
		this.myCanvas.create1DHisto(particleTab, 4, 3, prefix + "#phi" + suffix,
				prefix + "#phi" + suffix, "#phi (°)", phiBin, phiMin, phiMax);
		
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
		
		this.myCanvas.fill2DHisto(prefix + "Q^2 vs xB" + suffix, particle.getPhiDeg(), particle.getThetaDeg());
		this.myCanvas.fill2DHisto(prefix + "t vs xB" + suffix, particle.getThetaDeg(), particle.getP());
		this.myCanvas.fill2DHisto(prefix + "#phi vs xB" + suffix, particle.getPhiDeg(), particle.getP());
		this.myCanvas.fill2DHisto(prefix + "Q^2 vs #phi" + suffix, particle.getVz(), particle.getThetaDeg());
		this.myCanvas.fill2DHisto(prefix + "t vs #phi" + suffix, particle.getVz(), particle.getPhiDeg());
		this.myCanvas.fill2DHisto(prefix + "Q^2 vs t" + suffix, particle.getVz(), particle.getP());

		this.myCanvas.fill1DHisto(prefix + "Q^2" + suffix, particle.getThetaDeg());
		this.myCanvas.fill1DHisto(prefix + "xB" + suffix, particle.getPhiDeg());
		this.myCanvas.fill1DHisto(prefix + "t" + suffix, particle.getP());
		this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, particle.getPt());
	}

	/**
	 * Fill default plots
	 * @param particle particle to use to fill the plots
	 */
	public void fillDefaultHistograms(Particle particle) {
		fillDefaultHistograms(particle,particleName,"");
	}
}