package com.aiGame;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Handles all the a* logic
 * 
 * @author Linda Scoon
 *
 */
public class AStar {

	private PriorityQueue<Node> openList;
	private ArrayList<Node> closedList;

	public AStar() {
		/*
		 * The openList is a priority queue that uses a lambda as the comparable method
		 * that defines how the elements in the queue are to be arranged
		 */
		openList = new PriorityQueue<Node>((Node n1, Node n2) -> {
			if (n1.getfCost() < n2.getfCost()) {
				return -1;
			} else if (n1.getfCost() > n2.getfCost()) {
				return 1;
			}
			return 0;
		}) {
			private static final long serialVersionUID = 1L;

		};
	}

	/**
	 * @param g
	 * @param startIdx
	 * @param targetIdx
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> search(Graph g, int startIdx, int targetIdx) {

		// for debugging
//		System.out.println(startIdx + "  " + targetIdx);

		// initialising the graph, an array to hold the final path, a closed list and a
		// root
		ArrayList<Node> validPath = new ArrayList<>();
		ArrayList<Node> graph = g.getConnectedNodes();
		closedList = new ArrayList<>();
		Node root = graph.get(startIdx);

		root.setgCost(0);
		openList.add(root);

		// initialise a current Node
		Node currentNode;

		while (true) {// This loop run until a path is found or there are no elements left in the
						// openList

			// remove smallest
			currentNode = openList.poll();

			// if queue is empty break
			if (currentNode == null && startIdx == targetIdx) {
				break;
			}

			// if target is found return path
			if (currentNode.getElement() == targetIdx) {
				while (currentNode.getParentNode() != null) {
					validPath.add(currentNode.getParentNode());
					currentNode = currentNode.getParentNode();

					if (currentNode.getElement() == startIdx) {
						openList.clear();
						closedList.clear();

						// for debugging
//						System.out.println(validPath);

						return validPath;
					}

				}

			}

			// else add to closed list
			closedList.add(currentNode);

			// then get Neighbors
			for (Node n : currentNode.getNeighbors()) {
				updateOpenList(currentNode, n);
			}
		}
		closedList.clear();
		return null;// if there is no path
	}

	private void updateOpenList(Node currentNode, Node n) {
		double vhCost = 1;// vertical and horizontal cost
		double gCost;

		// checking if neighbor is valid
		if (n == null || closedList.contains(n) || n.isVisited()) {
			return;
		}

		if (!openList.contains(n)) {

			// set gCost
			gCost = currentNode.getgCost() + vhCost;
			n.setgCost(gCost);

			// setfCost
			n.setfCost(gCost + n.gethCost());

			// set parent node
			n.setParentNode(currentNode);

			// add to list
			openList.add(n);
		}
	}

}
