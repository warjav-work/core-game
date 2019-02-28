package engine.render.graphics.gui;

import java.util.ArrayList;

import engine.render.core.Drawer;
import engine.render.core.Window;

public class GUIManager {

	private static ArrayList<GUI> objects = new ArrayList<>();

	public static void update(Window win) {
		for (int i = 0; i < objects.size(); i++) {
			GUI gui = objects.get(i);
			if (gui.doDraw) {
				gui.update(win);
			}
		}
	}

	public static void render(Window win, Drawer d) {
		for (int i = 0; i < objects.size(); i++) {
			GUI gui = objects.get(i);
			if (gui.doDraw) {
				gui.render(win, d);
			}
		}
	}

	public static ArrayList<GUI> getObjects() {
		return objects;
	}

	public static void add(GUI gui) {
		objects.add(gui);
	}

	public static void remove(GUI gui) {
		objects.remove(gui);
	}

	public static void flush() {
		objects.clear();
	}

}
