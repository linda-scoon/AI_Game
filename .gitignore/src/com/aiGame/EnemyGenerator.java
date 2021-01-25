package com.aiGame;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * This class generates all the different AI
 * 
 * @author Linda Scoon
 *
 */
public class EnemyGenerator implements Updatable, Renderable {

	private Board board;
	private Player player;
	private static ArrayList<Enemy> enemies;

	public EnemyGenerator(Board b, Player p) {
		board = b;
		player = p;

		enemies = new ArrayList<>();
		addEnemies();
	}

	private void addEnemies() {

		// FSM
		enemies.add(new Enemy(300, 300, "FSM", board, player));

		// persuers
		enemies.add(new Enemy(400, 400, "persuer", board, player));
		enemies.add(new Enemy(100, 400, "persuer", board, player));

		// wanderers
		enemies.add(new Enemy(200, 200, "wanderer", board, player));
		enemies.add(new Enemy(200, 400, "wanderer", board, player));

		// seekers
		enemies.add(new Enemy(300, 100, "seeker", board, player));

	}

	@Override
	public void update(Input input) {

		for (Enemy e : enemies) {
			e.update(input);
		}
	}

	@Override
	public void render(Graphics2D g2, float sync) {

		for (Enemy e : enemies) {
			e.render(g2, sync);
		}
	}

	/**
	 * 
	 * @return ArrayList<Enemy>
	 */
	public static ArrayList<Enemy> getEnemies() {
		return enemies;
	}

}
