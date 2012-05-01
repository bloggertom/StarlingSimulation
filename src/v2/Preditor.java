package v2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Preditor extends Boid{

	private BufferedImage preditorBuffImage;
	
	public Preditor(){
		super();
		File file = new File("images/pacman.png");
		if(file.exists()){
			System.out.println("found file");
		}
		try {
			preditorBuffImage = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Couldn't create buffered image from file: "+file.getPath());
			e.printStackTrace();
		}
	}
	public BufferedImage getPreditorImage(){
		return preditorBuffImage;
	}
}
