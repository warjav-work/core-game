package engine.render.graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import engine.audio.AudioClip;
import engine.audio.AudioPlayer;
import engine.events.ButtonEvent;
import engine.events.ButtonListener;
import engine.input.Input;
import engine.input.Mouse;
import engine.physics.collisions.Collision;
import engine.render.core.GameManager;
import engine.render.core.Window;

public class Button extends GUI {
	private Color hoverColor = Color.GRAY, clickedColor = Color.LIGHT_GRAY, inactiveColor = Color.BLACK;
	private boolean clicked;

	private AudioClip clickClip, scrollClip;

	private ButtonListener listener;
	private ButtonEvent event;

	public Button(String title, int x, int y, int sx, int sy) {
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;

		this.hasBackground = true;
		this.backgroundColor = Color.WHITE;
		this.showWireFrame = true;
		this.frameColor = Color.GRAY;

		this.hasLabel = true;
		this.labelFont = new Font("Arial", 1, 16);
		this.labelText = title;
		this.labelColor = Color.BLACK;
		FontMetrics fm = GameManager.getWindow().getFontMetrics(labelFont);
		this.labelX = x + sx / 2 - fm.stringWidth(labelText) / 2;
		this.labelY = y + sy / 2 + fm.getAscent() / 2;

		this.doDraw = true;
	}

	public void addButtonListener(ButtonListener listener) {
		this.listener = listener;
		this.event = new ButtonEvent(this);
	}

	@Override
	public void update(Window win) {
		Mouse m = win.getMouse();
		if (Collision.isMouseColliding(this)) {
			if (m.isButtonPressed(Input.LEFT_MOUSE)) {
				clicked = true;
				this.backgroundColor = clickedColor;
				this.frameColor = clickedColor;
				if(clickClip != null) {
					AudioPlayer.playSound(clickClip, 1.0);
				}

			} else if (clicked && m.isMouseUp(Input.LEFT_MOUSE)) {
				clicked = false;
				this.labelColor = hoverColor;
				this.frameColor = hoverColor;

				// Trigger Evento de Boton
				if (!listener.equals(null)) {
					listener.action(event);
				}

			} else if (!clicked && labelColor != hoverColor) {
				this.labelColor = hoverColor;
				this.frameColor = hoverColor;
				if(scrollClip != null) {
					AudioPlayer.playSound(scrollClip, 1.0);
				}
			}
		} else if (labelColor != inactiveColor) {
			this.labelColor = inactiveColor;
			this.frameColor = inactiveColor;
		}

	}

	public void setSounds(AudioClip clickClip, AudioClip scrollClip) {
		this.clickClip = clickClip;
		this.scrollClip = scrollClip;
	}

	public void setColors(Color hoverColor, Color clickedColor, Color inactiveColor) {
		this.hoverColor = hoverColor;
		this.clickedColor = clickedColor;
		this.inactiveColor = inactiveColor;
	}

	public void setFont(String font, int size) {
		this.labelFont = new Font(font, 1, size);

		FontMetrics fm = GameManager.getWindow().getFontMetrics(labelFont);
		this.labelX = (int) x + (int) sx / 2 - fm.stringWidth(labelText) / 2;
		this.labelY = (int) y + (int) sy / 2 + fm.getAscent() / 2;
	}
	
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

}
