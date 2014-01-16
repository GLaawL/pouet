package tests;

import java.util.Date;

import interfaces.InterfacePrincipale;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InterfacePrincipale ip = new InterfacePrincipale();
		double dodo = 0.0;
		double distance = 0.0;
		
		for (int i = 0; i < ip.getCarte().getAvion().getBalisesTraversees().size(); i++) {
			Date maDate = new Date();
			System.out.println(maDate.toString());
			if(i+1 < ip.getCarte().getAvion().getBalisesTraversees().size()){
				distance = ip.getCarte().getAvion().getDistanceEntre2Points(
						ip.getCarte().getAvion().getBalisesTraversees().get(i), 
						ip.getCarte().getAvion().getBalisesTraversees().get(i+1));
				System.out.println("DISTANCE : "+ distance);
			}
			/*vitesse = (long) (temps * distance);
			System.out.println("VITESSE : " + vitesse);*/
			ip.getCarte().getAvion().putAvionEnDeplacement(true);
			while (ip.getCarte().getAvion().getAvionEnDeplacement()) {
				if(i+1 < ip.getCarte().getAvion().getBalisesTraversees().size()){
					if(!ip.getCarte().getAvion().getAvionDeroute()){
						ip.getCarte().getAvion().deplacerAvion(ip.getCarte().getAvion().getBalisesTraversees().get(i),ip.getCarte().getAvion().getBalisesTraversees().get(i+1));
						ip.getCarte().repaint();
						try {
							/*dodo = distance/Math.abs((ip.getCarte().getAvion().getBalisesTraversees().get(i+1).getX() 
									- ip.getCarte().getAvion().getBalisesTraversees().get(i).getX()));
									*/
							
							if(ip.getCarte().getAvion().getTrajectoireVerticale() || ip.getCarte().getAvion().getTrajectoireHorizontale()){
								dodo = (long) distance/100;
							} else {
								dodo = Math.abs(distance * ip.getCarte().getAvion().getCoefficientDirecteur());
							}
							
							Thread.sleep((long)(0.3*dodo)); // A TESTER -> Timer t = new Timer(100,this);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} 
			} 
			System.out.println("COEFFDIR : " + ip.getCarte().getAvion().getCoefficientDirecteur());
			System.out.println("DODO : " + dodo);
		}	
	}
}
