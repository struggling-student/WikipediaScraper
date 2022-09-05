package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.openqa.selenium.WebDriver;

/**
 * Class that extends MouseAdapter.
 * When an element that uses this listener is clicked, the webdriver will navigate to the link
 * 
 *
 */
class PersonMouseAdapter extends MouseAdapter {
	/**
	 * webdriver linked to this session
	 */
	private WebDriver d;
	/**
	 * link where the webdriver will navigate
	 */
	private String link;
	/**
	 * constructor of PersonMouseAdapter
	 * @param d the webdriver of the session
	 * @param link a link
	 */
	public PersonMouseAdapter(WebDriver d, String link) {
		this.d = d;
		this.link = link;
	}
	/**
	 * Override of the mouseclicked method. It calls the navigate() method
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.navigate();
	}
	/**
	 * function that uses the Webdriver to navigate to the link passed in the constructor
	 */
	private void navigate() {
		this.d.get(this.link);
	}
}
