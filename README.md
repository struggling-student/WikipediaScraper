# WikipediaScraper

Wikipedia Web Scraper was an Exam Project for the "Programming Methodologies" course of the first 
year at Computer Science - Sapienza University of Rome.

# Project requests

The project was 

> Tutorial is in Italian, an english version will be added soon.

# Tutorial - ReadME file.

## Guida installazione libreria Selenium

1. Scaricare la versione di Selenium più recente dal sito: https://www.selenium.dev/downloads/
oppure cliccare direttamente il seguente link.
2. Creare il Java Project nel quale si vuole utilizzare la libreria.
3. Cliccare con il tasto destro del mouse sul progetto, andare nella sezione Built Path e cliccare su Configure Built Path.
4. Cliccare su Java Built Path nella tendina di sinistra.
5. Selezionare Class Path e cliccare su Add External JARs.
6. Aprire la cartella in cui è presente lo zip appena scaricato (da cui devono essere stati estratti i file).
7. Aprire la cartella e selezionare tutti i file interni.
8. Ripetere il punto e selezionare i rimanenti Executable Jar File.
9. Cliccare su Apply and Close.
## Guida per usare lo Scraper

1. Modificare nella classe ImperatorScraper il path nella seguente riga:```System.setProperty(”webdriver.chrome.driver”,”CHANGE ME");```con il proprio percorso a ”chromedriver.exe”.
2. Modificare nella classe WikipediaClass il path nella seguente riga:```System.setProperty(”webdriver.chrome.driver”,”CHANGE ME”);```con il proprio percorso a ”chromedriver.exe”.
3. Modificare nella classe WikipediaScraperTester il path nella seguente riga:```System.setProperty(”webdriver.chrome.driver”,”CHANGE ME”);```con il proprio percorso a ”chromedriver.exe”.
4. Aprire la classe ImperatorScraper ed eseguirla.
5. Attendere che vengano aperte le pagine Wikipedia di ogni Imperatore in modo da formare i rispettivi alberi.
6. Alla fine del caricamento cliccare sull’icona di Java presente sulla barra delle applicazioni.
7. A questo punto verrà visualizzata un interfaccia contenente tutte le Dinastie.
8. Cliccare sul nome della Dinastia che si vuole visualizzare.
9. Una volta entrati all’interno di un albero, per torna alla pagina iniziale bisognerà cliccare il pulsante in basso.
