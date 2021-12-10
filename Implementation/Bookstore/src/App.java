import java.sql.*;
import java.util.Scanner;
import java.util.Vector;



public class App {
    static final String DB_URL = "jdbc:postgresql://localhost/FinalProject";
    static final String USER = "postgres";
    static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {        
        
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Vector<Integer> cart = new Vector<Integer>();
            String currentUserID;
            boolean loggedIn = false;
                             
            Scanner kb = new Scanner(System.in);
            boolean quit = false;
            while (quit == false) {

                System.out.println("Welcome to the Look Inna Book bookstore! \n");
                System.out.println("Are you a shopper? (1) \nor the owner? (2) \nor would you like to exit (3)");
                int userType = kb.nextInt();
                String bufferClear = kb.nextLine();
                if (userType == 1) {
                    boolean userQuit = false;
                    while (userQuit == false) {
                        System.out.println("Welcome to the user menu! \n");
                        System.out.println("Would you like to: \n");
                        System.out.println("(1): Search books by title \n");
                        System.out.println("(2): Search books by genre \n");
                        System.out.println("(3): Search books by ISBN \n");
                        System.out.println("(4): Search books by author \n");
                        System.out.println("(5): Register your account \n");
                        System.out.println("(6): Login to an existing account \n");
                        System.out.println("(7): Add a book to your cart \n");
                        System.out.println("(8): Remove a book from your cart \n");
                        System.out.println("(9): Checkout \n");
                        System.out.println("(0): Return to menu \n");
                        int adminMenuChoice = kb.nextInt();
                        bufferClear = kb.nextLine();
                        switch (adminMenuChoice){
                            case 1: {
                                PreparedStatement titleSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where title=?");
                                System.out.println("What is the title of the book you'd like to search for?");
                                String title = kb.nextLine();
                                titleSearch.setString(1, title);
                                ResultSet titleSet;
                                try {
                                    titleSet = titleSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                System.out.println("\nHere are your results");
                                while (titleSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + titleSet.getString(1) + " Author: " + titleSet.getString(2) + " Genre: " + titleSet.getString(3) + " ISBN: " + titleSet.getInt(4) + " Publisher: " + titleSet.getString(5) + " Publisher ID: " + titleSet.getInt(6) + " Pages: " + titleSet.getInt(7) + " Price: $" + titleSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 2: {
                                PreparedStatement genreSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where genre=?");
                                System.out.println("What is the genre of the book you'd like to search for?");
                                String genre = kb.nextLine();
                                genreSearch.setString(1, genre);
                                ResultSet genreSet;
                                try {
                                    genreSet = genreSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                System.out.println("\nHere are your results");
                                while (genreSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + genreSet.getString(1) + " Author: " + genreSet.getString(2) + " Genre: " + genreSet.getString(3) + " ISBN: " + genreSet.getInt(4) + " Publisher: " + genreSet.getString(5) + " Publisher ID: " + genreSet.getInt(6) + " Pages: " + genreSet.getInt(7) + " Price: $" + genreSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 3: {
                                PreparedStatement ISBNSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=?");
                                System.out.println("What is the ISBN of the book you'd like to search for?");
                                int ISBN = kb.nextInt();
                                bufferClear = kb.nextLine();
                                ISBNSearch.setInt(1, ISBN);
                                ResultSet ISBNSet;
                                try {
                                    ISBNSet = ISBNSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                System.out.println("\nHere are your results");
                                while (ISBNSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + ISBNSet.getString(1) + " Author: " + ISBNSet.getString(2) + " Genre: " + ISBNSet.getString(3) + " ISBN: " + ISBNSet.getInt(4) + " Publisher: " + ISBNSet.getString(5) + " Publisher ID: " + ISBNSet.getInt(6) + " Pages: " + ISBNSet.getInt(7) + " Price: $" + ISBNSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 4: {
                                PreparedStatement authorSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where author=?");
                                System.out.println("What is the name of the author of the book you'd like to search for?");
                                String author = kb.nextLine();
                                authorSearch.setString(1, author);
                                ResultSet authorSet;
                                try {
                                    authorSet = authorSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                System.out.println("\nHere are your results");
                                while (authorSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + authorSet.getString(1) + " Author: " + authorSet.getString(2) + " Genre: " + authorSet.getString(3) + " ISBN: " + authorSet.getInt(4) + " Publisher: " + authorSet.getString(5) + " Publisher ID: " + authorSet.getInt(6) + " Pages: " + authorSet.getInt(7) + " Price: $" + authorSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 5: {
                                if (loggedIn = false) {
                                    String RegisterUser = "insert into store_user(user_id, date_of_birth, address, email, full_name, phone_number) values(?, ?, ?, ?, ?, ?)";
                                    PreparedStatement RegisterStatement = conn.prepareStatement(RegisterUser);

                                    System.out.println("Welcome to Registration!\n");
                                    System.out.println("Please choose a unique user id \n");
                                    String RUserId = kb.nextLine();
                                    System.out.println("Please input your date of birth in the form (dd/mm/yyyy)\n");
                                    String RDateOfBirth = kb.nextLine();
                                    System.out.println("Please input your address in the form (123 house street, city, province, A1B2C3)\n");
                                    String RAddress = kb.nextLine();
                                    System.out.println("Please input your email address \n");
                                    String REmail = kb.nextLine();
                                    System.out.println("Please input your full name\n");
                                    String RFullName = kb.nextLine();
                                    System.out.println("Please input your phone number in the form 1234567890\n");
                                    int RPhoneNumber = kb.nextInt();
                                    bufferClear = kb.nextLine();
                                    
                                    RegisterStatement.setString(1, RUserId);
                                    RegisterStatement.setString(2, RDateOfBirth);
                                    RegisterStatement.setString(3, RAddress);
                                    RegisterStatement.setString(4, REmail);
                                    RegisterStatement.setString(5, RFullName);                              
                                    RegisterStatement.setInt(6, RPhoneNumber);

                                    

                                    try{
                                        RegisterStatement.executeUpdate();    
                                        currentUserID = RUserId;
                                        loggedIn = true;
                                        System.out.println("User added successfully \n") ;

                                        } catch (SQLException e) {
                                            System.out.println("Error registering: please try again \n");
                                        }                            

                                } else {
                                    System.out.println("\nYou're already logged in! \n");
                                }
                                break;
                            }
                            case 6: {
                                if (loggedIn = false) {
                                    PreparedStatement userSearch = conn.prepareStatement("select user_id from store_user where user_id=");
                                    System.out.println("What is your user_id?");
                                    String userID = kb.nextLine();
                                    userSearch.setString(1, userID);
                                    ResultSet userIDSet;
                                    try {
                                        userIDSet = userSearch.executeQuery();
                                    } catch (SQLException e) {
                                        System.out.println("\nInvalid user_id: please try again\n");
                                        break;
                                    }

                                    if (userIDSet.next()) {
                                        currentUserID = userID;
                                        loggedIn = true;
                                    }
                                } else {
                                    System.out.println("\nYou're already logged in! \n");
                                }

                                break;
                            }
                            case 7: {
                                break;
                            }
                            case 8: {
                                break;
                            }
                            case 9: {
                                break;
                            }
                            case 0: {
                                userQuit = true;
                                break;
                            }
                        }
                    }
                    

                } else if (userType == 2) {
                    boolean ownerQuit = false;
                    while (ownerQuit == false) {
                        System.out.println("Welcome to the owner menu! \n");
                        System.out.println("Would you like to: \n");
                        System.out.println("(1): Add a new book to the database \n");
                        System.out.println("(2): Remove a book from the database \n");
                        System.out.println("(3): Add a publisher to the database \n");
                        System.out.println("(4): Remove a publisher from the database \n");
                        System.out.println("(5): Add more copies of a book to inventory \n");
                        System.out.println("(6): View Reports Menu \n");
                        System.out.println("(0): Return to main menu \n");
                        int adminMenuChoice = kb.nextInt();
                        bufferClear = kb.nextLine();
                        switch (adminMenuChoice){
                            case 1: {
                                String addSql = "insert into book(title, author, retail_cost, genre, sold, available, ISBN, bulk_cost, publisher_percentage, publisher_id, sold_last_month, pages) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                PreparedStatement addStatement = conn.prepareStatement(addSql);
                                System.out.println("\nWhat is the book's title? \n");
                                String title = kb.nextLine();
                                System.out.println("\nWho wrote the book?\n");
                                String author = kb.nextLine();
                                System.out.println("\nWhat is the retail cost of the book?\n");
                                double retail_cost = kb.nextDouble();
                                bufferClear = kb.nextLine();
                                System.out.println("\nWhat is the genre of the book?\n");
                                String genre = kb.nextLine();
                                int sold = 0;
                                System.out.println("\nHow many copies would you like to add?\n");
                                int available = kb.nextInt();
                                bufferClear = kb.nextLine();
                                System.out.println("\nWhat is the ISBN?\n");
                                int ISBN = kb.nextInt();
                                bufferClear = kb.nextLine();
                                System.out.println("\nWhat is the bulk cost of the book?\n");
                                double bulk_cost = kb.nextDouble();
                                bufferClear = kb.nextLine();
                                System.out.println("\nWhat percentage of sales should go to the publisher?\n");
                                double publisher_percentage = kb.nextDouble();
                                bufferClear = kb.nextLine();
                                System.out.println("\nWhat is the ID of the publisher?\n");
                                int publisher_id = kb.nextInt();
                                bufferClear = kb.nextLine();
                                int sold_last_month = 0;
                                System.out.println("\nHow many pages does the book have?\n");
                                int pages = kb.nextInt();
                                bufferClear = kb.nextLine();

                                addStatement.setString(1, title);
                                addStatement.setString(2, author);
                                addStatement.setDouble(3, retail_cost);
                                addStatement.setString(4, genre);
                                addStatement.setInt(5, sold);
                                addStatement.setInt(6, available);
                                addStatement.setInt(7, ISBN);
                                addStatement.setDouble(8, bulk_cost);
                                addStatement.setDouble(9, publisher_percentage);
                                addStatement.setInt(10, publisher_id);
                                addStatement.setInt(11, sold_last_month);
                                addStatement.setInt(12, pages);
                                try{
                                addStatement.executeUpdate();    
                                System.out.println("Book added successfully \n") ;
                                } catch (SQLException e) {
                                    System.out.println("Error adding book: Please try again \n");
                                }
                                break;
                            }
                            case 2: {
                                String removeSql = "DELETE FROM book WHERE ISBN=?";
                                PreparedStatement removeStatement = conn.prepareStatement(removeSql);
                                System.out.println("What is the ISBN of the book you want to remove?"); 
                                int removeBook = kb.nextInt();
                                bufferClear = kb.nextLine();
                                removeStatement.setInt(1, removeBook);
                                try{
                                removeStatement.executeUpdate();
                                System.out.println("\nBook removed successfully \n") ;
                                } catch (SQLException e) {
                                    System.out.println("\nRemoval Error: Please try again \n");
                                }
                                break;

                                
                            }
                            case 3: {
                                String addPubSql = "insert into publisher(pub_name, address, email, phone_number, money_made, publisher_id) values(?, ?, ?, ?, ?, ?)";
                                PreparedStatement addPubStatement = conn.prepareStatement(addPubSql);
                                System.out.println("\nWhat is the publishers name? \n");
                                String publisherName = kb.nextLine();
                                System.out.println("\nWhat is the publishers address? (In the form of 123 House street, City, Province, A1B2C3) \n");
                                String publisherAddress = kb.nextLine();
                                System.out.println("\nWhat is the publishers email address? \n");
                                String publisherEmail = kb.nextLine();
                                System.out.println("\nWhat is the publishers phone number? (in the form of 1234567890)\n");
                                int publisherPhoneNumber = kb.nextInt();
                                bufferClear = kb.nextLine();
                                int publisherMoneyMade = 0;
                                System.out.println("\nWhat is the publisher id? \n");
                                int publisher_id = kb.nextInt();
                                bufferClear = kb.nextLine();

                                addPubStatement.setString(1, publisherName);
                                addPubStatement.setString(2, publisherAddress);
                                addPubStatement.setString(3, publisherEmail);
                                addPubStatement.setInt(4, publisherPhoneNumber);
                                addPubStatement.setInt(5, publisherMoneyMade);
                                addPubStatement.setInt(6, publisher_id);

                                try{
                                    addPubStatement.executeUpdate();
                                    System.out.println("\nPublisher added successfully. \n") ;
                                    } catch (SQLException e) {
                                        System.out.println("\n Failed to add publisher: Please try again \n");
                                    }
                                break;
                                
                            }
                            case 4: {
                                String removeSql = "DELETE FROM publisher WHERE publisher_id=?";
                                PreparedStatement removeStatement = conn.prepareStatement(removeSql);
                                System.out.println("\nWhat is the publisher_id of the publisher you want to remove?\n"); 
                                System.out.println("Note: To remove a publisher you must first remove all associated books");
                                int removePublisher = kb.nextInt();
                                bufferClear = kb.nextLine();
                                removeStatement.setInt(1, removePublisher);
                                try{
                                removeStatement.executeUpdate();
                                System.out.println("\nPublisher removed successfully \n") ;
                                } catch (SQLException e) {
                                    System.out.println("\nRemoval Error: Please try again \n");
                                }

                                break;
                                
                            }
                            case 5: {
                                int toAdd = 0;

                                String addInventory = "UPDATE book SET available=? where ISBN=?";
                                PreparedStatement addInventoryStatement = conn.prepareStatement(addInventory);

                                PreparedStatement availableStatement = conn.prepareStatement("SELECT available FROM book where ISBN=?");
                                System.out.println("What is the ISBN of the book you'd like to add more inventory of?");
                                int inventoryISBN = kb.nextInt();
                                bufferClear = kb.nextLine();
                                availableStatement.setInt(1, inventoryISBN);
                                ResultSet availableSet;
                                try {
                                    availableSet = availableStatement.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid ISBN: please try again\n");
                                    break;
                                }
                                while (availableSet.next()) {
                                    toAdd = availableSet.getInt(1);
                                }
                                System.out.println("How many copies would you like to add?");
                                toAdd += kb.nextInt();
                                bufferClear = kb.nextLine();
                                addInventoryStatement.setInt(1, toAdd);
                                addInventoryStatement.setInt(2, inventoryISBN);
                                try {
                                    addInventoryStatement.executeUpdate();
                                    System.out.println("\nInventory Added Successfully\n");
                                } catch (SQLException e) {
                                    System.out.println("\nSomething went wrong: please try again\n");
                                    break;
                                }

                                break;
                                
                            }
                            case 6: {

                                break;
                                
                            }
                            case 7: {

                                break;
                                
                            }
                            case 0: {
                                ownerQuit = true;

                                break;
                                
                            }
                        }
                    }

                } else if (userType == 3) {
                    quit = true;
                }







            }
    }


}

