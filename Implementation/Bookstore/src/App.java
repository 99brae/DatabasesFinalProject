import java.sql.*;
import java.util.Scanner;
import java.util.Vector;



class App {
    static final String DB_URL = "jdbc:postgresql://localhost/FinalProject";
    static final String USER = "postgres";
    static final String PASSWORD = "";
    static final int RESTOCKNUM = 10;

    public static void main(String[] args) throws Exception {        
            //establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //represents cart. is an vector of ISBN's
            Vector<Integer> cart = new Vector<Integer>();
            String currentUserID = "";
            boolean loggedIn = false;
                             
            Scanner kb = new Scanner(System.in);
            boolean quit = false;
            //main loop, lets you chose between user mode, owner mode and quit
            while (quit == false) {

                System.out.println("Welcome to the Look Inna Book bookstore! \n");
                System.out.println("Are you a shopper? (1) \nor the owner? (2) \nor would you like to exit (3)");
                int userType = kb.nextInt();
                String bufferClear = kb.nextLine();
                System.out.println(bufferClear);
                //user mode entry
                if (userType == 1) {
                    boolean userQuit = false;
                    //user mode loop
                    while (userQuit == false) {
                        //list of available options
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
                        System.out.println("(9): View Cart / Checkout \n");
                        System.out.println("(10): View order \n");
                        System.out.println("(0): Return to menu \n");
                        int adminMenuChoice = kb.nextInt();
                        bufferClear = kb.nextLine();

                        //search by title choice
                        switch (adminMenuChoice){
                            case 1: {
                                //create a prepared statement that takes a variable to represent title
                                PreparedStatement titleSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where title=?");
                                System.out.println("What is the title of the book you'd like to search for?");

                                String title = kb.nextLine();
                                //add entered title to the prepared statement
                                titleSearch.setString(1, title);
                                ResultSet titleSet;

                                //attempt to execute the query
                                try {
                                    titleSet = titleSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                //display the results of the search
                                System.out.println("\nHere are your results");
                                while (titleSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + titleSet.getString(1) + " Author: " + titleSet.getString(2) + " Genre: " + titleSet.getString(3) + " ISBN: " + titleSet.getInt(4) + " Publisher: " + titleSet.getString(5) + " Publisher ID: " + titleSet.getInt(6) + " Pages: " + titleSet.getInt(7) + " Price: $" + titleSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 2: {
                                //create a prepared statement that takes a variable to represent genre
                                PreparedStatement genreSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where genre=?");
                                System.out.println("What is the genre of the book you'd like to search for?");
                                String genre = kb.nextLine();
                                //add entered title to the prepared statement
                                genreSearch.setString(1, genre);
                                ResultSet genreSet;

                                //attempt to execute the query
                                try {
                                    genreSet = genreSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                //display the results of the search
                                System.out.println("\nHere are your results");
                                while (genreSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + genreSet.getString(1) + " Author: " + genreSet.getString(2) + " Genre: " + genreSet.getString(3) + " ISBN: " + genreSet.getInt(4) + " Publisher: " + genreSet.getString(5) + " Publisher ID: " + genreSet.getInt(6) + " Pages: " + genreSet.getInt(7) + " Price: $" + genreSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 3: {
                                //create a prepared statement that takes a variable to represent ISBN
                                PreparedStatement ISBNSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=?");
                                System.out.println("What is the ISBN of the book you'd like to search for?");
                                int ISBN = kb.nextInt();
                                //buffer clear is used after every nextInt() to deal with the newline character
                                bufferClear = kb.nextLine();
                                //add entered title to the prepared statement
                                ISBNSearch.setInt(1, ISBN);
                                ResultSet ISBNSet;
                                //attempt to execute the query
                                try {
                                    ISBNSet = ISBNSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                //display the results of the search
                                System.out.println("\nHere are your results");
                                while (ISBNSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + ISBNSet.getString(1) + " Author: " + ISBNSet.getString(2) + " Genre: " + ISBNSet.getString(3) + " ISBN: " + ISBNSet.getInt(4) + " Publisher: " + ISBNSet.getString(5) + " Publisher ID: " + ISBNSet.getInt(6) + " Pages: " + ISBNSet.getInt(7) + " Price: $" + ISBNSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 4: {
                                //create a prepared statement that takes a variable to represent author
                                PreparedStatement authorSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where author=?");
                                System.out.println("What is the name of the author of the book you'd like to search for?");
                                String author = kb.nextLine();
                                //add entered author name to the prepared statement
                                authorSearch.setString(1, author);
                                ResultSet authorSet;
                                //attempt to the execute the query
                                try {
                                    authorSet = authorSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nInvalid search: please try again\n");
                                    break;
                                }
                                int counter = 1;
                                //print the results of the search
                                System.out.println("\nHere are your results");
                                while (authorSet.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + authorSet.getString(1) + " Author: " + authorSet.getString(2) + " Genre: " + authorSet.getString(3) + " ISBN: " + authorSet.getInt(4) + " Publisher: " + authorSet.getString(5) + " Publisher ID: " + authorSet.getInt(6) + " Pages: " + authorSet.getInt(7) + " Price: $" + authorSet.getDouble(8) + "\n");
                                    counter++;
                                }
                                System.out.println("\n");
                                break;
                            }
                            case 5: {
                                //if the user is not already logged in they can register 
                                if (loggedIn == false) {
                                    //make a prepared statement and populate it with an empty store_user insert
                                    String RegisterUser = "insert into store_user(user_id, date_of_birth, address, email, full_name, phone_number) values(?, ?, ?, ?, ?, ?)";
                                    PreparedStatement RegisterStatement = conn.prepareStatement(RegisterUser);
                                    //request values from the user
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
                                    
                                    //put the user submitted variables into the prepared statement
                                    RegisterStatement.setString(1, RUserId);
                                    RegisterStatement.setString(2, RDateOfBirth);
                                    RegisterStatement.setString(3, RAddress);
                                    RegisterStatement.setString(4, REmail);
                                    RegisterStatement.setString(5, RFullName);                              
                                    RegisterStatement.setInt(6, RPhoneNumber);

                                    
                                    //attempt to execute the update to add the new user to table store_user 
                                    try{
                                        RegisterStatement.executeUpdate();    
                                        currentUserID = RUserId;
                                        loggedIn = true;
                                        System.out.println("User added successfully \n") ;

                                        } catch (SQLException e) {
                                            System.out.println("Error registering: please try again \n");
                                        }                            

                                } else {
                                    //if the user is already logged in, return to user menu
                                    System.out.println("\nYou're already logged in! \n");
                                }
                                break;
                            }
                            case 6: {
                                //if the user is not already logged in, they can log in
                                if (loggedIn == false) {
                                    //prepared statement to check the database for the user
                                    PreparedStatement userSearch = conn.prepareStatement("select user_id from store_user where user_id=?");
                                    System.out.println("What is your user_id?");
                                    String userID = kb.nextLine();
                                    userSearch.setString(1, userID);
                                    ResultSet userIDSet;
                                    //attempt to execute the query 
                                    try {
                                        userIDSet = userSearch.executeQuery();
                                    } catch (SQLException e) {
                                        System.out.println("\nInvalid user_id: please try again\n");
                                        break;
                                    }
                                    //if the user_id is not found this will be empty and return false
                                    if (userIDSet.next()) {
                                        currentUserID = userID;
                                        loggedIn = true;
                                        System.out.println("\nYou have logged in successfully \n");
                                    } else {
                                        System.out.println("\nYou do not appear to be in the system. Maybe try registering. \n");
                                    }
                                } else {
                                    System.out.println("\nYou're already logged in! \n");
                                }

                                break;
                            }
                            case 7: {
                                //add to cart by ISBN found in the search
                                System.out.println("What is the ISBN of the book you'd like to add to your cart");
                                int addISBN = kb.nextInt();
                                
                                //make a prepared statement to search for the book and the number in stock by the ISBN
                                PreparedStatement ISBNSearch = conn.prepareStatement("select ISBN, available from book where ISBN=?");                               
                                ISBNSearch.setInt(1, addISBN);
                                ResultSet ISBNSet;
                                //attempt to execute the query
                                try {
                                    ISBNSet = ISBNSearch.executeQuery();
                                } catch (SQLException e) {
                                    System.out.println("\nSomething went wrong: please try again\n");
                                    break;
                                }
                                //if the book is found and the amount in stock is greater than 0 add it to the cart
                                if (ISBNSet.next()) {
                                    if (ISBNSet.getInt(2) > 0){
                                        cart.add(addISBN);
                                        System.out.println("Item added to cart");
                                    } else {
                                        //print out of stock
                                        System.out.println("Item found but it is out of stock");
                                    }
                                } else {
                                    //ISBN error
                                    System.out.println("\nThis ISBN does not appear to be in our system. Maybe ask the owner to order it\n");
                                }
                            

                                break;
                            }
                            case 8: {
                                //remove book from cart by ISBN
                                System.out.println("What ISBN did you want to remove from your cart?");
                                Integer removeISBN = kb.nextInt();
                                bufferClear = kb.nextLine();
                                //if the ISBN is in the cart, remove it, otherwise report not found
                                if (cart.remove(removeISBN)) {
                                    System.out.println("Book Removed");
                                } else {
                                    System.out.println("Book not found");
                                }
                                break;
                                
                            }
                            case 9: {
                                //view cart and then check out is an option if the user is logged in
                                //query the database by ISBN to display every book in the cart 
                                double totalCost = 0;
                                System.out.println("\nYour cart contains: ");
                                for(int i = 0; i < cart.size(); i++) {
                                    
                                    //make a prepared statement to load book data and accept an ISBN
                                    PreparedStatement ISBNSearch = conn.prepareStatement("select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=?");
                                    
                                    ISBNSearch.setInt(1, cart.get(i));
                                    ResultSet ISBNSet;
                                    //attempt to run the query
                                    try {
                                        ISBNSet = ISBNSearch.executeQuery();
                                    } catch (SQLException e) {
                                        System.out.println("\nInvalid search: please try again\n");
                                        break;
                                    }
                                    
                                    
                                    //print the cart from the result set
                                    
                                    if (ISBNSet.next()) {
                                        System.out.println("\n"+ ((i+1)) + ": " + "Title: " + ISBNSet.getString(1) + " Author: " + ISBNSet.getString(2) + " Genre: " + ISBNSet.getString(3) + " ISBN: " + ISBNSet.getInt(4) + " Publisher: " + ISBNSet.getString(5) + " Publisher ID: " + ISBNSet.getInt(6) + " Pages: " + ISBNSet.getInt(7) + " Price: $" + ISBNSet.getDouble(8) + "\n");
                                        totalCost+=ISBNSet.getDouble(8);
                                        
                                    }
                                
                                }
                                    //print the full cost of the cart
                                    System.out.println("Total Cost: " + totalCost);

                                    System.out.println("\n");
                                //find the current highest order number in the database to chose the next order number
                                PreparedStatement maxOrderNumStatement = conn.prepareStatement("select max(order_number) from store_order");
                                ResultSet maxOrder = maxOrderNumStatement.executeQuery();
                                int nextOrderNum;
                                //set the next order number to either 1 or the highest + 1
                                if (maxOrder.next()) {
                                    nextOrderNum = maxOrder.getInt(1) + 1;
                                } else {
                                    nextOrderNum = 1;
                                }
                                //ask the user if they want to check out
                                System.out.println("Would you like to check out? (y/n)\n");
                                String checkout = kb.nextLine();
                                
                                //user wants to check out 
                                if (checkout.equals("y")) {
                                    //can only check out if logged in
                                    if (loggedIn == true) {
                                    //request info from user
                                    System.out.println("Please input your billing info (Credit card number in the form: 1234567890123456)\n");
                                    Long billingInfo = kb.nextLong();
                                    bufferClear = kb.nextLine();
                                    System.out.println("Please input your shipping address in the form (123 house street, city, province, A1B2C3)\n");
                                    String shippingInfo = kb.nextLine();
                                    String userId = currentUserID;
                                    String trackingInfo = "Processing";

                                    //create a preparedStatement and add user values to it
                                    PreparedStatement addOrder = conn.prepareStatement("insert into store_order(order_number, billing_info, shipping_info, user_id, tracking_info) values (?,?,?,?,?)");
                                    addOrder.setInt(1, nextOrderNum);
                                    addOrder.setLong(2, billingInfo);
                                    addOrder.setString(3, shippingInfo);
                                    addOrder.setString(4, userId);
                                    addOrder.setString(5, trackingInfo);

                                    //attempt to insert into store_user 
                                    try{
                                        addOrder.executeUpdate();
                                        //System.out.println("\nOrder Added Successfully \n") ;
                                    } catch (SQLException e) {
                                        System.out.println("\nOrder error: please try again \n");
                                    }

                                    //this is BIG for loop. For every book in the cart, several things need to happen. 
                                    //first, the order number, ISBN is added to ordered_book table
                                    //then we query the database to load some data on the book
                                    //then we update the book to increment its sold, decrement it's available and increment its sold_this_month
                                    //then we query the publisher of the book for its current money made and update it to include the profits from selling the book
                                    // I will comment inside the loop as well
                                    for (int i = 0; i < cart.size(); i++) {

                                        //make the statement to add to ordered_books
                                        PreparedStatement addOrderedBooks = conn.prepareStatement("insert into ordered_books(order_number, ISBN) values(?,?)");
                                        addOrderedBooks.setInt(1, nextOrderNum);
                                        addOrderedBooks.setInt(2, cart.get(i));
                                        try{
                                            addOrderedBooks.executeUpdate();
                                            //System.out.println("\nOrder Added Successfully \n") ;
                                        } catch (SQLException e) {
                                            System.out.println("\nOrder error: please try again \n");
                                        }
                                        //make the statement to query the database to load some book data for further operations
                                        PreparedStatement loadBookStatement = conn.prepareStatement("SELECT available, sold, publisher_percentage, publisher_id, sold_last_month, retail_cost FROM book where ISBN=?");                                        
                                        loadBookStatement.setInt(1, cart.get(i));
                                        ResultSet loadBookSet;
                                        try {
                                            loadBookSet = loadBookStatement.executeQuery();
                                        } catch (SQLException e) {
                                            System.out.println("\nInvalid ISBN: please try again\n");
                                            break;
                                        }
                                        int numAvailable = 0;
                                        int numSold = 0;
                                        double publisher_percentage = 0;
                                        int publisher_id = 0;
                                        int num_sold_last_month = 0;
                                        double retail_cost = 0;
                                        //load the book data 
                                        if (loadBookSet.next()) {
                                            numAvailable = loadBookSet.getInt(1);
                                            numSold = loadBookSet.getInt(2);
                                            publisher_percentage = loadBookSet.getDouble(3);
                                            publisher_id = loadBookSet.getInt(4);
                                            num_sold_last_month = loadBookSet.getInt(5);
                                            retail_cost = loadBookSet.getDouble(6);

                                        }
                                        //make the prepared statement to update available, sold, sold_last_month
                                        String updateBookSql = "UPDATE book SET available=?, sold=?, sold_last_month=? where ISBN=?";
                                        PreparedStatement updateBookStatement = conn.prepareStatement(updateBookSql);
                                        if (numAvailable - 1  < RESTOCKNUM) {
                                            updateBookStatement.setInt(1, numAvailable-1 + num_sold_last_month);
                                        } else {
                                            updateBookStatement.setInt(1, numAvailable-1);
                                        }
                                        
                                        updateBookStatement.setInt(2, numSold+1);
                                        updateBookStatement.setInt(3, num_sold_last_month+1);
                                        updateBookStatement.setInt(4, cart.get(i));

                                        //attempt to execute the update
                                        try {
                                            updateBookStatement.executeUpdate();
                                            //System.out.println("\nBook updated Successfully\n");
                                        } catch (SQLException e) {
                                            System.out.println("\nSomething went wrong: please try again\n");
                                            break;
                                        }
                                        //load the money_made from publisher so it can be added to and updated
                                        PreparedStatement loadPublisherStatement = conn.prepareStatement("SELECT money_made FROM publisher where publisher_id=?");                                        
                                        loadPublisherStatement.setInt(1, publisher_id);
                                        ResultSet loadPublisherSet;
                                        //attempt to execute the query
                                        try {
                                            loadPublisherSet = loadPublisherStatement.executeQuery();
                                        } catch (SQLException e) {
                                            System.out.println("\nInvalid ISBN: please try again\n");
                                            break;
                                        }
                                        double money_made = 0;
                                        if (loadPublisherSet.next()) {
                                            money_made = loadBookSet.getDouble(1);

                                        }
                                        double percent = publisher_percentage / 100;
                                        double moneyToAdd = retail_cost * percent;
                                        //update publisher to include the new money
                                        String updatePublisher = "UPDATE publisher SET money_made=? where publisher_id=?";
                                        PreparedStatement updatePublisherStatement = conn.prepareStatement(updatePublisher);
                                        updatePublisherStatement.setDouble(1, money_made+moneyToAdd);
                                        updatePublisherStatement.setInt(2, publisher_id);
                                        //attempt to execute the update
                                        try {
                                            updatePublisherStatement.executeUpdate();
                                            //System.out.println("\nBook updated Successfully\n");
                                        } catch (SQLException e) {
                                            System.out.println("\nSomething went wrong: please try again\n");
                                            break;
                                        }
                                        if (numAvailable - 1  < RESTOCKNUM) {

                                        }
                                    } 

                                } else {
                                    System.out.println("Please login/register before attempting to check out");
                                }


                                } else {
                                    System.out.println("Returning to user menu");
                                    break;
                                }
                                System.out.println("\nOrder made successfully (Order Number: " + nextOrderNum + "\n");
                                break;
                            }
                            case 10: {
                                //view a preexisting order by its order number
                                System.out.println("What is the order number you would like to view?");
                                int order = kb.nextInt();
                                bufferClear = kb.nextLine();                          
                                //make a prepared statement to load the order by its order number
                                PreparedStatement orderView = conn.prepareStatement("select * from store_order where order_number=?");
                                orderView.setInt(1, order);
                                ResultSet orderDetails;
                                //execute the query
                                
                                orderDetails = orderView.executeQuery();
                                //print the order info
                                if (orderDetails.next()) {
                                    System.out.println("Here is your order information: \n");
                                    System.out.println("Order Number: " + order + "  Billing Info: " + orderDetails.getLong(2) + "  Shipping Info: " + orderDetails.getString(3) + "  User ID: " + orderDetails.getString(4) + "  Tracking Info: " + orderDetails.getString(5) + "\n" );
                                }

                                //make a prepared statement to query the database for books and display them
                                PreparedStatement booksInOrder = conn.prepareStatement("select title, author, genre, book.ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book, ordered_books, publisher where order_number=? and ordered_books.ISBN = book.ISBN");
                                booksInOrder.setInt(1, order);
                                //execute the query
                                ResultSet books = booksInOrder.executeQuery();
                                int counter = 1;
                                //display the books
                                System.out.println("Here are the books in your order:");
                                while (books.next()) {
                                    System.out.println("\n"+ counter + ": " + "Title: " + books.getString(1) + " Author: " + books.getString(2) + " Genre: " + books.getString(3) + " ISBN: " + books.getInt(4) + " Publisher: " + books.getString(5) + " Publisher ID: " + books.getInt(6) + " Pages: " + books.getInt(7) + " Price: $" + books.getDouble(8) + "\n");
                                    counter++;
                                }

                                


                                break;
                            }
                            case 11: {
                                //extra in case I decide to add something 
                                break;
                            }
                            case 0: {
                                userQuit = true;
                                break;
                            }
                        }
                    }
                    

                } else if (userType == 2) {
                    //opens owner menu
                    boolean ownerQuit = false;
                    //owner menu loop
                    while (ownerQuit == false) {
                        //list owner menu options
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
                            //add a book to the database 
                            case 1: {
                                //make a prepared statement that takes all the book valuea and inserts them
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
                                //add user data to prepared statement 
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
                                //attempt to execute the update
                                try{
                                addStatement.executeUpdate();    
                                System.out.println("Book added successfully \n") ;
                                } catch (SQLException e) {
                                    System.out.println("Error adding book: Please try again \n");
                                }
                                break;
                            }
                            //delete a book from the database 
                            case 2: {
                                //make a prepared statement to delete a book by the ISBN
                                String removeSql = "DELETE FROM book WHERE ISBN=?";
                                PreparedStatement removeStatement = conn.prepareStatement(removeSql);
                                System.out.println("What is the ISBN of the book you want to remove?"); 
                                int removeBook = kb.nextInt();
                                bufferClear = kb.nextLine();
                                removeStatement.setInt(1, removeBook);
                                //attempt to execute the update
                                try{
                                removeStatement.executeUpdate();
                                System.out.println("\nBook removed successfully \n") ;
                                } catch (SQLException e) {
                                    System.out.println("\nRemoval Error: Please try again \n");
                                }
                                break;

                                
                            }
                            //add a publisher to the database 
                            case 3: {
                                //make prepared statement to add publisher to database with variables for each user input
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
                                double publisherMoneyMade = 0;
                                System.out.println("\nWhat is the publisher id? \n");
                                int publisher_id = kb.nextInt();
                                bufferClear = kb.nextLine();
                                //add user input to prepared statement 
                                addPubStatement.setString(1, publisherName);
                                addPubStatement.setString(2, publisherAddress);
                                addPubStatement.setString(3, publisherEmail);
                                addPubStatement.setInt(4, publisherPhoneNumber);
                                addPubStatement.setDouble(5, publisherMoneyMade);
                                addPubStatement.setInt(6, publisher_id);
                                //attempt to execute the update
                                try{
                                    addPubStatement.executeUpdate();
                                    System.out.println("\nPublisher added successfully. \n") ;
                                    } catch (SQLException e) {
                                        System.out.println("\n Failed to add publisher: Please try again \n");
                                    }
                                break;
                                
                            }
                            //remove publisher from the database by publisher id
                            case 4: {
                                //construct the prepared statement to remove the publisher from the database with publisher id
                                String removeSql = "DELETE FROM publisher WHERE publisher_id=?";
                                PreparedStatement removeStatement = conn.prepareStatement(removeSql);
                                System.out.println("\nWhat is the publisher_id of the publisher you want to remove?\n"); 
                                System.out.println("Note: To remove a publisher you must first remove all associated books");
                                int removePublisher = kb.nextInt();
                                bufferClear = kb.nextLine();
                                removeStatement.setInt(1, removePublisher);

                                //attempt to execute the query 
                                try{
                                removeStatement.executeUpdate();
                                System.out.println("\nPublisher removed successfully \n") ;
                                } catch (SQLException e) {
                                    System.out.println("\nRemoval Error: Please try again \n");
                                }

                                break;
                                
                            }
                            //increase the number in stock of a certain book
                            case 5: {
                                int toAdd = 0;
                                //make the prepared statement to update available to available + n
                                String addInventory = "UPDATE book SET available=? where ISBN=?";
                                PreparedStatement addInventoryStatement = conn.prepareStatement(addInventory);
                                //first, query the database for the current value of available
                                PreparedStatement availableStatement = conn.prepareStatement("SELECT available FROM book where ISBN=?");
                                System.out.println("What is the ISBN of the book you'd like to add more inventory of?");
                                int inventoryISBN = kb.nextInt();
                                bufferClear = kb.nextLine();
                                availableStatement.setInt(1, inventoryISBN);
                                ResultSet availableSet;
                                //execute the query
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
                                //execute the query to update available to available + user input
                                try {
                                    addInventoryStatement.executeUpdate();
                                    System.out.println("\nInventory Added Successfully\n");
                                } catch (SQLException e) {
                                    System.out.println("\nSomething went wrong: please try again\n");
                                    break;
                                }

                                break;
                                
                            }
                            //show reports
                            case 6: {
                                //report menu 
                                boolean reportQuit = false;
                                while (reportQuit == false) {
                                    //give owner some options
                                    System.out.println("Welcome to the report menu! \n");
                                    System.out.println("Would you like to: \n");
                                    System.out.println("(1): View sales vs. expenditures \n");
                                    System.out.println("(2): View sales per genre \n");
                                    System.out.println("(3): View sales per author \n");
                                    System.out.println("(4): View sales per publisher \n");  
                                    System.out.println("(0): Return to owner menu \n");      
                                    int reportMenuChoice = kb.nextInt();
                                    bufferClear = kb.nextLine();
                                    //display the sum of all sales compared to the sum of all purchases 
                                    switch (reportMenuChoice){   
                                        case 1: {
                                            //query for the sum of all sales
                                            PreparedStatement totalSales = conn.prepareStatement("select sum(sold) from book");
                                            ResultSet sales = totalSales.executeQuery();
                                            int sold = 0;
                                            if(sales.next()) {
                                                sold = sales.getInt(1);
                                            }
                                            //query for the sum of all available
                                            PreparedStatement totalAvailable = conn.prepareStatement("select sum(available) from book");
                                            ResultSet available = totalAvailable.executeQuery();
                                            int availableNum = 0;
                                            if(available.next()) {
                                                availableNum = available.getInt(1);
                                            }
                                            //output the results 
                                            System.out.println("Sales vs Expenditure Report:");
                                            System.out.println("Total Sold: " + sold + "\n");
                                            System.out.println("Total Bought: " + (sold + availableNum) + "\n");


                                            break;
                                        }
                                        case 2: {
                                            //query the database for the sum of sales per genre in the database
                                            PreparedStatement salesPerGenre = conn.prepareStatement("select genre, sum(sold) from book group by genre");
                                            ResultSet salesByGenre = salesPerGenre.executeQuery();
                                            System.out.println("Sales per genre report:");
                                            //output the report
                                            while(salesByGenre.next()) {
                                                System.out.println("Genre: " + salesByGenre.getString(1) + "  Sold: " + salesByGenre.getInt(2) + "\n");
                                            }
                                            break;
                                        }
                                        case 3: {
                                            //query the database for the sum of sales per author in the database
                                            PreparedStatement salesPerAuthor = conn.prepareStatement("select author, sum(sold) from book group by author");
                                            ResultSet salesByAuthor = salesPerAuthor.executeQuery();
                                            System.out.println("Sales per author report: \n");
                                            //output the database
                                            while(salesByAuthor.next()) {
                                                System.out.println("Author: " + salesByAuthor.getString(1) + "  Sold: " + salesByAuthor.getInt(2) + "\n");
                                            }
                                            
                                            break;
                                        }
                                        case 4: {
                                            //query the database for the sum of sales per publisher in the database
                                            PreparedStatement salesPerPublisher = conn.prepareStatement("select pub_name, sum(sold) from book, publisher group by pub_name");
                                            ResultSet salesByPublisher = salesPerPublisher.executeQuery();
                                            System.out.println("Sales per publisher report: \n");
                                            //output the results
                                            while(salesByPublisher.next()) {
                                                System.out.println("Publisher: " + salesByPublisher.getString(1) + "  Sold: " + salesByPublisher.getInt(2) + "\n");
                                            }
                                            
                                            break;
                                        }
                                        case 0: {
                                            reportQuit = true;
                                            break;
                                        }
                                    }

                                }
                            }
                                
                            
                            case 7: {

                                break;
                                
                            }
                            //if user wants to quit then quit
                            case 0: {
                                ownerQuit = true;

                                break;
                                
                            }
                        }
                    }
                //if user wants to quit then quit
                } else if (userType == 3) {
                    quit = true;
                }







            }
            
            kb.close();
    }
    

}

