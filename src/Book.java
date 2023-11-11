import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book {

	private String title, authors, category, language;
	private int bookID;
	private  Date dateAdded, dateRented, dateDue, datePublished;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
	private boolean fiction, overdue = false, rented = false, available = true;
	
	// Constructors
	public Book (String title,  String author, String category, boolean fiction, String language, Date published,  Date added){
		addBook(title,  author,  category, fiction, language,published,  added);
		
	}
	
	public void addBook(String title,  String author, String category, boolean fiction, String language, Date published,  Date added) {
		this.title = title;
		this.authors = author;
		this.category = category;
		this.fiction = fiction;
		this.language = language;
		this.datePublished = published;
		this.dateAdded= added;
		
	}

	// Setter Methods
	public void setTitle(String title) { this.title=title; }
	public void setAuthor (String author) { this.authors = author; }
	public void setCategory (String category) { this.category = category; }
	public void setLanguage (String language) { this.language = language;}
	public void setDateAdded (Date dateAdded) { this.dateAdded = dateAdded; }
	public void setDateRented (Date dateRented) { this.dateRented = dateRented; }
	
	public void setDatePublished (Date datePublished) { this.datePublished = datePublished; }
	
	public void setAvailability (String status) { 
		Date currentDate = new Date();
		
		if (status.equals("Rented")) {
			this.rented = true;
			this.available = false;
			dateRented = currentDate;
			setDateDue(dateRented);
			return;
		}
		if (currentDate.after(dateDue) || status.equals("Overdue")) {
				this.overdue=true;
				return;
				
		}  if (status.equals("Available")) {
			this.rented = false;
			this.available = true;
			this.overdue = false;
		}
	}
	
	// Method that calculates the date in which the book has to be returned, meaning adding 30 days to the date that it was rented
	public void setDateDue (Date dateRented) {
		// uses Calendar class in order to add 30 days to the date rented of the book, then set this date to the due date of the book
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateRented);
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		this.dateDue = calendar.getTime();
	}

	public void setFiction (Boolean fiction) {this.fiction = fiction; }
	public void setBookID (int iD) {this.bookID = iD; }
	
	// Getter Methods
	public String getTitle() {return title; }
	public String getAuthors() {return authors;} 
	public String getCategory() { return category;}
	public String getLanguage() { return language; } 
	public String getDateAdded() { return dateFormat.format(dateAdded); }
	
	public String getDateRented() {
		if (available == true && rented == false) {
			return "Available";
		}
		return dateFormat.format(dateRented); 
	}
	public String getDateDue() {
		if (available == true && rented == false) {
			return "Available";
		} else {
		return  dateFormat.format(dateDue); }
	}
	
	public String getDatePublished() { return dateFormat.format(datePublished);}
	public String getBookID() { return String.format("%04d", bookID); } 
	public Boolean isFiction() { return fiction; }
	
	public String getAvailability() {
		String hold = " ";
		
		if (rented == true) {
			hold = "Rented";
		} else if (overdue == true) {
			hold = "Overdue";
		} else hold = "Available";
		
		return hold;
	}
}
