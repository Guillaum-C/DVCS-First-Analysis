package plots.detectorPlots;

import org.clas12.analysisTools.event.central.cvt.CVTEvent;
import org.clas12.analysisTools.event.central.cvt.CVTRecTrack;
import org.clas12.analysisTools.plots.Canvas;

public class CVTPlots{

	private Canvas canvas;
	private String detectorName = "CVT";
	private String detectorTab = "CVT";
	private String detectorMoreTab = "CVT more";
//	private String detectorCorrelationTab = "CVT correlations";
	private String legend = "";
	private int detectorTabNumberOfRows = 3;
	private int detectorTabNumberOfColumns = 4;
	private int detectorMoreTabNumberOfRows = 3;
	private int detectorMoreTabNumberOfColumns = 4;
	
	int thetaBin = 200;
	double thetaMin = 20;
	double thetaMax = 150;

	int phiBin = 200;
	double phiMin = -180;
	double phiMax = 180;

	int pBin = 200;
	double pMin = 0;
	double pMax = 10.6 / 5;
	
	int vzBin = 200;
	double vzMin = -20;
	double vzMax = 20;

	int vtBin = 200;
	double vtMin = -5;
	double vtMax = 20;
	
	int ndfBin = 10;
	double ndfMin = 0;
	double ndfMax = 10;

	/**
	 * Create a CVT tab with CVT plots
	 * 
	 * @param canvas canvas
	 * @param name name used for the tab and the plots
	 */
	public CVTPlots(Canvas canvas, double electronEnergy) {
		super();
		this.canvas = canvas;
//		this.detectorCorrelationTab = tabName + " correlations";
		
		this.pMax = electronEnergy / 5;
		
		createTabs();
		createHistograms();
	}
	
	public CVTPlots(Canvas canvas, String tabName, double electronEnergy) {
		super();
		this.canvas = canvas;
		this.detectorTab = tabName;
		this.detectorMoreTab = tabName + " more";
//		this.detectorCorrelationTab = tabName + " correlations";
		
		this.pMax = electronEnergy / 5;
		
		createTabs();
		createHistograms();
	}
	
	public CVTPlots(Canvas canvas, String tabName, String legend, double electronEnergy) {
		super();
		this.canvas = canvas;
		this.detectorTab = tabName;
		this.detectorMoreTab = tabName + " more";
		this.legend = legend;
//		this.detectorCorrelationTab = tabName + " correlations";
		
		this.pMax = electronEnergy / 5;
		
		createTabs();
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
	public void createTabs() {
		this.getCanvas().addTab(detectorTab, detectorTabNumberOfRows, detectorTabNumberOfColumns);
		this.getCanvas().addTab(detectorMoreTab, detectorMoreTabNumberOfRows, detectorMoreTabNumberOfColumns);
//		this.getCanvas().addTab(detectorCorrelationTab, numberOfRows, numberOfColumns);

	}

	/**
	 * Create CVT histograms
	 */
	public void createHistograms() {
		
		this.getCanvas().create2DHisto(detectorTab, 1, 1, detectorName + "PosThetaPhi", detectorName + " pos #theta vs #phi"+legend, "#phi (°)",
				"#theta (°)", phiBin, phiMin, phiMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 1, 2, detectorName + "PosThetaMomentum", detectorName + " pos P vs #theta"+legend, "#theta (°)",
				"Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 1, detectorName + "PosPhiMomentum", detectorName + " pos P vs #phi"+legend, "#phi (°)",
				"Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 2, detectorName + "PosThetaVertexZ", detectorName + " pos #theta vs Vz"+legend,
				" Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 1, detectorName + "PosPhiVertexZ", detectorName + " pos #phi vs Vz"+legend, " Z-Vertex (cm)",
				"#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 2, detectorName + "PosMomentumVertexZ", detectorName + " pos P vs Vz"+legend,
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);
		
		
		this.getCanvas().create2DHisto(detectorTab, 1, 3, detectorName + "NegThetaPhi", detectorName + " neg #theta vs #phi"+legend, "#phi (°)",
				"#theta (°)", phiBin, phiMin, phiMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 1, 4, detectorName + "NegThetaMomentum", detectorName + " neg P vs #theta"+legend, "#theta (°)",
				"Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 3, detectorName + "NegPhiMomentum", detectorName + " neg P vs #phi"+legend, "#phi (°)",
				"Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 4, detectorName + "NegThetaVertexZ", detectorName + " neg #theta vs Vz"+legend,
				" Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 3, detectorName + "NegPhiVertexZ", detectorName + " neg #phi vs Vz"+legend, " Z-Vertex (cm)",
				"#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 4, detectorName + "NegMomentumVertexZ", detectorName + " neg P vs Vz"+legend,
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);
		
		
		
		this.getCanvas().create1DHisto(detectorMoreTab, 1, 1, detectorName + "PosTheta", detectorName + " pos #theta"+legend, "#theta (°)", thetaBin,
				thetaMin, thetaMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 1, 2, detectorName + "PosPhi", detectorName + " pos #phi"+legend, "#phi (°)", phiBin, phiMin,
				phiMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 2, 1, detectorName + "PosMomentum", detectorName + " pos P"+legend, "Momentum (GeV)", pBin, pMin,
				pMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 2, 2, detectorName + "PosNDF", detectorName + " pos NDF"+legend, "Number of degrees of freedom", ndfBin, ndfMin,
				ndfMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 3, 2, detectorName + "PosNbTracks", detectorName + " pos nb tracks"+legend, "Number of tracks", 10, 0,
				10);
		this.getCanvas().setLogY(detectorMoreTab, 3, 2, true);
		
		this.getCanvas().create1DHisto(detectorMoreTab, 1, 3, detectorName + "NegTheta", detectorName + " neg #theta"+legend, "#theta (°)", thetaBin,
				thetaMin, thetaMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 1, 4, detectorName + "NegPhi", detectorName + " neg #phi"+legend, "#phi (°)", phiBin, phiMin,
				phiMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 2, 3, detectorName + "NegMomentum", detectorName + " neg P"+legend, "Momentum (GeV)", pBin, pMin,
				pMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 2, 4, detectorName + "NegNDF", detectorName + " neg NDF"+legend, "Number of degrees of freedom", ndfBin, ndfMin,
				ndfMax);
		this.getCanvas().create1DHisto(detectorMoreTab, 3, 3, detectorName + "NegNbTracks", detectorName + " neg nb tracks"+legend, "Number of tracks", 10, 0,
				10);
		this.getCanvas().setLogY(detectorMoreTab, 3, 3, true);
		
		this.getCanvas().create1DHisto(detectorMoreTab, 3, 1, detectorName + "NbTracks", detectorName + " total nb tracks"+legend, "Number of tracks (Pos+Neg)", 10, 0,
				10);
		this.getCanvas().setLogY(detectorMoreTab, 3, 1, true);
		this.getCanvas().create1DHisto(detectorMoreTab, 3, 4, detectorName + "Chi2", detectorName + " #chi^2"+legend, "#chi^2", 200, 0, 200);
		this.getCanvas().setLogY(detectorMoreTab, 3, 4, true);
//		
//		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 1, detectorName + "ThetaCVTvsForward", detectorName +" vs electron #theta", "Electron #theta (°)", detectorName+" #theta (°)", thetaBin, 0, 30, thetaBin, thetaMin, thetaMax);
//		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 2, detectorName + "PhiCVTvsForward", detectorName +" vs electron #phi", "Electron #phi (°)", detectorName+" #phi (°)", phiBin, phiMin, phiMax, phiBin, phiMin, phiMax);
//		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 3, detectorName + "MomentumCVTvsForward", detectorName +" vs electron P", "Electron momentum (GeV)", detectorName+" momentum (GeV)", pBin, pMin, 10.6, pBin, pMin, pMax);
//		this.getCanvas().create2DHisto(detectorCorrelationTab, 1, 4, detectorName + "VertexCVTvsForward", detectorName +" vs electron Vz", "Electron Z-Vertex (cm)", detectorName+" Z-Vertex (cm)", vzBin, vzMin, vzMax, vzBin, vzMin, vzMax);
	}

	/**
	 * Fill CVT histograms
	 * 
	 * @param event processed event
	 */
	public void fillHistograms(CVTEvent cvtEvent) {
//	public void fillHistograms(Event event) {
//		CVTEvent cvtEvent = event.getCentralEvent().getCvtEvent();
//		double max = 0;
//		CVTRecTrack highestMomentumTrack = null;	
		
		int nbTrackPos=0;
		int nbTrackNeg=0;
		for (CVTRecTrack cvtTrack : cvtEvent.getCvtTracks()) {			
			if(cvtTrack.getCharge() >0){
				this.getCanvas().fill1DHisto(detectorName+"PosTheta", Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.getCanvas().fill1DHisto(detectorName+"PosPhi", Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.getCanvas().fill1DHisto(detectorName+"PosMomentum", cvtTrack.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"PosThetaPhi", Math.toDegrees(cvtTrack.getMomentum().phi()), Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.getCanvas().fill2DHisto(detectorName+"PosThetaMomentum", Math.toDegrees(cvtTrack.getMomentum().theta()), cvtTrack.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"PosPhiMomentum", Math.toDegrees(cvtTrack.getMomentum().phi()), cvtTrack.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"PosThetaVertexZ", cvtTrack.getVertex().z(), Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.getCanvas().fill2DHisto(detectorName+"PosPhiVertexZ", cvtTrack.getVertex().z(), Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.getCanvas().fill2DHisto(detectorName+"PosMomentumVertexZ", cvtTrack.getVertex().z(), cvtTrack.getMomentum().mag());
				this.getCanvas().fill1DHisto(detectorName+"PosNDF", cvtTrack.getNdf());
				nbTrackPos++;
			}
			if(cvtTrack.getCharge() <0){
				this.getCanvas().fill2DHisto(detectorName+"NegThetaPhi", Math.toDegrees(cvtTrack.getMomentum().phi()), Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.getCanvas().fill2DHisto(detectorName+"NegThetaMomentum", Math.toDegrees(cvtTrack.getMomentum().theta()), cvtTrack.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"NegPhiMomentum", Math.toDegrees(cvtTrack.getMomentum().phi()), cvtTrack.getMomentum().mag());
				this.getCanvas().fill2DHisto(detectorName+"NegThetaVertexZ", cvtTrack.getVertex().z(), Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.getCanvas().fill2DHisto(detectorName+"NegPhiVertexZ", cvtTrack.getVertex().z(), Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.getCanvas().fill2DHisto(detectorName+"NegMomentumVertexZ", cvtTrack.getVertex().z(), cvtTrack.getMomentum().mag());
				this.getCanvas().fill1DHisto(detectorName+"NegTheta", Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.getCanvas().fill1DHisto(detectorName+"NegPhi", Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.getCanvas().fill1DHisto(detectorName+"NegMomentum", cvtTrack.getMomentum().mag());
				this.getCanvas().fill1DHisto(detectorName+"NegNDF", cvtTrack.getNdf());
				nbTrackNeg++;
			}
			this.getCanvas().fill1DHisto(detectorName+"Chi2", cvtTrack.getChi2());
//			if (cvtTrack.getMomentum().mag()>max){
//				max = cvtTrack.getMomentum().mag();
//				highestMomentumTrack = cvtTrack;
//			}
		}
		this.getCanvas().fill1DHisto(detectorName+"NbTracks", cvtEvent.getCvtTracks().size());
		this.getCanvas().fill1DHisto(detectorName+"PosNbTracks", nbTrackPos);
		this.getCanvas().fill1DHisto(detectorName+"NegNbTracks", nbTrackNeg);
//		if (highestMomentumTrack!=null){
//			ParticleEvent particleEvent = event.getParticleEvent();
//			if(particleEvent.hasNumberOfElectrons()>0){
//				for (Electron electron: particleEvent.getElectrons()){
//					if (electron.getThetaDeg()>5){
//						this.getCanvas().fill2DHisto(detectorName + "ThetaCVTvsForward",electron.getThetaDeg(),Math.toDegrees(highestMomentumTrack.getMomentum().theta()));
//						this.getCanvas().fill2DHisto(detectorName + "PhiCVTvsForward",electron.getPhiDeg(),Math.toDegrees(highestMomentumTrack.getMomentum().phi()));
//						this.getCanvas().fill2DHisto(detectorName + "MomentumCVTvsForward",electron.getP(),highestMomentumTrack.getMomentum().mag());
//						this.getCanvas().fill2DHisto(detectorName + "VertexCVTvsForward",electron.getVz(),highestMomentumTrack.getVertex().z());
//					}
//				}
//			}
//		}
	}

}
