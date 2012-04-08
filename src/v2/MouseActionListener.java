package v2;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseActionListener implements MouseListener, MouseMotionListener{
	private Model model = Model.getInstance();
	private boolean entered = false;
	public MouseActionListener(){
		
	}

	@Override
	public void mouseClicked(MouseEvent click) {
		
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		entered = true;
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
		entered = false;
		model.setMouseX((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75));
		model.setMouseY((int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.25));
		System.out.println("Mouse Exit");
		
	}

	@Override
	public void mousePressed(MouseEvent click) {
		System.out.println("x="+click.getX()+", y="+click.getY());
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent mouse) {
		if(entered){
			model.setMouseX(mouse.getXOnScreen());
			model.setMouseY(mouse.getYOnScreen());
		}
		
	}
	
}
