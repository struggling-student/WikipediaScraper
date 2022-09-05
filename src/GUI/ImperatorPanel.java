package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * this class extends the JPanel and is used to display the people and the 
 * lines. I had to extend the JPanel in order to overwrite the paintComponent because
 * it's the only correct way of drawing lines inside a JPanel.
 *
 */
class ImperatorPanel extends JPanel {
	/**
	 * list of all the lines that the ui has to draw when the ImperatorPanel is updated
	 */
	private List<Line> lineList = new ArrayList<Line>();
	
	/**
	 * Override of the paintComponent() method. Inside the method all the lines
	 * inside lineList that needs to be displayed will be drawn.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for(Line l : lineList) {
			g.drawLine(l.getP1().getX(), l.getP1().getY(), l.getP2().getX(), l.getP2().getY());
		}
	}
	/**
	 * method that adds a new line. The line will be displayed when the ui calls the painComponent for the ImperatorPanel
	 * @param l line
	 */
	public void addLine(Line l) { this.lineList.add(l); }
}
