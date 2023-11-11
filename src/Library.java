import java.util.*;

public class Library {
	
	/**
	 * We used the LinkedList data type for its flexibilty and efficiency memory wise.
	 * LinkedLists are dynamic which mean s that inserting and deleting data becomes easier.
	 * Another reason is the way that each node has a reference to the next node, allowing sorting algorithms and manipulation of the dataset to be faster.
	 */
	private LinkedList<Book> catalog= new LinkedList<Book>(); 
	private int bookID = 0001;
	
	  public Library() {
		  
	        // Create and add Book objects to the catalog, this is our data set for the program
	        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee", "Classics", true, "English", createDate(1960, 7, 11), createDate(2022, 11, 15));
	        Book book2 = new Book("1984", "George Orwell", "Science Fiction", true, "English", createDate(1949, 6, 8), createDate(2022, 10, 22));
	        Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classics", true, "English", createDate(1925, 4, 10), createDate(2022, 9, 5));
	        Book book4 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", true, "English", createDate(1937, 9, 21), createDate(2022, 8, 18));
	        Book book5 = new Book("Pride and Prejudice", "Jane Austen", "Romance", true, "English", createDate(1813, 1, 28), createDate(2022, 7, 9));
	        Book book6 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Non-Fiction", false, "English", createDate(2011, 2, 10), createDate(2022, 6, 15));
	        Book book7 = new Book("The Da Vinci Code", "Dan Brown", "Mystery", true, "English", createDate(2003, 3, 18), createDate(2022, 5, 20));
	        Book book8 = new Book("The Catcher in the Rye", "J.D. Salinger", "Classics", true, "English", createDate(1951, 7, 16), createDate(2022, 4, 12));
	        Book book9 = new Book("A Brief History of Time", "Stephen Hawking", "Science", false, "English", createDate(1988, 4, 1), createDate(2022, 3, 7));
	        Book book10 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", true, "English", createDate(1997, 6, 26), createDate(2022, 2, 3));
	        Book book11 = new Book("Noli Me Tangere", "Jose Rizal", "Fiction", true, "Filipino", createDate(1887, 5, 21), createDate(2022, 2, 3));
	        Book book12 = new Book("El Filibusterismo", "Jose Rizal", "Fiction", true, "Filipino", createDate(1891, 9, 18), createDate(2022, 2, 3));
	        Book book13 = new Book("Si Janus Silang at ang Tiyanak ng Tabon", "Edgar Calabia Samar", "Fantasy", true, "Filipino", createDate(2014, 9, 1), createDate(2022, 2, 3));
	        Book book14 = new Book("Ang Paboritong Libro ni Hudas", "Bob Ong", "Humor", true, "Filipino", createDate(2003, 1, 1), createDate(2022, 2, 3));
	        Book book15 = new Book("Banaag at Sikat", "Lope K. Santos", "Classics", true, "Filipino", createDate(1906, 1, 1), createDate(2022, 2, 3));
		    Book book16 = new Book("Si Malakas at Si Maganda", "Unknown Author", "Folklore", true, "Cebuano", createDate(1820, 11, 25), createDate(2022, 2, 3));
		    Book book17 = new Book("Mga Sugilanong Bahin sa Banaue", "Federico Caballero", "Folklore", true, "Cebuano", createDate(1953, 1, 1), createDate(2022, 2, 3));
		    Book book18 = new Book("Ang Kiti-kiti ug ang Batong Buwad", "Federico Caballero", "Folklore", true, "Cebuano", createDate(1953, 1, 1), createDate(2022, 2, 3));
		    Book book19 = new Book("Si Pagong ug si Matsing", "Unknown Author", "Folklore", true, "Cebuano", createDate(1966, 3, 21), createDate(2022, 2, 3));
		    Book book20 = new Book("Ang Kapikas sa Sugilanon", "Magdalena Jalandoni", "Short Stories", true, "Cebuano", createDate(1859, 4, 5), createDate(2022, 2, 3));
		    
		    book20.setAvailability("Rented");
		    book15.setAvailability("Rented");
		    book11.setAvailability("Rented");
		    book13.setAvailability("Rented");
		    book18.setAvailability("Rented");


	        // Add the books to the catalog
	        addBook(book1);
	        addBook(book2);
	        addBook(book3);
	        addBook(book4);
	        addBook(book5);
	        addBook(book6);
	        addBook(book7);
	        addBook(book8);
	        addBook(book9);
	        addBook(book10);
	        addBook(book11);
	        addBook(book12);
	        addBook(book13);
	        addBook(book14);
	        addBook(book15);
	        addBook(book16);
	        addBook(book17);
	        addBook(book18);
	        addBook(book19);
	        addBook(book20);
	    }
	
	//Assigns the book with its bookID and adds it on to the LinkedList catalog
	public void addBook(Book newBook) {
		newBook.setBookID(bookID);
		getCatalog().addFirst(newBook);
		this.bookID++; // bookID increases each new book, meaning sorting for recently added becomes easier
	}
	
	//Removes a book from the linked list catalog
	public void removeBook(Book book) {
	    catalog.remove(book);
	}
	
	//We use calendar data types to create new Date data types as it handles the intricacies that comes with Dates, such as leap years. 
	 private Date createDate(int year, int month, int day) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(year, month - 1, day);
	        return calendar.getTime();
	    }

	 //Returns the LinkedList catalog
	public LinkedList<Book> getCatalog() {
		return catalog;
	}
	
	 /**
	  * The main sorting algorithm we use for each variety of sorting in the program is merge sort.
	  *  We simply modify the same template of merge sort depending on the attribute we need to compare from the books.
	  *  For example in sortByRecent we compare the bookID of each book.
	  */
	
	//Uses a merge sort and compares the bookID, the greater the bookID the more recent the book was added
	public void sortByRecent() {
	    // Convert the linked list to an array for sorting
	    Book[] booksArray = catalog.toArray(new Book[0]);

	    // Call the merge sort 
	    mergeSortRecent(booksArray, 0, booksArray.length - 1);

	    // Convert the sorted array back to a linked list
	    catalog.clear();
	    catalog.addAll(Arrays.asList(booksArray));
	}

	//first determines a mid point, then recursively splits the data set
	private void mergeSortRecent(Book[] arr, int left, int right) {
	    if (left < right) {
	        int mid = (left + right) / 2; // finding mid
	        mergeSortRecent(arr, left, mid); // recursively splitting until each sub-array only has one element
	        mergeSortRecent(arr, mid + 1, right);
	        mergeRecent(arr, left, mid, right);//merges the sorted arrays together
	    }
	}

	//Merges two sorted arrays
	private void mergeRecent(Book[] arr, int left, int mid, int right) {
		//Determining the sizes of the left and right array
	    int sizeL = mid - left + 1;
	    int sizeR = right - mid;

	    // Create temporary arrays
	    Book[] leftArray = new Book[sizeL];
	    Book[] rightArray = new Book[sizeR];

	    // Copy data to temporary arrays
	    System.arraycopy(arr, left, leftArray, 0, sizeL);
	    System.arraycopy(arr, mid + 1, rightArray, 0, sizeR);

	    int i = 0, j = 0;
	    int k = left;

	    // Merge the temporary arrays by comparing the bookID of the left book to the right.  if it is greater or equal, goes to the left if not, goes to the right
	    while (i < sizeL && j < sizeR) {
	        if (Integer.parseInt(leftArray[i].getBookID()) >= Integer.parseInt(rightArray[j].getBookID())) {
	            arr[k] = leftArray[i];
	            i++;
	        } else {
	            arr[k] = rightArray[j];
	            j++;
	        }
	        k++;
	    }

	    // Takes care of the rest of the remaining elements of the left array by copying them
	    while (i < sizeL) {
	        arr[k] = leftArray[i];
	        i++;
	        k++;
	    }

	    // Takes care of the rest of the remaining elements of the right array by copying them
	    while (j < sizeR) {
	        arr[k] = rightArray[j];
	        j++;
	        k++;
	    }
	}
	
	//the rest of the sorting methods that use a merge sort follow the same logic and have the same time complexity of O(n) 
	  
	
	 public List<Book> sortByYear(int year) { // Accepts int input for the year
		 	//temporary List of object Book 
	        List<Book> booksToShow = new ArrayList<>();

	        // goes through every book in the catalog 
	        for (Book book : catalog) { 
	        	
	        	// gets the date published of the book
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(new Date(book.getDatePublished()));
	            int bookYear = calendar.get(Calendar.YEAR);

	            //compares with the target year
	            if (bookYear == year) {
	            	// if it matches then we add to list of books to show
	                booksToShow.add(book);
	            }
	        }
	        
	        //returns the list to be displayed
	        return booksToShow;
	 }
	 
	//Sorts alphabetically increasingly (A -Z) and follows the same logic as the earlier sorting algorithm
	 public void sortAlphabeticallyAZ() {
		    Book[] booksArray = catalog.toArray(new Book[0]);
		    mergeSortAZ(booksArray, 0, booksArray.length - 1);
		    catalog.clear();
		    catalog.addAll(Arrays.asList(booksArray));
		}

		private void mergeSortAZ(Book[] arr, int left, int right) {
		    if (left < right) {
		        int mid = (left + right) / 2;
		        mergeSortAZ(arr, left, mid);
		        mergeSortAZ(arr, mid + 1, right);
		        mergeAZ(arr, left, mid, right);
		    }
		}

		private void mergeAZ(Book[] arr, int left, int mid, int right) {
		    int n1 = mid - left + 1;
		    int n2 = right - mid;

		    Book[] leftArray = new Book[n1];
		    Book[] rightArray = new Book[n2];

		    System.arraycopy(arr, left, leftArray, 0, n1);
		    System.arraycopy(arr, mid + 1, rightArray, 0, n2);

		    int i = 0, j = 0;
		    int k = left;

		    while (i < n1 && j < n2) {
		        if (leftArray[i].getTitle().compareTo(rightArray[j].getTitle()) <= 0) {
		            arr[k] = leftArray[i];
		            i++;
		        } else {
		            arr[k] = rightArray[j];
		            j++;
		        }
		        k++;
		    }

		    while (i < n1) {
		        arr[k] = leftArray[i];
		        i++;
		        k++;
		    }

		    while (j < n2) {
		        arr[k] = rightArray[j];
		        j++;
		        k++;
		    }
		}
		
		public void sortAlphabeticallyZA() {
		    Book[] booksArray = catalog.toArray(new Book[0]);

		    mergeSortZA(booksArray, 0, booksArray.length - 1);

		    catalog.clear();
		    catalog.addAll(Arrays.asList(booksArray));
		}

		private void mergeSortZA(Book[] arr, int left, int right) {
		    if (left < right) {
		        int mid = (left + right) / 2;
		        mergeSortZA(arr, left, mid);
		        mergeSortZA(arr, mid + 1, right);
		        mergeZA(arr, left, mid, right);
		    }
		}

		private void mergeZA(Book[] arr, int left, int mid, int right) {
		    int n1 = mid - left + 1;
		    int n2 = right - mid;

		    Book[] leftArray = new Book[n1];
		    Book[] rightArray = new Book[n2];

		    System.arraycopy(arr, left, leftArray, 0, n1);
		    System.arraycopy(arr, mid + 1, rightArray, 0, n2);

		    int i = 0, j = 0;
		    int k = left;

		    while (i < n1 && j < n2) {
		        if (leftArray[i].getTitle().compareToIgnoreCase(rightArray[j].getTitle()) >= 0) {
		            arr[k] = leftArray[i];
		            i++;
		        } else {
		            arr[k] = rightArray[j];
		            j++;
		        }
		        k++;
		    }

		    while (i < n1) {
		        arr[k] = leftArray[i];
		        i++;
		        k++;
		    }
		    
		    while (j < n2) {
		        arr[k] = rightArray[j];
		        j++;
		        k++;
		    }
		}
		
		//The next algorithm that will be used is a linear search algorithm, which filters a list based on a certain attribute
			public List<Book> sortByLanguage(String language) { //The string parameter language will depend on what option is chosen in the main frame
				//temporary list
		        List<Book> booksToShow = new ArrayList<>();

		        for (Book book : catalog) {
		        	//if the language of the book matches the string parameter input  then add to temporary list
		            if (book.getLanguage().equalsIgnoreCase(language)) {
		                booksToShow.add(book);
		            }
		        }
		        
		        //return the temporary list
		        return booksToShow;
		    }
		  
			//similar logic with sortBylanguage
		  public List<Book> sortByAvailability(String availabilityType) {
		        List<Book> booksToShow = new ArrayList<>();

		        for (Book book : catalog) {
		            if (availabilityType.equals("Available") && book.getAvailability().equals("Available")) {
		                booksToShow.add(book);
		            } else if (availabilityType.equals("Rented") && book.getAvailability().equals("Rented")) {
		                booksToShow.add(book);
		            } else if (availabilityType.equals("Overdue") && book.getAvailability().equals("Overdue")) {
		                booksToShow.add(book);
		            }
		            
		        }
		        return booksToShow;
		    }
	
}
