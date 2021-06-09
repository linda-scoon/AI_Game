package com.aiGame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * @author Linda Scoon
 *
 */
public class Game extends Canvas {

	private static final long serialVersionUID = 1L;
	public final int WIDTH = 500;
	public final int HEIGHT = 500;
	private Input input = new Input();
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();
	private static boolean running;

	/**
	 * Initialises the game. Takes no parameters
	 */
	public Game() {

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener(input);
		this.setFocusable(true);

	}

	public void start() {

		final int TICKS_PER_SECOND = 60;// 60 frames per second
		final int TIME_PER_TICK = 1000 / TICKS_PER_SECOND;
		final int MAX_FRAME_SKIPS = 5;

		int loops;// counts number off times we update
		long nextGameTick = System.currentTimeMillis();

		float sync;
		long timeAtLastFPSCheck = 0;
		int ticks = 0;
		running = true;

		while (running) {

			// updating
			loops = 0;

			/*
			 * This loop checks if the game is taking longer than expected; it then keeps
			 * updating until it has been updated the maximum number of times.
			 */
			while (System.currentTimeMillis() > nextGameTick && loops < MAX_FRAME_SKIPS) {
				update();
				ticks++;

				nextGameTick += TIME_PER_TICK;
				loops++;
			}

			// Rendering
			sync = (float) (System.currentTimeMillis() + TIME_PER_TICK - nextGameTick) / (float) TIME_PER_TICK;
			render(sync);

			// FPS Check
			if (System.currentTimeMillis() - timeAtLastFPSCheck >= 1000) {
				System.out.println(ticks);
				ticks = 0;
				timeAtLastFPSCheck = System.currentTimeMillis();

			}

		}
	}

	/**
	 * loops through all updatables
	 */
	public void update() {

		for (Updatable u : updatables) {
			u.update(input);
		}
	}

	/**
	 * this method renders graphics to the screen We first check if the buffer
	 * already exists. If it doesn't we create a buffer. Then we define the graphics
	 * and save them to the buffer before disposing of them. At the end we show the
	 * buffer
	 * 
	 * @param sync (interpolation)
	 */

	public void render(float sync) {

		BufferStrategy b = this.getBufferStrategy();

		if (b == null) {
			this.createBufferStrategy(2);
			return;// remember to include this because the buffer created within the braces can't
					// beused outside otherwise
		}

		Graphics2D g2 = (Graphics2D) b.getDrawGraphics();
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());// clear the screen

		for (Renderable r : renderables) {
			r.render(g2, sync);
		}
		g2.dispose();
		b.show();

	}

	/**
	 * 
	 * @param u
	 */
	public void addUpdatable(Updatable u) {
		updatables.add(u);
	}

	/**
	 * 
	 * @param u
	 */
	public void removeUpdatable(Updatable u) {
		updatables.remove(u);
	}

	/**
	 * 
	 * @param r
	 */
	public void addRenderable(Renderable r) {
		renderables.add(r);
	}

	/**
	 * 
	 * @param r
	 */
	public void removeRenderable(Renderable r) {
		renderables.remove(r);
	}

	/**
	 * 
	 * @return width
	 */
	@Override
	public int getWidth() {
		return WIDTH;
	}

	/**
	 * 
	 * @return height
	 */
	@Override
	public int getHeight() {
		return HEIGHT;
	}

	/**
	 * 
	 * @param running
	 */
	public static void setRunning(boolean run) {
		running = run;
	}

}
