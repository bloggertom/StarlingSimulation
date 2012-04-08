package v2;
/*
 * Copyright Thomas Wilson 2012
 * 
 * This class holds all the information about a starling
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Starling{
	
	private Vector possition;
	private Vector velocity;
	private double heading;
	private BufferedImage starlingBuffImage;
	private boolean perching = false;
	private int perchingTimer;
	
	
	public Starling(){
		File file = new File("images/twitter_bird.png");
		if(file.exists()){
			System.out.println("found file");
		}
		try {
			starlingBuffImage = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Couldn't create buffered image from file: "+file.getPath());
			e.printStackTrace();
		}
		
		possition = new Vector();
		velocity = new Vector();
	}
	
	public Vector getPossition(){
		return possition;
	}
	
	public Vector getVelocity(){
		return velocity;
	}
	public void setPossition(Vector possition){
		this.possition = possition;
	}
	public void setVelocity(Vector velocity){
		this.velocity = velocity;
	}
	
	public double getHeading(){
		return heading;
	}
	public BufferedImage getStarlingImage(){
		return starlingBuffImage;
	}
	
	public void setHeading(double h){
		this.heading = h;
	}
	
	public boolean perching(){
		return perching;
	}
	public void setPerching(boolean b){
		perching = b;
	}
	public int perchingTimer(){
		return perchingTimer;
	}
	public void setPerchingTimer(int p){
		perchingTimer = p;
	}
	
	
}
