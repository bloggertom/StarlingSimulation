package v2;

/*
 * The main view which displayes the starlings current possition.
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainView extends JPanel{
	private ArrayList<Starling> starlings; //starlings
	private ArrayList<Preditor> preditors; //preditors
	//private boolean simulatePreditors = false;
	private BufferedImage background;
	
	public MainView(){
		//load background image
		File file = new File("images/backgroundImg.JPG");
		
		try {
			background = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Couldn't create buffered image from file: "+file.getPath());
			e.printStackTrace();
		}
	}
	
	/*
	 * Starlings to be shown
	 */
	public void addStarlings(ArrayList<Starling> starlings){
		this.starlings = starlings;
	}
	/*
	 * preditors to be shown
	 */
	public void addPreditors(ArrayList<Preditor> preditors){
		this.preditors = preditors;
	}
	
	/*
	 * Pains graphics on the main view including starlings and preditors
	 * from the two array lists 
	 */
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		
		for(Starling starling : starlings){
			g.drawImage(starling.getStarlingImage(), (int)starling.getPossition().getX(), (int)starling.getPossition().getY(), this);
		}

		for(Preditor preditor : preditors){
			g.drawImage(preditor.getPreditorImage(), (int)preditor.getPossition().getX(), (int)preditor.getPossition().getY(), this);
		}
		
	}
}
