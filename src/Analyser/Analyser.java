package Analyser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.clas.files.HipoReader;
import org.clas.particles.Particle;
import org.clas.particles.ParticleEvent;
import org.clas.plots.Canvas;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;
import org.jlab.io.base.DataEvent;

public class Analyser {
	
	public Analyser(){
		
	}
	
	public static void main(String[] args) {
		
		/* ===== OPEN FILE ===== */
//		List<String> fileList = Arrays.asList("/Users/gchristi/Donnees_JLab/EngineeringRun_Part2_January/Run2451_2GeV_3nA_60pcFields_targetFilled_19Jan2018/out_clas_002451.evio.90.hipo", "/Users/gchristi/Donnees_JLab/EngineeringRun_Part2_January/Run2451_2GeV_3nA_60pcFields_targetFilled_19Jan2018/out_clas_002451.evio.91.hipo");
		String path = "/Users/gchristi/Donnees_JLab/EngineeringRun_Part2_January/Run2451_2GeV_3nA_60pcFields_targetFilled_19Jan2018/";
//		HipoReader hipoReader = new HipoReader(fileList);
		HipoReader hipoReader = new HipoReader(path, "002451");
		
		
		/* ===== CREATE PLOTS ===== */
		Canvas myCanvas = new Canvas();
		myCanvas.addTab("electron", 1, 1);
		myCanvas.addTab("proton", 1, 1);
		myCanvas.addTab("photon", 1, 1);
		
		H2F histoElectron = new H2F("electron", 360, -180, 180, 90, 0, 90);
		H2F histoProton = new H2F("proton", 360, -180, 180, 90, 0, 90);
		H2F histoPhoton = new H2F("photon", 360, -180, 180, 90, 0, 90);
		
		myCanvas.draw("electron", 1, 1, histoElectron);
		myCanvas.draw("proton", 1, 1, histoProton);
		myCanvas.draw("photon", 1, 1, histoPhoton);
		
		/* ===== ITERATE ===== */
		int maxEvents = 1000000;
		int eventIterator=1;
		while (eventIterator <= maxEvents && hipoReader.hasEvent()) {
			System.out.println("Event iterator"+eventIterator);	
			DataEvent event = hipoReader.getNextEvent();
			//event.show();
			
			ParticleEvent particleEvent = new ParticleEvent(false);
			particleEvent.readBanks(event);
			
			if (particleEvent.hasElectrons()>0){
				ArrayList<Particle> electrons = particleEvent.getElectrons();
				System.out.println(electrons.get(0).getPx());
				System.out.println(electrons.get(0).getPy());
//				System.out.println(electrons.get(0).getPz());
				System.out.println(electrons.get(0).getPhiDeg());
//				System.out.println(electrons.get(0).getThetaDeg());
				histoElectron.fill(electrons.get(0).getPhiDeg(), electrons.get(0).getThetaDeg());
			}
			if (particleEvent.hasProtons()>0){
				ArrayList<Particle> protons = particleEvent.getProtons();
				histoProton.fill(protons.get(0).getPhiDeg(), protons.get(0).getThetaDeg());
			}
			if (particleEvent.hasPhotons()>0){
				ArrayList<Particle> photons = particleEvent.getPhotons();
				histoPhoton.fill(photons.get(0).getPhiDeg(), photons.get(0).getThetaDeg());
			}
			
			//System.out.println("Event iterator"+eventIterator+" DONE");
			System.out.println("");
			eventIterator++;
		}
	}
}
