import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OptionsPanelController implements ActionListener{
	private OptionsPanel view;
	private Model model;
	private SimulatorInterface delegate;
	
	public OptionsPanelController(){
		view = new OptionsPanel(this);
		model = Model.getInstance();
		delegate = Simulator.getInstance();
	}
	public OptionsPanelController(OptionsPanel op){
		model = Model.getInstance();
		view = op;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
	}
	
	public void setOptionsPanel(OptionsPanel op){
		view = op;
	}
	
}
