/*
 * Copyright Thomas Wilson 2012
 * 
 * This class holds information about the main view in the Window class
 * 
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainView extends JPanel{
	private ArrayList<Starling> subViews;
	private JFrame superview;
	
	public MainView(){
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		for(Starling starling : subViews){
			g.drawImage(starling.getStarlingImage(), starling.getX(), starling.getY(), this);
		}
		
	}

	
	public void addStarlings(ArrayList<Starling> s){
		this.subViews = s;
	}
	public void setSuperview(JFrame frame){
		this.superview = frame;
	}
}
