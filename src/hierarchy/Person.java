package hierarchy;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import scraper.WikipediaClass;

public class Person {

	public static final String NAMEPATH = "//*[@id=\"firstHeading\"]";
	public static final String SPOUSEPATH1 = "Coniuge";
	public static final String SPOUSEPATH2 = "Consorte";
	public static final String MOTHERPATH = "Madre";
	public static final String FATHERPATH = "Padre";
	public static final String SONSPATH = "Figli";

	private String url;
	private WebDriver driver;
	private String name;
	private List<Person> spouse = new ArrayList<Person>();
	private List<Person> mother = new ArrayList<Person>();
	private List<Person> father = new ArrayList<Person>();
	private List<Person> sons = new ArrayList<Person>();

	public Person(String url, WebDriver driver) throws MalformedURLException {
		this.url = url;
		this.driver = driver;

	}

	/**
	 * 
	 * @return driver
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * opens the wikipedia webpage of the imperator object
	 */
	public void searchMe() {
		this.driver.get(this.url);
	}

	
	/**
	 * 
	 * 
	 * @return the body WebElement object of the person 
	 */
	public WebElement getBody() {
		try {
			return this.driver
					.findElement(By.cssSelector("#mw-content-text > div.mw-parser-output > table.sinottico > tbody"));
		}

		catch (Exception e) {
		}

		return this.driver.findElement(By.cssSelector("#mw-content-text > div.mw-parser-output > table.sinottico"));
	}

	/**
	 * fills in all instance variables that can be filled in
	 */
	public void fillInstanceVariables() {
		this.searchMe();
		this.fillName();
		try {
			this.findTr(this.getBody());

		} catch (Exception e) {
		}

	}

	/*
	 * fills in name (instance variable)
	 */
	public void fillName() {
		try {
			String text = this.driver.findElement(By.xpath(NAMEPATH)).getText();
			this.setName(text);
		}

		catch (Exception e) {
			this.searchMe();
			this.fillName();
		}
	}

	/*
	 * fills in spouse instance variable
	 * @param spousesElement html list
	 */
	public void fillSpouse(WebElement spousesElement) throws Exception {
		List<String[]> spousesUrls = this.extractPeople(spousesElement);
		for (String[] urlName : spousesUrls) {
			Person p = new Person(urlName[0], this.driver);
			p.setName(urlName[1]);
			this.addSpouse(p);
		}
	}

	/*
	 * fills in mother instance variable
	 * @param motherElement html list
	 */
	public void fillMother(WebElement motherElement) throws Exception {

		List<String[]> mothersUrls = this.extractPeople(motherElement);
		for (String[] urlName : mothersUrls) {
			Person p = new Person(urlName[0], this.driver);
			p.setName(urlName[1]);
			this.addMother(p);
		}
	}

	/*
	 * fills in father instance variable
	 * @param fatherElement html list
	 */
	public void fillFather(WebElement fatherElement) throws Exception {

		List<String[]> fathersUrls = this.extractPeople(fatherElement);
		for (String[] urlName : fathersUrls) {
			Person p = new Person(urlName[0], this.driver);
			p.setName(urlName[1]);
			this.addFather(p);
		}
	}

	/*
	 * fills in sons instance variable
	 * @param sonsElement html list
	 */
	public void fillSons(WebElement sonsElement) throws Exception {

		List<String[]> sonsUrls = this.extractPeople(sonsElement);
		for (String[] urlName : sonsUrls) {
			Person p = new Person(urlName[0], this.driver);
			p.setName(urlName[1]);
			this.addSon(p);
		}

	}

	/**
	 * 
	 * 
	 * @param body
	 * @return List of all the Trs WebElement in the body
	 */
	public List<WebElement> iterateOnBody(WebElement body) {
		List<WebElement> trS = body.findElements(By.tagName("tr"));
		return trS;
	}

	/**
	 * finds all the instance variables that can be filled in and call the methods
	 * to fill in them
	 * 
	 * @param body of wikipedia webpage
	 * @throws Exception
	 */
	private void findTr(WebElement body) throws Exception {
		List<WebElement> trS = this.iterateOnBody(body);
		for (WebElement tr : trS) {
			try {
				String text = tr.findElement(By.tagName("th")).getText();
				if (this.checkSpouse(text)) {
					this.fillSpouse(tr.findElement(By.tagName("td")));
				} else if (this.checkMother(text)) {
					this.fillMother(tr.findElement(By.tagName("td")));
				} else if (this.checkFather(text)) {
					this.fillFather(tr.findElement(By.tagName("td")));
				} else if (this.checkSons(text)) {
					this.fillSons(tr.findElement(By.tagName("td")));
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to SPOUSEPATH1 OR SPOUSEPATH2
	 */
	public boolean checkSpouse(String text) {
		return text.equals(SPOUSEPATH1) | text.equals(SPOUSEPATH2);
	}

	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to SONSPATH
	 */
	public boolean checkSons(String text) {
		return text.equals(SONSPATH);
	}

	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to FATHERPATH
	 */
	public boolean checkFather(String text) {
		return text.equals(FATHERPATH);
	}

	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to MOTHERPATH
	 */
	public boolean checkMother(String text) {
		return text.equals(MOTHERPATH);
	}

	/**
	 * This method takes in input a td webelement of a specific tr webelement and
	 * extracts the people contained there (if there are)
	 * 
	 * @param td webelement of a specific tr webelement
	 * @return a list of Person objects of the given td webelement
	 * @throws MalformedURLException
	 */
	public List<String[]> extractPeople(WebElement td) throws Exception {
		List<String[]> extracted = new ArrayList<String[]>();
		try {
			List<WebElement> allA = td.findElements(By.tagName("a"));
			for (WebElement elt : allA) {
				String url = elt.getAttribute("href");
				String name = elt.getAttribute("title");
				int lun = WikipediaClass.URLBEGIN.length();
				String sub = url.substring(lun);
				char first = sub.charAt(0);
				if (!url.equals(this.url) && !Character.isDigit(first) && !url.contains("#")) {
					String[] urlName = new String[] { url, name };
					extracted.add(urlName);
				}

			}

			return extracted;
		} catch (Exception e) {
			System.out.println("there is nobody in that webelement or it doesn't exist");
			return extracted;
		}

	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url, the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name, the name to set
	 */
	public void setName(String name) {
		this.name = name.substring(0, name.indexOf('(') == -1 ? name.length() : name.indexOf('('));
	}

	/**
	 * @return the spouses 
	 */
	public List<Person> getSpouse() {
		return spouse;
	}

	/**
	 * @param spouse, the spouse to set
	 */
	public void setSpouse(List<Person> spouse) {
		this.spouse = spouse;
	}

	/**
	 * add a spouse from input to Person spouse list
	 * 
	 * @param Person spouse
	 */
	public void addSpouse(Person sp) {
		this.spouse.add(sp);
	}

	/**
	 * @return the sons
	 */
	public List<Person> getSons() {
		return sons;
	}

	/**
	 * @param sons, the son to set
	 */
	public void setSons(List<Person> sons) {
		this.sons = sons;
	}

	/**
	 * add a son from input to Person sons list
	 * 
	 * @param Person son
	 */
	public void addSon(Person son) {
		this.sons.add(son);
	}

	/**
	 * @return the mother
	 */
	public List<Person> getMother() {
		return mother;
	}

	/**
	 * @param mother the mother to set
	 */
	public void setMother(List<Person> mother) {
		this.mother = mother;
	}

	/**
	 * add a mother from input to Person mother list
	 * 
	 * @param Person mother
	 */
	public void addMother(Person mum) {
		this.mother.add(mum);
	}

	/**
	 * @return the father
	 */
	public List<Person> getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(List<Person> father) {
		this.father = father;
	}

	/**
	 * add a father from input to Person father list
	 * 
	 * @param Person father
	 */
	public void addFather(Person dad) {
		this.father.add(dad);
	}

}
