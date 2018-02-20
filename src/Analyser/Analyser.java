package Analyser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.clas.files.HipoReader;
import org.clas.particles.Particle;
import org.clas.particles.ParticleEvent;
import org.jlab.io.base.DataEvent;

public class Analyser {
	
	public Analyser(){
		
	}
	
	public static void main(String[] args) {
		List<String> fileList = Arrays.asList("/Users/gchristi/out_clas_003050.evio.652.hipo");
		int maxEvents = 1000;
		
		HipoReader hipoReader = new HipoReader(fileList);
		int eventIterator=0;
		while (eventIterator <= maxEvents && hipoReader.hasEvent()) {
			DataEvent event = hipoReader.getNextEvent();
			
			ParticleEvent particleEvent = new ParticleEvent();
			particleEvent.readBanks(event);
			
			ArrayList<Particle> electrons = particleEvent.getElectrons();
			
		}
		
	}
	
}
