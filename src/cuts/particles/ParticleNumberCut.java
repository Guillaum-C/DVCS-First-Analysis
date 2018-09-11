package cuts.particles;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.ParticleEvent;

public class ParticleNumberCut {
	
	int particleNbMinCut = Integer.MIN_VALUE;
	int particleNbMaxCut = Integer.MAX_VALUE;
	
	int electronNbMinCut = Integer.MIN_VALUE;
	int electronNbMaxCut = Integer.MAX_VALUE;
	
	int photonNbMinCut = Integer.MIN_VALUE;
	int photonNbMaxCut = Integer.MAX_VALUE;
	
	int protonNbMinCut = Integer.MIN_VALUE;
	int protonNbMaxCut = Integer.MAX_VALUE;
	
	/**
	 * @return the particleNbMinCut
	 */
	public int getParticleNbMinCut() {
		return particleNbMinCut;
	}

	/**
	 * @param particleNbMinCut the particleNbMinCut to set
	 */
	public void setParticleNbMinCut(int particleNbMinCut) {
		this.particleNbMinCut = particleNbMinCut;
	}

	/**
	 * @return the particleNbMaxCut
	 */
	public int getParticleNbMaxCut() {
		return particleNbMaxCut;
	}

	/**
	 * @param particleNbMaxCut the particleNbMaxCut to set
	 */
	public void setParticleNbMaxCut(int particleNbMaxCut) {
		this.particleNbMaxCut = particleNbMaxCut;
	}

	/**
	 * @return the electronNbMinCut
	 */
	public int getElectronNbMinCut() {
		return electronNbMinCut;
	}

	/**
	 * @param electronNbMinCut the electronNbMinCut to set
	 */
	public void setElectronNbMinCut(int electronNbMinCut) {
		this.electronNbMinCut = electronNbMinCut;
	}

	/**
	 * @return the electronNbMaxCut
	 */
	public int getElectronNbMaxCut() {
		return electronNbMaxCut;
	}

	/**
	 * @param electronNbMaxCut the electronNbMaxCut to set
	 */
	public void setElectronNbMaxCut(int electronNbMaxCut) {
		this.electronNbMaxCut = electronNbMaxCut;
	}

	/**
	 * @return the photonNbMinCut
	 */
	public int getPhotonNbMinCut() {
		return photonNbMinCut;
	}

	/**
	 * @param photonNbMinCut the photonNbMinCut to set
	 */
	public void setPhotonNbMinCut(int photonNbMinCut) {
		this.photonNbMinCut = photonNbMinCut;
	}

	/**
	 * @return the photonNbMaxCut
	 */
	public int getPhotonNbMaxCut() {
		return photonNbMaxCut;
	}

	/**
	 * @param photonNbMaxCut the photonNbMaxCut to set
	 */
	public void setPhotonNbMaxCut(int photonNbMaxCut) {
		this.photonNbMaxCut = photonNbMaxCut;
	}

	/**
	 * @return the protonNbMinCut
	 */
	public int getProtonNbMinCut() {
		return protonNbMinCut;
	}

	/**
	 * @param protonNbMinCut the protonNbMinCut to set
	 */
	public void setProtonNbMinCut(int protonNbMinCut) {
		this.protonNbMinCut = protonNbMinCut;
	}

	/**
	 * @return the protonNbMaxCut
	 */
	public int getProtonNbMaxCut() {
		return protonNbMaxCut;
	}

	/**
	 * @param protonNbMaxCut the protonNbMaxCut to set
	 */
	public void setProtonNbMaxCut(int protonNbMaxCut) {
		this.protonNbMaxCut = protonNbMaxCut;
	}

	private boolean isInCuts(ParticleEvent oldParticleEvent){
		if (oldParticleEvent.hasNumberOfParticles() >= particleNbMinCut && oldParticleEvent.hasNumberOfParticles() <= particleNbMaxCut
				&& oldParticleEvent.hasNumberOfElectrons() >= electronNbMinCut && oldParticleEvent.hasNumberOfElectrons() <= electronNbMaxCut
				&& oldParticleEvent.hasNumberOfPhotons() >= photonNbMinCut && oldParticleEvent.hasNumberOfPhotons() <= photonNbMaxCut
				&& oldParticleEvent.hasNumberOfProtons() >= protonNbMinCut && oldParticleEvent.hasNumberOfProtons() <= protonNbMaxCut
				) {
			return true;
		}else{
			return false;
		}
	}
	
	private ParticleEvent cut(ParticleEvent oldParticleEvent) {
		ParticleEvent newParticleEvent = new ParticleEvent();
		if (this.isInCuts(oldParticleEvent)) {
			newParticleEvent = oldParticleEvent;
		}
		return newParticleEvent;
	}
	
	private Event cut(Event oldEvent){
		Event newEvent = new Event(oldEvent);
		newEvent.setParticleEvent(this.cut(oldEvent.getParticleEvent()));
		return newEvent;
	}
	
	public Event cutDVCS(Event oldEvent){
		this.setElectronNbMinCut(1);
		this.setElectronNbMaxCut(1);
		this.setPhotonNbMinCut(1);
		this.setProtonNbMinCut(1);
		return this.cut(oldEvent);
	}
	
	public Event cutPi0(Event oldEvent){
		this.setElectronNbMinCut(1);
		this.setElectronNbMaxCut(1);
		this.setPhotonNbMinCut(2);
		this.setProtonNbMinCut(1);
		return this.cut(oldEvent);
	}
	
	
}
