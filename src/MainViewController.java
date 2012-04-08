/*
 * Copyright Thomas Wilson 2012
 * 
 * This class is used to control interaction between items on screen and the model
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;


public class MainViewController {
	//private ArrayList<Starling> starlings;
	private Model model = Model.getInstance();
	private OptionsPanel options;
	private Window view;
	private SimulatorInterface simulator;
	
	
	public MainViewController(){
		view = new Window(this);
		this.setUpViews();
	}
	public MainViewController(Window view){
		
		this.view = view;
		this.setUpViews();
		
	}

	public void addStarlings(){
		view.getMainview().addStarlings(model.getStarlings());
	}
	
	public void refresh(){
		view.repaint();
	}
	
	private void setUpViews(){
		view.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		options = new OptionsPanel();
		Dimension d = new Dimension();
		d.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.15));
		options.setPreferredSize(d);
		
		view.add(BorderLayout.SOUTH, options);
		view.setVisible(true);
	}
	
	public void setDelegate(Simulator sim){
		simulator = sim;
	}
}
