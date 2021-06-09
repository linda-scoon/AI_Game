package com.aiGame;

/**
 * @author Flourish
 *
 */
public class Vector {
	private double x;
	private double y;

	/**
	 * 
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector add(Vector v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}

	public Vector sub(Vector v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}

	/**
	 * Multiply the vector by the given scalar
	 * 
	 * @param s
	 * @return the vector
	 */
	public Vector mult(double s) {
		this.x *= s;
		this.y *= s;
		return this;
	}

	public void setVector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This method returns the unit vector of the vector passed in
	 * 
	 * @param v
	 * @return v
	 */
	public void normalize() {

		x = getX() / Math.sqrt((Math.pow(getX(), 2) + (Math.pow(getY(), 2))));
		y = getY() / Math.sqrt((Math.pow(getX(), 2) + (Math.pow(getY(), 2))));
		setVector(x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

}
