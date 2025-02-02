/*
 * Task 28: Library Management System (AWT, Collections, and Exception Handling)
This task will help you practice AWT for GUI, Collections for data storage, and Exception Handling for validation. 
You will build a simple Library Management System where users can add, remove, search, and display books using a GUI. 
This task aligns with your textbook's AWT and Collections topics while reinforcing exception handling.

Requirements

Book Class
Fields: bookID, title, author, availabilityStatus (boolean).
Constructor: Initializes all fields.

Methods:
displayBookInfo(): Prints book details.
LibraryManager Class

Fields: ArrayList<Book> to store books.

Methods:
addBook(Book book): Adds a book to the list.
removeBook(int bookID): Removes a book by ID.
searchBookByTitle(String title): Searches for a book by title.
displayAllBooks(): Displays all books.

GUI Requirements (AWT-Based)
Text Fields to enter book details.
Buttons to:
Add a book.
Remove a book.
Search for a book.
Display all books.

List Box to show books in the library.

Exception Handling
Handle invalid book IDs (e.g., trying to remove a non-existent book).
Ensure book titles and authors are not left empty.

Concepts Covered
AWT GUI Development: Frame, Labels, Buttons, TextFields, List.
Collection Framework: ArrayList<Book> to store and manipulate book records.
Event Handling: Using ActionListener to perform book operations.
Exception Handling: Ensuring valid user inputs and handling errors gracefully.

Hints for Implementation
Use setLayout(null) and setBounds() for manual positioning in AWT.
Implement try-catch blocks for exception handling in user input.
Use streams to filter books based on title search.
Update the list dynamically whenever a book is added or removed.

Expected Outcome
A GUI-based Library System where users can add books, search, remove, and view all books with proper exception handling.
 */


package advance_tasks;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    private static int incrementBookID = 100; // Needs to be static
    private int bookID;
    private String title;
    private String author;
    private boolean availabilityStatus;

    public Book(String b_title, String b_author) {
        this.bookID = incrementBookID++; // Assigns the correct book ID
        this.title = b_title;
        this.author = b_author;
        this.availabilityStatus = true;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookID);
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Availability: " + availabilityStatus);
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }
}


class LibraryManager {
    private ArrayList<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        getBookList().add(book);
        System.out.println("Book Added: " + book.getTitle());
    }

    public void removeBook(int bookID) {
        boolean removed = getBookList().removeIf(book -> book.getBookID() == bookID);
        if (removed) {
            System.out.println("Book Removed: " + bookID);
        } else {
            System.out.println("Book not found with ID: " + bookID);
        }
    }

    public Book searchBookByTitle(String title) {
        return getBookList().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void displayAllBooks() {
        if (getBookList().isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : getBookList()) {
                book.displayBookInfo();
            }
        }
    }

	public ArrayList<Book> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
}


public class T2_libraryMS {
	
	public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        Frame f = new Frame("Library Management System");

        Label titleLabel = new Label("Book Title");
        titleLabel.setBounds(20, 50, 100, 20);
        TextField titleField = new TextField();
        titleField.setBounds(130, 50, 150, 20);

        Label authorLabel = new Label("Book Author");
        authorLabel.setBounds(20, 80, 100, 20);
        TextField authorField = new TextField();
        authorField.setBounds(130, 80, 150, 20);

        Button addBtn = new Button("Add Book");
        addBtn.setBounds(20, 110, 100, 30);

        Button removeBtn = new Button("Remove Book");
        removeBtn.setBounds(130, 110, 100, 30);

        Button searchBtn = new Button("Search");
        searchBtn.setBounds(240, 110, 100, 30);

        TextField searchField = new TextField();
        searchField.setBounds(20, 150, 150, 20);

        List bookList = new List();
        bookList.setBounds(20, 180, 320, 100);

        f.add(titleLabel);
        f.add(titleField);
        f.add(authorLabel);
        f.add(authorField);
        f.add(addBtn);
        f.add(removeBtn);
        f.add(searchBtn);
        f.add(searchField);
        f.add(bookList);

        f.setSize(400, 350);
        f.setLayout(null);
        f.setVisible(true);

        // Add Book Event
        addBtn.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();

            if (title.isEmpty() || author.isEmpty()) {
                System.out.println("Title and Author cannot be empty!");
                return;
            }

            Book newBook = new Book(title, author);
            lm.addBook(newBook);
            bookList.add(newBook.getBookID() + ": " + newBook.getTitle() + " - " + newBook.getTitle());

            // Clear fields after adding
            titleField.setText("");
            authorField.setText("");
        });

        // Remove Book Event
        removeBtn.addActionListener(e -> {
            try {
                int bookID = Integer.parseInt(searchField.getText().trim());
                lm.removeBook(bookID);
                bookList.removeAll();
                for (Book book : lm.getBookList()) {
                    bookList.add(book.getBookID() + ": " + book.getTitle() + " - " + book.getTitle());
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Book ID format!");
            }
        });

        // Search Book Event
        searchBtn.addActionListener(e -> {
            String title = searchField.getText().trim();
            Book foundBook = lm.searchBookByTitle(title);

            if (foundBook != null) {
                System.out.println("Book Found:");
                foundBook.displayBookInfo();
            } else {
                System.out.println("Book not found!");
            }
        });

        // Close Window
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });
    }
}
