import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

//Most GUI components were made with the help of WindowBuilder
public class MainUi extends JFrame {

	private JPanel contentPane;
	private JTable table; // Table that displays the catalog
	private JMenuBar menuBar = new JMenuBar(); 
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library library = new Library();  // Create a Library instance
	                MainUi mainUiFrame = new MainUi(library); // Create MainUi instance 
	                mainUiFrame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * MainUi accepts an object library parameter, as the methods form the library class 
	 * will be called on a multitude of times during the usage of the application
	 */
	
	public MainUi(Library library) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1332, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("My Book Buddy");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creating Menu Bar
		setJMenuBar(menuBar);
		
		// Creating Menus and MenuItems for the menuBar
		
		// Sort By
		JMenu sortMenu = new JMenu("Sort By");
		
		//Sort Recent - uses the bookID attribute to sort the books 
		JMenuItem sortRecent = new JMenuItem ("Recently Added");
		sortRecent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.sortByRecent();
				refreshTable(library.getCatalog());
				return;
			}
		});
		
		//Sorts by year published  - asks for a year input and only displays books that were published that year
		JMenuItem sortYear = new JMenuItem ("By Year Published");
		sortYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Year:"));
				List<Book> display = library.sortByYear(year);
				MainUi.this.refreshTable(display);
				return;
			}
		});
		
		//Sort Alphabetically - 2 types, decreasing (A - Z) and increasing (Z - A)
		JMenu sortAlphabetically = new JMenu ("Alphabetically");
		
		//Sort alphabetically A- Z
		JMenuItem sortAtoZ = new JMenuItem("A-Z");
		sortAtoZ.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				library.sortAlphabeticallyAZ();
				refreshTable(library.getCatalog());
				return;
			}
		});
		
		// Sort Alphabetically Z - A
		JMenuItem sortZtoA = new JMenuItem("Z-A");
		sortZtoA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.sortAlphabeticallyZA();
				refreshTable(library.getCatalog());
				return;
			}
		});
		
		// Adding Menu Items to Sort Alphabetically menu
		sortAlphabetically.add(sortAtoZ);
		sortAlphabetically.add(sortZtoA);
		
		//Sort by language - catalog accepts only English, Filipino, and Cebuano books
		JMenu sortLanguage = new JMenu ("Language");
		
		//Sorts all books that are English
		JMenuItem sortEnglish = new JMenuItem("English");
		sortEnglish.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        List<Book> englishBooks = library.sortByLanguage("English");
		        refreshTable(englishBooks); 
		    }
		});
		
		//Sorts all books that are Filipino
		JMenuItem sortFilipino = new JMenuItem("Filipino");
		sortFilipino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  List<Book> filipinoBooks = library.sortByLanguage("Filipino");
				  refreshTable(filipinoBooks); 
			}
		});
		
		
		//Sorts all books that are Cebuano
		JMenuItem sortCebuano = new JMenuItem("Cebuano");
		sortCebuano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Book> cebuanoBooks = library.sortByLanguage("Cebuano");
				refreshTable(cebuanoBooks); 
			}
		});
		
		//Adding menu items to Sort By Language
		sortLanguage.add(sortEnglish);
		sortLanguage.add(sortFilipino);
		sortLanguage.add(sortCebuano);
		
		//Sort by Availability - sorts books that are available, rented, or overdue
		JMenu sortAvailability = new JMenu ("Availability");
		
		// Displays books that are available
		JMenuItem sortAvailable = new JMenuItem ("Available");
		sortAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				List<Book> availableBooks = library.sortByAvailability("Available");
				refreshTable(availableBooks);
			}
		});
		
		
		//Displays books that are rented
		JMenuItem sortRented	= new JMenuItem ("Rented");
		sortRented.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Book> rentedBooks = library.sortByAvailability("Rented");
				refreshTable(rentedBooks);
			}
		});
		
		//Displays books that are overdue
		JMenuItem sortOverdue	= new JMenuItem ("Overdue");
		sortOverdue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Book> rentedBooks = library.sortByAvailability("Overdue");
				refreshTable(rentedBooks);
			}
		});
		sortAvailability.add(sortAvailable);
		sortAvailability.add(sortRented);
		sortAvailability.add(sortOverdue);
		
		//Adding all menu items that belong in Sort By
		sortMenu.add(sortRecent);
		sortMenu.add(sortYear);
		sortMenu.addSeparator();
		sortMenu.add(sortAlphabetically);
		sortMenu.add(sortLanguage);
		sortMenu.add(sortAvailability);
		
		// Changes the availability of the book into rented or available and calculates the due dates as needed
		 JMenuItem changeAvailability = new JMenuItem("Change Availability");
	        changeAvailability.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table.getSelectedRow();

	                if (selectedRow != -1) {
	                    //Get selected book in the table
	                    Book selectedBook = library.getCatalog().get(selectedRow);

	                    // If the book is available, change to rented and vice versa
	                    if (selectedBook.getAvailability().equals("Available")) {
	                    	
	                        // Book is available, set to rented then update the dates (rented and due dates)
	                        selectedBook.setAvailability("Rented");
	                        selectedBook.setDateRented(new Date());  // Set to current date
	                    } else {
	                    	
	                        // Book is rented, set to available meaning that the book has been returned
	                        selectedBook.setAvailability("Available");
	                        selectedBook.setDateRented(null);  // Reset date rented
	                    }

	                    // Update the due date
	                    selectedBook.setDateDue(new Date());

	                    // Refresh the table
	                    refreshTable(library.getCatalog());
	                }
	            }
	        });
		
		// Deletes a book from the catalog permanently
		JMenuItem deleteBook = new JMenuItem("Delete Book");
        deleteBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Gets selected book
                int selectedRow = table.getSelectedRow();
                
                // Confirms if the user wants to delete the book
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog( null,"Are you sure you want to delete this book?","Delete Book",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE );
                    
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        // If yes, remove the selected book from the catalog
                        Book selectedBook = library.getCatalog().get(selectedRow);
                        library.removeBook(selectedBook);

                        // Refresh the table
                        refreshTable(library.getCatalog());
                    }
                    
                } else { // If no book is selected, asks for input
                	JOptionPane.showMessageDialog(null, "Please Select a Book", "No Selected Book", JOptionPane.OK_OPTION);
                }
            }
        });
		
        // Adds a new book to the catalog
		JMenuItem addNew = new JMenuItem(new AbstractAction("Add New") {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Confirmint that the user wants to add a new book
				int confirm = JOptionPane.showConfirmDialog(null, "Add new book to the catalog?", "Add Book", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					//if Yes hide current frame then open the newBook frame
					setVisible(false);
					NewBook newBook = new NewBook(library);
					newBook.setVisible(true);
				}
			}
		});
		
		//Adding all menu items to main menu bar
		menuBar.add(sortMenu);
		menuBar.add(changeAvailability);
		menuBar.add(deleteBook);
		menuBar.add(addNew);
		
		//Creating scrollPane to display frame components
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1316, 455);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		table.setName("Catalog");
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new String[] {
					"Title", "Author/s", "Category", "Language", "Availability", "Date Published", "Date Rented", "Date Due", "Date Added", "Book ID"
				
		});
		
		//resizing table columns to get an ideal output
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(25);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(25);
		
		//when frame is first shown, the table is refreshed with the current library catalog
		refreshTable(library.getCatalog());

		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
	}
	
	//Refereshes the table with the updated catalog
	private  void refreshTable(List<Book> sortedBooks) {
		
		// Resets table model
		model.setRowCount(0);
		
		//Initiates an object array that will be assigned with the attributes of the books
		Object[] books;
		
		//Console output to confirm the current numbers of books in the catalog (not the numbers of books displayed)
		System.out.println("Number of Books in Catalog \t" + sortedBooks.size());
		
		//gets the information of each book in the catalog and displays them in their respective collumns
		for(int i = 0; i < sortedBooks.size(); i ++) {
			books = new Object[10];
			// using getter methods of books to get each attribute
			books[0] = sortedBooks.get(i).getTitle();
			books[1] = sortedBooks.get(i).getAuthors();
			books[2] = sortedBooks.get(i).getCategory();
			books[3] = sortedBooks.get(i).getLanguage();
			books[4] = sortedBooks.get(i).getAvailability();
			books[5] = sortedBooks.get(i).getDatePublished();
			books[6] = sortedBooks.get(i).getDateRented();
			books[7] = sortedBooks.get(i).getDateDue();
			books[8] = sortedBooks.get(i).getDateAdded();
			books[9] = sortedBooks.get(i).getBookID();
			
			// adds info of the book into the table
			model.addRow(books);
		}
	}
}
