package engine.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ResourceLoader {

	public static BufferedImage loadImage(String path) {
		BufferedImage img = null; 
		try {
			img = ImageIO.read(new File(path));
			
		} catch (IOException e) {
			Debug.LogError(e.getMessage());
			
		}
		return img;
	}
	
	public static BufferedImage loadImg(String nombre) {

		try {
			return ImageIO.read(ResourceLoader.class.getClassLoader().getResourceAsStream(nombre + ".png"));
		} catch (IOException e) {
			Debug.LogError(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
