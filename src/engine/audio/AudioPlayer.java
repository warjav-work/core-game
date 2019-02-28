package engine.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

public class AudioPlayer {

	public AudioPlayer() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized void playSound(AudioClip sfx, double vol) {
		Thread thread = new Thread() {

			public void run() {

				try {
					AudioInputStream stream = sfx.getAudioStream();

					Clip clip = AudioSystem.getClip();
					clip.open(stream);
					setVol(vol, clip);
					clip.start();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		};
		thread.start();

	}

	private static void setVol(double vol, Clip clip) {
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float dB = (float) (Math.log(vol) / Math.log(10) * 20);
		gain.setValue(dB);
	}

	public static AudioClip convertToMP3(AudioClip clip) {
		try {
			File file = clip.getFile();
			String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));

			Converter c = new Converter();

			c.convert(file.getAbsolutePath(), file.getParent() + "/" + fileName + ".wav");
			File newFile = new File(file.getParent() + "/" + fileName + ".wav");
			return new AudioClip(newFile.getAbsolutePath());
			
		} catch (JavaLayerException e) {
			
			e.printStackTrace();
		}

		return null;
	}

}
