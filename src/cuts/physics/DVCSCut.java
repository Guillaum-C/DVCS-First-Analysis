package cuts.physics;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;

public class DVCSCut {

	public Event Cut(Event oldEvent) {
		Event newEvent = null;
		
		ParticleEvent newParticleEvent = null;

		if (oldEvent.getParticleEvent().hasNumberOfElectrons() > 0 && oldEvent.getParticleEvent().hasNumberOfProtons() > 0
				&& oldEvent.getParticleEvent().hasNumberOfPhotons() > 0) {
			newParticleEvent = new ParticleEvent();
			for (Particle particleIterator : oldEvent.getParticleEvent().getParticles()) {
				newParticleEvent.addParticle(particleIterator);
			}
		}

		return newEvent;
	}
	
	
}
