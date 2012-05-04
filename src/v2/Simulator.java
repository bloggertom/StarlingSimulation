package v2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;


public class Simulator implements Runnable{
	private Model model;
	private JFrame window;
	private MainView mainview;
	private JPanel userInterface;
	private TextField numOfStarlings;
	//private TextField agility;
	private JSlider cohesion;
	private JSlider alignment;
	private JSlider distance;
	//private TextField distance;
	private JSlider speedSlider;
	private boolean running = false;
	private final int MAXSPEED = 150;
	private final int MINSPEED = 50;
	private JCheckBox simulatePeir = new JCheckBox("Simulate Peir");
	private JCheckBox simulatePreditors = new JCheckBox("Simulate Preditors");
	
	public Simulator(){
		model = Model.getInstance();
		window = new JFrame();
		mainview = new MainView();
		userInterface = new JPanel();
		this.setUpViews();
	}
	
	private void setUpViews(){
		window.setLayout(new BorderLayout());
		window.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MouseActionListener mouseListener = new MouseActionListener();
		mainview.addMouseListener(mouseListener);
		mainview.addMouseMotionListener(mouseListener);
		window.add(BorderLayout.CENTER, mainview);
		mainview.addStarlings(model.getStarlings());
		mainview.addPreditors(model.getPreditors());
		Dimension d = new Dimension();
		d.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.15));
		userInterface.setPreferredSize(d);
		
		JButton simulate = new JButton("Stimulate");
		UserInterfaceActionListener uiActionlistener = new UserInterfaceActionListener(this);
		simulate.addActionListener(uiActionlistener);
		userInterface.add(simulate);
		
		JLabel numOfStarlingsLabel = new JLabel("Starlings Num: ");
		userInterface.add(numOfStarlingsLabel);
		numOfStarlings = new TextField("200",5);
		userInterface.add(numOfStarlings);
		
		cohesion = new JSlider(JSlider.HORIZONTAL, 20, 200, 100);
		cohesion.setBorder(BorderFactory.createTitledBorder("Cohesion"));
		cohesion.setName("cohesion");
		SliderListener listener = new SliderListener();
		cohesion.addChangeListener(listener);
		userInterface.add(cohesion);
		
		alignment = new JSlider(JSlider.HORIZONTAL, 5, 50, 20);
		alignment.setBorder(BorderFactory.createTitledBorder("Alignment"));
		alignment.setName("alignment");
		alignment.addChangeListener(listener);
		userInterface.add(alignment);
		
		
		//JLabel distanceLabel = new JLabel("Separation");
		//userInterface.add(distanceLabel);
		//distance = new TextField("10", 5);
		distance = new JSlider(JSlider.HORIZONTAL, 0, 30, 7);
		distance.setBorder(BorderFactory.createTitledBorder("Separation"));
		distance.setName("distance");
		distance.addChangeListener(listener);
		userInterface.add(distance);
		
		speedSlider = new JSlider(JSlider.HORIZONTAL, MINSPEED, MAXSPEED, 100);
		speedSlider.setBorder(BorderFactory.createTitledBorder("Refresh Rate:"));
		
		Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();
		sliderLabels.put(new Integer(MINSPEED), new JLabel("Slow"));
		sliderLabels.put(new Integer(MAXSPEED), new JLabel("Fast"));
		speedSlider.setLabelTable(sliderLabels);
		speedSlider.setPaintLabels(true);
		userInterface.add(speedSlider);
		
		JPanel borderPanel = new JPanel();
		CheckboxChangeListener changeListener = new CheckboxChangeListener();
		borderPanel.setLayout(new GridLayout(0,1));
		borderPanel.setBorder(BorderFactory.createEtchedBorder());
		simulatePeir.setSelected(true);
		simulatePeir.addChangeListener(changeListener);
		simulatePreditors.setSelected(false);
		simulatePreditors.addChangeListener(changeListener);
		borderPanel.add(simulatePreditors);
		borderPanel.add(simulatePeir);
		
		userInterface.add(borderPanel);
		
		JButton resetButton = new JButton("Stop");
		resetButton.addActionListener(uiActionlistener);
		userInterface.add(resetButton);
		
		userInterface.setBorder(BorderFactory.createRaisedBevelBorder());
		window.add(BorderLayout.SOUTH, userInterface);
		//window.validate();
		
		window.pack();
		window.setVisible(true);
		System.out.println("Created user interface");
	}
	
	public void runSimulator(){
		if(!running){
			running = true;
			model.setSimulatePear(simulatePeir.isSelected());
			model.setPercievedVelocity(alignment.getValue());
			model.setStarlingDistance(distance.getValue());
			model.createStarlings(Integer.parseInt(numOfStarlings.getText()));
			model.setCohesion(cohesion.getValue());
			if(simulatePreditors.isSelected()){
				model.createPreditors(1);
			}
			Thread runner = new Thread(this);
			runner.start();
			
		}
	}
	@Override
	public void run() {
		System.out.println("Running Simulation");
		while(running){
			try{
				Thread.sleep(MAXSPEED + MINSPEED - speedSlider.getValue());
			}catch(InterruptedException e){
				System.out.println("Runner thread interrupted:\n"+e);
			}
			model.updateStarlings();
			if(simulatePreditors.isSelected()){
				model.updatePreditors();
			}
			window.repaint();
		}
		
		
	}
	
	public void stop(){
		running = false;
		model.removeAllStarlings();
		model.removeAllPreditors();
		window.repaint();
	}

}
