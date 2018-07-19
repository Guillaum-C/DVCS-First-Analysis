package plots.detectorPlots;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.forward.calorimeter.CalorimeterEvent;
import org.clas12.analysisTools.event.forward.calorimeter.CalorimeterRecCluster;
import org.clas12.analysisTools.event.particles.*;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.plots.Canvas;

public class DCPlots{

	private Canvas canvas;
	private final String detectorName;
	private final String detectorTab;
	private final String detectorCorrelationTab;
	private final String detectorBySectorTab;
	private final int numberOfRows = 3;
	private final int numberOfColumns = 4;
	
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
	 * Create a DC tab with DC plots
	 * 
	 * @param canvas canvas
	 * @param name name used for the tab and the plots
	 */
	public DCPlots(Canvas canvas, String name) {
		super();
		this.canvas = canvas;
		this.detectorName = name;
		this.detectorTab = name;
		this.detectorCorrelationTab = name + " correlation";
		this.detectorBySectorTab = detectorTab + "/sector";
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
		this.getCanvas().addTab(detectorTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorCorrelationTab, numberOfRows, numberOfColumns);
		this.getCanvas().addTab(detectorBySectorTab, numberOfRows, numberOfColumns);
	}

	/**
	 * Create DC histograms
	 */
	public void createHistograms() {
		this.getCanvas().create2DHisto(detectorTab, 1, 1, detectorName + "PosThetaPhi", detectorName + " pos #theta vs #phi", "#phi (°)",
				"#theta (°)", phiBin, phiMin, phiMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 1, detectorName + "PosThetaMomentum", detectorName + " pos P vs #theta", "#theta (°)",
				"Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 1, detectorName + "PosPhiMomentum", detectorName + " pos P vs #phi", "#phi (°)",
				"Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 1, 2, detectorName + "PosThetaVertexZ", detectorName + " pos #theta vs Vz",
				" Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 2, detectorName + "PosPhiVertexZ", detectorName + " pos #phi vs Vz", " Z-Vertex (cm)",
				"#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 2, detectorName + "PosMomentumVertexZ", detectorName + " pos P vs Vz",
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);
		
		this.getCanvas().create2DHisto(detectorTab, 1, 3, detectorName + "NegThetaPhi", detectorName + " neg #theta vs #phi", "#phi (°)",
				"#theta (°)", phiBin, phiMin, phiMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 3, detectorName + "NegThetaMomentum", detectorName + " neg P vs #theta", "#theta (°)",
				"Momentum (GeV)", thetaBin, thetaMin, thetaMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 3, detectorName + "NegPhiMomentum", detectorName + " neg P vs #phi", "#phi (°)",
				"Momentum (GeV)", phiBin, phiMin, phiMax, pBin, pMin, pMax);
		this.getCanvas().create2DHisto(detectorTab, 1, 4, detectorName + "NegThetaVertexZ", detectorName + " neg #theta vs Vz",
				" Z-Vertex (cm)", "#theta (°)", vzBin, vzMin, vzMax, thetaBin, thetaMin, thetaMax);
		this.getCanvas().create2DHisto(detectorTab, 2, 4, detectorName + "PhiVertexZ", detectorName + " neg #phi vs Vz", " Z-Vertex (cm)",
				"#phi (°)", vzBin, vzMin, vzMax, phiBin, phiMin, phiMax);
		this.getCanvas().create2DHisto(detectorTab, 3, 4, detectorName + "NegMomentumVertexZ", detectorName + " neg P vs Vz",
				" Z-Vertex (cm)", "Momentum (GeV)", vzBin, vzMin, vzMax, pBin, pMin, pMax);

	}

	/**
	 * Fill DC histograms
	 * 
	 * @param event processed event
	 */
	public void fillHistograms(Event event) {
		
	}

}
