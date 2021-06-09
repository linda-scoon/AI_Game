package com.aiGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Linda Scoon Processes input from the user.
 */
public class Input implements KeyListener {
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public Input() {
		up = false;
		down = false;
		left = false;
		right = false;
	}

	/**
	 * 
	 * @return direction
	 * This method converts the booleans to strings that are fed into other methods to be processed
	 */
	public String getDirection() {
		String direction = "";

		if (left) {
			direction = "left";

		}
		if (right) {
			direction = "right";

		}
		if (up) {
			direction = "up";
		}
		if (down) {
			direction = "down";
		}
		return direction;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
