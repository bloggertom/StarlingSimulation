package v2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private TextField agility;
	private TextField distance;
	private JSlider speedSlider;
	private boolean running = false;
	private final int MAXSPEED = 150;
	private final int MINSPEED = 50;
	private JCheckBox simulatePeir = new JCheckBox("Simulate Peir");
	
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
		
		JLabel agilityLabel = new JLabel("Agility:");
		agility = new TextField("25",5);
		userInterface.add(agilityLabel);
		userInterface.add(agility);
		
		JLabel distanceLabel = new JLabel("<html>Starling to Starling <br />Distance:</html>");
		userInterface.add(distanceLabel);
		distance = new TextField("10", 5);
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
		borderPanel.setBorder(BorderFactory.createEtchedBorder());
		simulatePeir.setSelected(true);
		simulatePeir.addChangeListener(changeListener);
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
			model.setAgility(Integer.parseInt(agility.getText()));
			model.setStarlingDistance(Integer.parseInt(distance.getText()));
			model.createStarlings(Integer.parseInt(numOfStarlings.getText()));
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
			window.repaint();
		}
		
		
	}
	
	public void stop(){
		running = false;
		model.removeAllStarlings();
		window.repaint();
	}

}
