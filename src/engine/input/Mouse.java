package engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import engine.utils.Debug;

public class Mouse extends InputCode implements MouseListener, MouseMotionListener {
	private ArrayList<Integer> pressedButtons = new ArrayList<Integer>();
	private ArrayList<Integer> downButtons = new ArrayList<Integer>();

	private static int x, y;

	public Mouse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		if (downButtons.indexOf(button) == -1) {
			pressedButtons.add(button);
			downButtons.add(button);
		}
		Debug.Log("MOUSE>> AGREGADO BOTON " + button);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int button = e.getButton();
		if (pressedButtons.indexOf(button) != -1) {
			pressedButtons.remove(pressedButtons.indexOf(button));
		}

		if (downButtons.indexOf(button) != -1) {
			downButtons.remove(downButtons.indexOf(button));
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();

	}

	// ACCESO A JUEGO

	public boolean isButtonPressed(int button) {
		boolean pressed = false;
		if (pressedButtons.indexOf(button) != -1) {
			pressedButtons.remove(pressedButtons.indexOf(button));
			pressed = true;
		}
		return pressed;
	}

	public boolean isMouseUp(int button) {
		return downButtons.contains(button) ? false : true;
	}

	public boolean isButtonDown(int button) {
		boolean down = false;
		if (downButtons.indexOf(button) != -1) {
			downButtons.remove(downButtons.indexOf(button));
			down = true;
		}
		return down;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// METODOS NO USADOS
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
