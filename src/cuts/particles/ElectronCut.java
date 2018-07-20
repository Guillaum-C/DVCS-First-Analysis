package cuts.particles;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;

public class ElectronCut extends ParticleCut {
	
	private ParticleEvent Cut(ParticleEvent oldParticleEvent) {
		ParticleEvent newParticleEvent = new ParticleEvent();
		if (oldParticleEvent.hasNumberOfParticles() > 0) {
			for (Particle particleIterator : oldParticleEvent.getParticles()) {
				if ( !(particleIterator instanceof Electron) || (particleIterator instanceof Electron && isInCuts(particleIterator))){
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
		this.setEnergyMinCut(2);
		return this.Cut(oldEvent);
	}
}
