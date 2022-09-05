package scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import org.openqa.selenium.By;


public class WikipediaClass {
	
	private String url = "https://it.wikipedia.org/wiki/Imperatori_romani";
	private WebDriver driver;
	//private WebDriver initialDriver;
	
	public static final String URLBEGIN = "https://it.wikipedia.org/wiki/";

	/**
	 * creates an object WikipediaClass opening Wikipedia Roman Imperators webpage 
	 */
	public WikipediaClass() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get(url);
	//	this.initialDriver = this.driver;
	}
		
	/**
	 * 
	 * @return driver
	 */
	public WebDriver getDriver() {return this.driver;}

	/**
	 * maximizes the window
	 */
	public void maximizeWindow() 
	{
		this.driver.manage().window().maximize();
	}
	
	
	/**searches the parameter in the wikipedia webpage
	 * 
	 * @param research
	 */
	public void search(String research) 
	{
		
		WebElement search = this.driver.findElement(By.name("search"));
		search.sendKeys(research);
		
		WebElement searchButton = this.driver.findElement(By.name("go"));
		searchButton.click();
	}
	
	/**
	 * 
	 * @return list of all the tables from the wikipedia webpage 
	 */
	public List<WebElement> getTables() 
	{
		List<WebElement> tables = this.driver.findElements(By.className("wikitable"));
		return tables;
	}
	
	/**
	 * webpage goes back to previous page 
	 */
	
	public void goBack() {this.driver.navigate().back();}
	
	/**
	 * refreshes the webpage
	 */
	public void refresh() {this.driver.navigate().refresh();}
		

		
}
	
	
	
