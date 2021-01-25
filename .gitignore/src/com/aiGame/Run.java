package com.aiGame;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main method
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final int WIDTH = 500;
		final int HEIGHT = 500;

		// initialising
		Game game = new Game();
		Player player = new Player();
		Board board = new Board(player);
		EnemyGenerator eg = new EnemyGenerator(board, player);
		JPanel panel = new JPanel();
		
		// add score board
		panel.add(player.getScoreLabel());

		// adding updatables and renderables
		game.addRenderable(board);

		game.addUpdatable(player);
		game.addRenderable(player);

		game.addRenderable(eg);
		game.addUpdatable(eg);

		// initialising the frame
		JFrame frame = new JFrame("AVOID CAPTURE");
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(game, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

		// starting the game
		game.start();
	}
}
