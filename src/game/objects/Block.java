package game.objects;

import java.awt.Color;

import engine.physics.objects.GameObject;
import engine.render.core.GameManager;
import engine.render.core.Window;

public class Block extends GameObject {
	private static int velocity = 3;
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.sx = 32;
		this.sy = 32;
		this.color = Color.RED;
		
		this.tag = "block";
	}

	@Override
	public void update(Window win, GameManager gm) {
		this.x -= velocity;
		if(x < 0-sx) {
			this.isDestroyed = true;
		}

	}

}
