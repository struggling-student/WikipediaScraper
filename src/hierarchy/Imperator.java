package hierarchy;

import org.openqa.selenium.WebElement;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Imperator extends Person {
	
	public static final String PREDECESSORPATH = "Predecessore";
	public static final String SUCCESSORPATH = "Successore";
	public static final String DYNASTYPATH = "Dinastia";

		

	private Imperator predecessor = null;
	private List<Imperator> successor = new ArrayList<Imperator>();
	private String dynasty = null;
	
	/**
	 * 
	 * @param url of the imperator wikipedia webpage
	 * @param driver of wikipedia 
	 * @throws MalformedURLException
	 */
	public Imperator(String url, WebDriver driver) throws MalformedURLException 
	{
		super(url, driver); 	
	}

	/**
	 * fills in all instance variables that can be filled in 
	 */
	public void fillInstanceVariables(List<Imperator> imperatorList) 					///////////////////////////////
	{
		super.searchMe();
		try {

			super.fillName();
			this.findTr(this.getBody(), imperatorList);

		}

		catch (Exception e) {}
	}

	private void findTr(WebElement body, List<Imperator> imperatorList) throws Exception {
		
		List<WebElement> trS = super.iterateOnBody(body);
		for (WebElement tr : trS) {
			try {
			String text = tr.findElement(By.tagName("th")).getText();							//////////////////////////////

			if (super.checkSpouse(text)) {super.fillSpouse(tr.findElement(By.tagName("td")));}
			else if (super.checkMother(text)) {super.fillMother(tr.findElement(By.tagName("td")));}
			else if (super.checkFather(text)) {super.fillFather(tr.findElement(By.tagName("td")));}
			else if (super.checkSons(text)) {super.fillSons(tr.findElement(By.tagName("td")));}
			else if (this.checkPredecessor(text)) {this.fillPredecessor(tr.findElement(By.tagName("td")), imperatorList);}
			else if (this.checkDynasty(text)) {this.fillDynasty(tr.findElement(By.tagName("td")));}

			}
			catch (Exception e) {}
		}
		
	}
	
	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to SUCCESSORPATH	
	 */
	public boolean checkSuccessor(String text) 
	{
		if (text.equals(SUCCESSORPATH)) {
			return true;
		}
		else {return false;}
	}
	
	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to PREDECESSORPATH	
	 */
	public boolean checkPredecessor(String text) 
	{
		if (text.equals(PREDECESSORPATH)) {
			return true;
		}
		else {return false;}
	}
	
	/**
	 * 
	 * @param text that is the th WebElement style
	 * @return true if the text corresponds to DYNASTYPATH	
	 */
	public boolean checkDynasty(String text) 
	{
		if (text.equals(DYNASTYPATH)) {
			return true;
		}
		else {return false;}
	}
	/*
	 * fills in predecessor instance variable
	 */
	public void fillPredecessor(WebElement predecessorElement, List<Imperator> imperatorList) throws Exception
	{

		String[] urlandName = super.extractPeople(predecessorElement).get(0);
		String stringUrlName = urlandName[0];		
		for(Imperator pred : imperatorList) {
			
			if(pred.getUrl().equals(stringUrlName)) {									////////////////////////////cerca nella lista degli imperatori gia ispezionati il predecessore
				pred.addSuccessor(this);	//questa istanza di imperator si aggiunge come successore all'istanza dell'imperatore che è anche suo predecessore
				this.setPredecessor(pred);
			}
		}
	}


	/*
	 * fills in dynasty instance variable
	 */
	public void fillDynasty(WebElement dynastyElement) throws Exception
	{
		String dynasty = super.extractPeople(dynastyElement).get(0)[0];
		this.setDynasty(dynasty);
	}
	/**
	 * @return the predecessor
	 */
	public Imperator getPredecessor() {
		return predecessor;
	}


	/**
	 * @param predecessor the predecessor to set
	 */
	public void setPredecessor(Imperator predecessor) {
		this.predecessor = predecessor;
	}


	/**
	 * @return the successor
	 */
	public List<Imperator> getSuccessors() {
		return successor;
		
	}


	/**
	 * @param successor the successor to set
	 */
	public void addSuccessor(Imperator successor) {
		this.successor.add(successor);
	}

	/**
	 * @return the dynasty
	 */
	public String getDynasty() {
		return dynasty;
	}


	/**
	 * @param dynasty the dynasty to set
	 */
	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}
}
