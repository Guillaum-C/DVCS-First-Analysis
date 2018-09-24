package analyser;

//import java.awt.GridLayout;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import org.clas12.analysisTools.event.particles.Electron;
//import org.clas12.analysisTools.event.particles.Particle;
//import org.clas12.analysisTools.event.particles.Photon;
//import org.clas12.analysisTools.event.particles.PionPlus;
//import org.clas12.analysisTools.event.particles.Proton;
//import org.jlab.groot.data.H1F;
//import org.jlab.groot.ui.TCanvas;
//import org.jlab.io.base.DataBank;
//import org.jlab.io.base.DataEvent;
//import org.jlab.io.hipo.HipoDataSource;
//import org.jlab.jnp.hipo.data.HipoEvent;
//import org.jlab.jnp.hipo.io.HipoReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import org.clas12.analysisTools.constants.BeamConstants;
import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.PionPlus;
import org.clas12.analysisTools.event.particles.Proton;
import org.clas12.analysisTools.files.HipoReader;
import org.clas12.analysisTools.plots.Canvas;
import org.jlab.clas.physics.Vector3;
import org.jlab.groot.data.H1F;
import org.jlab.groot.ui.TCanvas;
import org.jlab.io.base.DataBank;
import org.jlab.io.base.DataEvent;

import cuts.central.CVTCut;
import cuts.particles.ElectronCut;
import cuts.particles.ParticleNumberCut;
import cuts.particles.PhotonCut;
import cuts.particles.ProtonCut;
import cuts.physics.DVCSCut;
import cuts.physics.KinematicCut;
import cuts.physics.Pi0Cut;
import physics.ComputePhysicsParameters;
import plots.PlotTools;
import plots.detectorPlots.*;
import plots.particlePlots.*;
import plots.physics.KinematicalPlots;
import plots.physics.PhysicsPlots;

public class Analyser {

	static boolean displayHistos = true;

	static String path = "";
	static String runNumber = "";
	static String state = "Data";
	static double beamEnergy = 10.6;
	static int maxEvents = 10000000;

	static DetectorPlots detectorPlots;
	static PositiveNegativeChargesPlots positNegChargesPlots;
	static ParticlePlots particlePlots;
	static KinematicalPlots kinematicPlots;
	static PhysicsPlots physicsPlots;

	static String outPutFile = "outputProtons.txt";
	static String dataSet = "Inbending"; // Can be "Inbending" "Outbending"
											// "Simu" (ou "Old4013", "Old3889", "SimuPi0")
	static String outPutPlotsHipo = "BackgroundProtonInbending_";

	public static void main(String[] args) {

		/* Parameters */
		HipoReader hipoReader = getRunningArguments(args, dataSet);

		/* Plots */
		Canvas myCanvas = new Canvas(dataSet, true, false);
		createPlots(myCanvas, true);

		/* ===== OUPUT FILE ===== */
//		createOutputFile(outPutFile);

		/* ===== RDM PHOTON INPUT ===== */
//		 int lineNumber=0;
//		 try
//		 (
//		 FileReader input = new FileReader(outPutFile);
//		 LineNumberReader count = new LineNumberReader(input);
//		 )
//		 {
//		 while (count.skip(Long.MAX_VALUE) > 0)
//		 {
//		 // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
//		 }
//		
//		 lineNumber = count.getLineNumber() + 1 - 1; // +1 because line index starts at 0 / -1 because last line is empty
//		 } catch (IOException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
//		 System.out.println("Number of lines: "+lineNumber);

		/* ===== INITIAL STATE ===== */
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, Proton.mass);
		System.out.println("Initial proton: " + protonI.toString());

		LorentzVector electronI = new LorentzVector();
		electronI.setPxPyPzM(0, 0, beamEnergy, Electron.mass);
		System.out.println("Initial electron: " + electronI.toString());

		LorentzVector stateI = protonI.sum(electronI);
		System.out.println("Initial state: " + stateI.toString());

		boolean hasBeam = true; // true if Faraday cup has high counts for the
								// current 30ms, false else (means beam trip)

		int goodRandomEvents = 0;
		int goodRandomEventsWithDVCSProton = 0;

		/* Loop on events */
		int eventIterator = 0;
		while (eventIterator < maxEvents && hipoReader.hasNextEvent()) {
			eventIterator++;

			// System.out.println("Event: " + eventIterator);
			if (eventIterator % 10000 == 0) {
				System.out.println("Event: " + eventIterator + "  (Event:" + hipoReader.getCurrentEvent() + "/"
						+ hipoReader.getHipoFile().getSize() + "  (File:" + (hipoReader.getCurrentFile() + 1) + "/"
						+ hipoReader.getFileList().size() + ")");
			}

			DataEvent dataEvent = hipoReader.getNextEvent();

			Event processedEvent = new Event();
			processedEvent.readBanks(dataEvent);

			// System.out.println("Event: " + eventIterator);
			// dataEvent.show();

			// /* ===== FIX PROTON SELECTION FROM CENTRAL ===== */
			// // TODO To be removed later
			// ArrayList<Particle> particlesToBeRemoved = new ArrayList<>();
			// ArrayList<Particle> particlesToBeAdded = new ArrayList<>();
			// for (Particle particle :
			// processedEvent.getParticleEvent().getParticles()) {
			// if (!(particle instanceof Proton) && (particle.getCharge() == 1)
			// && (particle.hasCentralTrack() > 0)) {
			// Particle newProton = new Proton(particle);
			// particlesToBeRemoved.add(particle);
			// particlesToBeAdded.add(newProton);
			// }
			// }
			// for (Particle particle : particlesToBeRemoved) {
			// processedEvent.getParticleEvent().removeParticle(particle);
			// }
			// for (Particle newProton : particlesToBeAdded) {
			// processedEvent.getParticleEvent().addParticle(newProton);
			// }
			// END of new proton pid

			// /* Test */
			// DataEvent dataFollowingEvent = null;
			// if (eventIterator==5){
			// dataFollowingEvent=hipoReader.getRelativeEvent(0);
			// System.out.println("Relative 0:");
			// dataFollowingEvent.show();
			// dataFollowingEvent=hipoReader.getRelativeEvent(1);
			// System.out.println("Relative 1:");
			// dataFollowingEvent.show();
			// dataFollowingEvent=hipoReader.getRelativeEvent(2);
			// System.out.println("Relative 2:");
			// dataFollowingEvent.show();
			// dataFollowingEvent=hipoReader.getRelativeEvent(3);
			// System.out.println("Relative 3:");
			// dataFollowingEvent.show();
			// dataFollowingEvent=hipoReader.getRelativeEvent(4);
			// System.out.println("Relative 4:");
			// dataFollowingEvent.show();
			// dataFollowingEvent=hipoReader.getRelativeEvent(5);
			// System.out.println("Relative 5:");
			// dataFollowingEvent.show();
			// }

			// /* ===== RANDOM TRIGGER ===== */
//			if (processedEvent.getTrigger_bit(31)) {
////				fillDetectorsPlotsRandom(processedEvent);
//				if (hasBeam) {
//					goodRandomEvents++;
////					PhotonCut photonCutDVCS = new PhotonCut();
//					ProtonCut protonCutDVCS = new ProtonCut();
////					Event afterPhotonCuts = photonCutDVCS.CutDVCS(processedEvent);
//					Event afterProtonCuts = protonCutDVCS.CutDVCS(processedEvent);
//					if (processedEvent.getParticleEvent().hasNumberOfProtons() > 0) {
//						System.out.println("Proton found");
//					}
//					fillOutputProtonFile(outPutFile, afterProtonCuts);
////					if (afterProtonCuts.getParticleEvent().hasNumberOfPhotons() > 0) {
//					if (afterProtonCuts.getParticleEvent().hasNumberOfProtons() > 0) {
//						goodRandomEventsWithDVCSProton = goodRandomEventsWithDVCSProton
//								+ 1;
//
//						String text = "Event: " + eventIterator + " Ratio random: "
//								+ ((double) goodRandomEventsWithDVCSProton / (double) goodRandomEvents) + " ("
//								+ goodRandomEventsWithDVCSProton + "/" + goodRandomEvents + ")";
//						try {
//							Files.write(Paths.get("./normalisation.txt"), text.getBytes());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//
//						System.out.println("Event: " + eventIterator);
//						System.out.println(
//								"Ratio random: " + ((double) goodRandomEventsWithDVCSProton / (double) goodRandomEvents)
//										+ " (" + goodRandomEventsWithDVCSProton + "/" + goodRandomEvents + ")");
//
//					}
//				}
//			}
//
//			/* ===== Faraday cup state ===== */
//			if (dataEvent.hasBank("RAW::scaler") == true) {
//				/* Reset beam value */
//				hasBeam = false; // reset value
//
//				// dataEvent.getBank("RAW::scaler").show();
//
//				/* Plot FCvalue */
//				for (int bankEntry = 0; bankEntry < dataEvent.getBank("RAW::scaler").rows(); bankEntry++) {
//
//					int crate = dataEvent.getBank("RAW::scaler").getByte("crate", bankEntry);
//					int slot = dataEvent.getBank("RAW::scaler").getByte("slot", bankEntry);
//					int channel = dataEvent.getBank("RAW::scaler").getShort("channel", bankEntry);
//					if (crate == 64 && slot == 64 && channel == 0) {
//						int fCupGated = dataEvent.getBank("RAW::scaler").getInt("value", bankEntry);
////						myCanvas.fill1DHisto("FaradayCupValue", fCupGated);
//						// System.out.println("fCup gated: "+fCupGated);
//					}
//				}
//
//				/*
//				 * Look for next event with raw scaler bank, and set the hasBeam
//				 * value for the next events
//				 */
//				int count = 1;
//				DataEvent dataFollowingEvent = hipoReader.getRelativeEvent(count);
//				while (!(dataFollowingEvent == null) && count < 1000 && !(dataFollowingEvent.hasBank("RAW::scaler"))) {
//					count++;
//					dataFollowingEvent = hipoReader.getRelativeEvent(count);
//				}
//				if (dataFollowingEvent != null && dataFollowingEvent.hasBank("RAW::scaler")) {
//					DataBank bankParticle = dataEvent.getBank("RAW::scaler");
//					for (int bankEntry = 0; bankEntry < bankParticle.rows(); bankEntry++) {
//						int crate = bankParticle.getByte("crate", bankEntry);
//						int slot = bankParticle.getByte("slot", bankEntry);
//						int channel = bankParticle.getShort("channel", bankEntry);
//						if (crate == 64 && slot == 64 && channel == 0) { // gated
//																			// TRG
//																			// faraday
//																			// cup
//																			// (see
//																			// https://logbooks.jlab.org/entry/3554327)
//							int fCupGated = bankParticle.getInt("value", bankEntry);
//							int fCupThreshold = 20000;
//							if (fCupGated <= fCupThreshold) {
//								hasBeam = false;
//							} else {
//								hasBeam = true;
//							}
//						}
//					}
//				} else {
//					hasBeam = false;
//				}
//			}

			// /* ===== RAW PLOTS ===== */
			detectorPlots.fillDetectorsPlotsRaw(processedEvent);
			positNegChargesPlots.fillPosNegChargesPlotsRaw(processedEvent);
			particlePlots.fillParticlesPlotsRaw(processedEvent);
			particlePlots.fillNumberOfParticlesPlots(processedEvent, "");

			/* ===== PI0 ===== */

			/* ===== SELECTION + KINEMATIC CUTS ===== */
			Pi0Cut pi0Cut = new Pi0Cut();
			Event afterPi0Cuts = pi0Cut.Cut(processedEvent, electronI);

			boolean isPi0 = false;

			ArrayList<Photon> photons = afterPi0Cuts.getParticleEvent().getPhotons();
			for (Electron electronF : afterPi0Cuts.getParticleEvent().getElectrons()) {
				for (Proton protonF : afterPi0Cuts.getParticleEvent().getProtons()) {
					for (int photon1Iterator = 0; photon1Iterator < photons.size(); photon1Iterator++) {
						Photon photon1 = photons.get(photon1Iterator);
						for (int photon2Iterator = photon1Iterator + 1; photon2Iterator < photons
								.size(); photon2Iterator++) {
							Photon photon2 = photons.get(photon2Iterator);
							physicsPlots.fillDefaultPi0Histo(photon1, photon2, "Pi0", "");
							isPi0 = physicsPlots.fillDefaultPi0ExclusiveHisto(electronF, protonF, photon1, photon2,
									"Pi0", "");

						}
					}
				}
			}

			// /* ===== SELECTION + KINEMATIC CUTS ===== */
			// ElectronCut electronCutDVCS = new ElectronCut();
			// Event afterElectronCuts =
			// electronCutDVCS.CutDVCS(processedEvent);
			//
			// PhotonCut photonCutDVCS = new PhotonCut();
			// Event afterPhotonCuts = photonCutDVCS.CutDVCS(afterElectronCuts);
			//
			// ProtonCut protonCutDVCS = new ProtonCut();
			// Event afterProtonCuts = protonCutDVCS.CutDVCS(afterPhotonCuts);
			//
			// CVTCut cvtCutDVCS = new CVTCut();
			// Event afterCVTCuts =
			// cvtCutDVCS.CutDefaultAnalysis(afterProtonCuts);
			//
			// ParticleNumberCut particleNumberCutDVCS = new
			// ParticleNumberCut();
			// Event afterParticleNumberCuts =
			// particleNumberCutDVCS.cutDVCS(afterCVTCuts);
			//
			// KinematicCut kinematicalCutDVCS = new KinematicCut();
			// Event afterKinematicCuts =
			// kinematicalCutDVCS.CutDVCS(afterParticleNumberCuts, electronI);
			//
			// Event afterSelectionCuts = afterKinematicCuts;
			//
			// /* ===== SELECTION + KINEMATIC PLOTS ===== */
//			 fillDetectorsPlotsCut(afterSelectionCuts);
//			 fillParticlesPlotsCut(afterSelectionCuts.getParticleEvent(), "cut selection", "after selection cuts");

//			/* ===== SELECTION + KINEMATIC CUTS ===== */
			DVCSCut dvcsCut = new DVCSCut();
			Event afterDVCSCuts = dvcsCut.Cut(processedEvent, electronI);
//
//			/* ===== SELECTION + KINEMATIC PLOTS ===== */
			particlePlots.fillParticlesPlotsAfterCuts(afterDVCSCuts.getParticleEvent(), "cut selection",
					"after selection cuts");
			particlePlots.fillNumberOfParticlesPlots(afterDVCSCuts, "after selection");
//
			for (Electron electronF : afterDVCSCuts.getParticleEvent().getElectrons()) {
//				for (Proton protonF : readProtonFileRandom(outPutFile,lineNumber).getProtons()){
				for (Proton protonF : afterDVCSCuts.getParticleEvent().getProtons()) {
//					for (Photon photonF : readPhotonFileRandom(outPutFile,lineNumber).getPhotons()){
					for (Photon photonF : afterDVCSCuts.getParticleEvent().getPhotons()) {

						/* ===== COMPUTE KINEMATICAL PARAMETERS ===== */
						double t = ComputePhysicsParameters.computeT(protonF);
						double phiDeg = ComputePhysicsParameters.computePhiDeg(electronI, electronF, photonF);

						/* ===== SELECTION + KINEMATIC PLOTS ===== */
						kinematicPlots.fillDefaultHistograms(electronF, protonF, photonF, "Kinematic after selection",
								"");
						physicsPlots.fillDefaultHistogramsDVCS(electronF, protonF, photonF, beamEnergy,
								"DVCS after selection", "");
						physicsPlots.fillAsymetryHisto(phiDeg, afterDVCSCuts.getHelicity(), "Asym after selection", "");

						/* ===== MISSING MASSES ===== */
						LorentzVector finalStateEPG = new LorentzVector();
						finalStateEPG.add(electronF.getFourMomentum(), protonF.getFourMomentum(),
								photonF.getFourMomentum());
						double missingMass2EPG = stateI.substract(finalStateEPG).mass2();

						LorentzVector finalStateEPwhenG = new LorentzVector();
						finalStateEPwhenG.add(electronF.getFourMomentum(), protonF.getFourMomentum());
						double missingMass2EPwhenG = stateI.substract(finalStateEPwhenG).mass2();

						LorentzVector finalStateEGwhenP = new LorentzVector();
						finalStateEGwhenP.add(electronF.getFourMomentum(), photonF.getFourMomentum());
						double missingMass2EGwhenP = stateI.substract(finalStateEGwhenP).mass2();

						/* ===== EXCLUSIVITY CUTS ===== */
						if (-0.06<missingMass2EPG && missingMass2EPG<0.04) {
							/* ===== EXCLUSIVITY PLOTS 1 ===== */
//							 fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(),"cut DVCS ex1","after DVCS epg cuts");
//							 fillNumberParticlesPlots(afterDVCSCuts, "after DVCS epg cuts");
//							 kinematicPlots.fillDefaultHistograms(electronF,protonF, photonF, "Kinematic ex1","after DVCS epg cuts");
							 physicsPlots.fillDefaultHistogramsDVCS(electronF, protonF, photonF, beamEnergy, "DVCS ex1","after DVCS epg cuts");
							 physicsPlots.fillAsymetryHisto(phiDeg,afterDVCSCuts.getHelicity(), "Asym DVCS ex1","after DVCS epg cuts");

						} else {
							continue;
						}

						LorentzVector photonExpected = stateI.substract(finalStateEPwhenG);
						double angle2Photon = 180 / Math.PI
								* Math.acos(photonExpected.vect().dot(photonF.getFourMomentum().vect())
										/ (photonExpected.vect().mag() * photonF.getFourMomentum().vect().mag()));
						// LorentzVector protonExpected =
						// stateI.substract(finalStateEGwhenP);
						// double angle2Proton =
						// 180/Math.PI*Math.acos(protonExpected.vect().dot(protonF.getFourMomentum().vect())
						// / (protonExpected.vect().mag() *
						// protonF.getFourMomentum().vect().mag()));

						if (angle2Photon < 3) {
							/* ===== EXCLUSIVITY PLOTS 2 ===== */
							fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(), "cut DVCS ex2",
									"after photon cone cuts");
//							fillNumberParticlesPlots(afterDVCSCuts, "after photon cone cuts");
							kinematicPlots.fillDefaultHistograms(electronF, protonF, photonF, "DVCS ex2",
									"after photon cone cuts");
							physicsPlots.fillDefaultHistogramsDVCS(electronF, protonF, photonF, beamEnergy, "DVCS ex2",
									"after photon cone cuts");
							physicsPlots.fillAsymetryHisto(phiDeg, afterDVCSCuts.getHelicity(), "Asym DVCS ex2",
									"after photon cone cuts");
						} else {
							continue;
						}

						if (!isPi0) {
							fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(), "cut DVCS ex3",
									"after pi0 cuts");
							kinematicPlots.fillDefaultHistograms(electronF, protonF, photonF, "DVCS ex3", "after pi0 cuts");
							physicsPlots.fillDefaultHistogramsDVCS(electronF, protonF, photonF, beamEnergy, "DVCS ex3",
									"after pi0 cuts");
							physicsPlots.fillAsymetryHisto(phiDeg, afterDVCSCuts.getHelicity(), "Asym DVCS ex3",
									"after pi0 cuts");
						} else {
							fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(), "cut DVCS ex4",
									"just pi0");
							kinematicPlots.fillDefaultHistograms(electronF, protonF, photonF, "DVCS ex4", "just pi0");
							physicsPlots.fillDefaultHistogramsDVCS(electronF, protonF, photonF, beamEnergy, "DVCS ex4",
									"just pi0");
							physicsPlots.fillAsymetryHisto(phiDeg, afterDVCSCuts.getHelicity(), "Asym DVCS ex4",
									"just pi0");
						}

						// double transverseMissingMass = Math.sqrt(
						// Math.pow(finalStateEGwhenP.px(),2) +
						// Math.pow(finalStateEGwhenP.py(),2) );
						// if (Math.abs(transverseMissingMass) < 0.8){
						// if (photonF.getEnergy()>2){
						// fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(),
						// "cut DVCS ex3",
						// "after DVCS perpMM cuts");
						// physicsPlots.fillDefaultHistogramsDVCS(electronF,
						// protonF, photonF, beamEnergy, "DVCS ex3",
						// "after DVCS perpMM cuts");
						// fillNumberParticlesPlots(afterDVCSCuts, "after DVCS
						// perpMM cuts");
						// }
						// }else{
						// continue;
						// }

						// if (missingMass2EGwhenP < 1 && missingMass2EGwhenP >
						// 0.7){
						//// if (photonF.getEnergy()>2){
						// fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(),
						// "cut DVCS ex2",
						// "after MissingMEG cuts");
						// physicsPlots.fillDefaultHistogramsDVCS(electronF,
						// protonF, photonF, beamEnergy, "DVCS ex2",
						// "after MissingMEG cuts");
						// kinematicalPlots.fillDefaultHistograms(electronF,
						// protonF, photonF, beamEnergy, "DVCS ex2",
						// "after MissingMEG cuts");
						// fillNumberParticlesPlots(afterDVCSCuts, "after
						// MissingMEG cuts");
						//// }
						// }else{
						// continue;
						// }

						// if (photonF.getEnergy()>2 &&
						// afterDVCSCuts.getParticleEvent().hasNumberOfPhotons()==1){
						// fillParticlesPlotsCut(afterDVCSCuts.getParticleEvent(),
						// "cut DVCS ex5",
						// "after DVCS 1phot cuts");
						// physicsPlots.fillDefaultHistogramsDVCS(electronF,
						// protonF, photonF, beamEnergy, "DVCS ex5",
						// "after DVCS 1phot cuts");
						// fillNumberParticlesPlots(afterDVCSCuts, "after DVCS
						// 1phot cuts");
						// }

					}
				}
			}
			if (eventIterator % 1000000 == 0 || eventIterator == maxEvents - 1) {
				System.out.println("Saving plots");
				myCanvas.saveAll("allPlots2_" + eventIterator + ".hipo", false);

				System.out.println("Helic+ DVCS: Bin,asymetry");
				for (int i = 0; i < 20; i++) {
					System.out.println("Bin " + i + "," + myCanvas.get1DHisto("#phi_trento for events with helicity + (after photon cone cuts)").getBinContent(i));
				}
				System.out.println("Helic- DVCS: Bin,asymetry");
				for (int i = 0; i < 20; i++) {
					System.out.println("Bin " + i + "," + myCanvas.get1DHisto("#phi_trento for events with helicity - (after photon cone cuts)").getBinContent(i));
				}
				System.out.println("ASYMETRY DVCS: Bin,asymetry");
				for (int i = 0; i < 20; i++) {
					System.out.println("Bin " + i + "," + myCanvas.get1DHisto("Asymetry").getBinContent(i));
				}
				System.out.println("ASYMETRY DVCS (after DVCS epg cuts): Bin,asymetry");
				for (int i = 0; i < 20; i++) {
					System.out.println(
							"Bin " + i + "," + myCanvas.get1DHisto("Asymetry (after DVCS epg cuts)").getBinContent(i));
				}
				System.out.println("ASYMETRY DVCS (after photon cone cuts): Bin,asymetry");
				for (int i = 0; i < 20; i++) {
					System.out.println("Bin " + i + ","
							+ myCanvas.get1DHisto("Asymetry (after photon cone cuts)").getBinContent(i));
				}
				
				System.out.println("MM DVCS (just Pi0): Bin,mm");
				for (int i = 0; i < 300; i++) {
					System.out.println(myCanvas.get1DHisto("DVCS MM(ep->ep#gammaX)^2 (after DVCS epg cuts)").getBinContent(i)+ ",");
				}
				System.out.println("MM DVCS (just Pi0): Bin,mm");
				for (int i = 0; i < 300; i++) {
					System.out.println(myCanvas.get1DHisto("DVCS MM(ep->ep#gammaX)^2 (after photon cone cuts)").getBinContent(i)+ ",");
				}
				System.out.println("MM DVCS (just Pi0): Bin,mm");
				for (int i = 0; i < 300; i++) {
					System.out.println(myCanvas.get1DHisto("DVCS MM(ep->ep#gammaX)^2 (after pi0 cuts)").getBinContent(i)+ ",");
				}
				System.out.println("MM DVCS (just Pi0): Bin,mm");
				for (int i = 0; i < 300; i++) {
					System.out.println(myCanvas.get1DHisto("DVCS MM(ep->ep#gammaX)^2 (just pi0)").getBinContent(i)+ ",");
				}
			}

		}
	}

	public static HipoReader getRunningArguments(String[] args, String dataSet) {

		/* ===== OLD 2.2 GeV ===== */
		// electronEnergy = 2.2;
		// path
		// ="/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
		// runNumber = "002475";
		// String path ="/Users/gchristi/";
		// String runNumber = "002391";

		/* ===== OLD 6.4 GeV ===== */

		// electronEnergy = 6.4;
		// path
		// ="/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
		// runNumber = "003105";

		/* ===== OLD 10.6 GeV ===== */
		beamEnergy = 10.6;
		// path =
		// "/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/Run3432_10GeV_50nA_T-1_S-1_NegInbending_5b3.3/";
		// path =
		// "/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/Run3432_5bp2p1_NewMap_NewCVT/";//"volatile/clas12/data/rg-a/tag5bp2p1_newCVT_newfieldmap/cooked/";//"Data/run3532/";//"/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
		// path =
		// "/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January";
		// runNumber = "003532";//"003712";
		// runNumber = "003432";
		// runNumber = "003222";
		ArrayList<String> fileOldList = new ArrayList<>();
		fileOldList.add("/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/out_clas_003249.evio.900.hipo");

		ArrayList<String> fileOld = new ArrayList<>();
		fileOld.add(
				"/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/Run3432_10GeV_50nA_T-1_S-1_NegInbending_5b3.3/out_clas_003432.evio.2200.hipo");

		ArrayList<String> fileOldOutbending = new ArrayList<>();
		fileOldOutbending.add(
				"/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/Run3932_10GeV_45nA_S-1T1Out_11Avr/out_clas_003932.evio.10_19.hipo");
		ArrayList<String> fileOldInbending = new ArrayList<>();
		fileOldInbending.add(
				"/Users/gchristi/Donnees/JLab_Beam/EngineeringRun_Part2_January/Run4013_10GeV_50nA_S-1T-1In_18Avr/out_clas_004013.evio.10_19.hipo");

		ArrayList<String> fileSkimmedInbending4078 = new ArrayList<>();
		fileSkimmedInbending4078.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4078_8.hipo");

		/* ===== SKIMMED INBENDING 10.6 GeV ===== */
		ArrayList<String> fileSkimmedInbending = new ArrayList<>();
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4013_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4014_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4015_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4016_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4017_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4018_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4020_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4021_8.hipo");
		fileSkimmedInbending.add("/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4078_8.hipo");

		/* ===== SIMU DVCS 10.6 GeV ===== */
		ArrayList<String> fileSimu = new ArrayList<>();
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen1.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen2.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen3.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen4.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen5.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen6.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen7.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen8.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen9.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen10.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen11.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen12.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen13.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen14.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen15.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen16.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen17.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen18.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen19.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen20.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen21.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen22.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen23.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen24.dat.evio.hipo");
		fileSimu.add("/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/May_GemC-4a23_Coatjava-5a33/out_dvcsgen25.dat.evio.hipo");
		path = "/Users/gchristi/Donnees/JLab_Simu/Simu_DVCS_Harut/";
		runNumber = "";
		
		ArrayList<String> fileSimuPi0 = new ArrayList<>();
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_1.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_2.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_3.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_4.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_5.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_6.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_7.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_8.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_9.hipo");
		fileSimuPi0.add("/Users/gchristi/Donnees/JLab_Simu/Simu_Pi0_Harut_4a24_562_2018-09/out_deco_S-100_T-100_v1_10.hipo");
		

		HipoReader hipoReader = null;

		/* Ask user */
		// Example : java -jar Analyser_Cut10GeV.jar
		// /volatile/clas12/data/rg-a/current/ 003222 10 100000
		if (args.length > 0 && args[0] != null && args[1] != null && args[2] != null) {
			if (args.length == 4) {
				path = (String) args[0];
				System.out.println("Path: " + path);
				runNumber = (String) args[1];
				System.out.println("RunNumber: " + runNumber);
				int electronE = Integer.parseInt(args[2]);
				if (electronE == 2) {
					beamEnergy = 2.2;
				} else if (electronE == 6) {
					beamEnergy = 6.4;
				} else if (electronE == 10) {
					beamEnergy = 10.6;
				}
				maxEvents = Integer.parseInt(args[3]);
				hipoReader = new HipoReader(path, runNumber);
			} else {
				String file = (String) args[0];
				System.out.println("File: " + file);
				int electronE = Integer.parseInt(args[1]);
				if (electronE == 2) {
					beamEnergy = 2.2;
				} else if (electronE == 6) {
					beamEnergy = 6.4;
				} else if (electronE == 10) {
					beamEnergy = 10.6;
				}
				maxEvents = Integer.parseInt(args[2]);
				System.out.println("Elec energ: " + beamEnergy);

				ArrayList<String> listFiles = new ArrayList<>();
				listFiles.add(file);
				hipoReader = new HipoReader(listFiles);
			}

		} else {
			// hipoReader = new HipoReader(path, runNumber);
			// Can be "Inbending" "Outbending" "Simu" (ou "Old4013", "Old3889")
			if (dataSet.equals("Inbending")) {
				hipoReader = new HipoReader(fileSkimmedInbending);
			} else if (dataSet.equals("Outbending")) {
				// hipoReader = new HipoReader(fileSkimmedOutbending);
			} else if (dataSet.equals("Simu")) {
				hipoReader = new HipoReader(fileSimu);
			} else if (dataSet.equals("SimuPi0")){
				hipoReader = new HipoReader(fileSimuPi0);
			} else if (dataSet.equals("Old4013")) {
				hipoReader = new HipoReader(fileOldInbending);
			} else if (dataSet.equals("Old3889")) {
				hipoReader = new HipoReader(fileOldOutbending);
			}  else {
				hipoReader = new HipoReader(fileSimu);
			}

		}
		return hipoReader;
	}

	public static void createPlots(Canvas myCanvas, boolean displayHistos) {

		/* ===== INITIALIZATION ===== */
		detectorPlots = new DetectorPlots(myCanvas, beamEnergy);
		positNegChargesPlots = new PositiveNegativeChargesPlots(myCanvas);
		particlePlots = new ParticlePlots(myCanvas, beamEnergy);
		kinematicPlots = new KinematicalPlots(myCanvas, beamEnergy);
		physicsPlots = new PhysicsPlots(myCanvas, beamEnergy);

		/* ===== RAW PLOTS ===== */
		 detectorPlots.createDetectorsPlotsRaw();
		 detectorPlots.createDetectorsPlotsAfterCuts();
		 detectorPlots.createDetectorsPlotsRandomTrigger();
		
		 positNegChargesPlots.createDefaultHistograms(beamEnergy);
		
		 particlePlots.createParticlesPlotsRaw();
		 particlePlots.createNumberParticlesPlots("", "");

		/* ===== AFTER CUTS ===== */
		particlePlots.createParticlesPlotsAfterCuts("cut selection", "after selection cuts");
		particlePlots.createNumberParticlesPlots("Nb particles after selection", "after selection");
		kinematicPlots.createDefaultHistograms("Kinematic after selection", "");
		physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS after selection", "");
		physicsPlots.createAsymetryHisto("Asym after selection", "");

		particlePlots.createParticlesPlotsAfterCuts("cut DVCS ex1", "after DVCS epg cuts");
		particlePlots.createNumberParticlesPlots("ex1", "after DVCS epg cuts");
		kinematicPlots.createDefaultHistograms("Kinematic ex1", "after DVCS epg cuts");
		physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex1", "after DVCS epg cuts");
		physicsPlots.createAsymetryHisto("Asym DVCS ex1", "after DVCS epg cuts");

		particlePlots.createParticlesPlotsAfterCuts("cut DVCS ex2", "after photon cone cuts");
		particlePlots.createNumberParticlesPlots("Nb particles ex2", "after photon cone cuts");
		kinematicPlots.createDefaultHistograms("Kinematic ex2", "after photon cone cuts");
		physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex2", "after photon cone cuts");
		physicsPlots.createAsymetryHisto("Asym DVCS ex2", "after photon cone cuts");
		
		particlePlots.createParticlesPlotsAfterCuts("cut DVCS ex3", "after pi0 cuts");
		particlePlots.createNumberParticlesPlots("Nb particles ex3", "after pi0 cuts");
		kinematicPlots.createDefaultHistograms("Kinematic ex3", "after pi0 cuts");
		physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex3", "after pi0 cuts");
		physicsPlots.createAsymetryHisto("Asym DVCS ex3", "after pi0 cuts");
		
		particlePlots.createParticlesPlotsAfterCuts("cut DVCS ex4", "just pi0");
		particlePlots.createNumberParticlesPlots("Nb particles ex4", "just pi0");
		kinematicPlots.createDefaultHistograms("Kinematic ex4", "just pi0");
		physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex4", "just pi0");
		physicsPlots.createAsymetryHisto("Asym DVCS ex4", "just pi0");

		physicsPlots.createDefaultPi0Histo(beamEnergy, "Pi0", "");

		// createParticlesPlotsCuts(myCanvas, "cut DVCS ex3", "after DVCS perpMM
		// cuts");
		// createNumberParticlesPlots(myCanvas, "Nb particles ex3", "after DVCS
		// perpMM cuts");
		// physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex3",
		// "after DVCS perpMM cuts");

		// createParticlesPlotsCuts(myCanvas, "cut DVCS ex4", "after DVCS eg
		// cuts");
		// createNumberParticlesPlots(myCanvas, "Nb particles ex4", "after DVCS
		// eg cuts");
		// physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex4",
		// "after DVCS eg cuts");

		// createParticlesPlotsCuts(myCanvas, "cut DVCS ex5", "after DVCS 1phot
		// cuts");
		// createNumberParticlesPlots(myCanvas, "Nb particles ex5", "after DVCS
		// 1phot cuts");
		// physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex5",
		// "after DVCS 1phot cuts");

		// createParticlesPlotsCuts(myCanvas, "cut DVCS ex1", "after proton cone
		// cuts");
		// createNumberParticlesPlots(myCanvas, "Nb particles ex1", "after
		// proton cone cuts");
		// physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex1",
		// "after proton cone cuts");
		// kinematicalPlots.createDefaultHistograms(beamEnergy, "Kinematic ex1",
		// "after proton cone cuts");
		//
		// createParticlesPlotsCuts(myCanvas, "cut DVCS ex2", "after MissingMEG
		// cuts");
		// createNumberParticlesPlots(myCanvas, "Nb particles ex2", "after
		// MissingMEG cuts");
		// physicsPlots.createDefaultHistogramsDVCS(beamEnergy, "DVCS ex2",
		// "after MissingMEG cuts");
		// kinematicalPlots.createDefaultHistograms(beamEnergy, "Kinematic ex2",
		// "after MissingMEG cuts");

		// kinematicalPlots = new KinematicalPlots(myCanvas);
		// kinematicalPlots.createDefaultHistograms(beamEnergy, "DVCS after
		// selection", "");

		/* ===== EXTRA PLOTS ===== */
		// myCanvas.addTab("FaradayCup", 1, 1);
		// myCanvas.create1DHisto("FaradayCup", 1, 1, "FaradayCupValue",
		// "FaradayCupValue", "FaradayCupValue", 200, 0, 100000);

	}

	public static void fillNumberParticlesPlots(Event processedEvent, String description) {
		particlePlots.fillNumberOfParticlesPlots(processedEvent, description);
	}

	public static void fillParticlesPlotsCut(ParticleEvent processedEvent, String tab, String cutDescription) {
		particlePlots.fillParticlesPlotsAfterCuts(processedEvent, tab, cutDescription);
	}

	public static void fillDetectorsPlotsCut(Event processedEvent) {
		detectorPlots.fillDetectorsPlotsAfterCuts(processedEvent);
	}

	public static void fillDetectorsPlotsRandom(Event processedEvent) {
		detectorPlots.fillDetectorsPlotsRandomTrigger(processedEvent);
	}

	public static void createOutputFile(String fileName) {

		try (FileWriter fw = new FileWriter(fileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

		} catch (IOException e) {

		}
	}

	public static void fillOutputProtonFile(String fileName, Event event) {

		try (FileWriter fw = new FileWriter(fileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			if (event.getParticleEvent().hasNumberOfPhotons() > 0) {
				for (Photon photon : event.getParticleEvent().getPhotons()) {
					out.print(photon.getPx() + " " + photon.getPy() + " " + photon.getPz() + " /// ");
				}
				out.println();
			}
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

	}

	public static ParticleEvent readPhotonFileRandom(String fileName, int numberOfLines) {
		Random generator = new Random();
		int i = generator.nextInt(numberOfLines) + 1;
		// System.out.println("random line: "+i);
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get(outPutFile))) {
			line = lines.skip(i - 1).findFirst().get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] tokens = line.split(" ");
		// System.out.println();
		// System.out.println("token size:"+tokens.length);

		// for (String t : tokens)
		// System.out.println(t);

		ParticleEvent randomPhotonEvent = new ParticleEvent();
		for (int nbPhotons = 1; nbPhotons <= tokens.length / 4; nbPhotons++) {
			Particle newPhoton = new Photon();
			newPhoton.setMomentum(Double.parseDouble(tokens[4 * (nbPhotons - 1)]),
					Double.parseDouble(tokens[4 * (nbPhotons - 1) + 1]),
					Double.parseDouble(tokens[4 * (nbPhotons - 1) + 2]));
			// System.out.println();
			// System.out.println("Photon:"+nbPhotons);
			// System.out.println("pX:"+Double.parseDouble(tokens[4*(nbPhotons-1)]));
			// System.out.println("pY:"+Double.parseDouble(tokens[4*(nbPhotons-1)+1]));
			// System.out.println("pZ:"+Double.parseDouble(tokens[4*(nbPhotons-1)+2]));
			randomPhotonEvent.addParticle(newPhoton);
		}
		return randomPhotonEvent;
	}
	
	public static ParticleEvent readProtonFileRandom(String fileName, int numberOfLines) {
		Random generator = new Random();
		int i = generator.nextInt(numberOfLines) + 1;
		// System.out.println("random line: "+i);
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get(outPutFile))) {
			line = lines.skip(i - 1).findFirst().get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] tokens = line.split(" ");
		// System.out.println();
		// System.out.println("token size:"+tokens.length);

		// for (String t : tokens)
		// System.out.println(t);

		ParticleEvent randomPhotonEvent = new ParticleEvent();
		for (int nbProtons = 1; nbProtons <= tokens.length / 4; nbProtons++) {
			Particle newProton = new Proton();
			newProton.setMomentum(Double.parseDouble(tokens[4 * (nbProtons - 1)]),
					Double.parseDouble(tokens[4 * (nbProtons - 1) + 1]),
					Double.parseDouble(tokens[4 * (nbProtons - 1) + 2]));
			
			// System.out.println();
			// System.out.println("Photon:"+nbPhotons);
			// System.out.println("pX:"+Double.parseDouble(tokens[4*(nbPhotons-1)]));
			// System.out.println("pY:"+Double.parseDouble(tokens[4*(nbPhotons-1)+1]));
			// System.out.println("pZ:"+Double.parseDouble(tokens[4*(nbPhotons-1)+2]));
			randomPhotonEvent.addParticle(newProton);
		}
		return randomPhotonEvent;
	}

	public static void readHipoHisto(String[] args) {

		String path = "/Users/gchristi/allPlots2000000.hipo";
		if (args.length > 0 && args[0] != null) {
			path = (String) args[0];
		}

		// Canvas myCanvas = new Canvas("TOT", true);
		Canvas.readAll(path);
	}

	public static void test(String[] args) {
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, Proton.mass);
		System.out.println("Initial proton: " + protonI.toString());

		LorentzVector electronI = new LorentzVector();
		electronI.setPxPyPzM(2, 0, beamEnergy, Electron.mass);
		System.out.println("Initial electron: " + electronI.toString());

		LorentzVector electronF = new LorentzVector();
		electronF.setPxPyPzM(2, 0, -beamEnergy, Electron.mass);
		System.out.println("Final electron: " + electronI.toString());

		LorentzVector protonF = new LorentzVector();
		protonF.setPxPyPzM(1, 1.5, 0, Proton.mass);
		System.out.println("Final proton: " + protonI.toString());

		LorentzVector photonF = new LorentzVector();
		photonF.setPxPyPzM(1, 0, 5, Photon.mass);
		System.out.println("Final photon: " + photonF.toString());

		System.out.println("Virtual photon: " + electronI.substract(electronF).toString());

		System.out.println("PHI: " + ComputePhysicsParameters.computePhiDeg(electronI, electronF, photonF));

		LorentzVector electronInitial = electronI;
		LorentzVector electronFinal = electronF;
		LorentzVector photonFinal = photonF;
		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
		double phi = 0;
		Vector3 v1 = virtualPhoton.vect().cross(electronFinal.vect());
		Vector3 v2 = virtualPhoton.vect().cross(photonFinal.vect());
		double ptot2 = v1.mag2() * v2.mag2();
		if (ptot2 < 0) {
			phi = 0;
		} else {
			double arg = v1.dot(v2) / Math.sqrt(ptot2);
			if (arg > 1)
				arg = 1;
			if (arg < -1)
				arg = -1;
			phi = Math.acos(arg);
		}
		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
			phi = 2 * Math.PI - phi;
		}
		double phiDeg = 180 / Math.PI * phi;
		System.out.println("PHI function: " + phiDeg);

		double angle = ComputePhysicsParameters.angleDegBetween2Vectors(v1, v2);
		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
			angle = 360 - angle;
		}
		System.out.println("PHI calc2: " + angle);

		System.out.println("");
		System.out.println("");

		Vector3 vector1 = new Vector3(-1, 0, 1);
		Vector3 vector2 = new Vector3(0, 0, -1);

		double anglebetween = ComputePhysicsParameters.angleDegBetween2Vectors(vector1, vector2);
		System.out.println("angle diff: " + anglebetween);

	}

	// public static void testBasicReadingFile(String[] args){
	//
	// String dataFile =
	// "/Users/gchristi/Donnees/JLab_Beam/RG-A/Skimmed/4013_8.hipo";
	// HipoDataSource hipoFile = new HipoDataSource();
	// hipoFile.open(dataFile);
	// // Loop over events and print them on the screen
	// int counter = 0;
	// double total= 0;
	//
	// H1F h100 = new H1F("h100",100,-10,10);
	// H1F h101 = new H1F("h101",100,0,1);
	// H1F h102 = new H1F("h102",100,0,1000);
	//
	// h100.setTitle("Missing Mass (e^-#pi^+#pi^-)");
	// h102.setTitle("Invariant Mass (#pi^+#pi^-)");
	//
	// h100.setFillColor(43);
	// h101.setFillColor(42);
	// h102.setFillColor(44);
	//
	// TCanvas c1 = new TCanvas("c1",500,600);
	//
	// // start update canvas (every 1000 ms)
	// c1.getCanvas().initTimer(1000);
	// // draw histograms on the canvas
	// c1.divide(1,2);
	// c1.cd(0);
	// c1.draw(h100);
	// c1.draw(h101,"same");
	// c1.cd(1);
	// c1.draw(h102);
	//
	// while(counter<hipoFile.getSize()){
	// DataEvent event = hipoFile.getNextEvent();
	// counter++;
	// if (counter%1000==0){
	// System.out.println("Event: "+counter);
	// }
	// if (event.hasBank("REC::Particle") == true) {
	// DataBank bankParticle = event.getBank("REC::Particle");
	//
	// for (int particleIterator = 0; particleIterator < bankParticle
	// .rows(); particleIterator++) { /* For all tracks */
	// Particle newParticle;
	// int pid = bankParticle.getInt("pid", particleIterator);
	// double chi2 = bankParticle.getFloat("chi2pid", particleIterator);
	// int charge = bankParticle.getByte("charge", particleIterator);
	// double vx = bankParticle.getFloat("vx", particleIterator);
	// double px = bankParticle.getFloat("px", particleIterator);
	// double beta = bankParticle.getFloat("beta", particleIterator);
	// double a = pid + chi2 +charge+ vx + px +beta;
	// total = total+a;
	// h100.fill(vx);
	// h101.fill(beta);
	// h102.fill(chi2);
	// }
	// }
	// }
	// System.out.println("Done");
	// System.out.println("Total: "+total);
	// }

}
