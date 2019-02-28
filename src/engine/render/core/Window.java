package engine.render.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import engine.input.Input;
import engine.input.Mouse;
import engine.utils.Debug;

public class Window extends Canvas {

	private static final long serialVersionUID = -4460216847253265879L;

	private static JFrame FRAME;
	private static int WIDTH, HEIGHT, BUFFER_SIZE;
	private static String TITLE;

	private GameManager gm;
	private Drawer drawer;
	private Input input;
	private Mouse mouse;

	int frames, ticks, time;
	private int lastFrames, lastTicks;

	private Thread loop;
	private final double UPDATE_SPEED = 60;
	private static boolean running;

	public Window(String title, int w, int h, int bs, GameManager gm) {
		input = new Input();
		mouse = new Mouse();
		this.gm = gm;

		Window.TITLE = title;
		Window.WIDTH = w;
		Window.HEIGHT = h;
		Window.BUFFER_SIZE = bs;

		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		setSize(size);
		setFocusable(true);

		FRAME = new JFrame(TITLE);

		FRAME.setSize(size);
		FRAME.add(this);
		FRAME.pack();
		FRAME.setResizable(false);
		FRAME.setLocationRelativeTo(null);
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// show();
	}

	public void show() {
		this.createBufferStrategy(BUFFER_SIZE);
		running = true;
		FRAME.setVisible(true);

		drawer = new Drawer(this);

		startInputListeners();
		gameLoop();
	}

	private void startInputListeners() {
		this.addKeyListener(input);
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
	}

	/**
	 * Ejecuta el siguiente Buffer
	 */
	public void update() {
		if (!running) {
			Debug.LogError("VENTANA NO INICIADA!");
		}
		this.getBufferStrategy().show();

	}

	public void clear(Color clearColor) {
		if (!running) {
			Debug.LogError("VENTANA NO INICIADA!");
		}
		BufferStrategy st = this.getBufferStrategy();

		Graphics g = st.getDrawGraphics();
		g.setColor(clearColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public void close() {
		System.out.println("WINDOW>> Cerrando Aplicacion!");
		FRAME.dispose();
		System.exit(0);
	}

	public boolean isRunning() {
		return running;
	}

	private void gameLoop() {
		loop = new Thread() {
			public void run() {
				double lastTime = System.nanoTime();
				double delta = 0;
				final double ns = 1e9 / UPDATE_SPEED;

				double start = System.currentTimeMillis();
				int next = 1;

				while (isRunning()) {
					double nowTime = System.nanoTime();
					double now = (System.currentTimeMillis() - start) / 1000;
					delta += (nowTime - lastTime) / ns;
					lastTime = nowTime;

					while (delta >= 1) {

						gm.update();

						delta--;
					}
					gm.render();

					if (now >= next) {
						next++;
						time++;
						lastFrames = frames;
						lastTicks = ticks;
						Debug.Log("WINDOW>> FPS: " + lastFrames + ", UPS: " + lastTicks);
						Window.FRAME.setTitle(Window.TITLE + " | FPS: " + lastFrames + ", UPS: " + lastTicks);
						frames = 0;
						ticks = 0;
					}
				}
			}
		};
		loop.start();
	}

	public void setFullscreen(boolean fullscreen) {
		if (fullscreen && !running) {
			FRAME.dispose();
			FRAME.setUndecorated(true);
			FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
			FRAME.setVisible(true);

		}
		WIDTH = FRAME.getWidth();
		HEIGHT = FRAME.getHeight();
	}

	public Drawer getDrawer() {
		return drawer;
	}

	public Input getInput() {
		return input;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public static Window getWindow() {
		return GameManager.getWindow();

	}

}
