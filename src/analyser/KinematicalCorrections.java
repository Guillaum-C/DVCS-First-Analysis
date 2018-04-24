package analyser;

import org.clas12.analysisTools.event.particles.Electron;
import org.clas12.analysisTools.event.Event;

public class KinematicalCorrections {

	public static void correct2GeV100OutbendingTorus(Event oldEvent){
		
		// for run 2383 (100% outbending):
		
		double AA[] = {6.83045, 4.1226, 1.84998, 1.11049, 2.22096, 9.54057};
		double AB[] = {-0.771015, -0.524748, -0.182965, -0.0209766, -0.197437, -1.93703};
		double AC[] = {0.0221712, 0.0307074, 0.0155828, 0.0019323, 0.0115737, 0.167383};
		double AD[] = {0.000689754, -0.00064143, -0.000598563, -8.61622e-05, -0.00027472, -0.00637268};
		double AE[] = {-2.75416e-05, 2.57831e-06, 8.56679e-06, 1.40151e-06, 2.19829e-06, 8.99762e-05};
		double BA[] = {0.0723159, 0.0624633, 0.0420249, -0.0145283, -0.0306753, -0.12591};
		double BB[] = {-0.00968628, -0.0107396, -0.00941917, 0.00325576, 0.00493625, 0.0286776};
		double BC[] = {0.000289564, 0.000643635, 0.000810481, -0.000274498, -0.000276207, -0.00248044};
		double BD[] = {7.8976e-06, -1.39562e-05, -3.08215e-05, 1.01971e-05, 5.79629e-06, 9.4443e-05};
		double BE[] = {-3.30327e-07, 6.62678e-08, 4.35367e-07, -1.41476e-07, -3.28153e-08, -1.33288e-06};
		double CA[] = {0.000222868, 0.000307922, 0.000498406, 0.000440922, 0.000190687, 0.000461586};
		double CB[] = {-2.99233e-05, -5.34312e-05, -0.000112885, -9.8389e-05, -2.95135e-05, -0.000105235};
		double CC[] = {9.07008e-07, 3.23835e-06, 9.79746e-06, 8.32572e-06, 1.54429e-06, 9.11323e-06};
		double CD[] = {2.32709e-08, -7.19333e-08, -3.75518e-07, -3.10079e-07, -2.73518e-08, -3.47474e-07};
		double CE[] = {-9.93027e-10, 3.77415e-10, 5.33749e-09, 4.30429e-09, 5.9649e-11, 4.90934e-09};

		double phi_reg_min[] = {-178, - 117, -57, 3, 63, 123};   // narrow fiducial fit cut!
		double phi_reg_max[] = {-145, -88, -28, 32, 92, 154};    // narrow fiducial fit cut!

		//double phi_reg_min[] = {-180, - 124, -60, -2, 58, 119};    // wide fiducial cut on phi
		//double phi_reg_max[] = {-143,  -82,  -22, 38, 96, 156};    // wide fiducial cut on phi


		double theta_min = 6;
		double theta_max = 28;

		for (Electron electron : oldEvent.getParticleEvent().getElectrons()){
			double theta = electron.getThetaDeg();
			double phi = electron.getPhiDeg();
			double mom = electron.getMomentum().mag();

			double pcorr = 0;
			double A = 0;
			double B = 0;
			double C = 0;

			if(theta > theta_min && theta < theta_max){

				if( phi > phi_reg_min[0] && phi < phi_reg_max[0]){
					A = AA[0]*Math.pow(theta,0) + AB[0]*Math.pow(theta,1) + AC[0]*Math.pow(theta,2) + AD[0]*Math.pow(theta,3) + AE[0]*Math.pow(theta,4);
					B = BA[0]*Math.pow(theta,0) + BB[0]*Math.pow(theta,1) + BC[0]*Math.pow(theta,2) + BD[0]*Math.pow(theta,3) + BE[0]*Math.pow(theta,4);
					C = CA[0]*Math.pow(theta,0) + CB[0]*Math.pow(theta,1) + CC[0]*Math.pow(theta,2) + CD[0]*Math.pow(theta,3) + CE[0]*Math.pow(theta,4);
					pcorr = A * Math.pow(phi,0) + B * Math.pow(phi,1) + C * Math.pow(phi,2);
				}

				if( phi > phi_reg_min[1] && phi < phi_reg_max[1]){
					A = AA[1]*Math.pow(theta,0) + AB[1]*Math.pow(theta,1) + AC[1]*Math.pow(theta,2) + AD[1]*Math.pow(theta,3) + AE[1]*Math.pow(theta,4);
					B = BA[1]*Math.pow(theta,0) + BB[1]*Math.pow(theta,1) + BC[1]*Math.pow(theta,2) + BD[1]*Math.pow(theta,3) + BE[1]*Math.pow(theta,4);
					C = CA[1]*Math.pow(theta,0) + CB[1]*Math.pow(theta,1) + CC[1]*Math.pow(theta,2) + CD[1]*Math.pow(theta,3) + CE[1]*Math.pow(theta,4);
					pcorr = A * Math.pow(phi,0) + B * Math.pow(phi,1) + C * Math.pow(phi,2);
				}

				if( phi > phi_reg_min[2] && phi < phi_reg_max[2]){
					A = AA[2]*Math.pow(theta,0) + AB[2]*Math.pow(theta,1) + AC[2]*Math.pow(theta,2) + AD[2]*Math.pow(theta,3) + AE[2]*Math.pow(theta,4);
					B = BA[2]*Math.pow(theta,0) + BB[2]*Math.pow(theta,1) + BC[2]*Math.pow(theta,2) + BD[2]*Math.pow(theta,3) + BE[2]*Math.pow(theta,4);
					C = CA[2]*Math.pow(theta,0) + CB[2]*Math.pow(theta,1) + CC[2]*Math.pow(theta,2) + CD[2]*Math.pow(theta,3) + CE[2]*Math.pow(theta,4);
					pcorr = A * Math.pow(phi,0) + B * Math.pow(phi,1) + C * Math.pow(phi,2);
				}

				if( phi > phi_reg_min[3] && phi < phi_reg_max[3]){
					A = AA[3]*Math.pow(theta,0) + AB[3]*Math.pow(theta,1) + AC[3]*Math.pow(theta,2) + AD[3]*Math.pow(theta,3) + AE[3]*Math.pow(theta,4);
					B = BA[3]*Math.pow(theta,0) + BB[3]*Math.pow(theta,1) + BC[3]*Math.pow(theta,2) + BD[3]*Math.pow(theta,3) + BE[3]*Math.pow(theta,4);
					C = CA[3]*Math.pow(theta,0) + CB[3]*Math.pow(theta,1) + CC[3]*Math.pow(theta,2) + CD[3]*Math.pow(theta,3) + CE[3]*Math.pow(theta,4);
					pcorr = A * Math.pow(phi,0) + B * Math.pow(phi,1) + C * Math.pow(phi,2);
				}

				if( phi > phi_reg_min[4] && phi < phi_reg_max[4]){
					A = AA[4]*Math.pow(theta,0) + AB[4]*Math.pow(theta,1) + AC[4]*Math.pow(theta,2) + AD[4]*Math.pow(theta,3) + AE[4]*Math.pow(theta,4);
					B = BA[4]*Math.pow(theta,0) + BB[4]*Math.pow(theta,1) + BC[4]*Math.pow(theta,2) + BD[4]*Math.pow(theta,3) + BE[4]*Math.pow(theta,4);
					C = CA[4]*Math.pow(theta,0) + CB[4]*Math.pow(theta,1) + CC[4]*Math.pow(theta,2) + CD[4]*Math.pow(theta,3) + CE[4]*Math.pow(theta,4);
					pcorr = A * Math.pow(phi,0) + B * Math.pow(phi,1) + C * Math.pow(phi,2);
				}

				if( phi > phi_reg_min[5] && phi < phi_reg_max[5]){
					A = AA[5]*Math.pow(theta,0) + AB[5]*Math.pow(theta,1) + AC[5]*Math.pow(theta,2) + AD[5]*Math.pow(theta,3) + AE[5]*Math.pow(theta,4);
					B = BA[5]*Math.pow(theta,0) + BB[5]*Math.pow(theta,1) + BC[5]*Math.pow(theta,2) + BD[5]*Math.pow(theta,3) + BE[5]*Math.pow(theta,4);
					C = CA[5]*Math.pow(theta,0) + CB[5]*Math.pow(theta,1) + CC[5]*Math.pow(theta,2) + CD[5]*Math.pow(theta,3) + CE[5]*Math.pow(theta,4);
					pcorr = A * Math.pow(phi,0) + B * Math.pow(phi,1) + C * Math.pow(phi,2);
				}
			}

			double mom_corrected =  pcorr * mom;

			double px_corrected = mom_corrected * Math.sin(theta*Math.PI/180) * Math.cos(phi*Math.PI/180);
			double py_corrected = mom_corrected * Math.sin(theta*Math.PI/180) * Math.sin(phi*Math.PI/180);
			double pz_corrected = mom_corrected * Math.cos(theta*Math.PI/180);

			electron.getFourMomentum().setPxPyPzE(px_corrected, py_corrected, pz_corrected, Math.sqrt(mom_corrected*mom_corrected + Electron.mass*Electron.mass));
		} 
	}
	
}
