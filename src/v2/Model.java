package v2;
/*
 * Copyright Thomas Wilson 2012
 * 
 * This class holds information about all the starlings.
 */

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;


public class Model {
	private ArrayList<Starling> starlings;
	private ArrayList<Preditor> preditors;
	private static Model model;
	private boolean simulatePeir;
	private boolean simulatePreditors;
	private boolean mouseOnScreen;
	private double speedCap = 60;
	private int pervcivedVolocityFractionToAdd = 20;
	private int distanceToKeep = 10;
	private int agility = 25;
	private int tendancyX = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75), tendancyY = (int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.25);
	
	private Model(){
		starlings = new ArrayList<Starling>();
		preditors = new ArrayList<Preditor>();
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
	public ArrayList<Preditor> getPreditors(){
		return preditors;
	}
	
	public void updateStarlings(){
		Simulation:
		for(Starling starling : starlings){
			
			if(starling.perching()){
				if(starling.perchingTimer() > 0){
					starling.setPerchingTimer(starling.perchingTimer()-1);
					continue Simulation;
				}else{
					starling.setPerching(false);
				}
			}
			
			Vector vector1 = this.flyTowardsCenterMass(starling);
			Vector vector2 = this.keepingDistance(starling);
			Vector vector3 = this.matchVelocity(starling);
			Vector vector4 = this.keepOnScreen(starling);
			
			
			
			
			Vector newVelocity = 
					Vector.addVectors(
					Vector.addVectors(
					Vector.addVectors(
					Vector.addVectors(
									starling.getVelocity(), 
									vector1),
									vector2), 
									vector3),
									vector4); 
									
			
			
			
			if(simulatePreditors){
				newVelocity = Vector.addVectors(newVelocity, this.reactToPreditors(starling));
				if(mouseOnScreen){
					Vector vector5 = this.setTendancy(starling);
					newVelocity = Vector.addVectors(newVelocity,vector5);
				}
			}else{
				Vector vector5 = this.setTendancy(starling);
				newVelocity = Vector.addVectors(newVelocity,vector5);
			}
			
			Vector newPossition = Vector.addVectors(starling.getPossition(), newVelocity);
			
			starling.setVelocity(newVelocity);
			this.speedCap(starling);
			starling.setPossition(newPossition);
			
		}
	
		
	}
	/*
	 * Update preditors location
	 */
	public void updatePreditors(){
		
		for(Preditor preditor: preditors){
				Vector vector1 = this.preditorHunting(preditor);
				Vector vector2 = this.keepOnScreen(preditor);
				Vector vector3 = this.matchVelocity(preditor);
				Vector newVelocity = Vector.addVectors(vector1, vector2);
				newVelocity = Vector.addVectors(newVelocity, vector3);
				
				preditor.setPossition(Vector.addVectors(preditor.getPossition(), newVelocity));
		}
		
	}
	/*
	 * Create starlings
	 */
	public void createStarlings(int numOfStarlings){
		Random getRand = new Random();
		for(int i = 0; i<numOfStarlings; i++){
			Starling s = new Starling();
			s.getPossition().setX(getRand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width));
			s.getPossition().setY(getRand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height));
			s.setHeading(1);
			starlings.add(s);
		}
	}
	/*
	 * Create preditors
	 */
	public void createPreditors(int numOfPreditors){
		for(int i = 0; i<numOfPreditors; i++){
			Preditor p = new Preditor();
			p.getPossition().setX(Toolkit.getDefaultToolkit().getScreenSize().width/2);
			p.getPossition().setY(Toolkit.getDefaultToolkit().getScreenSize().height/2);
			preditors.add(p);
		}
	}
	
	public Vector preditorHunting(Boid preditor){
		Vector center = new Vector();
		for(Starling s : starlings){
			if(Vector.subtractVectors(s.getPossition(), preditor.getPossition()).getMagnitude() < 300){
				center = Vector.addVectors(center, s.getPossition());
			}
		}
		
		center = Vector.divideVectorsByScaler(center, starlings.size());
		
		
		return Vector.divideVectorsByScaler((Vector.subtractVectors(center, preditor.getPossition())), 50);
		
	}
	
	/*
	 * Calculates the starlings perceived center of the flock. To do this we have to exclude that starling as if we
	 * are doing it from his point of view. Once calculated we move the staring 1percent toward that position;
	 */
	public Vector flyTowardsCenterMass(Boid starling){
		Vector center = new Vector();
		for(Starling s : starlings){
			if(s!=starling && !s.perching()){
				if(Vector.subtractVectors(s.getPossition(), starling.getPossition()).getMagnitude() < 175){
					center = Vector.addVectors(center, s.getPossition());
				}
			}
		}
		
		center = Vector.divideVectorsByScaler(center, starlings.size()-1);
		
		return Vector.divideVectorsByScaler((Vector.subtractVectors(center, starling.getPossition())), 100);
		
	}
	/*
	 * Define a distance to keep the starlings away from one another in this instance it is 20;
	 */
	public Vector keepingDistance(Boid starling){
		Vector vector = new Vector();
		
		for(Starling s : starlings){
			if(s != starling){
				if(Vector.subtractVectors(s.getPossition(), starling.getPossition()).getMagnitude() < distanceToKeep){
					vector = Vector.subtractVectors(vector, Vector.subtractVectors(s.getPossition(), starling.getPossition()));
				}
			}
		}
		
		return vector;
	}
	/*
	 * Add a proportion of the perceived velocity of the other starlings 30
	 */
	public Vector matchVelocity(Boid starling){
		Vector vector = new Vector();
		
		for(Starling s : starlings){
			if(s != starling){
				vector = Vector.addVectors(vector, s.getPossition());
			}
			
			
		}
		
		vector = Vector.divideVectorsByScaler(vector, starlings.size()-1);
		
		if(starling instanceof Preditor){
			return Vector.divideVectorsByScaler(Vector.subtractVectors(vector, starling.getPossition()), 25);
		}
		return Vector.divideVectorsByScaler(Vector.subtractVectors(vector, starling.getPossition()), pervcivedVolocityFractionToAdd);
	}
	
	/*
	 * ensure starlings stay on screen;
	 */
	public Vector keepOnScreen(Boid boid){
		Vector vector = new Vector();
		int minX = 0, maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		int minY = 0, maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		int force = 20;
		
		//check x position of starling
		if(boid.getPossition().getX()> maxX){
			vector.setX(-force);
		}else if(boid.getPossition().getX()< minX){
			vector.setX(force);
		}
		
		//check y position of starling
		if(boid.getPossition().getY() > maxY){
			vector.setY(-force);
		}else if(boid.getPossition().getY() < minY){
			vector.setY(force);
		}
		
		if(boid instanceof Starling){
			Starling starling = (Starling)boid;
			if(simulatePeir){
				if(starling.getPossition().getY() > 285 && starling.getPossition().getY() < 372 && starling.getPossition().getX()< 500){
					//starling.getPossition().setY(starling.getPossition().getY);
					Random rand = new Random();
					int time = rand.nextInt(30);
					starling.setPerchingTimer(time+5);
					starling.setPerching(true);
				}
			}
		}
		
		//check z position of starling
		//TODO
		
		return vector;
		
	}
	
	/*
	 * Starling reacts to preditor if they are within a given distance
	 */
	public Vector reactToPreditors(Starling starling){
		int distance = 100;
		Vector vector = new Vector();
		for(Preditor p: preditors){
			if(Vector.subtractVectors(p.getPossition(), starling.getPossition()).getMagnitude() < distance){
				vector = Vector.multiplyByScaler(Vector.divideVectorsByScaler(Vector.subtractVectors(vector, starling.getPossition()),agility), -10);
			}
		}
		return vector;
	}
	/*
	 * cap starlings top speed;
	 */
	public void speedCap(Starling starling){

		if(starling.getVelocity().getMagnitude() > speedCap){
			starling.setVelocity(Vector.multiplyByScaler(Vector.divideVectorsByScaler(starling.getVelocity(), starling.getVelocity().getMagnitude()),speedCap));
		}
		
	}
	
	public Vector setTendancy(Starling starling){
		Vector vector = new Vector();
			vector.setX(tendancyX);
			vector.setY(tendancyY);
		
		return Vector.divideVectorsByScaler(Vector.subtractVectors(vector, starling.getPossition()),agility);
	}
	
	public void setMouseX(int x){
		tendancyX = x;
	}
	public void setMouseY(int y){
		tendancyY = y;
	}
	
	public void removeAllStarlings(){
		starlings.clear();
	}
	
	public void setSimulatePear(boolean b){
		simulatePeir = b;
	}
	
	public void setStarlingDistance(int distance){
		distanceToKeep = distance;
	}
	
	public void setAgility(int agility){
		this.agility = agility;
	}
	public void setSpeedCap(int c){
		this.speedCap = c;
	}
	public void setSimulatePreditors(boolean b){
		this.simulatePreditors = b;
	}
	public void removeAllPreditors(){
		preditors.clear();
	}
	public void setMouseOnScreen(boolean b){
		this.mouseOnScreen = b;
	}
}	
