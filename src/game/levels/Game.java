package game.levels;

import java.awt.Color;
import java.util.Random;

import engine.audio.AudioClip;
import engine.audio.AudioPlayer;
import engine.events.ButtonEvent;
import engine.events.ButtonListener;
import engine.networking.Client;
import engine.networking.Server;
import engine.render.core.Drawer;
import engine.render.core.GameLevel;
import engine.render.core.GameManager;
import engine.render.core.Window;
import engine.render.graphics.gui.Button;
import engine.render.graphics.gui.GUIManager;
import engine.render.graphics.gui.Label;
import engine.render.graphics.images.SpriteSheet;
import engine.utils.Debug;

public class Game extends GameLevel {
	SpriteSheet ss;
	int y = 200, x = 100;
	int cx, cy;
	AudioClip clip;
	AudioClip scrollClip, clipClip;
	Client client;
	Server server;

	String coords = "0, 0";
	Button scBtn, ssBtn;

	public Game(int levelID) {
		this.levelID = levelID;
	}

	@Override
	public void init(Window win, GameManager gm) {
		Debug.Log("GAME>> JUEGO INICIADO!");
		//clip = new AudioClip("res/rosa.mp3");
		//clip = AudioPlayer.convertToMP3(clip);
		
		clipClip = new AudioClip("res/start.wav");
		clipClip = new AudioClip("res/menutheme.wav");

		//Label l = new Label("Hola GUI", 200, 200);
		//Label l1 = new Label("Hola GUI", 200, 220, Color.RED);
		
		scBtn = new Button("Iniciar Cliente!", 100, 200, 100, 50);		
		scBtn.setColors(Color.WHITE, Color.GRAY, Color.DARK_GRAY);
		scBtn.setFont("Times New Roman", 25);
		scBtn.setSounds(clipClip, scrollClip);
		scBtn.addButtonListener(new ButtonListener() {
			@Override
			public void action(ButtonEvent e) {
				client = new Client("localhost", 5284);	
			}
		});
		GUIManager.add(scBtn);
		
		
		ssBtn = new Button("Iniciar Servidor!", 300, 200, 100, 50);		
		ssBtn.setColors(Color.WHITE, Color.GRAY, Color.DARK_GRAY);
		ssBtn.setFont("Times New Roman", 25);
		ssBtn.setSounds(clipClip, scrollClip);
		ssBtn.addButtonListener(new ButtonListener() {
			@Override
			public void action(ButtonEvent e) {
				server = new Server(5284);	
			}
		});
		GUIManager.add(ssBtn);
		
		
		
		//GUIManager.add(l);
		//l.setFont("Cablri", 50);
		//l.setColor(Color.CYAN);
		//GUIManager.add(l1);

	}

	@Override
	public void update(Window win, GameManager gm) {
		if(client != null) {
			String message = client.getMessage();
			if(message != null) {
				String[] rawColor = message.split(",");
				
				int r = Integer.parseInt(rawColor[0]);
				int g = Integer.parseInt(rawColor[1]);
				int b = Integer.parseInt(rawColor[2]);
				
				scBtn.setBackgroundColor(new Color(r, g, b));
			}
		}else if(server != null){
			Random rand = new Random();
			int r = 1 + rand.nextInt(255);
			int g = 1 + rand.nextInt(255);
			int b = 1 + rand.nextInt(255);
			
			server.updateClients(r+","+g+","+b);
		}
		
	}

	@Override
	public void render(Window win, Drawer d, GameManager gm) {

	}

}
