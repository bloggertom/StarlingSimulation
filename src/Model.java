/*
 * Copyright Thomas Wilson 2012
 * 
 * This class holds information about all the starlings.
 */

import java.awt.Toolkit;
import java.util.ArrayList;


public class Model {
	private ArrayList<Starling> starlings;
	private static Model model;
	
	private Model(){
		
		starlings = new ArrayList<Starling>();
		Starling s = new Starling();
		s.setX((int)Toolkit.getDefaultToolkit().getScreenSize().width/2);
		s.setY((int)Toolkit.getDefaultToolkit().getScreenSize().height/2);
		s.setHeading(1);
		starlings.add(s);
	}
	
	public static Model getInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}
	public ArrayList<Starling> getStarlings(){
		return starlings;
	}
	
	public void updateStarlings(){
		for(Starling s : starlings){
			if(s.getHeading()>0){
				s.setX(s.getX()+1);
			}else{
				s.setX(s.getX()-1);
			}
			
		}
	}
	
}
