package engine.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.utils.Debug;
/**
 * Sistema de obtención de recursos de Audio.
 * 
 * @author War
 *
 */
public class AudioClip {
	/**
	 * Atributo de tipo file que almacenará la ruta del recurso.
	 */
	private File file;
	/**
	 * Constructor  del objeto AudioClip. Almacena el recurso de Audio en un atributo File.
	 * @param path ruta del recurso.
	 */
	public AudioClip(String path) {
		file = new File(path);
		if (!file.exists()) {
			Debug.LogError("AUDIOCLIP>> ERROR: Audio no encontrado");
		}
	}
	/**
	 * Método que obtiene un recurso de sonido.
	 * @return devuelve un objeto AudioInputStream.
	 */
	public AudioInputStream getAudioStream() {
		try {
			
			return AudioSystem.getAudioInputStream(file);
			
		} catch (UnsupportedAudioFileException e) {
			Debug.LogError("AUDIOCLIP>> ERROR: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Debug.LogError("AUDIOCLIP>> ERROR: " + e.getMessage());
		}

		return null;
	}
	
	public File getFile() {
		return file;
	}

}
