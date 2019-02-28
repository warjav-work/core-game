package engine.physics.collisions;

import java.awt.Rectangle;

import engine.input.Mouse;
import engine.physics.objects.GameObject;
import engine.render.core.Drawer;
import engine.render.core.Window;
import engine.render.graphics.gui.GUI;

public class Collision {

	public static boolean isColliding(GameObject go1, GameObject go2) {
		Rectangle r1 = new Rectangle(go1.getX(), go1.getY(), go1.getSX(), go1.getSY());
		Rectangle r2 = new Rectangle(go2.getX(), go2.getY(), go2.getSX(), go2.getSY());

		return r1.intersects(r2);
	}

	public static boolean isMouseColliding(GUI gui) {
		Mouse m = Window.getWindow().getMouse();
		Drawer d = Window.getWindow().getDrawer();

		int mx = m.getX() - d.getCamX();
		int my = m.getY() - d.getCamY();

		Rectangle r1 = new Rectangle(mx, my, 1, 1);
		Rectangle r2 = new Rectangle((int) gui.getX(), (int) gui.getY(), (int) gui.getSX(), (int) gui.getSY());

		return r1.intersects(r2);
	}
}
