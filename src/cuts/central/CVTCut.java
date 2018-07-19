package cuts.central;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.central.cvt.CVTRecTrack;

public class CVTCut {

	int ndfMinCut = Integer.MIN_VALUE;
	int ndfMaxCut = Integer.MIN_VALUE;
	
	/**
	 * Cut using parameters
	 * @param oldEvent event on which we apply cuts
	 * @return event after cuts
	 */
	public Event Cut(Event oldEvent) {
		Event newEvent=null;
		
		if (!oldEvent.getCentralEvent().getCvtEvent().getCvtTracks().isEmpty()) {
			newEvent = new Event();
			
			for (CVTRecTrack trackIterator : oldEvent.getCentralEvent().getCvtEvent().getCvtTracks()){
				if (trackIterator.getNdf() > ndfMinCut && trackIterator.getNdf() < ndfMaxCut){
					newEvent.getCentralEvent().getCvtEvent().getCvtTracks().remove(trackIterator);
					newEvent.getParticleEvent().getParticles().remove(trackIterator.getParticle());
				}
			}	
		}
		return newEvent;
	}
}