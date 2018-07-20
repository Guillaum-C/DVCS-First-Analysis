package cuts.particles;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;

public class ParticleCut {

	double energyMinCut = Double.NEGATIVE_INFINITY;
	double energyMaxCut = Double.POSITIVE_INFINITY;

	double momentumXMinCut = Double.NEGATIVE_INFINITY;
	double momentumXMaxCut = Double.POSITIVE_INFINITY;

	double momentumYMinCut = Double.NEGATIVE_INFINITY;
	double momentumYMaxCut = Double.POSITIVE_INFINITY;

	double momentumZMinCut = Double.NEGATIVE_INFINITY;
	double momentumZMaxCut = Double.POSITIVE_INFINITY;

	double vertexXMinCut = Double.NEGATIVE_INFINITY;
	double vertexXMaxCut = Double.POSITIVE_INFINITY;

	double vertexYMinCut = Double.NEGATIVE_INFINITY;
	double vertexYMaxCut = Double.POSITIVE_INFINITY;

	double vertexZMinCut = Double.NEGATIVE_INFINITY;
	double vertexZMaxCut = Double.POSITIVE_INFINITY;

	double thetaMinCut = Double.NEGATIVE_INFINITY;
	double thetaMaxCut = Double.POSITIVE_INFINITY;

	double phiMinCut = Double.NEGATIVE_INFINITY;
	double phiMaxCut = Double.POSITIVE_INFINITY;

	double chargeMinCut = Double.NEGATIVE_INFINITY;
	double chargeMaxCut = Double.POSITIVE_INFINITY;

	double betaMinCut = Double.NEGATIVE_INFINITY;
	double betaMaxCut = Double.POSITIVE_INFINITY;

	double chi2pidMinCut = Double.NEGATIVE_INFINITY;
	double chi2pidMaxCut = Double.POSITIVE_INFINITY;

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
	 * @param energyMinCut
	 *            the energyMinCut to set
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
	 * @param energyMaxCut
	 *            the energyMaxCut to set
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
	 * @param momentumXMinCut
	 *            the momentumXMinCut to set
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
	 * @param momentumXMaxCut
	 *            the momentumXMaxCut to set
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
	 * @param momentumYMinCut
	 *            the momentumYMinCut to set
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
	 * @param momentumYMaxCut
	 *            the momentumYMaxCut to set
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
	 * @param momentumZMinCut
	 *            the momentumZMinCut to set
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
	 * @param momentumZMaxCut
	 *            the momentumZMaxCut to set
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
	 * @param vertexXMinCut
	 *            the vertexXMinCut to set
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
	 * @param vertexXMaxCut
	 *            the vertexXMaxCut to set
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
	 * @param vertexYMinCut
	 *            the vertexYMinCut to set
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
	 * @param vertexYMaxCut
	 *            the vertexYMaxCut to set
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
	 * @param vertexZMinCut
	 *            the vertexZMinCut to set
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
	 * @param vertexZMaxCut
	 *            the vertexZMaxCut to set
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
	 * @param thetaMinCut
	 *            the thetaMinCut to set
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
	 * @param thetaMaxCut
	 *            the thetaMaxCut to set
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
	 * @param phiMinCut
	 *            the phiMinCut to set
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
	 * @param phiMaxCut
	 *            the phiMaxCut to set
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
	 * @param chargeMinCut
	 *            the chargeMinCut to set
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
	 * @param chargeMaxCut
	 *            the chargeMaxCut to set
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
	 * @param betaMinCut
	 *            the betaMinCut to set
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
	 * @param betaMaxCut
	 *            the betaMaxCut to set
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
	 * @param chi2pidMinCut
	 *            the chi2pidMinCut to set
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
	 * @param chi2pidMaxCut
	 *            the chi2pidMaxCut to set
	 */
	public void setChi2pidMaxCut(double chi2pidMaxCut) {
		this.chi2pidMaxCut = chi2pidMaxCut;
	}

	/**
	 * Analyze if a particle is in the cut requirements
	 * @param particleToTest particle to analyze
	 * @return false if particle is supposed to be cut, true if particle is in the requirements
	 */
	public boolean isInCuts(Particle particleToTest) {
		if (particleToTest.getEnergy() > energyMinCut && particleToTest.getEnergy() < energyMaxCut
				&& particleToTest.getMomentum().x() > momentumXMinCut
				&& particleToTest.getMomentum().x() < momentumXMaxCut
				&& particleToTest.getMomentum().y() > momentumYMinCut
				&& particleToTest.getMomentum().y() < momentumYMaxCut
				&& particleToTest.getMomentum().z() > momentumZMinCut
				&& particleToTest.getMomentum().z() < momentumZMaxCut
				&& particleToTest.getVertex().x() > vertexXMinCut && particleToTest.getVertex().x() < vertexXMaxCut
				&& particleToTest.getVertex().y() > vertexYMinCut && particleToTest.getVertex().y() < vertexYMaxCut
				&& particleToTest.getVertex().z() > vertexZMinCut && particleToTest.getVertex().z() < vertexZMaxCut
				&& particleToTest.getTheta() > thetaMinCut && particleToTest.getTheta() < thetaMaxCut
				&& particleToTest.getPhi() > phiMinCut && particleToTest.getPhi() < phiMaxCut
				&& particleToTest.getCharge() > chargeMinCut && particleToTest.getCharge() < chargeMaxCut
				&& particleToTest.getBeta() > betaMinCut && particleToTest.getBeta() < chargeMaxCut
				&& particleToTest.getChi2pid() > chi2pidMinCut && particleToTest.getChi2pid() < chi2pidMaxCut) {
			return true;
		}else{
			return false;
		}
	}
	
	public boolean  isInCutsDebug(Particle particleToTest) {
		System.out.println("Particle to test energy: "+particleToTest.getEnergy());
		if (particleToTest.getEnergy() > energyMinCut && particleToTest.getEnergy() < energyMaxCut){
			System.out.println("OK energy");
		}
		if(particleToTest.getMomentum().x() > momentumXMinCut
				&& particleToTest.getMomentum().x() < momentumXMaxCut
				&& particleToTest.getMomentum().y() > momentumYMinCut
				&& particleToTest.getMomentum().y() < momentumYMaxCut
				&& particleToTest.getMomentum().z() > momentumZMinCut
				&& particleToTest.getMomentum().z() < momentumZMaxCut){
			System.out.println("OK momentum");
		}
		if (particleToTest.getVertex().x() > vertexXMinCut && particleToTest.getVertex().x() < vertexXMaxCut
				&& particleToTest.getVertex().y() > vertexYMinCut && particleToTest.getVertex().y() < vertexYMaxCut
				&& particleToTest.getVertex().z() > vertexZMinCut && particleToTest.getVertex().z() < vertexZMaxCut){
			System.out.println("OK vertex");
		}
		if (particleToTest.getTheta() > thetaMinCut && particleToTest.getTheta() < thetaMaxCut
				&& particleToTest.getPhi() > phiMinCut && particleToTest.getPhi() < phiMaxCut){
			System.out.println("OK thetaPhi");
		}
		if(particleToTest.getCharge() > chargeMinCut && particleToTest.getCharge() < chargeMaxCut
				&& particleToTest.getBeta() > betaMinCut && particleToTest.getBeta() < chargeMaxCut
				&& particleToTest.getChi2pid() > chi2pidMinCut && particleToTest.getChi2pid() < chi2pidMaxCut) {
			System.out.println("OK charge beta pid");
		}
		if (particleToTest.getEnergy() > energyMinCut && particleToTest.getEnergy() < energyMaxCut
				&& particleToTest.getMomentum().x() > momentumXMinCut
				&& particleToTest.getMomentum().x() < momentumXMaxCut
				&& particleToTest.getMomentum().y() > momentumYMinCut
				&& particleToTest.getMomentum().y() < momentumYMaxCut
				&& particleToTest.getMomentum().z() > momentumZMinCut
				&& particleToTest.getMomentum().z() < momentumZMaxCut
				&& particleToTest.getVertex().x() > vertexXMinCut && particleToTest.getVertex().x() < vertexXMaxCut
				&& particleToTest.getVertex().y() > vertexYMinCut && particleToTest.getVertex().y() < vertexYMaxCut
				&& particleToTest.getVertex().z() > vertexZMinCut && particleToTest.getVertex().z() < vertexZMaxCut
				&& particleToTest.getTheta() > thetaMinCut && particleToTest.getTheta() < thetaMaxCut
				&& particleToTest.getPhi() > phiMinCut && particleToTest.getPhi() < phiMaxCut
				&& particleToTest.getCharge() > chargeMinCut && particleToTest.getCharge() < chargeMaxCut
				&& particleToTest.getBeta() > betaMinCut && particleToTest.getBeta() < chargeMaxCut
				&& particleToTest.getChi2pid() > chi2pidMinCut && particleToTest.getChi2pid() < chi2pidMaxCut) {
			return true;
		}else{
			return false;
		}
	}

}
