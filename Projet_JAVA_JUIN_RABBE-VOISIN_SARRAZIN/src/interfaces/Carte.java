package interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Carte extends JPanel{
	
	private Avion avion;
	
	public Carte(){
		Point p1 = new Point(20,40);
		Point p2 = new Point(140,100);
		Point p3 = new Point(200,220);
		/*Point p1 = new Point(20,10);
		Point p2 = new Point(100,100);
		Point p3 = new Point(100,150);
		Point p4 = new Point(300,150);
		Point p5 = new Point(400,130);
		Point p6 = new Point(100,270);
		Point p7 = new Point(70,100);
		Point p8 = new Point(20,100);
		Point p9 = new Point(20,50);*/
		avion = new Avion((int)p1.getX(), (int)p1.getY());
		avion.ajouterBalisesTraversees(p1);
		avion.ajouterBalisesTraversees(p2);
		avion.ajouterBalisesTraversees(p3);
		/*avion.ajouterBalisesTraversees(p4);
		avion.ajouterBalisesTraversees(p5);
		avion.ajouterBalisesTraversees(p6);
		avion.ajouterBalisesTraversees(p7);
		avion.ajouterBalisesTraversees(p8);
		avion.ajouterBalisesTraversees(p9);*/
		this.setBackground(Color.WHITE);
		
	}
	
	public void paintComponent(Graphics g){
		//System.out.println("Carte -> paintComponent");
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		//TEST		
		g2.drawImage(avion.getImageIcon().getImage(), (int) avion.getCoordonneeEcranActuelle().getX(), (int) avion.getCoordonneeEcranActuelle().getY(), this);
		AffineTransform at = g2.getTransform();
		at.rotate(Math.toRadians(90));
		
		for (int i = 0; i < avion.getBalisesTraversees().size(); i++) {
			if(i+1 < avion.getBalisesTraversees().size()){
				g2.setColor(Color.RED);
				g2.drawLine((int)avion.getBalisesTraversees().get(i).getX()+5, (int)avion.getBalisesTraversees().get(i).getY()+5, (int)avion.getBalisesTraversees().get(i+1).getX()+5, (int)avion.getBalisesTraversees().get(i+1).getY()+5);
				g2.setColor(Color.BLACK);
			}
			g2.fillOval((int)avion.getBalisesTraversees().get(i).getX(), (int)avion.getBalisesTraversees().get(i).getY(), 10, 10);
		}
		g2.drawImage(avion.getImageIcon().getImage(), (int)avion.getCoordonneeEcranActuelle().getX(), (int)avion.getCoordonneeEcranActuelle().getY(), this);
	}
	
	public Avion getAvion(){
		return avion;
	}
	
}
