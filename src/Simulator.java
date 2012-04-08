/*
 * Copyright Thomas Wilson 2012
 * Simulator builds simulation and holds the loop to run it.
 */
public class Simulator implements SimulatorInterface{
	private MainViewController controller;
	private Model model;
	private static Simulator instance;
	
	
	private Simulator(){
		controller = new MainViewController();
		controller.setDelegate(this);
		controller.addStarlings();
		
		model = Model.getInstance();
	}
	/*
	 * Begins simulation
	 */
	public static Simulator getInstance(){
		if(instance == null){
			instance = new Simulator();
		}
		return instance;
	}
	
	public void runSimulation(){
		while(true){
			try{
				Thread.sleep(3);
			}catch(InterruptedException e){
				
			}
			model.updateStarlings();
			controller.refresh();
		}
	}
	
}
