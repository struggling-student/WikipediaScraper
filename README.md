# WikipediaScraper [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
> Wikipedia Web Scraper was an Exam Project for the "Programming Methodologies" course of the first 
year at Computer Science - Sapienza University of Rome.

</div> 
<div align="center">
  <img src="https://img.shields.io/badge/Made%20with-Java-orange?style=for-the-badge&logo=Java">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">
  <img src="https://img.shields.io/badge/Overleaf-47A141?style=for-the-badge&logo=Overleaf&logoColor=white">
</div

[![Immagine1.png](https://i.postimg.cc/T3wzqQJQ/Immagine1.png)](https://postimg.cc/D89p7rDb)

> Project Requests and Installation Tutorial is in Italian.

## Traccia

Il seguente progetto verterà sulla creazione di uno o più alberi genealogici relativi a ciascuna dinastia di Imperatori Romani, con lo scopo di visualizzare le diverse relazioni parentali che intercorrono.

Poiché tale processo dovrà essere il più automatico possibile andando ad attingere da diverse fonti dati presenti su Wikipedia, ai fini della realizzazione

dovrà essere impiegata la tecnica del web scraping. Essa sarà necessaria per

l’ottenimento delle informazioni relative alle diverse interazioni familiari di ciascun membro delle dinastie. L’infografica rappresenta l’albero genealogico della dinastia Giulio-Claudia con le relazioni parentali che intercorrono tra i diversi membri. Il nome dei sovrani è descritto tramite una formattazione in grassetto del loro nome, insieme alla data di inizio e fine regno.

Da un punto di vista tecnico, il progetto richiederà l’utilizzo di Selenium, un framework open-source multipiattaforma impiegato per il testing applicazioni web e, come nel nostro caso, per effettuare operazioni di web scraping.

A livello di specifiche, il progetto utilizzerà il web scraping e le interfacce grafiche
al fine di:
- Ottenere gli Imperatori di ciascuna Dinastia, partendo dalla pagina di
Wikipedia fornita all’inizio della descrizione.
- Visitare la pagina di ogni imperatore di ciascuna dinastia al fine di prelev-
are le sue relazioni di parentela. Ad esempio, riferendosi alla pagina Wiki-
pedia di Augusto.
- Creare un albero genealogico per ciascuna dinastia mediante gli strumenti
grafici visti a lezione.

## Struttura
### Web Scraper
- Hierarchy : Package che racchiude le tre classi Dynasty, Imperator e Person le quali gestiscono
le gerarchie degli imperatori.
  -  Dynasty : La classe Dynasty è stata realizzata per unire tutti gli imperatori di una stessa
dinastia in un unico oggetto.
  -  Person : La classe Person si occupa di gestire tutte le persone quali madri, padri, figli che
sono collegate agli imperatori, inoltre essa sarà estesa dalla classe Imperator.
  -  Imperator : La classe estende la classe Person, aggiungendo agli attributi della classe Person
altri due: Predecessor e Sucessor.
- Table : La classe Table serve per estrarre e gestire tutti gli oggetti Wikitable che sono nella
pagina HTML di Wikipedia contenenti le dinastie con i rispettivi imperatori.
- WikipediaClass : La classe gestisce l’apertura di Chrome, il collegamento a Wikipedia ed estrae tutte.
le tabelle delle dinastie dalla pagina principale degli Imperatori Romani.
- WikipediaScraperTester : La classe testa il funzionamento dello Scraper, e attraverso una serie di print per ogni Imperatore verifica il funzionamento del codice.

### Interfaccia
- Line : Lo scopo della classe Line è quella di costruire la linea che poi viene tracciata nell’
ImperatorPanel.
- Point : Lo scopo della classe Point è quello di determinare le coordinate del punto(X,Y).
- PersonMouseAdapter : Lo scopo della classe PersonMouseAdapter è quello di aprire la pagina Web di una persona quando si clicca su di essa.
- ImperatorScraper : La classe ImperatorScraper è la parte principale del progetto in cui viene avviata e gestita l’intera applicazione costruendo passo per passo la struttura dati contenente
i dati raccolti e la struttura grafica per rappresentarli.
- ImperatorCard : Lo scopo della classe ImperatorCard è quello di andare a disegnare a partire
dall’imperatore tutta la dinastia, facendo attenzione alla tipologia di successore(figlio
o meno) e formattando sull’interfaccia le posizioni delle mogli e dei figli.
- ImperatorPanel : Lo scopo della classe ImperaratorPanel è quella di avere un pannello sul quale
andare a disegnare le linee e le varie etichette.
- PersonCard : Lo scopo della classe PersonCard è quello di definire un’opportuna rappresentazione
grafica dei dati raccolti di ogni persona, in modo da renderizzare una visuale chiara
utilizzando delle etichette contenenti il nome.

## Guida installazione libreria Selenium

1. Scaricare la versione di Selenium più recente dal [sito](https://www.selenium.dev/downloads/) 
oppure cliccare direttamente il [seguente link](https://www.google.com/url?sa=D&q=https://selenium-release.storage.googleapis.com/3.141/selenium-java-3.141.59.zip&ust=1667419800000000&usg=AOvVaw0Kwa9J7MYcbiUUwBjM9eJ9&hl=it).
2. Creare il Java Project nel quale si vuole utilizzare la libreria.
3. Cliccare con il tasto destro del mouse sul progetto, andare nella sezione Built Path e cliccare su Configure Built Path.
4. Cliccare su Java Built Path nella tendina di sinistra.
5. Selezionare Class Path e cliccare su Add External JARs.
6. Aprire la cartella in cui è presente lo zip appena scaricato (da cui devono essere stati estratti i file).
7. Aprire la cartella e selezionare tutti i file interni.
8. Ripetere il punto e selezionare i rimanenti Executable Jar File.
9. Cliccare su Apply and Close.
## Guida per usare lo Scraper

1. Modificare nella classe ImperatorScraper il path nella seguente riga:
  - ```System.setProperty(”webdriver.chrome.driver”,”CHANGE ME");```
  - con il proprio percorso a ”chromedriver.exe”.
2. Modificare nella classe WikipediaClass il path nella seguente riga:
  - ```System.setProperty(”webdriver.chrome.driver”,”CHANGE ME”);```
  - con il proprio percorso a ”chromedriver.exe”.
3. Modificare nella classe WikipediaScraperTester il path nella seguente riga:
  - ```System.setProperty(”webdriver.chrome.driver”,”CHANGE ME”);```
  - con il proprio percorso a ”chromedriver.exe”.
> Se non si dispone di questo Driver, seguire le seguenti istruzioni
  - Verificare sul proprio PC la versione attuale di Chrome:
    - Aprire una pagina Google
    - Cliccare i tre punti in alto a destra e selezionare l'opzione Guida.
    - Cliccare su Informazione su Google Chrome.
    - Si aprirà una pagina con la versione attuale del vostro Chrome.
  - Accedere al [seguente link](https://chromedriver.chromium.org/downloads).
  - Scaricare la versione corrispondente alla vostra.
  - Scegliere il sistema operativo.
  - Estrarre il file zip scaricato.
  - A questo punto inserire il percorso del file appena scaricato all'interno dei punti 1,2,3 nelle rispettive classi.
4. Aprire la classe ImperatorScraper ed eseguirla.
5. Attendere che vengano aperte le pagine Wikipedia di ogni Imperatore in modo da formare i rispettivi alberi.
6. Alla fine del caricamento cliccare sull’icona di Java presente sulla barra delle applicazioni.
7. A questo punto verrà visualizzata un interfaccia contenente tutte le Dinastie.
8. Cliccare sul nome della Dinastia che si vuole visualizzare.
9. Una volta entrati all’interno di un albero, per torna alla pagina iniziale bisognerà cliccare il pulsante in basso.
