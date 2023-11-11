import java.text.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewBook extends JFrame {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField authorField;
	private JComboBox monthPublished;
    private JComboBox dayPublished;
	private JTextField yearPublished;
	private Library library;

	/**
	 * This frame asks for the details of a new book to be added into the 
	 */
	public NewBook(Library library) {
		this.library = library;
		setTitle("New Book");       
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titleField = new JTextField();
		titleField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		titleField.setBounds(10, 32, 188, 23);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		authorField = new JTextField();
		authorField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		authorField.setBounds(10, 88, 188, 23);
		contentPane.add(authorField);
		authorField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 87, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthors = new JLabel("Author/s:");
		lblAuthors.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		lblAuthors.setBounds(10, 63, 87, 14);
		contentPane.add(lblAuthors);
		
		JLabel lblDatePublished = new JLabel("Date Published(mm/dd/yyyy): ");
		lblDatePublished.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		lblDatePublished.setBounds(233, 7, 229, 23);
		contentPane.add(lblDatePublished);
		
		Integer[] months = new Integer[13];
		for (int i = 0; i != months.length ; i++) {
			months[i] = i;
		}
		DefaultComboBoxModel<Integer> cmbMonthsModel = new DefaultComboBoxModel<Integer>(months);
		monthPublished = new JComboBox(cmbMonthsModel);
		monthPublished.setBounds(232, 33, 79, 22);
		contentPane.add(monthPublished);
		
		Integer[] days = new Integer[32];
		for (int i = 0; i != days.length ; i++) {
			days[i] = i;
		}
		DefaultComboBoxModel<Integer> cmbDaysModel = new DefaultComboBoxModel<Integer>(days);
		dayPublished = new JComboBox(cmbDaysModel);
		dayPublished.setBounds(321, 33, 79, 22);
		contentPane.add(dayPublished);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		lblCategory.setBounds(10, 119, 87, 20);
		contentPane.add(lblCategory);
		
		JComboBox cmbGenre = new JComboBox();
		cmbGenre.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		cmbGenre.setModel(new DefaultComboBoxModel(new String[] {"Romance", "Mystery", "Thriller", "Science fiction ", "Fantasy", "Horror", "Historical fiction", "Biography", "Self-help", "Wellness & health", "True crime", "Travel"}));
		cmbGenre.setBounds(10, 150, 188, 22);
		contentPane.add(cmbGenre);
		
		JComboBox cmbFiction = new JComboBox();
		cmbFiction.setModel(new DefaultComboBoxModel(new String[] {"Fiction", "Non-Fiction"}));
		cmbFiction.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		cmbFiction.setBounds(10, 183, 188, 22);
		contentPane.add(cmbFiction);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		lblLanguage.setBounds(233, 60, 87, 20);
		contentPane.add(lblLanguage);
		
		JComboBox cmbLanguage = new JComboBox();
		cmbLanguage.setModel(new DefaultComboBoxModel(new String[] {"English", "Filipino", "Cebuano"}));
		cmbLanguage.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		cmbLanguage.setBounds(233, 89, 188, 22);
		contentPane.add(cmbLanguage);
		
		yearPublished = new JTextField();
		yearPublished.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		yearPublished.setBounds(410, 34, 86, 20);
		contentPane.add(yearPublished);
		yearPublished.setColumns(10);
		
		JButton addBtn = new JButton("Add Book");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String title, authors, category, language;
				 Date dateAdded, datePublished;
				 boolean fiction;
				 
				if (titleField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Title Input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
				else if (authorField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Author Input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				} else {
		
				 title = titleField.getText();
				 authors = authorField.getText();
				 category = cmbGenre.getSelectedItem().toString();
				 if(cmbFiction.getSelectedItem().equals("Fiction")) {
					 fiction = true;
				 } else {
					 fiction = false;
				 }
				 language = cmbLanguage.getSelectedItem().toString();
				 datePublished = new Date(
                         Integer.parseInt(yearPublished.getText()) - 1900,
                         Integer.parseInt(monthPublished.getSelectedItem().toString()) - 1,
                         Integer.parseInt(dayPublished.getSelectedItem().toString())
                 );

                 // Format the date as a string
                 SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                 Date currentDate = new Date();
               
                 
                 Book newBook = new Book(title,  authors, category,  fiction,  language, datePublished, currentDate  );
                 library.addBook(newBook);
                 dispose();
                 MainUi mainUi = new MainUi(library);
                 mainUi.setVisible(true);
                 
                 System.out.println("---Book Info---\n" + "Title\t\t: " + title + "\nAuthor\t\t: " + authors + "\nCategory\t:"+ category + "\nFiction\t\t: " + fiction + "\nLanguage\t: " + language + "\nDate Published\t: " +
                dateFormat.format( datePublished) + "\nDate Added\t: " + dateFormat.format(currentDate));
				}
			}
		});
		addBtn.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		addBtn.setBounds(233, 183, 116, 23);
		contentPane.add(addBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainUi mainUi = new MainUi(library);
				mainUi.setVisible(true);
			}
		});
		cancelBtn.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		cancelBtn.setBounds(359, 183, 116, 23);
		contentPane.add(cancelBtn);
	}

}
