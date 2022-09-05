package GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.openqa.selenium.WebDriver;

import hierarchy.Imperator;
import hierarchy.Person;

/**
 * Class that extends PersonCard. Overwrites the draw method in order to display
 * the wives, the sons and the successors of the person.
 * The draw method also calls the draw method of the next imperator.
 * In order to display without overlapping the labels, it calculates the distance where the next labels should be displayed.
 * draw() also calls the super.draw() in order to draw himself and to draw the line between this imperator and his predecessor. 
 * 
 */



public class ImperatorCard extends PersonCard{
	/**
	 * arraylist of JLabel for the wives wives
	 */
	private ArrayList<JLabel> wife = new ArrayList<JLabel>();
	/**
	 * arraylist of JLabel for the sons
	 */
	private ArrayList<JLabel> sons = new ArrayList<JLabel>();
	/**
	 * arraylist of personcard for the sons. This list is used to check if a successor is also a son.
	 * This is important because if a successor is a son, it will need to be displayed again and the old label needs to be destroyed.
	 */
	private List<PersonCard> descendants = new ArrayList<>();
	/**
	 * constructor of the ImepratorCard
	 * @param i the imperator
	 */	
	ImperatorCard(Imperator i) {
		super(i);
		System.out.println("Me = " + i.getName());
		for(Person wife : i.getSpouse()) {
			System.out.println("wife = " + wife.getName());
			this.wife.add(new JLabel(wife.getName()));			
		}
		for(Person son : i.getSons()) {
			System.out.println("Son = " + son.getName());
			this.sons.add(new JLabel(son.getName()));
		}
	}
	/**
	 * This method is used to draw the imperator and all the person related to him.
	 * @param p the point where the imperator will be displayed.
	 * @param old the point where the line from the predecessor should start
	 * @param panel the panel where the imperator will be displayed
	 * @param d the webdriver
	 */
	
	public PersonCard draw(Point p, Point old, ImperatorPanel panel, WebDriver d) {
		// calls the super.draw()
		super.draw(p, old, panel, d);
		//sets the border for the imperator
		this.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 4));
		int x = p.getX();
		int y = p.getY();		
		// adds the line to the predecessor
		panel.addLine(new Line(new Point(x + 100, y), new Point(old.getX(), old.getY())));
		// sets the bounds and the background of the label and of himself
		super.personLabel.setBounds(x, y, 200, 30);
		this.setBounds(x, y, 200, 30);
		this.setBackground(Color.white);
		// adds the elements to the ui
		this.add(super.personLabel);
		panel.add(this);
		
		// yspouse offsett is used to calculate where the y of the wives should start.
		// calculates the offset using the number of spouse and the height of the labels
		// yspousestart uses the uSpouseoffset to calculate where will the y coordinate of the 
		// wives start
		int ySpouseOffset = (person.getSpouse().size() * 30) / 2;
		int ySpouseStart = y + 15 - ySpouseOffset;
		
		// draw the wives using the PersonCard and the point calculated with ySpouseStart.
		// the line that connects the imperator to the spouse is horizontal, so the PersonCard is setted horizontal
		for(int i = 0; i < person.getSpouse().size(); i ++) { 
			Person spouse = person.getSpouse().get(i);
			new PersonCard(spouse).setHorizontal(true).draw(new Point(x + 340, ySpouseStart + i * 30 + 1), new Point(x + 200, y + 15), panel, d);
		}
		// a similar thing happens with the sons but, instead of being an y-offset, it's an x-offset
		int xSonOffset = (person.getSons().size() * 100);
		int xSonStart = y + 15 - xSonOffset;
		
		// draw the sons and add them to the descendants.
		for (int i = 0; i < person.getSons().size(); i++) {
			Person son = person.getSons().get(i);
			descendants.add(new PersonCard(son).draw(new Point(x + i * 200 + 1, y + 80), new Point(x + 100, y + 30), panel, d));
		}
		
		// now the imperator has to draw the successors.
		// the currentSpouseoffset is used in order to avoid the overlapping of the
		// spouse labels between the predecessor and the successor.
		int currentSpouseOffset = 0;
		
		// During the iteration, a check is done to see if the succesor is also a son.
		// if it's not, the successor will be displayed behind the sons.
		// if the successor is also a son, the old label of the son is deleted and his coordinates are used
		// to draw all vertical line that ends in a new successor who is also the son.
		for(int i = 0; i < ((Imperator) person).getSuccessors().size(); i++) {
			Imperator imp = ((Imperator) person).getSuccessors().get(i);
			PersonCard son = null;
			for(PersonCard s : descendants) {
				if(s.person.getName().equals(imp.getName())) {
					son = s;
					break;
				}
			}
			// if he is not a son i add the line and the new imperator
			if(son == null) {
				panel.getGraphics().drawLine(x, y, x + (100 * i), y + 200);
				new ImperatorCard(imp).draw(new Point(x + (600 * i), y + 100 + ySpouseOffset + currentSpouseOffset + ((Imperator) person).getSuccessors().get(i).getSpouse().size() * 30 + 25 + (imp.getSuccessors() == null ? 200 : 240)), new Point(x + 100,  y + 30), panel, d);			
			}
			// if he is a son I add the new imperator  and after I pass as the 'old' point the end of the line that leads to the son label.
			// Then i remove the son label 
			else {
				new ImperatorCard(imp).draw(new Point(son.getX(), son.getY() + ((Imperator) person).getSuccessors().get(i).getSpouse().size() * 30 + (imp.getSuccessors() == null ? 100 : 240)), new Point(son.getX() + 100, son.getY()), panel, d);
				panel.remove(son);
			}
			currentSpouseOffset += imp.getSpouse().size() * 30;
		}
		return this;
	}
}