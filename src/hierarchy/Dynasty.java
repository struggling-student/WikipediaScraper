package hierarchy;

import java.util.ArrayList;
import java.util.List;

import scraper.WikipediaClass;

public class Dynasty {

	private String name;
	private List<Imperator> imperators = new ArrayList<Imperator>();
	private String url;
	
	/**
	 * 
	 * @param url
	 * @return the name extracted from the url (from "https://it.wikipedia.org/wiki/Imperatori_romani" returns "Imperatori romani")
	 */
	public static String getNameFromUrl(String url) {return url.replace(WikipediaClass.URLBEGIN, "").replace("_", " ");}
	
	public Dynasty() 
	{
		
	}

	/**Creates the object Dynasty
	 * 
	 * @param dynasty that is the a element from wikipedia Roman Imperators Webpage
	 */
	public Dynasty(String name, String url) 
	{
		this.name = name;
		this.url = url;
	}
	/**
	 * Adds the Imperator given in input to the list imperators
	 * @param imp Imperator
	 */
	public void addImperator(Imperator imp) {this.imperators.add(imp);}
	
	public List<Imperator> getImperators() {return this.imperators;}

	/**
	 * @return the name of the dynasty
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name of the dynasty to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url of the dynasty
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url of the dynasty to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	

}

