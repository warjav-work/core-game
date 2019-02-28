package engine.render.core;

public abstract class GameLevel {
	protected int levelID;

	public GameLevel() {
	}

	public abstract void init(Window win, GameManager gm);

	public abstract void update(Window win, GameManager gm);

	public abstract void render(Window win, Drawer d, GameManager gm);

	public int getLevel() {
		return levelID;
	}

}
