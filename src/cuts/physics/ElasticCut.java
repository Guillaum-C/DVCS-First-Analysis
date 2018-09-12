package cuts.physics;

import java.util.ArrayList;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Proton;

import physics.ComputePhysicsParameters;

public class ElasticCut {

	public Event Cut(Event oldEvent, LorentzVector electronI) {
		Event newEvent = null;

		ParticleEvent newParticleEvent = null;

		if (oldEvent.getParticleEvent().hasNumberOfElectrons() > 0
				&& oldEvent.getParticleEvent().hasNumberOfProtons() > 0) {
			newParticleEvent = new ParticleEvent();
			ArrayList<Electron> electrons = oldEvent.getParticleEvent().getElectrons();
			ArrayList<Proton> protons = oldEvent.getParticleEvent().getProtons();
			for (Particle electron : electrons) {
				for (Particle proton : protons) {
					
					LorentzVector electronF = electron.getFourMomentum();
					
					double Q2 = ComputePhysicsParameters.computeQ2(electronI, electronF);
					double xB = ComputePhysicsParameters.computeXB(electronI, electronF);
					double W2 = ComputePhysicsParameters.computeW2(electronI, electronF);
					
					for (Particle particleIterator : oldEvent.getParticleEvent().getParticles()) {
						newParticleEvent.addParticle(particleIterator);
					}
				}
			}
		}

		return newEvent;
	}

}
