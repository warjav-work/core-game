package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import engine.utils.Debug;

public class Input extends InputCode implements KeyListener {
	private ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
	private ArrayList<Integer> downKeys = new ArrayList<Integer>();

	public Input() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (downKeys.indexOf(key) == -1) {
			pressedKeys.add(key);
			downKeys.add(key);
		}
		Debug.Log("INPUT>> AGREGADA TECLA " + key);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (pressedKeys.indexOf(key) != -1) {
			pressedKeys.remove(pressedKeys.indexOf(key));

		}
		if (downKeys.indexOf(key) != -1) {
			downKeys.remove(downKeys.indexOf(key));

		}

		Debug.Log("INPUT>> ELIMINADA TECLA " + key);

	}

	// ACCESO A JUEGO
	public boolean isKeyPressed(int key) {
		boolean pressed = false;
		if (pressedKeys.indexOf(key) != -1) {
			pressedKeys.remove(pressedKeys.indexOf(key));
			pressed = true;
		}
		return pressed;
	}
	
	public boolean isKeyDown(int key) {
		boolean pressed = false;
		if (downKeys.indexOf(key) != -1) {
			pressed = true;
		}
		return pressed;
	}

	// METODOS NO USADOS
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
