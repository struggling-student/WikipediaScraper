package scraper;

import org.openqa.selenium.WebElement;

import hierarchy.Imperator;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Table {
	private WebElement table;
	private WebDriver driver;
	private List<Imperator> imperators = new ArrayList<Imperator>();
	
	/**
	 * Creates object Table 
	 * @param table WebElement object
	 */
	public Table(WebElement table, WebDriver driver) 
	{
		this.table = table;
		this.driver = driver;
	}
	
	/**
	 * 
	 * @return all tr's of the table object
	 */
	public List<WebElement> getTr() 
	{
		List<WebElement> trS = this.table.findElements(By.tagName("tr"));
		return trS;
	}
	
	/**
	 * 
	 * @return List of WebElement, all imperator (WebElement objects) extracted from the list of tr's given in input
	 */
	public void fillImperators() 
	{
		List<WebElement> trS = this.getTr();
		List<Imperator> imperators = new ArrayList<Imperator>();
		
		for (WebElement tr : trS) {
			try {
			WebElement imperator = tr.findElement(By.tagName("b"));
			
			//works on "b" html element 
			WebElement imperatorA = imperator.findElement(By.tagName("a"));
			String url = imperatorA.getAttribute("href");
			Imperator p = new Imperator(url, this.driver);
			imperators.add(p);
			}
			catch (Exception e) {}
		
		}
			
		this.imperators = imperators;
	}
	/**
	 * 
	 * @return driver
	 */
	public WebDriver getDriver() {return this.driver;}

	/**
	 * @return the imperators
	 */
	public List<Imperator> getImperators() {
		return imperators;
	}

	/**
	 * @param imperators the imperators to set
	 */
	public void setImperators(List<Imperator> imperators) {
		this.imperators = imperators;
	}
}
