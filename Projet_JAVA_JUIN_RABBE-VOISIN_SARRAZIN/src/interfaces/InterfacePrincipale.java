package interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class InterfacePrincipale extends JFrame {

	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem menuItemQuitter;
	private Carte carte;
	private JButton derouter, rerouter;
	
	public InterfacePrincipale(){
		this.setTitle("PROJET JAVA By Juin & Rabbe-Voisin & Sarrazin");
		carte = new Carte();
		this.setContentPane(carte);
		
		
		// Gestion de la barre des menus
		menu = new JMenu("Fichiers");
		menuItemQuitter = new JMenuItem("Quitter");
		menu.add(menuItemQuitter);
		menuBar = new JMenuBar();
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		//Gestion des boutons
		derouter = new JButton("DŽrouter");
		derouter.addActionListener(new ActionDerouter());
		rerouter = new JButton("Rerouter");
		rerouter.addActionListener(new ActionRerouter());
		this.getContentPane().add(derouter, BorderLayout.NORTH);
		this.getContentPane().add(rerouter, BorderLayout.NORTH);
		
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public Carte getCarte(){
		return carte;
	}
	
	public class ActionDerouter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			while(InterfacePrincipale.this.carte.getAvion().getAvionEnDeplacement()){
				InterfacePrincipale.this.carte.getAvion().derouterAvion(10);
	
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public class ActionRerouter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//InterfacePrincipale.this.carte.getAvion().rerouterAvion();
		}
		
	}
}
