package engine.render.graphics.gui;

import java.awt.Color;
import java.awt.Font;

import engine.render.core.Drawer;
import engine.render.core.Window;
import engine.render.graphics.images.Image;

public abstract class GUI {
	protected float x, y, sx, sy;

	protected Image image;
	protected boolean hasImage, hasBackground, doDraw, showWireFrame;
	protected Color backgroundColor, frameColor;

	protected boolean hasLabel;
	protected String labelText = "";
	protected Color labelColor;
	protected Font labelFont;
	protected int labelX, labelY;

	public abstract void update(Window win);

	public void render(Window win, Drawer d) {
		if (doDraw) {
			if (hasBackground) {
				d.fillRect((int) x, (int) y, (int) sx, (int) sy, backgroundColor);
			} else if (hasImage && image != null) {
				d.drawImage(image, (int) x, (int) y);
			}

			if (showWireFrame) {
				d.drawRect((int) x, (int) y, (int) sx, (int) sy, frameColor);
			}

			if (hasLabel && !labelText.equals("")) {
				d.drawString(labelText, labelX, labelY, labelColor, labelFont);
			}
		}
	}

	public void setVisible(boolean state) {
		doDraw = state;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getSX() {
		return sx;
	}

	public float getSY() {
		return sy;
	}

}
