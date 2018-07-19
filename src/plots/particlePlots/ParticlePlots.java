package plots.particlePlots;

import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.plots.Canvas;

public class ParticlePlots {

	String plotName;
	boolean alsoPlotNoFT;
	
	int thetaBin = 200;
	double thetaMin = 0;
	double thetaMax = 40;

	int phiBin = 200;
	double phiMin = -180;
	double phiMax = 180;

	int pBin = 200;
	double pMin = 0;
	double pMax = 10.6;

	int ptBin = 200;
	double ptMin = 0;
	double ptMax = 10.6 / 4.;
	
	int vzBin = 200;
	double vzMin = -20;
	double vzMax = 20;

	int vtBin = 200;
	double vtMin = -5;
	double vtMax = 20;

	/**
	 * @param plotName
	 * @param alsoPlotNoFT
	 * @param thetaBin
	 * @param thetaMin
	 * @param thetaMax
	 * @param phiBin
	 * @param phiMin
	 * @param phiMax
	 * @param pBin
	 * @param pMin
	 * @param pMax
	 * @param ptBin
	 * @param ptMin
	 * @param ptMax
	 * @param vzBin
	 * @param vzMin
	 * @param vzMax
	 * @param vtBin
	 * @param vtMin
	 * @param vtMax
	 */
	public ParticlePlots(String plotName, boolean alsoPlotNoFT, int thetaBin, double thetaMin, double thetaMax,
			int phiBin, double phiMin, double phiMax, int pBin, double pMin, double pMax, int ptBin, double ptMin,
			double ptMax, int vzBin, double vzMin, double vzMax, int vtBin, double vtMin, double vtMax) {
		super();
		this.alsoPlotNoFT = alsoPlotNoFT;
		this.plotName = plotName;
		this.thetaBin = thetaBin;
		this.thetaMin = thetaMin;
		this.thetaMax = thetaMax;
		this.phiBin = phiBin;
		this.phiMin = phiMin;
		this.phiMax = phiMax;
		this.pBin = pBin;
		this.pMin = pMin;
		this.pMax = pMax;
		this.ptBin = ptBin;
		this.ptMin = ptMin;
		this.ptMax = ptMax;
		this.vzBin = vzBin;
		this.vzMin = vzMin;
		this.vzMax = vzMax;
		this.vtBin = vtBin;
		this.vtMin = vtMin;
		this.vtMax = vtMax;
	}

	/**
	 * Create plots in the given canvas
	 * @param myCanvas
	 */
	public void createParticlePlots(Canvas myCanvas) {
		String tabSummary = plotName;
		String tabMore = plotName+" more";
		
		if (alsoPlotNoFT){
			myCanvas.addTab(tabSummary, 3, 4);
			myCanvas.addTab(tabMore, 5, 4);
		}else{
			myCanvas.addTab(tabSummary, 3, 2);
			myCanvas.addTab(tabMore, 5, 3);
		}
		
		// With Forward Tagger
		myCanvas.create2DHisto(tabSummary, 1, 1, plotName + "ThetaPhi", plotName + " #theta vs #phi", "#phi (°)",
				"#theta (°)", phiBin, phiMin, phiMax, thetaBin, thetaMin, thetaMax);
		myCanvas.create2DHisto(tabSummary, 2, 1, plotName + "ThetaMomentum", plotName + " P vs #theta", "#theta (°)",
				"Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		myCanvas.create2DHisto(tabSummary, 3, 1, plotName + "PhiMomentum", plotName + " P vs #phi", "#phi (°)",
				"Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		myCanvas.create2DHisto(tabSummary, 1, 2, plotName + "ThetaVertexZ", plotName + " #theta vs Vz",
				" Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin, thetaMin, thetaMax);
		myCanvas.create2DHisto(tabSummary, 2, 2, plotName + "PhiVertexZ", plotName + " #phi vs Vz", " Z-Vertex (cm)",
				"#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		myCanvas.create2DHisto(tabSummary, 3, 2, plotName + "MomentumVertexZ", plotName + " P vs Vz",
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);

		// Without Forward Tagger
		if (alsoPlotNoFT){
		myCanvas.create2DHisto(tabSummary, 1, 3, plotName + "ThetaPhiNoFT", plotName + " #theta vs #phi (no FT)",
				"#phi (°)", "#theta (°)", phiBin, phiMin, phiMax, thetaBin, thetaMin, thetaMax);
		myCanvas.create2DHisto(tabSummary, 2, 3, plotName + "ThetaMomentumNoFT", plotName + " p vs #theta (no FT)",
				"#theta (°)", "Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		myCanvas.create2DHisto(tabSummary, 3, 3, plotName + "PhiMomentumNoFT", plotName + " p vs #phi (no FT)",
				"#phi (°)", "Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		myCanvas.create2DHisto(tabSummary, 1, 4, plotName + "ThetaVertexZNoFT", plotName + " #theta vs Vz (no FT)",
				" Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin, thetaMin, thetaMax);
		myCanvas.create2DHisto(tabSummary, 2, 4, plotName + "PhiVertexZNoFT", plotName + " #phi vs Vz (no FT)",
				" Z-Vertex (cm)", "#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		myCanvas.create2DHisto(tabSummary, 3, 4, plotName + "MomentumVertexZNoFT", plotName + " p vs Vz (no FT)",
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);
		}

		// With Forward Tagger
		myCanvas.create1DHisto(tabMore, 1, 1, plotName + "Theta", plotName + " #theta", "#theta (°)", thetaBin,
				thetaMin, thetaMax);
		myCanvas.create1DHisto(tabMore, 1, 2, plotName + "Phi", plotName + " #phi", "#phi (°)", phiBin, phiMin,
				phiMax);
		myCanvas.create1DHisto(tabMore, 2, 1, plotName + "Momentum", plotName + " P", "Momentum (GeV)", pBin, pMin,
				pMax);
		myCanvas.create1DHisto(tabMore, 2, 2, plotName + "TransverseMomentum", plotName + " Pt",
				"Transverse momentum (GeV)", ptBin, ptMin, ptMax);
		myCanvas.create2DHisto(tabMore, 3, 1, plotName + "ThetaTransverseMomentum", plotName + " Pt vs #theta",
				"#theta (°)", "Transverse momentum (GeV)", thetaBin, thetaMin, thetaMax, ptBin, ptMin, ptMax);
		myCanvas.create2DHisto(tabMore, 3, 2, plotName + "PhiTransverseMomentum", plotName + " Pt vs #phi",
				"#phi (°)", "Tranverse momentum (GeV)", phiBin, phiMin, phiMax, ptBin, ptMin, ptMax);
		myCanvas.create1DHisto(tabMore, 4, 2, plotName + "VertexZ", plotName + " Vz", " Z-Vertex (cm)", vzBin, vzMin,
				vzMax);
		myCanvas.create1DHisto(tabMore, 4, 1, plotName + "VertexT", plotName + " Vt", "Transverse vertex (cm)",
				vtBin, vtMin, vtMax);

		// Without Forward Tagger
		if (alsoPlotNoFT){
		myCanvas.create1DHisto(tabMore, 1, 3, plotName + "ThetaNoFT", plotName + " #theta (no FT)", "#theta (°)",
				thetaBin, thetaMin, thetaMax);
		myCanvas.create1DHisto(tabMore, 1, 4, plotName + "PhiNoFT", plotName + " #phi (no FT)", "#phi (°)", phiBin,
				phiMin, phiMax);
		myCanvas.create1DHisto(tabMore, 2, 3, plotName + "MomentumNoFT", plotName + " P (no FT)", "Momentum (GeV)",
				pBin, pMin, pMax);
		myCanvas.create1DHisto(tabMore, 2, 4, plotName + "TransverseMomentumNoFT", plotName + " Pt (no FT)",
				"Transverse momentum (GeV)", ptBin, ptMin, ptMax);
		myCanvas.create2DHisto(tabMore, 3, 3, plotName + "ThetaTransverseMomentumNoFT",
				plotName + " Pt vs #theta (no FT)", "#theta (°)", "Transverse momentum (GeV)", thetaBin, thetaMin,
				thetaMax, ptBin, ptMin, ptMax);
		myCanvas.create2DHisto(tabMore, 3, 4, plotName + "PhiTransverseMomentumNoFT",
				plotName + " Pt vs #phi (no FT)", "#phi (°)", "Tranverse momentum (GeV)", phiBin, phiMin, phiMax,
				ptBin, ptMin, ptMax);
		myCanvas.create1DHisto(tabMore, 4, 4, plotName + "VertexZNoFT", plotName + " Vz (no FT)", " Z-Vertex (cm)", vzBin,
				vzMin, vzMax);
		myCanvas.create1DHisto(tabMore, 4, 3, plotName + "VertexTNoFT", plotName + " Vt (no FT)", "Transverse vertex (cm)",
				vtBin, vtMin, vtMax);
		}

		// myCanvas.create1DHisto(tabMore, 2, 2, particleName + "ZMomentum",
		// "z-Momentum", "z-Momentum", 100, 0, 10);
		// myCanvas.create2DHisto(tabMore, 3, 2, particleName +
		// "ThetaZMomentum", "Theta vs z-Momentum", "Theta",
		// "z-Momentum", 90, 0, 90, 100, 0, 10);
		// myCanvas.create2DHisto(tabMore, 4, 2, particleName + "PhiZMomentum",
		// "Phi vs z-Momentum", "Phi",
		// "z-Momentum", 360, -180, 180, 100, 0, 10);

		myCanvas.create2DHisto(tabMore, 5, 1, plotName + "THTCCvsTFTOF", "THTCC VS TFTOF", "TFTOF", "THTCC", 100, 100,
				400, 100, 100, 400);
		myCanvas.create2DHisto(tabMore, 5, 2, plotName + "TFTOFFvsTCALO", "TFTOF VS TCALO", "TCALO", "TFTOF", 100, 100,
				400, 100, 100, 400);
		myCanvas.create2DHisto(tabMore, 5, 3, plotName + "TCALOvsTHTCC", "TCALO VS THTCC", "THTCC", "TCALO", 100, 100,
				400, 100, 100, 400);
	}

	/**
	 * Fill plots in the given canvas with the particle
	 * @param myCanvas
	 * @param particle
	 */
	public void fillParticlePlots(Canvas myCanvas, Particle particle) {
		
//		if (particle.hasCentralTrack()==1){
//			if(particle.getCvtRecTrack().getChi2()/particle.getCvtRecTrack().getNdf()<5){
		
		myCanvas.fill2DHisto(plotName + "ThetaPhi", particle.getPhiDeg(), particle.getThetaDeg());
		myCanvas.fill2DHisto(plotName + "ThetaMomentum", particle.getThetaDeg(), particle.getP());
		myCanvas.fill2DHisto(plotName + "PhiMomentum", particle.getPhiDeg(), particle.getP());
		myCanvas.fill2DHisto(plotName + "ThetaVertexZ", particle.getVz(), particle.getThetaDeg());
		myCanvas.fill2DHisto(plotName + "PhiVertexZ", particle.getVz(), particle.getPhiDeg());
		myCanvas.fill2DHisto(plotName + "MomentumVertexZ", particle.getVz(), particle.getP());

		myCanvas.fill1DHisto(plotName + "Theta", particle.getThetaDeg());
		myCanvas.fill1DHisto(plotName + "Phi", particle.getPhiDeg());
		myCanvas.fill1DHisto(plotName + "Momentum", particle.getP());
		myCanvas.fill1DHisto(plotName + "TransverseMomentum", particle.getPt());
		myCanvas.fill2DHisto(plotName + "ThetaTransverseMomentum", particle.getThetaDeg(), particle.getPt());
		myCanvas.fill2DHisto(plotName + "PhiTransverseMomentum", particle.getPhiDeg(), particle.getPt());
		myCanvas.fill1DHisto(plotName + "VertexZ", particle.getVz());
		myCanvas.fill1DHisto(plotName + "VertexT",
				Math.sqrt(Math.pow(particle.getVx(), 2) + Math.pow(particle.getVy(), 2)));
		// myCanvas.fill1DHisto(particleName + "ZMomentum", particle.getPz());
		// myCanvas.fill2DHisto(particleName + "ThetaZMomentum",
		// particle.getThetaDeg(), particle.getPz());
		// myCanvas.fill2DHisto(particleName + "PhiZMomentum",
		// particle.getPhiDeg(), particle.getPz());

		if (alsoPlotNoFT && particle.getThetaDeg() > 5) {
			myCanvas.fill2DHisto(plotName + "ThetaPhiNoFT", particle.getPhiDeg(), particle.getThetaDeg());
			myCanvas.fill2DHisto(plotName + "ThetaMomentumNoFT", particle.getThetaDeg(), particle.getP());
			myCanvas.fill2DHisto(plotName + "PhiMomentumNoFT", particle.getPhiDeg(), particle.getP());
			myCanvas.fill2DHisto(plotName + "ThetaVertexZNoFT", particle.getVz(), particle.getThetaDeg());
			myCanvas.fill2DHisto(plotName + "PhiVertexZNoFT", particle.getVz(), particle.getPhiDeg());
			myCanvas.fill2DHisto(plotName + "MomentumVertexZNoFT", particle.getVz(), particle.getP());

			myCanvas.fill1DHisto(plotName + "ThetaNoFT", particle.getThetaDeg());
			myCanvas.fill1DHisto(plotName + "PhiNoFT", particle.getPhiDeg());
			myCanvas.fill1DHisto(plotName + "MomentumNoFT", particle.getP());
			myCanvas.fill1DHisto(plotName + "TransverseMomentumNoFT", particle.getPt());
			myCanvas.fill2DHisto(plotName + "ThetaTransverseMomentumNoFT", particle.getThetaDeg(),
					particle.getPt());
			myCanvas.fill2DHisto(plotName + "PhiTransverseMomentumNoFT", particle.getPhiDeg(), particle.getPt());
			myCanvas.fill1DHisto(plotName + "VertexZNoFT", particle.getVz());
			myCanvas.fill1DHisto(plotName + "VertexTNoFT",
					Math.sqrt(Math.pow(particle.getVx(), 2) + Math.pow(particle.getVy(), 2)));
		}

		if (particle.hasFTOFClusters() > 0 && particle.hasHTCCClusters() > 0) {
			myCanvas.fill2DHisto(plotName + "THTCCvsTFTOF", particle.getFTOFClusters().get(0).getTime(),
					particle.getHTCCClusters().get(0).getTime());
		}
		if (particle.hasCalorimeterClusters() > 0 && particle.hasFTOFClusters() > 0) {
			myCanvas.fill2DHisto(plotName + "TFTOFFvsTCALO", particle.getCalorimeterRecClusters().get(0).getTime(),
					particle.getFTOFClusters().get(0).getTime());
		}
		if (particle.hasHTCCClusters() > 0 && particle.hasCalorimeterClusters() > 0) {
			myCanvas.fill2DHisto(plotName + "TCALOvsTHTCC", particle.getHTCCClusters().get(0).getTime(),
					particle.getCalorimeterRecClusters().get(0).getTime());
		}
//			}
//		}

	}

}
