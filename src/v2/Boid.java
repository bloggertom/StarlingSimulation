package v2;

import java.awt.image.BufferedImage;

public class Boid {
	Vector possition;
	Vector velocity;
	double heading;
	
	
	public Boid(){
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
	
	public void setHeading(double h){
		this.heading = h;
	}
}
