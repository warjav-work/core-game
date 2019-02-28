package engine.render.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class Drawer {

	private Window window;
	private BufferStrategy bs;
	private Graphics g;
	
	private int camX, camY;

	public Drawer(Window window) {
		this.window = window;
		this.bs = window.getBufferStrategy();
		this.g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
	}

	public void drawImage(engine.render.graphics.images.Image img, int x, int y) {
		g.drawImage(img.getRawImage(), x, y, null);
	}

	public void drawString(String str, int x, int y) {
		g.drawString(str, x, y);
	}
	
	public void drawString(String str, int x, int y, Color color) {
		Color oldc = g.getColor();
		g.setColor(color);
		g.drawString(str, x, y);
		g.setColor(oldc);
	}
	
	public void drawString(String str, int x, int y, Color color, Font font) {
		Color oldc = g.getColor();
		g.setColor(color);
		
		Font oldf = g.getFont();
		g.setFont(font);
		g.drawString(str, x, y);
		g.setColor(oldc);
		g.setFont(oldf);
	}

	public void fillRect(int x, int y, int sx, int sy) {
		g.fillRect(x, y, sx, sy);
	}
	
	public void fillRect(int x, int y, int sx, int sy, Color color) {
		Color oldc = g.getColor();
		g.setColor(color);
		g.fillRect(x, y, sx, sy);
		g.setColor(oldc);
	}
	
	public void drawRect(int x, int y, int sx, int sy) {
		g.drawRect(x, y, sx, sy);
	}
	
	public void drawRect(int x, int y, int sx, int sy, Color color) {
		Color oldc = g.getColor();
		g.setColor(color);
		g.drawRect(x, y, sx, sy);
		g.setColor(oldc);
	}

	public void setColor(Color color) {
		g.setColor(color);
	}

	public void setFont(Font font) {
		g.setFont(font);
	}
	
	public void setCamPos(int cx, int cy) {
		g.translate(-camX, -camY);
		camX = cx;
		camY = cy;
		g.translate(camX, camY);
	}
	
	public void moveCamera(int mx, int my) {
		camX += mx;
		camY += my;
		
		g.translate(mx, my);
	}

	public Window getWindow() {
		return window;
	}
	
	public int getCamX() {
		return camX;
	}
	
	public int getCamY() {
		return camY;
	}
	
	public Graphics getGraphics() {
		return g;
	}

	

}
