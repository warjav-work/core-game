package engine.render.graphics.images;

import java.awt.image.BufferedImage;

import engine.utils.ResourceLoader;

public class Image {
	private BufferedImage img;	

	public Image(String path) {
		img = ResourceLoader.loadImage(path);
		
	}
	
	public Image(BufferedImage img) {
		this.img = img;
	}

	public BufferedImage getRawImage(){
		return img;
	}	
	
	
	public int getWidth() {
		return img.getWidth(null);
	}
	
	public int getHeight() {
		return img.getHeight(null);
	}

}
