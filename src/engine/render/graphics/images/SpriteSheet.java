package engine.render.graphics.images;

import java.awt.image.BufferedImage;

import engine.utils.Debug;

public class SpriteSheet {

	private BufferedImage img;
	private int sx, sy;

	public SpriteSheet(Image img, int sx, int sy) {
		this.img = img.getRawImage();
		this.sx = sx;
		this.sy = sy;
	}

	public BufferedImage getSpriteImage(int x, int y) {
		BufferedImage image = null;
		try {
			image = img.getSubimage(x * sx, y * sy, sx, sy);
		} catch (Exception e) {
			Debug.LogError("SPRITESHEET>> Fallo al cargar SubImagen");
			e.printStackTrace();
		}
		return image;
	}
	
	public Image getSprite(int ax, int ay) {
		return new Image(getSpriteImage(ax, ay));
	}

}
