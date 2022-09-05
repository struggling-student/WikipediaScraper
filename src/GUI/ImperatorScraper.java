package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hierarchy.Dynasty;
import hierarchy.Imperator;
import scraper.Table;
import scraper.WikipediaClass;

/**
 * This is the main class of the UI.
 * It starts the graphical interface and stores the important JPanels
 * Used by the other classes.
 * 
 *
 */
public class ImperatorScraper {
	/**
	 * Main frame of the application. It's layout uses the BorderLayout of swing
	 */
	private JFrame frmImperators;
	/**
	 * Panel where there will be the buttons of the dynasties. It uses the grid layout of swing with 2 rows.
	 */
	private JPanel panel;
	/**
	 * Field where is stored the current Panel used to display the Cards of the imperators and the people related to them with all the connection between them.
	 * It uses x and y to display the Cards and the connections.
	 */
	protected ImperatorPanel viewPanel;
	/**
	 * This view contains the viewPanel and is useful for the scroll.
	 */
	protected JScrollPane scrollView;
	
	/**
	 * Launch the application.
	 * this function starts the gui by instantiating a new ImperatorScraper
	 */
	protected JLabel lblNewLabel;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImperatorScraper window = new ImperatorScraper();
					window.frmImperators.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImperatorScraper() {
		initialize();
	}

	/**
	 * Method called during the initialization of an ImperatorScraper.
	 * It first initializes the main frame, the panel where there will be the buttons and the panel of the title.
	 * It then starts the search on the wiki to collect informations about the Imperator in order to be able to display
	 * the dynasties.
	 * 
	 */
	private void initialize() {
		// new main frame
		frmImperators = new JFrame();
		frmImperators.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		// new panel for the buttons
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 50, 50, 50));
		panel.setBackground(SystemColor.controlHighlight);
		frmImperators.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		// panel of the title
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(10, 100));
		frmImperators.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		// new label with the title
		lblNewLabel = new JLabel("Imperators");
		lblNewLabel.setFont(new Font("Engravers MT", Font.PLAIN, 70));
		
		//adding the label to the panel and setting the title of the window
		panel_1.add(lblNewLabel);
		frmImperators.setTitle("Imperators");
		frmImperators.setBounds(100, 100, 1174, 658);
		frmImperators.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// getting informations about the tables.
		// here the scraper starts the search on the wiki and
		// stores the informations of the imperators.
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WikipediaClass wiki = new WikipediaClass();
		wiki.maximizeWindow();
		
		// list of the tables on the wiki page
		List<WebElement> tables = wiki.getTables();
		// list of dynasties found
		List<Dynasty> dynasties = new ArrayList<Dynasty>();
		// list of Table instantiated by the information retreived by the table list ('tables')
		List<Table> tableList = new ArrayList<Table>();
		
		WebDriver driver = wiki.getDriver();
		int lun = tables.size()-1;
		
		// finding tables and tableList
		for (int i = 0; i<=lun; i++) {
			Table t = new Table(tables.get(i), driver);
			t.fillImperators();			
			tableList.add(t);
		}
		
		// here the scraper goes inside each imperator and gets the information about him by using the method fillinstanceVariables().
		// it also creates the dynasties and adds the imperators inside them.
		for (int i = 0; i<=lun; i++) {
			Table t = tableList.get(i);
			boolean condition = true;
			List<Imperator> imperators = t.getImperators();
			for (Imperator imp : imperators) {
				imp.fillInstanceVariables(imperators);
				if (imp.getDynasty() != null && condition) {
					condition = false;
					Dynasty d = new Dynasty(imp.getDynasty(), imp.getDynasty());
					d.addImperator(imp);
					System.out.println("Added " + imp.getName() + " to " + d.getName());
					dynasties.add(d);
				}
				else if(!condition) {
					dynasties.get(dynasties.size() - 1).addImperator(imp);
					System.out.println("Added " + imp.getName() + " to " + dynasties.get(dynasties.size() - 1).getName());
				}
			}
		}
		
		// now the ui has to display a button foreach dynasty the scraper found.
		// Every dinasty will have a button in the ui with a ButtonMouseAdapter(Dynasty)
		// related. what the adapter does is: hiding the panel with the buttons, creating a
		// new ImperatorPanel, adding the new panel to the scrollView, creating a bar located
		// at the bottom where to display the 'undo' button, create and draw the first
		// ImperatorCard.
		// What the undo button does when it's clicked is to remove all the panels related to the
		// current dynasty and set visible the buttons of the dynasties
		for(Dynasty dynasty : dynasties) { 
			Button b = new Button();
			b.setLabel(Dynasty.getNameFromUrl(dynasty.getName()));
			panel.add(b);
			b.addMouseListener(new ButtonMouseAdapter(dynasty) {
				@Override
				public void mouseClicked(MouseEvent e) {
					panel.setVisible(false);
					lblNewLabel.setText(Dynasty.getNameFromUrl(dynasty.getName()).replace('_', ' '));
					// frmImperators.getContentPane().remove(panel);
					viewPanel = new ImperatorPanel();
					viewPanel.setBackground(Color.lightGray);
					viewPanel.setLayout(null);
					viewPanel.setPreferredSize(new Dimension(4000, 4000));
					scrollView = new JScrollPane(viewPanel);
					scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollView.getVerticalScrollBar().setUnitIncrement(20);
					//scrollView.add(viewPanel, BorderLayout.CENTER);
					frmImperators.getContentPane().add(scrollView, BorderLayout.CENTER);
					
					
					final JPanel undoPanel = new JPanel();
					JButton undoButton = new JButton();
					undoButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							scrollView.removeAll();
							scrollView.setVisible(false);
							frmImperators.getContentPane().remove(scrollView);
							frmImperators.getContentPane().remove(undoPanel);
							undoPanel.setVisible(false);
							panel.setVisible(true);
							frmImperators.getContentPane().add(panel, BorderLayout.CENTER);
							lblNewLabel.setText("Imperators");
						}
					});
					undoButton.setText("Indietro");
					undoPanel.add(undoButton);
					frmImperators.getContentPane().add(undoPanel, BorderLayout.SOUTH);
					Point p = new Point(100, 100);
					new ImperatorCard(this.dynasty.getImperators().get(0)).draw(p, p, viewPanel, driver);
				}
			});
		}
	}
}
/**
 * this class extends the Mouse adapter and is useful when the action that the button will do
 * needs a dynasty.
 *
 */
 class ButtonMouseAdapter extends MouseAdapter {
	protected Dynasty dynasty;
	
	public ButtonMouseAdapter(Dynasty dynasty) {
		super();
		this.dynasty = dynasty;
	}
	
	public Dynasty getDynasty() { return dynasty; }
}