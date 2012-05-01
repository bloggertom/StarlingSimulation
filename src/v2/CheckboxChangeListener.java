package v2;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CheckboxChangeListener implements ChangeListener{
	private Model model = Model.getInstance();
	@Override
	public void stateChanged(ChangeEvent state) {
		System.out.println("StateChanged");
		if(state.getSource() instanceof JCheckBox){
			System.out.println("Is instance of JCheckBox");
			JCheckBox checker = (JCheckBox) state.getSource();
			
			if(checker.getText().equals("Simulate Peir")){
				System.out.println(checker.getText());
				model.setSimulatePear(checker.isSelected());
			}else if(checker.getText().equals("Simulate Preditors")){
				System.out.println(checker.getText());
				model.setSimulatePreditors(checker.isSelected());
				if(checker.isSelected()){
					model.createPreditors(1);
				}else{
					model.removeAllPreditors();
				}
			}
		}
	}

}
