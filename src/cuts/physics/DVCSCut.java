package cuts.physics;

import org.clas12.analysisTools.event.Event;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.ParticleEvent;

import cuts.central.CVTCut;
import cuts.particles.ElectronCut;
import cuts.particles.ParticleNumberCut;
import cuts.particles.PhotonCut;
import cuts.particles.ProtonCut;

public class DVCSCut {

	public Event Cut(Event oldEvent, LorentzVector electronI) {
		Event newEvent = new Event(oldEvent);
		ParticleEvent newParticleEvent = new ParticleEvent();
		
		Event cutEvent = null;
		
		ParticleNumberCut particleNumberCut = new ParticleNumberCut();
		cutEvent = particleNumberCut.cutDVCS(oldEvent);
		
		ElectronCut electronCutDVCS = new ElectronCut();
		cutEvent = electronCutDVCS.CutDVCS(cutEvent);
		
		PhotonCut photonCutDVCS = new PhotonCut();
		cutEvent = photonCutDVCS.CutDVCS(cutEvent);
		
		ProtonCut protonCutDVCS = new ProtonCut();
		cutEvent = protonCutDVCS.CutDVCS(cutEvent);
		
		CVTCut cvtCutDVCS = new CVTCut();
		cutEvent = cvtCutDVCS.CutDefaultAnalysis(cutEvent);
		
		if (cutEvent.getParticleEvent().hasNumberOfElectrons()==0){
			newEvent.setParticleEvent(newParticleEvent);
			return newEvent;
		}
		
		KinematicCut kinematicCutQ2 = new KinematicCut();
		cutEvent = kinematicCutQ2.CutQ2(cutEvent, electronI);
		
		KinematicCut kinematicCutW2 = new KinematicCut();
		cutEvent = kinematicCutW2.CutW2(cutEvent, electronI);
		
		newEvent = cutEvent;

		return newEvent;
	}
	
	
}
