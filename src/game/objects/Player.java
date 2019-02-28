package game.objects;

import engine.input.Input;
import engine.input.Mouse;
import engine.physics.collisions.Collision;
import engine.physics.objects.GameObject;
import engine.physics.objects.ObjectManager;
import engine.render.core.GameManager;
import engine.render.core.Window;
import engine.utils.Debug;

public class Player extends GameObject {
	private int dy = 8;
	private static boolean isAlive = true;
	public static int score;
	
	public Player(Window win) {
		this.sx = 32;
		this.sy = sx;

		this.x = 100;
		this.y = win.getHeight() / 2 - sy / 2;
		
		this.tag = "player";
	}

	@Override
	public void update(Window win, GameManager gm) {
		Input input = win.getInput();
		// Mouse mouse = win.getMouse();
		if(!isAlive) {
			if(input.isKeyDown(Input.SPACE)) {
				isAlive = true;
				score = 0;
				ObjectManager.clearObjects();
				gm.enterLevel(0, true);
			}
			return;
		}
		score++;
		
		checkCollision();
		
		if (input.isKeyDown(Input.W)) {
			y -= dy;
		} else if (input.isKeyDown(Input.S)) {
			y += dy;
		}
		
		if(y > win.getHeight() - sy) {
			y = win.getHeight() - sy;
					
		}
		
		if(y < 0) {
			y = 0;
					
		}
	}

	private void checkCollision() {
		for(int i =0; i < ObjectManager.getObjects().size();i++) {
			GameObject go = ObjectManager.getObjects().get(i);
			if(go.getTag().equals("block")) {
				if(Collision.isColliding(this, go)) {
					Debug.Log("PLAYER>> Has Muerto!");
					isAlive = false;
					
				}
			}
		}
		
	}
	
	public static boolean isPlayerAlive() {
		return isAlive;
	}

}
