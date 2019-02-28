package engine.render.graphics.gui;

import java.awt.Color;
import java.awt.Font;

import engine.render.core.Window;

public class Label extends GUI {

	public Label(String text, int x, int y) {
		this.labelX = x;
		this.labelY = y;
		this.hasLabel = true;
		this.labelText = text;
		
		this.doDraw = true;
	}
	
	public Label(String text, int x, int y, Color color) {
		this.labelX = x;
		this.labelY = y;
		this.hasLabel = true;
		this.labelText = text;
		this.labelColor = color;
		
		this.doDraw = true;
	}
	
	public void setFont(String font, int size){
		this.labelFont = new Font(font, 1, size);
	}
	public void setFont(String font, int style, int size){
		this.labelFont = new Font(font, style, size);
	}
	
	public void setColor(Color color) {
		this.labelColor = color;
	}
	
	public void setText(String text) {
		this.labelText = text;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void update(Window win) {
		// TODO Auto-generated method stub

	}

}
