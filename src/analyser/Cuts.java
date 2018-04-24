package analyser;

import java.util.ArrayList;

import org.clas12.analysisTools.event.particles.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Cuts {

		public Cuts(){
			
		}
		
		public ParticleEvent CutTriggerBits(ParticleEvent oldParticleEvent, String triggerBits){
			
			return null;
		}
		
		public ParticleEvent CutDVCS(ParticleEvent oldParticleEvent){
			ParticleEvent newParticleEvent = null;
			
			if (oldParticleEvent.hasNumberOfElectrons()>=1 && oldParticleEvent.hasNumberOfProtons()>=0 && oldParticleEvent.hasNumberOfPhotons()>=1){
				newParticleEvent = new ParticleEvent();
				for (Particle particleIterator : oldParticleEvent.getParticles()){
					newParticleEvent.addParticle(particleIterator);				
				}
			}
			
			return newParticleEvent;
		}
		
		public ParticleEvent CutDVCS2(ParticleEvent oldParticleEvent){
			ParticleEvent newParticleEvent = null;
			
			if (oldParticleEvent.hasNumberOfElectrons()==1 && oldParticleEvent.hasNumberOfProtons()==1 && oldParticleEvent.hasNumberOfPhotons()==1){
				newParticleEvent = new ParticleEvent();
				for (Particle particleIterator : oldParticleEvent.getParticles()){
					newParticleEvent.addParticle(particleIterator);				
				}
			}
			
			return newParticleEvent;
		}
		
		public ParticleEvent CutDVCSFromElecAndPhoton(ParticleEvent oldParticleEvent){
			ParticleEvent newParticleEvent = null;
			
			if (oldParticleEvent.hasNumberOfElectrons()>=1 && oldParticleEvent.hasNumberOfPhotons()>=1 && oldParticleEvent.hasNumberOfPhotons()>=1){
				ArrayList<Electron> electrons = oldParticleEvent.getElectrons();
				ArrayList<Photon> photons = oldParticleEvent.getPhotons();
				ArrayList<Proton> protons = oldParticleEvent.getProtons();
				
				LorentzVector protonI = new LorentzVector();
				protonI.setPxPyPzM(0, 0, 0, 0.938272);
				LorentzVector electronI = new LorentzVector();
				electronI.setPxPyPzM(0, 0, 10.6, 0.000511);
				LorentzVector initialState = protonI.sum(electronI);
				
				//newParticleEvent = new ParticleEvent();
				
				for (Electron electronIterator : electrons){
					for (Photon photonIterator : photons){
						LorentzVector electronF = electronIterator.getFourMomentum();
						LorentzVector photonF = photonIterator.getFourMomentum();
						for (Proton protonIterator : protons){
							LorentzVector protonF = protonIterator.getFourMomentum();
							LorentzVector finalState = electronF.sum(photonF, protonF);
							LorentzVector difference = initialState.substract(finalState);
							if ( Math.abs(difference.px())<0.5 && Math.abs(difference.py())<0.5 && Math.abs(difference.pz())<0.5 && Math.abs(difference.e())<0.5 ){
								newParticleEvent = new ParticleEvent();
								newParticleEvent.addParticle(electronIterator);
								newParticleEvent.addParticle(protonIterator);
								newParticleEvent.addParticle(photonIterator);
								System.out.println("FOUND ! Difference: "+difference.toString());
							}
						}
					}
				}
			}
			
			return newParticleEvent;
		}
		
		public ParticleEvent CutPi0(ParticleEvent oldParticleEvent){
			ParticleEvent newParticleEvent = null;
			
			if (oldParticleEvent.hasNumberOfElectrons()>1 && oldParticleEvent.hasNumberOfProtons()>1 && oldParticleEvent.hasNumberOfPhotons()>2){
				newParticleEvent = new ParticleEvent();
				for (Particle particleIterator : oldParticleEvent.getParticles()){
					newParticleEvent.addParticle(particleIterator);				
				}
			}
			
			return newParticleEvent;
		}

	
	
	
	
	
	
	
}
