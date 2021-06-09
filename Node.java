package com.aiGame;
import java.util.ArrayList;

/**
 * Defines a node
 */
public class Node {

	private Object element;// making elements to be be objects so that we can add anything in the node
	private boolean visited;
	private ArrayList<Node> neighbors;
	private double fCost;
	private double gCost;
	private double hCost;
	private Node parentNode;

	public Node(Object e) {
		element = e;
		visited = false;
		neighbors = new ArrayList<>();
		gCost = 0;
		hCost = 0;
		fCost = gCost + hCost;
	}

	/**
	 * @return the element
	 */
	public int getElement() {
		return (int) element;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the neighbors
	 */
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors the neighbors to set
	 */
	public void setNeighbor(Node neighbor) {
		this.neighbors.add(neighbor);
	}

	/**
	 * @return the fCost
	 */
	public double getfCost() {
		return fCost;
	}

	/**
	 * @param fCost the fCost to set
	 */
	public void setfCost(double fCost) {
		this.fCost = fCost;
	}

	/**
	 * @return the gCost
	 */
	public double getgCost() {
		return gCost;
	}

	/**
	 * @param gCost the gCost to set
	 */
	public void setgCost(double gCost) {
		this.gCost = gCost;
	}

	/**
	 * @return the hCost
	 */
	public double gethCost() {
		return hCost;
	}

	/**
	 * @param hCost the hCost to set
	 */
	public void sethCost(double hCost) {
		this.hCost = hCost;
	}

	/**
	 * @return the parentNode
	 */
	public Node getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public String toString() {
		return "Node [element=" + element + "]";
	}
}
