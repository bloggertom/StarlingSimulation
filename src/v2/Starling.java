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


public class Starling extends Boid{
	
	private boolean perching = false;
	private int perchingTimer;
	private BufferedImage starlingBuffImage;
	
	public Starling(){
		super();
		
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
	public BufferedImage getStarlingImage(){
		return starlingBuffImage;
	}
	
}
