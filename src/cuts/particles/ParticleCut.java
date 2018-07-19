package cuts.particles;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;

public class ParticleCut {

	double energyMinCut = Double.POSITIVE_INFINITY;
	double energyMaxCut = Double.NEGATIVE_INFINITY;
	
	double momentumXMinCut = Double.POSITIVE_INFINITY;
	double momentumXMaxCut = Double.NEGATIVE_INFINITY;
	
	double momentumYMinCut = Double.POSITIVE_INFINITY;
	double momentumYMaxCut = Double.NEGATIVE_INFINITY;
	
	double momentumZMinCut = Double.POSITIVE_INFINITY;
	double momentumZMaxCut = Double.NEGATIVE_INFINITY;
	
	double vertexXMinCut = Double.POSITIVE_INFINITY;
	double vertexXMaxCut = Double.NEGATIVE_INFINITY;
	
	double vertexYMinCut = Double.POSITIVE_INFINITY;
	double vertexYMaxCut = Double.NEGATIVE_INFINITY;
	
	double vertexZMinCut = Double.POSITIVE_INFINITY;
	double vertexZMaxCut = Double.NEGATIVE_INFINITY;
	
	double thetaMinCut = Double.POSITIVE_INFINITY;
	double thetaMaxCut = Double.NEGATIVE_INFINITY;
	
	double phiMinCut = Double.POSITIVE_INFINITY;
	double phiMaxCut = Double.NEGATIVE_INFINITY;
	
	double chargeMinCut = Double.POSITIVE_INFINITY;
	double chargeMaxCut = Double.NEGATIVE_INFINITY;
	
	double betaMinCut = Double.POSITIVE_INFINITY;
	double betaMaxCut = Double.NEGATIVE_INFINITY;
	
	double chi2pidMinCut = Double.POSITIVE_INFINITY;
	double chi2pidMaxCut = Double.NEGATIVE_INFINITY;
	
	
	
	/**
	 * Creates a new particle cut
	 */
	public ParticleCut() {
		super();
	}
	
	/**
	 * @return the energyMinCut
	 */
	public double getEnergyMinCut() {
		return energyMinCut;
	}

	/**
	 * @param energyMinCut the energyMinCut to set
	 */
	public void setEnergyMinCut(double energyMinCut) {
		this.energyMinCut = energyMinCut;
	}

	/**
	 * @return the energyMaxCut
	 */
	public double getEnergyMaxCut() {
		return energyMaxCut;
	}

	/**
	 * @param energyMaxCut the energyMaxCut to set
	 */
	public void setEnergyMaxCut(double energyMaxCut) {
		this.energyMaxCut = energyMaxCut;
	}

	/**
	 * @return the momentumXMinCut
	 */
	public double getMomentumXMinCut() {
		return momentumXMinCut;
	}

	/**
	 * @param momentumXMinCut the momentumXMinCut to set
	 */
	public void setMomentumXMinCut(double momentumXMinCut) {
		this.momentumXMinCut = momentumXMinCut;
	}

	/**
	 * @return the momentumXMaxCut
	 */
	public double getMomentumXMaxCut() {
		return momentumXMaxCut;
	}

	/**
	 * @param momentumXMaxCut the momentumXMaxCut to set
	 */
	public void setMomentumXMaxCut(double momentumXMaxCut) {
		this.momentumXMaxCut = momentumXMaxCut;
	}

	/**
	 * @return the momentumYMinCut
	 */
	public double getMomentumYMinCut() {
		return momentumYMinCut;
	}

	/**
	 * @param momentumYMinCut the momentumYMinCut to set
	 */
	public void setMomentumYMinCut(double momentumYMinCut) {
		this.momentumYMinCut = momentumYMinCut;
	}

	/**
	 * @return the momentumYMaxCut
	 */
	public double getMomentumYMaxCut() {
		return momentumYMaxCut;
	}

	/**
	 * @param momentumYMaxCut the momentumYMaxCut to set
	 */
	public void setMomentumYMaxCut(double momentumYMaxCut) {
		this.momentumYMaxCut = momentumYMaxCut;
	}

	/**
	 * @return the momentumZMinCut
	 */
	public double getMomentumZMinCut() {
		return momentumZMinCut;
	}

	/**
	 * @param momentumZMinCut the momentumZMinCut to set
	 */
	public void setMomentumZMinCut(double momentumZMinCut) {
		this.momentumZMinCut = momentumZMinCut;
	}

	/**
	 * @return the momentumZMaxCut
	 */
	public double getMomentumZMaxCut() {
		return momentumZMaxCut;
	}

	/**
	 * @param momentumZMaxCut the momentumZMaxCut to set
	 */
	public void setMomentumZMaxCut(double momentumZMaxCut) {
		this.momentumZMaxCut = momentumZMaxCut;
	}

	/**
	 * @return the vertexXMinCut
	 */
	public double getVertexXMinCut() {
		return vertexXMinCut;
	}

	/**
	 * @param vertexXMinCut the vertexXMinCut to set
	 */
	public void setVertexXMinCut(double vertexXMinCut) {
		this.vertexXMinCut = vertexXMinCut;
	}

	/**
	 * @return the vertexXMaxCut
	 */
	public double getVertexXMaxCut() {
		return vertexXMaxCut;
	}

	/**
	 * @param vertexXMaxCut the vertexXMaxCut to set
	 */
	public void setVertexXMaxCut(double vertexXMaxCut) {
		this.vertexXMaxCut = vertexXMaxCut;
	}

	/**
	 * @return the vertexYMinCut
	 */
	public double getVertexYMinCut() {
		return vertexYMinCut;
	}

	/**
	 * @param vertexYMinCut the vertexYMinCut to set
	 */
	public void setVertexYMinCut(double vertexYMinCut) {
		this.vertexYMinCut = vertexYMinCut;
	}

	/**
	 * @return the vertexYMaxCut
	 */
	public double getVertexYMaxCut() {
		return vertexYMaxCut;
	}

	/**
	 * @param vertexYMaxCut the vertexYMaxCut to set
	 */
	public void setVertexYMaxCut(double vertexYMaxCut) {
		this.vertexYMaxCut = vertexYMaxCut;
	}

	/**
	 * @return the vertexZMinCut
	 */
	public double getVertexZMinCut() {
		return vertexZMinCut;
	}

	/**
	 * @param vertexZMinCut the vertexZMinCut to set
	 */
	public void setVertexZMinCut(double vertexZMinCut) {
		this.vertexZMinCut = vertexZMinCut;
	}

	/**
	 * @return the vertexZMaxCut
	 */
	public double getVertexZMaxCut() {
		return vertexZMaxCut;
	}

	/**
	 * @param vertexZMaxCut the vertexZMaxCut to set
	 */
	public void setVertexZMaxCut(double vertexZMaxCut) {
		this.vertexZMaxCut = vertexZMaxCut;
	}

	/**
	 * @return the thetaMinCut
	 */
	public double getThetaMinCut() {
		return thetaMinCut;
	}

	/**
	 * @param thetaMinCut the thetaMinCut to set
	 */
	public void setThetaMinCut(double thetaMinCut) {
		this.thetaMinCut = thetaMinCut;
	}

	/**
	 * @return the thetaMaxCut
	 */
	public double getThetaMaxCut() {
		return thetaMaxCut;
	}

	/**
	 * @param thetaMaxCut the thetaMaxCut to set
	 */
	public void setThetaMaxCut(double thetaMaxCut) {
		this.thetaMaxCut = thetaMaxCut;
	}

	/**
	 * @return the phiMinCut
	 */
	public double getPhiMinCut() {
		return phiMinCut;
	}

	/**
	 * @param phiMinCut the phiMinCut to set
	 */
	public void setPhiMinCut(double phiMinCut) {
		this.phiMinCut = phiMinCut;
	}

	/**
	 * @return the phiMaxCut
	 */
	public double getPhiMaxCut() {
		return phiMaxCut;
	}

	/**
	 * @param phiMaxCut the phiMaxCut to set
	 */
	public void setPhiMaxCut(double phiMaxCut) {
		this.phiMaxCut = phiMaxCut;
	}

	/**
	 * @return the chargeMinCut
	 */
	public double getChargeMinCut() {
		return chargeMinCut;
	}

	/**
	 * @param chargeMinCut the chargeMinCut to set
	 */
	public void setChargeMinCut(double chargeMinCut) {
		this.chargeMinCut = chargeMinCut;
	}

	/**
	 * @return the chargeMaxCut
	 */
	public double getChargeMaxCut() {
		return chargeMaxCut;
	}

	/**
	 * @param chargeMaxCut the chargeMaxCut to set
	 */
	public void setChargeMaxCut(double chargeMaxCut) {
		this.chargeMaxCut = chargeMaxCut;
	}

	/**
	 * @return the betaMinCut
	 */
	public double getBetaMinCut() {
		return betaMinCut;
	}

	/**
	 * @param betaMinCut the betaMinCut to set
	 */
	public void setBetaMinCut(double betaMinCut) {
		this.betaMinCut = betaMinCut;
	}

	/**
	 * @return the betaMaxCut
	 */
	public double getBetaMaxCut() {
		return betaMaxCut;
	}

	/**
	 * @param betaMaxCut the betaMaxCut to set
	 */
	public void setBetaMaxCut(double betaMaxCut) {
		this.betaMaxCut = betaMaxCut;
	}

	/**
	 * @return the chi2pidMinCut
	 */
	public double getChi2pidMinCut() {
		return chi2pidMinCut;
	}

	/**
	 * @param chi2pidMinCut the chi2pidMinCut to set
	 */
	public void setChi2pidMinCut(double chi2pidMinCut) {
		this.chi2pidMinCut = chi2pidMinCut;
	}

	/**
	 * @return the chi2pidMaxCut
	 */
	public double getChi2pidMaxCut() {
		return chi2pidMaxCut;
	}

	/**
	 * @param chi2pidMaxCut the chi2pidMaxCut to set
	 */
	public void setChi2pidMaxCut(double chi2pidMaxCut) {
		this.chi2pidMaxCut = chi2pidMaxCut;
	}

	/**
	 * Cut using parameters
	 * @param oldParticleEvent event on which we apply cuts
	 * @return event after cuts
	 */
	public ParticleEvent Cut(ParticleEvent oldParticleEvent) {
		ParticleEvent newParticleEvent = null;

		if (oldParticleEvent.hasNumberOfParticles() > 0) {
			newParticleEvent = new ParticleEvent();
			for (Particle particleIterator : oldParticleEvent.getParticles()) {
				if (particleIterator.getEnergy() > energyMinCut && particleIterator.getEnergy() < energyMaxCut &&
						particleIterator.getMomentum().x() > momentumXMinCut && particleIterator.getMomentum().x() < momentumXMaxCut &&
						particleIterator.getMomentum().y() > momentumYMinCut && particleIterator.getMomentum().y() < momentumYMaxCut &&
						particleIterator.getMomentum().z() > momentumZMinCut && particleIterator.getMomentum().z() < momentumZMaxCut &&
						particleIterator.getVertex().x() > vertexXMinCut && particleIterator.getVertex().x() < vertexXMaxCut &&
						particleIterator.getVertex().y() > vertexYMinCut && particleIterator.getVertex().y() < vertexYMaxCut &&
						particleIterator.getVertex().z() > vertexZMinCut && particleIterator.getVertex().z() < vertexZMaxCut &&
						particleIterator.getTheta() > thetaMinCut && particleIterator.getTheta() < thetaMaxCut &&
						particleIterator.getPhi() > phiMinCut && particleIterator.getPhi() < phiMaxCut &&
						particleIterator.getCharge() > chargeMinCut && particleIterator.getCharge() < chargeMaxCut &&
						particleIterator.getBeta() > betaMinCut && particleIterator.getBeta() < chargeMaxCut &&
						particleIterator.getChi2pid() > chi2pidMinCut && particleIterator.getChi2pid() < chi2pidMaxCut){
					newParticleEvent.addParticle(particleIterator);
				}
			}
		}

		return newParticleEvent;
	}
	
	
	
	
	
	
}
