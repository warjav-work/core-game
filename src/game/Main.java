package game;

import engine.render.core.GameManager;
import engine.render.core.Window;
import game.levels.Game;

public class Main extends GameManager {

	private static Window window;
	private static final int WIDTH = 700, HEIGHT = 500, BUFFER_SIZE = 2;
	private static final String TITLE = "Game Engine Test";

	private static final int GAME_ID = 0;

	public Main() {
		window = createWindow(TITLE, WIDTH, HEIGHT, BUFFER_SIZE);
		//window.setFullscreen(true);
		window.show();
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.addLevel(new Game(GAME_ID));
		// Iniciar Juego
		m.enterLevel(GAME_ID, true);

	}

}
