package cuts.central;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.central.cvt.CVTEvent;
import org.clas12.analysisTools.event.central.cvt.CVTRecTrack;
import org.clas12.analysisTools.event.particles.Particle;
import org.clas12.analysisTools.event.particles.ParticleEvent;

public class CVTCut {

	int ndfMinCut = Integer.MIN_VALUE;
	int ndfMaxCut = Integer.MAX_VALUE;

	double chi2MinCut = Double.NEGATIVE_INFINITY;
	double chi2MaxCut = Double.POSITIVE_INFINITY;

	double chi2OverNdfMinCut = Double.NEGATIVE_INFINITY;
	double chi2OverNdfMaxCut = Double.POSITIVE_INFINITY;
	
	double vertexXMinCut = Double.NEGATIVE_INFINITY;
	double vertexXMaxCut = Double.POSITIVE_INFINITY;
	
	double vertexYMinCut = Double.NEGATIVE_INFINITY;
	double vertexYMaxCut = Double.POSITIVE_INFINITY;
	
	double vertexZMinCut = Double.NEGATIVE_INFINITY;
	double vertexZMaxCut = Double.POSITIVE_INFINITY;
	
	double vertexTransverseMinCut = Double.NEGATIVE_INFINITY;
	double vertexTransverseMaxCut = Double.POSITIVE_INFINITY;
	
	double momentumXMinCut = Double.NEGATIVE_INFINITY;
	double momentumXMaxCut = Double.POSITIVE_INFINITY;
	
	double momentumYMinCut = Double.NEGATIVE_INFINITY;
	double momentumYMaxCut = Double.POSITIVE_INFINITY;
	
	double momentumZMinCut = Double.NEGATIVE_INFINITY;
	double momentumZMaxCut = Double.POSITIVE_INFINITY;
	
	double momentumTransverseMinCut = Double.NEGATIVE_INFINITY;
	double momentumTransverseMaxCut = Double.POSITIVE_INFINITY;
	
	double momentumMinCut = Double.NEGATIVE_INFINITY;
	double momentumMaxCut = Double.POSITIVE_INFINITY;
	
	double thetaMinCut = Double.NEGATIVE_INFINITY;
	double thetaMaxCut = Double.POSITIVE_INFINITY;
	
	double phiMinCut = Double.NEGATIVE_INFINITY;
	double phiMaxCut = Double.POSITIVE_INFINITY;
	
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
	 * @return the vertexTransverseMinCut
	 */
	public double getVertexTransverseMinCut() {
		return vertexTransverseMinCut;
	}

	/**
	 * @param vertexTransverseMinCut the vertexTransverseMinCut to set
	 */
	public void setVertexTransverseMinCut(double vertexTransverseMinCut) {
		this.vertexTransverseMinCut = vertexTransverseMinCut;
	}

	/**
	 * @return the vertexTransverseMaxCut
	 */
	public double getVertexTransverseMaxCut() {
		return vertexTransverseMaxCut;
	}

	/**
	 * @param vertexTransverseMaxCut the vertexTransverseMaxCut to set
	 */
	public void setVertexTransverseMaxCut(double vertexTransverseMaxCut) {
		this.vertexTransverseMaxCut = vertexTransverseMaxCut;
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
	 * @return the momentumTransverseMinCut
	 */
	public double getMomentumTransverseMinCut() {
		return momentumTransverseMinCut;
	}

	/**
	 * @param momentumTransverseMinCut the momentumTransverseMinCut to set
	 */
	public void setMomentumTransverseMinCut(double momentumTransverseMinCut) {
		this.momentumTransverseMinCut = momentumTransverseMinCut;
	}

	/**
	 * @return the momentumTransverseMaxCut
	 */
	public double getMomentumTransverseMaxCut() {
		return momentumTransverseMaxCut;
	}

	/**
	 * @param momentumTransverseMaxCut the momentumTransverseMaxCut to set
	 */
	public void setMomentumTransverseMaxCut(double momentumTransverseMaxCut) {
		this.momentumTransverseMaxCut = momentumTransverseMaxCut;
	}

	/**
	 * @return the momentumMinCut
	 */
	public double getMomentumMinCut() {
		return momentumMinCut;
	}

	/**
	 * @param momentumMinCut the momentumMinCut to set
	 */
	public void setMomentumMinCut(double momentumMinCut) {
		this.momentumMinCut = momentumMinCut;
	}

	/**
	 * @return the momentumMaxCut
	 */
	public double getMomentumMaxCut() {
		return momentumMaxCut;
	}

	/**
	 * @param momentumMaxCut the momentumMaxCut to set
	 */
	public void setMomentumMaxCut(double momentumMaxCut) {
		this.momentumMaxCut = momentumMaxCut;
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
	 * @return the ndfMinCut
	 */
	public int getNdfMinCut() {
		return ndfMinCut;
	}

	/**
	 * @param ndfMinCut
	 *            the ndfMinCut to set
	 */
	public void setNdfMinCut(int ndfMinCut) {
		this.ndfMinCut = ndfMinCut;
	}

	/**
	 * @return the ndfMaxCut
	 */
	public int getNdfMaxCut() {
		return ndfMaxCut;
	}

	/**
	 * @param ndfMaxCut
	 *            the ndfMaxCut to set
	 */
	public void setNdfMaxCut(int ndfMaxCut) {
		this.ndfMaxCut = ndfMaxCut;
	}

	/**
	 * @return the chi2MinCut
	 */
	public double getChi2MinCut() {
		return chi2MinCut;
	}

	/**
	 * @param chi2MinCut
	 *            the chi2MinCut to set
	 */
	public void setChi2MinCut(double chi2MinCut) {
		this.chi2MinCut = chi2MinCut;
	}

	/**
	 * @return the chi2MaxCut
	 */
	public double getChi2MaxCut() {
		return chi2MaxCut;
	}

	/**
	 * @param chi2MaxCut
	 *            the chi2MaxCut to set
	 */
	public void setChi2MaxCut(double chi2MaxCut) {
		this.chi2MaxCut = chi2MaxCut;
	}

	/**
	 * @return the chi2OverNdfMinCut
	 */
	public double getChi2OverNdfMinCut() {
		return chi2OverNdfMinCut;
	}

	/**
	 * @param chi2OverNdfMinCut
	 *            the chi2OverNdfMinCut to set
	 */
	public void setChi2OverNdfMinCut(double chi2OverNdfMinCut) {
		this.chi2OverNdfMinCut = chi2OverNdfMinCut;
	}

	/**
	 * @return the chi2OverNdfMaxCut
	 */
	public double getChi2OverNdfMaxCut() {
		return chi2OverNdfMaxCut;
	}

	/**
	 * @param chi2OverNdfMaxCut
	 *            the chi2OverNdfMaxCut to set
	 */
	public void setChi2OverNdfMaxCut(double chi2OverNdfMaxCut) {
		this.chi2OverNdfMaxCut = chi2OverNdfMaxCut;
	}
	
	public boolean isInCuts(CVTRecTrack cvtTrack) {
		if (	cvtTrack.getVertex().x() > vertexXMinCut && cvtTrack.getVertex().x() < vertexXMaxCut
				&& cvtTrack.getVertex().y() > vertexYMinCut && cvtTrack.getVertex().y() < vertexYMaxCut
				&& cvtTrack.getVertex().z() > vertexZMinCut && cvtTrack.getVertex().z() < vertexZMaxCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) > vertexTransverseMinCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) < vertexTransverseMaxCut
				&& cvtTrack.getMomentum().x() > momentumXMinCut && cvtTrack.getMomentum().x() < momentumXMaxCut
				&& cvtTrack.getMomentum().y() > momentumYMinCut && cvtTrack.getMomentum().y() < momentumYMaxCut
				&& cvtTrack.getMomentum().z() > momentumZMinCut && cvtTrack.getMomentum().z() < momentumZMaxCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) > momentumTransverseMinCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) < momentumTransverseMaxCut
				&& cvtTrack.getMomentum().mag() > momentumMinCut && cvtTrack.getMomentum().mag() < momentumMaxCut 
				&& cvtTrack.getMomentum().theta() > thetaMinCut && cvtTrack.getMomentum().theta() < thetaMaxCut
				&& cvtTrack.getMomentum().phi() > phiMinCut && cvtTrack.getMomentum().phi() < phiMaxCut
				&&cvtTrack.getNdf() > ndfMinCut && cvtTrack.getNdf() < ndfMaxCut 
				&& cvtTrack.getChi2() > chi2MinCut && cvtTrack.getChi2() < chi2MaxCut
				&& cvtTrack.getChi2() / (double) cvtTrack.getNdf() > chi2OverNdfMinCut && cvtTrack.getChi2() / (double) cvtTrack.getNdf() < chi2OverNdfMaxCut) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isInCutsDebug(CVTRecTrack cvtTrack) {
		if (cvtTrack.getVertex().x() > vertexXMinCut && cvtTrack.getVertex().x() < vertexXMaxCut
				&& cvtTrack.getVertex().y() > vertexYMinCut && cvtTrack.getVertex().y() < vertexYMaxCut
				&& cvtTrack.getVertex().z() > vertexZMinCut && cvtTrack.getVertex().z() < vertexZMaxCut){
			System.out.println("OK Vertex");
		}
		if (Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) > vertexTransverseMinCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) < vertexTransverseMaxCut){
			System.out.println("OK transverse vertex");
		}
		if (cvtTrack.getMomentum().x() > momentumXMinCut && cvtTrack.getMomentum().x() < momentumXMaxCut
				&& cvtTrack.getMomentum().y() > momentumYMinCut && cvtTrack.getMomentum().y() < momentumYMaxCut
				&& cvtTrack.getMomentum().z() > momentumZMinCut && cvtTrack.getMomentum().z() < momentumZMaxCut){
			System.out.println("OK momentum");
		}
		if (Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) > momentumTransverseMinCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) < momentumTransverseMaxCut
				&& cvtTrack.getMomentum().mag() > momentumMinCut && cvtTrack.getMomentum().mag() < momentumMaxCut ){
			System.out.println("OK momentum/transverse momentum");
		}
		if (cvtTrack.getMomentum().theta() > thetaMinCut && cvtTrack.getMomentum().theta() < thetaMaxCut
				&& cvtTrack.getMomentum().phi() > phiMinCut && cvtTrack.getMomentum().phi() < phiMaxCut){
			System.out.println("OK theta/phi");
		}
		if (cvtTrack.getNdf() > ndfMinCut && cvtTrack.getNdf() < ndfMaxCut){
			System.out.println("OK NDF");
		}
		if(cvtTrack.getChi2() > chi2MinCut && cvtTrack.getChi2() < chi2MaxCut){
			System.out.println("OK CHI2");
		}
		if(cvtTrack.getChi2() / (double) cvtTrack.getNdf() > chi2OverNdfMinCut
				&& cvtTrack.getChi2() / (double) cvtTrack.getNdf() < chi2OverNdfMaxCut){
			System.out.println("OK CHI2/NDF");
		}
		if (cvtTrack.getVertex().x() > vertexXMinCut && cvtTrack.getVertex().x() < vertexXMaxCut
				&& cvtTrack.getVertex().y() > vertexYMinCut && cvtTrack.getVertex().y() < vertexYMaxCut
				&& cvtTrack.getVertex().z() > vertexZMinCut && cvtTrack.getVertex().z() < vertexZMaxCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) > vertexTransverseMinCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) < vertexTransverseMaxCut
				&& cvtTrack.getMomentum().x() > momentumXMinCut && cvtTrack.getMomentum().x() < momentumXMaxCut
				&& cvtTrack.getMomentum().y() > momentumYMinCut && cvtTrack.getMomentum().y() < momentumYMaxCut
				&& cvtTrack.getMomentum().z() > momentumZMinCut && cvtTrack.getMomentum().z() < momentumZMaxCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) > momentumTransverseMinCut
				&& Math.sqrt(Math.pow(cvtTrack.getVertex().x(),2) + Math.pow(cvtTrack.getVertex().y(),2)) < momentumTransverseMaxCut
				&& cvtTrack.getMomentum().mag() > momentumMinCut && cvtTrack.getMomentum().mag() < momentumMaxCut 
				&& cvtTrack.getMomentum().theta() > thetaMinCut && cvtTrack.getMomentum().theta() < thetaMaxCut
				&& cvtTrack.getMomentum().phi() > phiMinCut && cvtTrack.getMomentum().phi() < phiMaxCut
				&&cvtTrack.getNdf() > ndfMinCut && cvtTrack.getNdf() < ndfMaxCut 
				&& cvtTrack.getChi2() > chi2MinCut && cvtTrack.getChi2() < chi2MaxCut
				&& cvtTrack.getChi2() / (double) cvtTrack.getNdf() > chi2OverNdfMinCut && cvtTrack.getChi2() / (double) cvtTrack.getNdf() < chi2OverNdfMaxCut) {
			System.out.println("ok all");
			return true;
		}else {
			return false;
		}
	}
	
	private CVTEvent CutCVT(CVTEvent oldCvtEvent){
		CVTEvent newCvtEvent = new CVTEvent();
		if (oldCvtEvent.getCvtTracks().size() > 0) {
			for (CVTRecTrack cvtTrack : oldCvtEvent.getCvtTracks()) {
				if ( isInCuts(cvtTrack)){
					newCvtEvent.addCVTTrack(cvtTrack);
				}
			}
		}
		return newCvtEvent;
	}
	
	private ParticleEvent CutParticles(ParticleEvent oldParticleEvent) {
		ParticleEvent newParticleEvent = new ParticleEvent();
		if (oldParticleEvent.hasNumberOfParticles() > 0) {
			newParticleEvent = new ParticleEvent();
			
			for (Particle particleIterator : oldParticleEvent.getParticles()) {
				if ( particleIterator.hasCentralTrack()>0){
					if (isInCuts(particleIterator.getCvtRecTrack())){
						newParticleEvent.addParticle(particleIterator);
					}
				}else{
					newParticleEvent.addParticle(particleIterator);
				}
			}
		}
		return newParticleEvent;
	}
	
	private Event Cut(Event event){
		Event newEvent = new Event(event);
		newEvent.getCentralEvent().setCvtEvent(this.CutCVT(event.getCentralEvent().getCvtEvent()));
		newEvent.setParticleEvent(this.CutParticles(event.getParticleEvent()));
		return newEvent;
	}
	
	public Event CutDefaultAnalysis(Event event){
		this.setChi2OverNdfMaxCut(5);
		return this.Cut(event);
	}

}