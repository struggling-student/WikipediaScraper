package scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hierarchy.Dynasty;
import hierarchy.Imperator;
import hierarchy.Person;

public class WikipediaScraperTester {
	
	public static void main(String[] args) throws Exception 
	{
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WikipediaClass wiki = new WikipediaClass();
		wiki.maximizeWindow();
		
		List<WebElement> tables = wiki.getTables();
		List<Dynasty> dynasties = new ArrayList<Dynasty>();
		List<Table> tableList = new ArrayList<Table>();
		
		WebDriver driver = wiki.getDriver();
		int lun = tables.size()-1;
		
		for (int i = 0; i<=lun; i++) {
			Table t = new Table(tables.get(i), driver);
			t.fillImperators();
			
			
			tableList.add(t);
		}

		for (int i = 0; i<=lun; i++) {
			System.out.println("\n--------NUOVA TABELLA--------");

			Table t = tableList.get(i);
			boolean condition = true;
			List<Imperator> imperators = t.getImperators();
			for (Imperator imp : imperators) {
				imp.fillInstanceVariables();
				String dynastyName = imp.getDynasty();
				if (dynastyName != null && condition) {
					condition = false;
					Dynasty d = new Dynasty(imp.getDynasty(), imp.getDynasty());
					dynasties.add(d);
				}
				System.out.println("\n\n\n");
				System.out.println("il mio nome è : " + imp.getName());
				
				for (Person spouse : imp.getSpouse()) {	
					System.out.println("Questa è una mia coniuge : "+spouse.getName());}
				
				
				if (imp.getPredecessor() != null ) {
					System.out.println("Il mio predecessore è : " + imp.getPredecessor().getName());
				}
				
				
				for (Person dad : imp.getFather()) {
					System.out.println("Questo è mio padre : "+dad.getName());
				}
				for (Person mum : imp.getMother()) {
					System.out.println("Questa è mia madre : "+mum.getName());
				}
				for (Person son : imp.getSons()) {
					System.out.println("Questo è mio figlio : "+son.getName());
				}

			}
		}
		System.out.println("ENDED");
		if (dynasties.size() != 0) {
			for (Dynasty d : dynasties) {
			System.out.println(Dynasty.getNameFromUrl(d.getName()));
			}
		}
	}
		
}
