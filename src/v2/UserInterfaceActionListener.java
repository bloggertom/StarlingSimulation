package v2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterfaceActionListener implements ActionListener{
	private Simulator simulator;

	
	public UserInterfaceActionListener(Simulator simulator){
		this.simulator = simulator;
	}
	@Override
	public void actionPerformed(ActionEvent action) {
		String actionCommand = action.getActionCommand();
		if(actionCommand.equals("Stimulate")){
			System.out.println("Starting simulation");
			simulator.runSimulator();
			
		}else if(actionCommand.equals("Stop")){
			System.out.println("Reseting simulation");
			simulator.stop();
		}
	}

}
