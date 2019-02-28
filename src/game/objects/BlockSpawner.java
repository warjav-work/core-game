package game.objects;

import java.util.Random;

import engine.physics.objects.GameObject;
import engine.physics.objects.ObjectManager;
import engine.render.core.GameManager;
import engine.render.core.Window;

public class BlockSpawner extends GameObject {
	private int tickspassed;
	private int timer = 32;
	private boolean movingDown = true;

	public BlockSpawner(Window win, int y) {
		this.x = win.getWidth() + 32;
		this.y = y;
		this.sx = 1;
		this.sy = 1;

		Random r = new Random();
		timer += r.nextInt(64);
		this.tag = "spawner";
	}

	@Override
	public void update(Window win, GameManager gm) {
		tickspassed++;
		if (tickspassed > timer) {
			spawn();
			tickspassed = 0;
		}

		if (movingDown) {
			y += 5;
			if (y > win.getHeight() - 32) {
				movingDown = !movingDown;
			}
		} else {
			y -= 5;
			if (y < 32) {
				movingDown = !movingDown;
			}
		}

	}

	private void spawn() {
		if (Player.isPlayerAlive()) {
			ObjectManager.addObject(new Block(x, y));
		}

	}

}
