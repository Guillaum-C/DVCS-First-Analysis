package analyser;

import java.util.ArrayList;

import org.clas12.analysisTools.constants.GeometryConstants;
import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.central.cvt.CVTEvent;
import org.clas12.analysisTools.event.central.cvt.CVTRecTrack;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.PionPlus;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.files.HipoReader;
import org.clas12.analysisTools.plots.Canvas;
import org.clas12.analysisTools.plots.detectorPlots.FTOFPlots;
import org.clas12.analysisTools.plots.detectorPlots.HTCCPlots;
import org.jlab.clas.physics.Vector3;
import org.jlab.io.base.DataBank;
import org.jlab.io.base.DataEvent;

public class Analyser {
	
	public Analyser() {
	}

	
	public static void createParticlePlots(Canvas myCanvas, String particleName){
		String tabParticle = particleName;
		myCanvas.addTab(tabParticle, 6, 3);
		myCanvas.create1DHisto(tabParticle, 1, 1, particleName+"Theta", "Theta", "Theta", 90, 0, 90);
		myCanvas.create1DHisto(tabParticle, 1, 2, particleName+"Phi", "Phi", "Phi", 360, -180, 180);
		myCanvas.create2DHisto(tabParticle, 1, 3, particleName+"ThetaPhi", "Theta vs Phi", "Phi", "Theta", 360, -180, 180, 90, 0, 90);
		
		myCanvas.create1DHisto(tabParticle, 2, 1, particleName+"TransverseMomentum", "Transverse Momentum", "Transverse Momentum", 100, 0, 2);
		myCanvas.create1DHisto(tabParticle, 2, 2, particleName+"ZMomentum", "z-Momentum", "z-Momentum", 100, 0, 10);
		myCanvas.create1DHisto(tabParticle, 2, 3, particleName+"Momentum", "Momentum", "Momentum", 100, 0, 10);
	
		myCanvas.create2DHisto(tabParticle, 3, 1, particleName+"ThetaTransverseMomentum", "Theta vs Transverse Momentum", "Theta", "Transverse Momentum", 90, 0, 90, 100, 0, 2);
		myCanvas.create2DHisto(tabParticle, 3, 2, particleName+"ThetaZMomentum", "Theta vs z-Momentum", "Theta", "z-Momentum", 90, 0, 90, 100, 0, 10);
		myCanvas.create2DHisto(tabParticle, 3, 3, particleName+"ThetaMomentum", "Theta vs Momentum", "Theta", "Momentum", 90, 0, 90, 100, 0, 10);
		
		myCanvas.create2DHisto(tabParticle, 4, 1, particleName+"PhiTransverseMomentum", "Phi vs Transverse Momentum", "Phi", "Tranverse Momentum", 360, -180, 180, 100, 0, 2);
		myCanvas.create2DHisto(tabParticle, 4, 2, particleName+"PhiZMomentum", "Phi vs z-Momentum", "Phi", "z-Momentum", 360, -180, 180, 100, 0, 10);
		myCanvas.create2DHisto(tabParticle, 4, 3, particleName+"PhiMomentum", "Phi vs Momentum", "Phi", "Momentum", 360, -180, 180, 100, 0, 10);
	
		myCanvas.create1DHisto(tabParticle, 5, 1, particleName+"VertexX", "X-vertex", "X-Vertex", 50, -10, 10);
		myCanvas.create1DHisto(tabParticle, 5, 2, particleName+"VertexY", "Y-vertex", "Y-Vertex", 50, -10, 10);
		myCanvas.create1DHisto(tabParticle, 5, 3, particleName+"VertexZ", "Z-vertex", "Z-Vertex", 100, -50, 50);
		
		myCanvas.create2DHisto(tabParticle, 6, 1, particleName+"THTCCvsTFTOF", "THTCC VS TFTOF", "TFTOF", "THTCC", 100, 100, 400, 100, 100, 400);
		myCanvas.create2DHisto(tabParticle, 6, 2, particleName+"TFTOFFvsTCALO", "TFTOF VS TCALO", "TCALO", "TFTOF", 100, 100, 400, 100, 100, 400);
		myCanvas.create2DHisto(tabParticle, 6, 3, particleName+"TCALOvsTHTCC", "TCALO VS THTCC", "THTCC", "TCALO", 100, 100, 400, 100, 100, 400);
		
	}
	
	public static void fillParticlePlots(Canvas myCanvas, String particleName, Particle particle){
		myCanvas.fill1DHisto(particleName+"Theta", particle.getThetaDeg());
		myCanvas.fill1DHisto(particleName+"Phi", particle.getPhiDeg());
		myCanvas.fill2DHisto(particleName+"ThetaPhi", particle.getPhiDeg(), particle.getThetaDeg());
		
		myCanvas.fill1DHisto(particleName+"TransverseMomentum", particle.getPt());
		myCanvas.fill1DHisto(particleName+"ZMomentum", particle.getPz());
		myCanvas.fill1DHisto(particleName+"Momentum", particle.getP());
		
		myCanvas.fill2DHisto(particleName+"ThetaTransverseMomentum", particle.getThetaDeg(), particle.getPt());
		myCanvas.fill2DHisto(particleName+"ThetaZMomentum", particle.getThetaDeg(), particle.getPz());
		myCanvas.fill2DHisto(particleName+"ThetaMomentum", particle.getThetaDeg(), particle.getP());
		
		myCanvas.fill2DHisto(particleName+"PhiTransverseMomentum", particle.getPhiDeg(), particle.getPt());
		myCanvas.fill2DHisto(particleName+"PhiZMomentum", particle.getPhiDeg(), particle.getPz());
		myCanvas.fill2DHisto(particleName+"PhiMomentum", particle.getPhiDeg(), particle.getP());
		
		myCanvas.fill1DHisto(particleName+"VertexX", particle.getVx());
		myCanvas.fill1DHisto(particleName+"VertexY", particle.getVy());
		myCanvas.fill1DHisto(particleName+"VertexZ", particle.getVz());
		
		if (particle.hasFTOFClusters()>0 && particle.hasHTCCClusters() >0){
			myCanvas.fill2DHisto(particleName+"THTCCvsTFTOF", particle.getFTOFClusters().get(0).getTime(), particle.getHTCCClusters().get(0).getTime());
		}
		if (particle.hasCalorimeterClusters()>0 && particle.hasFTOFClusters() >0){
			myCanvas.fill2DHisto(particleName+"TFTOFFvsTCALO", particle.getCalorimeterRecClusters().get(0).getTime(), particle.getFTOFClusters().get(0).getTime());
		}
		if (particle.hasHTCCClusters()>0 && particle.hasCalorimeterClusters() >0){
			myCanvas.fill2DHisto(particleName+"TCALOvsTHTCC", particle.getHTCCClusters().get(0).getTime(), particle.getCalorimeterRecClusters().get(0).getTime());
		}
		
	}
	
	public static void createParticlePlotsBySector(Canvas myCanvas, String particleName){
		String tabParticle = particleName+" by Sector";
		myCanvas.addTab(tabParticle, 8, GeometryConstants.NUMBER_OF_SECTORS+1);
		myCanvas.create1DHistoBySector(tabParticle, 1, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"Theta", "Theta", "Theta", 90, 0, 90);
		myCanvas.create2DHistoBySector(tabParticle, 2, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"ThetaPhi", "Theta vs Phi", "Phi", "Theta", 360, -180, 180, 90, 0, 90);
		
		myCanvas.create1DHistoBySector(tabParticle, 3, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"TransverseMomentum", "Transverse Momentum", "Transverse Momentum", 100, 0, 2);
		myCanvas.create1DHistoBySector(tabParticle, 4, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"Momentum", "Momentum", "Momentum", 100, 0, 10);
	
		myCanvas.create2DHistoBySector(tabParticle, 5, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"ThetaTransverseMomentum", "Theta vs Transverse Momentum", "Theta", "Transverse Momentum", 90, 0, 90, 100, 0, 2);
		myCanvas.create2DHistoBySector(tabParticle, 6, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"ThetaMomentum", "Theta vs Momentum", "Theta", "Momentum", 90, 0, 90, 100, 0, 10);
		
		myCanvas.create2DHistoBySector(tabParticle, 7, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"PhiTransverseMomentum", "Phi vs Transverse Momentum", "Phi", "Tranverse Momentum", 360, -180, 180, 100, 0, 2);
		myCanvas.create2DHistoBySector(tabParticle, 8, GeometryConstants.NUMBER_OF_SECTORS+1, particleName+"PhiMomentum", "Phi vs Momentum", "Phi", "Momentum", 360, -180, 180, 100, 0, 10);
	}
	
	public static void fillParticlePlotsBySector(Canvas myCanvas, String particleName, Particle particle){
		int sector = 0;
		myCanvas.fill1DHisto(particleName+"Theta", sector, particle.getThetaDeg());
		myCanvas.fill2DHisto(particleName+"ThetaPhi", sector, particle.getPhiDeg(), particle.getThetaDeg());
		
		myCanvas.fill1DHisto(particleName+"TransverseMomentum", sector, particle.getPt());
		myCanvas.fill1DHisto(particleName+"Momentum", sector, particle.getP());
		
		myCanvas.fill2DHisto(particleName+"ThetaTransverseMomentum", sector, particle.getThetaDeg(), particle.getPt());
		myCanvas.fill2DHisto(particleName+"ThetaMomentum", sector, particle.getThetaDeg(), particle.getP());
		
		myCanvas.fill2DHisto(particleName+"PhiTransverseMomentum", particle.getPhiDeg(), particle.getPt());
		myCanvas.fill2DHisto(particleName+"PhiMomentum", particle.getPhiDeg(), particle.getP());
		
		sector = GeometryConstants.getSector(particle.getPhiDeg());
		if (sector==0){
			return;
		}
		
		myCanvas.fill1DHisto(particleName+"Theta", sector, particle.getThetaDeg());
		myCanvas.fill2DHisto(particleName+"ThetaPhi", sector, particle.getPhiDeg(), particle.getThetaDeg());
		
		myCanvas.fill1DHisto(particleName+"TransverseMomentum", sector, particle.getPt());
		myCanvas.fill1DHisto(particleName+"Momentum", sector, particle.getP());
		
		myCanvas.fill2DHisto(particleName+"ThetaTransverseMomentum", sector, particle.getThetaDeg(), particle.getPt());
		myCanvas.fill2DHisto(particleName+"ThetaMomentum", sector, particle.getThetaDeg(), particle.getP());
		
		myCanvas.fill2DHisto(particleName+"PhiTransverseMomentum", particle.getPhiDeg(), particle.getPt());
		myCanvas.fill2DHisto(particleName+"PhiMomentum", particle.getPhiDeg(), particle.getP());
	}
	
	public static void main(String[] args) {
		
		/* ===== SETTINGS ===== */
		String path = "";
		String runNumber = "";
		double electronEnergy = 10.6;
		int maxEvents = 10000000;
		
		/* 2.2 GeV */
//		electronEnergy = 2.2;
//		path ="/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
//		runNumber = "002475";
		//		 String path ="/Users/gchristi/";
		//		 String runNumber = "002391";

		/* 6.4 GeV */
		
//		electronEnergy = 6.4;
//		path ="/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
//		runNumber = "003105";	

		/* 10.6 GeV */
		electronEnergy = 10.6;
		path = "/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/";//"volatile/clas12/data/rg-a/tag5bp2p1_newCVT_newfieldmap/cooked/";//"Data/run3532/";//"/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
//		runNumber = "003532";//"003712";
		runNumber = "003432";
		
		HipoReader hipoReader=null;
		
		/* Ask user */
		if (args.length>0 && args[0]!=null && args[1]!=null &&args[2]!=null){
			if (args[3]!=null){
				path = (String) args[0];
				System.out.println("Path: "+path);
				runNumber = (String) args[1];
				System.out.println("RunNumber: "+runNumber);
				int electronE = Integer.parseInt(args[2]);
				if (electronE==2){
					electronEnergy = 2.2;
				}else if (electronE==6){
					electronEnergy = 6.4;
				}else if (electronE==10){
					electronEnergy = 10.6;
				}
				maxEvents = Integer.parseInt(args[3]);
				hipoReader = new HipoReader(path, runNumber);
			}else{
				String file = (String) args[0];
				System.out.println("File: "+file);
				int electronE = Integer.parseInt(args[1]);
				if (electronE==2){
					electronEnergy = 2.2;

				}else if (electronE==6){
					electronEnergy = 6.4;
				}else if (electronE==10){
					electronEnergy = 10.6;
				}
				maxEvents = Integer.parseInt(args[2]);
				System.out.println("Elec energ: "+electronEnergy);

				ArrayList<String> listFiles = new ArrayList<>();
				listFiles.add(file);
				hipoReader = new HipoReader(listFiles);
			}
			
		}else{
			hipoReader = new HipoReader(path, runNumber);
		}
		
		/* ===== INITIAL STATE ===== */
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, 0.938272);
		System.out.println("Initial proton: " + protonI.toString());

		LorentzVector electronI = new LorentzVector();
		electronI.setPxPyPzM(0, 0, electronEnergy, 0.000511);
		System.out.println("Initial electron: " + electronI.toString());

		LorentzVector stateI = protonI.sum(electronI);
		System.out.println("Initial state: " + stateI.toString());

		/* ===== CREATE PLOTS ===== */
		Canvas myCanvas = new Canvas(runNumber, true);
		
		Analyser.createParticlePlots(myCanvas, "electron");
		Analyser.createParticlePlots(myCanvas, "electronNoFT");
		Analyser.createParticlePlots(myCanvas, "proton");
		Analyser.createParticlePlots(myCanvas, "photon");
		Analyser.createParticlePlots(myCanvas, "photonNoFT");
		Analyser.createParticlePlots(myCanvas, "piplus");
		Analyser.createParticlePlotsBySector(myCanvas, "electron");
		
		CalorimeterPlots caloPlots = new CalorimeterPlots(myCanvas, "CALO");
		FTOFPlots ftofPlots = new FTOFPlots(myCanvas, "FTOF");
		HTCCPlots htccPlots = new HTCCPlots(myCanvas, "HTCC");
		
		String tabCvt = "CVT +";
		myCanvas.addTab(tabCvt, 2, 3);
		myCanvas.create1DHisto(tabCvt, 1, 1, "cvtThetaP", "Theta distribution for cvt+", "Theta", 90, 0, 180);
		myCanvas.create1DHisto(tabCvt, 1, 2, "cvtPhiP", "Phi distribution for cvt+", "Phi", 90, -180, 180);
		myCanvas.create2DHisto(tabCvt, 1, 3, "cvtThetaPhiP", "Theta vs Phi distribution for cvt+", "Phi", "Theta", 90, -180, 180, 90, 0, 180);
		myCanvas.create1DHisto(tabCvt, 2, 1, "cvtMomentumP", "Momentum distribution for cvt+", "Momentum", 50, 0, 3);
		myCanvas.create2DHisto(tabCvt, 2, 2, "cvtThetaMomentumP", "Theta vs Momentum distribution for cvt+", "Momentum", "Theta", 50, 0, 3, 90, 0, 180);
		myCanvas.create2DHisto(tabCvt, 2, 3, "cvtPhiMomentumP", "Phi vs Momentum distribution for cvt+", "Momentum", "Phi", 50, 0, 3, 90, -180, 180);
		
		String tabElastic = "elastic";
		myCanvas.addTab(tabElastic, 3, 3);
		myCanvas.create2DHisto(tabElastic, 1, 1, "thetaPVSthetaE", "thetaPVSthetaE", "thetaE", "thetaP", 45, 0, 90, 45, 0, 180);
		myCanvas.create2DHisto(tabElastic, 1, 2, "thetaEVSmm", "thetaEVSmm", "mm", "thetaE", 50, -5, 15, 45, 0, 90);
		myCanvas.create1DHisto(tabElastic, 2, 1, "mm", "mm", "mm", 500, -5, 5);
		myCanvas.create1DHisto(tabElastic, 2, 2, "w", "w", "w", 500, -5, 15);
		myCanvas.create1DHisto(tabElastic, 3, 2, "wall", "wall", "wall", 500, -5, 15);
		
		Analyser.createParticlePlots(myCanvas, "DVCSelectron");
		Analyser.createParticlePlots(myCanvas, "DVCSproton");
		Analyser.createParticlePlots(myCanvas, "DVCSphoton");
		String tabDVCSAcceptance = "DVCSacceptance";
		myCanvas.addTab(tabDVCSAcceptance, 3, 3);
		myCanvas.create1DHisto(tabDVCSAcceptance, 1, 1, "dvcsThetaP", "Theta distribution for charges+", "Theta", 45, 0, 90);
		myCanvas.create1DHisto(tabDVCSAcceptance, 1, 2, "dvcsPhiP", "Phi distribution for charges+", "Phi", 180, -180, 180);
		myCanvas.create2DHisto(tabDVCSAcceptance, 1, 3, "dvcsThetaPhiP", "Theta vs Phi distribution for charges+", "Phi", "Theta", 180, -180, 180, 45, 0, 90);
		myCanvas.create1DHisto(tabDVCSAcceptance, 2, 1, "dvcsMomentumP", "Momentum distribution for charges+", "Momentum", 50, 0, 3);
		myCanvas.create2DHisto(tabDVCSAcceptance, 2, 2, "dvcsThetaMomentumP", "Theta vs Momentum distribution for charges+", "Momentum", "Theta", 50, 0, 3, 45, 0, 90);
		myCanvas.create2DHisto(tabDVCSAcceptance, 2, 3, "dvcsPhiMomentumP", "Phi vs Momentum distribution for charges+", "Momentum", "Phi", 50, 0, 3, 180, -180, 180);
		myCanvas.create1DHisto(tabDVCSAcceptance, 3, 1, "dvcsVertexEP", "Vertex eletron-charge+", "Vz", 50, -50, 50);
		myCanvas.create1DHisto(tabDVCSAcceptance, 3, 2, "dvcsVertexEG", "Vertex eletron-gamma+", "Vz", 50, -50, 50);
		myCanvas.create1DHisto(tabDVCSAcceptance, 3, 3, "dvcsVertexPG", "Vertex proton-gamma", "Vz", 50, -50, 50);
		
		String tabDVCSmmepg = "DVCSmm_epg";
		myCanvas.addTab(tabDVCSmmepg, 1, 2);
		myCanvas.create1DHisto(tabDVCSmmepg, 1, 1, "mm(ep->epg)2", "mm(ep->epg)2", "mm(ep->epg)2", 200, -5, 5);
		myCanvas.create2DHisto(tabDVCSmmepg, 1, 2, "q2VSxB(ep->epg)", "q2VSxB(ep->epg)", "xB", "q2", 100, 0, 1, 100, 0, 10);
		
		String tabDVCSmmep = "DVCSmm_ep";
		myCanvas.addTab(tabDVCSmmep, 6, 4);
		myCanvas.create1DHisto(tabDVCSmmep, 1, 1, "mm(ep->ep)2", "mm(ep->ep)2", "mm(ep->ep)2", 200, -5, 5);
		myCanvas.create2DHisto(tabDVCSmmep, 1, 2, "q2VSxB(ep->ep)", "q2VSxB(ep->ep)", "xB", "q2", 100, 0, 1, 100, 0, 10);
		myCanvas.create1DHisto(tabDVCSmmep, 1, 3, "mm(ep->ep)2whenPhot", "mm(ep->ep)2whenPhot", "mm(ep->ep)2", 200, -5, 5);
		myCanvas.create2DHisto(tabDVCSmmep, 1, 4, "q2VSxB(ep->ep)whenPhot", "q2VSxB(ep->ep)whenPhot", "xB", "q2", 100, 0, 1, 100, 0, 10);
		
		myCanvas.create1DHisto(tabDVCSmmep, 2, 1, "x-mm(ep->epg)", "x-mm(ep->epg)", "x-mm(ep->epg)", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmep, 2, 2, "y-mm(ep->epg)", "y-mm(ep->epg)", "y-mm(ep->epg)", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmep, 2, 3, "z-mm(ep->epg)", "z-mm(ep->epg)", "z-mm(ep->epg)", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmep, 2, 4, "e-mm(ep->epg)", "e-mm(ep->epg)", "e-mm(ep->epg)", 100, -5, 15);
		
		myCanvas.create1DHisto(tabDVCSmmep, 3, 1, "x-expectedPhoton", "x-expectedPhoton", "x-expectedPhoton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmep, 3, 2, "y-expectedPhoton", "y-expectedPhoton", "y-expectedPhoton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmep, 3, 3, "z-expectedPhoton", "z-expectedPhoton", "z-expectedPhoton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmep, 3, 4, "e-expectedPhoton", "e-expectedPhoton", "e-expectedPhoton", 100, -5, 15);
		
		myCanvas.create1DHisto(tabDVCSmmep, 4, 1, "x-foundPhoton", "x-foundPhoton", "x-foundPhoton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmep, 4, 2, "y-foundPhoton", "y-foundPhoton", "y-foundPhoton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmep, 4, 3, "z-foundPhoton", "z-foundPhoton", "z-foundPhoton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmep, 4, 4, "e-foundPhoton", "e-foundPhoton", "e-foundPhoton", 100, -5, 15);

		myCanvas.create1DHisto(tabDVCSmmep, 5, 1, "x-expected-foundPhoton", "x-expected-foundPhoton", "x-expected-foundPhoton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmep, 5, 2, "y-expected-foundPhoton", "y-expected-foundPhoton", "y-expected-foundPhoton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmep, 5, 3, "z-expected-foundPhoton", "z-expected-foundPhoton", "z-expected-foundPhoton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmep, 5, 4, "e-expected-foundPhoton", "e-expected-foundPhoton", "e-expected-foundPhoton", 100, -5, 15);

		myCanvas.create1DHisto(tabDVCSmmep, 6, 1, "theta-expectedPhoton", "theta-expectedPhoton", "theta", 100, 0, 90);
		myCanvas.create1DHisto(tabDVCSmmep, 6, 2, "theta-foundPhoton", "theta-foundPhoton", "theta", 100, 0, 90);
		myCanvas.create1DHisto(tabDVCSmmep, 6, 3, "theta-expected-foundPhoton", "theta-expected-foundPhoton", "theta-expected-foundPhoton", 100, -45, 45);
		myCanvas.create1DHisto(tabDVCSmmep, 6, 4, "missingPz(ep->epg)", "missingPz(ep->epg)", "missingPz(ep->epg)", 100, -10, 10);
		

		String tabDVCSmmeg = "DVCSmm_eg";
		myCanvas.addTab(tabDVCSmmeg, 6, 4);
		myCanvas.create1DHisto(tabDVCSmmeg, 1, 1, "mm(ep->eg)2", "mm(ep->eg)2", "mm(ep->eg)2", 200, -5, 5);
		myCanvas.create2DHisto(tabDVCSmmeg, 1, 2, "q2VSxB(ep->eg)", "q2VSxB(ep->eg)", "xB", "q2", 100, 0, 1, 100, 0, 10);
		
		myCanvas.create1DHisto(tabDVCSmmeg, 2, 1, "mm(ep->eg)2whenProt", "mm(ep->eg)2whenProt", "mm(ep->eg)2", 200, -5, 5);
		myCanvas.create2DHisto(tabDVCSmmeg, 2, 2, "q2VSxB(ep->eg)whenProt", "q2VSxB(ep->eg)whenProt", "xB", "q2", 100, 0, 1, 100, 0, 10);
		
		myCanvas.create1DHisto(tabDVCSmmeg, 3, 1, "x-expectedProton", "x-expectedProton", "x-expectedProton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmeg, 3, 2, "y-expectedProton", "y-expectedProton", "y-expectedProton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmeg, 3, 3, "z-expectedProton", "z-expectedProton", "z-expectedProton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmeg, 3, 4, "e-expectedProton", "e-expectedProton", "e-expectedProton", 100, -5, 15);
		
		myCanvas.create1DHisto(tabDVCSmmeg, 4, 1, "x-foundProton", "x-foundProton", "x-foundProton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmeg, 4, 2, "y-foundProton", "y-foundProton", "y-foundProton", 100, -5, 5);
		myCanvas.create1DHisto(tabDVCSmmeg, 4, 3, "z-foundProton", "z-foundProton", "z-foundProton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmeg, 4, 4, "e-foundProton", "e-foundProton", "e-foundProton", 100, -5, 15);

		myCanvas.create1DHisto(tabDVCSmmeg, 5, 1, "x-expected-foundProton", "x-expected-foundProton", "x-expected-foundProton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmeg, 5, 2, "y-expected-foundProton", "y-expected-foundProton", "y-expected-foundProton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmeg, 5, 3, "z-expected-foundProton", "z-expected-foundProton", "z-expected-foundProton", 100, -5, 15);
		myCanvas.create1DHisto(tabDVCSmmeg, 5, 4, "e-expected-foundProton", "e-expected-foundProton", "e-expected-foundProton", 100, -5, 15);

		myCanvas.create1DHisto(tabDVCSmmeg, 6, 1, "theta-expectedProton", "theta-expectedProton", "theta", 100, 0, 90);
		myCanvas.create1DHisto(tabDVCSmmeg, 6, 2, "theta-foundProton", "theta-foundProton", "theta", 100, 0, 90);
		myCanvas.create1DHisto(tabDVCSmmeg, 6, 3, "theta-expected-foundProton", "theta-expected-foundProton", "theta-expected-foundProton", 100, -45, 45);


		String tabSummary = "summary";
		myCanvas.addTab(tabSummary, 1, 2);
		myCanvas.create1DHisto(tabSummary, 1, 1, "cleaning(ep->ep)", "cleaning(ep->ep)", "cuts", 11, -1, 10);
		myCanvas.create1DHisto(tabSummary, 1, 2, "cleaning(ep->epg)", "cleaning(ep->epg)", "cuts", 11, -1, 10);
		
		
		/* ===== ITERATE ===== */
		int eventIterator = 0;
		while (eventIterator < maxEvents && hipoReader.hasNextEvent()) {
			eventIterator++;
			if (eventIterator%5000==0) System.out.println("Event: " + eventIterator);
			DataEvent dataEvent = hipoReader.getNextEvent();
			Event processedEvent = new Event();
			processedEvent.readBanks(dataEvent);
			
//			KinematicalCorrections.correct2GeV100OutbendingTorus(dataEvent);

//			Cuts cut = new Cuts();
//			
			/* ===== ELECTRON ===== */
			if (processedEvent.getParticleEvent().hasNumberOfElectrons() > 0) {
				ArrayList<Electron> electrons = processedEvent.getParticleEvent().getElectrons();
				for (Particle electron : electrons) {
					Analyser.fillParticlePlots(myCanvas, "electron", electron);
					if (electron.getThetaDeg()>5){
						Analyser.fillParticlePlots(myCanvas, "electronNoFT", electron);
						Analyser.fillParticlePlotsBySector(myCanvas, "electron", electron);
					}
				}
			}

			/* ===== PROTON ===== */
			if (processedEvent.getParticleEvent().hasNumberOfProtons() > 0) {
				ArrayList<Proton> protons = processedEvent.getParticleEvent().getProtons();
				for (Particle proton : protons) {
					Analyser.fillParticlePlots(myCanvas, "proton", proton);
				}
			}
			
			/* ===== PHOTON ===== */
			if (processedEvent.getParticleEvent().hasNumberOfPhotons() > 0) {
				ArrayList<Photon> photons = processedEvent.getParticleEvent().getPhotons();
				for (Particle photon : photons) {
					Analyser.fillParticlePlots(myCanvas, "photon", photon);
					if (photon.getThetaDeg()>5){
						Analyser.fillParticlePlots(myCanvas, "photonNoFT", photon);
					}
				}
			}
			
			/* ===== PI+ ===== */
			if (processedEvent.getParticleEvent().hasNumberOfPiPlus() > 0) {
				ArrayList<PionPlus> pipluss = processedEvent.getParticleEvent().getPiPlus();
				for (Particle piplus : pipluss) {
					Analyser.fillParticlePlots(myCanvas, "piplus", piplus);
				}
			}
			
			caloPlots.fillHistograms(processedEvent);
			ftofPlots.fillHistogram(processedEvent);
			htccPlots.fillHistogram(processedEvent);
			
			/* ===== CVT ===== */
//			for (Particle particle : processedEvent.getParticleEvent().getParticles()){
//				if ( particle.hasCentralTrack()==1 && particle.getCharge()==1 && particle.getCvtRecTrack().getNdf()>3 && particle.getCvtRecTrack().getChi2()<10 ){
//					//System.out.println("cvtThetaP: " +particle.getThetaDeg()+ "  cvtPhiP: "+particle.getPhiDeg()+"  cvtMomentumP: "+ particle.getMomentum().mag());
//					myCanvas.fill1DHisto("cvtThetaP", particle.getThetaDeg(), 1);
//					myCanvas.fill1DHisto("cvtPhiP", particle.getPhiDeg(), 1);
//					myCanvas.fill2DHisto("cvtThetaPhiP", particle.getPhiDeg(), particle.getThetaDeg(), 1);
//
//					myCanvas.fill1DHisto("cvtMomentumP", particle.getMomentum().mag(), 1);
//					myCanvas.fill2DHisto("cvtThetaMomentumP", particle.getMomentum().mag(), particle.getThetaDeg(), 1);
//					myCanvas.fill2DHisto("cvtPhiMomentumP", particle.getMomentum().mag(), particle.getPhiDeg(), 1);
//				}
//			}
			
			
			CVTEvent cvtEvent = processedEvent.getCentralEvent().getCvtEvent();
			for (CVTRecTrack cvtTracks : cvtEvent.getCvtTracks()){
				myCanvas.fill1DHisto("cvtThetaP", Math.toDegrees(cvtTracks.getMomentum().theta()), 1);
				myCanvas.fill1DHisto("cvtPhiP", Math.toDegrees(cvtTracks.getMomentum().phi()), 1);
				myCanvas.fill2DHisto("cvtThetaPhiP", Math.toDegrees(cvtTracks.getMomentum().phi()), Math.toDegrees(cvtTracks.getMomentum().theta()), 1);

				myCanvas.fill1DHisto("cvtMomentumP", cvtTracks.getMomentum().mag(), 1);
				myCanvas.fill2DHisto("cvtThetaMomentumP", cvtTracks.getMomentum().mag(), Math.toDegrees(cvtTracks.getMomentum().theta()), 1);
				myCanvas.fill2DHisto("cvtPhiMomentumP", cvtTracks.getMomentum().mag(), Math.toDegrees(cvtTracks.getMomentum().phi()), 1);
			}
			/* ===== ELASTIC ===== */
			if (processedEvent.getParticleEvent().hasNumberOfElectrons() > 0) {
				ArrayList<Electron> electrons = processedEvent.getParticleEvent().getElectrons();
				for (Particle electron : electrons) {
					
					LorentzVector electronF = electron.getFourMomentum();
					LorentzVector virtualPhoton = electronI.substract(electronF);
					double q2 = - virtualPhoton.mass2();
//					System.out.println("Q2 phot: "+virtualPhoton.mass());
//
					double xB = q2/( 2*0.938272*( electronI.e()-electronF.e() ) );
//
					double W2 = q2*(1/xB -1) + 0.938272*0.938272;
					

					double wall = electronF.substract(stateI).m2();
					myCanvas.fill1DHisto("wall", wall);
					
					
					for (Particle particle : processedEvent.getParticleEvent().getParticles()){
						if ( W2>4 && electron.getEnergy()>1 && particle.hasCentralTrack()==1 && particle.getCharge()==1 && particle.getCvtRecTrack().getNdf()>3 && particle.getCvtRecTrack().getChi2()<100 ){
							
//							thetaPVSthetaE
							myCanvas.fill2DHisto("thetaPVSthetaE", electron.getThetaDeg(), particle.getThetaDeg());
							
							LorentzVector proton = new LorentzVector();
							proton.setPxPyPzM(particle.getPx(), particle.getPy(), particle.getPz(), Proton.mass);
							LorentzVector stateF = electron.getFourMomentum().sum(proton);
							LorentzVector diff = stateF.substract(stateI);
							double mm = diff.m2();
							double w = electron.getFourMomentum().substract(stateI).m2();
							
							myCanvas.fill2DHisto("thetaEVSmm", mm, electron.getThetaDeg());
							
							myCanvas.fill1DHisto("mm", mm);
							myCanvas.fill1DHisto("w", w);
							
							//System.out.println("cvtThetaP: " +particle.getCvtRecTrack().getThetaDeg()+ "  cvtPhiP: "+particle.getPhiDeg()+"  cvtMomentumP: "+ particle.getMomentum().mag());
//					myCanvas.fill1DHisto("cvtThetaP", particle.getThetaDeg(), 1);
//					myCanvas.fill1DHisto("cvtPhiP", particle.getPhiDeg(), 1);
//					myCanvas.fill2DHisto("cvtThetaPhiP", particle.getPhiDeg(), particle.getThetaDeg(), 1);
//					
//					myCanvas.fill1DHisto("cvtMomentumP", particle.getMomentum().mag(), 1);
//					myCanvas.fill2DHisto("cvtThetaMomentumP", particle.getMomentum().mag(), particle.getThetaDeg(), 1);
//					myCanvas.fill2DHisto("cvtPhiMomentumP", particle.getMomentum().mag(), particle.getPhiDeg(), 1);
					
					
					
						}
					}
				}
			}
			
			/* ===== DVCS ===== */
			
			double q2cut = 1;
			
			myCanvas.fill1DHisto("cleaning(ep->ep)", 0);
			myCanvas.fill1DHisto("cleaning(ep->epg)", 0);
			
			
			/* EP -> EG */
			ArrayList<Electron> electrons = processedEvent.getParticleEvent().getElectrons();
			
			
			for (Particle electron : electrons) {
				LorentzVector electronF = electron.getFourMomentum();
				LorentzVector virtualPhoton = electronI.substract(electronF);
				
				double q2 = - virtualPhoton.mass2();
				double xB = q2/( 2*Proton.mass*( electronI.e()-electronF.e() ) );
				double w2 = q2*(1/xB -1) + Proton.mass*Proton.mass;
				
				if ( q2>q2cut && w2>4 && electron.getEnergy()>2 ){

					ArrayList<Photon> photons = processedEvent.getParticleEvent().getPhotons();
					for (Particle photon : photons) {
						
						if (photon.getEnergy()>7){
							LorentzVector photonF = photon.getFourMomentum();
						
							LorentzVector stateF = electronF.sum(photonF);
							LorentzVector difference = stateI.substract(stateF);
							double missingMassEG = difference.m2();
							

							myCanvas.fill1DHisto("mm(ep->eg)2", missingMassEG);
							myCanvas.fill2DHisto("q2VSxB(ep->eg)", xB, q2);
							
							
							LorentzVector diffNoProton = stateI.substract(photonF.sum(electronF));
							double expectedProtonTheta = Math.toDegrees(diffNoProton.theta());
							
							for (Particle chargePos : processedEvent.getParticleEvent().getParticles()){
								if ( !(chargePos.hasCentralTrack()==1 && chargePos.getCharge()==1 && processedEvent.getParticleEvent().hasNumberOfElectrons()>0 && processedEvent.getParticleEvent().hasNumberOfPhotons()>0) ){
									continue;
								}
								
								if ( chargePos.getCvtRecTrack().getNdf()>3 && electron.getEnergy()>2 ){
									LorentzVector protonF = new LorentzVector();
									protonF.setPxPyPzM(chargePos.getPx(), chargePos.getPy(), chargePos.getPz(), Proton.mass);
									

									myCanvas.fill1DHisto("x-expectedProton", diffNoProton.px());
									myCanvas.fill1DHisto("y-expectedProton", diffNoProton.py());
									myCanvas.fill1DHisto("z-expectedProton", diffNoProton.pz());
									myCanvas.fill1DHisto("e-expectedProton", diffNoProton.e());
									
									myCanvas.fill1DHisto("x-foundProton", chargePos.getPx());
									myCanvas.fill1DHisto("y-foundProton", chargePos.getPy());
									myCanvas.fill1DHisto("z-foundProton", chargePos.getPz());
									myCanvas.fill1DHisto("e-foundProton", chargePos.getEnergy());
									
									myCanvas.fill1DHisto("x-expected-foundProton", diffNoProton.px()-chargePos.getPx());
									myCanvas.fill1DHisto("y-expected-foundProton", diffNoProton.py()-chargePos.getPy());
									myCanvas.fill1DHisto("z-expected-foundProton", diffNoProton.pz()-chargePos.getPz());
									myCanvas.fill1DHisto("e-expected-foundProton", diffNoProton.e()-chargePos.getEnergy());
									
									myCanvas.fill1DHisto("theta-expectedProton", expectedProtonTheta);
									myCanvas.fill1DHisto("theta-foundProton", chargePos.getThetaDeg());
									myCanvas.fill1DHisto("theta-expected-foundProton", expectedProtonTheta-chargePos.getThetaDeg());
									
								}
								

							}
						}
					}
				
					
				}

			}
			
			/* EP -> EP */
			/* EP -> EPG */
			for (Particle chargePos : processedEvent.getParticleEvent().getParticles()){
				if ( !(chargePos.hasCentralTrack()==1 && chargePos.getCharge()==1 && processedEvent.getParticleEvent().hasNumberOfElectrons()>0 && processedEvent.getParticleEvent().hasNumberOfPhotons()>0) ){
					continue;
				}
				
//				ArrayList<Electron> electrons = processedEvent.getParticleEvent().getElectrons();
				for (Particle electron : electrons) {
					myCanvas.fill1DHisto("cleaning(ep->ep)", 1);
					LorentzVector electronF = electron.getFourMomentum();
					
					LorentzVector protonF = new LorentzVector();
					protonF.setPxPyPzM(chargePos.getPx(), chargePos.getPy(), chargePos.getPz(), Proton.mass);
					LorentzVector virtualPhoton = electronI.substract(electronF);
					
					double q2 = - virtualPhoton.mass2();
					double xB = q2/( 2*Proton.mass*( electronI.e()-electronF.e() ) );
					double w2 = q2*(1/xB -1) + Proton.mass*Proton.mass;
					
					if (q2>q2cut){
						myCanvas.fill1DHisto("cleaning(ep->ep)", 2);
						if (w2>4){
							myCanvas.fill1DHisto("cleaning(ep->ep)", 3);
							if (chargePos.getCvtRecTrack().getNdf()>3){
								myCanvas.fill1DHisto("cleaning(ep->ep)", 4);
								if (electron.getEnergy()>2){
									myCanvas.fill1DHisto("cleaning(ep->ep)", 5);
									
								}
								
							}
						}
					} 
					
					if ( q2>q2cut && w2>4 && chargePos.getCvtRecTrack().getNdf()>3 && electron.getEnergy()>2 ){
						myCanvas.fill1DHisto("cleaning(ep->ep)", 6);
						

						
						LorentzVector stateF = electronF.sum(protonF);
						LorentzVector difference = stateI.substract(stateF);
						double missingMassEP = difference.m2();
						
						myCanvas.fill1DHisto("mm(ep->ep)2", missingMassEP);
						myCanvas.fill2DHisto("q2VSxB(ep->ep)", xB, q2);
					}
					
					ArrayList<Photon> photons = processedEvent.getParticleEvent().getPhotons();
					for (Particle photon : photons) {
						
						if (photon.getEnergy()<7){
							continue;
						}
						
						myCanvas.fill1DHisto("cleaning(ep->epg)", 1);
						
						LorentzVector photonF = photon.getFourMomentum();
						
						Vector3 v1=virtualPhoton.vect().cross(electronF.vect());
						Vector3 v2=virtualPhoton.vect().cross(photonF.vect());
						double phi= Math.acos( v1.dot(v2)/(v1.mag()*v2.mag()) );
						
						LorentzVector stateF = electronF.sum(photonF, protonF);
						LorentzVector difference = stateI.substract(stateF);
						double missingMass = difference.m2();
						
						double anglePhotonElectron = Math.acos( virtualPhoton.vect().dot(photonF.vect()) / (virtualPhoton.vect().mag()*photonF.vect().mag()) );
					

						if (q2>q2cut){
							myCanvas.fill1DHisto("cleaning(ep->epg)", 2);
							if (w2>4){
								myCanvas.fill1DHisto("cleaning(ep->epg)", 3);
								if (chargePos.getCvtRecTrack().getNdf()>3){
									myCanvas.fill1DHisto("cleaning(ep->epg)", 4);
									if (electron.getEnergy()>2){
										myCanvas.fill1DHisto("cleaning(ep->epg)", 5);
										if(anglePhotonElectron<Math.PI/18){
											myCanvas.fill1DHisto("cleaning(ep->epg)", 6);
										}
										
									}
									
								}
							}
						} 
						
						if ( q2>q2cut && w2>4 && chargePos.getCvtRecTrack().getNdf()>3 && electron.getEnergy()>2 /* && anglePhotonElectron<Math.PI/18 */){
							
							
							LorentzVector stateFEP = electronF.sum(protonF);
							LorentzVector differenceEP = stateI.substract(stateFEP);
							double missingMassEP = differenceEP.m2();
							myCanvas.fill2DHisto("q2VSxB(ep->ep)whenPhot", xB, q2);
							myCanvas.fill1DHisto("mm(ep->ep)2whenPhot", missingMassEP);
							
							LorentzVector stateFEG = electronF.sum(photonF);
							LorentzVector differenceEG = stateI.substract(stateFEG);
							double missingMassEG = differenceEG.m2();
							myCanvas.fill1DHisto("mm(ep->eg)2whenProt", missingMassEG);
							myCanvas.fill2DHisto("q2VSxB(ep->eg)whenProt", xB,q2);
							
							
							Analyser.fillParticlePlots(myCanvas, "DVCSelectron", electron);
							Analyser.fillParticlePlots(myCanvas, "DVCSproton", chargePos);
							Analyser.fillParticlePlots(myCanvas, "DVCSphoton", photon);
							
							myCanvas.fill1DHisto("dvcsThetaP", chargePos.getThetaDeg(), 1);
							myCanvas.fill1DHisto("dvcsPhiP", chargePos.getPhiDeg(), 1);
							myCanvas.fill2DHisto("dvcsThetaPhiP", chargePos.getPhiDeg(), chargePos.getThetaDeg(), 1);
							myCanvas.fill1DHisto("dvcsMomentumP", chargePos.getMomentum().mag(), 1);
							myCanvas.fill2DHisto("dvcsThetaMomentumP", chargePos.getMomentum().mag(), chargePos.getThetaDeg(), 1);
							myCanvas.fill2DHisto("dvcsPhiMomentumP", chargePos.getMomentum().mag(), chargePos.getPhiDeg(), 1);
							
							myCanvas.fill1DHisto("dvcsVertexEP", electron.getVz()-chargePos.getVz());
							myCanvas.fill1DHisto("dvcsVertexEG", electron.getVz()-photon.getVz());
							myCanvas.fill1DHisto("dvcsVertexPG", chargePos.getVz()-photon.getVz());
							
							myCanvas.fill1DHisto("mm(ep->epg)2", missingMass);
							myCanvas.fill2DHisto("q2VSxB(ep->epg)", xB, q2);
							
							
							myCanvas.fill1DHisto("x-mm(ep->epg)", difference.px());
							myCanvas.fill1DHisto("y-mm(ep->epg)", difference.py());
							myCanvas.fill1DHisto("z-mm(ep->epg)", difference.pz());
							myCanvas.fill1DHisto("e-mm(ep->epg)", difference.e());
							
							LorentzVector diffNoPhoton = stateI.substract(protonF.sum(electronF));
							double expectedPhotonTheta = Math.toDegrees(diffNoPhoton.theta());
							myCanvas.fill1DHisto("x-expectedPhoton", diffNoPhoton.px());
							myCanvas.fill1DHisto("y-expectedPhoton", diffNoPhoton.py());
							myCanvas.fill1DHisto("z-expectedPhoton", diffNoPhoton.pz());
							myCanvas.fill1DHisto("e-expectedPhoton", diffNoPhoton.e());
							
							myCanvas.fill1DHisto("x-foundPhoton", photon.getPx());
							myCanvas.fill1DHisto("y-foundPhoton", photon.getPy());
							myCanvas.fill1DHisto("z-foundPhoton", photon.getPz());
							myCanvas.fill1DHisto("e-foundPhoton", photon.getEnergy());
							
							myCanvas.fill1DHisto("x-expected-foundPhoton", diffNoPhoton.px()-photon.getPx());
							myCanvas.fill1DHisto("y-expected-foundPhoton", diffNoPhoton.py()-photon.getPy());
							myCanvas.fill1DHisto("z-expected-foundPhoton", diffNoPhoton.pz()-photon.getPz());
							myCanvas.fill1DHisto("e-expected-foundPhoton", diffNoPhoton.e()-photon.getEnergy());
							
							myCanvas.fill1DHisto("theta-expectedPhoton", expectedPhotonTheta);
							myCanvas.fill1DHisto("theta-foundPhoton", photon.getThetaDeg());
							myCanvas.fill1DHisto("theta-expected-foundPhoton", expectedPhotonTheta-photon.getThetaDeg());
							
							if ( Math.abs(expectedPhotonTheta-photon.getThetaDeg())<5 ){
								myCanvas.fill1DHisto("cleaning(ep->epg)", 7);
								double missingZ = electronI.pz()+protonI.pz() - electronF.pz() - protonF.pz() - photonF.pz();
								myCanvas.fill1DHisto("missingPz(ep->epg)", missingZ);
							}
							
							
							//myCanvas.fill1DHisto("mm(ep->ep)2whenPhot", missingMassEP);
						}
					}
				}
			}	
		}
	}
	
	public static void main2(String[] args) {
		
		/* ===== INITIAL STATE ===== */
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, 0.938272);
		System.out.println("Initial proton: " + protonI.toString());

		LorentzVector electronI = new LorentzVector();
		// electronI.setPxPyPzM(0, 0, 2.2, 0.000511);
//		System.out.println("Initial electron: " + electronI.toString());

		/* ===== OPEN FILE ===== */
		/* 2.2 GeV */
//		 String path ="/Users/gchristi/Donnees_JLab/EngineeringRun_Part2_January";
//		 String runNumber = "002475";
//		 String path ="/Users/gchristi/";
//		 String runNumber = "002391";
//		 electronI.setPxPyPzM(0, 0, 2.2, 0.000511);
//		 HipoReader hipoReader = new HipoReader(path, runNumber);

		/* 6.4 GeV */
//		 String path = (String) args[0];
//		 String path = "/Users/gchristi/";
//		 electronI.setPxPyPzM(0, 0, 6.4, 0.000511);
////		 String runNumber = (String) args[1];
//		 String runNumber = "003105";
//		 HipoReader hipoReader = new HipoReader(path, runNumber);

		/* 10.5 GeV */
		 String path = (String) args[0];
//		String path = "/Users/gchristi/";
		electronI.setPxPyPzM(0, 0, 10.6, 0.000511);
		 String runNumber = (String) args[1];
//		String runNumber = "002915";
//		 String runNumber = "003432";
		HipoReader hipoReader = new HipoReader(path, runNumber);

		LorentzVector initialState = protonI.sum(electronI);
		System.out.println("Initial state: " + initialState.toString());

		/* ===== CREATE PLOTS ===== */
		Canvas myCanvas = new Canvas(runNumber);

		myCanvas.addTab("electron", 1, 1);
		myCanvas.addTab("proton", 1, 1);
		myCanvas.addTab("photon", 1, 1);
		myCanvas.addTab("piplus", 1, 1);
		
		myCanvas.addTab("elasticDistrib", 4, 2);
		myCanvas.addTab("elastic", 4, 4);
		
		myCanvas.addTab("Momentum", 4, 1);
		myCanvas.addTab("DVCS", 6, 4);
		myCanvas.addTab("EP", 4, 3);
		myCanvas.addTab("EG", 4, 2);
		myCanvas.addTab("Vertex", 3, 6);
		myCanvas.addTab("Asym", 2, 1);
		// myCanvas.addTab("Pi0", 1, 1);
		
		myCanvas.create2DHisto("electron", 1, 1, "histoElectron", "Theta vs Phi for electrons", "Phi", "Theta", 360, -180, 180, 90, 0, 90);
		// myCanvas.add2DMap("electron", 1, 2, "histo2Electron", "Phi", "Theta",
		// 360, -180, 180, 90, 0, 90, false);
		
		myCanvas.create2DHisto("proton", 1, 1, "histoProton", "Theta vs Phi for protons", "Phi", "Theta", 360, -180, 180, 90, 0, 90);
		
		myCanvas.create2DHisto("photon", 1, 1, "histoPhoton", "Theta vs Phi for photons", "Phi", "Theta", 360, -180, 180, 90, 0, 90);
		// myCanvas.add2DMap("photon", 1, 2, "histo2Photon", "Phi", "Theta",
		// 360, -180, 180, 90, 0, 90, false);
		
		myCanvas.create2DHisto("piplus", 1, 1, "histoPiplus", "Theta vs Phi for piplus", "Phi", "Theta", 360, -180, 180, 90, 0, 90);
		
		myCanvas.create1DHisto("elastic", 1, 1, "Elastic missing mass", "Elastic missing mass","missingMass ep -> e", 500, 0.5, 2);
		myCanvas.create1DHisto("elastic", 1, 2, "Elastic missing mass after cuts", "Elastic missing mass after cuts","missingMass ep -> e", 500, 0.5, 2);
		myCanvas.create1DHisto("elastic", 1, 3, "trackChi", "trackChi","chi", 100, 0, 1000);
		myCanvas.setLogY("elastic", 1, 3, true);

		myCanvas.create2DHisto("elastic", 2, 1, "phiCentralvsForward", "phiCentralvsForward","PhiMissing", "PhiProton", 360, -180, 180, 360, -180, 180);
		myCanvas.create2DHisto("elastic", 2, 3, "thetaCentralvsForward", "thetaCentralvsForward","ThetaMissing", "ThetaProton", 90, 0, 90, 90, 0, 90);

		myCanvas.create1DHisto("elastic", 2, 2, "phiCentralMinusForward", "phiCentralMinusForward","PhiResolution", 100, -20, 20);
		myCanvas.create1DHisto("elastic", 2, 4, "thetaCentralMinusForward", "thetaCentralMinusForward","ThetaResolution", 100, -20, 20);

		myCanvas.create2DHisto("elastic", 3, 1, "pCentralvsForward", "pCentralvsForward","PMissing", "PProton", 200, -0, 1.1, 200, -0, 1.1);
		myCanvas.create2DHisto("elastic", 3, 3, "ptCentralvsForward", "ptCentralvsForward","PTMissing", "PTProton", 200, -0, 1.1, 200, -0, 1.1);

		myCanvas.create1DHisto("elastic", 3, 2, "pCentralMinusForward", "pCentralMinusForward","PResolution",200, - 0.5, 0.5);
		myCanvas.create1DHisto("elastic", 3, 4, "ptCentralMinusForward", "ptCentralMinusForward","PTResolution", 200, -0.5, 0.5);

		myCanvas.create2DHisto("elastic", 4, 1, "dp/pVSp", "dp/pVSp","PForward", "DP/P (%)", 200, 0, 1, 200, -100, 100);
		myCanvas.create2DHisto("elastic", 4, 3, "dpt/ptVSpt", "dpt/ptVSpt","PTForward", "DPT/PT (%)", 200, 0, 1, 200, -100, 100);

		myCanvas.create1DHisto("elastic", 4, 2, "dp/p", "dp/p", "DP/P (%)",200, -100, 100);
		myCanvas.create1DHisto("elastic", 4, 4, "dpt/pt", "dpt/pt", "DPT/PT (%)", 200, -100, 100);

		myCanvas.create1DHisto("elasticDistrib", 1, 1, "pCentral", "pCentral", "pCentral", 200, 0, 1.5);
		myCanvas.create1DHisto("elasticDistrib", 1, 2, "expectedpCentral", "expectedpCentral", "expectedpCentral", 200, 0, 1.5);

		myCanvas.create1DHisto("elasticDistrib", 1, 3, "ptCentral", "ptCentral", "ptCentral", 200, 0, 1.5);
		myCanvas.create1DHisto("elasticDistrib", 1, 4, "expectedptCentral", "expectedptCentral", "expectedptCentral", 200, 0, 1.5);

		myCanvas.create1DHisto("elasticDistrib", 2, 1, "phiCentral", "phiCentral", "phiCentral", 360, -180, 180);
		myCanvas.create1DHisto("elasticDistrib", 2, 2, "expectedphiCentral", "expectedphiCentral", "expectedphiCentral", 360, -180, 180);

		myCanvas.create1DHisto("elasticDistrib", 2, 3, "thetaCentral", "thetaCentral", "thetaCentral", 180, 0, 100);
		myCanvas.create1DHisto("elasticDistrib", 2, 4, "expectedthetaCentral", "expectedthetaCentral", "expectedthetaCentral", 180, 0, 100);

//		myCanvas.addStatBox("elastic", 1, 1, 1111);
//		myCanvas.addStatBox("elastic", 1, 2, 1, 1, 1, 1, 0, 0, 0);
//		myCanvas.addStatBox("elastic", 1, 3, 1, 1, 0, 1, 1, 0, 1);
//		myCanvas.fill1DDistrib("pCentral", p, 1);
//		myCanvas.fill1DDistrib("expectedpCentral", expectedP, 1);
//		
//		myCanvas.fill1DDistrib("ptCentral", pt, 1);
//		myCanvas.fill1DDistrib("expectedptCentral", expectedPT, 1);
//		
//		myCanvas.fill1DDistrib("phiCentral", phi0, 1);
//		myCanvas.fill1DDistrib("expectedphiCentral", expectedPhi, 1);
//		
//		myCanvas.fill1DDistrib("thetaCentral", theta, 1);
//		myCanvas.fill1DDistrib("expectedthetaCentral", expectedTheta, 1);
		
		
		
//		myCanvas.fill2DMap("dp/pVSp", (expectedP - p)/expectedP, expectedP, 1);
//		myCanvas.fill2DMap("dpt/ptVSpt", (expectedPT-pt)/expectedPT, expectedPT, 1);
//		myCanvas.fill1DDistrib("dp/pVSp", (expectedP - p)/expectedP, 1);
//		myCanvas.fill1DDistrib("dpt/pVSpt", (expectedPT - pt)/expectedPT, 1);
		
//		myCanvas.add1DDistrib("Momentum", 1, 1, "totMomX", "totMomX", 500, -5, 5, false);
//		myCanvas.add1DDistrib("Momentum", 1, 2, "totMomY", "totMomY", 500, -5, 5, false);
//		myCanvas.add1DDistrib("Momentum", 1, 3, "totMomZ", "totMomZ", 500, -10, 18, false);
//
//		myCanvas.add1DDistrib("DVCS", 1, 1, "missingMassSingleElec", "missingMass ep -> e", 500, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 1, 2, "missingMassMaxEnergElec", "missingMass ep -> e", 500, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 1, 3, "missingMassSingleElec>Gev", "missingMass ep -> e", 500, 0.5, 2, false);
//		
//		myCanvas.add1DDistrib("DVCS", 4, 1, "missingMassSingleElec1", "missingMass ep -> e", 50, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 4, 2, "missingMassSingleElec2", "missingMass ep -> e", 50, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 4, 3, "missingMassSingleElec3", "missingMass ep -> e", 50, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 4, 4, "missingMassSingleElec4", "missingMass ep -> e", 50, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 4, 5, "missingMassSingleElec5", "missingMass ep -> e", 50, 0.5, 2, false);
//		myCanvas.add1DDistrib("DVCS", 4, 6, "missingMassSingleElec6", "missingMass ep -> e", 50, 0.5, 2, false);
//
//		myCanvas.add1DDistrib("DVCS", 2, 1, "finalMomX", "finalMomX", 500, -5, 5, false);
//		myCanvas.add1DDistrib("DVCS", 2, 2, "finalMomY", "finalMomY", 500, -5, 5, false);
//		myCanvas.add1DDistrib("DVCS", 2, 3, "finalMomZ", "finalMomZ", 500, -10, 10, false);
//		myCanvas.add1DDistrib("DVCS", 2, 4, "finalMomE", "finalMomE", 500, -10, 10, false);
//
//		myCanvas.add2DMap("DVCS", 3, 1, "electronDVCS", "Phi", "Theta", 180, -180, 180, 45, 0, 90, false);
//		myCanvas.add2DMap("DVCS", 3, 2, "protonDVCS", "Phi", "Theta", 180, -180, 180, 45, 0, 90, false);
//		myCanvas.add2DMap("DVCS", 3, 3, "photonDVCS", "Phi", "Theta", 180, -180, 180, 45, 0, 90, false);
//		myCanvas.add2DMap("DVCS", 3, 4, "photonWPhi", "Phi", "W", 360, -180, 180, 500, 0.5, 1.5, false);
//		
//		
//
//		myCanvas.add2DMap("EP", 1, 1, "TElecVSTProt", "TElec", "TProt", 100, 100, 300, 100, 100, 300, false);
//		myCanvas.add1DDistrib("EP", 1, 2, "TElecMINUSTProt", "TElecMINUSTProt", 50, -100, 20, false);
//		myCanvas.add2DMap("EP", 1, 3, "MissingMassVSTime", "missingMass ep->ep", "TElecMINUSTProt", 50, -4, 4, 50, -100,
//				20, false);
//		myCanvas.add1DDistrib("EP", 1, 4, "missingMassEP", "missingMass ep -> ep", 50, -4, 4, false);
//
//		myCanvas.add2DMap("EP", 2, 1, "electron ep", "Phi", "Theta", 360, -180, 180, 45, 0, 90, false);
//		myCanvas.add2DMap("EP", 2, 2, "proton ep", "Phi", "Theta", 180, -180, 180, 45, 0, 90, false);
//		myCanvas.add2DMap("EP", 2, 3, "photon ep", "Phi", "Theta", 180, -180, 180, 45, 0, 90, false);
//		myCanvas.add2DMap("EP", 2, 4, "piplus ep", "Phi", "Theta", 180, -180, 180, 45, 0, 90, false);
//
//		// myCanvas.add2DMap("EP", 3, 1, "vertexElecVSProtX", "VertexP",
//		// "VertexE", 50, -10, 10, 50, -0.05, 0.05, false);
//		// myCanvas.add2DMap("EP", 3, 2, "vertexElecVSProtY", "VertexP",
//		// "VertexE", 50, -10, 10, 50, -0.05, 0.05, false);
//		// myCanvas.add2DMap("EP", 3, 3, "vertexElecVSProtZ", "VertexP",
//		// "VertexE", 50, -10, 10, 50, -0.05, 0.05, false);
//		//
//		myCanvas.add2DMap("EP", 3, 1, "vertexElecVSProtX", "VertexP", "VertexE", 50, -10, 10, 50, -10, 10, false);
//		myCanvas.add2DMap("EP", 3, 2, "vertexElecVSProtY", "VertexP", "VertexE", 50, -10, 10, 50, -10, 10, false);
//		myCanvas.add2DMap("EP", 3, 3, "vertexElecVSProtZ", "VertexP", "VertexE", 50, -10, 10, 50, -10, 10, false);
//		myCanvas.add2DMap("EP", 3, 3, "vertexElecVSProtZ", "VertexP", "VertexE", 50, -10, 10, 50, -10, 10, false);
//		
//		myCanvas.add1DDistrib("EP", 3, 4, "missingMassEPGamma", "missingMass ep -> epGamma", 50, -4, 4, false);
//
//		myCanvas.add2DMap("EG", 1, 1, "Q2XB", "xB", "Q2", 50, 0, 0.8, 50, 0, 12, false);
//		myCanvas.add1DDistrib("EG", 1, 2, "TElec", "TElec", 50, 400, 700, false);
//		myCanvas.add2DMap("EG", 1, 3, "MissingMassVStElec", "missingMass ep->eGamma", "TElec", 50, -4, 4, 50, 400, 700,
//				false);
//		myCanvas.add1DDistrib("EG", 1, 4, "missingMassEGamma", "missingMass ep -> eGamma", 50, -4, 4, false);
//
//		myCanvas.add1DDistrib("EG", 2, 1, "finalMomEgammaX", "finalMomEgammaX", 50, -5, 5, false);
//		myCanvas.add1DDistrib("EG", 2, 2, "finalMomEgammaY", "finalMomEgammaY", 50, -5, 5, false);
//		myCanvas.add1DDistrib("EG", 2, 3, "finalMomEgammaZ", "finalMomEgammaZ", 50, -10, 18, false);
//		myCanvas.add1DDistrib("EG", 2, 4, "finalMomEgammaE", "finalMomEgammaE", 50, -10, 18, false);
//
//		myCanvas.add2DMap("Vertex", 1, 1, "vertexElecVSProtX1", "VertexP", "VertexE", 50, -10, 10, 50, -10, 10, false);
//		myCanvas.add2DMap("Vertex", 1, 2, "vertexElecVSProtY1", "VertexP", "VertexE", 50, -10, 10, 50, -10, 10, false);
//		myCanvas.add2DMap("Vertex", 1, 3, "vertexElecVSProtZ1", "VertexP", "VertexE", 50, -10, 10, 50, -20, 20, false);
//
//		myCanvas.add2DMap("Vertex", 2, 1, "vertexElecVSProtX2", "VertexP", "VertexE", 50, -10, 10, 50, -1, 1, false);
//		myCanvas.add2DMap("Vertex", 2, 2, "vertexElecVSProtY2", "VertexP", "VertexE", 50, -10, 10, 50, -1, 1, false);
//		myCanvas.add2DMap("Vertex", 2, 3, "vertexElecVSProtZ2", "VertexP", "VertexE", 50, -10, 10, 50, -1, 1, false);
//
//		myCanvas.add2DMap("Vertex", 3, 1, "vertexElecVSProtX3", "VertexP", "VertexE", 50, -10, 10, 50, -0.05, 0.05,
//				false);
//		myCanvas.add2DMap("Vertex", 3, 2, "vertexElecVSProtY3", "VertexP", "VertexE", 50, -10, 10, 50, -0.05, 0.05,
//				false);
//		myCanvas.add2DMap("Vertex", 3, 3, "vertexElecVSProtZ3", "VertexP", "VertexE", 50, -10, 10, 50, -0.05, 0.05,
//				false);
//
//		myCanvas.add1DDistrib("Vertex", 4, 1, "vertexElecX4", "VertexE", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Vertex", 4, 2, "vertexElecY4", "VertexE", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Vertex", 4, 3, "vertexElecZ4", "VertexE", 50, -20, 20, false);
//
//		myCanvas.add1DDistrib("Vertex", 5, 1, "vertexProtX5", "VertexP", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Vertex", 5, 2, "vertexProtY5", "VertexP", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Vertex", 5, 3, "vertexProtZ5", "VertexP", 50, -10, 10, false);
//
//		myCanvas.add1DDistrib("Vertex", 6, 1, "vertexProtMinusElecX6", "VertexP-E", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Vertex", 6, 2, "vertexProtMinusElecY6", "VertexP-E", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Vertex", 6, 3, "vertexProtMinusElecZ6", "VertexP-E", 50, -10, 10, false);
//
//		
//		myCanvas.add1DDistrib("Asym", 1, 1, "AsymMissingMass", "AsymMissingMassEP->EG", 50, -10, 10, false);
//		myCanvas.add1DDistrib("Asym", 1, 2, "NombreDePointsSecEffVSPhi", "NombreDePointsSecEffVSPhi", 12, 0, 360, false);
//		
//		int nbPos = 0;
//		int nbNeg = 0;
//		
		
		
		/* ===== ITERATE ===== */
//		int maxEvents = 1000;
		int maxEvents = Integer.parseInt(args[2]);
		int eventIterator = 0;
		while (eventIterator < maxEvents && hipoReader.hasNextEvent()) {
			eventIterator++;
			System.out.println("Event: " + eventIterator);
			DataEvent event = hipoReader.getNextEvent();
//			event.show();
			
			Event dataEvent = new Event();
//			ParticleEvent particleEvent = new ParticleEvent();
//			particleEvent.readParticleBanks(event);
			dataEvent.readBanks(event);
			
//			KinematicalCorrections.correct2GeV100OutbendingTorus(dataEvent);

			// Cuts cut = new Cuts();
			//
			/* ===== ELECTRON ===== */
			if (dataEvent.getParticleEvent().hasNumberOfElectrons() > 0) {
				ArrayList<Electron> electrons = dataEvent.getParticleEvent().getElectrons();
				// System.out.println("px: "+electrons.get(0).getPx());
				// System.out.println("py: "+electrons.get(0).getPy());
				// System.out.println("pz: "+electrons.get(0).getPz());
				// System.out.println("phi: "+electrons.get(0).getPhiDeg());
				// System.out.println("theta: "+electrons.get(0).getThetaDeg());
				for (Particle electron : electrons) {
//					if (electron.hasCalorimeterClusters()>0 && electron.hasFTOFClusters()>0 && electron.hasForwardTrack()>0){
//						System.out.println("Electron : "+electron.getFourMomentum()+" Calo energy: "+electron.getCalorimeterRecClusters().get(0).getEnergy()+" FTOF time: "+electron.getFTOFClusters().get(0).getTime()+" FwdTrack NDF: "+electron.getForwardRecTrack().getNdf()+" vx: "+electron.getForwardRecTrack().getVertex().x());
//					}
					if (electron.getThetaDeg()>5){
						myCanvas.fill2DHisto("histoElectron", electron.getPhiDeg(), electron.getThetaDeg(), 1);
					}
				}
			}

			/* ===== PROTON ===== */
			if (dataEvent.getParticleEvent().hasNumberOfProtons() > 0) {
				ArrayList<Proton> protons = dataEvent.getParticleEvent().getProtons();
				for (Particle proton : protons) {
					myCanvas.fill2DHisto("histoProton", proton.getPhiDeg(), proton.getThetaDeg(), 1);
				}
			}

			/* ===== PHOTON ===== */
			if (dataEvent.getParticleEvent().hasNumberOfPhotons() > 0) {
				ArrayList<Photon> photons = dataEvent.getParticleEvent().getPhotons();
				for (Particle photon : photons) {
//					if (photon.hasCalorimeterClusters()>0 && photon.hasFTOFClusters()>0 && photon.hasForwardTrack()>0){
//						System.out.println("Photon : "+photon.getFourMomentum()+" Calo energy: "+photon.getCalorimeterRecClusters().get(0).getEnergy()+" FTOF time: "+photon.getFTOFClusters().get(0).getTime()+" FwdTrack NDF: "+photon.getForwardRecTrack().getNdf()+" vx: "+photon.getForwardRecTrack().getVertex().x());
//					}
					myCanvas.fill2DHisto("histoPhoton", photon.getPhiDeg(), photon.getThetaDeg(), 1);
					
				}
			}

			/* ===== Pi+ ===== */
			if (dataEvent.getParticleEvent().hasNumberOfPiPlus() > 0) {
				ArrayList<PionPlus> pipluss = dataEvent.getParticleEvent().getPiPlus();
				for (PionPlus piplus : pipluss) {
					myCanvas.fill2DHisto("histoPiplus", piplus.getPhiDeg(), piplus.getThetaDeg(), 1);
				}
			}
			
//			myCanvas.add1DDistrib("elastic", 1, 1, "Elastic missing mass", "missingMass ep -> e", 500, 0.5, 2, false);
//			myCanvas.add1DDistrib("elastic", 1, 2, "Elastic missing mass after cuts", "missingMass ep -> e", 500, 0.5, 2, false);
//			myCanvas.add1DDistrib("elastic", 1, 3, "Central resolution", "resolution", 100, -10, -10, false);
//			phiCentralvsForward
			
			if (dataEvent.getParticleEvent().hasNumberOfElectrons() == 1){
				
				Electron electron = dataEvent.getParticleEvent().getElectrons().get(0);
				
				LorentzVector finalState = electron.getFourMomentum();
				//System.out.println("Electron: "+finalState.toString());
				LorentzVector missingFourMomentum = initialState.substract(finalState);
				
				double expectedPhi = missingFourMomentum.phi();
				double expectedTheta = missingFourMomentum.theta();
				double expectedP = missingFourMomentum.vect().mag();
				double expectedPT = Math.sqrt( Math.pow(missingFourMomentum.vect().x(),2) + Math.pow(missingFourMomentum.vect().y(),2) );
				
				double missingMass = missingFourMomentum.mass2();
				
				myCanvas.fill1DHisto("Elastic missing mass", missingMass, 1);
				
				if (missingMass > 0.80 && missingMass < 1.02 && event.hasBank("CVTRec::Tracks") == true){
					
					
					DataBank bankCVTRecTracks = event.getBank("CVTRec::Tracks");
					
					if (bankCVTRecTracks.rows()==1){
						for (int recIterator = 0; recIterator < bankCVTRecTracks.rows();recIterator++) { /* For all hits */
							int trackID = bankCVTRecTracks.getShort("ID", recIterator);
							int trackCharge = bankCVTRecTracks.getByte("q", recIterator);
							double trackChi2 = bankCVTRecTracks.getFloat("chi2", recIterator) ;
							double p = bankCVTRecTracks.getFloat("p", recIterator);
							double pt = bankCVTRecTracks.getFloat("pt", recIterator);
							double phi0 = bankCVTRecTracks.getFloat("phi0", recIterator);
							double tanDip = bankCVTRecTracks.getFloat("tandip", recIterator);
							
							double theta = Math.PI/2 - Math.atan(tanDip);
									
							myCanvas.fill1DHisto("trackChi", trackChi2, 1);
							if (trackCharge == 1 && trackChi2 < 10 ){
								myCanvas.fill1DHisto("Elastic missing mass after cuts", missingMass, 1);
								
								myCanvas.fill1DHisto("pCentral", p, 1);
								myCanvas.fill1DHisto("expectedpCentral", expectedP, 1);
								myCanvas.fill1DHisto("ptCentral", pt, 1);
								myCanvas.fill1DHisto("expectedptCentral", expectedPT, 1);
								
								myCanvas.fill1DHisto("phiCentral", Math.toDegrees(phi0), 1);
								myCanvas.fill1DHisto("expectedphiCentral", Math.toDegrees(expectedPhi), 1);
								myCanvas.fill1DHisto("thetaCentral", Math.toDegrees(theta), 1);
								myCanvas.fill1DHisto("expectedthetaCentral", Math.toDegrees(expectedTheta), 1);
								
								myCanvas.fill2DHisto("phiCentralvsForward", Math.toDegrees(expectedPhi), Math.toDegrees(phi0), 1);
								myCanvas.fill2DHisto("thetaCentralvsForward", Math.toDegrees(expectedTheta), Math.toDegrees(theta), 1);
								myCanvas.fill1DHisto("phiCentralMinusForward", Math.toDegrees(expectedPhi) - Math.toDegrees(phi0), 1);
								myCanvas.fill1DHisto("thetaCentralMinusForward", Math.toDegrees(expectedTheta) - Math.toDegrees(theta), 1);
								
								myCanvas.fill2DHisto("pCentralvsForward", expectedP, p, 1);
								myCanvas.fill2DHisto("ptCentralvsForward", expectedPT, pt, 1);
								myCanvas.fill1DHisto("pCentralMinusForward", expectedP - p, 1);
								myCanvas.fill1DHisto("ptCentralMinusForward", expectedPT - pt, 1);
								
								myCanvas.fill2DHisto("dp/pVSp", expectedP, (expectedP - p)/expectedP*100, 1);
								myCanvas.fill2DHisto("dpt/ptVSpt", expectedPT, (expectedPT-pt)/expectedPT*100, 1);
								myCanvas.fill1DHisto("dp/p", (expectedP - p)/expectedP*100, 1);
								myCanvas.fill1DHisto("dpt/pt", (expectedPT - pt)/expectedPT*100, 1);
								
//								// Get the Java runtime
//						        Runtime runtime = Runtime.getRuntime();
//						        // Run the garbage collector
//						        runtime.gc();
//						        // Calculate the used memory
//						        long memory = runtime.totalMemory() - runtime.freeMemory();
//						        System.out.println("Used memory is bytes: " + memory);
//						        System.out.println("Used memory is megabytes: "+ memory / (1024L * 1024L));
								
							}
							
						}
					}
					
					//bankCVTRecTracks.show();
					
				}
			}
			
			
			
			
			
			
			
			
//			if (event.hasBank("CVTRec::Tracks") == true) {
//
//				DataBank bankRecCal = event.getBank("CVTRec::Tracks");
//				bankRecCal.show();
//			
//			}
//			if (event.hasBank("ECAL::clusters") == true) {
//
//				DataBank bankRecCal = event.getBank("ECAL::clusters");
//				bankRecCal.show();
//			
//			}
//			if (event.hasBank("ECAL::peaks") == true) {
//
//				DataBank bankRecCal = event.getBank("ECAL::peaks");
//				bankRecCal.show();
//			}
//			
//			double[] timeOfParticlesInCalo= new
//					double[particleEvent.hasNumberOfParticles()];
//			double[] energyOfParticlesInCalo= new
//					double[particleEvent.hasNumberOfParticles()];
//			int []   sectorOfParticlesInCalo= new int [particleEvent.hasNumberOfParticles()];
//			if (event.hasBank("REC::Calorimeter") == true) {
//
//				DataBank bankRecCal = event.getBank("REC::Calorimeter");
//				bankRecCal.show();
////				System.out.println("NB Calo Rows: "+bankRecCal.rows());
//				for (int recCalIterator = 0; recCalIterator < bankRecCal.rows();
//						recCalIterator++) { /* For all hits */
//					int hitID = bankRecCal.getShort("index", recCalIterator);
//					int particleID = bankRecCal.getShort("pindex", recCalIterator);
//					// System.out.println("Particle ID: "+particleID);
//					double particleEnergy = bankRecCal.getFloat("energy",
//							recCalIterator);
//					// System.out.println("Particle energ: "+particleEnergy);
//					double particleTime = bankRecCal.getFloat("time",
//							recCalIterator);
//					// System.out.println("Particle time: "+particleTime);
//					int particleSector = bankRecCal.getByte("sector", recCalIterator);
//					timeOfParticlesInCalo[particleID] = particleTime;
//					energyOfParticlesInCalo[particleID] = particleEnergy;
//					sectorOfParticlesInCalo[particleID] = particleSector;
//
//					if (event.hasBank("ECAL::hits") == true) {
//						DataBank bankCal = event.getBank("REC::Calorimeter");
//						// bankCal.show();
//						for (int calIterator = 0; calIterator < bankCal.rows();
//								calIterator++) { /* For all hits */
//
//							int hitCaloID = bankCal.getShort("index", recCalIterator);
//							if (hitID==hitCaloID){
//								double hitCaloEnergy = bankCal.getFloat("energy",
//										recCalIterator);
//								// System.out.println("Hit energ: "+hitCaloEnergy);
//								double hitCaloTime = bankCal.getFloat("time", recCalIterator);
//								// System.out.println("Hit time: "+hitCaloTime);
//								timeOfParticlesInCalo[particleID] = hitCaloTime;
//								energyOfParticlesInCalo[particleID] = hitCaloEnergy;
//							}
//						}
//					}
//				}
//			}
//			
//			
//			
//			
//			
//			
//			
//			
//			
//			double totMomX = 0;
//			double totMomY = 0;
//			double totMomZ = 0;
//
//			for (Particle particle : particleEvent.getParticles()) {
//				totMomX += particle.getPx();
//				totMomY += particle.getPy();
//				totMomZ += particle.getPz();
//			}
//
//			myCanvas.fill1DDistrib("totMomX", totMomX, 1);
//			myCanvas.fill1DDistrib("totMomY", totMomY, 1);
//			myCanvas.fill1DDistrib("totMomZ", totMomZ, 1);
//
//			if (particleEvent.hasNumberOfElectrons() == 1) {
//				Electron electronSingleElec = particleEvent.getElectrons().get(0);
//
//				LorentzVector finalStateSingleElec = electronSingleElec.getFourMomentum();// .getFourMomentum().sum(/*protonDVCSF.getFourMomentum(),
//				// System.out.println("Electron:
//				// "+electron.getFourMomentum().toString());
//
//				LorentzVector difference = initialState.substract(finalStateSingleElec);
//				// System.out.println("Difference: "+difference.toString());
//
//				double missingMassSingleElec = difference.mass();
//				
//				myCanvas.fill2DMap("photonWPhi", electronSingleElec.getPhiDeg(), missingMassSingleElec, 1);
////				int sector = sectorOfParticlesInCalo[(int)electronSingleElec.getBeta()];
////				
////				//System.out.println(sector);
////				if (sector>0 && electronSingleElec.getThetaDeg()>5){
////				myCanvas.fill1DDistrib("missingMassSingleElec"+sector, missingMassSingleElec, 1);
//////				photonWPhi
////				
////				}
//			}
//			// }
//			// missingMassMaxEnergElec
//			ArrayList<Electron> electronsAll = particleEvent.getElectrons();
//			if (particleEvent.hasNumberOfElectrons() > 0) {
//				Electron electronMaxEnergetic = (Electron) particleEvent.getElectrons().get(0);
//				double maxEnerg = electronsAll.get(0).getEnergy();
//				for (Particle electron : electronsAll) {
//
//					if (electron.getEnergy() > maxEnerg) {
//						maxEnerg = electron.getEnergy();
//						electronMaxEnergetic = (Electron) electron;
//					}
//				}
//				LorentzVector finalStatemaxEnerg = electronMaxEnergetic.getFourMomentum();// .getFourMomentum().sum(/*protonDVCSF.getFourMomentum(),
//				// System.out.println("Electron:
//				// "+electron.getFourMomentum().toString());
//
//				LorentzVector difference = initialState.substract(finalStatemaxEnerg);
//				// System.out.println("Difference: "+difference.toString());
//
//				double missingMassMaxEnerg = difference.mass();
//
//				myCanvas.fill1DDistrib("missingMassMaxEnergElec", missingMassMaxEnerg, 1);
//			}
//
//			// if (totMomZ>3){
//			// System.out.println("Number of particles:
//			// "+particleEvent.hasNumberOfParticles());
//
//			// if
//			// (particleEvent.hasNumberOfParticles()==particleEvent.hasNumberOfElectrons()){
//			if (particleEvent.hasNumberOfElectrons() > 0) {
//				Electron electronSingleElec = (Electron) particleEvent.getElectrons().get(0);
//				double maxEnerg = electronsAll.get(0).getEnergy();
//				for (Particle electron : electronsAll) {
//
//					if (electron.getEnergy() > maxEnerg) {
//						maxEnerg = electron.getEnergy();
//						electronSingleElec = (Electron) electron;
//					}
//				}
//				if (electronSingleElec.getThetaDeg()>8){
//					LorentzVector finalStateSingleElec = electronSingleElec.getFourMomentum();// .getFourMomentum().sum(/*protonDVCSF.getFourMomentum(),
//					// System.out.println("Electron:
//					// "+finalStateSingleElec.toString());
//
//					LorentzVector difference = initialState.substract(finalStateSingleElec);
//					// System.out.println("Difference: "+difference.toString());
//
//					double missingMassSingleElec = difference.mass();
//
//					myCanvas.fill1DDistrib("missingMassSingleElec>Gev", missingMassSingleElec, 1);
//
//					myCanvas.fill1DDistrib("finalMomX", finalStateSingleElec.px(), 1);
//					myCanvas.fill1DDistrib("finalMomY", finalStateSingleElec.py(), 1);
//					myCanvas.fill1DDistrib("finalMomZ", finalStateSingleElec.pz(), 1);
//					myCanvas.fill1DDistrib("finalMomE", finalStateSingleElec.e(), 1);
//				}
//			}
//			// }
//			// }
////			int helicity = 0;
////			if (event.hasBank("REC::Particle") == true) {
////				 DataBank bankEvent = event.getBank("REC::Particle");
//////				 for (int bankEventIterator = 0; bankEventIterator < bankEvent.rows(); bankEventIterator++) { /* For all hits */
//////					helicity = bankEvent.getByte("Helic", bankEventIterator);
//////				 }
////				 bankEvent.show();
////			}
//			
//			
//			boolean hasHelicity = false;
//			boolean isHelicityPos = false;
//			if (event.hasBank("HEL::adc") == true) {
//				DataBank bankParticle = event.getBank("HEL::adc");
//				//bankParticle.show();
//				hasHelicity = true;
//				int pedestal = bankParticle.getShort("ped", 0);
//				if (pedestal>1000){
//					isHelicityPos = true;
//				}
//				hasHelicity = true;
//			}
//			// if (event.hasBank("REC::Cherenkov") == true) {
//			// DataBank bankParticle = event.getBank("REC::Cherenkov");
//			//// bankParticle.show();
//			// }
//
//			// System.out.println("NB Particles:
//			// "+particleEvent.hasNumberOfParticles());
//			// System.out.println("NB Electrons:
//			// "+particleEvent.hasNumberOfElectrons());
//			// System.out.println("NB Photons:
//			// "+particleEvent.hasNumberOfPhotons());
//			//
//			
//
//			double[] timeOfParticlesInFTOF = new double[particleEvent.hasNumberOfParticles()];
//			double[] energyOfParticlesInFTOF = new double[particleEvent.hasNumberOfParticles()];
//
//			if (event.hasBank("REC::Scintillator") == true) {
//
//				DataBank bankRecFTOF = event.getBank("REC::Scintillator");
//				// bankRecCal.show();
//				// System.out.println("NB FTOF Rows: "+bankRecFTOF.rows());
//				for (int recFTOFIterator = 0; recFTOFIterator < bankRecFTOF
//						.rows(); recFTOFIterator++) { /* For all hits */
//					int hitID = bankRecFTOF.getShort("index", recFTOFIterator);
//					int particleID = bankRecFTOF.getShort("pindex", recFTOFIterator);
//					// System.out.println("Particle ID: "+particleID);
//					double particleEnergy = bankRecFTOF.getFloat("energy", recFTOFIterator);
//					// System.out.println("Particle energ: "+particleEnergy);
//					double particleTime = bankRecFTOF.getFloat("time", recFTOFIterator);
//					// System.out.println("Particle time: "+particleTime);
//					timeOfParticlesInFTOF[particleID] = particleTime;
//					energyOfParticlesInFTOF[particleID] = particleEnergy;
//
//					// if (event.hasBank("ECAL::hits") == true) {
//					// DataBank bankCal = event.getBank("REC::Calorimeter");
//					//// bankCal.show();
//					// for (int calIterator = 0; calIterator < bankCal.rows();
//					// calIterator++) { /* For all hits */
//					//
//					// int hitCaloID = bankCal.getShort("index",
//					// recFTOFIterator);
//					// if (hitID==hitCaloID){
//					// double hitCaloEnergy = bankCal.getFloat("energy",
//					// recFTOFIterator);
//					//// System.out.println("Hit energ: "+hitCaloEnergy);
//					// double hitCaloTime = bankCal.getFloat("time",
//					// recFTOFIterator);
//					//// System.out.println("Hit time: "+hitCaloTime);
//					// timeOfParticlesInFTOF[particleID] = hitCaloTime;
//					// energyOfParticlesInFTOF[particleID] = hitCaloEnergy;
//					// }
//					// }
//					// }
//				}
//			}
//			Boolean random = false;
//			boolean[] trigger_bits = new boolean[32];
//
//			if (event.hasBank("RUN::config")) {
//				DataBank bank = event.getBank("RUN::config");
//				long TriggerWord = bank.getLong("trigger", 0);
//				for (int i = 31; i >= 0; i--) {
//					trigger_bits[i] = (TriggerWord & (1 << i)) != 0;
//				}
//			}
//
//			if (trigger_bits[31]) {
//				random = true;
//			}
//			if (trigger_bits[0]) {
//				// System.out.println("Number of protons:
//				// "+particleEvent.hasNumberOfProtons());
//				if (particleEvent.hasNumberOfElectrons() > 0 && particleEvent.hasNumberOfProtons() > 0) {
//					// TElecVSTProt
//					ArrayList<Electron> electrons = particleEvent.getElectrons();
//					for (Electron electron : electrons) {
//						// System.out.println("electron energy:
//						// "+electron.getEnergy());
//						// System.out.println("calo energy:
//						// "+energyOfParticlesInFTOF[(int)electron.getBeta()]);
//						// System.out.println("calo time:
//						// "+timeOfParticlesInFTOF[(int)electron.getBeta()]);
//					}
//					// System.out.println();
//					ArrayList<Proton> protons = particleEvent.getProtons();
//					for (Proton proton : protons) {
//						// System.out.println("proton energy:
//						// "+proton.getEnergy());
//						// System.out.println("ftof energy:
//						// "+energyOfParticlesInFTOF[(int)proton.getBeta()]);
//						// System.out.println("ftof time:
//						// "+timeOfParticlesInFTOF[(int)proton.getBeta()]);
//
//					}
//					// System.out.println();
//					for (int caloIterator = 0; caloIterator < particleEvent.hasNumberOfParticles(); caloIterator++) {
//						// System.out.println("ftof energy:
//						// "+energyOfParticlesInFTOF[caloIterator]);
//						// System.out.println("ftof time:
//						// "+timeOfParticlesInFTOF[caloIterator]);
//					}
//
//					for (Electron electron : electrons) {
//						for (Proton proton : protons) {
//							if (true/*proton.getThetaDeg() < 30 /*
//															 * &&
//															 * timeOfParticlesInFTOF
//															 * [(int)electron.
//															 * getBeta()] >500
//															 */) {
//								// TIME
//								myCanvas.fill2DMap("TElecVSTProt", timeOfParticlesInFTOF[(int) electron.getBeta()],
//										timeOfParticlesInFTOF[(int) proton.getBeta()], 1);
//								myCanvas.fill1DDistrib("TElecMINUSTProt",
//										timeOfParticlesInFTOF[(int) electron.getBeta()]
//												- timeOfParticlesInFTOF[(int) proton.getBeta()],
//										1);
//
//								// VERTEX
//								myCanvas.fill2DMap("vertexElecVSProtX", proton.getVx(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtY", proton.getVy(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtZ", proton.getVz(), electron.getVx(), 1);
//
//								myCanvas.fill2DMap("vertexElecVSProtX1", proton.getVx(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtY1", proton.getVy(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtZ1", proton.getVz(), electron.getVx(), 1);
//
//								myCanvas.fill2DMap("vertexElecVSProtX2", proton.getVx(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtY2", proton.getVy(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtZ2", proton.getVz(), electron.getVx(), 1);
//
//								myCanvas.fill2DMap("vertexElecVSProtX3", proton.getVx(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtY3", proton.getVy(), electron.getVx(), 1);
//								myCanvas.fill2DMap("vertexElecVSProtZ3", proton.getVz(), electron.getVx(), 1);
//
//								myCanvas.fill1DDistrib("vertexElecX4", electron.getVx(), 1);
//								myCanvas.fill1DDistrib("vertexElecY4", electron.getVy(), 1);
//								myCanvas.fill1DDistrib("vertexElecZ4", electron.getVz(), 1);
//
//								myCanvas.fill1DDistrib("vertexProtX5", proton.getVx(), 1);
//								myCanvas.fill1DDistrib("vertexProtY5", proton.getVy(), 1);
//								myCanvas.fill1DDistrib("vertexProtZ5", proton.getVz(), 1);
//
//								myCanvas.fill1DDistrib("vertexProtMinusElecX6", proton.getVx() - electron.getVx(), 1);
//								myCanvas.fill1DDistrib("vertexProtMinusElecY6", proton.getVy() - electron.getVy(), 1);
//								myCanvas.fill1DDistrib("vertexProtMinusElecZ6", proton.getVz() - electron.getVz(), 1);
//
//								if ( ((electron.getVx() > -2 && electron.getVx() < -0.0001)
//										|| (electron.getVx() > 0.0001 && electron.getVx() < 2))
//										&& ((electron.getVy() > -2 && electron.getVy() < -0.0001)
//												|| (electron.getVy() > 0.0001 && electron.getVy() < 2))
//										&& ((electron.getVz() > -2 && electron.getVz() < -0.0001)
//										|| (electron.getVz() > 0.0001 && electron.getVz() < 10)) ) {
//									if ( ((proton.getVx() > -2 && proton.getVx() < -0.0001)
//											|| (proton.getVx() > 0.0001 && proton.getVx() < 2))
//											&& ((proton.getVy() > -3.5 && proton.getVy() < -0.0001)
//													|| (proton.getVy() > 0.0001 && proton.getVy() < 3))
//											&& ((proton.getVz() > -10 && proton.getVz() < -0.0001)
//											|| (proton.getVz() > 0.0001 && proton.getVz() < 8)) ) {
//										// MISSING MASS
//										double tElecMoinstProt = timeOfParticlesInFTOF[(int) electron.getBeta()]
//												- timeOfParticlesInFTOF[(int) proton.getBeta()];
//										LorentzVector electronF = electron.getFourMomentum();
//										// System.out.println("Electron:
//										// "+electron.getFourMomentum().toString());
//										LorentzVector protonF = proton.getFourMomentum();
//										// System.out.println("Proton:
//										// "+proton.getFourMomentum().toString());
//
//										myCanvas.fill2DMap("electron ep", electron.getPhiDeg(), electron.getThetaDeg(), 1);
//
//										myCanvas.fill2DMap("proton ep", proton.getPhiDeg(), proton.getThetaDeg(), 1);
//
//										LorentzVector finalState = electronF
//												.sum(protonF/* , photonF */);
//										LorentzVector diff = initialState.substract(finalState);
//										double missingMass = diff.mass();
//
//										myCanvas.fill2DMap("MissingMassVSTime", missingMass, tElecMoinstProt, 1);
//										myCanvas.fill1DDistrib("missingMassEP", missingMass, 1);
//										
//										if (particleEvent.hasNumberOfPhotons() > 0){
//											ArrayList<Photon> photons = particleEvent.getPhotons();
//											for (Particle photon : photons) {
//												if (photon.getEnergy() > 2) {
//													LorentzVector photonF = photon.getFourMomentum();
//													LorentzVector finalState2 = electronF
//															.sum(protonF , photonF );
//													LorentzVector diff2 = initialState.substract(finalState2);
//													double missingMass2 = diff2.mass();
//													
//													myCanvas.fill1DDistrib("missingMassEPGamma", missingMass, 1);
//													
//												}
//											}
//										}
//										
//									}
//								}
//							}
//						}
//					}
//				}
//
//				if (particleEvent.hasNumberOfElectrons() > 0 && particleEvent.hasNumberOfPhotons() > 0 ) {
//
//					ArrayList<Electron> electrons = particleEvent.getElectrons();
//					ArrayList<Photon> photons = particleEvent.getPhotons();
//					for (Electron electron : electrons) {
//						LorentzVector electronF = electron.getFourMomentum();
//
//						for (Particle photon : photons) {
//							if (photon.getEnergy() > 2) {
//								LorentzVector photonF = photon.getFourMomentum();
//								LorentzVector finalState = electronF.sum(photonF);
//
//
//								LorentzVector virtualPhoton = electronI.substract(electronF);
//								double q2 = - virtualPhoton.mass2();
////								System.out.println("Q2 phot: "+virtualPhoton.mass());
//
//								double xB = q2/( 2*0.938272*( electronI.e()-electronF.e() ) );
//
//								double W2 = q2*(1/xB -1) + 0.938272*0.938272;
//								
//								if (q2>2 && W2>4 && electronF.e()>2){
//									if ( ((electron.getVx() > -2 && electron.getVx() < -0.0001)
//											|| (electron.getVx() > 0.0001 && electron.getVx() < 2))
//											&& ((electron.getVy() > -2 && electron.getVy() < -0.0001)
//													|| (electron.getVy() > 0.0001 && electron.getVy() < 2))
//											&& ((electron.getVz() > -2 && electron.getVz() < -0.0001)
//											|| (electron.getVz() > 0.0001 && electron.getVz() < 10)) ) {
//										
//										if (timeOfParticlesInFTOF[(int) electron.getBeta()] > 180 && timeOfParticlesInFTOF[(int) electron.getBeta()]<230){
//										
//
//									LorentzVector diff = initialState.substract(finalState);
//									// System.out.println("Difference:
//									// "+diff.toString());
//									double missingMass = diff.mass();
//									double tElec = timeOfParticlesInFTOF[(int) electron.getBeta()];
//									myCanvas.fill1DDistrib("TElec", tElec, 1);
//
//									myCanvas.fill2DMap("MissingMassVStElec", missingMass, tElec, 1);
//									myCanvas.fill1DDistrib("missingMassEGamma", missingMass, 1);
//									myCanvas.fill1DDistrib("finalMomEgammaX", finalState.px(), 1);
//									myCanvas.fill1DDistrib("finalMomEgammaY", finalState.py(), 1);
//									myCanvas.fill1DDistrib("finalMomEgammaZ", finalState.pz(), 1);
//									myCanvas.fill1DDistrib("finalMomEgammaE", finalState.e(), 1);
//									
//									myCanvas.fill2DMap("Q2XB", xB, q2, 1);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//
//
//				if (particleEvent.hasNumberOfElectrons() > 0 && particleEvent.hasNumberOfPhotons() > 0 && hasHelicity) {
//
//					ArrayList<Electron> electrons = particleEvent.getElectrons();
//					ArrayList<Photon> photons = particleEvent.getPhotons();
//
//					for (Electron electron : electrons) {
//						LorentzVector electronF = electron.getFourMomentum();
//
//						for (Particle photon : photons) {
//							if (photon.getEnergy() > 2) {
//
//								LorentzVector photonF = photon.getFourMomentum();
//
//								LorentzVector virtualPhoton = electronI.substract(electronF);
//								double q2 = - virtualPhoton.mass2();
////								System.out.println("Q2 phot: "+virtualPhoton.mass());
////								System.out.println("Electron Initial: "+electronI.e());
//								double xB = q2/( 2*0.938272*( electronI.e()-electronF.e() ) );
//
//								double W2 = q2*(1/xB -1) + 0.938272*0.938272;
//
//								if (q2>2 && W2>4 && electronF.e()>2 && timeOfParticlesInFTOF[(int) electron.getBeta()] > 580 && timeOfParticlesInFTOF[(int) electron.getBeta()]<600){
//
//									Vector3 qq = virtualPhoton.vect();
//									Vector3 ef = electronF.vect();
//									Vector3 q1 = photonF.vect();
//									Vector3 v1=qq.cross(ef);
//									Vector3 v2=qq.cross(q1);
//									double phi= Math.acos( v1.dot(v2)/(v1.mag()*v2.mag()) );
//									if (qq.dot(v1.cross(v2))<0) phi=2.*Math.PI-phi;
//									double phiDeg = Math.toDegrees(phi);
//									//								System.out.println("Phi: "+phi+ " phiDeg: "+phiDeg);
//									if (phiDeg>360){
//										phiDeg=phiDeg-Math.floor(phiDeg/360)*360;
////										System.out.println("PhiDegrescale: "+phiDeg);
//									}
//
//
//									LorentzVector finalState = electronF.sum(photonF);
//									LorentzVector diff = initialState.substract(finalState);
//									double missingMass = diff.mass();
//
//									if (phiDeg > 0 && phiDeg < 180){
//										if (isHelicityPos) {
//											myCanvas.fill1DDistrib("AsymMissingMass", missingMass, 1);
//											nbPos++;
//										}
//										else {
//											myCanvas.fill1DDistrib("AsymMissingMass", missingMass, -1);
//											nbNeg++;
//										}
//
//									}
//									if (phiDeg > 180 && phiDeg < 360){
//										if (isHelicityPos) {
//											myCanvas.fill1DDistrib("AsymMissingMass", missingMass, -1);
//											nbPos++;
//										}
//										else {
//											myCanvas.fill1DDistrib("AsymMissingMass", missingMass, 1);
//											nbNeg++;
//										}
//									}
//									//								NombreDePointsSecEffVSPhi
//
//
//									if (missingMass>0 && missingMass<1.3){
//										if (isHelicityPos){
//											myCanvas.fill1DDistrib("NombreDePointsSecEffVSPhi", phiDeg, 1);
//										}else{
//											myCanvas.fill1DDistrib("NombreDePointsSecEffVSPhi", phiDeg, -1);
//										}
//									}
//									System.out.println("Pos/Neg:  "+nbPos+"/"+nbNeg);
//								}
//							}
//						}
//					}
//				}
//				
//			} // End of if trigger bits
//			
//			
//			// MissingMassVSTime
//			// System.out.println();
//			// System.out.println();
//
//			// /* ===== DVCS ===== */
//			//
//			// ParticleEvent eventDVCS = cut.CutDVCS(particleEvent);
//			// if (eventDVCS!=null){
//			//// System.out.println("DVCS");
//			//// System.out.println("Number of elec:
//			// "+eventDVCS.hasNumberOfElectrons());
//			//// System.out.println("Number of prot:
//			// "+eventDVCS.hasNumberOfProtons());
//			//// System.out.println("Number of phot:
//			// "+eventDVCS.hasNumberOfPhotons());
//			// ArrayList<Electron> electrons = eventDVCS.getElectrons();
//			//
//			//// System.out.println("Initial state: "+initialState.toString());
//			//
//			// Electron electronDVCSF = (Electron)
//			// eventDVCS.getElectrons().get(0);
//			// double maxEnerg = electrons.get(0).getEnergy();
//			// for (Particle electron : electrons){
//			//
//			// if (electron.getEnergy()>maxEnerg){
//			// maxEnerg = electron.getEnergy();
//			// electronDVCSF = (Electron) electron;
//			// }
//			// }
//			//// System.out.println("Final electron
//			// DVCS:"+electronDVCSF.getFourMomentum().toString());
//			// myCanvas.fill2DMap("electronDVCS", electronDVCSF.getPhiDeg(),
//			// electronDVCSF.getThetaDeg(),1);
//			//
//			// //Proton protonDVCSF = (Proton) eventDVCS.getProtons().get(0);
//			//
//			//// Proton protonDVCSF = (Proton) eventDVCS.getProtons().get(0);
//			//// ArrayList<Proton> protons = eventDVCS.getProtons();
//			//// double maxEnergProt = protons.get(0).getEnergy();
//			//// for (Particle proton : protons){
//			//// if (proton.getEnergy()>maxEnerg){
//			//// maxEnerg = proton.getEnergy();
//			//// protonDVCSF = (Proton) proton;
//			//// }
//			//// }
//			////
//			//// System.out.println("Final proton DVCS:
//			// "+protonDVCSF.getFourMomentum().toString());
//			//// myCanvas.fill2DMap("protonDVCS", protonDVCSF.getPhiDeg(),
//			// protonDVCSF.getThetaDeg(),1);
//			//
//			//
//			// //Photon photonDVCSF1 = (Photon) eventDVCS.getPhotons().get(0);
//			//
//			// Photon photonDVCSF1 = null; // = (Photon)
//			// eventDVCS.getPhotons().get(0);
//			// ArrayList<Photon> photons = eventDVCS.getPhotons();
//			// double maxEnergPhot = 2;//photons.get(0).getEnergy();
//			// for (Particle photon : photons){
//			//// System.out.println("Photon energy:"+photon.getEnergy());
//			// if (photon.getEnergy()>maxEnergPhot){
//			// maxEnergPhot = photon.getEnergy();
//			// photonDVCSF1 = (Photon) photon;
//			// }
//			// }
//			//// if (photonDVCSF1!=null){
//			////// System.out.println("Final photon DVCS:
//			// "+photonDVCSF1.getFourMomentum().toString());
//			//// myCanvas.fill2DMap("photonDVCS", photonDVCSF1.getPhiDeg(),
//			// photonDVCSF1.getThetaDeg(),1);
//			////
//			//// LorentzVector finalStateDVCS =
//			// electronDVCSF.getFourMomentum().sum(/*protonDVCSF.getFourMomentum(),
//			// photonDVCSF1.getFourMomentum()*/);
//			////// System.out.println("Final state DVCS:
//			// "+finalStateDVCS.toString());
//			////
//			//// LorentzVector difference =
//			// initialState.substract(finalStateDVCS);
//			////// System.out.println("Difference: "+difference.toString());
//			//// double missingMassDVCS = difference.mass();
//			////// System.out.println("Final missing mass DVCS:
//			// "+missingMassDVCS);
//			//// myCanvas.fill1DDistrib("missingMassDVCS", missingMassDVCS, 1);
//			////
//			////
//			//// myCanvas.fill1DDistrib("missingMomX", difference.px(), 1);
//			//// myCanvas.fill1DDistrib("missingMomY", difference.py(), 1);
//			//// myCanvas.fill1DDistrib("missingMomZ", difference.pz(), 1);
//			//// myCanvas.fill1DDistrib("missingMomE", difference.e(), 1);
//			////
//			////
//			//// }
//			//
//			// LorentzVector finalStateDVCS =
//			// electronDVCSF.getFourMomentum();//.getFourMomentum().sum(/*protonDVCSF.getFourMomentum(),
//			// photonDVCSF1.getFourMomentum()*/);
//			//// System.out.println("Final state DVCS:
//			// "+finalStateDVCS.toString());
//			//
//			// LorentzVector difference =
//			// initialState.substract(finalStateDVCS);
//			//// System.out.println("Difference: "+difference.toString());
//			// double missingMassDVCS = difference.mass();
//			//// System.out.println("Final missing mass DVCS:
//			// "+missingMassDVCS);
//			//// myCanvas.fill1DDistrib("missingMassDVCS", missingMassDVCS, 1);
//			//
//			//// double totEnergyRec2=0;
//			//// for (Particle particle : particleEvent.getParticles()){
//			//// if (particle.getEnergy()!=-1){
//			//// totEnergyRec2 += particle.getEnergy();
//			//// }
//			//// }
//			//// myCanvas.fill1DDistrib("totEnergyRec2", totEnergyRec2, 1);
//			// }
//			//
		}
		
//		if (eventIterator==maxEvents){
//			System.out.println("MAX EVENT REACH");
//		}
//		myCanvas.save("pCentral", "/Users/gchristi/test_toRemove.hipo");
////		try {
////			TimeUnit.SECONDS.sleep(1);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println(" ================== READ ====================");
////		myCanvas.read("/Users/gchristi/Documents/workspace/Clas12/Analyse/FirstAnalysis/pCentral.hipo");
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		myCanvas.test();
//		myCanvas.saveAll("/Users/gchristi/test_toRemove2.hipo");
//		myCanvas.readAll("/Users/gchristi/test_toRemove2.hipo");
		
	}
	
}
