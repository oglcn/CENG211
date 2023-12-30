package Data;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
// Abstract class representing a borrowable item
abstract public class BorrowableItem implements Searchable {
    protected   int BORROWING_CHARGE;
    protected   int BORROWING_DAY_LIMIT;
    protected   int PENALTY;
    protected int priority;
    protected String itemCode;
    protected String title;
    protected String publisher;
    protected String author;
    protected Date startBorrow;
    protected Date endBorrow;
    protected int numberOfBorrowingDays;
    protected int borrowingDayLimit;
    //Parameterized constructor
    public BorrowableItem(String itemCode, String title, String priority,
                          String author, String publisher, String startBorrow, String endBorrow) throws ParseException {
        this.itemCode = itemCode;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.priority = setPriority(priority);
        this.startBorrow = setDate(startBorrow);
        this.endBorrow = setDate(endBorrow);
        this.numberOfBorrowingDays = calculateBorrowingDays();
    }
    protected void setPenalty(int penalty) {
        PENALTY=penalty;
    }
    protected void setBORROWING_CHARGE(int charge){
        BORROWING_CHARGE=charge;
    }
    protected void setBORROWING_DAY_LIMIT(int day_limit){
        BORROWING_DAY_LIMIT=day_limit;
    }
    // Copy constructor
    public BorrowableItem(BorrowableItem other) throws ParseException {
        this.itemCode = other.itemCode;
        this.author = other.author;
        this.title = other.title;
        this.publisher = other.publisher;
        this.priority = other.priority;
        this.startBorrow = new Date(other.startBorrow.getTime());
        this.endBorrow = new Date(other.endBorrow.getTime());
        this.numberOfBorrowingDays = other.numberOfBorrowingDays;
    }
    // Set priority based on a string representation
    private int setPriority(String priority) {
        return switch (priority) {
            case "invaluable" -> 3;
            case "highly significant" -> 2;
            case "noteworthy" -> 1;
            default -> 0;
        };
    }
    // Parse string to date
    private Date setDate(String date) throws ParseException {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }
    // Calculate the number of borrowing days
    private int calculateBorrowingDays() {
        long differenceInMilliseconds = Math.abs(endBorrow.getTime() - startBorrow.getTime());
        long differenceInDays = differenceInMilliseconds / (24 * 60 * 60 * 1000);
        return (int) differenceInDays;
    }
    // Method to search by title
    public boolean searchByTitle(String title) {
        return this.title.equals(title);
    }
    // Abstract method to search by title and item type
    abstract public boolean searchByTitleAndItemType(String title, String itemType);
    public Integer getBorrowingCharge() {
        return BORROWING_CHARGE;
    }
    public int getNumberOfBorrowingDays() {
        return numberOfBorrowingDays;
    }
    public int getPriority() {
        return priority;
    }

    // Override equals method to check object equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowableItem that = (BorrowableItem) o;
        return priority == that.priority &&
                numberOfBorrowingDays == that.numberOfBorrowingDays &&
                Objects.equals(itemCode, that.itemCode) &&
                Objects.equals(title, that.title) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(author, that.author) &&
                Objects.equals(startBorrow, that.startBorrow) &&
                Objects.equals(endBorrow, that.endBorrow);
    }
    // Override toString method for a string representation of the object
    @Override
    public String toString() {
        String result = "Item Number: "+itemCode+" Title: "+title+  " Item Type: " + getItemType() + " Borrowing Days: "+ numberOfBorrowingDays+" ";
        return  result;
    }
    abstract public String getItemType();



}
 