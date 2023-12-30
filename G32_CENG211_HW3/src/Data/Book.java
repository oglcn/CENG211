package Data;

import java.text.ParseException;

public class Book extends BorrowableItem {
    // Constants
    private String itemType="book";

    // Constructor
    public Book(String itemCode, String title, String priority,
                String author, String publisher, String startBorrow, String endBorrow) throws ParseException {
        super(itemCode, title, priority, author, publisher, startBorrow, endBorrow);
        super.setBORROWING_CHARGE(5);
        super.setPenalty(5);
        super.setBORROWING_DAY_LIMIT(10);
    }

    // Copy constructor
    public Book(Book other) throws ParseException {
        super(other); // Call the copy constructor of the superclass
        super.setBORROWING_CHARGE(5);
        super.setPenalty(5);
        super.setBORROWING_DAY_LIMIT(10);
    }
    public String getItemType() {return itemType;}

    // Override method to search by title and item type
    @Override
    public boolean searchByTitleAndItemType(String title, String itemType) {
        return super.searchByTitle(title) && getItemType().equals(itemType);
    }

    // Override toString method for a string representation of the object
    @Override
    public String toString() {
        return super.toString()+" Exceed: ";
    }


}