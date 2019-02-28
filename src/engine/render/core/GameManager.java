package engine.render.core;

import java.awt.Color;
import java.util.ArrayList;

import engine.physics.objects.ObjectManager;
import engine.render.graphics.gui.GUIManager;

public class GameManager {
	private ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
	private GameLevel currentLevel;
	private static Window window;
	

	public GameManager() {
		// TODO Auto-generated constructor stub
	}

	// Accesos del juego
	public void addLevel(GameLevel level) {
		levels.add(level);
	}

	public void flushLevels(GameLevel level) {
		levels.clear();
	}

	public void enterLevel(int levelID, boolean doInit) {
		currentLevel = levels.get(levelID);
		if (doInit) {
			currentLevel.init(window, this);
		}
	}

	// Accesos de Ventana (Window)
	public Window createWindow(String TITLE, int WIDTH, int HEIGHT, int BUFFER_SIZE) {
		window = new Window(TITLE, WIDTH, HEIGHT, BUFFER_SIZE, this);
		
		return window;
	}

	protected void init() {
		if (isLevelOpen()) {
			currentLevel.init(window, this);
		}
	}

	protected void render() {
		if (isLevelOpen()) {
			window.clear(Color.BLACK);
			
			currentLevel.render(window, window.getDrawer(), this);
			ObjectManager.render(window, window.getDrawer());
			GUIManager.render(window, window.getDrawer());
			
			window.update();
			window.frames++;
		}
		
	}

	protected void update() {
		if (isLevelOpen()) {
			currentLevel.update(window, this);
			ObjectManager.update(window, this);
			GUIManager.update(window);
			window.ticks++;
		}
	}

	private boolean isLevelOpen() {
		if (currentLevel == null) {
			return false;
		}
		return true;
	}

	public static Window getWindow() {
		
		return window;
	}
	
	

}
