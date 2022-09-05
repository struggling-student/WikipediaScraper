package GUI;

/**
 * class that represent a point.
 *
 */
class Point {
	/**
	 * coordinates of the point
	 */
	private int x, y;
	/**
	 * constructor of the point
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * getter of the x coordinate
	 * @return x
	 */
	public int getX() { return x; }
	/**
	 * getter of the y coordiante
	 * @return y
	 */
	public int getY() { return y; }
}
