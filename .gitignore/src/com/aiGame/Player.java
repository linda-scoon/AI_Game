package com.aiGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Player implements Updatable, Renderable {

	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private int pixelSize;
	private int left;
	private int right;
	private int up;
	private int down;
	private JLabel scoreLabel;
	private int score;
	private int life;

	public Player() {
		scoreLabel = new JLabel();
		life = 100;
		pixelSize = 20;
		xVel = 20;
		yVel = 20;
		x = 20;
		y = 20;
	}

	@Override
	public void render(Graphics2D g2, float sync) {
		g2.draw(getBounds());
		g2.setColor(Color.BLUE);
		g2.fillOval(x, y, pixelSize, pixelSize);
	}

	@Override
	/**
	 * @param input
	 */
	public void update(Input input) {
		getWallLocation();

		if (input.getDirection() == "left") {
			if (left != 0) {
				x -= xVel;
			}

		} else if (input.getDirection() == "right") {
			if (right != 0) {
				this.x += xVel;
			}

		} else if (input.getDirection() == "up") {
			if (up != 0) {
				this.y -= yVel;
			}

		} else if (input.getDirection() == "down") {
			if (down != 0) {
				this.y += yVel;
			}
		}
		// play the game
		play();
	}

	public void incrementScore() {// Increment Score on every update
		score++;
	}

	/**
	 * Game logic
	 */
	public void play() {

		for (Enemy e : EnemyGenerator.getEnemies()) {// looping thru enemies

			if (!e.catchPlayer() && life != 0) {// if player is not caught and still has life increment score
				incrementScore();

			} // if player is caught but still has life life-1
			else if (e.catchPlayer() && getLife() != 0) {e.catchPlayer();
				life--;

			} else if (life == 0) {// if player has no life end game;
				setScoreLabel("GAME OVER |   | Score: " + getScore() + " Life: " + getLife());

				// undrawing the path
				if (e.getPath() != null) {
					e.getPath().clear();
				}
				Game.setRunning(false);
			}
			setScoreLabel("Score: " + getScore() + "   Lives: " + getLife());
		}
	}

	/**
	 * The method getBoard is called to access the board array. Then the coordinates
	 * of the current player position, + or - the number of pixels the next move
	 * shall be, is divided by pixelSize inorder to get the row and col of the
	 * array. If the digit in that position != 0, the player moves
	 **/
	public void getWallLocation() {

		left = Board.getBoard()[(x / pixelSize) - 1][y / pixelSize];
		right = Board.getBoard()[(x / pixelSize) + 1][y / pixelSize];
		up = Board.getBoard()[x / pixelSize][(y / pixelSize) - 1];
		down = Board.getBoard()[x / pixelSize][(y / pixelSize) + 1];
	}

	/**
	 * 
	 * @return Rectangle
	 */
	public Rectangle getBounds() {
		return new Rectangle(x - pixelSize / 2, y - pixelSize / 2, pixelSize * 2, pixelSize * 2);
	}

	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @return xVel
	 */
	public int getxVel() {
		return xVel;
	}

	/**
	 * 
	 * @param xVel
	 */
	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	/**
	 * 
	 * @return yVel
	 */
	public int getyVel() {
		return yVel;
	}

	/**
	 * 
	 * @param yVel
	 */
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @param scoreLabel the scoreLabel to set
	 */
	public void setScoreLabel(String scoreLabel) {
		this.scoreLabel.setText(scoreLabel);
	}

	/**
	 * @return the scoreLabel
	 */
	public JLabel getScoreLabel() {
		return scoreLabel;
	}

	/**
	 * 
	 * @return life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * 
	 * @param life
	 */
	public void setLife(int life) {
		this.life = life;
	}
}
