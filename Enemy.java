package com.aiGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Linda Scoon
 *
 */
public class Enemy implements Renderable, Updatable {
	private int x;
	private int y;
	private int pixelSize;
	private Player player;
	private Board board;
	private int left;
	private int right;
	private int up;
	private int down;
	private int xVel;
	private int yVel;
	private int time;
	private int speed;
	private Color color;
	private AStar aStar;
	private Graph graph;
	private String state;
	private ArrayList<Node> path;
	ArrayList<Node> route = new ArrayList<>();
	private int pathX;
	private int pathY;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param s
	 * @param b
	 * @param p
	 */
	public Enemy(int x, int y, String s, Board b, Player p) {
		this.state = s;
		this.player = p;
		this.board = b;
		this.x = x;
		this.y = y;

		pixelSize = board.getPixelSize();
//		contact = false;// to check if player is already in contact with enemy
		speed = 20;
		xVel = 20;
		yVel = 20;

		EnemyGenerator.getEnemies();

		aStar = new AStar();
		graph = board.getGraph();

	}

	@Override
	public void render(Graphics2D g2, float sync) {
		g2.setColor(color);
		g2.fillOval(x, y, pixelSize, pixelSize);
		g2.drawString(state, x, y);

		// drawing a* path
		if (path != null) {
			for (Node n : path) {

				int pathX = (n.getElement() / board.getRowNum()) * pixelSize;
				int pathY = (n.getElement() % board.getRowNum()) * pixelSize;
				g2.fillOval(pathX, pathY, 5, 5);
			}
		}
	}

	@Override
	public void update(Input input) {
		switch (state) {
		case "seeker":
			seek();
			break;
		case "wanderer":
			wander(speed);
			break;
		case "persuer":
			persue();
			break;
		case "FSM":
			changeState();
		}
	}

	/**
	 * This method generates random movements
	 */
	public void wander(int speed) {
		this.color = Color.BLACK;
		getWallLocation();

		/**
		 * Since update is running in the game loop, with every iteration time is
		 * increased, and after "speed" iterations the condition becomes true and the
		 * conditionals inside are run. creating the animation
		 */
		time++;
		if (time % speed == 0) {
			Random r = new Random();
			int direction = r.nextInt(5) + 1;

			switch (direction) {
			case 1:
				if (left != 0) {
					x -= xVel;
				}
				break;
			case 2:
				if (right != 0) {
					x += xVel;
				}
				break;
			case 3:
				if (up != 0) {
					y -= yVel;
				}
				break;
			case 4:
				if (down != 0) {
					y += yVel;
				}
				break;
			case 0:
				y += 0;
				x += 0;
			}
		}
	}

	/**
	 * This method moves the ball towards the player
	 */
	public void seek() {
		this.color = Color.RED;
		getWallLocation();

		time++;
		if (time % speed == 0) {

			if (player.getX() < this.getX()) {
				if (left != 0) {
					this.x -= xVel;
				}
			}
			if (player.getX() > this.getX()) {
				if (right != 0) {
					this.x += xVel;
				}
			}
			if (player.getY() < this.getY()) {
				if (up != 0) {
					this.y -= yVel;
				}
			}
			if (player.getY() > this.getY()) {
				if (down != 0) {
					this.y += yVel;
				}
			}
		}
	}

	/**
	 * uses a* to track the player
	 */
	public void persue() {
		setColor(new Color(163, 20, 20));
		getWallLocation();

		time++;
		if (time % speed == 0) {
			path = aStar.search(graph, getStart(), getTarget());// getting arrayList of nodes

			if (path != null && path.size() - 1 != 0) {// ensuring we dont get any null pointer exceptions

				pathX = (path.get(path.size() - 2).getElement() / board.getRowNum()) * pixelSize;
				pathY = (path.get(path.size() - 2).getElement() % board.getRowNum()) * pixelSize;

				/*
				 * The conditionals below check to see if the x and y coordinates of the enemy
				 * are < or > the path coordinates and then move in that direction.
				 */
				if (this.x < pathX) {
					this.x += xVel;
				}
				if (this.x > pathX) {
					this.x -= xVel;
				}
				if (this.y < pathY) {
					this.y += yVel;
				}
				if (this.y > pathY) {
					this.y -= yVel;
				}
			}
		}
	}

	/**
	 * @return int target index
	 */
	private int getTarget() {
		/*
		 * To convert x and y coordiates into an index on the array, use this formula
		 * index = width * y + x. In this programme x and y have been flipped because
		 * the arrays (0,0) is different from the screens (0,0) Therefore the formula
		 * being used is index = width * x + y
		 */
		int target = (board.getWidth() / 20) * (player.getX() / 20) + (player.getY() / 20);

		return target;
	}

	/**
	 * @return int start index
	 */
	private int getStart() {
		// index = width * y + x (see target for explanation)
		int start = (board.getWidth() / 20) * (this.x / 20) + (this.y / 20);
		return start;
	}

	/**
	 * this controls the finite state machine starts off stationary then wanders and
	 * seeks then persues
	 */
	public void changeState() {
		this.color = Color.WHITE;
		time++;
		// 500_stationary
		if (time < 1000) {
			x += 0;
			y += 0;
		}

		// 1000_wander and seek

		if (time > 1000 && time < 6000 && player.getY() <= board.getHeight() / 2) {
			wander(speed);
		}
		if (time > 1000 && time < 6000 && player.getY() >= board.getHeight() / 2) {
			seek();
		}

		// last persute
		if (time > 6000) {
			persue();
		}
		
		// refresh
		if (time > 10000) {
			time = 0;
			path.clear();
		}
	}

	/**
	 * defines the coordinates of all the wall positions (0's) in the array
	 */
	private void getWallLocation() {
		// see player for details
		left = Board.getBoard()[(x / pixelSize) - 1][y / pixelSize];
		right = Board.getBoard()[(x / pixelSize) + 1][y / pixelSize];
		up = Board.getBoard()[x / pixelSize][(y / pixelSize) - 1];
		down = Board.getBoard()[x / pixelSize][(y / pixelSize) + 1];
	}

	/**
	 * collision with player
	 * 
	 * @return
	 */
	public boolean catchPlayer() {
		if (this.getBounds().intersects(player.getBounds())) {
			xVel = 0;
			yVel = 0;
			return true;
		}
		else {
			xVel = 20;
			yVel = 20;
		}
		return false;
	}

	/**
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
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the enemy
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Defines an external rectangle around the enemy for collision detection
	 * 
	 * @return Rectangle
	 */
	public Rectangle getBounds() {
		return new Rectangle(x - pixelSize / 2, y - pixelSize / 2, pixelSize * 2, pixelSize * 2);
	}

	/**
	 * @return
	 */
	public ArrayList<Node> getPath() {
		return path;
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
}
