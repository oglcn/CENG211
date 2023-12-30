package Data;

import java.text.ParseException;
import java.util.Objects;

public class Magazine extends BorrowableItem{

    // Constants
    private String itemType = "magazine";

    // Constructor
    public Magazine(String itemCode, String title, String priority,
                String author, String publisher, String startBorrow, String endBorrow) throws ParseException {
        super(itemCode, title, priority, author, publisher, startBorrow, endBorrow);
        super.setBORROWING_CHARGE(10);
        super.setBORROWING_DAY_LIMIT(7);
        super.setPenalty(2);
    }
    // Copy constructor
    public Magazine(Magazine other) throws ParseException {
        super(other); // Call the copy constructor of the superclass
        this.itemType = other.getItemType(); // Copy the specific attribute of the subclass
    }

    @Override
    public Integer getBorrowingCharge() {
        return BORROWING_CHARGE;
    }

    // Getter method for item type
    public String getItemType() {
        return itemType;
    }

    // Override method to search by title and item type
    @Override
    public boolean searchByTitleAndItemType(String title, String itemType) {
        return this.title.equals(title) && this.itemType.equals(itemType);
    }

    // Override equals method to check object equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(itemType, magazine.itemType);
    }


    // Override toString method for a string representation of the object
    @Override
    public String toString() {
        return super.toString();
    }
    // Override method to calculate borrowing charge

}