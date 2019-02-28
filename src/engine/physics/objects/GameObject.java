package engine.physics.objects;

import java.awt.Color;

import engine.render.core.Drawer;
import engine.render.core.GameManager;
import engine.render.core.Window;
import engine.render.graphics.images.Image;

public abstract class GameObject {

	protected int x, y;
	protected int sx, sy;
	protected boolean doDraw = true, didDraw, hasImage, isDestroyed;
	protected Image image;
	protected Color color;
	protected String tag;

	public GameObject() {
		
	}

	public abstract void update(Window win, GameManager gm);

	public void render(Window win, Drawer d) {
		if (doDraw) {
			if (hasImage) {
				
				d.drawImage(image, x, y);
			} else {
				
				d.fillRect(x, y, sx, sy, color);
			}
			didDraw = true;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSX() {
		return sx;
	}
	
	public int getSY() {
		return sy;
	}
	
	public boolean isDidDraw() {
		return didDraw;
	}
	
	public String getTag() {
		return tag;
	}
	

}
