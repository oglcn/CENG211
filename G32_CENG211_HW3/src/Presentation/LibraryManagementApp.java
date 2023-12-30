package Presentation;

import java.text.ParseException;

import Data.CustomerList;
import Data.FileIO;

/**
 * A class containing the main method to demonstrate the functionality of the application.
 */
public class LibraryManagementApp {
    /**
     * The main method that reads data from a file, creates a CustomerList, and performs some operations on it.
     *
     * @param args Command-line arguments (not used in this example).
     * @throws ParseException If an error occurs during parsing.
     */
    public static void main(String[] args) throws ParseException {
        // Read data from the "items.csv" file and create a CustomerList
        CustomerList customerList = FileIO.readFile("items.csv");

        // Print the list of customers
        customerList.printCustomers();

        // Search for items with the title "History of Art" and item type "book"
        customerList.searchByTitleAndItemType("History of Art", "book");

        // Search for items with the title "Animal Farm"
        customerList.searchByTitle("Animal Farm");
    }
}
