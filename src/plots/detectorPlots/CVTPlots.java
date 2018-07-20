package plots.detectorPlots;

import org.clas12.analysisTools.event.central.cvt.CVTEvent;
import org.clas12.analysisTools.event.central.cvt.CVTRecTrack;
import org.clas12.analysisTools.plots.Canvas;

public class CVTPlots {

	private Canvas myCanvas;
	private String detectorName = "CVT";

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
	double vtMin = -0.01;
	double vtMax = 0.01;

	int chi2Bin = 200;
	double chi2Min = 0;
	double chi2Max = 200;
	
	int ndfBin = 10;
	double ndfMin = 0;
	double ndfMax = 10;
	
	int nbTrackBin = 10;
	double nbTrackMin = 0;
	double nbTrackMax = 10;

	/**
	 * Create a CVT plot class
	 * @param canvas canvas to use
	 */
	public CVTPlots(Canvas canvas) {
		super();
		this.myCanvas = canvas;

	}

	/**
	 * Create default plots
	 * @param electronEnergy beam energy (for plot scale)
	 * @param tabName name of the tab to create (a "more" tab will be also create)
	 * @param legend fill this to add a legend in parenthesis at the end of the plots title
	 */
	public void createDefaultHistograms(double electronEnergy, String tabName, String legend) {

		this.pMax = electronEnergy / 5;

		String detectorTab = tabName;
		String detectorMoreTab = detectorTab + " more";

		int detectorTabNumberOfRows = 3;
		int detectorTabNumberOfColumns = 4;
		int detectorMoreTabNumberOfRows = 5;
		int detectorMoreTabNumberOfColumns = 4;

		this.myCanvas.addTab(detectorTab, detectorTabNumberOfRows, detectorTabNumberOfColumns);
		this.myCanvas.addTab(detectorMoreTab, detectorMoreTabNumberOfRows, detectorMoreTabNumberOfColumns);

		String prefix = detectorName + " ";
		String suffix = " (pos)";
		if (!legend.equals("")) {
			suffix = " (pos, " + legend + ")";
		}
		this.myCanvas.create2DHisto(detectorTab, 1, 1, prefix + "#theta vs #phi" + suffix,
				prefix + "#theta vs #phi" + suffix, "#phi (°)", "#theta (°)", phiBin, phiMin, phiMax, thetaBin,
				thetaMin, thetaMax);
		this.myCanvas.create2DHisto(detectorTab, 1, 2, prefix + "P vs #theta" + suffix, prefix + "P vs #theta" + suffix,
				"#theta (°)", "Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		this.myCanvas.create2DHisto(detectorTab, 2, 1, prefix + "P vs #phi" + suffix, prefix + "P vs #phi" + suffix,
				"#phi (°)", "Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.myCanvas.create2DHisto(detectorTab, 2, 2, prefix + "#theta vs Vz" + suffix,
				prefix + "#theta vs Vz" + suffix, " Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin,
				thetaMin, thetaMax);
		this.myCanvas.create2DHisto(detectorTab, 3, 1, prefix + "#phi vs Vz" + suffix, prefix + "#phi vs Vz" + suffix,
				" Z-Vertex (cm)", "#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		this.myCanvas.create2DHisto(detectorTab, 3, 2, prefix + "P vs Vz" + suffix, prefix + "P vs Vz" + suffix,
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);

		prefix = detectorName + " ";
		suffix = " (neg)";
		if (!legend.equals("")) {
			suffix = " (neg, " + legend + ")";
		}
		this.myCanvas.create2DHisto(detectorTab, 1, 3, prefix + "#theta vs #phi" + suffix,
				prefix + "#theta vs #phi" + suffix, "#phi (°)", "#theta (°)", phiBin, phiMin, phiMax, thetaBin,
				thetaMin, thetaMax);
		this.myCanvas.create2DHisto(detectorTab, 1, 4, prefix + "P vs #theta" + suffix, prefix + "P vs #theta" + suffix,
				"#theta (°)", "Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		this.myCanvas.create2DHisto(detectorTab, 2, 3, prefix + "P vs #phi" + suffix, prefix + "P vs #phi" + suffix,
				"#phi (°)", "Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.myCanvas.create2DHisto(detectorTab, 2, 4, prefix + "#theta vs Vz" + suffix,
				prefix + "#theta vs Vz" + suffix, " Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin,
				thetaMin, thetaMax);
		this.myCanvas.create2DHisto(detectorTab, 3, 3, prefix + "#phi vs Vz" + suffix, prefix + "#phi vs Vz" + suffix,
				" Z-Vertex (cm)", "#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		this.myCanvas.create2DHisto(detectorTab, 3, 4, prefix + "P vs Vz" + suffix, prefix + "P vs Vz" + suffix,
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);

		prefix = detectorName + " ";
		suffix = " (pos)";
		if (!legend.equals("")) {
			suffix = " (pos, " + legend + ")";
		}
		this.myCanvas.create1DHisto(detectorMoreTab, 1, 1, prefix + "#theta" + suffix, prefix + "#theta" + suffix,
				"#theta (°)", thetaBin, thetaMin, thetaMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 1, 2, prefix + "#phi" + suffix, prefix + "#phi" + suffix,
				"#phi (°)", phiBin, phiMin, phiMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 2, 1, prefix + "P" + suffix, prefix + "P" + suffix,
				"Momentum (GeV)", pBin, pMin, pMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 2, 2, prefix + "NDF" + suffix, prefix + "NDF" + suffix,
				"Number of degrees of freedom", ndfBin, ndfMin, ndfMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 3, 1, prefix + "Vx" + suffix, prefix + "Vx" + suffix,
				"X-Vertex (cm)", vtBin, vtMin, vtMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 3, 2, prefix + "Vy" + suffix, prefix + "Vy" + suffix,
				"Y-Vertex (cm)", vtBin, vtMin, vtMax);
		this.myCanvas.create2DHisto(detectorMoreTab, 4, 1, prefix + "Vy vs Vx" + suffix, prefix + "Vy vs Vx" + suffix,
				"X-Vertex (cm)", "Y-Vertex (cm)", vtBin, vtMin, vtMax, vtBin, vtMin, vtMax);
		this.myCanvas.setLogZ(detectorMoreTab, 4, 1, true);
		this.myCanvas.create1DHisto(detectorMoreTab, 4, 2, prefix + "Vz" + suffix, prefix + "Vz" + suffix,
				"Z-Vertex (cm)", vzBin, vzMin, vzMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 5, 2, prefix + "nb tracks" + suffix, prefix + "nb tracks" + suffix,
				"Number of tracks", nbTrackBin, nbTrackMin, nbTrackMax);
		this.myCanvas.setLogY(detectorMoreTab, 5, 2, true);

		prefix = detectorName + " ";
		suffix = " (neg)";
		if (!legend.equals("")) {
			suffix = " (neg, " + legend + ")";
		}
		this.myCanvas.create1DHisto(detectorMoreTab, 1, 3, prefix + "#theta" + suffix, prefix + "#theta" + suffix,
				"#theta (°)", thetaBin, thetaMin, thetaMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 1, 4, prefix + "#phi" + suffix, prefix + "#phi" + suffix,
				"#phi (°)", phiBin, phiMin, phiMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 2, 3, prefix + "P" + suffix, prefix + "P" + suffix,
				"Momentum (GeV)", pBin, pMin, pMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 2, 4, prefix + "NDF" + suffix, prefix + "NDF" + suffix,
				"Number of degrees of freedom", ndfBin, ndfMin, ndfMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 3, 3, prefix + "Vx" + suffix, prefix + "Vx" + suffix,
				"X-Vertex (cm)", vtBin, vtMin, vtMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 3, 4, prefix + "Vy" + suffix, prefix + "Vy" + suffix,
				"Y-Vertex (cm)", vtBin, vtMin, vtMax);
		this.myCanvas.create2DHisto(detectorMoreTab, 4, 3, prefix + "Vy vs Vx" + suffix, prefix + "Vy vs Vx" + suffix,
				"X-Vertex (cm)", "Y-Vertex (cm)", vtBin, vtMin, vtMax, vtBin, vtMin, vtMax);
		this.myCanvas.setLogZ(detectorMoreTab, 4, 3, true);
		this.myCanvas.create1DHisto(detectorMoreTab, 4, 4, prefix + "Vz" + suffix, prefix + "Vz" + suffix,
				"Z-Vertex (cm)", vzBin, vzMin, vzMax);
		this.myCanvas.create1DHisto(detectorMoreTab, 5, 3, prefix + "nb tracks" + suffix, prefix + "nb tracks" + suffix,
				"Number of tracks", nbTrackBin, nbTrackMin, nbTrackMax);
		this.myCanvas.setLogY(detectorMoreTab, 5, 3, true);

		prefix = detectorName + " ";
		suffix = " (total)";
		if (!legend.equals("")) {
			suffix = " (total, " + legend + ")";
		}
		this.myCanvas.create1DHisto(detectorMoreTab, 5, 1, prefix + "nb tracks" + suffix,
				prefix + "total nb tracks" + suffix, "Number of tracks (Pos+Neg)", nbTrackBin, nbTrackMin, nbTrackMax);
		this.myCanvas.setLogY(detectorMoreTab, 5, 1, true);
		this.myCanvas.create1DHisto(detectorMoreTab, 5, 4, prefix + "#chi^2" + suffix, prefix + "#chi^2" + suffix,
				"#chi^2", chi2Bin, chi2Min, chi2Max);
		this.myCanvas.setLogY(detectorMoreTab, 5, 4, true);
		
		// canvas.create2DHisto(detectorCorrelationTab, 1, 1, detectorName +
		// "ThetaCVTvsForward", detectorName +" vs electron #theta", "Electron
		// #theta (°)", detectorName+" #theta (°)", thetaBin, 0, 30, thetaBin,
		// thetaMin, thetaMax);
		// canvas.create2DHisto(detectorCorrelationTab, 1, 2, detectorName +
		// "PhiCVTvsForward", detectorName +" vs electron #phi", "Electron #phi
		// (°)", detectorName+" #phi (°)", phiBin, phiMin, phiMax, phiBin,
		// phiMin, phiMax);
		// canvas.create2DHisto(detectorCorrelationTab, 1, 3, detectorName +
		// "MomentumCVTvsForward", detectorName +" vs electron P", "Electron
		// momentum (GeV)", detectorName+" momentum (GeV)", pBin, pMin, 10.6,
		// pBin, pMin, pMax);
		// canvas.create2DHisto(detectorCorrelationTab, 1, 4, detectorName +
		// "VertexCVTvsForward", detectorName +" vs electron Vz", "Electron
		// Z-Vertex (cm)", detectorName+" Z-Vertex (cm)", vzBin, vzMin, vzMax,
		// vzBin, vzMin, vzMax);
	}
	
	/**
	 * Create default plots. This tab is titled by the name of the particle so only one tab can be created using this method.
	 * @param electronEnergy beam energy (for plot scale)
	 */
	public void createDefaultHistograms(double electronEnergy) {
		this.createDefaultHistograms(electronEnergy, detectorName, "");
	}
		
	/**
	 * Fill default plots
	 * @param cvtEvent event to use to fill the plots
	 * @param tabName name of the tab to fill
	 * @param legend to fill plots created with a legend
	 */
	public void fillDefaultHistograms(CVTEvent cvtEvent, String tabName, String legend) {
		
		int nbTrackPos = 0;
		int nbTrackNeg = 0;
		for (CVTRecTrack cvtTrack : cvtEvent.getCvtTracks()) {
			if (cvtTrack.getCharge() > 0) {
				
				String prefix = detectorName + " ";
				String suffix = " (pos)";
				if (!legend.equals("")) {
					suffix = " (pos, " + legend + ")";
				}
				this.myCanvas.fill2DHisto(prefix + "#theta vs #phi" + suffix, Math.toDegrees(cvtTrack.getMomentum().phi()),
						Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.myCanvas.fill2DHisto(prefix + "P vs #theta" + suffix,
						Math.toDegrees(cvtTrack.getMomentum().theta()), cvtTrack.getMomentum().mag());
				this.myCanvas.fill2DHisto(prefix + "P vs #phi" + suffix, Math.toDegrees(cvtTrack.getMomentum().phi()),
						cvtTrack.getMomentum().mag());
				this.myCanvas.fill2DHisto(prefix + "#theta vs Vz" + suffix, cvtTrack.getVertex().z(),
						Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.myCanvas.fill2DHisto(prefix + "#phi vs Vz" + suffix, cvtTrack.getVertex().z(),
						Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.myCanvas.fill2DHisto(prefix + "P vs Vz" + suffix, cvtTrack.getVertex().z(),
						cvtTrack.getMomentum().mag());
				this.myCanvas.fill1DHisto(prefix + "#theta" + suffix, Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.myCanvas.fill1DHisto(prefix + "P" + suffix, cvtTrack.getMomentum().mag());
				this.myCanvas.fill1DHisto(prefix + "Vx" + suffix, cvtTrack.getVertex().x());
				this.myCanvas.fill1DHisto(prefix + "Vy" + suffix, cvtTrack.getVertex().y());
				this.myCanvas.fill1DHisto(prefix + "Vz" + suffix, cvtTrack.getVertex().z());
				this.myCanvas.fill2DHisto(prefix + "Vy vs Vx" + suffix, cvtTrack.getVertex().x(),cvtTrack.getVertex().y());
				this.myCanvas.fill1DHisto(prefix + "NDF" + suffix, cvtTrack.getNdf());
				nbTrackPos++;
			}
			if (cvtTrack.getCharge() < 0) {
				String prefix = detectorName + " ";
				String suffix = " (neg)";
				if (!legend.equals("")) {
					suffix = " (neg, " + legend + ")";
				}
				this.myCanvas.fill2DHisto(prefix + "#theta vs #phi" + suffix, Math.toDegrees(cvtTrack.getMomentum().phi()),
						Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.myCanvas.fill2DHisto(prefix + "P vs #theta" + suffix,
						Math.toDegrees(cvtTrack.getMomentum().theta()), cvtTrack.getMomentum().mag());
				this.myCanvas.fill2DHisto(prefix + "P vs #phi" + suffix, Math.toDegrees(cvtTrack.getMomentum().phi()),
						cvtTrack.getMomentum().mag());
				this.myCanvas.fill2DHisto(prefix + "#theta vs Vz" + suffix, cvtTrack.getVertex().z(),
						Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.myCanvas.fill2DHisto(prefix + "#phi vs Vz" + suffix, cvtTrack.getVertex().z(),
						Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.myCanvas.fill2DHisto(prefix + "P vs Vz" + suffix, cvtTrack.getVertex().z(),
						cvtTrack.getMomentum().mag());
				this.myCanvas.fill1DHisto(prefix + "#theta" + suffix, Math.toDegrees(cvtTrack.getMomentum().theta()));
				this.myCanvas.fill1DHisto(prefix + "#phi" + suffix, Math.toDegrees(cvtTrack.getMomentum().phi()));
				this.myCanvas.fill1DHisto(prefix + "P" + suffix, cvtTrack.getMomentum().mag());
				this.myCanvas.fill1DHisto(prefix + "Vx" + suffix, cvtTrack.getVertex().x());
				this.myCanvas.fill1DHisto(prefix + "Vy" + suffix, cvtTrack.getVertex().y());
				this.myCanvas.fill1DHisto(prefix + "Vz" + suffix, cvtTrack.getVertex().z());
				this.myCanvas.fill2DHisto(prefix + "Vy vs Vx" + suffix, cvtTrack.getVertex().x(),cvtTrack.getVertex().y());
				this.myCanvas.fill1DHisto(prefix + "NDF" + suffix, cvtTrack.getNdf());
				nbTrackNeg++;
			}
			String prefix = detectorName + " ";
			String suffix = " (total)";
			if (!legend.equals("")) {
				suffix = " (total, " + legend + ")";
			}
			this.myCanvas.fill1DHisto(prefix + "#chi^2" + suffix, cvtTrack.getChi2());
		}
		String prefix = detectorName + " ";
		String suffix = " (total)";
		if (!legend.equals("")) {
			suffix = " (total, " + legend + ")";
		}
		this.myCanvas.fill1DHisto(prefix + "nb tracks" + suffix, cvtEvent.getCvtTracks().size());
		
		prefix = detectorName + " ";
		suffix = " (pos)";
		if (!legend.equals("")) {
			suffix = " (pos, " + legend + ")";
		}
		this.myCanvas.fill1DHisto(prefix + "nb tracks" + suffix, nbTrackPos);
		
		prefix = detectorName + " ";
		suffix = " (neg)";
		if (!legend.equals("")) {
			suffix = " (neg, " + legend + ")";
		}
		this.myCanvas.fill1DHisto(prefix + "nb tracks" + suffix, nbTrackNeg);
	}

	
	/**
	 * Fill default plots
	 * @param cvtEvent event to use to fill the plots
	 */
	public void fillDefaultHistograms(CVTEvent cvtEvent) {
		this.fillDefaultHistograms(cvtEvent, detectorName, "");
	}
}
