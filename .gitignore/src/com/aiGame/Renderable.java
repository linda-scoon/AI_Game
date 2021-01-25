package com.aiGame;
/**
 * 
 */


import java.awt.Graphics2D;

/**
 * @author Flourish
 * This interface enables the consolidation of all objects
 * that need to be rendered, for easy processing.
 * 
 */
public interface Renderable {
	/**
	 * 
	 * @param g
	 * @param sync
	 */
	public void render(Graphics2D g2, float sync);
}
