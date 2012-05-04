package v2;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener{

	private Model model = Model.getInstance();
	@Override
	public void stateChanged(ChangeEvent arg0) {
		JSlider slider = (JSlider)arg0.getSource();
		
		if(slider.getName().equals("alignment")){
			System.out.println("alignment "+slider.getValue());
			model.setPercievedVelocity(slider.getValue());
		}
		else if(slider.getName().equals("cohesion")){
			System.out.println("cohesion "+slider.getValue());
			model.setCohesion(slider.getValue());
		}
		else if(slider.getName().equals("distance")){
			System.out.println("distnace");
			model.setDistance(slider.getValue());
		}
	}

}
