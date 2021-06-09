package com.aiGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Creates a board and graph
 * 
 * @author Linda Scoon
 *
 */
public class Board implements Renderable {

	/*
	 * The array representation of the screen 0 = Wall, 1 = Grass
	 */
	private static int[][] board = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

	};

	private int pixelSize;
	private Graph graph;
	private Node[][] nodeGrid;
	private int rowNum;
	private int colNum;
	private int height;
	private int width;
	private Player player;

	private int time;

	public Board(Player p) {

		player = p;
		height = 500;
		width = 500;
		pixelSize = 20;
		rowNum = board.length;
		colNum = board[0].length;
		nodeGrid = new Node[rowNum][colNum];
		graph = new Graph();

		genGrassPatches();
		createNodeGrid();
		createGraph();
	}

	/**
	 * Makes a node of every element in the array using its index
	 */
	private void createNodeGrid() {// Making every element in the board a node.
		int idx = 0;
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {

				// add node to node grid
				nodeGrid[row][col] = new Node(idx);

				// setting walls
				if (board[row][col] == 0) {
					nodeGrid[row][col].setVisited(true);
				}
				idx++;
			}
		}
	}

	/**
	 * - Sets the hCost of every node - Calls the addNeighbors() to define all the
	 * connections - Adds the array of connected nodes to graph
	 */
	private void createGraph() {// connecting the nodes
		ArrayList<Node> connectedNodes = new ArrayList<>();

		// Get target coordinates
		int xTarget = player.getX();
		int yTarget = player.getY();

		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {

				// Calculate hCost using euclidean distance
				double hCost = Math.sqrt(Math.pow((row - xTarget), 2) + Math.pow((col - yTarget), 2));

				// adding hCost
				nodeGrid[row][col].sethCost(hCost);

				// adding neighbors
				addNeighbors(row, col);
				connectedNodes.add(nodeGrid[row][col]);
			}
		}
		graph.setConnectedNodes(connectedNodes);
	}

	/**
	 * Defines node connections
	 * 
	 * @param row
	 * @param col
	 */
	private void addNeighbors(int row, int col) {

		if (!this.nodeGrid[row][col].isVisited()) {
			if (widthinGrid(row + 1, col, this.rowNum, this.colNum)) {
				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row + 1][col]);
			}
			if (widthinGrid(row - 1, col, this.rowNum, this.colNum)) {
				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row - 1][col]);
			}
			if (widthinGrid(row, col + 1, this.rowNum, this.colNum)) {
				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row][col + 1]);
			}
			if (widthinGrid(row, col - 1, this.rowNum, this.colNum)) {
				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row][col - 1]);
			}

			// diagonals [need a gcost added in the astar]
//			if (widthinGrid(row + 1, col + 1, this.rowNum, this.colNum)) {
//				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row + 1][col + 1]);
//			}
//			if (widthinGrid(row - 1, col - 1, this.rowNum, this.colNum)) {
//				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row - 1][col - 1]);
//			}
//			if (widthinGrid(row - 1, col + 1, this.rowNum, this.colNum)) {
//				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row - 1][col + 1]);
//			}
//			if (widthinGrid(row + 1, col - 1, this.rowNum, this.colNum)) {
//				this.nodeGrid[row][col].setNeighbor(this.nodeGrid[row + 1][col - 1]);
//			}
		}
	}

	/**
	 * Checks that only positions within the grid are searched
	 * 
	 * @param row
	 * @param col
	 * @param maxRow
	 * @param maxCol
	 * @return boolean
	 */
	private boolean widthinGrid(int row, int col, int maxRow, int maxCol) {
		if (row < 0 || col < 0) {
			return false;
		}
		if (row >= maxRow || col >= maxCol) {
			return false;
		}
		return true;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @return pixelSize
	 */
	public int getPixelSize() {
		return pixelSize;
	}

	/**
	 * 
	 * @param pixelSize
	 */
	public void setPixelSize(int pixelSize) {
		this.pixelSize = pixelSize;
	}

	/**
	 * 
	 * @return rowNum
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * 
	 * @param rowNum
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * 
	 * @return
	 */
	public int getColNum() {
		return colNum;
	}

	/**
	 * @param colNum
	 */
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	/**
	 * 
	 * @return graph
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * 
	 * @return board
	 */
	public static int[][] getBoard() {
		return board;
	}

	/**
	 * Randomly assigns squares of the array to be a darker green
	 */
	private void genGrassPatches() {
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {

					if (board[row][col] == 2) {
						board[row][col] = 1;
				}
				if (Math.random() < 0.2 && board[row][col] != 0) {
					board[row][col] = 2;
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g2, float sync) {
		time++;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				Color color;
				switch (board[row][col]) {
				case 0:
					color = new Color(149, 153, 146);
					break;
				case 2:
					color = new Color(77, 172, 7);
					break;
				default:
					color = new Color(38, 252, 0);

				}
				g2.setColor(color);
				g2.fillRect(row * pixelSize, col * pixelSize, pixelSize, pixelSize);
			}
		}
		if (time % 60000 == 0) {// generate random background
			genGrassPatches();
		}
	}

}