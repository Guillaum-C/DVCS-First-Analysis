package physics;

import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.particles.LorentzVector;
import org.clas12.analysisTools.event.particles.Particle;
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

	public static double computeT(LorentzVector protonFinal){
		LorentzVector protonI = new LorentzVector();
		protonI.setPxPyPzM(0, 0, 0, Proton.mass);
		if (protonI.substract(protonFinal).mass2()>0){
		System.out.println(protonI);
		System.out.println(protonFinal);
		System.out.println(protonI.substract(protonFinal));
		System.out.println(protonI.substract(protonFinal).mass2());
		System.out.println("");
		}
		return protonI.substract(protonFinal).mass2();
	}
	
	public static double computeT(Proton proton){
		return computeT(proton.getFourMomentum());
	}


	public static double computePhiDeg(LorentzVector electronInitial, LorentzVector electronFinal, LorentzVector photonFinal){
		
		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
		double phi = 0;
		Vector3 v1 = virtualPhoton.vect().cross(electronFinal.vect());
		Vector3 v2 = virtualPhoton.vect().cross(photonFinal.vect());
		phi = angleBetween2Vectors(v1, v2);
		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
			phi = 2 * Math.PI - phi;
		}
		double phiDeg = 180 / Math.PI * phi;
		return phiDeg;
		
//		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
//		double phi = 0;
//		Vector3 v1 = virtualPhoton.vect().cross(electronFinal.vect());
//		Vector3 v2 = virtualPhoton.vect().cross(photonFinal.vect());
//		double ptot2 = v1.mag2() * v2.mag2();
//		if (ptot2 < 0) {
//			phi = 0;
//		} else {
//			double arg = v1.dot(v2) / Math.sqrt(ptot2);
//			if (arg > 1)
//				arg = 1;
//			if (arg < -1)
//				arg = -1;
//			phi = Math.acos(arg);
//		}
//		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
//			phi = 2 * Math.PI - phi;
//		}
//		double phiDeg = 180 / Math.PI * phi;
//		return phiDeg;
	}
	
	public static double computePhiDeg(LorentzVector electronInitial, Electron electron, Photon photon){
		return computePhiDeg(electronInitial, electron.getFourMomentum(), photon.getFourMomentum());
	}
	
	public static double computePhiDegWithProton(LorentzVector electronInitial, LorentzVector electronFinal, LorentzVector protonFinal) {

		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
		double phi = 0;
		Vector3 v1 = virtualPhoton.vect().cross(electronFinal.vect());
		Vector3 v2 = virtualPhoton.vect().cross(protonFinal.vect());
		phi = angleBetween2Vectors(v1, v2);
		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
			phi = 2 * Math.PI - phi;
		}
		double phiDeg = 180 / Math.PI * phi;
		return phiDeg;
		
//		LorentzVector virtualPhoton = electronInitial.substract(electronFinal);
//		double phi = 0;
//		Vector3 v1 = virtualPhoton.vect().cross(electronFinal.vect());
//		Vector3 v2 = virtualPhoton.vect().cross(protonFinal.vect());
//		double ptot2 = v1.mag2() * v2.mag2();
//		if (ptot2 < 0) {
//			phi = 0;
//		} else {
//			double arg = v1.dot(v2) / Math.sqrt(ptot2);
//			if (arg > 1)
//				arg = 1;
//			if (arg < -1)
//				arg = -1;
//			phi = Math.acos(arg);
//		}
//		if (virtualPhoton.vect().dot(v1.cross(v2)) < 0) {
//			phi = 2 * Math.PI - phi;
//		}
//		double phiDeg = 180 / Math.PI * phi;
//		if (phiDeg>180) {phiDeg=phiDeg-180;}else{phiDeg=phiDeg+180;}
//		return phiDeg;
	}

	public static double computePhiDegWithProton(LorentzVector electronInitial, Electron electron, Proton proton){
		return computePhiDegWithProton(electronInitial, electron.getFourMomentum(), proton.getFourMomentum());
	}
	
	public static double computePi0InvariantMass(LorentzVector photon1, LorentzVector photon2){
		LorentzVector sum = photon1.sum(photon2);
		double invMass = sum.m();
		return invMass;
	}
	
	public static double computePi0InvariantMass(Photon photon1, Photon photon2){
		return computePi0InvariantMass(photon1.getFourMomentum(), photon2.getFourMomentum());
	}
	
	public static double angleDegBetween2Vectors(Vector3 vector1, Vector3 vector2){
		double angleDeg = 180/Math.PI*ComputePhysicsParameters.angleBetween2Vectors(vector1, vector2);
		return angleDeg;
	}
	
	public static double angleBetween2Vectors(Vector3 vector1, Vector3 vector2){
		double angle = Math.acos(vector1.dot(vector2)
				/ (vector1.mag() * vector2.mag()));
		return angle;
	}

	public static double angleBetween2Particles(Particle particle1, Particle particle2){
		return angleDegBetween2Vectors(particle1.getMomentum(), particle2.getMomentum());
	}
}


