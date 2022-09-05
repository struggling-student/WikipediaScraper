package GUI;

/**
 * Class that represents a line inside the ImepratorPanel
 *
 */
class Line {
	/**
	 * first and second point of the line
	 */
	private Point p1, p2;
	/**
	 * constructor of the line
	 * @param p1 first point
	 * @param p2 second point
	 */
	Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	/**
	 * getter of the first point
	 * @return
	 */
	public Point getP1() { return p1; }
	/**
	 * getter of the second point
	 * @return
	 */
	public Point getP2() { return p2; }
}
