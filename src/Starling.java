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
	
	private int x;
	private int y;
	private int z;
	private int vx;
	private int vy;
	private int vz;
	private double heading;
	private BufferedImage starlingBuffImage;
	private int size;
	
	
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
	}
	
	
	
	
	// Getting information about this starling
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getz(){
		return z;
	}
	public double getHeading(){
		return heading;
	}
	public BufferedImage getStarlingImage(){
		return starlingBuffImage;
	}
	public int getVelocityX(){
		return vx;
	}
	public int getVelocityY(){
		return vy;
	}
	public int getVelocityZ(){
		return vz;
	}
	
	//Setting information about this starling
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setZ(int z){
		this.z = z;
	}
	public void setHeading(double h){
		this.heading = h;
	}
	public void setVelocityX(int vx){
		this.vx = vx;
	}
	public void setVelocityY(int vy){
		this.vy = vy;
	}
	public void setVelocityZ(int vz){
		this.vz = vz;
	}
	
}
