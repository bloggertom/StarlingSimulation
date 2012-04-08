/*
 * Copyright Thomas Wilson 2012
 * 
 * This class holds information about the main window of the application
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Window extends JFrame{
	
	private MainView mainview;
	private JPanel options;
	private MainViewController controller;
	
	public Window(MainViewController c){
		controller = c;
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainview = new MainView();
		//c.setView(this);
		mainview.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainview.setBorder(BorderFactory.createLineBorder(Color.black));
		mainview.setSuperview(this);
		this.add(BorderLayout.CENTER, mainview);
		
	}
	
	public MainView getMainview(){
		return mainview;
	}
	
	
}
