package physics;

import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Photon;
import org.clas12.analysisTools.event.particles.Proton;
import org.jlab.clas.physics.Vector3;

public class ComputePhysicsParameters {
	
	public static double computeQ2(LorentzVector electronInitial, LorentzVector electronFinal){
		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
		double q2 = -virtualPhoton.mass2();
		return q2;
	}
	
	public static double computeQ2(LorentzVector electronInitial, Electron electron){
		return computeQ2(electronInitial, electron.getFourMomentum());
	}
	
	public static double computeXB(LorentzVector electronInitial, LorentzVector electronFinal){
		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
		double q2 = -virtualPhoton.mass2();
		double xB = q2 / (2 * Proton.mass * (electronInitial.e() - electronFinal.e()));
		return xB;
	}
	
	public static double computeXB(LorentzVector electronInitial, Electron electron){
		return computeXB(electronInitial, electron.getFourMomentum());
	}
	
	public static double computeW2(LorentzVector electronInitial, LorentzVector electronFinal){
		double q2 = computeQ2(electronInitial, electronFinal);
		double xB = computeXB(electronInitial, electronFinal);
		double W2 = q2 * (1 / xB - 1) + Proton.mass * Proton.mass;
		return W2;
	}
	
	public static double computeW2(LorentzVector electronInitial, Electron electron){
		return computeW2(electronInitial, electron.getFourMomentum());
	}

	public static double computePhiDeg(LorentzVector electronInitial, LorentzVector electronFinal, LorentzVector photonFinal){
		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
		double phi = 0;
		Vector3 v1 = virtualPhoton.vect().cross(electronFinal.vect());
		Vector3 v2 = virtualPhoton.vect().cross(photonFinal.vect());
		double ptot2 = v1.mag2() * v2.mag2();
		if (ptot2 < 0) {
			phi = 0;
		} else {
			double arg = v1.dot(v2) / Math.sqrt(ptot2);
			if (arg > 1)
				arg = 1;
			if (arg < -1)
				arg = -1;
			phi = Math.acos(arg);
		}
		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
			phi = 2 * Math.PI - phi;
		}
		double phiDeg = 180 / Math.PI * phi;
		return phiDeg;
	}
	
	public static double computePhiDeg(LorentzVector electronInitial, Electron electron, Photon photon){
		return computePhiDeg(electronInitial, electron.getFourMomentum(), photon.getFourMomentum());
	}
	
	public static double computeT(LorentzVector protonFinal){
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, Proton.mass);
		return protonI.substract(protonFinal).mass2();
	}
	
	public static double computeT(Proton proton){
		return computeT(proton.getFourMomentum());
	}
}


