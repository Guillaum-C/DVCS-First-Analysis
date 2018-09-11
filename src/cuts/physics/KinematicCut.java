package cuts.physics;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;
import org.clas12.analysisTools.event.particles.Proton;

import physics.ComputePhysicsParameters;

public class KinematicCut {

	double q2MinCut = Double.NEGATIVE_INFINITY;
	double q2MaxCut = Double.POSITIVE_INFINITY;

	double xBMinCut = Double.NEGATIVE_INFINITY;
	double xBMaxCut = Double.POSITIVE_INFINITY;

	double w2MinCut = Double.NEGATIVE_INFINITY;
	double w2MaxCut = Double.POSITIVE_INFINITY;
	
	double tMinCut = Double.NEGATIVE_INFINITY;
	double tMaxCut = Double.POSITIVE_INFINITY;

	double phiMinCut = Double.NEGATIVE_INFINITY;
	double phiMaxCut = Double.POSITIVE_INFINITY;
	
	/**
	 * @return the q2MinCut
	 */
	public double getQ2MinCut() {
		return q2MinCut;
	}

	/**
	 * @param q2MinCut the q2MinCut to set
	 */
	public void setQ2MinCut(double q2MinCut) {
		this.q2MinCut = q2MinCut;
	}

	/**
	 * @return the q2MaxCut
	 */
	public double getQ2MaxCut() {
		return q2MaxCut;
	}

	/**
	 * @param q2MaxCut the q2MaxCut to set
	 */
	public void setQ2MaxCut(double q2MaxCut) {
		this.q2MaxCut = q2MaxCut;
	}

	/**
	 * @return the xBMinCut
	 */
	public double getxBMinCut() {
		return xBMinCut;
	}

	/**
	 * @param xBMinCut the xBMinCut to set
	 */
	public void setxBMinCut(double xBMinCut) {
		this.xBMinCut = xBMinCut;
	}

	/**
	 * @return the xBMaxCut
	 */
	public double getxBMaxCut() {
		return xBMaxCut;
	}

	/**
	 * @param xBMaxCut the xBMaxCut to set
	 */
	public void setxBMaxCut(double xBMaxCut) {
		this.xBMaxCut = xBMaxCut;
	}

	/**
	 * @return the w2MinCut
	 */
	public double getW2MinCut() {
		return w2MinCut;
	}

	/**
	 * @param w2MinCut the w2MinCut to set
	 */
	public void setW2MinCut(double w2MinCut) {
		this.w2MinCut = w2MinCut;
	}

	/**
	 * @return the w2MaxCut
	 */
	public double getW2MaxCut() {
		return w2MaxCut;
	}

	/**
	 * @param w2MaxCut the w2MaxCut to set
	 */
	public void setW2MaxCut(double w2MaxCut) {
		this.w2MaxCut = w2MaxCut;
	}

	/**
	 * @return the tMinCut
	 */
	public double gettMinCut() {
		return tMinCut;
	}

	/**
	 * @param tMinCut the tMinCut to set
	 */
	public void settMinCut(double tMinCut) {
		this.tMinCut = tMinCut;
	}

	/**
	 * @return the tMaxCut
	 */
	public double gettMaxCut() {
		return tMaxCut;
	}

	/**
	 * @param tMaxCut the tMaxCut to set
	 */
	public void settMaxCut(double tMaxCut) {
		this.tMaxCut = tMaxCut;
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

	private boolean isInCuts(Electron electronF, LorentzVector electronI){
		
		if ( ComputePhysicsParameters.computeQ2(electronI, electronF) > q2MinCut 
				&& ComputePhysicsParameters.computeQ2(electronI, electronF) < q2MaxCut
				&& ComputePhysicsParameters.computeXB(electronI, electronF) > xBMinCut
				&& ComputePhysicsParameters.computeXB(electronI, electronF) < xBMaxCut
				&& ComputePhysicsParameters.computeW2(electronI, electronF) > w2MinCut
				&& ComputePhysicsParameters.computeW2(electronI, electronF) < w2MaxCut
				) {
			return true;
		}else{
			return false;
		}
	}
	
//	private boolean isInCutsDVCS(Electron electronF, Proton protonF, LorentzVector electronI){
//		return true;
//	}
	
	private ParticleEvent Cut(ParticleEvent oldParticleEvent, LorentzVector electronI) {
		ParticleEvent newParticleEvent = new ParticleEvent();
		if (oldParticleEvent.hasNumberOfElectrons() == 1) {
			Electron electron = oldParticleEvent.getElectrons().get(0);
			if ( isInCuts(electron, electronI) ){
				newParticleEvent=oldParticleEvent;
			}
		} else if (oldParticleEvent.hasNumberOfElectrons() > 1){
			throw new IllegalArgumentException("Wrong number of electrons");
		}
		return newParticleEvent;
	}

	private Event Cut(Event event, LorentzVector electronI){
		Event newEvent = new Event(event);
		newEvent.setParticleEvent(this.Cut(event.getParticleEvent(), electronI));
		return newEvent;
	}
	
	public Event CutQ2(Event event, LorentzVector electronI){
		this.setQ2MinCut(1);
		return this.Cut(event, electronI);
	}
	
	public Event CutW2(Event event, LorentzVector electronI){
		this.setW2MinCut(4);
		return this.Cut(event, electronI);
	}
	
	public Event CutDVCS(Event event, LorentzVector electronI){
		this.setQ2MinCut(1);
		this.setW2MinCut(4);
		return this.Cut(event, electronI);
	}
	
}
