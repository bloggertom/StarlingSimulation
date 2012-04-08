import javax.swing.JButton;
import javax.swing.JPanel;


public class OptionsPanel extends JPanel{
	private JButton go;
	private OptionsPanelController controller;
	
	public OptionsPanel(){
		controller = new OptionsPanelController(this);
		
	}
	
	public OptionsPanel(OptionsPanelController controller){
		this.controller = controller;
		controller.setOptionsPanel(this);
	}
	
	private void addSetUpSubViews(){
		go = new JButton("Go!");
		go.addActionListener(controller);
		
		this.add(go);
	}
}
