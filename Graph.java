package com.aiGame;
import java.util.ArrayList;

/**
 * Stores an arrayList of connected nodes
 * 
 * @author Linda Scoon
 *
 */
public class Graph {
	private ArrayList<Node> connectedNodes;

	public Graph() {
		setConnectedNodes(new ArrayList<>());
	}

	/**
	 * @return the connectedNodes
	 */
	public ArrayList<Node> getConnectedNodes() {
		return connectedNodes;
	}

	/**
	 * @param connectedNodes the connectedNodes to set
	 */
	public void setConnectedNodes(ArrayList<Node> connectedNodes) {
		this.connectedNodes = connectedNodes;
	}

}
