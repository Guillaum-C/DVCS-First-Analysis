package plots.particlePlots;

import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.plots.Canvas;

public class ProtonPlots {

	public Canvas myCanvas;
	public String particleName = "Proton";
	
	int thetaBin = 200;
	double thetaMin = 0;
	double thetaMax = 180;

	int phiBin = 200;
	double phiMin = -180;
	double phiMax = 180;

	int pBin = 200;
	double pMin = 0;
	double pMax = 10.6 / 3;

	int ptBin = 200;
	double ptMin = 0;
	double ptMax = 10.6 / 5.;

	int vzCtrBin = 200;
	double vzCtrMin = -20;
	double vzCtrMax = 20;
	
	int vzFwdBin = 200;
	double vzFwdMin = -20;
	double vzFwdMax = 20;

	int vtCtrBin = 200;
	double vtCtrMin = -0.005;
	double vtCtrMax = 0.02;
	
	int vtFwdBin = 200;
	double vtFwdMin = -5;
	double vtFwdMax = 20;

	/**
	 * Create a proton plots class
	 * @param canvas
	 */
	public ProtonPlots(Canvas canvas) {
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
		this.pMax = electronEnergy / 3;
		this.ptMax = electronEnergy / 5.;

		String particleTab = tabName;
		String particleMoreTab = particleTab + " more";
		
		int particleTabNumberOfRows = 3;
		int particleTabNumberOfColumns = 2;
		int particleMoreTabNumberOfRows = 4;
		int particleMoreTabNumberOfColumns = 6;
		this.myCanvas.addTab(particleTab, particleTabNumberOfRows, particleTabNumberOfColumns);
		this.myCanvas.addTab(particleMoreTab, particleMoreTabNumberOfRows, particleMoreTabNumberOfColumns);

		String prefix = particleName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.create2DHisto(particleTab, 1, 1, prefix + "#theta vs #phi" + suffix,
				prefix + "#theta vs #phi" + suffix, "#phi (°)", "#theta (°)", phiBin, phiMin, phiMax, thetaBin,
				thetaMin, thetaMax);
		this.myCanvas.create2DHisto(particleTab, 2, 1, prefix + "P vs #theta" + suffix,
				prefix + "P vs #theta" + suffix, "#theta (°)", "Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin,
				pMin, pMax);
		this.myCanvas.create2DHisto(particleTab, 3, 1, prefix + "P vs #phi" + suffix, prefix + "P vs #phi" + suffix,
				"#phi (°)", "Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.myCanvas.create2DHisto(particleTab, 1, 2, prefix + "#theta vs Vz" + suffix,
				prefix + "#theta vs Vz" + suffix, " Z-Vertex (cm)", "#theta (°)", vzCtrBin, vzCtrMin, vzCtrMax, thetaBin,
				thetaMin, thetaMax);
		this.myCanvas.create2DHisto(particleTab, 2, 2, prefix + "#phi vs Vz" + suffix,
				prefix + "#phi vs Vz" + suffix, " Z-Vertex (cm)", "#phi (°)", vzCtrBin, vzCtrMin, vzCtrMax, phiBin, phiMin,
				phiMax);
		this.myCanvas.create2DHisto(particleTab, 3, 2, prefix + "P vs Vz" + suffix, prefix + "P vs Vz" + suffix,
				" Z-Vertex (cm)", "Momentum (GeV)", vzCtrBin, vzCtrMin, vzCtrMax, pBin, pMin, pMax);

		prefix = particleName + " ";
		suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.create1DHisto(particleMoreTab, 1, 1, prefix + "#theta" + suffix, prefix + "#theta" + suffix,
				"#theta (°)", thetaBin, thetaMin, thetaMax);
		this.myCanvas.create1DHisto(particleMoreTab, 1, 2, prefix + "#phi" + suffix, prefix + "#phi" + suffix,
				"#phi (°)", phiBin, phiMin, phiMax);
		this.myCanvas.create1DHisto(particleMoreTab, 2, 1, prefix + "P" + suffix, prefix + "P" + suffix,
				"Momentum (GeV)", pBin, pMin, pMax);
		this.myCanvas.create1DHisto(particleMoreTab, 2, 2, prefix + "Pt" + suffix, prefix + "Pt" + suffix,
				"Transverse momentum (GeV)", ptBin, ptMin, ptMax);
		this.myCanvas.create2DHisto(particleMoreTab, 3, 1, prefix + "Pt vs #theta" + suffix,
				prefix + "Pt vs #theta" + suffix, "#theta (°)", "Transverse momentum (GeV)", thetaBin, thetaMin,
				thetaMax, ptBin, ptMin, ptMax);
		this.myCanvas.create2DHisto(particleMoreTab, 3, 2, prefix + "Pt vs #phi" + suffix,
				prefix + "Pt vs #phi" + suffix, "#phi (°)", "Tranverse momentum (GeV)", phiBin, phiMin, phiMax, ptBin,
				ptMin, ptMax);
		this.myCanvas.create1DHisto(particleMoreTab, 4, 1, prefix + "Vz" + suffix, prefix + "Vz" + suffix,
				" Z-Vertex (cm)", vzCtrBin, vzCtrMin, vzCtrMax);
		this.myCanvas.create1DHisto(particleMoreTab, 4, 2, prefix + "Vt" + suffix, prefix + "Vt" + suffix,
				"Transverse vertex (cm)", vtCtrBin, vtCtrMin, vtCtrMax);

		// In the central
		prefix = particleName + " ";
		suffix = " (in Central)";
		if (!legend.equals("")) {
			suffix = " (in Central, " + legend + ")";
		}
		this.myCanvas.create1DHisto(particleMoreTab, 1, 3, prefix + "#theta" + suffix, prefix + "#theta" + suffix,
				"#theta (°)", thetaBin, thetaMin, thetaMax);
		this.myCanvas.create1DHisto(particleMoreTab, 1, 4, prefix + "#phi" + suffix, prefix + "#phi" + suffix,
				"#phi (°)", phiBin, phiMin, phiMax);
		this.myCanvas.create1DHisto(particleMoreTab, 2, 3, prefix + "P" + suffix, prefix + "P" + suffix,
				"Momentum (GeV)", pBin, pMin, pMax);
		this.myCanvas.create1DHisto(particleMoreTab, 2, 4, prefix + "Pt" + suffix, prefix + "Pt" + suffix,
				"Transverse momentum (GeV)", ptBin, ptMin, ptMax);
		this.myCanvas.create2DHisto(particleMoreTab, 3, 3, prefix + "Pt vs #theta" + suffix,
				prefix + "Pt vs #theta" + suffix, "#theta (°)", "Transverse momentum (GeV)", thetaBin, thetaMin,
				thetaMax, ptBin, ptMin, ptMax);
		this.myCanvas.create2DHisto(particleMoreTab, 3, 4, prefix + "Pt vs #phi" + suffix,
				prefix + "Pt vs #phi" + suffix, "#phi (°)", "Tranverse momentum (GeV)", phiBin, phiMin, phiMax, ptBin,
				ptMin, ptMax);
		this.myCanvas.create1DHisto(particleMoreTab, 4, 3, prefix + "Vz" + suffix, prefix + "Vz" + suffix,
				" Z-Vertex (cm)", vzCtrBin, vzCtrMin, vzCtrMax);
		this.myCanvas.create1DHisto(particleMoreTab, 4, 4, prefix + "Vt" + suffix, prefix + "Vt" + suffix,
				"Transverse vertex (cm)", vtCtrBin, vtCtrMin, vtCtrMax);

		// In the forward
		prefix = particleName + " ";
		suffix = " (in Forward)";
		if (!legend.equals("")) {
			suffix = " (in Forward, " + legend + ")";
		}
		this.myCanvas.create1DHisto(particleMoreTab, 1, 5, prefix + "#theta" + suffix, prefix + "#theta" + suffix,
				"#theta (°)", thetaBin, thetaMin, thetaMax);
		this.myCanvas.create1DHisto(particleMoreTab, 1, 6, prefix + "#phi" + suffix, prefix + "#phi" + suffix,
				"#phi (°)", phiBin, phiMin, phiMax);
		this.myCanvas.create1DHisto(particleMoreTab, 2, 5, prefix + "P" + suffix, prefix + "P" + suffix,
				"Momentum (GeV)", pBin, pMin, pMax);
		this.myCanvas.create1DHisto(particleMoreTab, 2, 6, prefix + "Pt" + suffix, prefix + "Pt" + suffix,
				"Transverse momentum (GeV)", ptBin, ptMin, ptMax);
		this.myCanvas.create2DHisto(particleMoreTab, 3, 5, prefix + "Pt vs #theta" + suffix,
				prefix + "Pt vs #theta" + suffix, "#theta (°)", "Transverse momentum (GeV)", thetaBin, thetaMin,
				thetaMax, ptBin, ptMin, ptMax);
		this.myCanvas.create2DHisto(particleMoreTab, 3, 6, prefix + "Pt vs #phi" + suffix,
				prefix + "Pt vs #phi" + suffix, "#phi (°)", "Tranverse momentum (GeV)", phiBin, phiMin, phiMax, ptBin,
				ptMin, ptMax);
		this.myCanvas.create1DHisto(particleMoreTab, 4, 5, prefix + "Vz" + suffix, prefix + "Vz" + suffix,
				" Z-Vertex (cm)", vzFwdBin, vzFwdMin, vzFwdMax);
		this.myCanvas.create1DHisto(particleMoreTab, 4, 6, prefix + "Vt" + suffix, prefix + "Vt" + suffix,
				"Transverse vertex (cm)", vtFwdBin, vtFwdMin, vtFwdMax);
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
	public void fillDefaultHistograms(Particle particle, String tabName, String legend) {

		String prefix = particleName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.fill2DHisto(prefix + "#theta vs #phi" + suffix, particle.getPhiDeg(), particle.getThetaDeg());
		this.myCanvas.fill2DHisto(prefix + "P vs #theta" + suffix, particle.getThetaDeg(), particle.getP());
		this.myCanvas.fill2DHisto(prefix + "P vs #phi" + suffix, particle.getPhiDeg(), particle.getP());
		this.myCanvas.fill2DHisto(prefix + "#theta vs Vz" + suffix, particle.getVz(), particle.getThetaDeg());
		this.myCanvas.fill2DHisto(prefix + "#phi vs Vz" + suffix, particle.getVz(), particle.getPhiDeg());
		this.myCanvas.fill2DHisto(prefix + "P vs Vz" + suffix, particle.getVz(), particle.getP());

		prefix = particleName + " ";
		suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.fill1DHisto(prefix + "#theta" + suffix, particle.getThetaDeg());
		this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, particle.getPhiDeg());
		this.myCanvas.fill1DHisto(prefix + "P" + suffix, particle.getP());
		this.myCanvas.fill1DHisto(prefix + "Pt" + suffix, particle.getPt());
		this.myCanvas.fill2DHisto(prefix + "Pt vs #theta" + suffix, particle.getThetaDeg(),
				particle.getPt());
		this.myCanvas.fill2DHisto(prefix + "Pt vs #phi" + suffix, particle.getPhiDeg(), particle.getPt());
		this.myCanvas.fill1DHisto(prefix + "Vz" + suffix, particle.getVz());
		this.myCanvas.fill1DHisto(prefix + "Vt" + suffix,
				Math.sqrt(Math.pow(particle.getVx(), 2) + Math.pow(particle.getVy(), 2)));
		
		if (particle.hasCentralTrack()>0){
			prefix = particleName + " ";
			suffix = " (in Central)";
			if (!legend.equals("")) {
				suffix = " (in Central, " + legend + ")";
			}
			this.myCanvas.fill1DHisto(prefix + "#theta" + suffix, particle.getThetaDeg());
			this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, particle.getPhiDeg());
			this.myCanvas.fill1DHisto(prefix + "P" + suffix, particle.getP());
			this.myCanvas.fill1DHisto(prefix + "Pt" + suffix, particle.getPt());
			this.myCanvas.fill2DHisto(prefix + "Pt vs #theta" + suffix, particle.getThetaDeg(),
				particle.getPt());
			this.myCanvas.fill2DHisto(prefix + "Pt vs #phi" + suffix, particle.getPhiDeg(), particle.getPt());
			this.myCanvas.fill1DHisto(prefix + "Vz" + suffix, particle.getVz());
			this.myCanvas.fill1DHisto(prefix + "Vt" + suffix,
				Math.sqrt(Math.pow(particle.getVx(), 2) + Math.pow(particle.getVy(), 2)));
		}
		
		if (!(particle.hasCentralTrack()>0)){
			prefix = particleName + " ";
			suffix = " (in Forward)";
			if (!legend.equals("")) {
				suffix = " (in Forward, " + legend + ")";
			}
			this.myCanvas.fill1DHisto(prefix + "#theta" + suffix, particle.getThetaDeg());
			this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, particle.getPhiDeg());
			this.myCanvas.fill1DHisto(prefix + "P" + suffix, particle.getP());
			this.myCanvas.fill1DHisto(prefix + "Pt" + suffix, particle.getPt());
			this.myCanvas.fill2DHisto(prefix + "Pt vs #theta" + suffix, particle.getThetaDeg(),
				particle.getPt());
			this.myCanvas.fill2DHisto(prefix + "Pt vs #phi" + suffix, particle.getPhiDeg(), particle.getPt());
			this.myCanvas.fill1DHisto(prefix + "Vz" + suffix, particle.getVz());
			this.myCanvas.fill1DHisto(prefix + "Vt" + suffix,
				Math.sqrt(Math.pow(particle.getVx(), 2) + Math.pow(particle.getVy(), 2)));
		}

//		if (particle.hasFTOFClusters() > 0 && particle.hasHTCCClusters() > 0) {
//			this.myCanvas.fill2DHisto(particleName + "THTCCvsTFTOF", particle.getFTOFClusters().get(0).getTime(),
//					particle.getHTCCClusters().get(0).getTime());
//		}
//		if (particle.hasCalorimeterClusters() > 0 && particle.hasFTOFClusters() > 0) {
//			this.myCanvas.fill2DHisto(particleName + "TFTOFFvsTCALO",
//					particle.getCalorimeterRecClusters().get(0).getTime(), particle.getFTOFClusters().get(0).getTime());
//		}
//		if (particle.hasHTCCClusters() > 0 && particle.hasCalorimeterClusters() > 0) {
//			this.myCanvas.fill2DHisto(particleName + "TCALOvsTHTCC", particle.getHTCCClusters().get(0).getTime(),
//					particle.getCalorimeterRecClusters().get(0).getTime());
//		}

	}

	/**
	 * Fill default plots
	 * @param particle particle to use to fill the plots
	 */
	public void fillDefaultHistograms(Particle particle) {
		fillDefaultHistograms(particle,particleName,"");
	}
}
