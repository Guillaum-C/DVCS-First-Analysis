package cuts.particles;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Photon;

public class PhotonCut extends ParticleCut{

	private ParticleEvent Cut(ParticleEvent oldParticleEvent) {
		ParticleEvent newParticleEvent = new ParticleEvent();
		if (oldParticleEvent.hasNumberOfParticles() > 0) {
			for (Particle particleIterator : oldParticleEvent.getParticles()) {
				if ( !(particleIterator instanceof Photon) || (particleIterator instanceof Photon && isInCuts(particleIterator))){
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
	
	public Event CutBackground(Event oldEvent) {
		this.setEnergyMinCut(1);
		return this.Cut(oldEvent);
	}
	
	public Event CutDVCSRemovePi0(Event oldEvent) {
		this.setEnergyMinCut(2);
		return this.Cut(oldEvent);
	}
	
	public Event CutPi0(Event oldEvent) {
		this.setEnergyMinCut(1);
		return this.Cut(oldEvent);
	}
	
	
}
