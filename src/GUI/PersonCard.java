package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openqa.selenium.WebDriver;

import hierarchy.Person;

/**
 * Class used to display a person. It extends a JPanel because labels does not have a working background, so we decided to use the
 * JPanel's background with a label inside
 *
 */
class PersonCard extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  Person of the PersonCard
	 */
	protected Person person;
	/**
	 *  Label used to display the name in the ui
	 */
	protected JLabel personLabel = new JLabel();
	/** boolean that says if the PersonCard is horizontal.
	* if the PersonCard is not horizontal the line that connects the Person to the father/successor/husband
	* will start from the top, otherwise it ill start on the left. (it's horizontal only for wives)
	*/
	private boolean isHorizontal;
	
	/**
	 * Constructor of the PersonCard. It takes the person that has to be displayed
	 * @param p
	 */
	PersonCard(Person p) {
		this.person = p;
		this.personLabel = new JLabel(p.getName());
	}
	/**
	 * method that sets the isHorizontal field
	 * @param t boolean is horizontal
	 * @return the personcard
	 */
	public PersonCard setHorizontal(boolean t) { isHorizontal = t; return this; }
	
	/**
	 * Method that displays the PersonCard on the panel passed as input.
	 * It draws a line from the predecessor (from the old point) to the point p, where the PersonCard
	 * will be drawn. It also adds the event that navigates to the person's wiki when the PersonCard is clicked.
	 * @param p point where the label will be displayed
	 * @param old position of the predecessor (the line will start from this point)
	 * @param panel panel where the person will be drawn
	 * @param d webdriver used to navigate to the wiki page of the person
	 * @return
	 */
	public PersonCard draw(Point p, Point old, ImperatorPanel panel, WebDriver d) {
		
		
		// set the listener
		this.addMouseListener(new PersonMouseAdapter(d, this.person.getUrl()));
		// calculates the point where the line will start and adds the line
		int x = p.getX();
		int y = p.getY();
		int xOffset;
		int yOffset;
		if(this.isHorizontal) {
			xOffset = 0;
			yOffset = 15;
			// set the border of the panel
			this.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
		}
		else {
			xOffset = 100;
			yOffset = 0;
			// set the border of the panel
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		}
		panel.addLine(new Line(new Point(x + xOffset, y + yOffset), new Point(old.getX(), old.getY())));
		// sets the look of the JPanel and the label
		personLabel.setBounds(x, y, 200, 30);
		this.setBounds(x, y, 200, 30);
		this.setBackground(Color.white);
		this.add(personLabel);
		panel.add(this);
		return this;
	}
}