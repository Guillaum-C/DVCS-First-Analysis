package plots.physics;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.plots.Canvas;

import physics.ComputePhysicsParameters;

public class PhysicsPlots {
	
	Canvas myCanvas;
	double electronEnergy;
	
	boolean debug =false;
	
	int mmEPGBin = 300;
	double mmEPGMin = -0.15;
	double mmEPGMax = 0.15;
	
	int mmEPGTBin = 300;
	double mmEPGTMin = -1;
	double mmEPGTMax = 5;
	
	int mmEPGZBin = 300;
	double mmEPGZMin = -5;
	double mmEPGZMax = 10;
	

	int mmEPBin = 300;
	double mmEPMin = -1.5;
	double mmEPMax = 1.5;
	
//	int mmEPTBin = 100;
//	double mmEPTMin = -1;
//	double mmEPTMax = 10;
//	
//	int mmEPZBin = 100;
//	double mmEPZMin = -10;
//	double mmEPZMax = 10;
	

	int mmEGBin = 300;
	double mmEGMin = -1;
	double mmEGMax = 4;
	
//	int mmEGTBin = 100;
//	double mmEGTMin = -1;
//	double mmEGTMax = 10;
//	
//	int mmEGZBin = 100;
//	double mmEGZMin = -10;
//	double mmEGZMax = 10;
	
	
	int thetaProtonBin = 100;
	double thetaProtonMin = 0;
	double thetaProtonMax = 150; 
	
	int thetaPhotonBin = 100;
	double thetaPhotonMin = 0;
	double thetaPhotonMax = 50; 
	
	int thetaElectronBin = 100;
	double thetaElectronMin = 0;
	double thetaElectronMax = 50; 
	
	int pProtonBin = 100;
	double pProtonMin = 0;
	double pProtonMax = 3; 
	
	int pPhotonBin = 100;
	double pPhotonMin = 0;
	double pPhotonMax = 10; 
	
	int pElectronBin = 100;
	double pElectronMin = 0;
	double pElectronMax = 10; 
	
	int deltaThetaBin = 100;
	double deltaThetaMin = -5;
	double deltaThetaMax = 5;
	
	int deltaThetaProtonBin = 100;
	double deltaThetaProtonMin = -50;
	double deltaThetaProtonMax = 50;
	
	int deltaConeBin = 300;
	double deltaConeMin = 0;
	double deltaConeMax = 10;
	
	int deltaConeProtonBin = 100;
	double deltaConeProtonMin = 0;
	double deltaConeProtonMax = 60;
	
	int phiBin = 20;
	double phiMin = 0;
	double phiMax = 360;
	
	int helicityBin = 3;
	double helicityMin = -1;
	double helicityMax = 2;
	
	int mGGBin = 200;
	double mGGMin = 0;
	double mGGMax = 0.4;
	
	/**
	 * Create plots class
	 * @param canvas canvas
	 * @param electronEnergy beam energy
	 */
	public PhysicsPlots(Canvas canvas, double electronEnergy){
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
	public void createDefaultHistogramsDVCS(double electronEnergy, String tabName, String legend) {
		String processTab = tabName;
		String processTabMore = tabName+" more";
		
		String processName = "DVCS";
		
		int tabNumberOfRows = 4;
		int tabNumberOfColumns = 3;
		int tabNumberOfRowsMore = 4;
		int tabNumberOfColumnsMore =3;

		this.myCanvas.addTab(processTab, tabNumberOfRows, tabNumberOfColumns);
		this.myCanvas.addTab(processTabMore, tabNumberOfRowsMore, tabNumberOfColumnsMore);

		String prefix = processName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		
		this.myCanvas.create1DHisto(processTab, 1, 1, prefix + "MM(ep->ep#gammaX)^2" + suffix,
				prefix + "MM(ep->ep#gammaX)^2" + suffix, "MM(ep->ep#gammaX)^2", mmEPGBin, mmEPGMin, mmEPGMax);
		this.myCanvas.create1DHisto(processTab, 1, 2, prefix + "MM(ep->epX)^2 when #gamma" + suffix,
				prefix + "MM(ep->epX)^2 when #gamma" + suffix, "MM(ep->epX)^2", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTab, 1, 3, prefix + "MM(ep->e#gammaX)^2 when p" + suffix,
				prefix + "MM(ep->e#gammaX)^2 when p" + suffix, "MM(ep->e#gammaX)^2", mmEGBin, mmEGMin, mmEGMax);
		
		this.myCanvas.create2DHisto(processTab, 2, 1,prefix + "MM(ep->epX)^2 when #gamma vs MM(ep->ep#gammaX)^2" + suffix,
				prefix + "MM(ep->epX)^2 when #gamma vs MM(ep->ep#gammaX)^2" + suffix, "MM(ep->ep#gammaX)^2", "MM(ep->epX)^2", mmEPGBin, mmEPGMin, mmEPGMax, mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create2DHisto(processTab, 2, 2,prefix + "MM(ep->e#gammaX)^2 when p vs MM(ep->ep#gammaX)^2" + suffix,
				prefix + "MM(ep->e#gammaX)^2 when p vs MM(ep->ep#gammaX)^2" + suffix, "MM(ep->ep#gammaX)^2", "MM(ep->e#gammaX)^2", mmEPGBin, mmEPGMin, mmEPGMax, mmEGBin, mmEGMin, mmEGMax);
		this.myCanvas.create2DHisto(processTab, 2, 3,prefix + "MM(ep->epX)^2 when #gamma vs MM(ep->e#gammaX)^2 when p" + suffix,
				prefix + "MM(ep->epX)^2 when #gamma vs MM(ep->e#gammaX)^2 when p" + suffix, "MM(ep->e#gammaX)^2", "MM(ep->epX)^2", mmEGBin, mmEGMin, mmEGMax, mmEPBin, mmEPMin, mmEPMax);

		this.myCanvas.create1DHisto(processTab, 3, 1, prefix + "MM(ep->ep#gammaX)^2 - T" + suffix,
				prefix + "MM(ep->ep#gammaX)^2 - T" + suffix, "MM(ep->ep#gammaX)^2 - transverse", mmEPGTBin, mmEPGTMin, mmEPGTMax);
		this.myCanvas.create1DHisto(processTab, 3, 2, prefix + "MM(ep->ep#gammaX)^2 - Z" + suffix,
				prefix + "MM(ep->ep#gammaX)^2 - Z" + suffix, "MM(ep->ep#gammaX)^2 - Z", mmEPGZBin, mmEPGZMin, mmEPGZMax);
		this.myCanvas.create1DHisto(processTab, 3, 3, prefix + "MM(ep->ep#gammaX)^2 - E" + suffix,
				prefix + "MM(ep->ep#gammaX)^2 - E" + suffix, "MM(ep->ep#gammaX)^2 - E", mmEPGZBin, mmEPGZMin, mmEPGZMax);

		this.myCanvas.create1DHisto(processTab, 4, 1, prefix + "Cone #theta proton" + suffix,
				prefix + "Cone #theta proton" + suffix, "Cone #theta proton (°)", deltaConeProtonBin, deltaConeProtonMin, deltaConeProtonMax);
		this.myCanvas.create1DHisto(processTab, 4, 2, prefix + "Cone #theta photon" + suffix,
				prefix + "Cone #theta photon" + suffix, "Cone #theta photon (°)", deltaConeBin, deltaConeMin, deltaConeMax);
		this.myCanvas.create1DHisto(processTab, 4, 3, prefix + "Cone #theta electron" + suffix,
				prefix + "Cone #theta electron" + suffix, "Cone #theta electron (°)", deltaConeBin, deltaConeMin, deltaConeMax);
		
//		this.myCanvas.create1DHisto(processTab, 4, 1, prefix + "MM(ep->epX)^2 when #gamma - T" + suffix,
//				prefix + "MM(ep->epX)^2 when #gamma - T" + suffix, "MM(ep->epX)^2 when #gamma - transverse", mmEPTBin, mmEPTMin, mmEPTMax);
//		this.myCanvas.create1DHisto(processTab, 4, 2, prefix + "MM(ep->epX)^2 when #gamma - Z" + suffix,
//				prefix + "MM(ep->epX)^2 when #gamma - Z" + suffix, "MM(ep->epX)^2 when #gamma - Z", mmEPZBin, mmEPZMin, mmEPZMax);
//		this.myCanvas.create1DHisto(processTab, 4, 3, prefix + "MM(ep->epX)^2 when #gamma - E" + suffix,
//				prefix + "MM(ep->epX)^2 when #gamma - E" + suffix, "MM(ep->epX)^2 when #gamma - E", mmEPZBin, mmEPZMin, mmEPZMax);
//		
//		this.myCanvas.create1DHisto(processTab, 5, 1, prefix + "MM(ep->e#gammaX)^2 when p - T" + suffix,
//				prefix + "MM(ep->e#gammaX)^2 when p - T" + suffix, "MM(ep->e#gammaX)^2 when p - transverse", mmEGTBin, mmEGTMin, mmEGTMax);
//		this.myCanvas.create1DHisto(processTab, 5, 2, prefix + "MM(ep->e#gammaX)^2 when p - Z" + suffix,
//				prefix + "MM(ep->e#gammaX)^2 when p - Z" + suffix, "MM(ep->e#gammaX)^2 when p - Z", mmEGZBin, mmEGZMin, mmEGZMax);
//		this.myCanvas.create1DHisto(processTab, 5, 3, prefix + "MM(ep->e#gammaX)^2 when p - E" + suffix,
//				prefix + "MM(ep->e#gammaX)^2 when p - E" + suffix, "MM(ep->e#gammaX)^2 when p - E", mmEGZBin, mmEGZMin, mmEGZMax);
		
		
		
		this.myCanvas.create2DHisto(processTabMore, 1, 1, prefix + "Proton #theta found vs expected" + suffix,
				prefix + "Proton #theta found vs expected" + suffix, "Proton #theta expected (°)", "Proton #theta found (°)", thetaProtonBin, thetaProtonMin, thetaProtonMax, thetaProtonBin, thetaProtonMin, thetaProtonMax);
		this.myCanvas.create2DHisto(processTabMore, 1, 2, prefix + "Photon #theta found vs expected" + suffix,
				prefix + "Photon #theta found vs expected" + suffix, "Photon #theta expected (°)", "Photon #theta found (°)", thetaPhotonBin, thetaPhotonMin, thetaPhotonMax, thetaPhotonBin, thetaPhotonMin, thetaPhotonMax);
		this.myCanvas.create2DHisto(processTabMore, 1, 3, prefix + "Electron #theta found vs expected" + suffix,
				prefix + "Electron #theta found vs expected" + suffix, "Electron #theta expected (°)", "Electron #theta found (°)", thetaElectronBin, thetaElectronMin, thetaElectronMax, thetaElectronBin, thetaElectronMin, thetaElectronMax);
		
		this.myCanvas.create2DHisto(processTabMore, 2, 1, prefix + "Proton P found vs expected" + suffix,
				prefix + "Proton P found vs expected" + suffix, "Proton momentum expected (GeV)", "Proton momentum found (GeV)", pProtonBin, pProtonMin, pProtonMax, pProtonBin, pProtonMin, pProtonMax);
		this.myCanvas.create2DHisto(processTabMore, 2, 2, prefix + "Photon P found vs expected" + suffix,
				prefix + "Photon P found vs expected" + suffix, "Photon momentum expected (GeV)", "Photon momentum found (GeV)", pPhotonBin, pPhotonMin, pPhotonMax, pPhotonBin, pPhotonMin, pPhotonMax);
		this.myCanvas.create2DHisto(processTabMore, 2, 3, prefix + "Electron P found vs expected" + suffix,
				prefix + "Electron P found vs expected" + suffix, "Electron momentum expected (GeV)", "Electron momentum found (GeV)", pElectronBin, pElectronMin, pElectronMax, pElectronBin, pElectronMin, pElectronMax);
		
		this.myCanvas.create1DHisto(processTabMore, 3, 1, prefix + "Proton #Delta#theta" + suffix,
				prefix + "Proton #Delta#theta" + suffix, "Proton #theta found - expected (°)", deltaThetaProtonBin, deltaThetaProtonMin, deltaThetaProtonMax);
		this.myCanvas.create1DHisto(processTabMore, 3, 2, prefix + "Photon #Delta#theta" + suffix,
				prefix + "Photon #Delta#theta" + suffix, "Photon #theta found - expected (°)", deltaThetaBin, deltaThetaMin, deltaThetaMax);
		this.myCanvas.create1DHisto(processTabMore, 3, 3, prefix + "Electron #Delta#theta" + suffix,
				prefix + "Electron #Delta#theta" + suffix, "Electron #theta found - expected (°)", deltaThetaBin, deltaThetaMin, deltaThetaMax);

		this.myCanvas.create2DHisto(processTabMore, 4, 1, prefix + "Cone #theta proton vs MM(ep->e#gammaX)^2" + suffix,
				prefix + "Cone #theta proton vs MM(ep->e#gammaX)^2" + suffix, "MM(ep->e#gammaX)^2", "Cone #theta proton (°)", mmEGBin, mmEGMin, mmEGMax, deltaConeProtonBin, deltaConeProtonMin, deltaConeProtonMax);
		this.myCanvas.create2DHisto(processTabMore, 4, 2, prefix + "Cone #theta photon vs MM(ep->e#gammaX)^2" + suffix,
				prefix + "Cone #theta photon vs MM(ep->e#gammaX)^2" + suffix, "MM(ep->e#gammaX)^2", "Cone #theta photon (°)", mmEGBin, mmEGMin, mmEGMax, deltaConeBin, deltaConeMin, deltaConeMax);
		this.myCanvas.create2DHisto(processTabMore, 4, 3, prefix + "Cone #theta electron vs MM(ep->e#gammaX)^2" + suffix,
				prefix + "Cone #theta electron vs MM(ep->e#gammaX)^2" + suffix, "MM(ep->e#gammaX)^2", "Cone #theta electron (°)", mmEGBin, mmEGMin, mmEGMax, deltaConeBin, deltaConeMin, deltaConeMax);
	}
	
	public void fillDefaultHistogramsDVCS(Electron electronF, Proton protonF, Photon photonF, double beamEnergy, String tabName, String legend) {
		String processTab = tabName;
		String processTabMore = tabName+" more";
		
		String processName = "DVCS";
		
		String prefix = processName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, Proton.mass);
		LorentzVector electronI = new LorentzVector();
		electronI.setPxPyPzM(0, 0, beamEnergy, Electron.mass);
		LorentzVector stateI = protonI.sum(electronI);
		
		LorentzVector finalStateEPG = new LorentzVector();
		finalStateEPG.add(electronF.getFourMomentum(), protonF.getFourMomentum(), photonF.getFourMomentum());
		double missingMass2EPG = stateI.substract(finalStateEPG).mass2();
		
		LorentzVector finalStateEPwhenG = new LorentzVector();
		finalStateEPwhenG.add(electronF.getFourMomentum(), protonF.getFourMomentum());
		double missingMass2EPwhenG = stateI.substract(finalStateEPwhenG).mass2();
		
		LorentzVector finalStateEGwhenP = new LorentzVector();
		finalStateEGwhenP.add(electronF.getFourMomentum(), photonF.getFourMomentum());
		double missingMass2EGwhenP = stateI.substract(finalStateEGwhenP).mass2();
		
		LorentzVector finalStatePGwhenE = new LorentzVector();
		finalStatePGwhenE.add(protonF.getFourMomentum(), photonF.getFourMomentum());
		double missingMass2PGwhenE = stateI.substract(finalStatePGwhenE).mass2();
		
		LorentzVector protonExpected = stateI.substract(finalStateEGwhenP);
		LorentzVector photonExpected = stateI.substract(finalStateEPwhenG);
		LorentzVector electronExpected = stateI.substract(finalStatePGwhenE);
		
		double thetaProtonExpected = protonExpected.theta() * 180 / Math.PI;
		double thetaPhotonExpected = photonExpected.theta() * 180 / Math.PI;
		double thetaElectronExpected = electronExpected.theta() * 180 / Math.PI;
		
		double momentumProtonExpected = protonExpected.vect().mag();
		double momentumPhotonExpected = photonExpected.vect().mag();
		double momentumElectronExpected = electronExpected.vect().mag();
		
		double angle2Proton = 180/Math.PI*Math.acos(protonExpected.vect().dot(protonF.getFourMomentum().vect())
				/ (protonExpected.vect().mag() * protonF.getFourMomentum().vect().mag()));
		double angle2Photon = 180/Math.PI*Math.acos(photonExpected.vect().dot(photonF.getFourMomentum().vect())
				/ (photonExpected.vect().mag() * photonF.getFourMomentum().vect().mag()));
		double angle2Electron = 180/Math.PI*Math.acos(electronExpected.vect().dot(electronF.getFourMomentum().vect())
				/ (electronExpected.vect().mag() * electronF.getFourMomentum().vect().mag()));
		
		this.myCanvas.fill1DHisto(prefix + "MM(ep->ep#gammaX)^2" + suffix, missingMass2EPG);
		this.myCanvas.fill1DHisto(prefix + "MM(ep->epX)^2 when #gamma" + suffix, missingMass2EPwhenG);
		this.myCanvas.fill1DHisto(prefix + "MM(ep->e#gammaX)^2 when p" + suffix, missingMass2EGwhenP);
		
		this.myCanvas.fill2DHisto(prefix + "MM(ep->epX)^2 when #gamma vs MM(ep->ep#gammaX)^2" + suffix, missingMass2EPG, missingMass2EPwhenG);
		this.myCanvas.fill2DHisto(prefix + "MM(ep->e#gammaX)^2 when p vs MM(ep->ep#gammaX)^2" + suffix, missingMass2EPG, missingMass2EGwhenP);
		this.myCanvas.fill2DHisto(prefix + "MM(ep->epX)^2 when #gamma vs MM(ep->e#gammaX)^2 when p" + suffix, missingMass2EGwhenP, missingMass2EPwhenG);
		
		this.myCanvas.fill1DHisto(prefix + "MM(ep->ep#gammaX)^2 - T" + suffix, Math.sqrt( Math.pow(stateI.substract(finalStateEPG).px(),2) + Math.pow(stateI.substract(finalStateEPG).py(),2) ) );
		this.myCanvas.fill1DHisto(prefix + "MM(ep->ep#gammaX)^2 - Z" + suffix, stateI.substract(finalStateEPG).pz());
		this.myCanvas.fill1DHisto(prefix + "MM(ep->ep#gammaX)^2 - E" + suffix, stateI.substract(finalStateEPG).e());
		
//		this.myCanvas.fill1DHisto(prefix + "MM(ep->epX)^2 when #gamma - T" + suffix, Math.sqrt( Math.pow(stateI.substract(finalStateEPwhenG).px(),2) + Math.pow(stateI.substract(finalStateEPwhenG).py(),2) ));
//		this.myCanvas.fill1DHisto(prefix + "MM(ep->epX)^2 when #gamma - Z" + suffix, stateI.substract(finalStateEPwhenG).pz());
//		this.myCanvas.fill1DHisto(prefix + "MM(ep->epX)^2 when #gamma - E" + suffix, stateI.substract(finalStateEPwhenG).e());
//		
//		this.myCanvas.fill1DHisto(prefix + "MM(ep->e#gammaX)^2 when p - T" + suffix, Math.sqrt( Math.pow(stateI.substract(finalStateEGwhenP).px(),2) + Math.pow(stateI.substract(finalStateEGwhenP).py(),2) ));
//		this.myCanvas.fill1DHisto(prefix + "MM(ep->e#gammaX)^2 when p - Z" + suffix, stateI.substract(finalStateEGwhenP).pz());
//		this.myCanvas.fill1DHisto(prefix + "MM(ep->e#gammaX)^2 when p - E" + suffix, stateI.substract(finalStateEGwhenP).e());
		

		
		this.myCanvas.fill2DHisto(prefix + "Proton #theta found vs expected" + suffix, thetaProtonExpected, protonF.getThetaDeg());
		this.myCanvas.fill2DHisto(prefix + "Photon #theta found vs expected" + suffix, thetaPhotonExpected, photonF.getThetaDeg());
		this.myCanvas.fill2DHisto(prefix + "Electron #theta found vs expected" + suffix, thetaElectronExpected, electronF.getThetaDeg());
		
		this.myCanvas.fill2DHisto(prefix + "Proton P found vs expected" + suffix, momentumProtonExpected, protonF.getMomentum().mag());
		this.myCanvas.fill2DHisto(prefix + "Photon P found vs expected" + suffix, momentumPhotonExpected, photonF.getMomentum().mag());
		this.myCanvas.fill2DHisto(prefix + "Electron P found vs expected" + suffix, momentumElectronExpected, electronF.getMomentum().mag());
		
		this.myCanvas.fill1DHisto(prefix + "Proton #Delta#theta" + suffix, protonF.getThetaDeg()-thetaProtonExpected);
		this.myCanvas.fill1DHisto(prefix + "Photon #Delta#theta" + suffix, photonF.getThetaDeg()-thetaPhotonExpected);
		this.myCanvas.fill1DHisto(prefix + "Electron #Delta#theta" + suffix, electronF.getThetaDeg()-thetaElectronExpected);
		
		this.myCanvas.fill1DHisto(prefix + "Cone #theta proton" + suffix, angle2Proton);
		this.myCanvas.fill1DHisto(prefix + "Cone #theta photon" + suffix, angle2Photon);
		this.myCanvas.fill1DHisto(prefix + "Cone #theta electron" + suffix, angle2Electron);
		
		this.myCanvas.fill2DHisto(prefix + "Cone #theta proton vs MM(ep->e#gammaX)^2" + suffix, missingMass2EGwhenP, angle2Proton);
		this.myCanvas.fill2DHisto(prefix + "Cone #theta photon vs MM(ep->e#gammaX)^2" + suffix, missingMass2EGwhenP, angle2Photon);
		this.myCanvas.fill2DHisto(prefix + "Cone #theta electron vs MM(ep->e#gammaX)^2" + suffix, missingMass2EGwhenP, angle2Electron);
		
//		this.myCanvas.create2DHisto(processTabMore, 4, 1, prefix + "Cone #theta proton vs MM(ep->e#gammaX)^2" + suffix,
//				prefix + "Cone #theta proton vs MM(ep->e#gammaX)^2" + suffix, "MM(ep->e#gammaX)^2", "Cone #theta proton (°)", mmEGBin, mmEGMin, mmEGMax, deltaConeBin, deltaConeMin, deltaConeMax);
//		this.myCanvas.create2DHisto(processTabMore, 4, 2, prefix + "Cone #theta photon vs MM(ep->e#gammaX)^2" + suffix,
//				prefix + "Cone #theta photon vs MM(ep->e#gammaX)^2" + suffix, "MM(ep->e#gammaX)^2", "Cone #theta photon (°)", mmEGBin, mmEGMin, mmEGMax, deltaConeBin, deltaConeMin, deltaConeMax);
//		this.myCanvas.create2DHisto(processTabMore, 4, 3, prefix + "Cone #theta electron vs MM(ep->e#gammaX)^2" + suffix,
//				prefix + "Cone #theta electron vs MM(ep->e#gammaX)^2" + suffix, "MM(ep->e#gammaX)^2", "Cone #theta electron (°)", mmEGBin, mmEGMin, mmEGMax, deltaConeBin, deltaConeMin, deltaConeMax);

	}

	public void createAsymetryHisto(String tabName, String legend){
		String tab = tabName;
//		String tabMore = tabName+" more";
		
		String processName = "Asymetry";
		
		int tabNumberOfRows = 2;
		int tabNumberOfColumns = 2;
//		int tabNumberOfRowsMore = 4;
//		int tabNumberOfColumnsMore =3;

		this.myCanvas.addTab(tab, tabNumberOfRows, tabNumberOfColumns);
//		this.myCanvas.addTab(tabMore, tabNumberOfRowsMore, tabNumberOfColumnsMore);

		String prefix = processName + " ";
		String prefixTitle = "";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}	
		
//		int xBBin = 200;
//		double xBMin = 0;
//		double xBMax = 1;
//
//		int q2Bin = 200;
//		double q2Min = 0;
//		double q2Max = 10.6;
//		this.myCanvas.create2DHisto(tab, 1, 1, prefix + "Q^2 vs xB" + suffix,
//				prefix + "Q^2 vs xB" + suffix, "xB", "Q^2 (GeV^2)", xBBin, xBMin, xBMax, q2Bin,
//				q2Min, q2Max);
		
		myCanvas.create1DHisto(tab, 1, 1, prefixTitle+"Asymetry"+suffix, prefix+"Asymetry"+suffix, "#phi_trento (°)", phiBin, phiMin, phiMax);
		myCanvas.create1DHisto(tab, 1, 2, prefixTitle+"Helicity"+suffix, prefix+"Helicity"+suffix, "Helicity", helicityBin, helicityMin, helicityMax);
		myCanvas.create1DHisto(tab, 2, 1, prefixTitle+"#phi_trento for events with helicity +"+suffix, prefix+"#phi_trento for events with helicity +"+suffix, "#phi_trento (°)", phiBin, phiMin, phiMax);
		myCanvas.create1DHisto(tab, 2, 2, prefixTitle+"#phi_trento for events with helicity -"+suffix, prefix+"#phi_trento for events with helicity -"+suffix, "#phi_trento (°)", phiBin, phiMin, phiMax);
	}
	
	public void fillAsymetryHisto(double phiDeg, int helicity, String tabName, String legend){
		String tab = tabName;
//		String tabMore = tabName+" more";
		
		String processName = "Asymetry";
		
		String prefix = processName + " ";
		String prefixTitle = "";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		
		this.myCanvas.fill1DHisto(prefixTitle+"Helicity"+suffix, helicity);
		if (helicity == 1) {
			this.myCanvas.fill1DHisto(prefixTitle+"#phi_trento for events with helicity +"+suffix, phiDeg, 1);
		} else if (helicity == -1){
			this.myCanvas.fill1DHisto(prefixTitle+"#phi_trento for events with helicity -"+suffix, phiDeg, 1);
		} else if (helicity == 0){
			//ignore this
		} else {
			throw new IllegalArgumentException("Helicity has to be +1 or -1 (or 0)");
		}
		int phiBin = this.myCanvas.get1DHisto(prefixTitle+"Asymetry"+suffix).getxAxis().getBin(phiDeg);
		double nPlus = this.myCanvas.get1DHisto(prefixTitle+"#phi_trento for events with helicity +"+suffix).getBinContent(phiBin);
		double nMinus = this.myCanvas.get1DHisto(prefixTitle+"#phi_trento for events with helicity -"+suffix).getBinContent(phiBin);
		this.myCanvas.get1DHisto(prefixTitle+"Asymetry"+suffix).setBinContent(phiBin, (nPlus - nMinus) / (nPlus + nMinus));
	}

	public void createDefaultPi0Histo(double electronEnergy, String tabName, String legend) {
		String processTab = tabName;
		String processTabMore = tabName+" more";
		
		String processName = "Pi0";
		
		int tabNumberOfRows = 3;
		int tabNumberOfColumns = 2;
		int tabNumberOfRowsMore = 5;
		int tabNumberOfColumnsMore =4;

		this.myCanvas.addTab(processTab, tabNumberOfRows, tabNumberOfColumns);
		this.myCanvas.addTab(processTabMore, tabNumberOfRowsMore, tabNumberOfColumnsMore);

		String prefixTitle = "";
		String prefix = processName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.create1DHisto(processTab, 1, 1, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " (in Fwd and FT " + legend + ")";
		this.myCanvas.create1DHisto(processTab, 1, 2, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " (in Fwd " + legend + ")";
		this.myCanvas.create1DHisto(processTab, 2, 1, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " (in FT " + legend + ")";
		this.myCanvas.create1DHisto(processTab, 2, 2, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = "";
		this.myCanvas.create1DHisto(processTab, 3, 1, prefix + "M(#gamma#gamma) theta" + suffix,
				prefix + "M(#gamma#gamma) theta" + suffix, "M(#gamma#gamma)", 100, 0, 50);
		this.myCanvas.create1DHisto(processTab, 3, 2, prefix + "Energy of photon (with highest energy)" + suffix,
				prefix + "Energy of photon (with highest energy)" + suffix, "Energy (GeV)", 100, 0, 10);
		
		suffix = "";
		this.myCanvas.create1DHisto(processTabMore, 1, 1, prefixTitle + "MM(epgg)" + suffix,
				prefix + "MM(epgg)" + suffix, "MM(epgg)", mmEPGBin, mmEPGMin, mmEPGMax);
		this.myCanvas.create1DHisto(processTabMore, 1, 2, prefixTitle + "MM(epg1)" + suffix,
				prefix + "MM(epg1)" + suffix, "MM(epg1)", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTabMore, 1, 3, prefixTitle + "MM(epg2)" + suffix,
				prefix + "MM(epg2)" + suffix, "MM(epg2)", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTabMore, 1, 4, prefixTitle + "MM(ep)" + suffix,
				prefix + "MM(ep)" + suffix, "MM(ep)", 100, -5, 5);
//		suffix = " (in Fwd and FT " + legend + ")";
//		this.myCanvas.create1DHisto(processTabMore, 2, 1, prefixTitle + "M(#gamma#gamma)" + suffix,
//				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
//		suffix = " (in Fwd and FT " + legend + ")";
//		this.myCanvas.create1DHisto(processTabMore, 2, 2, prefixTitle + "M(#gamma#gamma)" + suffix,
//				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
//		suffix = " (in Fwd " + legend + ")";
//		this.myCanvas.create1DHisto(processTabMore, 2, 3, prefixTitle + "M(#gamma#gamma)" + suffix,
//				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
//		suffix = " (in FT " + legend + ")";
//		this.myCanvas.create1DHisto(processTabMore, 2, 4, prefixTitle + "M(#gamma#gamma)" + suffix,
//				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		
		suffix = "after epgg cut";
		this.myCanvas.create1DHisto(processTabMore, 2, 1, prefixTitle + "MM(epgg)" + suffix,
				prefix + "MM(epgg)" + suffix, "MM(epgg)", mmEPGBin, mmEPGMin, mmEPGMax);
		this.myCanvas.create1DHisto(processTabMore, 2, 2, prefixTitle + "MM(epg1)" + suffix,
				prefix + "MM(epg1)" + suffix, "MM(epg1)", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTabMore, 2, 3, prefixTitle + "MM(epg2)" + suffix,
				prefix + "MM(epg2)" + suffix, "MM(epg2)", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTabMore, 2, 4, prefixTitle + "MM(ep)" + suffix,
				prefix + "MM(ep)" + suffix, "MM(ep)", 100, -5, 5);
		this.myCanvas.create1DHisto(processTabMore, 3, 1, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " after epgg cut (in Fwd and FT " + legend + ")";
		this.myCanvas.create1DHisto(processTabMore, 3, 2, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " after epgg cut (in Fwd " + legend + ")";
		this.myCanvas.create1DHisto(processTabMore, 3, 3, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " after epgg cut (in FT " + legend + ")";
		this.myCanvas.create1DHisto(processTabMore, 3, 4, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		
		suffix = "after M(gg) cut";
		this.myCanvas.create1DHisto(processTabMore, 4, 1, prefixTitle + "MM(epgg)" + suffix,
				prefix + "MM(epgg)" + suffix, "MM(epgg)", mmEPGBin, mmEPGMin, mmEPGMax);
		this.myCanvas.create1DHisto(processTabMore, 4, 2, prefixTitle + "MM(epg1)" + suffix,
				prefix + "MM(epg1)" + suffix, "MM(epg1)", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTabMore, 4, 3, prefixTitle + "MM(epg2)" + suffix,
				prefix + "MM(epg2)" + suffix, "MM(epg2)", mmEPBin, mmEPMin, mmEPMax);
		this.myCanvas.create1DHisto(processTabMore, 4, 4, prefixTitle + "MM(ep)" + suffix,
				prefix + "MM(ep)" + suffix, "MM(ep)", 100, -5, 5);
		this.myCanvas.create1DHisto(processTabMore, 5, 1, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " after M(gg) cut (in Fwd and FT " + legend + ")";
		this.myCanvas.create1DHisto(processTabMore, 5, 2, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " after M(gg) cut (in Fwd " + legend + ")";
		this.myCanvas.create1DHisto(processTabMore, 5, 3, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
		suffix = " after M(gg) cut (in FT " + legend + ")";
		this.myCanvas.create1DHisto(processTabMore, 5, 4, prefixTitle + "M(#gamma#gamma)" + suffix,
				prefix + "M(#gamma#gamma)" + suffix, "M(#gamma#gamma)", mGGBin, mGGMin, mGGMax);
	}
	
	public void fillDefaultPi0Histo(Photon photon1, Photon photon2, String tabName, String legend){
		String processTab = tabName;
//		String processTabMore = tabName+" more";
		
		String processName = "Pi0";
		
		String prefixTitle = "";
		String prefix = processName + " ";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,ComputePhysicsParameters.computePi0InvariantMass(photon1, photon2));
		if ((photon1.getThetaDeg()>5 && photon2.getThetaDeg()<5) || (photon1.getThetaDeg()<5 && photon2.getThetaDeg()>5)){
			suffix = " (in Fwd and FT " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,ComputePhysicsParameters.computePi0InvariantMass(photon1, photon2));
		}
		if (photon1.getThetaDeg()>5 && photon2.getThetaDeg()>5){
			suffix = " (in Fwd " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,ComputePhysicsParameters.computePi0InvariantMass(photon1, photon2));
		}
		if (photon1.getThetaDeg()<5 && photon2.getThetaDeg()<5){
			suffix = " (in FT " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,ComputePhysicsParameters.computePi0InvariantMass(photon1, photon2));
		}
		suffix = "";
		this.myCanvas.fill1DHisto(prefix + "M(#gamma#gamma) theta" + suffix,photon1.getThetaDeg());
		this.myCanvas.fill1DHisto(prefix + "M(#gamma#gamma) theta" + suffix,photon2.getThetaDeg());
		
	}
	
	public boolean fillDefaultPi0ExclusiveHisto(Electron electronF, Proton protonF, Photon photon1, Photon photon2, String tabName, String legend){
		String prefixTitle = "";
		String suffix = "";
		if (!legend.equals("")) {
			suffix = " (" + legend + ")";
		}
		
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, Proton.mass);
		LorentzVector electronI = new LorentzVector();
		electronI.setPxPyPzM(0, 0, electronEnergy, Electron.mass);
		LorentzVector stateI = protonI.sum(electronI);
		
		LorentzVector finalStateEPGG = new LorentzVector();
		finalStateEPGG.add(electronF.getFourMomentum(), protonF.getFourMomentum(), photon1.getFourMomentum(), photon2.getFourMomentum());
		double missingMass2EPGG = stateI.substract(finalStateEPGG).mass2();
		
		LorentzVector finalStateEPG1whenP = new LorentzVector();
		finalStateEPG1whenP.add(electronF.getFourMomentum(), protonF.getFourMomentum(), photon1.getFourMomentum());
		double missingMass2EPG1whenP = stateI.substract(finalStateEPG1whenP).mass2();
		
		LorentzVector finalStateEPG2whenP = new LorentzVector();
		finalStateEPG2whenP.add(electronF.getFourMomentum(), protonF.getFourMomentum(), photon2.getFourMomentum());
		double missingMass2EPG2whenP = stateI.substract(finalStateEPG2whenP).mass2();

		LorentzVector finalStateEPwhenG = new LorentzVector();
		finalStateEPwhenG.add(electronF.getFourMomentum(), protonF.getFourMomentum());
		double missingMass2EPwhenG = stateI.substract(finalStateEPwhenG).mass2();
		
		double invMass = ComputePhysicsParameters.computePi0InvariantMass(photon1, photon2);
		
		double highestEnergy = Math.max(photon1.getEnergy(), photon2.getEnergy());
		if (highestEnergy < 2){
			return false;
		}
		
		suffix = "";
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epgg)" + suffix, missingMass2EPGG);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epg1)" + suffix, missingMass2EPG1whenP);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epg2)" + suffix, missingMass2EPG2whenP);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(ep)" + suffix, missingMass2EPwhenG);
		
		if(Math.abs(missingMass2EPGG)>0.1 || Math.abs(missingMass2EPG1whenP)>0.4 || Math.abs(missingMass2EPG2whenP)>0.4){
			return false;
		}
		
		suffix = "after epgg cut";
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epgg)" + suffix, missingMass2EPGG);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epg1)" + suffix, missingMass2EPG1whenP);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epg2)" + suffix, missingMass2EPG2whenP);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(ep)" + suffix, missingMass2EPwhenG);
		this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		if ((photon1.getThetaDeg()>5 && photon2.getThetaDeg()<5) || (photon1.getThetaDeg()<5 && photon2.getThetaDeg()>5)){
			suffix = " after epgg cut (in Fwd and FT " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		}
		if (photon1.getThetaDeg()>5 && photon2.getThetaDeg()>5){
			suffix = " after epgg cut (in Fwd " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		}
		if (photon1.getThetaDeg()<5 && photon2.getThetaDeg()<5){
			suffix = " after epgg cut (in FT " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		}
		
		if(!(0.115<invMass&&invMass<0.155)){
			return false;
		}
		
		suffix = "after M(gg) cut";
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epgg)" + suffix, missingMass2EPGG);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epg1)" + suffix, missingMass2EPG1whenP);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(epg2)" + suffix, missingMass2EPG2whenP);
		this.myCanvas.fill1DHisto(prefixTitle + "MM(ep)" + suffix, missingMass2EPwhenG);
		this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		if ((photon1.getThetaDeg()>5 && photon2.getThetaDeg()<5) || (photon1.getThetaDeg()<5 && photon2.getThetaDeg()>5)){
			suffix = " after M(gg) cut (in Fwd and FT " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		}
		if (photon1.getThetaDeg()>5 && photon2.getThetaDeg()>5){
			suffix = " after M(gg) cut (in Fwd " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		}
		if (photon1.getThetaDeg()<5 && photon2.getThetaDeg()<5){
			suffix = " after M(gg) cut (in FT " + legend + ")";
			this.myCanvas.fill1DHisto(prefixTitle + "M(#gamma#gamma)" + suffix,invMass);
		}
		
		String processName = "Pi0";
		String prefix = processName + " ";
		suffix = "";
		
		this.myCanvas.fill1DHisto(prefix + "Energy of photon (with highest energy)" + suffix, highestEnergy);
		
		
		return true;
		
	}

}
