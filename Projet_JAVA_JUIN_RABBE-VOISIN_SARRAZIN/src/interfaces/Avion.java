package interfaces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

public class Avion {

	private String OACI;
	private Point coordonneeEcranDepart, coordonneeEcranArrivee, coordonneeEcranActuelle; // sera remplacé par la classe FeuilleDeVol
	private ImageIcon imageIcon;
	private int cap = 20;
	private ArrayList<Point> balisesTraversees, coordonneesDeLAvionEntreDeuxBalises; //sera remplacé par la classe Balise
	private boolean avionEnDeplacement = false, 
					avionDeroute = false, 
					trajectoireVerticale = false, 
					trajectoireHorizontale = false;;
	private double coefficientDirecteur = 0;
	
	public Avion(int x, int y){
		this.coordonneeEcranActuelle = new Point(x, y); 
		this.imageIcon = new ImageIcon("avion9.gif"); 
		balisesTraversees = new ArrayList<Point>();
		coordonneesDeLAvionEntreDeuxBalises = new ArrayList<Point>();
	}
	
	public void deplacerAvion(Point a, Point b){
		/*System.out.println("Cap : " + Math.toRadians(cap));
		System.out.println("COS : " + Math.cos(Math.toRadians(cap)));
		System.out.println("SIN : " + Math.sin(Math.toRadians(cap)));
		double x = coordonneeEcranActuelle.getX() + Math.cos(Math.toRadians(cap));
        double y = coordonneeEcranActuelle.getY() + Math.sin(Math.toRadians(cap));
        coordonneeEcranActuelle.setLocation(x, y);*/
		
		if(coordonneeEcranActuelle.getX() == a.getX() && coordonneeEcranActuelle.getY() == a.getY()){
			calculerCoefficientDirecteur(a, b);
		}
		
		
		//double x,y;
		int sensX = 1, sensY = 1;
		
		if(a.getX() > b.getX()){
			sensX = -1;
		}
		
		if(a.getY() > b.getY()){
			sensY = -1;
		}
		
		
		if (coordonneeEcranActuelle.getX() != b.getX() || coordonneeEcranActuelle.getY() != b.getY()) {
			if(trajectoireVerticale == false && trajectoireHorizontale == false){
				/*System.out.println("coordonneeEcranActuelle : " + coordonneeEcranActuelle.getX() + " / " + coordonneeEcranActuelle.getY());
				System.out.println("b : " + b.getX() + " / " + b.getY());
				System.out.println("Coeff Dir : " + coefficientDirecteur);
				System.out.println("sensX : " + sensX + " / sensY : " + sensY);*/
				/*x = coordonneeEcranActuelle.getX() + 1;
				y = (((coordonneeEcranActuelle.getX()+1)*coefficientDirecteur)-(coefficientDirecteur*b.getX())+b.getY());
				coordonneeEcranActuelle.setLocation(x,y);*/
				coordonneeEcranActuelle.setLocation(coordonneeEcranActuelle.getX() + sensX,
						(((coordonneeEcranActuelle.getX()+sensX)*coefficientDirecteur)-(coefficientDirecteur*b.getX())+b.getY()));
			} else if(trajectoireVerticale == true && trajectoireHorizontale == false){
				// Cas particulier où où trajectoire parallèle à l'axe des ordonnées
				coordonneeEcranActuelle.setLocation(coordonneeEcranActuelle.getX() , coordonneeEcranActuelle.getY() + sensY);
			} else if(trajectoireVerticale == false && trajectoireHorizontale == true){
				// Cas particulier où trajectoire parallèle à l'axe des abscisses
				coordonneeEcranActuelle.setLocation(coordonneeEcranActuelle.getX() + sensX , coordonneeEcranActuelle.getY());
			}
		} else {
			avionEnDeplacement = false;
		}

	}
	
	public void calculerCoefficientDirecteur(Point a, Point b){
		int deltaX = (int) (b.getX() - a.getX()); 
		int deltaY = (int) (b.getY() - a.getY());
		
		if(deltaX == 0){ // Cas particulier où trajectoire parallèle à l'axe des ordonnées
			trajectoireVerticale = true;
		} else {
			trajectoireVerticale = false;
			coefficientDirecteur = (double) deltaY / deltaX;
		}
		
		if(deltaY == 0){ // Cas particulier où trajectoire parallèle à l'axe des abscisses
			trajectoireHorizontale = true;
		} else {
			trajectoireHorizontale = false;
		}
	}
	
	public void derouterAvion(int cap){
		avionDeroute = true;
		deplacerAvion(coordonneeEcranActuelle, new Point(200,100));
	}
	
	//public void rerouterAvion(){
		//avionDeroute = false;
		//deplacerAvion(coordonneeEcranActuelle, b)
	//}
	
	//Peut être judicieux de le placer dans une classe utilitaire, à Voir !
	public double getDistanceEntre2Points(Point a, Point b){
		return Math.sqrt(Math.pow(b.getX()-a.getX(), 2)+Math.pow(b.getY()-a.getY(), 2));
	}
	
	public Point getCoordonneeEcranActuelle(){
		return coordonneeEcranActuelle;
	}
	
	public double getCoefficientDirecteur(){
		return coefficientDirecteur;
	}
	
	public ImageIcon getImageIcon(){
		return imageIcon;
	}
	
	public void ajouterBalisesTraversees(Point point){
		balisesTraversees.add(point);
	}
	
	public ArrayList<Point> getBalisesTraversees(){
		return balisesTraversees;
	}
	
	public ArrayList<Point> getCoordonneesDeLAvionEntreDeuxBalises(){
		return coordonneesDeLAvionEntreDeuxBalises;
	}
	
	public void putAvionEnDeplacement(boolean etat){
		avionEnDeplacement = etat;
	}
	
	public boolean getAvionEnDeplacement(){
		return avionEnDeplacement;
	}
	
	public boolean getAvionDeroute(){
		return avionDeroute;
	}
	
	public boolean getTrajectoireHorizontale(){
		return trajectoireHorizontale;
	}
	
	public boolean getTrajectoireVerticale(){
		return trajectoireVerticale;
	}
	
}
