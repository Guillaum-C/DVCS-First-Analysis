package cuts.particles;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Proton;

public class ProtonCut extends ParticleCut {

	private ParticleEvent Cut(ParticleEvent oldParticleEvent) {
		ParticleEvent newParticleEvent = new ParticleEvent();
		if (oldParticleEvent.hasNumberOfParticles() > 0) {
			for (Particle particleIterator : oldParticleEvent.getParticles()) {
				if ( !(particleIterator instanceof Proton) || (particleIterator instanceof Proton && isInCuts(particleIterator))){
					newParticleEvent.addParticle(particleIterator);
				}
			}
		}
		return newParticleEvent;
	}
	
	private Event Cut(Event event){
		Event newEvent = new Event(event);
		newEvent.setParticleEvent(this.Cut(event.getParticleEvent()));
		return newEvent;
	}
	
	public Event CutDVCS(Event oldEvent) {
		return this.Cut(oldEvent);
	}
}
